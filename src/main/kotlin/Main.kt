import algorithms.Fibonacci
import kotlin.time.Duration
import kotlin.time.ExperimentalTime
import kotlin.time.measureTime

val NLIST = listOf(10, 20, 30)

@OptIn(ExperimentalTime::class)
private fun measureForN(algorithm: (input: Int) -> Unit): List<Duration> {
    return NLIST.map { n ->
        measureTime {
            algorithm(n)
        }
    }
}

fun main(args: Array<String>) {
    val fib = Fibonacci()
    val result: Map<String, List<Duration>> = mapOf(
        "Recursive" to measureForN(fib::recursive),
        "Recursive optimized" to measureForN(fib::recursiveOptimized),
        "Iterative " to measureForN(fib::iterative),
        "Binet" to measureForN(fib::binet),
        "Matrix" to measureForN(fib::matrix),
    )

    result.forEach { (k, v) ->
        println("$k:")
        v.forEachIndexed { i, time ->
            println("${NLIST[i]} - $time")
        }
    }
}