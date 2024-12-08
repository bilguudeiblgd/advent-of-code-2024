import kotlin.math.abs

fun main() {

    fun part1(input: List<String>): Int {
        var sum = 0
        val n = input.size
        val m = input[0].length
        for (i in input.indices) {
            for (j in input[i].indices) {
                if (input[i][j] == 'X') {

                    if (j + 3 < n) {
                        val toLeft = "${input[i][j + 1]}${input[i][j + 2]}${input[i][j + 3]}"
                        sum += if (toLeft == "MAS") 1 else 0
                    }

                    if (j - 3 >= 0) {
                        val toLeft = "${input[i][j - 1]}${input[i][j - 2]}${input[i][j - 3]}"
                        sum += if (toLeft == "MAS") 1 else 0
                    }

                    // Check down
                    if (i + 3 < n) {
                        val toDown = "${input[i + 1][j]}${input[i + 2][j]}${input[i + 3][j]}"
                        sum += if (toDown == "MAS") 1 else 0
                    }

                    // Check up
                    if (i - 3 >= 0) {
                        val toDown = "${input[i - 1][j]}${input[i - 2][j]}${input[i - 3][j]}"
                        sum += if (toDown == "MAS") 1 else 0
                    }

                    // Check diagonal down-right
                    if (i + 3 < n && j + 3 < m) {
                        val diagonalDownRight = "${input[i + 1][j + 1]}${input[i + 2][j + 2]}${input[i + 3][j + 3]}"
                        sum += if (diagonalDownRight == "MAS") 1 else 0
                    }

                    // Check diagonal down-left
                    if (i + 3 < n && j - 3 >= 0) {
                        val diagonalDownLeft = "${input[i + 1][j - 1]}${input[i + 2][j - 2]}${input[i + 3][j - 3]}"
                        sum += if (diagonalDownLeft == "MAS") 1 else 0
                    }

                    // Check diagonal up-right
                    if (i - 3 >= 0 && j + 3 < m) {
                        val diagonalUpRight = "${input[i - 1][j + 1]}${input[i - 2][j + 2]}${input[i - 3][j + 3]}"
                        sum += if (diagonalUpRight == "MAS") 1 else 0
                    }

                    // Check diagonal up-left
                    if (i - 3 >= 0 && j - 3 >= 0) {
                        val diagonalUpLeft = "${input[i - 1][j - 1]}${input[i - 2][j - 2]}${input[i - 3][j - 3]}"
                        sum += if (diagonalUpLeft == "MAS") 1 else 0
                    }
                }
            }
        }
        return sum
    }

    fun part2(input: List<String>): Int {
        var sum = 0
        val n = input.size
        val m = input[0].length
        for (i in input.indices) {
            for (j in input[i].indices) {
                if (input[i][j] == 'A') {
                    val leftTop = if (i - 1 >= 0 && j - 1 >= 0) input[i - 1][j - 1] else continue
                    val leftBottom = if (i + 1 < n && j - 1 >= 0) input[i + 1][j - 1] else continue
                    val rightTop = if (i - 1 >= 0 && j + 1 < n) input[i - 1][j + 1] else continue
                    val rightBottom = if (i + 1 < n && j + 1 < n) input[i + 1][j + 1] else continue

                    if("${leftTop}${rightBottom}" != "MS" && "${leftTop}${rightBottom}" != "SM" ) {
                        continue
                    }
                    if("${leftBottom}${rightTop}" != "MS" && "${leftBottom}${rightTop}" != "SM" ) {
                        continue
                    }
                    sum += 1
                }
            }
        }
        return sum

    }

    val input = readInput("Day04")
    println(part1(input))
    println(part2(input))
}