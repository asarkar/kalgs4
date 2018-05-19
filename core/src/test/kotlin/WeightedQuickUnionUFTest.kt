package org.abhijitsarkar.kalgs4

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

/**
 * @author Abhijit Sarkar
 */
class WeightedQuickUnionUFTest {
    private val uf: UnionFind

    init {
        val lines = javaClass.getResourceAsStream("/tinyUF.txt")
                .bufferedReader()
                .readLines()

        uf = WeightedQuickUnionUF(lines.first().toInt())

        lines.drop(1)
                .map { it.split("\\s".toRegex()).map(String::trim) }
                .forEach { uf.union(it.first().toInt(), it.last().toInt()) }
    }

    @Test
    fun `should find parent for connected components {4,3,8,9}`() {
        listOf(4, 3, 8, 9)
                .windowed(2, 1)
                .forEach {
                    val p = it.first()
                    val q = it.last()
                    val connected = uf.connected(p, q)
                    println("""[$p,$q] are ${if (connected) "" else "not "}connected""")
                    assertTrue(connected)
                    assertEquals(4, uf.find(p))
                    assertEquals(4, uf.find(q))
                }
    }

    @Test
    fun `should find parent for connected components {6,0,2,5,1,7}`() {
        listOf(6, 0, 2, 5, 1, 7)
                .windowed(2, 1)
                .forEach {
                    val p = it.first()
                    val q = it.last()
                    val connected = uf.connected(p, q)
                    println("""[$p,$q] are ${if (connected) "" else "not "}connected""")
                    assertTrue(connected)
                }
    }

    @Test
    fun `should find {1,3} not connected`() {
        assertFalse(uf.connected(1, 3))
    }
}