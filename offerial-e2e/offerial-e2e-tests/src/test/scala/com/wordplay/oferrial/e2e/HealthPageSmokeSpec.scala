package com.wordplay.oferrial.e2e

import com.typesafe.scalalogging.StrictLogging
import org.hamcrest.Matchers.equalTo
import org.joda.time.DateTime
import org.scalatest.FunSpec
import org.scalatest.concurrent.Eventually

import scala.concurrent.duration._

class HealthPageSmokeSpec extends FunSpec with StrictLogging with Configuration with Eventually
  with OfferialRestApiHelper {
  implicit val patience = PatienceConfig(5 seconds, 1 second)
  implicit val versionId = DateTime.now.getMillis

  describe("Offerial health status page") {


  }
}

