import org.scalatest.funsuite._
import aoc._

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
