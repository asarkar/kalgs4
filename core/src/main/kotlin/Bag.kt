package org.abhijitsarkar.kalgs4

/**
 * @author Abhijit Sarkar
 */
interface Bag<Item> : Iterable<Item> {
    val isEmpty: Boolean
        get() = size == 0

    val size: Int
}

interface MutableBag<Item> : Bag<Item> {
    fun add(item: Item): Boolean
}