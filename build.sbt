name := "swagger-validator"

version := "1.0"

scalaVersion := "2.12.1"

libraryDependencies ++= Seq(
                        "io.circe" %% "circe-core" % "0.7.0-M2",
                        "io.circe" %% "circe-generic" % "0.7.0-M2",
                        "io.circe" %% "circe-parser" % "0.7.0-M2",
                        "io.circe" %% "circe-java8" % "0.7.0-M2",
                        "ch.qos.logback" % "logback-classic" % "1.2.3",
                        "com.typesafe.scala-logging" %% "scala-logging" % "3.7.1"
                        )
        