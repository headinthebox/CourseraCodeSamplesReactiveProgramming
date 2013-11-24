package coursera.usgs

class Point {
  private val coordinates: Array[Double] = null

  lazy val latitude: Double              = coordinates(1)
  lazy val longitude  : Double           = coordinates(0)
  lazy val altitude  : Double            = coordinates(2)

  override def toString() = s"{ 'longitude':'${longitude}', 'latitude':'${latitude}', 'altitude':'${altitude}' }";

}
