package coursera.usgs

class Feature {

  val properties : Properties = null
  val geometry: Point         = null

  override def toString() = s"{ 'properties':'${properties}', 'geometry':'${geometry}' }";
}
