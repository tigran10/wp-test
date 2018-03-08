package com.wordplay.oferrial.e2e


import com.jayway.restassured.response.Response
import com.typesafe.scalalogging.StrictLogging
import org.joda.time.DateTime
import org.scalatest._
import org.scalatest.concurrent.Eventually

import org.hamcrest.Matchers.equalTo
import scala.concurrent.duration._
import scalaz.Scalaz._
import scala.io.Source


class CrudE2eSpec extends FunSpec with StrictLogging with Configuration with Matchers with Eventually with GivenWhenThen with BeforeAndAfter
  with OneInstancePerTest with OfferialRestApiHelper {

  val readJson = readJsonFromDir("offers") _
  def readJsonFromDir(dir: String)(fileName: String): String = Source.fromInputStream(getClass().getResourceAsStream(s"/messages/${dir}/${fileName}.json")).mkString

  implicit val patience = PatienceConfig(5 seconds, 1 second)
  implicit val versionId = DateTime.now.getMillis

  before {
    Given("Server is running")
    getServerUpPage() |> responseShouldBeOK
  }

  after {
    // wait a bit to make sure all routes are reinitialised
    Thread.sleep(1000L)
  }

  describe("Offerial") {

    it("should save offer create request correctly") {
      createOffer(readJson("google")) |> responseShouldBeCreated |> responseContainsGoogle
    }

    it("should update the offer") {
      updateOffer("6adbee8d-dd37-4828-8656-8404a66680ef", readJson("iphone")) |> responseShouldBeOK
    }

    it("should delete the offer") {
      deleteOffer("6adbee8d-dd37-4828-8656-8404a66680ef") |> responseShouldBeOK
    }
  }

  def responseContainsGoogle(response: Response): Unit = response.then().body(
    "uniqueId", equalTo("6adbee8d-dd37-4828-8656-8404a66680ef")
  )

  def creatingOffersShouldBeCorrect() {
    withClue("this offer was updated, database should contain new offer") {

    }
  }

}
