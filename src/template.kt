fun main() {
    val file = "src/input/santa/template.txt"
    DayX.dayX(file)
}

class DayX {
    companion object {
        fun dayX(path: String) {
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