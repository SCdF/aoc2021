package aoc

import org.scalatest.funsuite._
import scala.io.Source

class Day12dSpec extends AnyFunSuite {
  val tests = List(
    ("""start-A
start-b
A-c
A-b
b-d
A-end
b-end""", 10, 36),
    ("""dc-end
HN-start
start-kj
dc-start
dc-HN
LN-dc
HN-end
kj-sa
kj-HN
kj-dc""", 19, 103),
    ("""fs-end
he-DX
fs-he
start-DX
pj-DX
end-zg
zg-sl
zg-pj
pj-he
RW-he
fs-DX
pj-RW
zg-RW
start-pj
he-WI
zg-he
pj-fs
start-RW""", 226, 3509))

  test("part one works") {
    tests.foreach({
      case (input, p1, p2) => {
        assert(Day12.paths(input).size == p1)
        assert(Day12.paths(input, 1).size == p2)
      }
    })
  }
}
