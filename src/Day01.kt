fun main() {
    fun greaterThanSum(ts: List<Int>): Int = if (ts.size == 1) 0 else {
        val first = ts[0]
        val second = ts[1]
        if (first < second)
            1 + greaterThanSum(ts.drop(1))
        else
            greaterThanSum(ts.drop(1))
    }

    fun part1(input: List<String>): Int {

        val intInput = input.map { it.toInt() }

        return greaterThanSum(intInput)
    }

    fun greaterThanSumPart2(ts: List<Int>): Int = if (ts.size == 3) 0 else {
        val first = ts[0] + ts[1] + ts[2]
        val second = ts[1] + ts[2] + ts[3]
        if (first < second)
            1 + greaterThanSumPart2(ts.drop(1))
        else
            greaterThanSumPart2(ts.drop(1))
    }

    fun part2(input: List<String>): Int {
        val intInput = input.map { it.toInt() }

        return greaterThanSumPart2(intInput)
    }

    val input = readInput("Day01")

    println(part1(input))
    println(part2(input))
}
