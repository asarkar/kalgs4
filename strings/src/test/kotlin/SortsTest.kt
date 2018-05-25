package org.abhijitsarkar.kalgs4.strings

import kotlin.test.Test
import kotlin.test.assertTrue

class SortsTest {
    @Test
    fun `should sort using LSD sort`() {
        val a = arrayOf(
                "4PGC938", "2IYE230", "3CIO720", "1ICK750", "1OHV845", "4JZY524", "1ICK750",
                "3CIO720", "1OHV845", "1OHV845", "2RLA629", "2RLA629", "3ATW723"
        )
        lsdSort(a, 7)
        assertTrue(arrayOf("1ICK750", "1ICK750", "1OHV845", "1OHV845", "1OHV845", "2IYE230", "2RLA629", "2RLA629",
                "3ATW723", "3CIO720", "3CIO720", "4JZY524", "4PGC938"
        ) contentEquals a)
    }

    @Test
    fun `should sort using MSD sort`() {
        val a = arrayOf("seashells", "sells", "surely", "she", "shells", "she", "sea", "shore")
        msdSort(a)
        assertTrue(arrayOf("sea", "seashells", "sells", "she", "she", "shells", "shore", "surely") contentEquals a)
    }

    @Test
    fun `should sort using 3-way String Quick sort`() {
        val a = arrayOf("seashells", "sells", "surely", "she", "shells", "she", "sea", "shore")
        threeWayStringQSort(a)
        assertTrue(arrayOf("sea", "seashells", "sells", "she", "she", "shells", "shore", "surely") contentEquals a)
    }
}