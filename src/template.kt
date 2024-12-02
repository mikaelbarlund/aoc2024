fun main() {
    val file = "src/input/santa/template.txt"
    DayX.dayX(file)
}

class DayX {
    companion object {
        fun dayX(path: String) {
            println(
                "day 1 first: " +
                        path.readFile()
            )
            println(
                "day 1 second: " +
                        path.readFile()
            )
        }
    }
}