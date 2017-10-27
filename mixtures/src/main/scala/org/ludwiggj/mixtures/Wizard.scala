package org.ludwiggj.mixtures

import akka.actor.{Actor, ActorSystem, Props}

import scala.concurrent.Await
import scala.concurrent.duration.Duration
import java.util.concurrent.TimeUnit._

class Wizard extends Actor {
  override def receive = {
    case (result: Result) => {
      println(s"Result = ${result.smoke}")
      context.system.terminate()
    }
  }
}

object Wizard extends App {
//  val mixtures: List[Int] = List(1, 2, 3)
//  val mixtures: List[Int] = List(10, 85, 30, 60, 5)
  val mixtures: List[Int] = List(10, 85, 30, 60, 5, 75, 25, 40, 85, 70)
//  val mixtures: List[Int] = 1 to 22 map (_ * 3) toList
  val system = ActorSystem("wizard")
  val wizard = system.actorOf(Props(new Wizard))

  system.actorOf(Props(new Experiment(mixtures))).tell(Mix(Position(None)), wizard)

  Await.ready(system.whenTerminated, Duration(1, MINUTES))
}