package org.abhijitsarkar.kalgs4.sorting

import edu.princeton.cs.algs4.IndexMinPQ

/**
 * @author Abhijit Sarkar
 */
fun <Key : Comparable<Key>> indexedMinPQ(n: Int): IndexedMinPQ<Key> = IndexedMinPQImpl(n)

// TODO: Implement my own IndexedPQ
class IndexedMinPQImpl<Key : Comparable<Key>>(private val n: Int) : IndexedMinPQ<Key> {
    private val delegate = IndexMinPQ<Key>(n)

    override val isEmpty: Boolean
        get() = delegate.isEmpty
    override val size: Int
        get() = delegate.size()

    override fun insert(i: Int, key: Key) {
        delegate.insert(i, key)
    }

    override fun decreaseKey(i: Int, key: Key) {
        delegate.decreaseKey(i, key)
    }

    override fun deleteMin(): Int {
        return delegate.delMin()
    }

    override fun contains(i: Int): Boolean {
        return delegate.contains(i)
    }

    override fun iterator(): Iterator<Int> {
        return IndexedMinPQIterator(delegate.iterator())
    }

    inner class IndexedMinPQIterator(private val it: Iterator<Int>) : Iterator<Int> by it
}