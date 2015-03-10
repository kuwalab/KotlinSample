package poi

import org.apache.poi.ss.usermodel.WorkbookFactory
import java.io.File
import java.nio.file.Paths
import org.apache.poi.ss.usermodel.Sheet
import org.apache.poi.ss.usermodel.Cell
import org.apache.poi.ss.usermodel.Row
import org.apache.poi.ss.usermodel.Workbook
import java.nio.file.Files
import java.io.IOException
import kotlin.platform.platformStatic

fun main(args: Array<String>) {
    var workbook = Excel.open("res/ブック1.xlsx")

    var sheet = workbook.getSheetAt(0)

    println(sheet[0, 0])
    println(sheet[1, 1])
    println(sheet[0, 3])
    sheet[0, 10] = "あいうえお"
    sheet[0, 11] = 100
    sheet[0, 12] = 1.2

    Excel.write(workbook, "res/ブック2.xlsx")

    workbook.close()
}

class Excel {
    class object {
        platformStatic fun open(fileName: String): Workbook {
            return WorkbookFactory.create(Paths.get(fileName).toFile())
        }

        platformStatic fun write(workbook: Workbook, fileName: String) {
            var output = Paths.get(fileName)
            try {
                Files.newOutputStream(output).use {
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
