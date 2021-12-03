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

  def calculateLocation(directions: List[String]): Int = {
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

object Day03 {
  def powerConsumption(input: List[String]): Int = {
    val addedRows = input     // ["010", "110", "111"]
      .map(_.map(_.asDigit))  // [[0,1,0], [1,1,0], [1,1,1]]
      .transpose              // [[0,1,1], [1,1,1], [0,0,1]]
      .map(_.reduce(_+_))     // [1,2,3]

    val cutoff = input.size / 2

    val gammaString = addedRows
      .map(x => if (x > cutoff) "1" else "0")
      .mkString

    val gamma = Integer.parseInt(gammaString, 2)
    // if only scala had unsigned ints!
    // --> val epilon = ~gamma <--
    // instead we're going to do something ugly
    val epsilonString = gammaString.map(_ match {
      case '0' => '1'
      case '1' => '0'
    }).mkString
    val epsilon = Integer.parseInt(epsilonString, 2)

    println(f"gamma: $gamma, epsilon: $epsilon")

    gamma * epsilon
  }

  def main() {
    val day03 = Source.fromFile("./src/data/day03")
      .getLines.toList

    val answer = powerConsumption(day03)
    println(f"Day 03: $answer")
  }
}

object Aoc extends App {
  Day01.main()
  Day02.main()
  Day03.main()
}
