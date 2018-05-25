package org.abhijitsarkar.kalgs4

/**
 * @author Abhijit Sarkar
 */
class LinkedStack<Item> : Stack<Item> {
    private var n = 0
    private var first: Node? = null

    override fun push(i: Item) {
        Node(i, first).also {
            first = it
            ++n
        }
    }

    override fun pop(): Item {
        return when (size) {
            0 -> throw NoSuchElementException()
            else -> first!!.item.also {
                first = first!!.next
                --n
            }
        }
    }

    override val size: Int
        get() = n

    override fun iterator(): Iterator<Item> {
        return StackIterator()
    }

    inner class Node(val item: Item, var next: Node?)

    inner class StackIterator : Iterator<Item> {
        private var current: Node? = first

        override fun hasNext(): Boolean {
            return current != null
        }

        override fun next(): Item {
            return current!!.item.also { current = current!!.next }
        }
    }
}