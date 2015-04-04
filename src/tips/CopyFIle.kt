package tips.t002

import java.nio.file.Files
import java.nio.file.Paths

fun main(args: Array<String>) {
    val srcFile = Paths.get("res/test.zip")
    val destFile = Paths.get("res/dest.zip")
    Files.newInputStream(srcFile).use { ins ->
        Files.newOutputStream(destFile).use { os ->
            val size = ins.copyTo(os, bufferSize = 1024 * 8)
            println("ファイルサイズ=${size}")
        }
    }
}
