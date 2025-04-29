package org.example


fun main() {
    val n = readLine()!!.toInt()
    val numbers = readLine()!!.split(" ").map { it.toLong() }

    val maxlen = numbers.maxOfOrNull { it.toString(2).length } ?: 0

//    val b: Array<List<Boolean>> = Array(n) { i ->
//        val binaryStr = numbers[i].toString(2).padStart(maxlen, '0')
//        BooleanArray(maxlen) { j -> binaryStr[j] == '1' }.toList().sorted()
//    }
    val numb = Array(n) { i ->
        numbers[i].countOneBits()
    }.toMutableList()
//    numb.sortDescending()

    val B = Array(n) {BooleanArray(maxlen)}
    for (i in 0 until maxlen) {
        var count = 0
        if (i%2==0) {
            var res = B[0][i]
            for (j in 0 until n) {
                if (numb[j] > 0) {
                    if (count < (n/2)*2) {
                        B[j][i] = true
                        numb[j]--
                        count++
                    }
                }
            }
        } else {
            for (j in n-1 downTo  0) {
                if (numb[j] > 0) {
                    if (count < (n/2)*2) {
                        B[j][i] = true
                        numb[j]--
                        count++
                    }
                }
            }
        }
        if (count%2!= 0) {
            println("impossible")
            return
        }
    }
    val question = mutableListOf<Int>()
    for (i in 0 until maxlen) {
        var res = B[0][i]
        for (j in 1 until n) {
            res = res xor B[j][i]
        }
        if (res) question.add(i)
    }
    var countSwap = 0
    if (question.size%2 == 0) {
        for (i in question.indices step 2) {
            for (j in 0 until n) {
                if (B[j][question[i]] != B[j][question[i + 1]]) {
                    B[j][question[i]] = B[j][question[i + 1]].also {
                        B[j][question[i + 1]] = !B[j][question[i + 1]]
                        countSwap++
                    }
                    break
                }
            }
        }
    } else {
        for (i in 0..(question.size-4) step 2) {
            for (j in 0 until n) {
                if (B[j][question[i]] != B[j][question[i + 1]]) {
                    B[j][question[i]] = B[j][question[i + 1]].also {
                        B[j][question[i + 1]] = !B[j][question[i + 1]]
                        countSwap++
                    }
                    break
                }
            }
        }
        if
    }

    numb.forEach { if (it!=0) {
        println("impossible")
        return
    }}




    val result = B.map { boolArr ->
        boolArr.joinToString("") { if (it) "1" else "0" }.toLong(2)
    }
    print(result.joinToString(" "))
}