package coursera.rx

import rx.lang.scala.subscriptions._
import org.scalatest.junit.JUnitSuite
import org.junit.Assert._
import org.junit.Test

class Subscriptions extends JUnitSuite {

  @Test def FishingI(): Unit = {

   val subscription = Subscription{
     println("Bye, bye, I'm out fishing")
   }

   subscription.unsubscribe()
   println(subscription.isUnsubscribed)
   // bug!
   subscription.unsubscribe()
 }

  @Test def FishingII(): Unit = {

    val subscription = BooleanSubscription{
      println("Bye, bye, I'm out fishing as well")
    }

    subscription.unsubscribe()
    println(subscription.isUnsubscribed)
    // bug!
    subscription.unsubscribe()
  }

  @Test def Composite(): Unit = {
    val a = BooleanSubscription { println("Apple") }
    val b = Subscription { println("Banana") }
    val c = Subscription{ println ("Cranberry") }

    val composite = CompositeSubscription(a,b)

    println(s"composite.isUnsubscribed=${composite.isUnsubscribed}")

    composite.unsubscribe()

    println(s"a.isUnsubscribed=${a.isUnsubscribed}")
    println(s"b.isUnsubscribed=${b.isUnsubscribed}")
    println(s"composite.isUnsubscribed=${composite.isUnsubscribed}")

    println(s"c.isUnsubscribed=${c.isUnsubscribed}")
    composite += c
    println(s"c.isUnsubscribed=${c.isUnsubscribed}")

  }

  @Test def Multi(): Unit = {
    val a = BooleanSubscription { println("Apple") }
    val b = Subscription { println("Banana") }
    val c = Subscription{ println ("Cranberry") }

    val multiple = MultipleAssignmentSubscription()

    println(s"multiple.isUnsubscribed=${multiple.isUnsubscribed}")

    multiple.subscription = a
    multiple.subscription = b

    multiple.unsubscribe()

    println(s"a.isUnsubscribed=${a.isUnsubscribed}")
    println(s"b.isUnsubscribed=${b.isUnsubscribed}")
    println(s"multiple.isUnsubscribed=${multiple.isUnsubscribed}")

    println(s"c.isUnsubscribed=${c.isUnsubscribed}")
    multiple.subscription = c
    println(s"c.isUnsubscribed=${c.isUnsubscribed}")

  }

}
