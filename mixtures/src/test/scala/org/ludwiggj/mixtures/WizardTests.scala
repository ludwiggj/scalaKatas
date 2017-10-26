package org.ludwiggj.mixtures

import utest._
import scala.language.postfixOps

object WizardTests extends TestSuite {
  var wizard: Wizard = null

  override def utestBeforeEach(path: Seq[String]): Unit = {
    wizard = new Wizard(new PocketBook())
  }

  val tests = Tests {


    "smoke tests" - {
      var smoke = 0


      "smoke for potion of no mixtures" - {
        smoke = wizard.mix(List())
        smoke ==> 0
        smoke
      }

      "smoke for potion of one mixture" - {
        smoke = wizard.mix(List(18))
        smoke ==> 0
        smoke
      }

      "smoke for potion of two mixtures" - {
        smoke = wizard.mix(List(18, 19))
        smoke ==> 342
        smoke
      }

      "three mixtures" - {

        "smoke for (40, 60, 20)" - {
          smoke = wizard.mix(List(40, 60, 20))
          smoke ==> 2400
          smoke
        }

        "smoke for (60, 20, 40)" - {
          smoke = wizard.mix(List(60, 20, 40))
          smoke ==> 4400
          smoke
        }
      }

      "five mixtures" - {
        "smoke for (10, 85, 30, 60, 5)" - {
          smoke = wizard.mix(List(10, 85, 30, 60, 5))
          smoke ==> 4625
          smoke
        }
      }

      "ten mixtures" - {
        "smoke for (10, 85, 30, 60, 5, 75, 25, 40, 85, 70)" - {
          smoke = wizard.mix(List(10, 85, 30, 60, 5, 75, 25, 40, 85, 70))
          smoke ==> 10200
          smoke
        }
      }

      "fifteen mixtures" - {
        "smoke for (10, 85, 30, 60, 5, 75, 25, 40, 85, 70, 15, 25, 35, 45, 55)" - {
          smoke = wizard.mix(List(10, 85, 30, 60, 5, 75, 25, 40, 85, 70, 15, 25, 35, 45, 55))
          smoke ==> 14825
          smoke
        }
      }

      "twenty mixtures" - {
        "smoke for twenty mixtures" - {
          smoke = wizard.mix(1 to 20 map (_ * 3) toList)
          smoke ==> 20235
          smoke
        }
      }

      "twenty-two mixtures" - {
        "smoke for twenty-two mixtures" - {
          smoke = wizard.mix(1 to 22 map (_ * 3) toList)
          smoke ==> 24763
          smoke
        }
      }

      // NOTE - The algorithm struggles above 20 mixtures
      // Solve using concurrency; for example, see:

      // https://stackoverflow.com/questions/1715580/how-to-discover-number-of-logical-cores-on-mac-os-x
      // https://blog.matthewrathbone.com/2017/03/28/scala-concurrency-options.html
      // http://danielwestheide.com/blog/2013/02/27/the-neophytes-guide-to-scala-part-14-the-actor-approach-to-concurrency.html
      // http://www.reactive.io/tips/2014/03/28/getting-started-with-actor-based-programming-using-scala-and-akka
      // http://www.reactive.io/tips/2014/04/08/parallel-computing-made-easy-with-scala-and-akka
      // https://www.safaribooksonline.com/library/view/applied-akka-patterns/9781491934876/ch04.html

      //      "twenty-five mixtures" - {
      //        "smoke for twenty-five mixtures" - {
      //          smoke = wizard.mix(1 to 25 map (_ * 3) toList)
      //          smoke ==> 20235
      //          smoke
      //        }
      //      }
    }
  }
}