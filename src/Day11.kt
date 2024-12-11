fun main() {
    fun part1(input: List<String>): Int {
        var arr = input[0].split(' ').map { it.toLong() }. toMutableList()
        for (i in 0 until  25) {
            val tmp = mutableListOf<Long>()
            for (el in arr) {
                if(el == 0L)
                    tmp.add(1)
                else if(el.toString().length % 2 == 0) {
                    val len = el.toString().length
                    tmp.add(el.toString().substring(0..<len/2).toLong())
                    tmp.add(el.toString().substring(len/2..<len).toLong())
                }
                else
                    tmp.add(el * 2024)
            }
            arr = tmp
        }
        return arr.size
    }

    fun part2(input: List<String>): Long {
        val arr = input[0].split(' ').map { it.toLong() }. toMutableList()
        var stoneCounts = arr.groupingBy { it }.eachCount().mapValues { it.value.toLong() }.mapKeys { it.key.toString() }.toMutableMap()

        for (i in 0 until  75) {
            val newStoneCounts = mutableMapOf<String, Long>()

            for ((stone, count) in stoneCounts) {
                if(stone == "0")
                    newStoneCounts["1"] = newStoneCounts.getOrDefault("1", 0L) + count
                else if(stone.length % 2 == 0) {
                    val mid = stone.length / 2
                    val left = stone.substring(0, mid).toLong()
                    val right = stone.substring(mid).toLong()
                    newStoneCounts[left.toString()] = newStoneCounts.getOrDefault(left.toString(), 0L) + count
                    newStoneCounts[right.toString()] = newStoneCounts.getOrDefault(right.toString(), 0L) + count
                }
                else {
                    val newStone = (stone.toLong() * 2024).toString()
                    newStoneCounts[newStone] = newStoneCounts.getOrDefault(newStone, 0L) + count
                }
            }
            stoneCounts = newStoneCounts
        }
        return stoneCounts.values.sum()
    }


    val input = readInput("Day11")
//    println(part1(input))
    println(part2(input))
}
