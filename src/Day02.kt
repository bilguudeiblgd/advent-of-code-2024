import kotlin.math.abs

fun main() {
    fun isSafeList(list: List<Int>): Int {
        val ascendingList = list.sorted()
        val descendingList = list.sortedDescending()
        if(list != ascendingList && list != descendingList) {
            return 0
        }
        for(i in 1 until ascendingList.size) {
            val diff = ascendingList[i] - ascendingList[i-1]
            if(diff !in 1..3) {
                return 0
            }
        }
        return 1
    }

    fun part1(input: List<String>): Int {
        var sum = 0
        input.forEach { inp ->
            val list = inp.split(" ").map { it.toInt() }
            sum += isSafeList(list)
            }
        return sum
    }

    fun part2(input: List<String>): Int {
        var sum = 0
        for (i in input.indices) {
            val list = input[i].split(" ").toMutableList().map { it.toInt() }.toMutableList()
            var indexToDrop = 0
            var ascending = 0
            for (j in 1 until list.size) {
                val diff = abs(list[j] - list[j-1])
                if(diff !in 1..3) {
                    indexToDrop = j
                    break
                }
                if(ascending == 1) {
                    if(list[j] - list[j-1] < 0) {
                        indexToDrop = j;
                        break;
                    }
                } else if(ascending == -1) {
                    if(list[j] - list[j-1] > 0) {
                        indexToDrop = j;
                        break;
                    }
                }
                else {
                    if(list[j] - list[j-1] > 0) {
                        ascending = 1
                    } else {
                        ascending = -1
                    }
                }
            }

            val check0 = isSafeList(list.filterIndexed { index, _ -> index != indexToDrop })
            val check1 = isSafeList(list.filterIndexed { index, _ -> index != 0 })
            val check2 = isSafeList(list.filterIndexed { index, _ -> index != indexToDrop - 1 })

            if(check1 == 1 || check0 == 1 || check2 == 1) {
                sum += 1
            }
        }
        return sum
    }

    val input = readInput("Day02")
    println(part1(input))
    println(part2(input))
}