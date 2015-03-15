package poi2

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
import java.util.regex.Pattern

fun main(args: Array<String>) {
    Excel.open("res/ブック1.xlsx").use { (workbook) ->
        var sheet = workbook.getSheetAt(0)

        // セルの読み込み
        println(sheet[0, 0])
        println(sheet[1, 1])
        println(sheet[0, 3])
        // 値がないセルも安全
        println(sheet[100, 100])

        println(sheet at "A1")
        println(sheet at "B2")
        println(sheet at "AT13")

        // セルの書き込み
        sheet[0, 10] = "あいうえお"
        sheet[0, 11] = 100
        sheet[0, 12] = 1.2
        sheet at "E7" value 100

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
    var row = this[y]
    return row[x]
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

val ORIGIN = 'A'.toInt()
val RADIX = 26

// 参考 https://github.com/nobeans/gexcelapi/blob/master/src/main/groovy/org/jggug/kobo/gexcelapi/CellLabelUtils.groovy
fun Sheet.at(cellLabel: String): Cell {
    val p1 = Pattern.compile("([a-zA-Z]+)([0-9]+)");
    val matcher = p1.matcher(cellLabel)
    matcher.find()

    var num = 0
    matcher.group(1).toUpperCase().reverse().forEachIndexed {
        (i, c) ->
        var delta = c.toInt() - ORIGIN+ 1
        num = num + delta * Math.pow(RADIX.toDouble(), i.toDouble()).toInt()
    }
    num = num - 1
    return this[num, matcher.group(2).toInt() - 1]
}

fun Cell.value(value: Any) {
    when (value) {
        is String -> this.setCellValue(value)
        is Int -> this.setCellValue(value.toDouble())
        is Double -> this.setCellValue(value)
        else -> throw IllegalArgumentException("文字列か数値だけにして")
    }
}
