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
  println(f"Day 07.2: ${Day07.evenMoreOptimalFuel(day07)}")

  val day08 = Source.fromFile("./src/data/day08").getLines.toList
  println(f"Day 08: ${Day08.easyCount(day08)}")
  val day08Total = day08.map(Day08.digits).reduce(_+_)
  println(f"Day 08.2: $day08Total")
}
