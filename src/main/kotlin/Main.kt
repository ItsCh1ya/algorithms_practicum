import algorithms.Fibonacci
import algorithms.Huffman
import common.FS
import java.util.*
import kotlin.time.ExperimentalTime
import kotlin.time.measureTime

@OptIn(ExperimentalTime::class)
fun main() {
    val scanner = Scanner(System.`in`)
    while (true) {
        println("Choose an algorithm:")
        println("1. Fibonacci")
        println("2. Huffman")
        println("3. Quit")
        print("Enter your choice: ")

        when (scanner.nextInt()) {
            1 -> {
                println("Choose a Fibonacci method:")
                println("1. Recursive")
                println("2. Iterative")
                println("3. Recursive Optimized")
                println("4. Binet Formula")
                println("5. Matrix Exponentiation")
                println("6. Equal or Odd")
                print("Enter your choice: ")
                val fibChoice = scanner.nextInt()

                print("Enter the input value (n): ")
                val n = scanner.nextInt()

                val time = measureTime {
                    val fibonacci = Fibonacci()
                    val result = when (fibChoice) {
                        1 -> fibonacci.recursive(n)
                        2 -> fibonacci.iterative(n)
                        3 -> fibonacci.recursiveOptimized(n)
                        4 -> fibonacci.binet(n)
                        5 -> fibonacci.matrix(n)
                        6 -> fibonacci.isEven(n)
                        else -> 0 // Invalid choice
                    }
                    println("Result: $result")
                }

                println("Time taken: $time")
            }
            2 -> {
                println("Choose a Huffman method:")
                println("1. Encode")
                println("2. Decode (from src/main/resources/huffmanIO.txt)")
                print("Enter your choice: ")
                val hufChoice = scanner.nextInt()

                val time = measureTime {
                    val huffman = Huffman(FS())
                    val result = when (hufChoice) {
                        1 -> {
                            print("Enter the text to encode: ")
                            val n = readln()
                            huffman.encode(n)
                        }
                        2 -> huffman.decode()
                        else -> 0 // Invalid choice
                    }
                    println("Result: $result")
                }

                println("Time taken: $time")
            }
            3 -> {
                println("Goodbye!")
                return
            }
            else -> println("Invalid choice. Try again.")
        }
    }
}
