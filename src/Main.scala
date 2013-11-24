import coursera.adventure.unsafe.Adventure
import coursera.rx._
import coursera.usgs.{Magnitude, Usgs}
import rx.lang.scala.Observable
import coursera.rx.{Subscriptions, EarthQuakes, Nested, Quizzes}
import coursera.usgs.{Magnitude, Usgs}

object Main {
  def main(args: Array[String]): Unit = {
    // coursera.rx.HelloObservables.ticks()
    // Quizzes.quizI()
    // Nested.flattenNestedStreams()
    // Nested.concatenateNestedStreams()
    // EarthQuakes.quakes().subscribe(println(_))
    // EarthQuakes.major().subscribe(println(_))
    // EarthQuakes.ofMagnitude(Magnitude.Minor).subscribe(println(_))
    // EarthQuakes.withCountryMerged().subscribe(println(_), e => println(e.getMessage), () => println(">>>>>>"))
    // EarthQuakes.withCountryConcatenated().subscribe(println(_), e => println(e.getMessage), () => println(">>>>>>"))
    // EarthQuakes.groupedByCountry().subscribe(println(_), e => println(e.getMessage), () => println(">>>>>>"))
    // Subscriptions.FishingI()
    // Subscriptions.FishingII()
    // Subscriptions.Composite()
    // Subscriptions.Multi()
    // Creation.from(Range(1,10).inclusive).subscribe(println(_))
    // Creation.startWith(Observable(4,5,6), 0, 1, 2, 3).subscribe(println(_))
    // Creation.filter(Observable(Range(1,10).inclusive), (x: Int) => x%2 == 0).subscribe(println(_))
    // Creation.map(Observable(Range(1,10).inclusive), (x: Int) => x*2).subscribe(println(_))
    // Subjects.PublishSubjectIsAChannel()
    // Subjects.ReplaySubjectIsAChannel()
    // Subjects.AsyncSubjectIsAFuture()
    // Subjects.BehaviorSubjectIsACache()
    // Blocking.dontDoThisAtHomeKids()
  }

}







