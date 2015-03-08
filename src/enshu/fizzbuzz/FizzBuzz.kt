package enshu.fizzbuzz

fun main(args: Array<String>) {
    for (i in 1..100) {
        print("${i} ")
        if (i % 3 == 0 && i % 5 == 0) {
            println("Fizz Buzz")
        } else if (i % 3 == 0) {
            println("Fizz")
        } else if (i % 5 == 0) {
            println("Buzz")
        } else {
            println()
        }
    }
}
