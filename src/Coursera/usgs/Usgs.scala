package coursera.usgs

import retrofit.http.GET
import retrofit.{RetrofitError, RestAdapter, Callback}
import rx.lang.scala.Observable
import rx.lang.scala.subjects.AsyncSubject
import retrofit.client.Response

object Usgs {

  private val restAdapter = new RestAdapter.Builder().setServer("http://earthquake.usgs.gov").build()

  def apply(): Observable[Feature] = {

    val subject = AsyncSubject[FeatureCollection]()

    restAdapter.create(classOf[Usgs]).get(new Callback[FeatureCollection] {

      def failure(error: RetrofitError): Unit = {
        subject.onError(error.getCause)
      }

      def success(t: FeatureCollection, response: Response): Unit = {
        subject.onNext(t)
        subject.onCompleted()
      }

    })

    subject.flatMap(collection => Observable(collection.features : _*))
  }
}

private trait Usgs {
  @GET("/earthquakes/feed/geojson/all/day")
  def get(callback: Callback[FeatureCollection])
}

