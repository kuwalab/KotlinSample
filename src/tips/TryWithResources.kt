package tips.t001

import java.io.BufferedWriter
import java.io.IOException
import java.nio.charset.StandardCharsets
import java.nio.file.Files
import java.nio.file.Paths

fun main(args: Array<String>) {
    var outputPath = Paths.get("outputText.txt")
    try {
        Files.newBufferedWriter(outputPath, StandardCharsets.UTF_8).use<BufferedWriter, Unit> {
            it.append("あいうえお")
            it.newLine()
        }
    } catch (e: IOException) {
        e.printStackTrace()
    }
}
