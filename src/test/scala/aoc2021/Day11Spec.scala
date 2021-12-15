package aoc

import org.scalatest.funsuite._
import scala.io.Source

class Day11Spec extends AnyFunSuite {
  val testInput = """5483143223
2745854711
5264556173
6141336146
6357385478
4167524645
2176841721
6882881134
4846848554
5283751526"""

  test("part one works") {
    assert(Day11.flashCount(testInput, 100) == 1656)
  }
  test("completion score works") {
    assert(Day11.stepsUntilSynchronization(testInput) == 195)
  }
}
