package coursera

import scala.concurrent.{ExecutionContext, Future}
import scala.util.{Failure, Success, Try}

package object extensions {

  implicit class ListExtensions[T](val source: List[T]) extends AnyVal {
    def sumBy(keySelector: T => Int): Int = ???

    private def sumBy[B](keySelector: T => B)(implicit num: Numeric[B]): B = {
        source.map(keySelector).sum
    }

  }

  def f[T](that: Future[T]): PartialFunction[Throwable, Future[T]]  = { case _: Throwable => that }
  def g[T]:                  PartialFunction[Throwable, Failure[T]] = { case t: Throwable => Failure(t) }

  implicit class FutureExtensions[T](val future: Future[T]) extends AnyVal {

    def fallbackTo[U >: T](that: =>Future[U])(implicit executor: ExecutionContext): Future[U] = {
      future.recoverWith(f(that.recoverWith(f(future))))
    }

    def withTry()(implicit executor: ExecutionContext): Future[Try[T]] = {
      future.map(Success(_)) recover g
    }
  }

  def withTry[T](future: Future[T])(implicit executor: ExecutionContext): Future[Try[T]] = {
    future.
    future.map(Success(_)) recover { case t: Throwable => Failure(t) }
  }

  def fallbackTo[U](future: Future[U], that: =>Future[U])(implicit executor: ExecutionContext): Future[U] = {
    future.recoverWith { case _: Throwable => that.recoverWith {  case _: Throwable => future } }
  }

}
