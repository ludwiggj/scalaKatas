package org.ludwiggj.mixtures

case class Experiment(mixtures: List[Int], pocketBook: PocketBook, smoke: Int = 0) {
  def mix(): Option[Int] = {
    if (pocketBook.continueExperiment(this)) {
//      println(s"+++ Experiment $this")
      mixtures match {
        case Nil => None
        case _ :: Nil => {
//          println(s"********* Result $smoke")
          Some(smoke)
        }
        case mixtureList => {
          val list = {
            (0 to mixtureList.size - 2).toList map { i =>
              val leadingMixtures = mixtureList take i
              val (thisMix, thisSmoke) = mixPair(i)
              val trailingMixtures = mixtureList drop i + 2

//              println(s">>>>>> Processing [$leadingMixtures, (${mixtureList(i)} + ${mixtureList(i + 1)}), $trailingMixtures]")
              this.copy(mixtures = leadingMixtures ++ List(thisMix) ++ trailingMixtures, smoke = smoke + thisSmoke).mix()
            }
          }

//          println(s"--------- Results $list")

          list.filter(_.isDefined) match {
            case l if l.size > 0 => l.min
            case _ => None
          }
        }
      }
    } else {
      return None
    }
  }

  private def mixPair(i: Int): (Int, Int) = {
    ((mixtures(i) + mixtures(i + 1)) % 100, mixtures(i) * mixtures(i + 1))
  }
}