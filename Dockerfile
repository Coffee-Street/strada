FROM openjdk:8-jdk

COPY ./ /test-application
WORKDIR /test-application

CMD ["./gradlew", "bootRun"]
