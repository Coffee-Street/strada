# build stage
From openjdk:11-jdk AS builder

COPY gradlew /strada/
COPY gradle /strada/gradle
COPY gradle.properties /strada/gradle.properties
COPY build.gradle.kts /strada/build.gradle.kts
COPY settings.gradle.kts /strada/settings.gradle.kts
COPY api /strada/api
COPY buildSrc /strada/buildSrc

WORKDIR /strada

RUN chmod +X ./gradlew
RUN ./gradlew build && ./gradlew tasks --all

# deploy stage
FROM openjdk:11-jdk

# COPY api/build/libs/api*.jar /strada/service.jar
COPY --from=builder strada/api/build/libs/api*.jar /strada/service.jar
WORKDIR /strada
ENTRYPOINT java -jar -Dspring.profiles.active=prod service.jar
