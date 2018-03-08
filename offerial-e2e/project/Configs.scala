import sbt._


object Configs {

  val SmokeTest = config("smoke") extend (Test)

}