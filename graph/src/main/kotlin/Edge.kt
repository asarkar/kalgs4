package org.abhijitsarkar.kalgs4.graph

/**
 * @author Abhijit Sarkar
 */
class Edge(private val v: Int, private val w: Int, val weight: Double) : Comparable<Edge> {
    val either: Int
        get() = v

    fun other(vertex: Int): Int {
        return if (v == vertex) w else v
    }

    override fun compareTo(other: Edge): Int {
        return weight.compareTo(other.weight)
    }

    override fun toString(): String {
        return "Edge(v=$v, w=$w, weight=$weight)"
    }
}