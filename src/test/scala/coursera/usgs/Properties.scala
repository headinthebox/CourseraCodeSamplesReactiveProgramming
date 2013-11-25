package coursera.usgs

import com.google.gson.annotations.SerializedName
import java.util.Date
import scala.transient

class Properties {
  val place: String           = null
  @SerializedName("time")
  private val _time: Long     = 0L
  @transient
  lazy val time: Date         = new Date(_time)
  @SerializedName("updated")
  private val _updated: Long  = 0L
  @transient
  lazy val updated: Date      = new Date(_updated)
  @SerializedName("mag")
  val magnitude: Double       = 0D
  val detail: String          = null
  val felt: Int               = 0
  val cdi: Double             = 0D
  val mmi: Double             = 0D
  val alert: String           = null
  val status: String          = null
  val tsunami: Int            = 0
  val sig: Int                = 0
  val net: String             = null
  val code: String            = null
  val ids: String             = null
  val sources: String         = null
  val types: String           = null
  val nst: Int                = 0
  val dmin: Double            = 0D
  val rms: Double             = 0D
  val gap: Double             = 0D
  val magType: String         = null
  val `type`: String          = null

  // add fields that you want to see.
  override def toString() = s"{ 'time':'${time}', 'place':'${place}', 'magnitude':'${magnitude}' }";

}
