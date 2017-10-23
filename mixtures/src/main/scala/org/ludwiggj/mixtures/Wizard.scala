package org.ludwiggj.mixtures

class Wizard(val pocketBook: PocketBook) {
  def mix(mixtures: List[Int]): Int = {
    val result = Experiment(mixtures, pocketBook).mix().getOrElse(0)
    pocketBook.summary()
    result
  }
}