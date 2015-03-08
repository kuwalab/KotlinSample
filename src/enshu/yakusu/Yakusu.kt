package enshu.yakusu

fun main(args: Array<String>) {
    var a = 18
    var b = 12

    while (true) {
        var result = a % b
        if (result == 0) {
            println(b)
            break
        }
        a = b
        b = result
    }
}