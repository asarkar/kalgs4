package org.abhijitsarkar.kalgs4.sorting

/**
 * @author Abhijit Sarkar
 */
interface MinPQ<Key> : Iterable<Key> {
    val isEmpty: Boolean
    val size: Int
    fun insert(k: Key)
    fun deleteMin(): Key
}