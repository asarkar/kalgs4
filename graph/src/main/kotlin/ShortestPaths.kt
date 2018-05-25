package org.abhijitsarkar.kalgs4.graph

import org.abhijitsarkar.kalgs4.Queue
import org.abhijitsarkar.kalgs4.emptyQueue
import org.abhijitsarkar.kalgs4.emptyStack
import org.abhijitsarkar.kalgs4.sorting.indexedMinPQ

/**
 * @author Abhijit Sarkar
 */
fun dijkstraSP(src: Int, dest: Int, g: EdgeWeightedDigraph): Iterable<DirectedEdge> {
    val edgeTo = IntArray(g.numVertices)
    val distTo = DoubleArray(g.numVertices).apply {
        fill(Double.POSITIVE_INFINITY)
    }

    val q = indexedMinPQ<Double>(g.numVertices).apply {
        insert(src, 0.0)
        for (v in 1 until g.numVertices) insert(v, Double.POSITIVE_INFINITY)
    }

    tailrec fun visit() {
        if (q.isEmpty) return

        val v = q.deleteMin()

        // relax all edges pointing from v
        g.adj(v)
                .filter {
                    val w = it.to
                    q.contains(w) && (distTo[v] + it.weight) < distTo[w]
                }
                .forEach {
                    val w = it.to
                    val dist = distTo[v] + it.weight
                    q.decreaseKey(w, dist)
                    distTo[w] = dist
                    edgeTo[w] = v
                }
        visit()
    }

    q.decreaseKey(0, 0.0)
    distTo[src] = 0.0
    visit()

    val stack = emptyStack<DirectedEdge>()
    generateSequence(dest) { if (edgeTo[it] != src) edgeTo[it] else null }
            .map { DirectedEdge(edgeTo[it], it, distTo[it]) }
            .forEach(stack::push)

    return stack
}

fun bellmanFordSP(src: Int, dest: Int, g: EdgeWeightedDigraph): Iterable<DirectedEdge> {
    val edgeTo = IntArray(g.numVertices)
    val distTo = DoubleArray(g.numVertices).apply {
        fill(Double.POSITIVE_INFINITY)
        this[src] = 0.0
    }

    fun relax(q1: Queue<Int>, q2: Queue<Int>, marked: BooleanArray) {
        while (!q1.isEmpty) {
            val v = q1.dequeue()
            g.adj(v)
                    .forEach {
                        val w = it.to

                        val dist = distTo[v] + it.weight
                        if (dist < distTo[w]) {
                            distTo[w] = dist
                            edgeTo[w] = v
                            if (!marked[w]) {
                                q2.enqueue(w)
                                marked[w] = true
                            }
                        }
                    }
        }
    }

    var q = emptyQueue<Int>().apply {
        for (i in 0 until g.numVertices) enqueue(i)
    }
    var otherQ = emptyQueue<Int>()
    var i = 1

    do {
        check(i < g.numVertices) { "Negative cycle detected" } // can be found by inspecting the boolean array
        check(q.size <= g.numVertices) { "Duplicate vertices detected in the queue" }
        check(otherQ.isEmpty) { "There's a bug in the code" }

        relax(q, otherQ, BooleanArray(g.numVertices))
        val tmp = q
        q = otherQ
        otherQ = tmp
        ++i
    } while (!q.isEmpty)

    val stack = emptyStack<DirectedEdge>()
    generateSequence(dest) { if (edgeTo[it] != src) edgeTo[it] else null }
            .map { DirectedEdge(edgeTo[it], it, distTo[it]) }
            .forEach(stack::push)

    return stack
}