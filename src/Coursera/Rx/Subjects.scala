package coursera.rx

import rx.lang.scala.subjects.{AsyncSubject, ReplaySubject, PublishSubject}

object Subjects {

  /*
  Banana: 42
  Apple: 42

  Banana: 4711
  Banana.

  Cranberry.
   */
  def PublishSubjectIsAChannel() {

    val channel = PublishSubject[Integer](0)  // bug
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
  def ReplaySubjectIsAChannel() {

    val channel = ReplaySubject[Integer]()
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
  def AsyncSubjectIsAFuture() {

    val channel = AsyncSubject[Integer]()
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
