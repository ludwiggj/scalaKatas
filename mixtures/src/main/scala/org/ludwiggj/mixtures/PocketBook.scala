//package org.ludwiggj.mixtures
//
//class PocketBook() {
//  private var experimentCount = 0
//  private var abandonedExperimentCount = 0
//  private var notes: Map[List[Int], Int] = Map()
//
//  def continueExperiment(experiment: Experiment): Boolean = {
//    experimentCount += 1
//    val experimentMixtures = experiment.mixtures
//    val experimentSmoke = experiment.smoke
//
//    val continueExperiment = notes.get(experimentMixtures) match {
//      case Some(smoke) =>
//        val thisExperimentIsBetter = experimentSmoke < smoke
//        if (thisExperimentIsBetter) {
//          notes = notes + (experimentMixtures -> experimentSmoke)
//        } else {
//          abandonedExperimentCount += 1
////          println(s"+_+_+_+_ Experiment [$experiment], number [${experimentCount}] abandoned. Best smoke is [$smoke]")
//        }
//        thisExperimentIsBetter
//
//      case _ =>
//        notes = notes + (experimentMixtures -> experimentSmoke)
//        true
//    }
//
//    continueExperiment
//  }
//
//  def summary(): Unit = {
//    println(s"Total experiment count [$experimentCount] = conducted [${experimentCount - abandonedExperimentCount}] + abandoned [$abandonedExperimentCount]")
//  }
//}