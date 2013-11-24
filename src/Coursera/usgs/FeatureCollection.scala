package coursera.usgs

/*
*  Bindings for http://earthquake.usgs.gov/earthquakes/feed/v1.0/geojson.php
*  using Square retrofit
* */

class FeatureCollection {
  val metadata : MetaData       = null
  val features : Array[Feature] = null

  // yes, @headinthebox sometimes uses folds ;-)
  override def toString() = s"{ 'metadata':'${metadata}', 'features':[${features.map(_.toString()).reduceLeft((x,s)=> s"$x,\n $s")}] }";
}










