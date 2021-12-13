package aoc

import org.scalatest.funsuite._
import scala.io.Source

class Day09Spec extends AnyFunSuite {
  val testInput = """2199943210
3987894921
9856789892
8767896789
9899965678"""

  test("works") {
    assert(Day09.riskLevel(testInput) == 15)
  }
}
