package zip

import java.io.FileInputStream
import java.util.zip.ZipInputStream
import java.nio.file.Paths
import java.nio.file.Files
import java.io.FileOutputStream
import java.io.InputStream
import java.io.OutputStream

fun main(args: Array<String>) {
    ZipInputStream(FileInputStream(Paths.get("res/test.zip").toFile())).use { (zis) ->
        while (true) {
            var entry = zis.getNextEntry()
            if (entry == null) break

            if (entry.isDirectory()) {
                Files.createDirectories(Paths.get("res", "zip", entry.getName()))
            } else {
                FileOutputStream(Paths.get("res", "zip", entry.getName()).toFile()).use { (fos) ->
                    copy(zis, fos)
                }
            }
        }
    }
}

fun copy(ins: InputStream, os: OutputStream) {
    var buf = ByteArray(1024 * 8)
    var length: Int
    while (true) {
        length = ins.read(buf)
        if (length == -1) break
        os.write(buf, 0, length)
    }
}
