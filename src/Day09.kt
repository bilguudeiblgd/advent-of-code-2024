import kotlin.math.abs

fun main() {
    fun part1(input: List<String>): Long {
        var sum: Long = 0
        for (line in input) {
            var sparseList : MutableList<String> = mutableListOf()
            for(i in line.indices) {
                if(i % 2 == 0) {
                    sparseList.addAll( List(line[i].digitToInt()) { "${i / 2}" } )
                } else {
                    sparseList.addAll( List(line[i].digitToInt()) { "." } )
                }
            }
            var i = 0;
            var j = sparseList.size - 1;
            while(i < j) {
                if(sparseList[i] != ".") {
                    i++;
                    continue
                }
                if(sparseList[j] == ".") {
                    sparseList.removeAt(j)
                    j--;
                    continue
                }

                if(sparseList[i] == ".") {
                    sparseList[i] = sparseList[j];
                    sparseList.removeAt(j)
                    j--;
                    i++;
                    continue;
                }
            }

            sparseList.forEachIndexed { index, str ->
                    if(str != ".")
                        sum += index * str.toInt();
            }
        }
        return sum
    }

    fun part2(input: List<String>): Long {
        fun getFreeSpace(list: List<Int>, index: Int): Int {
            var sum = 0
            for(i in index until list.size) {
                if(list[i] != -1) return sum
                sum++
            }
            return sum
        }
        var sum: Long = 0
        for (line in input) {
            var sparseList : MutableList<Int> = mutableListOf()
            for(i in line.indices) {
                if(i % 2 == 0) {
                    sparseList.addAll( List(line[i].digitToInt()) {i / 2} )
                } else {
                    sparseList.addAll( List(line[i].digitToInt()) {-1}  )
                }
            }
            var j = sparseList.size - 1;
            while(0 < j) {
                // is whitespace
                if(sparseList[j] < 0) {
                    j--;
                    continue
                }

                val neededSpace = sparseList.count { sparseList[j] == it }
                for(k in 0 until j) {
                    val totalSpace = getFreeSpace(sparseList, k)
                    if(totalSpace >= neededSpace) {
                        for(m in 0..<neededSpace) {
                            sparseList[k + m] = sparseList[j-m]
                            sparseList[j-m] = -1
                        }
                        break
                    }
                }
                j--;
                continue;
            }

            sparseList.forEachIndexed { index, num ->
                if(num != -1)
                    sum += index * num;
            }
        }
        return sum

    }


    val input = readInput("Day09")
//    println(part1(input))
    println(part2(input))
}
