package coursera.rx

import rx.lang.scala.Observable
import scala.languageFeature.postfixOps._
import scala.concurrent.duration._

object Nested {

  def flattenNestedStreams(): Unit = {

    val xs: Observable[Int]              = Observable(3,2,1)
    val yss: Observable[Observable[Int]] = xs.map(x => Observable.interval(x seconds).map(_ => x).take(2))
    val zs = yss.flatten

    println(zs.toBlockingObservable.toList.equals(List(1,2,1,3,2,3)))

  }

  def concatenateNestedStreams(): Unit = {

    val xs: Observable[Int]              = Observable(3,2,1)
    val yss: Observable[Observable[Int]] = xs.map(x => Observable.interval(x seconds).map(_ => x).take(2))
    val zs = yss.concat

    println(zs.toBlockingObservable.toList.equals(List(3, 3, 2, 2, 1, 1)))

  }

}
