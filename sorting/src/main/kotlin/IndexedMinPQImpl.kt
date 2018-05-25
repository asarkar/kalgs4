package org.abhijitsarkar.kalgs4.sorting

/**
 * @author Abhijit Sarkar
 */
class IndexedMinPQImpl<Key : Comparable<Key>> internal constructor(n: Int) : IndexedMinPQ<Key> {
    companion object {
        private const val UNOCCUPIED = -1
    }

    @Suppress("UNCHECKED_CAST")
    private val keys: Array<Key?> = arrayOfNulls<Comparable<*>>(n) as Array<Key?>
    /*
     * given integer i, it's key is keys[i]
     * the heap index for the key corresponding to i is heapIndices[i]
     * heap[i] is the index (into keys[]) of the key corresponding to i
     *
     * invariants:
     * heapIndices[heap[i]] = i
     * heap[heapIndices[i]] = i
     *
     * heapIndices is needed to support decreaseKey operation
     * we can't keep the keys directly on the heap because we need to keep track of the original indices
     * supplied by the client for all operations involving an index
     */
    private val heap: IntArray = IntArray(n)
    private val heapIndices: IntArray = IntArray(n) { UNOCCUPIED }

    private var s: Int = 0

    override val isEmpty: Boolean
        get() = size == 0
    override val size: Int
        get() = s

    override fun insert(i: Int, key: Key) {
        require(!contains(i)) { "Index $i is already associated with a key" }

        keys[i] = key
        heap[s++] = i
        heapIndices[i] = s - 1
        swim(parent(s - 1))
    }

    override fun decreaseKey(i: Int, key: Key) {
        require(contains(i)) { "Index $i has no associated key" }

        keys[i] = key
        // if the key decreased, it doesn't need to sink down, but may need to swim up to maintain heap property
        swim(parent(heapIndices[i]))
    }

    override fun deleteMin(): Int {
        if (isEmpty) throw NoSuchElementException()

        val min = heap[0]
        swap(0, s - 1)
        heap[--s] = UNOCCUPIED
        heapIndices[min] = UNOCCUPIED

        sink(0)

        return min
    }

    override fun contains(i: Int): Boolean {
        return heapIndices[i] != UNOCCUPIED
    }

    private tailrec fun swim(k: Int) {
        if (isHeap(k)) return

        val (left, right) = children(k)
        val min = min(left, right) ?: return

        swap(k, min)
        swim(parent(k))
    }

    private fun sink(k: Int) {
        if (isHeap(k)) return

        val (left, right) = children(k)
        val min = min(left, right) ?: return

        swap(k, min)

        sink(left)
        sink(right)
    }

    private fun parent(k: Int): Int {
        return (k - 1) / 2
    }

    private fun children(k: Int): Pair<Int, Int> {
        val left = 2 * k + 1
        val right = left + 1

        return (left to right)
    }

    private fun swap(i: Int, j: Int) {
        heap[i] = heap[j].apply {
            heap[j] = heap[i]
        }

        // as explained before
        heapIndices[heap[i]] = i
        heapIndices[heap[j]] = j
    }

    private fun isHeap(k: Int): Boolean {
        if (parent(k) >= s) return true

        val (left, right) = children(k)

        return when {
            left < s && right < s -> isNotGreater(k, left) && isNotGreater(k, right)
            left < s -> isNotGreater(k, left)
            right < s -> isNotGreater(k, right)
            else -> true
        }
    }

    private fun min(i: Int, j: Int): Int? {
        return when {
            i < s && j < s -> if (min(keys[heap[i]]!!, keys[heap[j]]!!) == keys[heap[i]]) i else j
            i < s -> i
            j < s -> j
            else -> throw IllegalStateException()
        }
    }

    private fun min(k1: Key, k2: Key): Key {
        return if (k1 < k2) k1 else k2
    }

    private fun isNotGreater(i: Int, j: Int): Boolean {
        return min(keys[heap[i]]!!, keys[heap[j]]!!) == keys[heap[i]]
    }

    override fun iterator(): Iterator<Int> {
        return IndexedMinPQ()
    }

    override fun toString(): String {
        return """IndexedMinPQ(${
        heap
                .withIndex()
                .filter { it.value >= 0 }
                .joinToString { "(${it.index},${keys[it.value]})" }
        })"""
    }

    private inner class IndexedMinPQ : Iterator<Int> {
        private var current: Int = 0
        override fun hasNext(): Boolean {
            return current < s
        }

        override fun next(): Int {
            if (!hasNext()) throw NoSuchElementException()

            return heap[current++]
        }
    }
}