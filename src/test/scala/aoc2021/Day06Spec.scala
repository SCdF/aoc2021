package aoc

import org.scalatest.funsuite._
import scala.io.Source

class Day06Spec extends AnyFunSuite {
  val testInput = "3,4,3,1,2"

  test("works") {
    assert(Day06.spawnSimulation(testInput, 18) == 26)
    assert(Day06.spawnSimulation(testInput, 80) == 5934)
  }
}
