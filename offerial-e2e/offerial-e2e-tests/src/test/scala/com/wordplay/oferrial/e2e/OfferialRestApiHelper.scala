package com.wordplay.oferrial.e2e

import com.jayway.restassured.RestAssured
import com.jayway.restassured.response.Response

trait OfferialRestApiHelper extends Configuration {

  def getServerHealthPage() =
    RestAssured.when()
      .get(s"http://$offerialHost:$offerialPort/health")

  def getServerUpPage() =
    RestAssured.when()
      .get(s"http://$offerialHost:$offerialPort/up")


  def getOffer(id: String) =
    RestAssured.when()
      .get(s"http://$offerialHost:$offerialPort/offers/$id")


  def createOffer(offer: String) =
    RestAssured
      .given()
        .body(offer).contentType("application/json")
      .when()
        .post(s"http://$offerialHost:$offerialPort/offers")


  def deleteOffer(id: String) =
    RestAssured
      .delete(s"http://$offerialHost:$offerialPort/offers/$id")

  def updateOffer(id: String, offer: String) =
    RestAssured
      .given()
        .contentType("application/json")
        .body(offer)
      .when()
        .put(s"http://$offerialHost:$offerialPort/offers/$id")

  def responseShouldBeAccepted(response: Response): Unit = response.then().statusCode(202)

  def responseShouldBeInternalServerError(response: Response): Unit = response.then().statusCode(500)

  def responseShouldBeOK(response: Response): Response = {
    response.then().statusCode(200)
    response
  }

  def responseShouldBeCreated(response: Response): Response = {
    response.then().statusCode(201)
    response
  }

  def responseShouldBeNotFound(response: Response): Unit = response.then().statusCode(404)
}
