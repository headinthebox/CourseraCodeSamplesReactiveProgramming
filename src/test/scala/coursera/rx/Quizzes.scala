package coursera.rx

import rx.lang.scala.Observable
import org.junit.Test
import org.scalatest.junit.JUnitSuite
import org.junit.Assert._

class Quizzes extends JUnitSuite {

  @Test def quizI(): Unit = {

    val xs = Observable(1 to 10)
    val ys = xs.map(x => x+1)

    assertEquals(List(2,3,4,5,6,7,8,9,10,11), ys.toBlockingObservable.toList)
  }
}
