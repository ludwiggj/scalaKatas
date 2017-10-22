package org.ludwiggj.mixtures

import utest._

object WizardTests extends TestSuite {
  val tests = Tests {

    "smoke tests" - {
      var smoke = 0


      "smoke for potion of no mixtures" - {
        smoke = Wizard.mix(List())
        smoke ==> 0
        smoke
      }

      "smoke for potion of one mixture" - {
        smoke = Wizard.mix(List(18))
        smoke ==> 0
        smoke
      }

      "smoke for potion of two mixtures" - {
        smoke = Wizard.mix(List(18, 19))
        smoke ==> 342
        smoke
      }

      "three mixtures" - {

        "smoke for (40, 60, 20)" - {
          smoke = Wizard.mix(List(40, 60, 20))
          smoke ==> 2400
          smoke
        }

        "smoke for (60, 20, 40)" - {
          smoke = Wizard.mix(List(60, 20, 40))
          smoke ==> 4400
          smoke
        }
      }

      "five mixtures" - {
        "smoke for (10, 85, 30, 60, 5)" - {
          smoke = Wizard.mix(List(10, 85, 30, 60, 5))
          smoke ==> 4625
          smoke
        }
      }

      "ten mixtures" - {
        "smoke for (10, 85, 30, 60, 5, 75, 25, 40, 85, 70)" - {
          smoke = Wizard.mix(List(10, 85, 30, 60, 5, 75, 25, 40, 85, 70))
          smoke ==> 10200
          smoke
        }
      }

//      "fifteen mixtures" - {
//        "smoke for (10, 85, 30, 60, 5, 75, 25, 40, 85, 70, 15, 25, 35, 45, 55)" - {
//          smoke = Wizard.mix(List(10, 85, 30, 60, 5, 75, 25, 40, 85, 70, 15, 25, 35, 45, 55))
//          smoke ==> 10200
//          smoke
//        }
//      }
    }
  }
}