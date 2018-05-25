package org.abhijitsarkar.kalgs4.graph

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertFalse

/**
 * @author Abhijit Sarkar
 */
class ShortestPathsTest {
    @Test
    fun `should find the shortest path using Dijkstra's algorithm`() {
        val g = EdgeWeightedDigraph(8).apply {
            addEdge(DirectedEdge(0, 1, 5.0))
            addEdge(DirectedEdge(0, 7, 8.0))
            addEdge(DirectedEdge(0, 4, 9.0))
            addEdge(DirectedEdge(4, 7, 5.0))
            addEdge(DirectedEdge(4, 5, 4.0))
            addEdge(DirectedEdge(4, 6, 20.0))
            addEdge(DirectedEdge(1, 3, 15.0))
            addEdge(DirectedEdge(1, 2, 12.0))
            addEdge(DirectedEdge(1, 7, 4.0))
            addEdge(DirectedEdge(7, 2, 7.0))
            addEdge(DirectedEdge(7, 5, 6.0))
            addEdge(DirectedEdge(5, 2, 1.0))
            addEdge(DirectedEdge(5, 6, 13.0))
            addEdge(DirectedEdge(2, 3, 3.0))
            addEdge(DirectedEdge(2, 6, 11.0))
            addEdge(DirectedEdge(3, 6, 9.0))
        }

        val edges = dijkstraSP(0, 6, g).iterator()
        assertEquals(DirectedEdge(0, 4, 9.0), edges.next())
        assertEquals(DirectedEdge(4, 5, 13.0), edges.next())
        assertEquals(DirectedEdge(5, 2, 14.0), edges.next())
        assertEquals(DirectedEdge(2, 6, 25.0), edges.next())

        assertFalse(edges.hasNext())
    }

    @Test
    fun `should find the shortest path using Bellman-Ford algorithm`() {
        val g = EdgeWeightedDigraph(5).apply {
            addEdge(DirectedEdge(0, 3, 8.0))
            addEdge(DirectedEdge(3, 4, 2.0))
            addEdge(DirectedEdge(4, 3, 1.0))
            addEdge(DirectedEdge(0, 1, 4.0))
            addEdge(DirectedEdge(0, 2, 5.0))
            addEdge(DirectedEdge(1, 2, -3.0))
            addEdge(DirectedEdge(2, 4, 4.0))
        }

        val edges = bellmanFordSP(0, 3, g).iterator()
        assertEquals(DirectedEdge(0, 1, 4.0), edges.next())
        assertEquals(DirectedEdge(1, 2, 1.0), edges.next())
        assertEquals(DirectedEdge(2, 4, 5.0), edges.next())
        assertEquals(DirectedEdge(4, 3, 6.0), edges.next())

        assertFalse(edges.hasNext())
    }

    @Test
    fun `should find the negative cycle using Bellman-Ford algorithm`() {
        val g = EdgeWeightedDigraph(4).apply {
            addEdge(DirectedEdge(0, 1, 1.0))
            addEdge(DirectedEdge(1, 2, 3.0))
            addEdge(DirectedEdge(2, 3, 2.0))
            addEdge(DirectedEdge(3, 1, -6.0))
        }

        assertFailsWith(IllegalStateException::class) { bellmanFordSP(0, 3, g) }
    }
}