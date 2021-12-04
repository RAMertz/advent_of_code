fun main() {

    data class BingoBlock(val value: Int, val marked: Boolean = false)

    data class BingoBoard(
        val rows: List<List<BingoBlock>>,
        val columns: List<List<BingoBlock>>,
        val turnsToComplete: Int = 1000,
        val winningNum: Int = 1000,
    )

    fun parseRow(row: String): List<Int> {
        return row.split(" ").filterNot { it == "" }.map { it.toInt() }
    }

    fun parseBingoNums(bingoNums: String): List<Int> {
        return bingoNums.split(",").map { it.toInt() }
    }

    fun getBoardsRows(rows: List<String>): List<List<List<Int>>> {
        val windowedRows = rows.windowed(5, 6)

        return windowedRows.map { it.map { parseRow(it) } }
    }

    fun printBoardRows(row: List<List<BingoBlock>>) {
        row.map { println(it) }
        println()
    }

    fun setRows(row: List<List<Int>>): BingoBoard {
        val board = BingoBoard(
            row.map { it.map { BingoBlock(it) } },
            emptyList()
        )

        return board
    }

    fun setColumns(board: BingoBoard): BingoBoard {
        val rows = board.rows
        val size = rows[0].size
        var n = 1

        val columns = mutableListOf(rows.map { it[0] })

        while (n < size) {
            columns += rows.map { it[n] }
            n++
        }

        return BingoBoard(rows, columns.toList())
    }

    fun markMatchingValues(value: Int, unit: List<List<BingoBlock>>): List<List<BingoBlock>> {
        return unit.map {
            it.map {
                if (it.value == value) {
                    BingoBlock(value, true)
                } else BingoBlock(it.value, it.marked)
            }
        }
    }

    fun checkForCompletion(rows: List<List<BingoBlock>>, columns: List<List<BingoBlock>>, turns: Int): Int? {
        return if (rows.map { it.map { it.marked } }.contains(rows.map { true }) ||
            columns.map { it.map { it.marked } }.contains(columns.map { true })
        ) turns + 1
        else null
    }

    fun setBingoNumsToComplete(board: BingoBoard, bingoNums: List<Int>): BingoBoard {
        var rows = listOf<List<BingoBlock>>()
        var columns = listOf<List<BingoBlock>>()
        val size = bingoNums.size
        var n = 0
        var bingo = false
        var bingoBoard = board

        while (n < size && !bingo) {
            rows = markMatchingValues(
                value = bingoNums[n],
                unit = bingoBoard.rows
            )
            columns = markMatchingValues(
                value = bingoNums[n],
                unit = bingoBoard.columns
            )
            bingoBoard = checkForCompletion(rows, columns, n)?.let {
                BingoBoard(
                    rows = rows,
                    columns = columns,
                    turnsToComplete = it,
                    winningNum = bingoNums[n]
                )
            } ?: BingoBoard(rows = rows, columns = columns)

            if (bingoBoard.turnsToComplete != 1000) {
                bingo = true
            }
            n++
        }


        return bingoBoard
    }

    fun createBingoBoard(row: List<List<Int>>, bingoNums: List<Int>): BingoBoard {
        var board = setRows(row)
        board = setColumns(board)
        board = setBingoNumsToComplete(board, bingoNums)
        return board
    }

    fun createBingoBoards(boards: List<String>, bingoNums: List<Int>): List<BingoBoard> {
        val boardsRows = getBoardsRows(boards)

        return boardsRows.map { createBingoBoard(it, bingoNums) }
    }

    fun sumOfUnmarkedNumbers(board: BingoBoard): Int {
        var sum = 0
        board.rows.map {
            it.map {
                if (!it.marked) sum += it.value
            }
        }
        return sum
    }

    fun part1(input: List<String>): Int {
        val bingoNums = parseBingoNums(input[0])
        val bingoBoards = createBingoBoards(input.drop(2), bingoNums)

        val turnsToComplete = bingoBoards.maxByOrNull { it.turnsToComplete }?.turnsToComplete
        val winningNum = bingoBoards.maxByOrNull { it.turnsToComplete }?.winningNum
        val sumOfUnmarkedNumbers = bingoBoards.maxByOrNull { it.turnsToComplete }?.let { sumOfUnmarkedNumbers(it) }

        println(bingoBoards.map { it.turnsToComplete })


        println("turnsToComplete: $turnsToComplete")
        println("sum: $sumOfUnmarkedNumbers")

        return if (winningNum != null) {
            winningNum * sumOfUnmarkedNumbers!!
        } else 0
    }

    fun part2(input: List<String>): Int {
        return input.size
    }

    val input = readInput("Day04")

    println(part1(input))
    println(part2(input))
}
