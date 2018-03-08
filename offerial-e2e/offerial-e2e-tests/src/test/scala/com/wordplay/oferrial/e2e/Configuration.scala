package com.wordplay.oferrial.e2e

import com.typesafe.config.ConfigFactory

trait Configuration {

  val config = ConfigFactory.load(System.getProperty("env", "application"))

  val offerialHost = config.getString("offerial.host")
  val offerialPort = config.getInt("offerial.port")

}
