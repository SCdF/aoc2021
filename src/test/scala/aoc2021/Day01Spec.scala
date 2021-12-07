package aoc

import org.scalatest.funsuite._

class Day01Spec extends AnyFunSuite {
  test("incrementCount should work!") {
    val testInput = List(199,200,208,210,200,207,240,269,260,263)
    assert(Day01.incrementCount(testInput) == 7)
  }
}
