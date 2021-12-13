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

  def normalizedKey(key: String): Seq[Char] = key.toList.sorted
  def extractKey(keys: Seq[String], size: Int): Seq[Char] = normalizedKey(keys.find(_.size == size).get)

  def deduceKeys(keys: Seq[String]): List[Seq[Char]] = {
    // 1, 4, 7 and 8 have unique lengths
    val one = extractKey(keys, 2)
    val four = extractKey(keys, 4)
    val seven = extractKey(keys, 3)
    val eight = extractKey(keys, 7)

    val zeroOrSixOrNine = keys.filter(_.size == 6).map(normalizedKey)
    val twoOrThreeOrFive = keys.filter(_.size == 5).map(normalizedKey)

    // 3 covers 1, while 2 and 5 both miss one pip
    val three = twoOrThreeOrFive.find(_.intersect(one).size == 2).get

    // 9 covers 4, while 0 and 6 both miss one pip
    val nine = zeroOrSixOrNine.find(_.intersect(four).size == 4).get

    // 0 covers 7 and is not 9
    val zero = zeroOrSixOrNine.filter(_ != nine).find(_.intersect(seven).size == 3).get

    // 6 is not 0 or 9
    val six = zeroOrSixOrNine.diff(List(zero, nine))(0)

    // 5 is completely covered by 6
    val five = twoOrThreeOrFive.find(_.intersect(six).size == 5).get

    // 2 is not 5 or 3
    val two = twoOrThreeOrFive.diff(List(five, three))(0)

    List(zero, one, two, three, four, five, six, seven, eight, nine)
  }

  def digits(inputLine: String): Int = {
    val keys :: digits :: _ = inputLine.split('|').map(_.split(' ').filter(_.size > 0)).toList

    val index = deduceKeys(keys)

    digits.map(normalizedKey).map(d => index.indexOf(d)).mkString.toInt
  }
}
