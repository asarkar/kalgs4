package org.abhijitsarkar.kalgs4.sorting

/**
 * @author Abhijit Sarkar
 */
interface IndexedMinPQ<Key : Comparable<Key>> : Iterable<Int> {
    val isEmpty: Boolean
    val size: Int
    fun insert(i: Int, key: Key)
    fun decreaseKey(i: Int, key: Key)
    fun deleteMin(): Int
    fun contains(i: Int): Boolean
}