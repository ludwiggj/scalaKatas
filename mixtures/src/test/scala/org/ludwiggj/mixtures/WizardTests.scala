package org.ludwiggj.mixtures

import utest._

object WizardTests extends TestSuite {
  val tests = Tests {

    "smoke tests" - {
      var smoke = 0


      "smoke for potion of no mixtures" - {
        smoke = Wizard.mix(List())
        smoke ==> -1
        smoke
      }

      "smoke for potion of one mixture" - {
        smoke = Wizard.mix(List(18))
        smoke ==> 18
        smoke
      }

      "smoke for potion of two mixtures" - {
        smoke = Wizard.mix(List(18, 19))
        smoke ==> 37
        smoke
      }

      "three mixtures" - {

        "smoke for (40, 60, 20)" - {
          smoke = Wizard.mix(List(40, 60, 20))
          smoke ==> 20
          smoke
        }

        //
        //        "smoke for (60, 20, 40)" - {
        //          smoke = Wizard.mix(Seq(60, 20, 40))
        //          smoke ==> 4400
        //          smoke
        //        }

        //        "least smoky for (20, 40, 60)" - {
        //          smoke = Wizard.optimumMix(Seq(20, 40, 60))
        //          smoke ==> 2400
        //          smoke
        //        }

      }

      "five mixtures" - {
        "smoke for (10, 85, 30, 60, 5)" - {
          smoke = Wizard.mix(List(10, 85, 30, 60, 5))
          smoke ==> 90
          smoke
        }
      }
    }
  }
}

/*
scala> val l = List(1,2,3,4,5)
l: List[Int] = List(1, 2, 3, 4, 5)

scala> val z = l.sliding(2,1).toList.zipWithIndex
z: List[(List[Int], Int)] = List((List(1, 2),0), (List(2, 3),1), (List(3, 4),2), (List(4, 5),3))

scala> z map { case (pair, index) =>
     | List(l take index, pair, l drop (index + 2))
     | }
res0: List[List[List[Int]]] = List(List(List(), List(1, 2), List(3, 4, 5)), List(List(1), List(2, 3), List(4, 5)), List(List(1, 2), List(3, 4), List(5)), List(List(1, 2, 3), List(4, 5), List()))

scala> val kahuna = z map { case (pair, index) =>
     |   List(l take index, pair, l drop (index + 2))
     | }
kahuna: List[List[List[Int]]] = List(List(List(), List(1, 2), List(3, 4, 5)), List(List(1), List(2, 3), List(4, 5)), List(List(1, 2), List(3, 4), List(5)), List(List(1, 2, 3), List(4, 5), List()))

scala> val kahuna2 = z map { case (pair, index) =>
     | (l take index, pair, l drop (index + 2))
     | }
kahuna2: List[(List[Int], List[Int], List[Int])] = List((List(),List(1, 2),List(3, 4, 5)), (List(1),List(2, 3),List(4, 5)), (List(1, 2),List(3, 4),List(5)), (List(1, 2, 3),List(4, 5),List()))

scala> kahuna2 map { x=>
     | x._1 ++ x._2 ++ x._3
     | }
res1: List[List[Int]] = List(List(1, 2, 3, 4, 5), List(1, 2, 3, 4, 5), List(1, 2, 3, 4, 5), List(1, 2, 3, 4, 5))
 */