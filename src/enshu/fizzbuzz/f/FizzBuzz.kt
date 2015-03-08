package enshu.fizzbuzz.f

fun Int.isFizz() = this % 3 == 0
fun Int.isBuzz() = this % 5 == 0

fun main(args: Array<String>) {
    for (i in 1..100) {
        print("${i} ")
        when {
            i.isFizz() && i.isBuzz() -> println("FizzBuzz")
            i.isFizz() -> println("Fizz")
            i.isBuzz() -> println("Buzz")
            else -> println()
        }
    }
}
