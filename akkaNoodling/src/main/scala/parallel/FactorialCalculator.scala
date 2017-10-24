package parallel

import akka.actor.Actor

import scala.annotation.tailrec

class FactorialCalculator extends Actor {
  def receive = {
    case num: Int => sender ! (num, factor(num))
  }

  private def factor(num: Int) = factorTail(num, 1)

  @tailrec private def factorTail(num: Int, acc: BigInt): BigInt = {
    (num, acc) match {
      case (0, a) => a
      case (n, a) => factorTail(n - 1, n * a)
    }
  }
}