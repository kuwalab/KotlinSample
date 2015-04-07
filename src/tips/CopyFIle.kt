package tips.t002

import java.io.File
import java.nio.file.Files
import java.nio.file.Paths

fun main(args: Array<String>) {
    // Fileのコピー
    val srcFile = File("res/test.zip")
    val destFile = File("res/dest1.zip")
    val size1 = srcFile.copyTo(destFile, bufferSize = 1024 * 8)
    println("ファイルサイズ=${size1}")

    // InputStreamのコピー
    val srcPath = Paths.get("res/test.zip")
    val destPath = Paths.get("res/dest2.zip")
    Files.newInputStream(srcPath).use { ins ->
        Files.newOutputStream(destPath).use { os ->
            val size2 = ins.copyTo(os, bufferSize = 1024 * 8)
            println("ファイルサイズ=${size2}")
        }
    }
}
