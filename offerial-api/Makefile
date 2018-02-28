PROJECT_NAME=offerial


compile:
	./gradlew build

package-exec:
	./gradlew shadowJar

build-docker:
	./gradlew shadowJar && docker build . -t offerio

run-docker:
	docker run -d -p 9999:9999 offerio


start:
	./gradlew startServer

stop:
	./gradlew stopServer

help:
	@echo 'Available commands: help, build, build-exec, build-docker'
	@echo ''
	@echo 'Commands in this Makefile are top level commands to package the final version of the app. For low level commands, see subdirectories.'
	@echo ''

.PHONY: help