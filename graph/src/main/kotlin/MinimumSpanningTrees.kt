package org.abhijitsarkar.kalgs4.graph

import org.abhijitsarkar.kalgs4.mutableBagOf
import org.abhijitsarkar.kalgs4.sorting.indexedMinPQ
import org.abhijitsarkar.kalgs4.sorting.minPQ
import org.abhijitsarkar.kalgs4.unionFind

/**
 * @author Abhijit Sarkar
 */
fun kruskalMST(g: EdgeWeightedGraph): Iterable<Edge> {
    val mst = mutableBagOf<Edge>()
    val minPQ = minPQ<Edge>().apply {
        g.edges.iterator()
                .forEach(this::insert)
    }
    val uf = unionFind(g.numVertices)

    while (!minPQ.isEmpty && mst.size < g.numVertices - 1) {
        val e = minPQ.deleteMin()
        val v = e.either
        val w = e.other(v)

        if (!uf.connected(v, w)) {
            uf.union(v, w)
            mst.add(e)
        }
    }

    return mst
}

fun primLazyMST(g: EdgeWeightedGraph): Iterable<Edge> {
    val mst = mutableBagOf<Edge>()
    val minPQ = minPQ<Edge>()
    val marked = BooleanArray(g.numVertices)

    fun visit(v: Int) {
        marked[v] = true

        g.adj(v)
                .filterNot { marked[it.other(v)] }
                .forEach(minPQ::insert)
    }

    visit(0)

    while (!minPQ.isEmpty && mst.size < g.numVertices - 1) {
        val e = minPQ.deleteMin()
        val v = e.either
        val w = e.other(v)

        if (!marked[v] || !marked[w]) mst.add(e)

        listOf(v, w)
                .filterNot { marked[it] }
                .forEach(::visit)
    }

    return mst
}

fun primEagerMST(g: EdgeWeightedGraph): Iterable<Edge> {
    val edgeTo = IntArray(g.numVertices)
    val distTo = DoubleArray(g.numVertices).apply {
        fill(Double.POSITIVE_INFINITY)
    }
    val mst = mutableBagOf<Edge>()
    val src = 0
    val q = indexedMinPQ<Double>(g.numVertices).apply {
        insert(src, 0.0)
        for (v in 1 until g.numVertices) insert(v, Double.POSITIVE_INFINITY)
    }

    tailrec fun visit() {
        if (q.isEmpty) return

        val v = q.deleteMin()

        if (v != src) {
            mst.add(Edge(v, edgeTo[v], distTo[v]))
        }

        g.adj(v)
                .filter {
                    val w = it.other(v)
                    q.contains(w) && it.weight < distTo[w]
                }
                .forEach {
                    val w = it.other(v)
                    q.decreaseKey(w, it.weight)
                    distTo[w] = it.weight
                    edgeTo[w] = v
                }
        visit()
    }

    q.decreaseKey(0, 0.0)
    visit()

    return mst
}