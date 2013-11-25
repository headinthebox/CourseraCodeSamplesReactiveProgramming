name := "CourseraCodeSamplesReactiveProgramming"

version := "1.0"

scalaVersion := "2.10.2"

scalacOptions ++= Seq("-deprecation", "-feature")

libraryDependencies ++= Seq(
	"com.netflix.rxjava" % "rxjava-scala" % "0.15.0",
	"org.scalatest" % "scalatest_2.10" % "2.0",
	"junit" % "junit" % "4.11",
	"org.scala-lang.modules" %% "scala-async" % "0.9.0-M2",
	"com.squareup.retrofit" % "retrofit" % "1.2.2",
	"com.typesafe.akka" %% "akka-actor" % "2.2.1"
)

