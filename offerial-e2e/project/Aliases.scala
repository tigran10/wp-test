import sbt._

object Aliases {

  val aliases =
      addCommandAlias("e2eTest", ";project offerial-e2e-tests ;clean; test")
}