import kotlin.math.abs

fun main() {
    val file = "src/input/santa/Day02.txt"
    Day02.day02(file)
}

class Day02 {
    private data class Day(val safe: Boolean, val previous: Int?, val dampened: Boolean)
    companion object {
        fun day02(path: String) {
            println(
                "first: " +
                        path.readFile()
                            .split("\n")
                            .map { it.split(" ").map { a -> a.toInt() } }
                            .map { it.isSafe(false) }
                            .count{it}
            )
            println(
                "second: " +
                        path.readFile()
                            .split("\n")
                            .map { it.split(" ").map { a -> a.toInt() } }
                            .map { it.isSafe(true) || it.reversed().isSafe(true) }
                            .count{it}
            )
        }

        private fun List<Int>.isSafe(dampen: Boolean): Boolean {
            val dir = this[0].dir(this[1])
            val safe = this.foldIndexed(Day(true, null as Int?, false)) { i, acc, element ->
                if (i == 0) return@foldIndexed Day(true, null, false)
                val prev: Int = acc.previous ?: (i - 1)
                val diff = abs(element - this[prev])
                val thisDir = this[prev].dir(element)
                val thisSafe = (
                        acc.safe &&
                                dir == thisDir &&
                                diff < 4 &&
                                diff > 0
                        )
                if (thisSafe) Day(true, null, acc.dampened)
                else if (dampen &&!acc.dampened) Day(true, prev, true)
                else Day(false, null, true)
            }
            return safe.safe
        }
        private fun Int.dir(b: Int): Boolean = this > b
    }
}


