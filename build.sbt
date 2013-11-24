name := "Coursera Code Samples"

version := "0.1"

scalaVersion := "2.10.2"

resolvers += "Typesafe Repository" at "http://repo.typesafe.com/typesafe/releases/"

libraryDependencies ++= Seq(
  "org.scala-lang.modules" %% "scala-async" % "0.9.0-M3",
  "com.netflix.rxjava" % "rxjava-scala" % "0.15.0",
  "com.squareup.retrofit" % "retrofit" % "1.2.2",
  "com.typesafe.akka" %% "akka-actor" % "2.2.3"
)
