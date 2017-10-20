package org.ludwiggj.mixtures

import org.ludwiggj.mixtures.Wizard.correctMixer
import utest._

object WizardTests extends TestSuite {
  val tests = Tests {

    "smoke tests" - {
      var smoke = 0

      "smoke for potion of two mixtures" - {
        smoke = Wizard.mix(Seq(18, 19))
        smoke ==> 342
        smoke
      }

      "three mixtures" - {

        "smoke for (40, 60, 20)" - {
          smoke = Wizard.mix(Seq(40, 60, 20))
          smoke ==> 2400
          smoke
        }

        "smoke for (60, 20, 40)" - {
          smoke = Wizard.mix(Seq(60, 20, 40))
          smoke ==> 4400
          smoke
        }

        "least smoky for (20, 40, 60)" - {
          smoke = Wizard.optimumMix(Seq(20, 40, 60))
          smoke ==> 2400
          smoke
        }
      }
    }

    "exponential tests" - {
      //    // 2 secs
      //    "least smoky potion of ten mixtures (0 to 9)" - {
      //      Wizard.optimumMix(0 to 9 toList) ==> 870
      //    }
      //
      //    // 26 secs
      //    "least smoky potion of eleven mixtures (0 to 10)" - {
      //      Wizard.optimumMix(0 to 10 toList) ==> 1320
      //    }
      //
      //    // 345 secs
      //    "least smoky potion of twelve mixtures (0 to 11)" - {
      //      Wizard.optimumMix(0 to 11 toList) ==> 1925
      //    }
      //
      //    // Too long!
      //    "least smoky potion of thirteen mixtures (0 to 12)" - {
      //      Wizard.optimumMix(0 to 12 toList) ==> 870
      //    }
    }

    "number of mixtures properties" - {
      def checkProperties(mixtures: Seq[Int], mixer: (Int, Int) => Int = correctMixer) = {
        val (min, max, combinations, unique) = Wizard.mixProperties(mixtures, mixer)
        println(s"min [$min] max [$max] combinations [$combinations] ... of which unique [$unique]")
        min ==> max
        (min, max, unique)
      }

      "using specified mixing algorithm" - {

        "four mixtures (1 to 4)" - {
          checkProperties(1 to 4 toList)
        }

        "four mixtures (10, 20, 30, 40)" - {
          checkProperties(10 to 40 by 10 toList)
        }

        "five mixtures (1 to 5)" - {
          checkProperties(1 to 5 toList)
        }

        "nine mixtures (2 to 10)" - {
          checkProperties(2 to 10 toList)
        }

        "ten mixtures (0 to 9)" - {
          checkProperties(0 to 9 toList)
        }

        "ten mixtures (0, 10, 20, 30 ... 90)" - {
          checkProperties(0 to 90 by 10 toList)
        }

        "ten mixtures (25, 28, 31 ... 52)" - {
          checkProperties(25 to 52 by 3 toList)
        }
      }

      "using mixing algorithm without % 100" - {

        val newMixingAlgorithm = (x: Int, y: Int) => x + y

        "four mixtures (1 to 4)" - {
          checkProperties(1 to 4 toList, newMixingAlgorithm)
        }

        "four mixtures (10, 20, 30, 40)" - {
          checkProperties(10 to 40 by 10 toList)
        }

        "five mixtures (1 to 5)" - {
          checkProperties(1 to 5 toList, newMixingAlgorithm)
        }

        "nine mixtures (2 to 10)" - {
          checkProperties(2 to 10 toList, newMixingAlgorithm)
        }

        "ten mixtures (0 to 9)" - {
          checkProperties(0 to 9 toList, newMixingAlgorithm)
        }

        "ten mixtures (0, 10, 20, 30 ... 90)" - {
          checkProperties(0 to 90 by 10 toList, newMixingAlgorithm)
        }

        "ten mixtures (25, 28, 31 ... 52)" - {
          checkProperties(25 to 52 by 3 toList, newMixingAlgorithm)
        }
      }
    }
  }
}
