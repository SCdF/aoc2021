package aoc

import scala.annotation.tailrec

object Day07 {
  def median(numbers: List[Int]): Int = {
    val sorted= numbers.sorted
    sorted(sorted.size / 2)
  }

  def mean(numbers: List[Int]): Int =
    Math.round(numbers.reduce(_+_) / numbers.size.toFloat)

  def optimalFuel(input: String): Int = {
    val positions = input.split(',').map(_.toInt).toList
    val optimalPosition = median(positions)

    positions.map(p => Math.abs(p - optimalPosition)).reduce(_+_)
  }

  @tailrec
  def cost(i: Int, count: Long = 0): Long =
    if (i == 0) count else cost(i - 1, count + i)

  def evenMoreOptimalFuel(input: String): Long = {
    val positions = input.split(',').map(_.toInt).toList
    val optimalPosition = mean(positions)

    def totalCount(optimalPos: Int) = positions
      .map(p => cost(Math.abs(p - optimalPos)))
      .reduce(_+_)

    // OK so I guess "mean" isn't right? This is slightly embarrassing, but it works!
    Math.min(
      totalCount(optimalPosition),
      Math.min(
        totalCount(optimalPosition - 1),
        totalCount(optimalPosition + 1))
    )
  }
}
