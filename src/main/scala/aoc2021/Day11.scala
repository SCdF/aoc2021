package aoc

import scala.collection.mutable.ArrayBuffer
import scala.annotation.tailrec

object Day11 {
  def parseInput(input: String) = {
    input.split('\n')
    .map(_.toList.map(_.asDigit).toArray)
    .toArray
  }

  case class Point(x: Int, y: Int)
  case class Grid(grid: Array[Array[Int]]) {
    val MAX_Y = grid.size - 1
    val MAX_X = grid(0).size - 1

    def apply(p: Point) = grid(p.y)(p.x)
    def update(p: Point, value: Int) = grid(p.y)(p.x) = value

    def points() = (0 to MAX_Y).map(y => (0 to MAX_X).map(x => Point(x,y))).flatten

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

  // @tailrec
  def flashWalk(grid: Grid)(point: Point): Int = grid(point) match {
    case x if x > 9 => {
      // cheap way to mark it's flashed once
      grid(point) = -9999

      1 + grid.neighbors(point).map(p => {
        grid(p) += 1
        flashWalk(grid)(p)
      }).sum
    }
    case _ => 0
  }

  def runStep(grid: Grid)(step: Int): Int = {
    // first increment everything once
    grid.points.foreach(point => grid(point) += 1)

    // then recursively propagate flashes
    val flashes = grid.points.map(flashWalk(grid)).sum

    // then set flashed cells to zero
    grid.points.foreach(point => grid(point) = Math.max(grid(point), 0))

    // println(f":: Step $step")
    // println(grid.grid.map(_.mkString + '\n').mkString)

    flashes
  }

  def flashCount(input: String, steps: Int): Int = {
    val grid = Grid(parseInput(input))

    (1 to steps).map(runStep(grid)).sum
  }

  def stepsUntilSynchronization(input: String): Int = {
    val grid = Grid(parseInput(input))

    var steps = 0
    while (grid.grid.flatten.sum != 0) {
      steps += 1
      runStep(grid)(steps)
    }
    steps
  }
}
