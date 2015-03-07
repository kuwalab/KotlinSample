package test

fun main(args: Array<String>) {
    if (args.size() != 1) {
        println("西暦年を入れてください")
        return
    }

    var year: Int = try {
        args[0].toInt()
    } catch (e: NumberFormatException) {
        println("整数で入力してください")
        return
    }

    isLeapYear(year)
}

fun isLeapYear(year: Int): Boolean {
    // グリゴリオ歴以前はうるう年ではない
    if (year < 1582) {
        return false
    }
    if (year % 4 != 0) {
        return false
    }
    if (year % 400 == 0) {
        return true
    }
    if (year % 100 == 0) {
        return false
    }
    return true
}
