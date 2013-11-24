package coursera.rx

import rx.lang.scala._
import rx.lang.scala.subscriptions._
import scala.concurrent.{ExecutionContext, Future}
import scala.async.Async._

object Creation {

  def from[T](source: Iterable[T]): Observable[T] = {
     Observable(observer => {
       source.foreach(observer.onNext(_))
       observer.onCompleted()

       // When you return an empty subscription
       // the alarm bells should go off.
       Subscription{}
     })
  }

  def never[T]: Observable[T] = {
    // except here
    Observable(observer => Subscription{})
  }

  def empty[T](): Observable[T] = {
    Observable(observer => {
      // here
      observer.onCompleted()
      Subscription{}
    })
  }

  def error[T](error: Throwable): Observable[T] = {
    Observable(observer => {
      // here
      observer.onError(error)
      Subscription{}
    })
  }

  def single[T](value: T): Observable[T] = {
    Observable(observer => {
      // and here
      observer.onNext(value)
      Subscription{}
    })
  }

  def startWith[T](source: Observable[T], values: T*): Observable[T] = {
    Observable(observer => {
      values.foreach(observer.onNext)
      source.subscribe(observer)
    })
  }

  def filter[T](source: Observable[T], predicate: T=>Boolean): Observable[T] = {
    Observable(observer => {
      source.subscribe(
        value => if(predicate(value)) observer.onNext(value),
        error => observer.onError(error),
        () => observer.onCompleted
      )
    })
  }

  def map[T, R](source: Observable[T], selector: T=>R): Observable[R] = {
    Observable(observer => {
      source.subscribe(
        value => observer.onNext(selector(value)),
        error => observer.onError(error),
        () => observer.onCompleted
      )
    })
  }

  def map[T,R](source: Iterable[T], selector: T=>R): Iterable[R] = {
    new Iterable[R] {

      def iterator = new Iterator[R] {

        // should grab outer here.
        val outer: Iterator[T] = source.iterator

        def hasNext: Boolean = {
          outer.hasNext
        }

        def next(): R = {
          selector(outer.next())
        }
      }
    }
  }

  def map[T,R](source: Future[T], selector: T=>R)(implicit executor: ExecutionContext): Future[R] = async {
    selector(await{ source })
  }
}
