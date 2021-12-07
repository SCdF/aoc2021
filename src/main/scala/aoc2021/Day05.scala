package aoc

object Day05 {
  case class Point(x: Int, y: Int)
  object DangerLine {
    def apply(x1: Int, y1: Int, x2: Int, y2: Int): DangerLine = {
      if (x1 > x2 || y1 > y2) new DangerLine(Point(x2, y2), Point(x1, y1))
      else new DangerLine(Point(x1, y1), Point(x2, y2))
    }
  }
  case class DangerLine(start: Point, end: Point) {
    def straight(): Boolean = start.x == end.x || start.y == end.y
    def locations(): List[Point] = {
      val xs = start.x to end.x by (if (end.x < start.x) -1 else 1)
      val ys = start.y to end.y by (if (end.y < start.y) -1 else 1)
      xs
        .zipAll(ys, xs.last, ys.last)
        .map(xy => Point(xy._1, xy._2))
        .toList
    }
  }
  def dangerCount(lines: List[String], considerDiagonals: Boolean = false): Int = {
    val line = raw"(\d+),(\d+) -> (\d+),(\d+)".r

    lines
      .map(_ match {
        case line(x1, y1, x2, y2) => DangerLine(x1.toInt, y1.toInt, x2.toInt, y2.toInt)
      })
      .filter(considerDiagonals || _.straight)
      .map(_.locations)
      .flatten
      .groupBy(identity)
      .filter(_._2.size > 1)
      .size
  }
}
