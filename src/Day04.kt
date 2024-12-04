fun main() {
    val file = "src/input/santa/Day04.txt"
    Day04.day04(file)
}

class Day04 {
    companion object {
        fun day04(path: String) {
            val data = path.readFile().split("\n").map { ("$it-").toList() }
            val horizontal = data.map { it.joinToString("") }.joinToString(" ")
            val vertical = (0..<data[0].size).map { col -> data.map { it[col] }.joinToString("") }.joinToString(" ")
            val diagonal1 = (0..<data[0].size).map { col ->
                data.indices.map { row -> data[row][(col + row) % data[0].size] }.joinToString("")
            }.joinToString(" ")
            val diagonal2 = (0..<data[0].size).map { col ->
                data.indices.map { row -> data[row].reversed()[(col + row) % data[0].size] }.joinToString("")
            }.joinToString(" ")
            val all = "$vertical $horizontal $diagonal1 $diagonal2"
            val andBack = all + " " + all.reversed()
            println(
                "first: " + //2504
                        andBack.findMatches(Regex("XMAS")).size
            )
            val data2 = path.readFile()
            /*
            M.S
            .A.
            M.S
             */
            val lineLen = data2.indexOf("\n") - 2
            val regexCombined = Regex(
                "(?=(" +
                        "M.S(?s).{$lineLen}.A.(?s).{$lineLen}M.S|" +
                        "M.M(?s).{$lineLen}.A.(?s).{$lineLen}S.S|" +
                        "S.S(?s).{$lineLen}.A.(?s).{$lineLen}M.M|" +
                        "S.M(?s).{$lineLen}.A.(?s).{$lineLen}S.M))"
            )
            println(
                "second: " + //1923
                        (data2).findMatches(regexCombined).size

            )
        }
    }
}