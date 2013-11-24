package coursera.rx

import rx.lang.scala.Observable
import scala.language.postfixOps
import scala.concurrent.duration._
import org.junit.Test
import org.scalatest.junit.JUnitSuite
import org.junit.Assert._

class Nested extends JUnitSuite {

  @Test def flattenNestedStreams(): Unit = {

    val xs: Observable[Int]              = Observable(3,2,1)
    val yss: Observable[Observable[Int]] = xs.map(x => Observable.interval(x seconds).map(_ => x).take(2))
    val zs = yss.flatten

    assertEquals(List(1, 2, 1, 3, 2, 3), zs.toBlockingObservable.toList)
  }

  @Test def concatenateNestedStreams(): Unit = {

    val xs: Observable[Int]              = Observable(3,2,1)
    val yss: Observable[Observable[Int]] = xs.map(x => Observable.interval(x seconds).map(_ => x).take(2))
    val zs = yss.concat

    assertEquals(List(3, 3, 2, 2, 1, 1), zs.toBlockingObservable.toList)
  }

}
