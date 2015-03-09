package poi

import org.apache.poi.ss.usermodel.WorkbookFactory
import java.io.File
import java.nio.file.Paths
import org.apache.poi.ss.usermodel.Sheet
import org.apache.poi.ss.usermodel.Cell
import org.apache.poi.ss.usermodel.Row

fun main(args: Array<String>) {
    var workbook = WorkbookFactory.create(Paths.get("res/ブック1.xlsx").toFile())

    var sheet = workbook.getSheetAt(0)

    println(sheet.getRow(0)?.getCell(0) ?: "empty")
    println(sheet[0][0])
    println(sheet[1][1])

    workbook.close()
}

fun Sheet.get(n: Int) : Row {
    return this.getRow(n) ?: this.createRow(n)
}

fun Row.get(n: Int): Cell {
    return this.getCell(n) ?: this.createCell(n)
}
