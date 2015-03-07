package student

fun main(args: Array<String>) {
    var studentList = listOf(
            Student(1, "テスト　一郎", 80, 80),
            Student(2, "テスト　二郎", 100, 70),
            Student(3, "テスト　三郎", 0, 60),
            Student(4, "テスト　四郎", 50, 60),
            Student(5, "テスト　五郎", 70, 90)
    )

    println("***** 初期状態 *****")
    /*
    studentList.forEach({
        (s: Student) ->
        println(s)
    })
    以下と同義 中置呼び出し
    */
    studentList forEach {
        println(it)
    }

    println("*** 国語が60点以上の抽出 ***")
    studentList filter {
        it.kokugo >= 60
    } forEach {
        println(it)
    }

    println("*** 国語の平均点の算出 ***")
    var sum = studentList.fold(0) {(sum, current: Student) ->
        sum + current.kokugo
    }
    println("国語の平均点は${sum / studentList.size()}")
}
