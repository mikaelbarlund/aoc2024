fun main() {
    val file = "src/input/santa/Day03.txt"
    Day03.day03(file)
}

class Day03 {
    companion object {
        fun day03(path: String) {
            println(
                "first: " +
                        path.readFile()
            )
            println(
                "second: " +
                        path.readFile()
            )
        }
    }
}