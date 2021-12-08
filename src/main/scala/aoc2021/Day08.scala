package aoc

object Day08 {
  def easyCount(linesOfInput: List[String]): Int = {
    val uniqueSizes = List(2,4,3,7)
    linesOfInput.map(
      _.split('|')(1)
       .split(' ')
       .filter(word => uniqueSizes.contains(word.size))
       .size
    ).reduce(_+_)
  }
}
