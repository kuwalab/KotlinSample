package net.kuwalab

import net.kuwalab.kexcelapi.KExcel
import net.kuwalab.kexcelapi.*

fun main(args: Array<String>) {
    KExcel.open("ブック1.xlsx").use { workbook ->
        val sheet = workbook.getSheetAt(0)

        println(sheet("A1").getString())
    }
}
