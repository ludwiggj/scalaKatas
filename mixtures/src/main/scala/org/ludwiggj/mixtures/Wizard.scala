package org.ludwiggj.mixtures

object Wizard {
  def optimumMix(mixtures: Seq[Int]): Int = {
    val potion = mixtures.foldLeft(0, 0) {
      case ((potionMixture, smoke), mixture) => ((potionMixture + mixture) % 100, smoke + (potionMixture * mixture))
    }
    potion._2
  }
}

/*
When mixing two mixtures of colors a and b, the resulting mixture will have the color (a+b) mod 100.

Also, there will be some smoke in the process. The amount of smoke generated when mixing two mixtures of colors a and b
is a*b.

40 60 20

      acc (Acc_m, Acc_s) mix 40 => Mix Acc_m + 40 / 100, Smoke Acc_m * 40 => (40, 0)
                 (40, 0) mix 60 => Mix 40 + 60 / 100, Smoke 40 * 60       => (0, 2400)
               (0, 2400) mix 20 => Mix  0 + 20 / 100, Smoke  0 * 20       => (20, 2400 + 0)

                       a mix b  => Mix a + b / 100, Smoke a * b

   So acc should be (0, 0)
*/



