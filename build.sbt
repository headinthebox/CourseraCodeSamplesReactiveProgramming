name := "CourseraCodeSamplesReactiveProgramming"

version := "1.0"

scalaVersion := "2.10.2"

val rxVersion = "0.15.0"

libraryDependencies ++= Seq(
	"com.netflix.rxjava" % "rxjava-scala" % rxVersion,
	"org.scalatest" % "scalatest_2.10" % "2.0",
	"junit" % "junit" % "4.11"
)


