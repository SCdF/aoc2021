package aoc

import scala.collection.mutable.ArrayBuffer
import scala.io.Source

object Day02 {
  case class Location(var forward: Int, var down: Int, var aim: Int)
  class Direction(val instruction: String) {
    val direction :: distanceStr :: _ = instruction.split(' ').toList
    val distance: Int = distanceStr.toInt

    def apply(loc: Location) {
      direction match {
        case "forward" => {
          loc.forward += distance
          loc.down += (loc.aim * distance)
        }
        case "down" => loc.aim += distance
        case "up" => loc.aim -= distance
      }
    }
  }

  def calculateLocation(directions: Iterable[String]): Int = {
    val loc = Location(0,0,0)
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
