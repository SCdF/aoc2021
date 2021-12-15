package aoc

object Helpers {
  implicit class SeqIntMaths(x: Seq[Int]) {
    def median(): Int = {
      val sorted= x.sorted
      sorted(sorted.size / 2)
    }

    def mean(): Int =
      Math.round(x.reduce(_+_) / x.size.toFloat)
  }
  implicit class SeqLongMaths(x: Seq[Long]) {
    def median(): Long = {
      val sorted= x.sorted
      sorted(sorted.size / 2)
    }

    def mean(): Long =
      Math.round(x.reduce(_+_) / x.size.toFloat)
  }
}
