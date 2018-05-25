package org.abhijitsarkar.kalgs4

import java.util.*

/**
 * @author Abhijit Sarkar
 */
// Fisher–Yates shuffle - https://en.wikipedia.org/wiki/Fisher%E2%80%93Yates_shuffle
fun <T> shuffle(a: Array<T>) {
    val r = Random()
    for (i in a.size - 1 downTo 1) exchange(a, i, r.nextInt(i + 1))
}

fun <T> exchange(a: Array<T>, i: Int, j: Int) {
    a[i] = a[j].apply {
        a[j] = a[i]
    }
}

fun <T : Comparable<T>> partition(a: Array<T>, lo: Int, hi: Int): Int {
    val k = a[lo]
    tailrec fun internal(lo: Int, hi: Int): Int {
        if (hi < lo) return hi

        val i = generateSequence(lo + 1, Int::inc)
                .dropWhile { a[it] < k && it < hi }
                .first()

        val j = generateSequence(hi, Int::dec)
                .dropWhile { a[it] > k && it >= i }
                .first()

        println("Exchanging a[$i] = ${a[i]} with a[$j] = ${a[j]}")
        exchange(a, i, j)

        return internal(i + 1, j - 1)
    }

    return internal(lo, hi).also {
        exchange(a, lo, it)
    }
}