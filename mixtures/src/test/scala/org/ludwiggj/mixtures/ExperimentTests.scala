package org.ludwiggj.mixtures

import utest._

object ExperimentTests extends TestSuite {
  val tests = Tests {

    "experiment tests" - {

      val pocketBook = new PocketBook()

      val experiment1 = Experiment(List(1, 2), pocketBook, 0)
      val experiment2 = Experiment(List(), pocketBook, 0)
      val experiment3 = Experiment(List(20, 40, 60), pocketBook, 800)
      val experiments = List(experiment1, experiment2, experiment3)


      "contains matches" - {
        experiments.contains(experiment1) ==> true
        experiments.contains(experiment2) ==> true
        experiments.contains(experiment3) ==> true
      }

      "does not contain mismatches" - {
        experiments.contains(Experiment(List(2,4,6), pocketBook, 8)) ==> false
      }
    }
  }
}