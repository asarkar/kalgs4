package org.abhijitsarkar.kalgs4.searching

import kotlin.test.Test
import kotlin.test.assertTrue

class RedBlackBSTTest {
    @Test
    fun `should build a RedBlackBST`() {
        val rbBST = RedBlackBST<Char>()

        rbBST.put('S')
        rbBST.put('E')
        rbBST.put('A')
        rbBST.put('R')
        rbBST.put('C')
        rbBST.put('H')
        rbBST.put('X')
        rbBST.put('M')
        rbBST.put('P')
        rbBST.put('L')

        assertTrue(rbBST.levelOrder()
                .map(Pair<Char, Int>::first) == listOf('M', 'E', 'R', 'C', 'L', 'P', 'X', 'A', 'H', 'S'))
    }
}