package org.ludwiggj.mixtures

object Wizard {

  //  def mix(mixtures: Seq[Int]) = {
  //    val potion = mixtures.foldLeft(0, 0) {
  //      case ((potionMixture, smoke), mixture) => ((potionMixture + mixture) % 100, smoke + (potionMixture * mixture))
  //    }
  //    potion._2
  //  }

  def mix(mixtures: List[Int]): Int = {
    println(s"+++ Mixtures $mixtures +++")
    mixtures match {
      case Nil => -1
      case mixture :: Nil => mixture
      case mixtureList => {
        val list =
        {
          (0 to mixtureList.size - 2).toList map { i =>
            val leadingMixtures = mixtureList take i
            val thisMix = (mixtureList(i) + mixtureList(i + 1)) % 100
            val trailingMixtures = mixtureList drop i + 2

            println(s">>>>>> Processing [$leadingMixtures, (${mixtureList(i)} + ${mixtureList(i + 1)}), $trailingMixtures] <<<")
            mix(leadingMixtures ++ List(thisMix) ++ trailingMixtures)
          }
        }

        println(s"--------- Results $list ---")
        list.min
      }
    }
  }
}

//        val nextStepSublists = mixtures.sliding(2, 1).toList.zipWithIndex map {
//          case (pairList, index) =>
//            (mixtureList take index, (pairList(0), pairList(1)), mixtureList drop (index + 2))
//        }
//        println(s">>>>>>>>>>>> Sublists $nextStepSublists <<<")
//        val mixes = nextStepSublists.map {
//          case (first, pair, last) => {
//            mix((first :+ ((pair._1 + pair._2) % 100)) ++ last)
//          }
//        }

//l.sliding (2, 1).toList.zipWithIndex
//
//val kahuna2 = z map {
//  case (pair, index) =>
//  | (l take index, pair, l drop (index + 2) )
//  |
//}
//
//  kahuna2 map {
//  x =>
//  | x._1 ++ x._2 ++ x._3
//  |
//}