//name := "swagger-validator"
//
//version := "1.0"
//
//scalaVersion := "2.12.1"

lazy val root = (project in file(".")).
  settings(
    version       := "1.0.0",
    name          := "swagger-validator",
    organization  := "io.swagger",
    scalaVersion  := "2.11.8",

    libraryDependencies ++= Seq(
      "com.fasterxml.jackson.module" %% "jackson-module-scala" % "2.4.2",
      "com.sun.jersey" % "jersey-core" % "1.19",
      "com.sun.jersey" % "jersey-client" % "1.19",
      "com.sun.jersey.contribs" % "jersey-multipart" % "1.19",
      "org.jfarcand" % "jersey-ahc-client" % "1.0.5",
      "io.swagger" % "swagger-core" % "1.5.8",
      "joda-time" % "joda-time" % "2.2",
      "org.joda" % "joda-convert" % "1.2",
      "org.scalatest" %% "scalatest" % "2.2.4" % "test",
      "junit" % "junit" % "4.8.1" % "test",
      "io.circe" %% "circe-core" % "0.7.0-M2",
      "io.circe" %% "circe-generic" % "0.7.0-M2",
      "io.circe" %% "circe-parser" % "0.7.0-M2",
      "io.circe" %% "circe-java8" % "0.7.0-M2",
      "ch.qos.logback" % "logback-classic" % "1.2.3",
      "com.typesafe.scala-logging" %% "scala-logging" % "3.7.1"
    ),

    resolvers ++= Seq(
      Resolver.jcenterRepo,
      Resolver.mavenLocal
    ),

    scalacOptions := Seq(
      "-unchecked",
      "-deprecation",
      "-feature"
    ),

    publishArtifact in (Compile, packageDoc) := false
  )

//libraryDependencies ++= Seq(
//                        "io.circe" %% "circe-core" % "0.7.0-M2",
//                        "io.circe" %% "circe-generic" % "0.7.0-M2",
//                        "io.circe" %% "circe-parser" % "0.7.0-M2",
//                        "io.circe" %% "circe-java8" % "0.7.0-M2",
//                        "ch.qos.logback" % "logback-classic" % "1.2.3",
//                        "com.typesafe.scala-logging" %% "scala-logging" % "3.7.1"
//)
