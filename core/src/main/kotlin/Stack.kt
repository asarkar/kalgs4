package org.abhijitsarkar.kalgs4

/**
 * @author Abhijit Sarkar
 */
interface Stack<Item> : Iterable<Item> {
    fun push(i: Item)
    fun pop(): Item
    val isEmpty: Boolean
        get() = size == 0

    val size: Int
}