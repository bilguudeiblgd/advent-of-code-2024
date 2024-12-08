import kotlin.math.abs

fun main() {
    fun isPossibleSum(currentSum: Long, index: Int, target: Long, nums: List<Long>): Boolean {
        if (currentSum == target) return true
        if (currentSum > target) return false
        if (index >= nums.size) return false

        return isPossibleSum(currentSum + nums[index], index + 1, target, nums) ||
                isPossibleSum(if (index == 0) nums[index] else currentSum * nums[index], index + 1, target, nums)
    }

    fun part1(input: List<String>): Long {
        var sum: Long = 0
        for (line in input) {
            val res = line.split(':')[0].toLong()
            val expr = line.split(':')[1].trim()
            val nums = expr.split(" ").map { it.toLong() }
            if (isPossibleSum(0, 0, res, nums)) {
                sum += res
            }
        }
        return sum
    }

    fun isPossibleSumWithConcat(currentSum: Long, index: Int, target: Long, nums: List<Long>): Boolean {
        if (currentSum == target && index == nums.size) return true
        if (currentSum > target) return false
        if (index >= nums.size) return false

        val concat = (currentSum.toString() + nums[index].toString()).toLong()
        return isPossibleSumWithConcat(currentSum + nums[index], index + 1, target, nums) ||
                isPossibleSumWithConcat(currentSum * nums[index], index + 1, target, nums) ||
                isPossibleSumWithConcat(concat, index + 1, target, nums)
    }

    fun part2(input: List<String>): Long {
        var sum: Long = 0
        for (line in input) {
            val res = line.split(':')[0].toLong()
            val expr = line.split(':')[1].trim()
            val nums = expr.split(" ").map { it.toLong() }
            if (isPossibleSumWithConcat(nums[0], 1, res, nums)) {
                sum += res
            }
        }
        return sum
    }


    val input = readInput("Day07")
//    println(part1(input))
    println(part2(input))
}
