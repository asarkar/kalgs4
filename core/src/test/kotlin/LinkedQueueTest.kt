package org.abhijitsarkar.kalgs4

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

/**
 * @author Abhijit Sarkar
 */
class LinkedQueueTest {
    @Test
    fun `should do what a queue does`() {
        val queue = emptyQueue<Int>()

        queue.enqueue(1)
        queue.enqueue(2)
        queue.enqueue(3)
        assertEquals(3, queue.size)
        assertFalse(queue.isEmpty)

        assertEquals(1, queue.dequeue())
        assertEquals(2, queue.size)
        assertEquals(2, queue.dequeue())
        assertEquals(1, queue.size)
        queue.enqueue(1)
        assertEquals(2, queue.size)
        assertEquals(3, queue.dequeue())
        assertEquals(1, queue.size)
        assertEquals(1, queue.dequeue())
        assertTrue(queue.isEmpty)
    }

    @Test
    fun `should iterate over a queue`() {
        val queue = emptyQueue<Int>()

        queue.enqueue(1)
        queue.enqueue(2)
        queue.enqueue(3)

        val items = queue.iterator().asSequence().toList()
        assertEquals(listOf(1, 2, 3), items)
    }
}