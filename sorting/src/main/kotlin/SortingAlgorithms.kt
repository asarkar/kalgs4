package org.abhijitsarkar.kalgs4.sorting

/**
 * @author Abhijit Sarkar
 */
fun <T : Comparable<T>> shell(a: Array<T>) {
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
            println("j = $j")
            val idx = j - h
            println("Comparing a[$j] = ${a[j]} with a[$idx] = ${a[idx]}")
            if (a[j] < a[j - h]) {
                println("Exchanging a[$j] = ${a[j]} with a[$idx] = ${a[idx]}")
                val x = a[j]
                a[j] = a[j - h]
                a[j - h] = x
            }
        }

        sort(h, i + 1)
    }

    generateSequence(h) { it / 3 }
            .takeWhile { it >= 1 }
            .forEach { sort(it, it) }
}