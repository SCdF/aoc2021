package aoc

import scala.annotation.tailrec
import Helpers._

object Day07 {
  def optimalFuel(input: String): Int = {
    val positions = input.split(',').map(_.toInt).toList
    val optimalPosition = positions.median

    positions.map(p => Math.abs(p - optimalPosition)).reduce(_+_)
  }

  @tailrec
  def cost(i: Int, count: Long = 0): Long =
    if (i == 0) count else cost(i - 1, count + i)

  def evenMoreOptimalFuel(input: String): Long = {
    val positions = input.split(',').map(_.toInt).toList
    val optimalPosition = positions.mean

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
