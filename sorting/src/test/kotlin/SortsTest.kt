package org.abhijitsarkar.kalgs4.sorting

import kotlin.test.Test
import kotlin.test.assertEquals

/**
 * @author Abhijit Sarkar
 */
class SortsTest {
    @Test
    fun `should sort using shell sort`() {
        val a = "SHELLSORTEXAMPLE".toCharArray().toTypedArray()
        shellSort(a)

        assertEquals("AEEEHLLLMOPRSSTX", a.joinToString(separator = ""))
    }

    @Test
    fun `should sort using merge sort`() {
        val a = "MERGESORTEXAMPLE".toCharArray().toTypedArray()
        mergeSort(a)

        assertEquals("AEEEEGLMMOPRRSTX", a.joinToString(separator = ""))
    }

    @Test
    fun `should sort using quick sort`() {
        val a = "QUICKSORTEXAMPLE".toCharArray().toTypedArray()
        mergeSort(a)

        assertEquals("ACEEIKLMOPQRSTUX", a.joinToString(separator = ""))
    }
}