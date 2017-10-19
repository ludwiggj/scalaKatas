package org.ludwiggj.mixtures

import utest._

object MixtureTests extends TestSuite {
  val tests = Tests {
    "simple potion of two mixtures" - {
      Wizard.optimumMix(Seq(18, 19)) ==> 342
    }
  }
}