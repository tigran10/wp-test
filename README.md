# Offerrial

This is a small microservice which provides somple CRUD operations and moderation status for offers.

## Technologies
I was thinking to go with good old `spring boot`, but then changed my mind and tried with `dropwizard` as an alternative lightweight framework. 
I have not used any in memory db, to not have yet another dependency and layer in the test. Instead there is a simple `dao` like interface with simple implementation. I have included `Dockerfile` for convinience, but havent made it part of my gradle build yet.


## Build

To build a shadow jar use command below

```./gradlew shadowJar``` or ```make package-exec```

## Build Docker
To have things packaged into docker image run makefile command below

```make build-docker``` 

## RUN
Use commands below to run/stop fat jar
```./gradlew startServer``` and ```./gradlew stopServer``` 

And for docker ```make run-docker``` to run it. It will map `9999` port from container to the host.

open `localhost:9999/swagger`

## Docs

I hope code is self explanatory, `swagger` end point contains all the resource definitions. 
You can navigate all the `APIs` using `hateoas` links, starting from the root `localhost:9999`. 
Every response contains links to related resources or actions (like changing `moderation status` of offer).

## Default Data
I am intentionally adding one offer to inital dao service, you can query it with id  `"6adbee8d-dd37-4828-8656-8404a66680ef"`.
Or just query `http://localhost:9999/offers` to get list of offers.

## JSON example
You can use json snipped below when playing with endpoints which require payload like `POST` and `PUT` 

```
{
  "id": "6adbee8d-dd37-4828-8656-8404a66680ef",
  "name": "google home",
  "price": {
    "value": 49.124,
    "currency": "GBP"
  },
  "moderationStatus": "APPROVED",
  "startDate": "2018-02-27",
  "expiryDate": "2019-02-27"
}
```
## Offer Details

- *moderationStatus* - It is possible to mark offer as `APPROVED` `PENDING` or `REJECTED`. 

- *active* -  This field tells the client if the offer is currently `active` or not. It takes into considiration the moderationsStatus and start/expiry dates.

- *displayValue* - its a formatted version for display. 

The rest is pretty standard 

```
  "offerDetails": {
    "id": "6adbee8d-dd37-4828-8656-8404a66680ef",
    "name": "iphone 6s",
    "startDate": "2018-03-01",
    "expiryDate": "2018-03-01",
    "price": {
      "value": 10.124,
      "currency": "GBP",
      "displayValue": 10.12
    },
    "moderationStatus": "APPROVED",
    "active": false
```

## Integration tests

Integration tests are done with `spec2`. I like bdd-ing with cucumber as well, but this time i have chosen more geeky approach and went for `specs2`. You will need SBT to run the tests. Make sure you have `offerial` application up and running. 


```
cd offerial-e2e-tests
sbt test
```

If your application is running under specific `host` and `port` you can change `application.conf` file in `resources` folder.

If you dont want to install sbt and friends, you can run tests inside container, which has all cli tools ready there.

```
docker-compose -f docker-compose-test-local.yml run --rm e2e

```

## Known issues
⛄️🤔❄️
