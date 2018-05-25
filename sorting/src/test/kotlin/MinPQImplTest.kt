package org.abhijitsarkar.kalgs4.sorting

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

/**
 * @author Abhijit Sarkar
 */
class MinPQImplTest {
    private val keys = listOf(3, 5, 9, 12, 6, 10)

    @Test
    fun `should start with an empty queue and then add and remove items`() {
        val q = emptyMinPQ<Int>()
        assertTrue(q.isEmpty)
        assertEquals(0, q.size)

        keys.forEach(q::insert)
        assertFalse(q.isEmpty)
        assertEquals(6, q.size)

        assertPQ(q)
    }

    @Test
    fun `should start with given keys and then add and remove items`() {
        val q = minPQOf<Int>(keys.toTypedArray())
        assertFalse(q.isEmpty)
        assertEquals(6, q.size)

        assertPQ(q)
    }

    @Test
    fun `should iterate the keys`() {
        val q = minPQOf<Int>(keys.toTypedArray())

        val items = q.iterator()
                .asSequence()
                .toList()

        assertEquals(keys.sorted(), items.sorted())
    }

    private fun assertPQ(q: MinPQ<Int>) {
        q.insert(4)
        assertEquals(7, q.size)

        assertEquals(3, q.deleteMin())
        assertEquals(6, q.size)

        assertEquals(4, q.deleteMin())
        assertEquals(5, q.size)

        q.insert(2)
        assertEquals(6, q.size)
        assertEquals(2, q.deleteMin())
        assertEquals(5, q.size)
    }
}