package coursera

import scala.concurrent.duration._
import org.junit.Test
import org.scalatest.junit.JUnitSuite
import _root_.rx.lang.scala.Observable
import _root_.rx.lang.scala.Notification

object Utils {
  
  /**
   * Print an observable to stdout, blocking the calling thread.
   */
  def displayObservable[T](o: Observable[T]): Unit = {
    println()
    toPrintableNotifications(o).toBlockingObservable.foreach(println(_))
    println()
  }
  
  def toPrintableNotifications[T](o: Observable[T]): Observable[String] = {
    val t0 = System.currentTimeMillis
    for ((millis, notif) <- o.materialize.timestamp) 
      yield f"t = ${(millis-t0)/1000.0}%.3f: ${notificationToString(notif)}"
  }
  
  /**
   * does what Notification.toString (or its subclasses) should do
   */
  def notificationToString[T](n: Notification[T]): String = n match {
    case Notification.OnNext(value) => s"OnNext($value)"
    case Notification.OnError(err) => s"OnError($err)"
    case Notification.OnCompleted() => "OnCompleted()"
  }
  
  implicit class ExtendedObservable[T](o: Observable[T]) {
    def doOnEach(onNext: T => Unit): Observable[T] = {
      val action: _root_.rx.util.functions.Action1[T] = _root_.rx.lang.scala.ImplicitFunctionConversions.scalaFunction1ProducingUnitToAction1(onNext)
      // casting because Java Observable lacks "? super T"
      val jObs: _root_.rx.Observable[T] = o.asJavaObservable.asInstanceOf[_root_.rx.Observable[T]].doOnEach(action)
      Observable[T](jObs)
    }
  }
}
