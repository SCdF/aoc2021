package aoc

import scala.collection.mutable.Stack
import aoc.Helpers._

object Day10 {
  val OPENERS = List('(', '{', '[', '<')

  def lineError(line: String) = {
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
  }

  def errorScore(input: String): Long =
    input.split('\n')
    .map(lineError)
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

  def completionScore(input: String): Long =
    input.split('\n')
    .filter(lineError(_) == None)
    .map(line => {
      val stack = Stack[Char]()
      line.foreach(_ match {
        case x if OPENERS.contains(x) => stack.push(x)
        case x => stack.pop() // we're being in efficient reprocessing this anyway
                              // and at this point we already know the stack is valid
      })

      stack.foldLeft(0L)({
        case (score: Long, '(') => (score * 5) + 1
        case (score: Long, '[') => (score * 5) + 2
        case (score: Long, '{') => (score * 5) + 3
        case (score: Long, '<') => (score * 5) + 4
        case _ => ???
      })
    })
    .sorted
    .toList // in theory Array should implicit convert to ArraySeq, which is a Seq.
    .median // <-- which would let this implicit work

}
