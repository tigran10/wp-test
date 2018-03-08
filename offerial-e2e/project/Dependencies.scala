import sbt._
import Keys._


object Dependencies {

  val sprayVersion = "1.3.2"
  val camelVersion = "2.15.0"

  private val commonDeps = Seq(
    "com.typesafe" % "config" % "1.2.1",
    "ch.qos.logback" % "logback-classic" % "1.1.3",
    "joda-time" % "joda-time" % "2.8.1",
    "org.scalaz" %% "scalaz-core" % "7.1.3",
    "org.jdbi" % "jdbi" % "2.63.1" % "test",
    "com.github.tomakehurst" % "wiremock" % "1.57" % "test",
    "org.scalatest" %% "scalatest" % "2.2.5" % "test"
  )

  val offerialDeps = deps(commonDeps ++
    Seq(
      "org.apache.activemq" % "activemq-camel" % "5.11.1",
      "org.slf4j" % "slf4j-api" % "1.7.12",
      "org.json4s" %% "json4s-jackson" % "3.2.11",
      "io.spray" %% "spray-routing" % sprayVersion,
      "io.spray" %% "spray-can" % sprayVersion,
      "io.spray" %% "spray-client" % sprayVersion,
      "io.spray" %% "spray-httpx" % sprayVersion,
      "io.spray" %% "spray-testkit" % sprayVersion % "test",
      "org.mockito" % "mockito-all" % "1.10.19" % "test"
    )
  )

  val e2eDeps = deps(commonDeps ++
    Seq(
      "com.typesafe.scala-logging" %% "scala-logging" % "3.1.0",
      "com.jayway.restassured" % "rest-assured" % "2.4.1" % "test"
    )
  )


  private def deps(modules: Seq[ModuleID]): Seq[Setting[_]] = Seq(libraryDependencies ++= modules)

}