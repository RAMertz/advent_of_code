fun main() {

    class Coords(var horizontal: Int, var depth: Int, var aim: Int) {}

    fun useDirection(coords: Coords, input: List<String>): Coords {
        if (input.isEmpty()) {
            return coords
        }

        val numSpaces = input[0].last().toString().toInt()
        val direction = input[0].dropLast(2)

        if (direction == "forward") {
            coords.horizontal = coords.horizontal + numSpaces
            coords.depth = coords.depth + (coords.aim * numSpaces)
        } else if (direction == "down") {
            coords.aim = coords.aim + numSpaces
        } else if (direction == "up") {
            coords.aim = coords.aim - numSpaces
        } else throw Throwable("unrecognized direction")

        return useDirection(coords, input.drop(1))
    }

    fun part1(input: List<String>): Int {
        val coords = useDirection(Coords(0, 0, 0), input)

        return coords.depth * coords.horizontal
    }

    fun part2(input: List<String>): Int {
        return input.size
    }

    val input = readInput("Day02")

    println(part1(input))
    println(part2(input))
}
