package org.ludwiggj.mixtures

import akka.actor.{Actor, ActorLogging, ActorRef, Props}

case class Position(value: Option[Int])

case class Result(position: Position, smoke: Int) {
  def positionValue() = position.value.get
}

case class Mix(position: Position)

class Experiment(mixtures: List[Int], smoke: Int = 0) extends Actor with ActorLogging {
  var results: List[Int] = _
  var clientActorRef: ActorRef = _
  var clientPosition: Position = _

//  log.info(s"Experiment: mixtures [$mixtures], smoke: [$smoke]")

  private def mixPair(mixtures: List[Int], i: Int): (Int, Int) = {
    ((mixtures(i) + mixtures(i + 1)) % 100, mixtures(i) * mixtures(i + 1))
  }

  override def receive = {
    case (mix: Mix) => {
//      log.info(s"Received [$position]")

      val position = mix.position

      clientActorRef = sender
      mixtures match {
        case Nil => sender ! Result(position, smoke = 0)
        case mix :: Nil => {
//                    log.info(s"Single item in list [$mix]")
          sender ! Result(position, smoke)
        }
        case mixtureList => {
          clientPosition = position
          results = List.fill(mixtures.size - 1)(-1)

          (0 to mixtureList.size - 2).toList foreach { i =>
            val leadingMixtures = mixtureList take i
            val (thisMix, thisSmoke) = mixPair(mixtures, i)
            val trailingMixtures = mixtureList drop i + 2

//            log.info(s"Exp: (mix[$mixtures], smoke: [$smoke]), index [$i] => Exp: (mix[${leadingMixtures ++ List(thisMix) ++ trailingMixtures}], smoke: [${smoke + thisSmoke}])")

            context.actorOf(Props(
              new Experiment(leadingMixtures ++ List(thisMix) ++ trailingMixtures, smoke = smoke + thisSmoke)
            )) ! Mix(Position(Some(i)))
          }
        }
      }
    }

    case (result: Result) => {
      results = results.updated(result.positionValue(), result.smoke)
      if (!results.contains(-1)) {
        clientActorRef ! Result(clientPosition, results.min)
      }
    }
  }
}