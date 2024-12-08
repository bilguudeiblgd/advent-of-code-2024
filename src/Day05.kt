fun main() {

    fun part1(input: List<String>): Int {
        var sum = 0
        var secondPart = false
        var dependencies = HashMap<Int, MutableList<Int>>()
        for (line in input) {
            if (!secondPart) {
                if (line == "") {
                    secondPart = true
                    continue
                }
                val values = line.split('|').map { it.toInt() }
                if (dependencies.containsKey(values[1])) {
                    dependencies[values[1]]?.add(values[0])
                } else {
                    dependencies[values[1]] = mutableListOf(values[0])
                }
            } else {
                var legitOrder = true
                val list = line.split(',').map { it.toInt() }
                val seenMap: MutableMap<Int, Boolean> = list.associateWith { false }.toMutableMap()
                for (num in list) {
                    val myDependencies = dependencies.getOrDefault(num, emptyList())
                    myDependencies.forEach {
                        if (seenMap.containsKey(it) && !seenMap.getOrDefault(it, false)) {
                            legitOrder = false
                        }
                    }
                    seenMap[num] = true
                }
                if (legitOrder) {
                    sum += list[list.size / 2]
                }
            }
        }
        return sum
    }

    fun part2(input: List<String>): Int {
        var sum = 0
        var secondPart = false
        var dependencies = HashMap<Int, MutableList<Int>>()
        for (line in input) {
            if (!secondPart) {
                if (line == "") {
                    secondPart = true
                    continue
                }
                val values = line.split('|').map { it.toInt() }
                if (dependencies.containsKey(values[1])) {
                    dependencies[values[1]]?.add(values[0])
                } else {
                    dependencies[values[1]] = mutableListOf(values[0])
                }
            } else {
                var legitOrder = true
                val list = line.split(',').map { it.toInt() }
                var seenMap: MutableMap<Int, Boolean> = list.associateWith { false }.toMutableMap()
                for (num in list) {
                    val myDependencies = dependencies.getOrDefault(num, emptyList())
                    myDependencies.forEach {
                        if (seenMap.containsKey(it) && !seenMap.getOrDefault(it, false)) {
                            legitOrder = false
                        }
                    }
                    seenMap[num] = true
                }
                if (!legitOrder)
                {
                    // fix the order
                    val updateList = list.toMutableList()
                    seenMap = list.associateWith { false }.toMutableMap()
                    var i = 0;
                    while (i < list.size) {
                        var updatedTheList = false

                        val myDependencies = dependencies.getOrDefault(updateList[i], emptyList())
                        for (depNum in myDependencies) {
                            if (seenMap.containsKey(depNum) && !seenMap.getOrDefault(depNum, false)) {
                                legitOrder = false
                                updateList.removeAt(updateList.indexOf(depNum))
                                updateList.add(i, depNum)
                                updatedTheList = true
                                break
                            }
                        }
                        if (updatedTheList) continue
                        seenMap[updateList[i]] = true
                        i++
                    }
                    sum += updateList[updateList.size / 2]
                }
            }
        }
        return sum
    }

    val input = readInput("Day05")
//    println(part1(input))
    println(part2(input))
}