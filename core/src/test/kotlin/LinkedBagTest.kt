package org.abhijitsarkar.kalgs4

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertFalse
import kotlin.test.assertTrue

/**
 * @author Abhijit Sarkar
 */
class LinkedBagTest {
    @Test
    fun `should meet Bag spec`() {
        val bag = mutableBagOf<Int>()

        assertTrue(bag.isEmpty)
        assertEquals(0, bag.size)

        (1..3)
                .map(bag::add)
                .forEach { assertTrue(it) }

        assertFalse(bag.isEmpty)
        assertEquals(3, bag.size)

        val l = bag.iterator()
                .asSequence()
                .toList()

        assertEquals(3, l.size)
        for (i in 1..3) assertTrue(l.contains(i))
    }

    @Test
    fun `should throw NoSuchElementException if iterating on an empty bag`() {
        assertFailsWith(NoSuchElementException::class) { mutableBagOf<Any>().iterator().next() }
    }
}