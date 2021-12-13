package aoc

import scala.collection.mutable.ArrayBuffer

object Day09 {
  def riskLevel(input: String): Int = {
    val heights = input.split('\n').map(_.toList.map(_.asDigit).toList).toList

    val MAX_Y = heights.size - 1
    val MAX_X = heights(0).size - 1

    (for {
      y <- (0 to MAX_Y)
      x <- (0 to MAX_X)
    } yield (y, x)).map({case (y, x) => {
      val point = heights(y)(x)

      var comparators = ArrayBuffer[Int]()
      // Top
      if (y > 0 && x > 0)         comparators += heights(y-1)(x-1)
      if (y > 0)                  comparators += heights(y-1)(x)
      if (y > 0 && x < MAX_X)     comparators += heights(y-1)(x+1)

      // Middle
      if (         x > 0)         comparators += heights(y)(x-1)
      // middle ignored here!
      if (         x < MAX_X)     comparators += heights(y)(x+1)

      // Bottom
      if (y < MAX_Y && x > 0)     comparators += heights(y+1)(x-1)
      if (y < MAX_Y)              comparators += heights(y+1)(x)
      if (y < MAX_Y && x < MAX_X) comparators += heights(y+1)(x+1)

      if (comparators.exists(_ <= point)) None else Some(point)
    }}).flatten.reduce(_+_+1)+1 // don't judge me I'm tired
  }
}
