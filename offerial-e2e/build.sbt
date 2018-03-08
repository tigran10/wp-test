import Testing._
import Configs._
import Dependencies._
import BaseSettings._
import sbt._


lazy val Parent = Project("root", file("."))
  .configs(SmokeTest)
  .aggregate(offerialE2eTests)
  .settings(baseSettings: _*)

lazy val offerialE2eTests = Project("offerial-e2e-tests", file("offerial-e2e-tests"))
  .configs(SmokeTest)
  .settings(baseSettings: _*)
  .settings(testSettings: _*)
  .settings(e2eDeps: _*)

