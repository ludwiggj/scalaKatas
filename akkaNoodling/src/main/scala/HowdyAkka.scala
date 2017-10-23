import akka.actor.{ActorSystem, Props}
import java.util.concurrent.TimeUnit._
import scala.concurrent.Await
import scala.concurrent.duration.Duration

case object Ticket

case object FullPint

case object EmptyPint

object HowdyAkka extends App {
  val system = ActorSystem("howdy-akka")

  val zed = system.actorOf(Props(new BarTender), "zed")

  val alice = system.actorOf(Props(new Person), "alice")

  zed.tell(Ticket, alice)

  Await.ready(system.whenTerminated, Duration(1, MINUTES))
}