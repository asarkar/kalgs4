package org.abhijitsarkar.kalgs4

/**
 * @author Abhijit Sarkar
 */

fun <Item> mutableBagOf(vararg items: Item): MutableBag<Item> = LinkedBag<Item>(*items)

class LinkedBag<Item>(vararg items: Item) : MutableBag<Item> {
    private var n: Int = 0
    private var first: Node<Item>? = null

    init {
        items.forEach { add(it) }
    }

    data class Node<Item>(val item: Item, val next: Node<Item>?)

    override val size: Int
        get() = n

    override fun add(item: Item): Boolean {
        Node(item, first).also {
            first = it
            ++n
        }

        return true
    }

    override fun iterator(): Iterator<Item> {
        return LinkedBagIterator(first)
    }

    private class LinkedBagIterator<Item>(private var current: Node<Item>?) : Iterator<Item> {
        override fun hasNext(): Boolean {
            return current != null
        }

        override fun next(): Item {
            if (!hasNext()) throw NoSuchElementException()

            return current!!.item.also {
                current = current!!.next
            }
        }
    }
}