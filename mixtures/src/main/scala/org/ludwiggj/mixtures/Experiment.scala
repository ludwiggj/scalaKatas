package org.ludwiggj.mixtures

import akka.actor.{Actor, ActorLogging, ActorRef, Props}

case class Position(value: Option[Int])

case class Result(position: Position, smoke: Option[Int]) {
  def positionValue() = position.value.get
}

case class Mix(position: Position)

class Experiment(data: ExperimentData, pocketBook: ActorRef) extends Actor with ActorLogging {
  var results: List[Option[Int]] = _
  var resultCount = 0
  var clientActorRef: ActorRef = _
  var clientPosition: Position = _
  var nextExperiments: List[ExperimentData] = _

  private def mixPair(mixtures: List[Int], i: Int): (Int, Int) = {
    ((mixtures(i) + mixtures(i + 1)) % 100, mixtures(i) * mixtures(i + 1))
  }

  override def receive = {
    case (mix: Mix) => {

      val position = mix.position

      if (position.value.isEmpty) {
        log.info("Experiment has begun!")
      }

      val mixtures = data.mixtures
      val smoke = data.smoke

      clientActorRef = sender

      mixtures match {
        case Nil => sender ! Result(position, smoke = None)

        case _ :: Nil => sender ! Result(position, Some(smoke))

        case mixtureList => {
          clientPosition = position
          results = List.fill(mixtures.size - 1)(None)

//          log.info(s"START! data [$data], results [$results], size [${results.size}]")

          nextExperiments = (0 to mixtureList.size - 2).toList map { i =>
            val leadingMixtures = mixtureList take i
            val (thisMix, thisSmoke) = mixPair(mixtures, i)
            val trailingMixtures = mixtureList drop i + 2

            //            log.info(s"Exp: (mix[$mixtures], smoke: [$smoke]), index [$i] => Exp: (mix[${leadingMixtures ++ List(thisMix) ++ trailingMixtures}], smoke: [${smoke + thisSmoke}])")
            ExperimentData(leadingMixtures ++ List(thisMix) ++ trailingMixtures, smoke + thisSmoke)
          }

          nextExperiments.zipWithIndex.foreach {
            case (expData, index) => pocketBook ! (expData, index)
          }
        }
      }
    }

    case (result: Result) => {
      updateResult(result.positionValue(), result.smoke)
    }

    case (conductExperiment: Boolean, position: Int) => {
//      log.info(s"conductExperiment [$conductExperiment] position [$position]")
      if (conductExperiment) {
        context.actorOf(Props(new Experiment(nextExperiments(position), pocketBook))) ! Mix(Position(Some(position)))
      } else {
        updateResult(position, None)
      }
    }
  }

  def updateResult(position: Int, result: Option[Int]): Unit = {
    resultCount = resultCount + 1
    results = results.updated(position, result)

//    log.info(s"RESULT! data [$data], results [$results], resultCount [$resultCount]")

    if (resultCount == results.size) {
      val smokeResults = results.filter(_.isDefined)

      val minSmoke = if (smokeResults.isEmpty) None else smokeResults.min

      clientActorRef ! Result(clientPosition, minSmoke)
    }
  }
}