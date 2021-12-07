package aoc

object Day06 {
  def simulate(fishes: List[Int]): List[Int] =
    fishes
      .map(fish => if (fish == 0) List(6, 8) else List(fish - 1))
      .flatten


  def spawnSimulation(input: String, days: Int): Int = {
    val initialFishes = input.split(',').map(_.toInt).toList

    // println(f"Initial state: ${initialFishes.size}")
    (1 to days).foldLeft(initialFishes)((fishes, day) => {
      // print(f"After $day days: ")
      val before = System.currentTimeMillis

      val next = simulate(fishes)

      // println(f"${next.size} (took ${System.currentTimeMillis - before} millis)")
      next
    }).size
  }
}
