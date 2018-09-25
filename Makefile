.DEFAULT_GOAL := build-run

build:
	./mvnw clean package

run:
	java -jar ./target/project-lvl1-s420-1.0-SNAPSHOT-jar-with-dependencies.jar

update:
	./mvnw versions:update-properties
	./mvnw versions:display-plugin-updates

build-run: build run
