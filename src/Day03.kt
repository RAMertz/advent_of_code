fun main() {

    fun getCommonMax(position: Int, list: List<String>): Int {
        val input = list.map { it[position] }

        val zero = input.count { it == '0' }
        val one = input.count { it == '1' }

        return if (zero < one) {
            1
        } else 0
    }

    fun getCommonMin(position: Int, list: List<String>): Int {
        val input = list.map { it[position] }

        val zero = input.count { it == '0' }
        val one = input.count { it == '1' }

        return if (zero > one) {
            1
        } else 0
    }

    fun part1(input: List<String>): Int {
        var max = ""
        max = max.plus(getCommonMax(0, input))
        max = max.plus(getCommonMax(1, input))
        max = max.plus(getCommonMax(2, input))
        max = max.plus(getCommonMax(3, input))
        max = max.plus(getCommonMax(4, input))
        max = max.plus(getCommonMax(5, input))
        max = max.plus(getCommonMax(6, input))
        max = max.plus(getCommonMax(7, input))
        max = max.plus(getCommonMax(8, input))
        max = max.plus(getCommonMax(9, input))
        max = max.plus(getCommonMax(10, input))
        max = max.plus(getCommonMax(11, input))

        var min = ""
        min = min.plus(getCommonMin(0, input))
        min = min.plus(getCommonMin(1, input))
        min = min.plus(getCommonMin(2, input))
        min = min.plus(getCommonMin(3, input))
        min = min.plus(getCommonMin(4, input))
        min = min.plus(getCommonMin(5, input))
        min = min.plus(getCommonMin(6, input))
        min = min.plus(getCommonMin(7, input))
        min = min.plus(getCommonMin(8, input))
        min = min.plus(getCommonMin(9, input))
        min = min.plus(getCommonMin(10, input))
        min = min.plus(getCommonMin(11, input))

        return max.toInt(2) * min.toInt(2)
    }

    fun getCommonO2(position: Int, list: List<String>): Char {
        val input = list.map { it[position] }

        val zero = input.count { it == '0' }
        val one = input.count { it == '1' }

        return if (zero <= one) {
            '1'
        } else '0'
    }

    fun getCommonCO2(position: Int, list: List<String>): Char {
        val input = list.map { it[position] }

        val zero = input.count { it == '0' }
        val one = input.count { it == '1' }

        return if (zero > one) {
            '1'
        } else '0'
    }

    fun filterCommonO2(position: Int, input: String, oxygenNum: Char): Boolean {
        return input[position] == oxygenNum
    }

    fun constructO2AtPosition(position: Int, input: List<String>, oxygenNum: Char, hasVal: Boolean = false): List<String> {
        var output = mutableListOf<String>()
        if (input.isNotEmpty()) {
            output = if (filterCommonO2(position, input[0], oxygenNum)) {
                output.plus(input[0]).plus(constructO2AtPosition(position, input.drop(1), oxygenNum, true)).toMutableList()
            } else {
                if (input.size == 1 && !hasVal) return input
                constructO2AtPosition(position, input.drop(1), oxygenNum, hasVal).toMutableList()
            }
        }
        return output
    }

    fun constructO2(input: List<String>): Int {
        var oxygen = constructO2AtPosition(0, input, getCommonO2(0, input))
        oxygen = constructO2AtPosition(1, oxygen, getCommonO2(1, oxygen))
        oxygen = constructO2AtPosition(2, oxygen, getCommonO2(2, oxygen))
        oxygen = constructO2AtPosition(3, oxygen, getCommonO2(3, oxygen))
        oxygen = constructO2AtPosition(4, oxygen, getCommonO2(4, oxygen))
        oxygen = constructO2AtPosition(5, oxygen, getCommonO2(5, oxygen))
        oxygen = constructO2AtPosition(6, oxygen, getCommonO2(6, oxygen))
        oxygen = constructO2AtPosition(7, oxygen, getCommonO2(7, oxygen))
        oxygen = constructO2AtPosition(8, oxygen, getCommonO2(8, oxygen))
        oxygen = constructO2AtPosition(9, oxygen, getCommonO2(9, oxygen))
        oxygen = constructO2AtPosition(10, oxygen, getCommonO2(10, oxygen))
        oxygen = constructO2AtPosition(11, oxygen, getCommonO2(11, oxygen))

        var carbon = constructO2AtPosition(0, input, getCommonCO2(0, input))
        carbon = constructO2AtPosition(1, carbon, getCommonCO2(1, carbon))
        carbon = constructO2AtPosition(2, carbon, getCommonCO2(2, carbon))
        carbon = constructO2AtPosition(3, carbon, getCommonCO2(3, carbon))
        carbon = constructO2AtPosition(4, carbon, getCommonCO2(4, carbon))
        carbon = constructO2AtPosition(5, carbon, getCommonCO2(5, carbon))
        carbon = constructO2AtPosition(6, carbon, getCommonCO2(6, carbon))
        carbon = constructO2AtPosition(7, carbon, getCommonCO2(7, carbon))
        carbon = constructO2AtPosition(8, carbon, getCommonCO2(8, carbon))
        carbon = constructO2AtPosition(9, carbon, getCommonCO2(9, carbon))
        carbon = constructO2AtPosition(10, carbon, getCommonCO2(10, carbon))
        carbon = constructO2AtPosition(11, carbon, getCommonCO2(11, carbon))

        println("carbon: $carbon")
        println("carbon: ${carbon[0].toInt(2)}")
        println("oxygen: $oxygen")
        println("oxygen: ${oxygen[0].toInt(2)}")

        return carbon[0].toInt(2) * oxygen[0].toInt(2)
    }

    fun part2(input: List<String>): Int {

        return constructO2(input)
    }

    val input = readInput("Day03")

    // println(part1(input))
    println(part2(input))
}
