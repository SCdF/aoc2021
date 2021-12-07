package aoc

import scala.collection.mutable.ArrayBuffer
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
    println(f"Day 01.2: $averagedCount")
  }
}
