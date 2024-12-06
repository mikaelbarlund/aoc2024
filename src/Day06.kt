fun main() {
    val file = "src/input/santa/Day06.txt"
    Day06.day06(file)
}

class Day06 {
    companion object {
        private val UP = Pair(-1, 0)
        private val RIGHT = Pair(0, 1)
        private val DOWN = Pair(1, 0)
        private val LEFT = Pair(0, -1)
        fun day06(path: String) {
            val input = path.readFile()
            val grid = input.split("\n").map { it.split("").filter { it.isNotEmpty() } }
            val start = grid.findCharPosition()
            var direction = UP
            val guardPath = mutableListOf(start!!)
            while (guardPath[0].inGrid(grid)) {
                val position = guardPath[0]
                val nextPosition = position.nextPosition(direction)
                if (grid.charAt(nextPosition) == "#") {
                    direction = when (direction) {
                        UP -> RIGHT
                        RIGHT -> DOWN
                        DOWN -> LEFT
                        else -> UP
                    }
                } else {
                    guardPath.add(0, nextPosition)
                }
            }
            val distinctLocations = guardPath.toSet()
            println(
                "first: "
                        + "\npath distinct: " + (distinctLocations.size - 1)
            )
            val now = System.currentTimeMillis()
            val foo = distinctLocations.filter { it.inGrid(grid) && it != start }.filterIndexed { i, obstruct ->
                if (i % 100 == 0) println(" " + i + " " + ((System.currentTimeMillis() - now) / (i + 1)))
                val newGrid = grid.replaceChar(obstruct, "#")
                val guardPathO = mutableListOf(Pair(start, UP))
                while (guardPathO[0].first.inGrid(newGrid)) {
                    val position = guardPathO[0].first
                    val nextPosition = position.nextPosition(guardPathO[0].second)
                    val nexttep = if (newGrid.charAt(nextPosition) == "#") {
                        val nextDirection = when (guardPathO[0].second) {
                            UP -> RIGHT
                            RIGHT -> DOWN
                            DOWN -> LEFT
                            else -> UP
                        }
                        Pair(guardPathO[0].first, nextDirection)
                    } else {
                        Pair(nextPosition, guardPathO[0].second)
                    }
                    if (guardPathO.contains(nexttep)) {
                        return@filterIndexed true
                    }
                    guardPathO.add(0, nexttep)
                }
                false
            }
            println(
                "second: "
                        + foo.size
            )
            println("time: " + (System.currentTimeMillis() - now) / 1000)
        }
    }
}

private fun List<List<String>>.findCharPosition(): Pair<Int, Int>? {
    for (i in this.indices) {
        for (j in this[i].indices) {
            if (this[i][j] == "^") {
                return Pair(i, j)
            }
        }
    }
    return null
}

private fun List<List<String>>.charAt(position: Pair<Int, Int>): String {
    return if (position.inGrid(this)) this[position.first][position.second] else "X"
}

private fun Pair<Int, Int>.inGrid(grid: List<List<String>>): Boolean {
    return this.first in grid.indices && this.second in grid[this.first].indices
}

private fun Pair<Int, Int>.nextPosition(direction: Pair<Int, Int>): Pair<Int, Int> {
    return Pair(this.first + direction.first, this.second + direction.second)
}

private fun List<List<String>>.replaceChar(position: Pair<Int, Int>, newChar: String): List<List<String>> {
    return this.mapIndexed { rowIndex, row ->
        row.mapIndexed { colIndex, char ->
            if (rowIndex == position.first && colIndex == position.second) newChar else char
        }
    }
}