package coursera.rx

import rx.lang.scala.{Observable, Subscription}
import scala.languageFeature.postfixOps._
import scala.concurrent.duration._

object HelloObservables {

  def ticks(): Unit = {

    val ticks: Observable[Long]        = Observable.interval(1 second)
    val evens: Observable[Long]        = ticks.filter(s => s%2 == 0)
    val buffers: Observable[Seq[Long]] = evens.buffer(2,1)

    // run the program for a while
    val subscription: Subscription     = buffers.subscribe(println(_))

    readLine()

    // stop the stream
    subscription.unsubscribe()
  }

}







