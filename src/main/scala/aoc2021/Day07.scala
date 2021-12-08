package aoc

object Day07 {
  def median(numbers: List[Int]): Int = {
    val sorted= numbers.sorted
    sorted(sorted.size / 2)
  }

  def optimalFuel(input: String): Int = {
    val positions = input.split(',').map(_.toInt).toList
    val optimalPosition = median(positions)

    positions.map(p => Math.abs(p - optimalPosition)).reduce(_+_)
  }
}
