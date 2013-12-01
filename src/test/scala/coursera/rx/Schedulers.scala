package coursera.rx

import rx.lang.scala.{Observable, Scheduler, Subscription}
import scala.language.postfixOps
import org.junit.Test
import org.scalatest.junit.JUnitSuite

/**
 * Since several of the sample function below run on their own thread,
 * or run forever, add @Test by hand in front of the functions you want study.
 * It is best to only have one test enabled.
 */
class Schedulers extends JUnitSuite {

  def fromIterableBad[T](seq: Iterable[T]) : Observable[T] = {

     Observable(observer => {
     try {
       seq.foreach(observer.onNext(_))
     } catch  {
       case e: Throwable => observer.onError(e)
     }

     Subscription()
   })
  }

  def from(start: Integer): Iterable[Integer] = {
    new Iterable[Integer]() {
      def iterator: Iterator[Integer] = {
        var n = start-1;
        new Iterator[Integer] () {
          def hasNext: Boolean =  { n += 1; true }
          def next(): Integer = n
        }
      }
    }
  }

  def attemptI(): Unit = {
    val nats: Observable[Integer] = fromIterableBad(from(0))
    val subscription = nats.subscribe(println(_))
    subscription.unsubscribe()
  }

  def helloScheduler(): Unit = {
    val scheduler: Scheduler = rx.lang.scala.concurrency.Schedulers.newThread
    println(s"running on ${Thread.currentThread().getName}")
    val subscription = scheduler.schedule{
      println(s"running on ${Thread.currentThread().getName}")
    }
    subscription.unsubscribe()
  }

  /**
   * Don't count on this to work!
   * The only guarantee you get is that you can cancel the work
   * between it gets scheduled and before it gets run.
   * (RxJava goes out of the way to make it stop)
   */
  def attemptII(): Unit = {
    val scheduler: Scheduler = rx.lang.scala.concurrency.Schedulers.newThread
    val nats: Observable[Integer] = Observable(observer => {
      scheduler.schedule{ from(0).foreach(observer.onNext(_)) }
    })

    val subscription = nats.subscribe(println(_))
    subscription.unsubscribe()
    println("You won the lottery")
  }

  // warning: does not catch exceptions and send to onError
  def attemptIII(): Unit = {
    val scheduler: Scheduler = rx.lang.scala.concurrency.Schedulers.newThread
    val nats: Observable[Integer] = Observable(observer => {
      val iterator = from(0).iterator
      scheduler.scheduleRec(self => {

        if(iterator.hasNext) {
          observer.onNext(iterator.next()); self
        } else {
          observer.onCompleted()
        }

      })
    })
    val subscription = nats.subscribe(println(_))
    subscription.unsubscribe()
    println("we made it work!")
  }

  /**
  * In the slides this is defined as factory method on Observable in the quiz.
  * Perhaps the easiest way to use schedulers in scenarios like the above,
  * since you can directly subscribe to the scheduler.
  */
  def SchedulerToObservable()(implicit scheduler: Scheduler): Observable[Unit] = {
      Observable(observer => {
        scheduler.scheduleRec(self => {
          observer.onNext()
          self
        })
      })
  }

  @Test def unitObservable() {
    implicit val scheduler: Scheduler = rx.lang.scala.concurrency.Schedulers.newThread
    val observable = SchedulerToObservable()
    observable.subscribe(u => println("unit"))
    println("unitObservable out")
  }

  def scheduleRec(outer: Scheduler, work: (=>Unit)=>Unit): Subscription = {
    val subscription = rx.lang.scala.subscriptions.MultipleAssignmentSubscription()
    outer.schedule(s => {
      def loop(): Unit = {
        subscription.subscription = s.schedule {
          work { loop() } }
      }
      loop()
      subscription
    })
    subscription
  }

  @Test def comeOnBabyOneMoreTime(): Unit = {

    val ns = (Observable(observer =>  {
      var n = 0
      scheduleRec(rx.lang.scala.concurrency.Schedulers.newThread, self => {
         observer.onNext(n); n += 1
         self
      })}): Observable[Integer]).take(5)

    ns.subscribe(println(_))

    Thread.sleep(1000)

  }

  def range(start: Int, count: Int)(implicit s: Scheduler): Observable[Int] = {
    Observable(observer => {
      var i = 0
      SchedulerToObservable().subscribe(u => {
        if (i < count) { observer.onNext(start + i); i += 1 }
        else { observer.onCompleted() }
      })
    })
  }

  @Test def range() {
    implicit val scheduler: Scheduler = rx.lang.scala.concurrency.Schedulers.newThread
    val xs = range(1, 10)
    xs.subscribe(x => println(x))
    println("range out")
  }

}
