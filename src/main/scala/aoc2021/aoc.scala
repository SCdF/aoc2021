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

  val day06 = Source.fromFile("./src/data/day06").getLines.mkString
  println(f"Day 06: ${Day06.spawnSimulation(day06, 80)}")
  println(f"Day 06.2: ${Day06.spawnSimulation(day06, 256)}")

  val day07 = Source.fromFile("./src/data/day07").getLines.mkString
  println(f"Day 07: ${Day07.optimalFuel(day07)}")
}
