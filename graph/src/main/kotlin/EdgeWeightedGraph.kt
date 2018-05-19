package org.abhijitsarkar.kalgs4.graph

import org.abhijitsarkar.kalgs4.MutableBag
import org.abhijitsarkar.kalgs4.mutableBagOf

/**
 * @author Abhijit Sarkar
 */
class EdgeWeightedGraph(private val v: Int) {
    private var e: Int = 0
    private val adj: Array<MutableBag<Edge>?> = arrayOfNulls(v)

    val numVertices: Int
        get() = v

    val numEdges: Int
        get() = e

    fun addEdge(e: Edge): Boolean {
        val v = e.either
        val w = e.other(v)

        listOf(v, w)
                .forEach {
                    if (adj[it] == null) {
                        adj[it] = mutableBagOf(e)
                    } else {
                        adj[it]!!.add(e)
                    }
                }
        this.e += 1

        return true
    }

    fun adj(v: Int): Iterable<Edge> {
        return adj[v] ?: mutableBagOf()
    }

    fun degree(v: Int): Int {
        return adj[v]?.size ?: 0
    }

    val edges: Iterable<Edge>
        get() = (0 until v).flatMap { vertex ->
            adj(vertex).fold(mutableListOf<Edge>() to false) { acc, edge ->
                val addSelfLoop = edge.other(vertex) == vertex && !acc.second
                val add = (edge.other(vertex) > vertex) || addSelfLoop

                if (add) acc.first.add(edge)

                acc.first to addSelfLoop
            }.first
        }
}