package parallel

import akka.actor.{ActorSystem, Props}

object FactorialActors extends App {
  val factorials = List(20, 18, 32, 28, 22, 42, 55, 48)

  val system = ActorSystem("factorial")

  val collector = system.actorOf(Props(new FactorialCollector(factorials)), "collector")
}