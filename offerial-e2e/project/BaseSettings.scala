import sbt._
import sbt.Keys._
import Aliases._

object BaseSettings {

  lazy val baseSettings =
    Seq(
      version := System.getProperty("build.number", "SNAPSHOT"),
      organization := "com.worldapy",
      description := "offerial integration",
      scalaVersion := "2.11.7",
      javacOptions := Seq(
        "-Xlint:unchecked",
        "-Xlint:deprecation"
      ),
      crossPaths := false,
      resolvers ++= Resolvers.resolverSettings
    ) ++
      aliases
}