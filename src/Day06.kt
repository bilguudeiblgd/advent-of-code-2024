import kotlin.math.abs

val GUARD_DIRECTION = listOf('^', 'v', '>', '<');

fun positionIsOutside(position: Pair<Int, Int>, n: Int, m: Int): Boolean {
    return position.first < 0 || position.second < 0 || position.first >= n || position.second >= m
}

fun determineCurrentPosition(input: List<List<Char>>): Pair<Int, Int> {
    for (y in input.indices) {
        for (x in input[0].indices) {
            if (GUARD_DIRECTION.contains(input[y][x])) {
                return Pair(y, x)
            }
        }
    }
    return Pair(-1, -1)
}

fun main() {
    fun part1(input: List<String>): Int {
        var sum = 1

        val maze = input.map { str -> str.map { it }.toMutableList() }.toMutableList()
        val n = input.size
        val m = input[0].length
        var currentPosition = determineCurrentPosition(maze)
        var visited = MutableList(n) { MutableList(m) { false } }
        visited[currentPosition.first][currentPosition.second] = true

        while (true) {
            val currentDirection = maze[currentPosition.first][currentPosition.second]
            val nextMove = when (currentDirection) {
                '^' -> currentPosition.copy(first = currentPosition.first - 1)
                'v' -> currentPosition.copy(first = currentPosition.first + 1)
                '>' -> currentPosition.copy(second = currentPosition.second + 1)
                '<' -> currentPosition.copy(second = currentPosition.second - 1)
                else -> Pair(-1, -1)
            }

            if (positionIsOutside(nextMove, n, m)) {
                break
            }

            if (input[nextMove.first][nextMove.second] == '#') {
                maze[currentPosition.first][currentPosition.second] = when (currentDirection) {
                    '^' -> '>'
                    '>' -> 'v'
                    'v' -> '<'
                    '<' -> '^'
                    else -> ' '
                }
            } else {
                maze[currentPosition.first][currentPosition.second] = 'X'
                maze[nextMove.first][nextMove.second] = currentDirection
                currentPosition = nextMove
                if (!visited[nextMove.first][nextMove.second]) {
                    sum++
                    visited[nextMove.first][nextMove.second] = true
                }
            }

        }
        return sum
    }

    fun part2(input: List<String>): Int {
        var sum = 0

        val n = input.size
        val m = input[0].length


        for (i in 0 until n) {
            for (j in 0 until m) {
                var iteration = 0
                val maze = input.map { str -> str.map { it }.toMutableList() }.toMutableList()
                var currentPosition = determineCurrentPosition(maze)
                var visited = MutableList(n) { MutableList(m) { false } }
                visited[currentPosition.first][currentPosition.second] = true

                maze[i][j] = if(GUARD_DIRECTION.contains(maze[i][j])) maze[i][j] else '#'
                var rotated = 0
                while (true) {
                    if(rotated > n * m) {
                        sum ++
                        break
                    }

                    val currentDirection = maze[currentPosition.first][currentPosition.second]
                    val nextMove = when (currentDirection) {
                        '^' -> currentPosition.copy(first = currentPosition.first - 1)
                        'v' -> currentPosition.copy(first = currentPosition.first + 1)
                        '>' -> currentPosition.copy(second = currentPosition.second + 1)
                        '<' -> currentPosition.copy(second = currentPosition.second - 1)
                        else -> Pair(-1, -1)
                    }

                    if (positionIsOutside(nextMove, n, m)) {
                        break
                    }

                    if (maze[nextMove.first][nextMove.second] == '#') {
                        maze[currentPosition.first][currentPosition.second] = when (currentDirection) {
                            '^' -> '>'
                            '>' -> 'v'
                            'v' -> '<'
                            '<' -> '^'
                            else -> ' '
                        }
                        rotated++
                    } else {
                        iteration++
                        maze[currentPosition.first][currentPosition.second] = 'X'
                        maze[nextMove.first][nextMove.second] = currentDirection
                        currentPosition = nextMove
                        if (!visited[nextMove.first][nextMove.second]) {
                            visited[nextMove.first][nextMove.second] = true
                        }
                    }

                }

            }
        }

        return sum

    }


    val input = readInput("Day06")
//    println(part1(input))
    println(part2(input))
}
