package org.abhijitsarkar.kalgs4.graph

import org.abhijitsarkar.kalgs4.MutableBag
import org.abhijitsarkar.kalgs4.emptyBag
import org.abhijitsarkar.kalgs4.mutableBagOf

/**
 * @author Abhijit Sarkar
 */
class EdgeWeightedDigraph(private val v: Int) {
    private var e: Int = 0
    private val adj: Array<MutableBag<DirectedEdge>?> = arrayOfNulls(v)
    private val indegrees = IntArray(v)

    val numVertices: Int
        get() = v

    val numEdges: Int
        get() = e

    fun indegree(v: Int): Int {
        return indegrees[v]
    }

    fun outdegree(v: Int): Int {
        return adj[v]?.size ?: 0
    }

    fun addEdge(edge: DirectedEdge) {
        if (adj[edge.from] == null) {
            adj[edge.from] = mutableBagOf()
        }

        adj[edge.from]!!.add(edge)
        ++indegrees[edge.from]
    }

    fun adj(v: Int): Iterable<DirectedEdge> {
        return adj[v] ?: emptyBag()
    }
}