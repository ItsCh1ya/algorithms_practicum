package algorithms

import common.FS
import java.util.*

data class Node(val data: Char, val frequency: Int, val left: Node? = null, val right: Node? = null)

class Huffman(private val fs: FS) {
    fun encode(text: String): String {
        val frequencyMap = text.groupingBy { it }.eachCount()
        val priorityQueue = PriorityQueue<Node> { a, b -> a.frequency - b.frequency }
        frequencyMap.forEach { (char, frequency) ->
            priorityQueue.add(Node(char, frequency))
        }
        val root = buildHuffmanTree(priorityQueue)
        val codeMap = buildHuffmanCodes(root)
        val encodedString = text.map { char -> codeMap[char] ?: "" }.joinToString("")

        fs.writeEncryptedData(codeMap, encodedString)

        return encodedString

    }

    fun decode(): String {
        val (codeMap, encodedString) = fs.readEncryptedData()
        var currentCode = ""
        var decodedString = ""

        encodedString.forEach {
            currentCode += it
            if (currentCode in codeMap.keys) {
                decodedString += codeMap[currentCode]
                currentCode = ""
            }
        }

        return  decodedString
    }

    private fun buildHuffmanTree(priorityQueue: PriorityQueue<Node>): Node {
        while (priorityQueue.size > 1) {
            val left = priorityQueue.poll()
            val right = priorityQueue.poll()
            val parent = Node('\u0000', left.frequency + right.frequency, left, right)
            priorityQueue.add(parent)
        }
        return priorityQueue.poll()
    }

    private fun buildHuffmanCodes(root: Node): Map<Char, String> {
        val codeMap = mutableMapOf<Char, String>()
        fun traverse(node: Node, code: String) {
            if (node.left == null && node.right == null) {
                codeMap[node.data] = code
            }
            node.left?.let { traverse(it, code + "0") }
            node.right?.let { traverse(it, code + "1") }
        }
        traverse(root, "")
        return codeMap
    }
}
