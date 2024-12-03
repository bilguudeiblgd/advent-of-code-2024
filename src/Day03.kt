import kotlin.math.abs

fun computeMulsInString(string: String): Int {
    val out = """mul\(([0-9]+),([0-9]+)\)""".toRegex().findAll(string)
    val pairList = out.map { Pair(it.groupValues[1].toInt(), it.groupValues[2].toInt()) }.toList()
    return pairList.fold(0) { acc, pair ->
        return@fold acc + pair.first * pair.second
    }
}

fun main() {
    fun part1(input: List<String>): Int {
        var sum = 0
        for (line in input) {
            sum += computeMulsInString(line)
        }
        return sum
    }

    fun part2(input: List<String>): Int {
        var sum = 0
        var flag = true

        for (line in input) {
            val regexResult = """mul\(([0-9]{1,3}),([0-9]{1,3})\)|do\(\)|don't\(\)""".toRegex().findAll(line)
            val results = regexResult.toList()
            for(result in results) {
                if (result.value.startsWith("mul") && flag) {
                    sum += result.groupValues[1].toInt() * result.groupValues[2].toInt()
                }
                else if(result.value == "don't()") flag = false
                else if(result.value == "do()") flag = true
            }
        }
        return sum
    }


    val input = readInput("Day03_part2")
//    println(part1(input))
    println(part2(input))
}
