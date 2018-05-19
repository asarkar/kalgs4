package org.abhijitsarkar.kalgs4.sorting

/**
 * @author Abhijit Sarkar
 */
fun <Key> minPQ(): MinPQ<Key> = MinPQImpl()

class MinPQImpl<Key> : MinPQ<Key> {
    private val comparator: Comparator<Key>

    private var keys: Array<Key?>
    private var n: Int = 0

    constructor(keys: Array<Key?>, comparator: Comparator<Key>? = null) : this(keys.size, comparator) {
        keys
                .filter { it != null }
                .forEach {
                    insert(it!!)
                }
    }

    @Suppress("UNCHECKED_CAST")
    constructor(n: Int = 0, comparator: Comparator<Key>? = null) {
        this.keys = arrayOfNulls<Any>(n) as Array<Key?>
        this.comparator = comparator ?: Comparator { o1, o2 ->
            if (o1 is Comparable<*>) {
                @Suppress("UNCHECKED_CAST")
                (o1 as Comparable<Key>).compareTo(o2)
            } else {
                throw IllegalArgumentException("Key is not Comparable and no Comparator is supplied")
            }
        }
    }

    override val isEmpty: Boolean
        get() = size == 0

    private val isFull: Boolean
        get() = size == keys.size

    override val size: Int
        get() = n

    override fun insert(k: Key) {
        if (isFull)
            resize(kotlin.math.max(keys.size * 2, 2))

        keys[n++] = k

        // intuitively, half of the nodes are at the leaf level of a binary tree. if we start there and move up,
        // we will progressively restore the heap property for the whole tree
        swim(parent(n - 1))
    }

    private fun resize(newSize: Int) {
        keys = keys.copyOf(newSize)
    }

    override fun deleteMin(): Key {
        if (isEmpty) throw NoSuchElementException()

        val min = keys[0]
        swap(0, n - 1)
        keys[--n] = null

        sink(0)

        if (!isEmpty && size == keys.size / 4)
            resize(keys.size / 2)

        return min!!
    }

    private tailrec fun swim(k: Int) {
        if (k < 0 || isHeap(k)) return

        val (left, right) = children(k)
        val min = min(left, right) ?: return

        swap(k, min)
        swim(parent(k))
    }

    private fun sink(k: Int) {
        if (k >= n || isHeap(k)) return

        val (left, right) = children(k)
        val min = min(left, right) ?: return

        swap(k, min)

        sink(left)
        sink(right)
    }

    private fun parent(k: Int): Int {
        return kotlin.math.max(0, (k - 1) / 2)
    }

    private fun children(k: Int): Pair<Int, Int> {
        val left = 2 * k + 1
        val right = left + 1

        return (left to right)
    }

    private fun swap(i: Int, j: Int) {
        val tmp = keys[i]
        keys[i] = keys[j]
        keys[j] = tmp
    }

    private fun isHeap(k: Int): Boolean {
        val (left, right) = children(k)

        return when {
            left < n && right < n -> isNotGreater(k, left) && isNotGreater(k, right)
            left < n -> isNotGreater(k, left)
            right < n -> isNotGreater(k, right)
            else -> true
        }
    }

    private fun min(i: Int, j: Int): Int? {
        return when {
            i < n && j < n -> if (min(keys[i]!!, keys[j]!!) == keys[i]) i else j
            i < n -> i
            j < n -> j
            else -> throw IllegalStateException()
        }
    }

    private fun min(k1: Key, k2: Key): Key {
        @Suppress("UNCHECKED_CAST")
        return (arrayOf(k1 as Any, k2 as Any) as Array<Key>).minWith(comparator)!!
    }

    private fun isNotGreater(i: Int, j: Int): Boolean {
        return min(keys[i]!!, keys[j]!!) == keys[i]
    }

    override fun iterator(): Iterator<Key> {
        return MinPQIterator()
    }

    private inner class MinPQIterator : Iterator<Key> {
        private var current: Int = 0
        override fun hasNext(): Boolean {
            return current < n
        }

        override fun next(): Key {
            if (!hasNext()) throw NoSuchElementException()

            return keys[current++]!!
        }
    }
}