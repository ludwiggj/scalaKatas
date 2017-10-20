package org.ludwiggj.mixtures

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

      "five mixtures" - {
        "least smoky for (10, 85, 30, 60, 5)" - {
          smoke = Wizard.optimumMix(Seq(10, 85, 30, 60, 5))
          smoke ==> 4625
          smoke
        }
      }
    }
  }
}