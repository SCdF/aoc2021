package aoc

import scala.collection.mutable.ArrayBuffer
import scala.io.Source

object Day03 {
  def inputAsInts(input: List[String]): List[List[Int]] =
    input                     // ["010", "110", "111"]
      .map(_.map(_.asDigit).toList)  // [[0,1,0], [1,1,0], [1,1,1]]
      .toList

  // TODO: do I need defaultHigh? I don't think this logic got used
  def commonBitmask(input: List[List[Int]], defaultHigh: Boolean = false): List[Int] = {
    val addedRows = input
      .transpose              // [[0,1,1], [1,1,1], [0,0,1]]
      .map(_.reduce(_+_))     // [2,3,1]

    val cutoff = input.size / 2.0

    addedRows
      .map(x => if (x > cutoff || (x == cutoff && defaultHigh)) 1 else 0)
  }

  // if only scala had unsigned ints!
  // --> val epilon = ~gamma <--
  // instead we're going to do something ugly
  private def horridBinaryNot(binaryString: String): String =
    binaryString.map(_ match {
      case '0' => '1'
      case '1' => '0'
    }).mkString

  def powerConsumption(input: List[String]): Int = {
    val gammaString = commonBitmask(inputAsInts(input)).mkString

    val gamma = Integer.parseInt(gammaString, 2)
    val epsilon = Integer.parseInt(horridBinaryNot(gammaString), 2)

    gamma * epsilon
  }

  def lifeSupportRating(input: List[String]): Int = {
    case class ListFilter(high: List[List[Int]], low: List[List[Int]]) {
      def filterByColumn(idx: Int, column: List[Int]): ListFilter = {
        val highCutoff = high.size / 2.0
        val lowCutoff = low.size / 2.0

        val highCount = high.map(_(idx)).reduce(_+_)
        val lowCount = low.map(_(idx)).reduce(_+_)

        val highPass = if (highCount >= highCutoff) 1 else 0
        val lowPass = if (lowCount >= lowCutoff) 0 else 1

        val newHigh = if (high.size == 1) high else high.filter(_(idx) == highPass)
        val newLow = if (low.size == 1) low else low.filter(_(idx) == lowPass)

        // println(f"Filter round for $idx")
        // println(f"=== Highpass, ratio is $highCount for $highCutoff, == $highPass")
        // println(newHigh)
        // println(f"=== Lowpass, ratio is $lowCount for $lowCutoff, == $lowPass")
        // println(newLow)

        ListFilter(newHigh, newLow)
      }
    }

    val inputAsIntArray = inputAsInts(input)
    val common = commonBitmask(inputAsIntArray)

    var filter = ListFilter(inputAsIntArray, inputAsIntArray)

    filter = inputAsIntArray
      .transpose
      .zipWithIndex
      .foldLeft(filter)((filtered: ListFilter, column: (List[Int], Int)) => {
        val idx = column._2
        val columnValues = column._1

        // TODO: workout had a short circuit this. If both high and low are found, we shouldn't keep going
        filtered.filterByColumn(idx, columnValues)
      })

    // println("Oxy", filter.high)
    // println("Co2", filter.low)
    val oxyGen = Integer.parseInt(filter.high(0).mkString, 2)
    val coScrub = Integer.parseInt(filter.low(0).mkString, 2)

    oxyGen * coScrub
  }

  def main() {
    val day03 = Source.fromFile("./src/data/day03")
      .getLines.toList

    println(f"Day 03: ${powerConsumption(day03)}")
    println(f"Day 03.2: ${lifeSupportRating(day03)}")
  }
}
