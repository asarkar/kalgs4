package org.abhijitsarkar.kalgs4

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

/**
 * @author Abhijit Sarkar
 */
class LinkedStackTest {
    @Test
    fun `should do what a stack does`() {
        val stack = emptyStack<Int>()

        stack.push(1)
        stack.push(2)
        stack.push(3)
        assertEquals(3, stack.size)
        assertFalse(stack.isEmpty)

        assertEquals(3, stack.pop())
        assertEquals(2, stack.size)
        assertEquals(2, stack.pop())
        assertEquals(1, stack.size)
        stack.push(3)
        assertEquals(2, stack.size)
        assertEquals(3, stack.pop())
        assertEquals(1, stack.size)
        assertEquals(1, stack.pop())
        assertTrue(stack.isEmpty)
    }

    @Test
    fun `should iterate over a stack`() {
        val stack = emptyStack<Int>()

        stack.push(1)
        stack.push(2)
        stack.push(3)

        val items = stack.iterator().asSequence().toList()
        assertEquals(listOf(3, 2, 1), items)
    }
}