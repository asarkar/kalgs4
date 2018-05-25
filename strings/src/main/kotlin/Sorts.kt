package org.abhijitsarkar.kalgs4.strings

fun lsdSort(a: Array<String>, d: Int, r: Int = 256) {
    if (d < 1) return

    val n = a.size
    val count = IntArray(r + 1)
    val aux = arrayOfNulls<String>(n)

    a.forEachIndexed { _, s ->
        // use character as index into count array
        count[s[d - 1].toInt() + 1]++
    }
    (0 until r).forEach {
        // cumulative; count[65] = 2 means there are 2 characters less than 'A' ('A' = 65 on ASCII table)
        count[it + 1] += count[it]
    }
    a.forEach {
        // count[i] gives the index of the string in the aux array; if count[65] = 2, then 'A' must be at aux[2]
        aux[count[it[d - 1].toInt()]++] = it
    }
    a.forEachIndexed { i, _ ->
        a[i] = aux[i]!!
    }
    lsdSort(a, d - 1)
}

// -1 ensures that shorter strings that are prefixes of longer ones appear before them, since all characters are
// represented by positive integers
fun String.charAt(d: Int): Int = if (d >= this.length) -1 else this[d].toInt()

fun msdSort(a: Array<String>) {
    fun msdSort(d: Int, lo: Int, hi: Int, r: Int = 256) {
        if (hi - lo <= 1) return

        val n = hi - lo
        val count = IntArray(r + 2)
        val aux = arrayOfNulls<String>(n)

        (lo until hi).forEach {
            count[a[it].charAt(d) + 2]++
        }
        (0..r).forEach {
            count[it + 1] += count[it]
        }
        (lo until hi).forEach {
            aux[count[a[it].charAt(d) + 1]++] = a[it]
        }
        (lo until hi).forEach {
            a[it] = aux[it - lo]!!
        }
        (0..r).forEach {
            // recursively sort the subarrays
            msdSort(d + 1, lo + count[it], lo + count[it + 1])
        }
    }
    msdSort(0, 0, a.size)
}

fun threeWayStringQSort(a: Array<String>) {
    fun swap(i: Int, j: Int) {
        a[i] = a[j].apply {
            a[j] = a[i]
        }
    }

    fun threeWayStringQSort(d: Int, lo: Int, hi: Int) {
        if (hi - lo <= 1) return

        var lt = lo
        var gt = hi
        val p = a[lo].charAt(d)
        var i = lo + 1

        while (i < gt) {
            val c = a[i].charAt(d)
            when {
                c < p -> swap(lt++, i++)
                c > p -> swap(i, --gt)
                else -> i++
            }
        }
        threeWayStringQSort(d, lo, lt - 1)
        if (p >= 0) threeWayStringQSort(d + 1, lt, gt)
        threeWayStringQSort(d, gt, hi)
    }
    threeWayStringQSort(0, 0, a.size)
}

