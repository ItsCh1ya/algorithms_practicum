package common

import java.io.File

class FS {
    private val file = File("src/main/resources/huffmanIO.txt")
    fun writeEncryptedData(codeMap: Map<Char, String>, encodedString: String) {
        file.writeText("${codeMap.keys.size} ${encodedString.length}\n")
        codeMap.forEach {
            file.appendText("'${it.key}': ${it.value}\n")
        }
        file.appendText(encodedString)
    }
    fun readEncryptedData(): Pair<Map<String, Char>, String> {
        val lines = file.readLines()
        val sizeData = lines[0].split(" ")
        val codeMapSize = sizeData[0].toInt()

        val codeMap = mutableMapOf<String, Char>()

        for (i in 1 until codeMapSize + 1) {
            val parts = lines[i].split(":")
            val value = parts[0].trim()[1]
            val key = parts[1].trim()
            codeMap[key] = value
        }

        val encodedString = lines.subList(codeMapSize + 1, lines.size).joinToString("\n")

        return codeMap to encodedString
    }

}