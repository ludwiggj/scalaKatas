import akka.actor.{Actor, ActorLogging}

class Person extends Actor with ActorLogging {
  def receive = {
    case FullPint =>
      log.info("I'll make short work of this")

      Thread.sleep(1000)

      log.info("I'm ready for the next")

      sender ! EmptyPint
  }
}