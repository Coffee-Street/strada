FROM openjdk:8-jdk

COPY ./ /strada_application
WORKDIR /strada_application

CMD ["chmod", "+x", "./gradlew"]
CMD ["./gradlew", "bootRun"]