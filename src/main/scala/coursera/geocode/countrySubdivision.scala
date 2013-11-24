package coursera.geocode

class CountrySubdivision {
  val countryCode: String = null
  var countryName: String = null
  val adminCode1: String = null
  val adminName1: String = null

  override def toString(): String = {
    s"{ 'countryCode':'${countryCode}', 'countryName':'${countryName}', 'adminCode1':'${adminCode1}', 'adminName1':'${adminName1}'}";
  }
}