package aoc

import scala.collection.mutable.ArrayBuffer

object Day12 {
  case class Room(
    name: String,
    big: Boolean,
    destinations: ArrayBuffer[Room] = ArrayBuffer()) {

    def this(name: String) = this(name, name.head.isUpper)
    override def toString() = f"$name: ${destinations.map(_.name)}"
  }

  def paths(input: String, allowances: Int = 0): Seq[Seq[Room]] = {
    val rooms: Seq[Room] = input
      .split('\n')
      .foldLeft(ArrayBuffer[Room]())({case (rooms, line) => line.split('-') match {
        case Array(start, end) => {
          val startRoom = rooms.find(_.name == start).orElse({
            val r = new Room(start)
            rooms += r
            Some(r)
          }).get
          val endRoom = rooms.find(_.name == end).orElse({
            val r = new Room(end)
            rooms += r
            Some(r)
          }).get

          startRoom.destinations += endRoom
          endRoom.destinations += startRoom

          rooms
        }
      }})

    def travel(path: Seq[Room], blocked: Seq[Room], allowances: Int = allowances): Seq[Seq[Room]] = {
      val room = path.last

      if (room.name == "end") return List(path)

      room.destinations
        .filterNot(_.name == "start")
        .map(dest =>
          if (dest.big) Some(travel(path :+ dest, blocked, allowances))
          else if (!blocked.contains(dest)) Some(travel(path :+ dest, blocked :+ dest, allowances))
          else if (blocked.contains(dest) && allowances > 0) Some(travel(path :+ dest, blocked :+ dest, allowances - 1))
          else None
        ).flatten.flatten
    }

    val start = rooms.find(_.name == "start").get
    travel(List(start), List(start))
  }
}
