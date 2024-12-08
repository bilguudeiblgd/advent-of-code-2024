import kotlin.math.abs


fun isInsideGrid(coordinate: Pair<Int, Int>, n: Int,m: Int): Boolean {
    return !(coordinate.first < 0 || coordinate.second < 0 || coordinate.first >= n || coordinate.second >= m)
}

fun main() {
    fun part1(input: List<String>): Int {
        var sum = 0
        val n = input.size
        val m = input[0].length
        val map = input.map { it -> it.map { it1 -> it1 }.toMutableList() }.toMutableList()
        val dictionary: MutableMap<Char, MutableList<Pair<Int, Int>>> = mutableMapOf()
        for (i in map.indices) {
            for (j in map[i].indices) {
                if(map[i][j] == '.') continue
                if (dictionary.contains(map[i][j]))
                    dictionary[map[i][j]]?.add(Pair(i, j))
                else
                    dictionary[map[i][j]] = mutableListOf(Pair(i, j))
            }
        }


        // create anti frequency

        for ((key, value) in dictionary) {
            for(i in value.indices) {
                for(j in i +1  until value.size) {
                    val position = value[i]
                    val position1 = value[j]
                    if(position1 == position) continue

                    val diff = position1.subtract(position)

                    val resonant = position1.add(diff)
                    val resonant1 = Pair(position.first - diff.first, position.second - diff.second)

                    if(isInsideGrid(resonant, n, m)) {
                        map[resonant.first][resonant.second] = '#'
                    }


                    if(isInsideGrid(resonant1, n, m) ) {
                        map[resonant1.first][resonant1.second] = '#'
                    }

                }
            }
        }

        sum = map.sumOf { it.count {c -> c == '#'} }

        return sum
    }



    fun part2(input: List<String>): Int {
        var sum = 0
        val n = input.size
        val m = input[0].length
        val map = input.map { it -> it.map { it1 -> it1 }.toMutableList() }.toMutableList()
        val dictionary: MutableMap<Char, MutableList<Pair<Int, Int>>> = mutableMapOf()
        for (i in map.indices) {
            for (j in map[i].indices) {
                if(map[i][j] == '.') continue
                if (dictionary.contains(map[i][j]))
                    dictionary[map[i][j]]?.add(Pair(i, j))
                else
                    dictionary[map[i][j]] = mutableListOf(Pair(i, j))
            }
        }


        // create anti frequency

        for ((key, value) in dictionary) {
            for(i in value.indices) {
                for(j in i +1  until value.size) {
                    val position = value[i]
                    val position1 = value[j]
                    if(position1 == position) continue

                    val diff = position1.subtract(position)

                    var toDownResonant = position1.add(diff)
                    var toUpResonant = position.subtract(diff)
                    while(isInsideGrid(toDownResonant, n, m)) {
                        val (y,x) = toDownResonant
                        if(map[y][x] == '.') {
                            map[y][x] = '#'
                        }
                        toDownResonant = toDownResonant.add(diff)
                    }

                    while(isInsideGrid(toUpResonant, n, m)) {
                        val (y,x) = toUpResonant
                        if(map[y][x] == '.') {
                            map[y][x] = '#'
                        }
                        toUpResonant = toUpResonant.subtract(diff)
                    }
                }
            }
        }

        sum = map.sumOf { it.count {c -> c != '.'} }

        return sum

    }


    val input = readInput("Day08")
//    println(part1(input))
    println(part2(input))
}

fun Pair<Int,Int>.subtract(b: Pair<Int, Int>): Pair<Int, Int> {
    return Pair(this.first - b.first, this.second - b.second)
}

fun Pair<Int,Int>.add(b: Pair<Int, Int>): Pair<Int, Int> {
    return Pair(this.first + b.first, this.second + b.second)
}
