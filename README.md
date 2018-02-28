# Offerrial

This is a small microservice which provides somple CRUD operations and moderation status for offers.

## Technologies
I was thinking to go with good old `spring boot`, but then changed my mind and tried with `dropwizard` as an alternative lightweight framework. 
I have not used any in memory db, to not have yet another dependency and layer in the test. Instead there is a simple `dao` like interface with simple implementation.


## Build

To build a shadow jar use command below

```./gradlew shadowJar```

## RUN
Use two commands below to run fat jar
```./gradlew startServer``` and ```./gradlew stopServer``` 

open `localhost:9999/swagger`

## Docs

I hope code is self explanatory, `swagger` end point contains all the resource definitions. 
You can navigate all the `APIs` using `hateoas` links, starting from the root `localhost:9999`. 
Every response contains links to related resources or actions (like changing moderation status of offer).

## Default Data
I am intentionally adding one offer to inital dao service, you can query it with id  `"6adbee8d-dd37-4828-8656-8404a66680ef"`.
Or just query `http://localhost:9999/offers` to get list of offers.


## Integration tests

I will try to add some scala specs2 tests here, if you are reading the message then clearly i did not have time to finish it yet:) 
But if you like the test so far, I will make sure they are in place before meeting.

## Known issues

