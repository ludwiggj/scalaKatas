package org.ludwiggj.mixtures

case class Experiment(mixtures: List[Int], smoke: Int = 0) {
  def mix(): Int = {
    println(s"+++ Experiment $this")
    mixtures match {
      case Nil => 0
      case _ :: Nil => {
        println(s"********* Result $smoke")
        smoke
      }
      case mixtureList => {
        val list = {
          (0 to mixtureList.size - 2).toList map { i =>
            val leadingMixtures = mixtureList take i
            val (thisMix, thisSmoke) = mixPair(i)
            val trailingMixtures = mixtureList drop i + 2

            println(s">>>>>> Processing [$leadingMixtures, (${mixtureList(i)} + ${mixtureList(i + 1)}), $trailingMixtures]")
            this.copy(mixtures = leadingMixtures ++ List(thisMix) ++ trailingMixtures, smoke = smoke + thisSmoke).mix()
          }
        }

        println(s"--------- Results $list")
        list.min
      }
    }
  }

  private def mixPair(i: Int): (Int, Int) = {
    ((mixtures(i) + mixtures(i + 1)) % 100, mixtures(i) * mixtures(i + 1))
  }
}

object Wizard {
  def mix(mixtures: List[Int]): Int = {
    Experiment(mixtures).mix()
  }
}