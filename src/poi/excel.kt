package poi

import org.apache.poi.ss.usermodel.WorkbookFactory
import java.io.File
import java.nio.file.Paths

fun main(args: Array<String>) {
    var workbook = WorkbookFactory.create(Paths.get("res/ブック1.xlsx").toFile())

    var sheet = workbook.getSheetAt(0)

    println(sheet.getRow(0)?.getCell(0) ?: "empty")

    workbook.close()
}
