package poi

import java.io.FileInputStream
import java.io.IOException
import java.nio.file.Files
import java.nio.file.Paths
import org.apache.poi.ss.usermodel.Sheet
import org.apache.poi.ss.usermodel.Cell
import org.apache.poi.ss.usermodel.Row
import org.apache.poi.ss.usermodel.Workbook
import org.apache.poi.ss.usermodel.WorkbookFactory
import kotlin.platform.platformStatic

fun main(args: Array<String>) {
    Excel.open("res/ブック1.xlsx").use { (workbook) ->
        var sheet = workbook.getSheetAt(0)

        println(sheet[0, 0])
        println(sheet[1, 1])
        println(sheet[0, 3])
        // 無いセルも安全
        println(sheet[100, 100])
        sheet[0, 10] = "あいうえお"
        sheet[0, 11] = 100
        sheet[0, 12] = 1.2

        Excel.write(workbook, "res/ブック2.xlsx")
    }
}

class Excel {
    class object {
        platformStatic fun open(fileName: String): Workbook {
            return WorkbookFactory.create(FileInputStream(Paths.get(fileName).toFile()))
        }

        platformStatic fun write(workbook: Workbook, fileName: String) {
            var outputPath = Paths.get(fileName)
            try {
                Files.newOutputStream(outputPath).use {
                    workbook.write(it)
                }
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }
}

fun Sheet.get(n: Int): Row {
    return this.getRow(n) ?: this.createRow(n)
}

fun Row.get(n: Int): Cell {
    return this.getCell(n) ?: this.createCell(n, Cell.CELL_TYPE_BLANK)
}

fun Sheet.get(x: Int, y: Int): Cell {
    var row = this.getRow(y) ?: this.createRow(y)
    return row.getCell(x) ?: row.createCell(x, Cell.CELL_TYPE_BLANK)
}

fun Sheet.set(x: Int, y: Int, value: Any) {
    var cell = this[x, y]
    when (value) {
        is String -> cell.setCellValue(value)
        is Int -> cell.setCellValue(value.toDouble())
        is Double -> cell.setCellValue(value)
        else -> throw IllegalArgumentException("文字列か数値だけにして")
    }
}
