package aoc

import scala.io.Source

object Aoc extends App {
  // Day01.main()
  // Day02.main()
  // Day03.main()
  // Day04.main()

  // val day05 = Source.fromFile("./src/data/day05").getLines.toList
  // println(f"Day 05: ${Day05.dangerCount(day05)}")
  // println(f"Day 05.2: ${Day05.dangerCount(day05, true)}")

  // val day06 = Source.fromFile("./src/data/day06").getLines.mkString
  // println(f"Day 06: ${Day06.spawnSimulation(day06, 80)}")
  // println(f"Day 06.2: ${Day06.spawnSimulation(day06, 256)}")

  // val day07 = Source.fromFile("./src/data/day07").getLines.mkString
  // println(f"Day 07: ${Day07.optimalFuel(day07)}")
  // println(f"Day 07.2: ${Day07.evenMoreOptimalFuel(day07)}")

  // val day08 = Source.fromFile("./src/data/day08").getLines.toList
  // println(f"Day 08: ${Day08.easyCount(day08)}")
  // val day08Total = day08.map(Day08.digits).reduce(_+_)
  // println(f"Day 08.2: $day08Total")

  // val day09 = Source.fromFile("./src/data/day09").mkString
  // println(f"Day 09: ${Day09.riskLevel(day09)}")
  // println(f"Day 09.2: ${Day09.basins(day09)}")

  // val day10 = Source.fromFile("./src/data/day10").mkString
  // println(f"Day 10: ${Day10.errorScore(day10)}")
  // println(f"Day 10.1: ${Day10.completionScore(day10).toLong}")

  val day11 = """4871252763
8533428173
7182186813
2128441541
3722272272
8751683443
3135571153
5816321572
2651347271
7788154252"""
  println(f"Day 11: ${Day11.flashCount(day11, 100)}")
  println(f"Day 11.1: ${Day11.stepsUntilSynchronization(day11)}")

  val day12 = """xx-xh
vx-qc
cu-wf
ny-LO
cu-DR
start-xx
LO-vx
cu-LO
xx-cu
cu-ny
xh-start
qc-DR
vx-AP
end-LO
ny-DR
vx-end
DR-xx
start-DR
end-ny
ny-xx
xh-DR
cu-xh"""
  println(f"Day 12: ${Day12.paths(day12).size}")
}
