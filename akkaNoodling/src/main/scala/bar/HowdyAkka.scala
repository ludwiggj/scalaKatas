package bar

import java.util.concurrent.TimeUnit._

import akka.actor.{ActorSystem, Props}

import scala.concurrent.Await
import scala.concurrent.duration.Duration

case class Ticket(quantity: Int)
case class FullPint(number: Int)
case class EmptyPint(number: Int)

// See http://www.reactive.io/tips/2014/03/28/getting-started-with-actor-based-programming-using-scala-and-akka
object HowdyAkka extends App {
  val system = ActorSystem("howdy-akka")

  val zed = system.actorOf(Props(new BarTender), "zed")

  val alice = system.actorOf(Props(new Person), "alice")
  val bob = system.actorOf(Props(new Person), "bob")
  val charlie = system.actorOf(Props(new Person), "charlie")

  zed.tell(Ticket(2), alice)
  zed.tell(Ticket(3), bob)
  zed.tell(Ticket(1), charlie)

  Await.ready(system.whenTerminated, Duration(1, MINUTES))
}