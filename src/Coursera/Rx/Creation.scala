package coursera.rx

import rx.lang.scala._
import rx.lang.scala.subscriptions._

object Creation {

  def from[T](source: Iterable[T]): Observable[T] = {
     Observable(observer => {
       source.foreach(observer.onNext(_))
       observer.onCompleted()
       Subscription{}
     })
  }

}
