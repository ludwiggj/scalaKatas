package org.ludwiggj.mixtures

import akka.actor.{Actor, ActorLogging, ActorRef, ActorSystem, Props}

import scala.concurrent.Await
import scala.concurrent.duration.Duration
import java.util.concurrent.TimeUnit._

class Wizard(val pocketBook: ActorRef) extends Actor with ActorLogging {
  println(pocketBook)
  override def receive = {
    case (result: Result) => {
      log.info(s"Result = ${result.smoke}")

      pocketBook ! Summmary

      context.system.terminate()
    }
  }
}

object Wizard extends App {
//  val mixtures: List[Int] = List(1, 2, 3)
//  val mixtures: List[Int] = List(10, 85, 30, 60, 5)
//  val mixtures: List[Int] = List(10, 85, 30, 60, 5, 75, 25, 40, 85, 70)
  val mixtures: List[Int] = 1 to 20 map (_ * 3) toList
  val system = ActorSystem("laboratory")

  val pocketBook = system.actorOf(Props(new PocketBook))

  val harry = system.actorOf(Props(new Wizard(pocketBook)))

  system.actorOf(Props(new Experiment(ExperimentData(mixtures), pocketBook))).tell(Mix(Position(None)), harry)

  Await.ready(system.whenTerminated, Duration(3, MINUTES))
}