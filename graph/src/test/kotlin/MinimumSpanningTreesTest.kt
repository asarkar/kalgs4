package org.abhijitsarkar.kalgs4.graph

import kotlin.math.min
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

/**
 * @author Abhijit Sarkar
 */
class MinimumSpanningTreesTest {
    @Test
    fun `should get MST using Kruskal's algorithm`() {
        assertMST(kruskalMST(buildGraph()))
    }

    @Test
    fun `should get MST using Prim's lazy algorithm`() {
        assertMST(primLazyMST(buildGraph()))
    }

    @Test
    fun `should get MST using Prim's eager algorithm`() {
        assertMST(primEagerMST(buildGraph()))
    }

    private fun assertMST(edges: Iterable<Edge>) {
        val mst = edges
                .map {
                    it.either.run {
                        val p = this
                        val q = it.other(this)
                        val min = min(p, q)
                        if (min == p) p to q else q to p
                    }
                }
                .sortedWith(compareBy({ it.first }, { it.second }))
                .toList()

        assertEquals(7, mst.size)

        assertTrue(mst.contains(0 to 2))
        assertTrue(mst.contains(0 to 7))
        assertTrue(mst.contains(1 to 7))
        assertTrue(mst.contains(2 to 3))
        assertTrue(mst.contains(2 to 6))
        assertTrue(mst.contains(4 to 5))
        assertTrue(mst.contains(5 to 7))
    }

    private fun buildGraph(): EdgeWeightedGraph {
        val edges = listOf(
                Edge(0, 7, 0.16),
                Edge(2, 3, 0.17),
                Edge(1, 7, 0.19),
                Edge(0, 2, 0.26),
                Edge(5, 7, 0.28),
                Edge(1, 3, 0.29),
                Edge(1, 5, 0.32),
                Edge(2, 7, 0.34),
                Edge(4, 5, 0.35),
                Edge(1, 2, 0.36),
                Edge(4, 7, 0.37),
                Edge(0, 4, 0.38),
                Edge(6, 2, 0.40),
                Edge(3, 6, 0.52),
                Edge(6, 0, 0.58)
        )
                .shuffled()

        return EdgeWeightedGraph(8).apply {
            edges.forEach(this::addEdge)
        }
    }
}