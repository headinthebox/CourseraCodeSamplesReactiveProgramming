package coursera.rx

import rx.lang.scala.Observable
import scala.language.postfixOps
import scala.concurrent.duration._
import org.junit.Test
import org.scalatest.junit.JUnitSuite
import org.junit.Assert._
import coursera.Utils._

class Nested extends JUnitSuite {
 
  @Test def flattenNestedStreams(): Unit = {

    val xs: Observable[Int]              = Observable(3,2,1)
    val yss: Observable[Observable[Int]] = xs.map(x => Observable.interval(x seconds).map(_ => x).take(2))
    val zs = yss.flatten

    val list = zs.toBlockingObservable.toList
    val isFirst = list == List(1, 1, 2, 3, 2, 3)
    val isSecond = list == List(1, 2, 1, 3, 2, 3) 
    // behavior of flatten is non-deterministic
    assertTrue(isFirst || isSecond)
    if (isFirst) println("first option")
    if (isSecond) println("second option")
  }

  @Test def concatenateNestedStreams(): Unit = {

    val xs: Observable[Int]              = Observable(3,2,1)
    val yss: Observable[Observable[Int]] = xs.map(x => Observable.interval(x seconds).map(_ => x).take(2))
    val zs = yss.concat

    assertEquals(List(3, 3, 2, 2, 1, 1), zs.toBlockingObservable.toList)
  }
  
  /**
   * The marble diagram on the slides is not entirely correct, but the final Observable zs is correct.
   * Interested students can investigate what happens in between using this test.
   * The difference is that Observable.interval produces a cold Observable, which only starts emitting
   * values once concat subscribes to it. So there is no buffering here, and concat is only subscribed
   * to one inner observable at the same time. 
   */
  @Test def concatenateNestedStreamsWhatIsReallyGoingOn() {
    val t0 = System.currentTimeMillis
    
    val xs: Observable[Int] = Observable(3, 2, 1)
    val yss: Observable[Observable[Int]] =
      xs map { x => Observable.interval(x seconds).doOnEach(
        n => println(f"${(System.currentTimeMillis-t0)/1000.0}%.3f:Observable.interval($x seconds) emits $n")
      ).map(_ => x).take(2) }
    val zs: Observable[Int] = yss.concat
    
    displayObservable(zs)
  }

}
