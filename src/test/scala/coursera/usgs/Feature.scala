package coursera.usgs

import retrofit.http.GET
import retrofit.{RestAdapter, RetrofitError, Callback}
import rx.lang.scala.Observable
import rx.lang.scala.subjects.AsyncSubject
import retrofit.client.Response

class Feature {

  val properties : Properties = null
  val geometry: Point         = null

  override def toString() = s"{ 'properties':'${properties}', 'geometry':'${geometry}' }";
}
