package org.ludwiggj.mixtures

import utest._

object MixtureTests extends TestSuite {
  val tests = Tests {
    "potion of two mixtures" - {
      Wizard.mix(Seq(18, 19)) ==> 342
    }

    "potion of three mixtures (40, 60, 20)" - {
      Wizard.mix(Seq(40, 60, 20)) ==> 2400
    }

    "potion of three mixtures (60, 20, 40)" - {
      Wizard.mix(Seq(60, 20, 40)) ==> 4400
    }
  }
}