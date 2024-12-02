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

    var sum = 0;

    list1.forEachIndexed { i, el ->
        sum += list1[i] * list2.count {el == it}
    }
    print(sum)
}
