name := "scala-api-monitor"

version := "0.1"

scalaVersion := "2.12.4"

libraryDependencies += "com.typesafe.akka" %% "akka-http" % "10.0.11"

libraryDependencies += "com.typesafe.akka" %% "akka-http-testkit" % "10.0.11" % "test"

libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.5" % "test"

libraryDependencies +=  "org.scalaj" %% "scalaj-http" % "2.3.0"

libraryDependencies += "com.typesafe.akka" %% "akka-http-spray-json" % "10.0.11"

libraryDependencies += "net.liftweb" %% "lift-json" % "3.2.0"