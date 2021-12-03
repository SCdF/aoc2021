import org.scalatest.funsuite._
import aoc._

class Day01Spec extends AnyFunSuite {
  test("incrementCount should work!") {
    val testInput = List(199,200,208,210,200,207,240,269,260,263)
    assert(Day01.incrementCount(testInput) == 7)
  }
}

class Day02Spec extends AnyFunSuite {
  test("works") {
    val testInput = List(
      "forward 5",
      "down 5",
      "forward 8",
      "up 3",
      "down 8",
      "forward 2",
    )

    assert(Day02.calculateLocation(testInput) == 900)
  }
}
