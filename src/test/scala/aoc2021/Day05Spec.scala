package aoc

import org.scalatest.funsuite._
import scala.io.Source

class Day05Spec extends AnyFunSuite {
      val testInput = """0,9 -> 5,9
8,0 -> 0,8
9,4 -> 3,4
2,2 -> 2,1
7,0 -> 7,4
6,4 -> 2,0
0,9 -> 2,9
3,4 -> 1,4
0,0 -> 8,8
5,5 -> 8,2"""

  test("works hor and vert") {
    assert(Day05.dangerCount(testInput.split('\n').toList) == 5)
  }
  test("works hor, vert and diag") {
    assert(Day05.dangerCount(testInput.split('\n').toList, true) == 12)
  }
}
