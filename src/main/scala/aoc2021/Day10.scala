package aoc

import scala.collection.mutable.Stack

object Day10 {
  val OPENERS = List('(', '{', '[', '<')

  def errorScore(input: String): Double =
    input.split('\n')
    .map(line => {
      val stack = Stack[Char]()

      line.find(_ match {
        case x if OPENERS.contains(x) => {
          stack.push(x)
          false
        }
        case x => {
          val popped = stack.pop()

          (x == ')' && popped != '(') ||
          (x == '}' && popped != '{') ||
          (x == ']' && popped != '[') ||
          (x == '>' && popped != '<')
        }
      })
    })
    .flatten
    .groupBy(identity)
    .map({case (char, array) => (char, array.size)})
    .map(_ match {
      case (')',count) => 3 * count
      case (']',count) => 57 * count
      case ('}',count) => 1197 * count
      case ('>',count) => 25137 * count
    })
    .sum
}
