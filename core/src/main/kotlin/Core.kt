package org.abhijitsarkar.kalgs4

/**
 * @author Abhijit Sarkar
 */
fun <Item> mutableBagOf(vararg items: Item): MutableBag<Item> = LinkedBag(*items)

fun <Item> emptyBag(): MutableBag<Item> = LinkedBag()

fun <Item> emptyQueue(): Queue<Item> = LinkedQueue()

fun <Item> emptyStack(): Stack<Item> = LinkedStack()

fun unionFind(n: Int): UnionFind = WeightedQuickUnionUF(n)
