package org.abhijitsarkar.kalgs4

import kotlin.test.Test
import kotlin.test.assertEquals

/**
 * @author Abhijit Sarkar
 */
class ArraysTest {
    @Test
    fun `should partition an array`() {
        val a = charArrayOf('K', 'R', 'A', 'T', 'E', 'L', 'E', 'P', 'U', 'I', 'M', 'Q', 'C', 'X', 'O', 'S')
                .toTypedArray()

        val k = partition(a, 0, a.size - 1)
        assertEquals(5, k)
        assertEquals('K', a[k])
    }
}