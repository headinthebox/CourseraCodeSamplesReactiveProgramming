package coursera.rx

import rx.lang.scala.Observable

object Quizzes {

  def quizI(): Unit = {

    val xs = Observable(Range(1,10).inclusive)
    val ys = xs.map(x => x+1)

    println(ys.toBlockingObservable.toList.equals(List(2,3,4,5,6,7,8,9,10,11)))

  }
}
