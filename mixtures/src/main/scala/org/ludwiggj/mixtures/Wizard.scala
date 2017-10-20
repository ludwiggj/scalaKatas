package org.ludwiggj.mixtures

object Wizard {

    /*

    When mixing two mixtures of colors a and b, the resulting mixture will have the color (a+b) mod 100.
    Also, there will be some smoke in the process. The amount of smoke generated when mixing two mixtures
    of colors a and b is a*b.

    Example potion of mixtures (40 60 20)

          acc (Acc_m, Acc_s) mix 40 => Mix Acc_m + 40 / 100, Smoke Acc_m * 40 => (40, 0)
                     (40, 0) mix 60 => Mix 40 + 60 / 100, Smoke 40 * 60       => (0, 2400)
                   (0, 2400) mix 20 => Mix  0 + 20 / 100, Smoke  0 * 20       => (20, 2400 + 0)

                           a mix b  => Mix a + b / 100, Smoke a * b

       So acc should be (0, 0)
    */

  val correctMixer = (x: Int, y: Int) => (x + y) % 100

  def mix(mixtures: Seq[Int], mixer: (Int, Int) => Int = correctMixer): Int = {
    val potion = mixtures.foldLeft(0, 0) {
      case ((potionMixture, smoke), mixture) => (mixer(potionMixture, mixture), smoke + (potionMixture * mixture))
    }
    potion._2
  }

  // Naive implementation that does not scale
  private def allMixes(mixtures: Seq[Int], mixer: (Int, Int) => Int = correctMixer): Iterator[Int] = {
    mixtures.permutations.map {
      mix(_, mixer)
    }
  }

  def optimumMix(mixtures: Seq[Int]): Int = {
    allMixes(mixtures).min
  }

  def mixProperties(mixtures: Seq[Int], mixer: (Int, Int) => Int): (Int, Int, Int, Int) = {
    val smokes = allMixes(mixtures, mixer).toList

    (smokes.min, smokes.max, smokes.size, smokes.distinct.size)
  }
}
