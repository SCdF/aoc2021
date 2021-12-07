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

object Day04 {
  case class Board(var entries: List[List[Int]]) {
    def value(): Int = entries.flatten.reduce(_+_)

    def won(): Boolean = {
      (entries ++ entries.transpose).map(_.reduce(_+_)).exists(_ == 0)
    }

    def play(number: Int): Boolean = {
      entries = entries.map(_.map(x => if (x == number) 0 else x))

      won
    }

  }

  def parseBoardsFromInput(lines: Iterator[String]): List[Board] = {

    val boards: ArrayBuffer[Board] = ArrayBuffer[Board]()
    while (lines.hasNext) {
      // take out a line of whitespace
      lines.next

      // Now we have 5 lines of 5 numbers
      val boardLines = List(
        lines.next,
        lines.next, // you'd think you could do lines.take(5) to consume the next 5 lines
        lines.next, // However, this doesn't actually increment the Iterator! AFAICT you need to
        lines.next, // MANUALLY consume each line one by one. Surely this is not the way...
        lines.next,
      )

      val numbers =
        boardLines.map(
          _.split(' ')     // we should split by whitespace regex to not have to
          .filter(_ != "") // filter out empty elements
          .map(_.toInt)
          .toList
        ).toList

      boards += Board(numbers)
    }

    boards.toList
  }

  def bingo(input: String): Option[Int] = {
    val lines = input.split('\n').iterator
    val bingoNumbers = lines.next.split(',').map(Integer.parseInt(_))
    val bingoBoards = parseBoardsFromInput(lines)

    for (bn <- bingoNumbers) {
      for (board <- bingoBoards) {
        if (board.play(bn)) return Some(board.value * bn)
      }
    }

    None
  }

  def worstBingoBoard(input: String): Int = {
    val lines = input.split('\n').iterator
    val bingoNumbers = lines.next.split(',').map(Integer.parseInt(_))
    var bingoBoards = parseBoardsFromInput(lines)

    var winningScore: Int = 0
    for (bn <- bingoNumbers) {
      bingoBoards = bingoBoards.map(board => {
        if (board.play(bn)) {
          winningScore = board.value * bn
          None
        } else Some(board)
      }).flatten
    }

    winningScore
  }

  def main() {
    val day04 = Source.fromFile("./src/data/day04").mkString
    println(f"Day 04: ${bingo(day04)}")
    println(f"Day 04.2: ${worstBingoBoard(day04)}")
  }
}

object Aoc extends App {
  // Day01.main()
  // Day02.main()
  // Day03.main()
  Day04.main()
}
