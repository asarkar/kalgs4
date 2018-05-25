package org.abhijitsarkar.kalgs4.searching

import org.abhijitsarkar.kalgs4.emptyQueue
import org.abhijitsarkar.kalgs4.searching.Node.Companion.Color.BLACK
import org.abhijitsarkar.kalgs4.searching.Node.Companion.Color.RED

class Node<Key : Comparable<Key>>(
        val key: Key,
        var left: Node<Key>? = null,
        var right: Node<Key>? = null,
        var color: Color = Color.RED
) {
    companion object {
        enum class Color {
            RED, BLACK
        }
    }

    fun isRightRotationReqd(): Boolean {
        return left?.color == Color.RED && left?.left?.color == Color.RED
    }

    fun isLeftRotationReqd(): Boolean {
        return right?.color == Color.RED
    }

    fun isColorFlipReqd(): Boolean {
        return left?.color == Color.RED && right?.color == Color.RED
    }

    fun rotateRight(): Node<Key> {
        val x = left!!
        left = x.right
        x.right = this
        x.color = color
        color = RED

        return x
    }

    fun rotateLeft(): Node<Key> {
        val x = right!!
        right = x.left
        x.left = this
        x.color = color
        color = RED

        return x
    }

    fun flipColors(): Node<Key> {
        color = RED
        left!!.color = BLACK
        right!!.color = BLACK

        return this
    }

    override fun toString(): String {
        return "Node(key=$key, left=$left, right=$right, color=$color)"
    }
}

class RedBlackBST<Key : Comparable<Key>> {
    private var root: Node<Key>? = null

    fun put(key: Key) {
        fun put(key: Key, node: Node<Key>?): Node<Key> {
            var newNode = when {
                node == null -> Node(key)
                key < node.key -> put(key, node.left).let {
                    println("Setting ${node.key}.left = ${it.key}")
                    node.left = it
                    node
                }
                else -> put(key, node.right).let {
                    println("Setting ${node.key}.right = ${it.key}")
                    node.right = it
                    node
                }
            }

            do {
                if (newNode!!.isColorFlipReqd()) {
                    println("Flipping colors of ${newNode.key}")
                    newNode = newNode.flipColors()
                }
                if (newNode.isLeftRotationReqd()) {
                    println("Rotating ${newNode.key} left")
                    newNode = newNode.rotateLeft()
                }
                if (newNode.isRightRotationReqd()) {
                    println("Rotating ${newNode.key} right")
                    newNode = newNode.rotateRight()
                }
            } while (newNode!!.isColorFlipReqd() || newNode.isLeftRotationReqd() || newNode.isRightRotationReqd())

            return newNode
        }
        println("Inserting $key")
        root = put(key, root).also {
            println("Setting color of ${it.key} to BLACK")
            it.color = BLACK
        }
    }

    private inner class InternalNode(val node: Node<Key>, val level: Int)

    fun levelOrder(): Iterable<Pair<Key, Int>> {
        val yetToVisit = emptyQueue<InternalNode>()
        val visited = emptyQueue<Pair<Key, Int>>()

        root?.also { yetToVisit.enqueue(InternalNode(it, 1)) }

        while (!yetToVisit.isEmpty) {
            do {
                val node = yetToVisit.dequeue()
                        .also { visited.enqueue(it.node.key to it.level) }
                listOfNotNull(node.node.left, node.node.right)
                        .map { InternalNode(it, node.level + 1) }
                        .forEach(yetToVisit::enqueue)
            } while (!yetToVisit.isEmpty && yetToVisit.peek().level == node.level)
        }

        return visited
    }
}