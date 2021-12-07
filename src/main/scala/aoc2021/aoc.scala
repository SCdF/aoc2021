package aoc

import scala.io.Source

object Aoc extends App {
  Day01.main()
  Day02.main()
  Day03.main()
  Day04.main()

  val day05 = Source.fromFile("./src/data/day05").getLines.toList
  println(f"Day 05: ${Day05.dangerCount(day05)}")
  println(f"Day 05.2: ${Day05.dangerCount(day05, true)}")
}
