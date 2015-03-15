package poi3

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
import poi2.*

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

        Excel.write(workbook, "res/ブック2.xlsx")
    }
}
