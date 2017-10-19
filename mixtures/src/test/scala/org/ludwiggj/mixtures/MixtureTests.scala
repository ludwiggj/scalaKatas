package org.ludwiggj.mixtures

import utest._

object MixtureTests extends TestSuite {
  val tests = Tests {
    "simple potion of two mixtures" - {
      Wizard.mix(Seq(18, 19)) ==> 342
    }
  }
}