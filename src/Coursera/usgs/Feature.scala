package coursera.usgs

<<<<<<< HEAD
=======
import retrofit.http.GET
import retrofit.{RestAdapter, RetrofitError, Callback}
import rx.lang.scala.Observable
import rx.lang.scala.subjects.AsyncSubject
import retrofit.client.Response


>>>>>>> f75c8cbe5585da5c05d5d5f7a7aa8923f1f74451
class Feature {

  val properties : Properties = null
  val geometry: Point         = null

  override def toString() = s"{ 'properties':'${properties}', 'geometry':'${geometry}' }";
}
