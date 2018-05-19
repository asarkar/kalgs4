package org.abhijitsarkar.kalgs4

/**
 * @author Abhijit Sarkar
 */
class WeightedQuickUnionUF(private val n: Int) : UnionFind {
    private val parent: IntArray = IntArray(n)
    private val size: IntArray = IntArray(n)
    private var count = 0

    init {
        count = n
        for (i in 0 until n) {
            parent[i] = i
            size[i] = 1
        }
    }

    override fun count(): Int {
        return n
    }

    override tailrec fun find(p: Int): Int {
        return if (p == parent[p]) p else find(parent[p])
    }

    override fun connected(p: Int, q: Int): Boolean {
        return find(p) == find(q)
    }

    override fun union(p: Int, q: Int): Int {
        val rootP = find(p)
        val rootQ = find(q)

        val parent = if (size[rootP] < size[rootQ]) {
            parent[rootP] = rootQ
            size[rootQ] += size[rootP]
            rootQ
        } else {
            parent[rootQ] = rootP
            size[rootP] += size[rootQ]
            rootP
        }
        --count

        return parent
    }
}