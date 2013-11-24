package coursera

import scala.concurrent.{Promise, ExecutionContext, Future}
import scala.async.Async._
import scala.util.{Success, Failure, Try}
import coursera.extensions._

package object combinators {

  def retryI[T](n: Int)(block: =>Future[T]): Future[T] = {
    if (n == 0) {
      Future.failed(new Exception("Sorry"))
    } else {
      block fallbackTo { retryI(n-1) { block } }
    }
  }

  def retryII[T](n: Int)(block: =>Future[T]): Future[T] = {
    val ns: Iterator[Int] = (1 to n).iterator
    val attempts: Iterator[()=>Future[T]] = ns.map(_ => ()=>block)
    val failed: Future[T] = Future.failed(new Exception)
    attempts.foldLeft(failed)((a, block) => a fallbackTo { block() })
  }

  def retryIII[T](n: Int)(block: =>Future[T]): Future[T] = {
    val ns: Iterator[Int] = (1 to n).iterator
    val attempts: Iterator[()=>Future[T]] = ns.map(_ => ()=>block)
    val failed: Future[T] = Future.failed(new Exception)
    attempts.foldRight(()=>failed)((block, a) => ()=> { block() fallbackTo{ a() }}) ()
  }

  def retryIV[T](n: Int)(block: =>Future[T])(implicit executor: ExecutionContext): Future[T] = async {
    var i: Int = 0
    var result: Try[T] = Failure(new Exception("Oops"))

    while (i < n) {
      result = await { block.withTry() }

      result match {
        case Success(s) => { i = i+1  }
        case Failure(f) => { i = n    }
      }
    }

    result.get
  }

  def filterI[T](future: Future[T], predicate: T => Boolean)(implicit executor: ExecutionContext): Future[T] = async{
    val x: T = await{ future }
    if(!predicate(x)) {
      throw new Exception("No such element")
    } else {
      x
    }
  }

  def filterII[T](future: Future[T], predicate: T => Boolean)(implicit executor: ExecutionContext): Future[T] = {
    val p = Promise[T]()
    future.onComplete {
      case Success(s) => {
        if(!predicate(s)) {
          p.failure(new Exception("No such element"))
        } else {
          p.success(s)
        }
      }
      case Failure(f) => { p.failure(f) }
    }
    p.future
  }

  def flatMap[T,S](future: Future[T], selector: T => Future[S])(implicit executor: ExecutionContext): Future[S] = async{
    val x: T = await{ future }
    await{ selector(x) }: S
  }

  def race[T](left: Future[T], right: Future[T])(implicit executor: ExecutionContext): Future[T] = {
    val p = Promise[T]()

    left  onComplete { p.tryComplete }
    right onComplete { p.tryComplete }

    p.future
  }

  def zipI[T, S, R](future: Future[T], other: Future[S], combine: (T, S) => R)
                   (implicit executor: ExecutionContext): Future[R] = async {
    combine(await{ future }: T, await{ other }: S)
  }

  def zipII[T, S, R](future: Future[T], other: Future[S], combine: (T, S) => R)
                    (implicit executor: ExecutionContext): Future[R] = {
    val p = Promise[R]()

    future onComplete {
      case Failure(f) => { p.failure(f) }
      case Success(t) => { other onComplete {
        case Failure(f) => { p.failure(f) }
        case Success(s) => p.success(combine(t,s))
      }}
    }

    p.future
  }
}