package org.ludwiggj.mixtures

import akka.actor.{Actor, ActorLogging, ActorRef, Props}

class Experiment(mixtures: List[Int], smoke: Int = 0, val level: Int = 0) extends Actor with ActorLogging {
  var results: List[Int] = null
  var client: ActorRef = null
  var clientPos: Int = -1

//  log.info(s"Experiment: mixtures [$mixtures], smoke: [$smoke], level: [$level]")

  private def mixPair(mixtures: List[Int], i: Int): (Int, Int) = {
    ((mixtures(i) + mixtures(i + 1)) % 100, mixtures(i) * mixtures(i + 1))
  }

  override def receive = {
    case (position: Int) => {
      client = sender
      mixtures match {
        case Nil => sender ! (position, 0)
        case mix :: Nil => {
//                    log.info(s"Single item in list [$mix]")
          sender ! (position, smoke)
        }
        case mixtureList => {
          clientPos = position
          results = List.fill(mixtures.size - 1)(-1)

          (0 to mixtureList.size - 2).toList foreach { i =>
            val leadingMixtures = mixtureList take i
            val (thisMix, thisSmoke) = mixPair(mixtures, i)
            val trailingMixtures = mixtureList drop i + 2

//            log.info(s"Exp: (mix[$mixtures], smoke: [$smoke], lev: [$level]), index [$i] => Exp: (mix[${leadingMixtures ++ List(thisMix) ++ trailingMixtures}], smoke: [${smoke + thisSmoke}], lev: [${level + 1}])")

            context.actorOf(Props(
              new Experiment(leadingMixtures ++ List(thisMix) ++ trailingMixtures, smoke = smoke + thisSmoke, level = level + 1)
            )) ! i
          }
        }
      }
    }

    case (position: Int, result: Int) => {
      results = results.updated(position, result)
      if (!results.contains(-1)) {
        //        log.info(s">>>>>>> result: level [$level] result [${results.min}]")
        client ! (clientPos, results.min)
      }
    }
  }
}