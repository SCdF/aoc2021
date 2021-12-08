package aoc

object Day06 {
  def spawnSimulation(input: String, daysToSimulate: Int): Long = {
    val initialFishes = input.split(',').map(_.toInt).toList

    val dayBlocks = Array.fill[Long](daysToSimulate + 1)(0)
    dayBlocks(0) = initialFishes.size // at day "zero" we have this many fish

    // Convert our linear fish counts into spawn days in our day blocks
    initialFishes.foreach(fish => {
      var spawnHop = fish + 1 // + 1 because spawning happens on the "zeroth" day
      while (spawnHop <= daysToSimulate) {
        dayBlocks(spawnHop) += 1 // a fish will spawn on this day

        spawnHop += 7
      }
    })

    // println(f"Initial Fishes Setup (${dayBlocks.reduce(_+_)})")
    // println(dayBlocks.zipWithIndex.toList)

    // Starting at day 0, walk forwards until you find a day you have spawns for
    var day = 1
    while (day <= daysToSimulate) {
      val fishes = dayBlocks(day)
      if (fishes > 0) {
        // First spawn is 8 days in the future
        var spawnHop = day + 8 + 1 // + 1 because spawning happens on the "zeroth" day
        while (spawnHop <= daysToSimulate) {
          dayBlocks(spawnHop) += fishes // this many fishes will spawn on this day

          spawnHop += 7
        }
      }

      // println(f"After Processing Day $day (${dayBlocks.reduce(_+_)})")
      // println(dayBlocks.zipWithIndex.toList)
      day += 1
    }

    dayBlocks.reduce(_+_)
  }
}

object Day06Slow {
  def simulate(fishes: List[Int]): List[Int] =
    fishes
      .map(fish =>
        if (fish == 0)
          List(6, 8)
        else
          List(fish - 1))
      .flatten


  def spawnSimulation(input: String, days: Int): Int = {
    val initialFishes = input.split(',').map(_.toInt).toList

    // println(f"\nInitial state: ${initialFishes.size} for $days days")
    // println(initialFishes)
    (1 to days).foldLeft(initialFishes)((fishes, day) => {
      // print(f"After $day days: ")
      val before = System.currentTimeMillis

      val next = simulate(fishes)

      // println(f"${next.size} (took ${System.currentTimeMillis - before} millis)")
      // println(next)
      next
    }).size
  }
}
