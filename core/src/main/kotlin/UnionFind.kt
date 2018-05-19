package org.abhijitsarkar.kalgs4

/**
 * @author Abhijit Sarkar
 */
interface UnionFind {
    fun count(): Int

    fun find(p: Int): Int

    fun union(p: Int, q: Int): Int

    fun connected(p: Int, q: Int): Boolean
}