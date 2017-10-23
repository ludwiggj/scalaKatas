import akka.actor.{Actor, ActorLogging}

class BarTender extends Actor with ActorLogging {
  def receive = {
    case Ticket =>
      log.info("1 pint coming right up")

      Thread.sleep(1000)

      log.info("Your pint is ready, here you go")

      sender ! FullPint

    case EmptyPint =>
      log.info("I think you're done for the day")

      context.system.terminate()
  }
}