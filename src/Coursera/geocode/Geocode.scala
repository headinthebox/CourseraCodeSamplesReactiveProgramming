package coursera.geocode

import retrofit.http.{Query, GET}
import retrofit.{RetrofitError, RestAdapter, Callback}
import scala.concurrent.{Promise, Future}
import retrofit.http.{Query, Path, GET}
import retrofit.{RetrofitError, RestAdapter, Callback}
import scala.concurrent.{ExecutionContext, Promise, Future}
import retrofit.client.Response
import coursera.usgs.Point

object ReverseGeocode {

  private val restAdapter = new RestAdapter.Builder().setServer("http://ws.geonames.org").build()

  def apply(point: Point): Future[CountrySubdivision] = {
    ReverseGeocode(point.latitude, point.longitude)
  }

  def apply(latitude: Double, longitude: Double): Future[CountrySubdivision] = {

    // Promise/Future is isomorphic to Observer/Observable as a Subject

    val promise = Promise[CountrySubdivision]()

    restAdapter.create(classOf[ReverseGeocode]).get(latitude, longitude, new Callback[CountrySubdivision] {

      def failure(error: RetrofitError): Unit = {
        promise.failure(error.getCause)
      }

      def success(t: CountrySubdivision, response: Response): Unit = {
        promise.success(t)
      }

    })

    promise.future
  }
}

private trait ReverseGeocode {
  @GET("/countrySubdivisionJSON")
  def get(@Query("lat")latitude: Double, @Query("lng")longitude: Double, callback: Callback[CountrySubdivision])
}
