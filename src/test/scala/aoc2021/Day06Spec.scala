package aoc

import org.scalatest.funsuite._
import scala.io.Source

class Day06Spec extends AnyFunSuite {
  val testInput = "3,4,3,1,2"

  test("slow works for one fish") {
    val input = "3"

    val dayCounts = List(
      1, // day 0:  3
      1, // day 1:  2
      1, // day 2:  1
      1, // day 3:  0
      2, // day 4:  6,8
      2, // day 5:  5,7
      2, // day 6:  4,6
      2, // day 7:  3,5
      2, // day 8:  2,4
      2, // day 9:  1,3
      2, // day 10: 0,2
      3, // day 11: 6,8,1
      3, // day 12: 5,7,0
      4, // day 13: 4,6,6,8
    ).zipWithIndex.drop(1) // don't care about day 0


    dayCounts.foreach({ case (count, day) =>
      assert(
        Day06Slow.spawnSimulation(input, day) == count,
        f"(day $day failed)")
    })
  }

  test("quick works the same as slow for one fish") {
    val input = "3"

    (1 to 13).foreach(day =>
      assert(
        Day06.spawnSimulation(input, day) == Day06Slow.spawnSimulation(input, day),
        f"(day $day failed)")
    )
  }

  test("quick is the same as slow for with only 1 day") {
    assert(Day06.spawnSimulation(testInput, 1) == Day06Slow.spawnSimulation(testInput, 1))
  }
  test("quick is the same as slow for 2 days") {
    assert(Day06.spawnSimulation(testInput, 2) == Day06Slow.spawnSimulation(testInput, 2))
  }
  test("quick is the same as slow for 3 days") {
    assert(Day06.spawnSimulation(testInput, 3) == Day06Slow.spawnSimulation(testInput, 3))
  }

  test("works slowly") {
    assert(Day06Slow.spawnSimulation(testInput, 18) == 26)
    assert(Day06Slow.spawnSimulation(testInput, 80) == 5934)
  }

  test("works quickly") {
    assert(Day06.spawnSimulation(testInput, 18) == 26, "18 day simulation")
    assert(Day06.spawnSimulation(testInput, 80) == 5934, "80 day simulation")
    assert(Day06.spawnSimulation(testInput, 256) == 26984457539L, "256 day simulation")
  }
}
