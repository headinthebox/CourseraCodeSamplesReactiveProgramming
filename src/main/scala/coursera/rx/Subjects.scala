package coursera.rx

import org.junit.Test
import org.scalatest.junit.JUnitSuite
import org.junit.Assert._
import rx.lang.scala.subjects.{BehaviorSubject, AsyncSubject, ReplaySubject, PublishSubject}


class Subjects extends JUnitSuite {

  /*
  Banana: 42
  Apple: 42

  Banana: 4711
  Banana.

  Cranberry.
   */
  @Test def PublishSubjectIsAChannel() {

    val channel = PublishSubject[Int](0)  // bug, should be PublishSubject[Int]() 
    val a = channel.subscribe(x => println(s"Apple: $x"), e => println(s"Apple~ $e"), () => println(s"Apple."))
    val b = channel.subscribe(x => println(s"Banana: $x"), e => println(s"Banana~ $e"), () => println(s"Banana."))

    channel.onNext(42)

    a.unsubscribe()

    channel.onNext(4711)
    channel.onCompleted()

    val c = channel.subscribe(x => println(s"Cranberry: $x"), e => println(s"Cranberry~ $e"), () => println(s"Cranberry."))

    channel.onNext(13)
  }

  /*
  Banana: 42
  Apple: 42

  Banana: 4711
  Banana.

  Cranberry: 42
  Cranberry: 4711
  Cranberry.
   */
  @Test def ReplaySubjectIsAChannel() {

    val channel = ReplaySubject[Int]()
    val a = channel.subscribe(x => println(s"Apple: $x"), e => println(s"Apple~ $e"), () => println(s"Apple."))
    val b = channel.subscribe(x => println(s"Banana: $x"), e => println(s"Banana~ $e"), () => println(s"Banana."))

    channel.onNext(42)

    a.unsubscribe()

    channel.onNext(4711)
    channel.onCompleted()

    val c = channel.subscribe(x => println(s"Cranberry: $x"), e => println(s"Cranberry~ $e"), () => println(s"Cranberry."))

    channel.onNext(13)
  }

  /*
  Apple: 2013
  Banana: 2013

  Banana: 42
  Apple: 42

  Banana: 4711
  Banana.

  Cranberry.
   */
  @Test def BehaviorSubjectIsACache() {

    val channel = BehaviorSubject(2013)
    val a = channel.subscribe(x => println(s"Apple: $x"), e => println(s"Apple~ $e"), () => println(s"Apple."))
    val b = channel.subscribe(x => println(s"Banana: $x"), e => println(s"Banana~ $e"), () => println(s"Banana."))

    channel.onNext(42)

    a.unsubscribe()

    channel.onNext(4711)
    channel.onCompleted()

    val c = channel.subscribe(x => println(s"Cranberry: $x"), e => println(s"Cranberry~ $e"), () => println(s"Cranberry."))

    channel.onNext(13)
  }

  /*
  Banana: 4711
  Banana.
  Cranberry: 4711
  Cranberry.
   */
  @Test def AsyncSubjectIsAFuture() {

    val channel = AsyncSubject[Int]()
    val a = channel.subscribe(x => println(s"Apple: $x"), e => println(s"Apple~ $e"), () => println(s"Apple."))
    val b = channel.subscribe(x => println(s"Banana: $x"), e => println(s"Banana~ $e"), () => println(s"Banana."))

    channel.onNext(42)

    a.unsubscribe()

    channel.onNext(4711)
    channel.onCompleted()

    val c = channel.subscribe(x => println(s"Cranberry: $x"), e => println(s"Cranberry~ $e"), () => println(s"Cranberry."))

    channel.onNext(13)
  }
}


