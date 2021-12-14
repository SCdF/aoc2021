package aoc

import scala.collection.mutable.ArrayBuffer

import scala.annotation.tailrec

object Day09 {

  def parseInput(input: String) = {
    input.split('\n')
    .map(_.toList.map(_.asDigit).toArray)
    .toArray
  }

  case class Point(x: Int, y: Int)
  case class Heights(heights: Array[Array[Int]]) {
    val MAX_Y = heights.size - 1
    val MAX_X = heights(0).size - 1

    def apply(p: Point) = heights(p.y)(p.x)

    def consume(p: Point): Option[Int] = {
      val value = heights(p.y)(p.x)

      if (value == -1 || value == 9) return None

      heights(p.y)(p.x) = -1

      Some(value)
    }

    def neighbors(point: Point, diagonals: Boolean = true): Seq[Point] = {
      var neighbors = ArrayBuffer[Point]()

      val x = point.x
      val y = point.y

      // Top
      if (y > 0 && x > 0 && diagonals)         neighbors += Point(x-1, y-1)
      if (y > 0)                               neighbors += Point(x, y-1)
      if (y > 0 && x < MAX_X && diagonals)     neighbors += Point(x+1, y-1)

      // Middle
      if (         x > 0)                      neighbors += Point(x-1, y)
      // middle ignored here!
      if (         x < MAX_X)                  neighbors += Point(x+1, y)

      // Bottom
      if (y < MAX_Y && x > 0 && diagonals)     neighbors += Point(x-1, y+1)
      if (y < MAX_Y)                           neighbors += Point(x, y+1)
      if (y < MAX_Y && x < MAX_X && diagonals) neighbors += Point(x+1, y+1)

      neighbors
    }
  }

  def riskLevel(input: String): Int = {
    val heights = Heights(parseInput(input))

    (for {
      y <- (0 to heights.MAX_Y)
      x <- (0 to heights.MAX_X)
    } yield (y, x)).map({case (y, x) => {
      val point = heights.heights(y)(x)
      if (heights.neighbors(Point(x, y)).exists(heights(_) <= point)) None else Some(point)
    }}).flatten.reduce(_+_+1)+1 // don't judge me I'm tired
  }

  def basins(input: String): Int = {
    val heights = Heights(parseInput(input))

    // @tailrec
    def walk(point: Point): Int = heights.consume(point) match {
      case Some(_) => heights.neighbors(point, false).map(walk).sum + 1
      case None => 0
    }

    def nextBasinPoint(): Option[Point] = (for {
      y <- (0 to heights.MAX_Y)
      x <- (0 to heights.MAX_X)
    } yield Point(x, y)).find(p => {
      val value = heights(p)
      value != -1 && value != 9
    })

    var basins = ArrayBuffer[Int]()
    var point = nextBasinPoint()

    while (point != None) {
      basins += walk(point.get)
      point = nextBasinPoint()
    }

    basins.sorted.takeRight(3).reduce(_*_)
  }
}
