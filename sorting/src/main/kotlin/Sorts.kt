package org.abhijitsarkar.kalgs4.sorting

import org.abhijitsarkar.kalgs4.exchange
import org.abhijitsarkar.kalgs4.partition
import org.abhijitsarkar.kalgs4.shuffle

/**
 * @author Abhijit Sarkar
 */
fun <T : Comparable<T>> shellSort(a: Array<T>) {
    // 3x + 1 increment sequence:  1, 4, 13, 40, 121, 364, 1093, ...
    val h = generateSequence(1) {
        if (it < a.size / 3) 3 * it + 1 else null
    }
            .last()

    tailrec fun sort(h: Int, i: Int) {
        if (i >= a.size) {
            return
        }
        println("h = $h, i = $i")

        for (j in i downTo h step h) {
            if (a[j] < a[j - h]) exchange(a, j, j - h)
        }

        sort(h, i + 1)
    }

    generateSequence(h) { it / 3 }
            .takeWhile { it >= 1 }
            .forEach { sort(it, it) }
}

fun <T : Comparable<T>> mergeSort(a: Array<T>) {
    @Suppress("UNCHECKED_CAST")
    val aux = arrayOfNulls<Comparable<*>>(a.size) as Array<T>

    fun merge(lo: Int, mid: Int, hi: Int) {
        var i = lo
        var j = mid + 1

        for (k in lo..hi) aux[k] = a[k]

        for (k in lo..hi) {
            when {
                i > mid -> a[k] = aux[j++]
                j > hi -> a[k] = aux[i++]
                aux[j] < aux[i] -> a[k] = aux[j++]
                else -> a[k] = aux[i++]
            }
        }
    }

    fun divide(a: Array<T>, low: Int, high: Int) {
        if (high <= low) return

        val mid = low + (high - low) / 2
        divide(a, low, mid)
        divide(a, mid + 1, high)

        merge(low, mid, high)
    }

    divide(a, 0, a.size - 1)
}

fun <T : Comparable<T>> quickSort(a: Array<T>) {
    fun sort(lo: Int, hi: Int) {
        if (lo <= hi) return

        val i = partition(a, lo, hi)
        // a[i] is in place
        sort(lo, i - 1)
        sort(i + 1, hi)
    }

    shuffle(a)
    sort(0, a.size - 1)
}