package org.abhijitsarkar.kalgs4.sorting

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertTrue

/**
 * @author Abhijit Sarkar
 */
class IndexedMinPQImplTest {
    private val keys = listOf(3, 5, 9, 12, 6, 10)

    @Test
    fun `should start with an empty queue and then add and remove items`() {
        val q = indexedMinPQ<Int>(6)
        keys.withIndex().forEach { q.insert(it.index, it.value) }

        println("After init: $q")
        assertEquals(0, q.deleteMin())
        assertEquals(5, q.size)
        println("After deleteMin: $q")
        assertEquals(1, q.deleteMin())
        assertEquals(4, q.size)
        println("After deleteMin: $q")

        q.decreaseKey(2, 2)
        assertEquals(4, q.size)
        println("After decreasing key for i = 2 to 2: $q")
        q.decreaseKey(5, 3)
        assertEquals(4, q.size)
        println("After decreasing key for i = 5 to 3: $q")
        assertEquals(2, q.deleteMin())
        assertEquals(3, q.size)
        println("After deleteMin: $q")
        assertEquals(5, q.deleteMin())
        assertEquals(2, q.size)
        println("After deleteMin: $q")
        assertEquals(4, q.deleteMin())
        assertEquals(1, q.size)
        println("After deleteMin: $q")
        assertEquals(3, q.deleteMin())
        assertTrue(q.isEmpty)
        println("After deleteMin: $q")

        q.insert(0, 2)
        assertEquals(1, q.size)
        println("After inserting key for i = 0 to 2: $q")
        q.insert(1, 1)
        assertEquals(2, q.size)
        println("After inserting key for i = 1 to 1: $q")
        assertEquals(1, q.deleteMin())
        assertEquals(1, q.size)
        println("After deleteMin: $q")
        assertEquals(0, q.deleteMin())
        assertTrue(q.isEmpty)
        println("After deleteMin: $q")
    }

    @Test
    fun `should throw exception if inserting for existing index`() {
        val q = indexedMinPQ<Int>(1)
        q.insert(0, 1)
        assertFailsWith<IllegalArgumentException> {
            q.insert(0, 2)
        }
    }

    @Test
    fun `should throw exception if decreasing for non-existing index`() {
        val q = indexedMinPQ<Int>(1)
        q.insert(0, 1)
        q.deleteMin()
        assertFailsWith<IllegalArgumentException> {
            q.decreaseKey(0, 2)
        }
    }

    @Test
    fun `should iterate over the key indices`() {
        val q = indexedMinPQ<Int>(6)
        keys.withIndex().forEach { q.insert(it.index, it.value) }

        val items = q.iterator()
                .asSequence()
                .toList()

        assertEquals(keys.indices.toList(), items.sorted())
    }
}