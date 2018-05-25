package org.abhijitsarkar.kalgs4.sorting

/**
 * @author Abhijit Sarkar
 */
fun <Key> emptyMinPQ(): MinPQ<Key> = MinPQImpl(0, null)

fun <Key> minPQ(n: Int = 0, comparator: Comparator<Key>? = null): MinPQ<Key> = MinPQImpl(n, comparator)

fun <Key> minPQOf(keys: Array<Key?>, comparator: Comparator<Key>? = null): MinPQ<Key> = MinPQImpl(keys, comparator)

fun <Key : Comparable<Key>> indexedMinPQ(n: Int): IndexedMinPQ<Key> = IndexedMinPQImpl(n)