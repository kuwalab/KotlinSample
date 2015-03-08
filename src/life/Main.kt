package life

import java.nio.file.Paths
import java.nio.file.Files
import java.io.BufferedReader
import java.nio.charset.StandardCharsets
import java.io.FileInputStream
import java.io.File
import java.io.IOException

fun main(args: Array<String>) {
    val inputPath = Paths.get("res/field.txt")
    if (!Files.exists(inputPath)) {
        println("ファイルがありません")
        return
    }
    if (Files.isDirectory(inputPath)) {
        println("ファイルではありません")
        return
    }

    try {
        Files.newBufferedReader(inputPath, StandardCharsets.UTF_8).use {
            it.lines()
        }
    } catch (e: IOException) {
        e.printStackTrace()
    }
}
