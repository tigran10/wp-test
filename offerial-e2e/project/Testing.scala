import Configs._
import sbt.Keys._
import sbt._


object Testing {

  lazy val defaultTestSettings = Seq(
    fork in Test := false,
    parallelExecution in Test := false
  )

  lazy val smokeTestSettings = inConfig(SmokeTest)(Defaults.testSettings) ++ Seq(
    fork in SmokeTest := false,
    parallelExecution in SmokeTest := false,
    scalaSource in SmokeTest := baseDirectory.value / "src/test/scala",
    resourceDirectory in SmokeTest := baseDirectory.value / "src/test/resources",
    testOptions in SmokeTest := Seq(Tests.Filter({ name => name endsWith "SmokeSpec" }))
  )

  lazy val testSettings = defaultTestSettings ++ smokeTestSettings

}
