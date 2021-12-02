package aoc

import scala.io.Source

object Day01 {
  def incrementCount(depths: List[Int]): Int = {
    depths
      .sliding(2)
      .map((window) => if (window(1) > window(0)) 1 else 0)
      .reduce(_+_)
  }

  def main() {
    val day01 = Source.fromFile("./src/data/day01")
      .getLines.toList
      .map(_.toInt)

    val count = incrementCount(day01)
    println(f"Day 01: $count")
    val day01Averaged = day01
      .sliding(3)
      .map(_.reduce(_+_))
      .toList

    val averagedCount = incrementCount(day01Averaged)
    println(f"Day 01 averaged count: $averagedCount")
  }
}

object Day02 {
  case class Location(var forward: Int, var down: Int)
  class Direction(val instruction: String) {
    val direction :: distanceStr :: _ = instruction.split(' ').toList
    val distance: Int = distanceStr.toInt

    def apply(loc: Location) {
      direction match {
        case "forward" => loc.forward += distance
        case "down" => loc.down += distance
        case "up" => loc.down -= distance
      }
    }
  }

  def calculateLocation(directions: List[String]): Int = {
    val loc = Location(0,0)
    directions
      .map(new Direction(_))
      .map(_.apply(loc))

    loc.forward * loc.down
  }

  def main() {
    val day02 = Source.fromFile("./src/data/day02")
      .getLines.toList

    val answer = calculateLocation(day02)
    println(f"Day 02: $answer")
  }
}

object Aoc extends App {
  Day01.main()
  Day02.main()
}
