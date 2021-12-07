import org.scalatest.funsuite._
import aoc._

class Day03Spec extends AnyFunSuite {

  test("commonBitmask treats equal counts as 0 by default") {
    assert(Day03.commonBitmask(List(
      List(0,0,0),
      List(1,1,1),
    )) == List(0,0,0))
  }

  test("commonBitmask can treat equal counts as 0") {
    assert(Day03.commonBitmask(List(
      List(0,0,0),
      List(1,1,1),
    ), false) == List(0,0,0))
  }

  test("commonBitmask can treat equal counts as 1") {
    assert(Day03.commonBitmask(List(
      List(0,0,0),
      List(1,1,1),
    ), true) == List(1,1,1))
  }

  test("works part 1") {
    val testInput = List(
      "00100",
      "11110",
      "10110",
      "10111",
      "10101",
      "01111",
      "00111",
      "11100",
      "10000",
      "11001",
      "00010",
      "01010",
    )

    assert(Day03.powerConsumption(testInput) == 198)
  }

  test("works part 2") {
     val testInput = List(
      "00100",
      "11110",
      "10110",
      "10111",
      "10101",
      "01111",
      "00111",
      "11100",
      "10000",
      "11001",
      "00010",
      "01010",
    )

    assert(Day03.lifeSupportRating(testInput) == 230)
  }
}
