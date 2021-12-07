package aoc

import scala.collection.mutable.ArrayBuffer
import scala.io.Source

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
