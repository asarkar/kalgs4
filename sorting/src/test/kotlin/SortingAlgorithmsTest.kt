package org.abhijitsarkar.kalgs4.sorting

import kotlin.test.Test
import kotlin.test.assertEquals

/**
 * @author Abhijit Sarkar
 */
class SortingAlgorithmsTest {
    @Test
    fun `should sort using shell sort`() {
        val a = "SHELLSORTEXAMPLE".toCharArray().toTypedArray()
        shell(a)

        assertEquals("AEEEHLLLMOPRSSTX", a.joinToString(separator = ""))
    }
}