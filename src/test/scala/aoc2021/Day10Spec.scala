package aoc

import org.scalatest.funsuite._
import scala.io.Source

class Day10Spec extends AnyFunSuite {
  val testInput = """[({(<(())[]>[[{[]{<()<>>
[(()[<>])]({[<{<<[]>>(
{([(<{}[<>[]}>{[]{[(<()>
(((({<>}<{<{<>}{[]{[]{}
[[<[([]))<([[{}[[()]]]
[{[{({}]{}}([{[{{{}}([]
{<[[]]>}<{[{[{[]{()[[[]
[<(<(<(<{}))><([]([]()
<{([([[(<>()){}]>(<<{{
<{([{{}}[<[[[<>{}]]]>[]]"""

  test("part one works") {
    assert(Day10.errorScore(testInput) == 26397)
  }
  test("completion score works") {
    assert(Day10.completionScore(testInput) == 288957)
  }
}
