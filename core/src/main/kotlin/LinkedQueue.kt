package org.abhijitsarkar.kalgs4

/**
 * @author Abhijit Sarkar
 */
class LinkedQueue<Item> : Queue<Item> {
    private var n = 0
    private var first: Node? = null
    private var last: Node? = null

    override fun enqueue(i: Item) {
        val node = when (size) {
            0 -> Node(i).apply {
                first = this
            }
            1 -> Node(i).apply {
                first!!.next = this
            }
            else -> Node(i).apply {
                last!!.next = this
            }
        }
        last = node
        ++n
    }

    override fun dequeue(): Item {
        val item = when (size) {
            0 -> throw NoSuchElementException()
            1 -> first!!.item.apply {
                first = null
                last = null
            }
            else -> first!!.item.apply {
                first = first!!.next
            }
        }
        --n
        return item!!
    }

    override fun peek(): Item {
        return when (size) {
            0 -> throw NoSuchElementException()
            else -> first!!.item!!
        }
    }

    override val size: Int
        get() = n

    override fun iterator(): Iterator<Item> {
        return QueueIterator()
    }

    inner class Node(var item: Item?, var next: Node?) {
        constructor(item: Item) : this(item, null)
    }

    inner class QueueIterator : Iterator<Item> {
        private var current: Node? = first

        override fun hasNext(): Boolean {
            return current != null
        }

        override fun next(): Item {
            return current!!.item.also { current = current!!.next }!!
        }
    }
}