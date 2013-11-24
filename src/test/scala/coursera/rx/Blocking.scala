package coursera.rx

import rx.lang.scala.Observable

import scala.language.postfixOps
import scala.concurrent.duration._
import org.junit.Test
import org.scalatest.junit.JUnitSuite


class Blocking extends JUnitSuite {

  // List(0, 1, 2, 3, 4)
  // 10
  @Test def dontDoThisAtHomeKids(): Unit = {

    val xs: Observable[Long] = Observable.interval(1 second).take(5)

    val ys: List[Long] = xs.toBlockingObservable.toList
    println(ys)

    val zs: Observable[Long] = xs.sum

    val sum: Long = zs.toBlockingObservable.single
    println(sum)

  }

}
