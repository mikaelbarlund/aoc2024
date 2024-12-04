fun main() {
    val file = "src/input/santa/Day03.txt"
    Day03.day03(file)
}

class Day03 {
    companion object {
        private fun String.findmatches(regex: Regex): List<String> {
            return regex.findAll(this).map { it.value }.toList()
        }
        private fun String.mul(): Int {
            val (a, b) = this.findmatches(Regex("\\d{1,3}")).map { it.toInt() }
            return a*b
        }
        fun day03(path: String) {
            println(
                "first: " + //168539636
                        path.readFile()
                            .findmatches(Regex("mul\\(\\d{1,3},\\d{1,3}\\)"))
                            .sumOf { it.mul() }
            )
            println(
                "second: \n" + //97529391
                        path.readFile()
                            .replace("\n", "")
                            .findmatches(Regex("(^|do\\(\\)).*?($|don't\\(\\))"))
                            .joinToString ()
                            .findmatches(Regex("mul\\(\\d{1,3},\\d{1,3}\\)"))
                            .sumOf { it.mul() }
            )
        }
    }
}