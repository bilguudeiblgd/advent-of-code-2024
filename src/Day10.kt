import kotlin.math.abs


val surrounding = listOf<Pair<Int, Int>>(Pair(0,-1), Pair(0 ,1), Pair(-1,0), Pair(1,0))

fun main() {
    fun dfs(coordinate: Pair<Int,Int>, map: List<String>, destinationMap : MutableMap<Pair<Int,Int>, Boolean>): Int {
        val n = map.size
        val m = map[0].length

        val currentHeight: Int = map[coordinate.first][coordinate.second].digitToInt()
        if(currentHeight == 9) {
            if(destinationMap.containsKey(coordinate)){
                return 0
            } else {
                destinationMap[coordinate] = true
                return 1
            }
        }

        var aggregate = 0
        for (s in surrounding) {
            val newCoordinate = coordinate.addCoordinates(s)
            if(newCoordinate.first >= 0 && newCoordinate.second >= 0 && newCoordinate.first < n && newCoordinate.second < m
                && map[newCoordinate.first][newCoordinate.second] != '.'
                && map[newCoordinate.first][newCoordinate.second].digitToInt() == currentHeight + 1
                ) {
                aggregate += dfs(newCoordinate, map, destinationMap)
            }
        }

        return aggregate
    }
    fun part1(input: List<String>): Int {
        var sum = 0
        for(i in input.indices) {
            for(j in input[i].indices) {
                if(input[i][j] == '0') {
                    var dest:  MutableMap<Pair<Int, Int>, Boolean> = mutableMapOf()

                    sum += dfs(Pair(i,j), input, dest)
                }
            }
        }

        return sum
    }

    fun totalPath(coordinate: Pair<Int,Int>, map: List<String>): Int {
        val n = map.size
        val m = map[0].length

        val currentHeight: Int = map[coordinate.first][coordinate.second].digitToInt()
        if(currentHeight == 9) {
            return 1
        }

        var aggregate = 0
        for (s in surrounding) {
            val newCoordinate = coordinate.addCoordinates(s)
            if(newCoordinate.first >= 0 && newCoordinate.second >= 0 && newCoordinate.first < n && newCoordinate.second < m
                && map[newCoordinate.first][newCoordinate.second] != '.'
                && map[newCoordinate.first][newCoordinate.second].digitToInt() == currentHeight + 1
            ) {
                aggregate += totalPath(newCoordinate, map)
            }
        }

        return aggregate
    }


    fun part2(input: List<String>): Int {
        var sum = 0
        for(i in input.indices) {
            for(j in input[i].indices) {
                if(input[i][j] == '0') {

                    sum += totalPath(Pair(i,j), input,)
                }
            }
        }
        return sum

    }


    val input = readInput("Day10")
//    println(part1(input))
    println(part2(input))
}

fun Pair<Int, Int>.addCoordinates(other: Pair<Int, Int>): Pair<Int, Int> {
    return Pair(this.first + other.first, this.second + other.second)
}