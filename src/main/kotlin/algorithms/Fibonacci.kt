package algorithms

import kotlin.math.*

class Fibonacci {
    // 1.1. Вычисление n-го числа Фибоначчи с использованием рекурсивного алгоритма
    fun recursive(n: Int): Int = if (n <= 1) n else recursive(n - 1) + recursive(n - 2)

    // 1.2. Вычисление n-го числа Фибоначчи с использованием цикла
    fun iterative(n: Int): Int {
        var prev = 0
        var next = 1
        repeat(n) {
            val temp = next
            next += prev
            prev = temp
        }
        return prev
    }

    // 1.3. Вычисление n-го числа Фибоначчи с записью числового ряда в массив
    fun recursiveOptimized(n: Int): Int {
        val cache = mutableListOf(0, 1)

        fun fib(n: Int): Int = cache.getOrNull(n) ?: (fib(n - 1) + fib(n - 2))

        return fib(n)
    }

    // 1.4. Вычисление n-го числа Фибоначчи при помощи формулы Бине
    fun binet(n: Int): Long {
        val phi = (1 + sqrt(5.0)) / 2
        return (phi.pow(n) / sqrt(5.0)).roundToLong()
    }

    // 1.5. Определение четности n-го большого числа Фибоначчи
    fun isEven(n: Int): Boolean = n % 3L == 0L

    // 1.6. Вычисление n-го числа матричным алоритмом
    fun matrix(n: Int): Int {
        val id = arrayOf(intArrayOf(1, 0), intArrayOf(0, 1))
        var result = id
        var bits = n
        var matrix = arrayOf(
            intArrayOf(0, 1),
            intArrayOf(1, 1)
        )
        while (bits > 0) {
            if (bits and 1 == 1) {
                result = matrixMultiply(result, matrix)
            }
            matrix = matrixMultiply(matrix, matrix)
            bits = bits shr 1
        }
        return result[1][0]
    }

    private fun matrixMultiply(
        a: Array<IntArray>,
        b: Array<IntArray>
    ): Array<IntArray> {
        val result = Array(2) { IntArray(2) }
        for (i in 0 until 2) {
            for (j in 0 until 2) {
                for (k in 0 until 2) {
                    result[i][j] += a[i][k] * b[k][j]
                }
            }
        }
        return result
    }
}