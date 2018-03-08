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


  before {
    Given("Server is running")
    getServerUpPage() |> responseShouldBeOK
  }

  after {
    // wait a bit to make sure all routes are reinitialised
    Thread.sleep(1000L)
  }

  describe("offerial api") {

    it("should save offer request correctly") {
      createOffer(readJson("google")) |> responseShouldBeCreated |> responseContainsGoogleId
    }

    it("should not found not existing id") {
      getOffer("8608a4c5-f21b-41a6-8d73-3f8c467bc1c4") |> responseShouldBeNotFound
    }

    it("should get the offer correctly") {
      getOffer("d9c3df7e-9b6e-46bd-897c-6bf39adf92ec")
        .then().statusCode(200)
        .and().body("offerDetails.id", equalTo("d9c3df7e-9b6e-46bd-897c-6bf39adf92ec"))
        .and().body("offerDetails.name", equalTo("google home"))
        .and().body("offerDetails.price.currency", equalTo("GBP"))
    }

    it("should update the offer") {
      updateOffer("d9c3df7e-9b6e-46bd-897c-6bf39adf92ec", readJson("iphone"))
        .then().statusCode(200)
        .and().body("offerDetails.id", equalTo("d9c3df7e-9b6e-46bd-897c-6bf39adf92ec"))
        .and().body("offerDetails.name", equalTo("iphone 8"))
        .and().body("offerDetails.price.currency", equalTo("GBP"))
    }

    it("should delete the offer") {
      deleteOffer("d9c3df7e-9b6e-46bd-897c-6bf39adf92ec") |> responseShouldBeOK
      getOffer("d9c3df7e-9b6e-46bd-897c-6bf39adf92ec") |> responseShouldBeNotFound
    }
  }

  def responseContainsGoogleId(response: Response): Unit = response.then().body("uniqueId", equalTo("d9c3df7e-9b6e-46bd-897c-6bf39adf92ec"))

  def creatingOffersShouldBeCorrect() {
    withClue("this offer was updated, database should contain new offer") {

    }
  }

}
