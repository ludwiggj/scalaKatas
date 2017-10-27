package org.ludwiggj.mixtures

import akka.actor.{Actor, ActorLogging}

case object Summmary

class PocketBook extends Actor with ActorLogging {
  private var experimentCount = 0
  private var abandonedExperimentCount = 0
  private var notes: Map[List[Int], Int] = Map()

  override def receive = {
    case (data: ExperimentData, position: Int) => sender ! (continueExperiment(data), position)

    case Summmary =>
      log.info(
        s"Total experiment count [$experimentCount] = conducted [${experimentCount - abandonedExperimentCount}]" +
        s" + abandoned [$abandonedExperimentCount]"
      )
  }

  def continueExperiment(experiment: ExperimentData): Boolean = {
    experimentCount += 1
    val experimentMixtures = experiment.mixtures
    val experimentSmoke = experiment.smoke

    val continueExperiment = notes.get(experimentMixtures) match {
      case Some(smoke) =>
        val thisExperimentIsBetter = experimentSmoke < smoke
        if (thisExperimentIsBetter) {
          notes = notes + (experimentMixtures -> experimentSmoke)
        } else {
          abandonedExperimentCount += 1
          //          log.info(s"ABANDONED EXPERIMENT!! [$experiment], number [${experimentCount}], best smoke is [$smoke]")
        }
        thisExperimentIsBetter

      case _ =>
        notes = notes + (experimentMixtures -> experimentSmoke)
        true
    }

    continueExperiment
  }
}