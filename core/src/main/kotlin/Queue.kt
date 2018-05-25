package org.abhijitsarkar.kalgs4

/**
 * @author Abhijit Sarkar
 */
interface Queue<Item> : Iterable<Item> {
    fun enqueue(i: Item)
    fun dequeue(): Item
    fun peek(): Item
    val isEmpty: Boolean
        get() = size == 0

    val size: Int
}