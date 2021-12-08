package aoc

import org.scalatest.funsuite._
import scala.io.Source

class Day07Spec extends AnyFunSuite {
  val testInput = "16,1,2,0,4,2,7,1,2,14"

  test("works") {
    assert(Day07.optimalFuel(testInput) == 37)
  }
}
