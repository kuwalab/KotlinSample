package enshu.yakusu.f

fun main(args: Array<String>) {
    var a = 217
    var b = 186

    println(y(a, b))
}

fun y(a: Int, b: Int): Int = if (b == 0) a else y(b, a % b)
