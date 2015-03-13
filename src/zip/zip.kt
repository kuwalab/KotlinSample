package zip

import java.io.FileInputStream
import java.util.zip.ZipInputStream
import java.nio.file.Paths
import java.nio.file.Files
import java.io.FileOutputStream

fun main(args: Array<String>) {
    ZipInputStream(FileInputStream(Paths.get("res/test.zip").toFile())).use { (zis) ->
        while (true) {
            var entry = zis.getNextEntry()
            if (entry == null) break

            if (entry.isDirectory()) {
                Files.createDirectories(Paths.get("res", "zip", entry.getName()))
            } else {
                FileOutputStream(Paths.get("res", "zip", entry.getName()).toFile()).use { (fos) ->
                    var buf = ByteArray(1024 * 8)
                    var length: Int
                    while (true) {
                        length = zis.read(buf)
                        if (length == -1) break;
                        fos.write(buf, 0, length)
                    }
                }
            }
        }
    }
}
