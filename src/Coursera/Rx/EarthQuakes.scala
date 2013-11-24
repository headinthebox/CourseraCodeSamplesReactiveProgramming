package coursera.rx

import rx.lang.scala.Observable
import scala.languageFeature.postfixOps._
import coursera.usgs.{Point, Usgs, Feature, Magnitude}
import coursera.usgs.Magnitude.Magnitude
import coursera.geocode.{ReverseGeocode, CountrySubdivision}
import scala.concurrent.{ExecutionContext, Future}
import ExecutionContext.Implicits.global


object EarthQuakes {

  def quakes(): Observable[Feature] = Usgs()

  def major(): Observable[(Point, Magnitude)] = ofMagnitude(Magnitude.Major)

  def ofMagnitude(atLeast: Magnitude) = {

    quakes().map(quake => (quake.geometry, Magnitude(quake.properties.magnitude))).filter {
       case (location, magnitude) => magnitude >= atLeast
    }

  }

  def withCountry(flatten: Observable[Observable[(Feature, CountrySubdivision)]] => Observable[(Feature, CountrySubdivision)])
    : Observable[(Feature, CountrySubdivision)] = {
    flatten(quakes().map(quake => {
      val country: Future[CountrySubdivision] = ReverseGeocode(quake.geometry)
      ToObservable(country.map(country => (quake,country)))
         .filter{ case(quake, country) => country.countryName != null }
    }))
  }

  def withCountryMerged(): Observable[(Feature, CountrySubdivision)] = {
     withCountry(xss => xss.flatten)
  }

  def withCountryConcatenated(): Observable[(Feature, CountrySubdivision)] = {
    withCountry(xss => xss.concat)
  }

  def groupedByCountry(): Observable[(String, Observable[(Feature, CountrySubdivision)])] = {
    withCountryMerged().groupBy{ case (quake, country) => country.countryName }
  }
}




