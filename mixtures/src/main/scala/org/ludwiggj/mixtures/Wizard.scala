package org.ludwiggj.mixtures

import akka.actor.{Actor, ActorSystem, Props}

import scala.concurrent.Await
import scala.concurrent.duration.Duration
import java.util.concurrent.TimeUnit._

class Wizard extends Actor {
  override def receive = {
    case (_: Int, result: Int) => {
      println(s"Position, Result = $result")
      context.system.terminate()
    }
  }
}

object Wizard extends App {
//  val mixtures: List[Int] = List(10, 85, 30, 60, 5)
  val mixtures: List[Int] = List(10, 85, 30, 60, 5, 75, 25, 40, 85, 70)
//  val mixtures: List[Int] = 1 to 22 map (_ * 3) toList
  val system = ActorSystem("wizard")
  val wizard = system.actorOf(Props(new Wizard))

  system.actorOf(Props(new Experiment(mixtures))).tell(-1, wizard)

  Await.ready(system.whenTerminated, Duration(1, MINUTES))
}