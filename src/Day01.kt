import kotlin.math.abs

fun main() {
    fun part1(input: List<String>): Int {
        return input.size
    }

    fun part2(input: List<String>): Int {
        return input.size
    }

    // Test if implementation meets criteria from the description, like:
    check(part1(listOf("test_input")) == 1)

    // Or read a large test input from the `src/Day01_test.txt` file:
    val testInput = readInput("Day01")

    val matrix = testInput.map { it.split("   ") }
    val list1 = matrix.map { it[0].toInt() }
    val list2 = matrix.map { it[1].toInt() }

    val sortedList1 = list1.sorted()
    val sortedList2 = list2.sorted()

    var sum = 0;
    for (i in 0 until sortedList1.size) {
        sum += abs(sortedList2[i] - sortedList1[i])
    }
    println(sum)
}
