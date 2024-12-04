fun main() {
    val file = "src/input/santa/Day03.txt"
    Day03.day03(file)
}

class Day03 {
    companion object {

        private fun String.mul(): Int {
            val (a, b) = this.findMatches(Regex("\\d{1,3}")).map { it.toInt() }
            return a*b
        }
        fun day03(path: String) {
            println(
                "first: " + //168539636
                        path.readFile()
                            .findMatches(Regex("mul\\(\\d{1,3},\\d{1,3}\\)"))
                            .sumOf { it.mul() }
            )
            println(
                "second: \n" + //97529391
                        path.readFile()
                            .replace("\n", "")
                            .findMatches(Regex("(^|do\\(\\)).*?($|don't\\(\\))"))
                            .joinToString ()
                            .findMatches(Regex("mul\\(\\d{1,3},\\d{1,3}\\)"))
                            .sumOf { it.mul() }
            )
        }
    }
}