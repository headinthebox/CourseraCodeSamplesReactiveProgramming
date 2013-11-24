package coursera.usgs

object Magnitude extends Enumeration {

  type Magnitude = Value
  val Micro, Minor, Light, Moderate, Strong, Major, Great = Value

  def apply(magnitude: Double): Magnitude = {

    if(magnitude >= 8.0) return Great
    if(magnitude >= 7.0) return Major
    if(magnitude >= 6.0) return Strong
    if(magnitude >= 5.0) return Moderate
    if(magnitude >= 4.0) return Light
    if(magnitude >= 3.0) return Minor
    return Micro

  }
}
