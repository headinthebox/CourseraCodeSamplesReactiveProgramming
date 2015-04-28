name := "CourseraCodeSamplesReactiveProgramming"

version := "1.0"

scalaVersion := "2.11.6"

scalacOptions ++= Seq("-deprecation", "-feature")

libraryDependencies ++= Seq(
    "com.netflix.rxjava" % "rxjava-scala" % "0.15.1",
    "org.scalatest" %% "scalatest" % "2.2.4",
    "junit" % "junit" % "4.11",
    "org.scala-lang.modules" %% "scala-async" % "0.9.2",
    "com.squareup.retrofit" % "retrofit" % "1.2.2",
    "com.typesafe.akka" %% "akka-actor" % "2.3.10"
)

