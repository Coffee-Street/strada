FROM openjdk:11-jdk

COPY api/build/libs/api*.jar /strada/service.jar
WORKDIR /strada
ENTRYPOINT java -jar -Dspring.profiles.active=prod service.jar

#TODO:
## Stage 1: Cache build dependencies
#FROM docker repo url
#RUN mkdir -p /home/gradle/cache
#ENV GRADLE_USER_HOME /home/gradle/cache
#
#WORKDIR /home/gradle/project
#
## COPY with glob is not supported yet.
## https://github.com/moby/moby/issues/15858
## https://github.com/moby/moby/issues/29211
#COPY build.gradle.kts settings.gradle.kts gradle.properties ./
#COPY buildSrc buildSrc
#COPY core/build.gradle.kts core/build.gradle.kts
#COPY domain/build.gradle.kts domain/build.gradle.kts
#COPY infrastructure/build.gradle.kts infrastructure/build.gradle.kts
#COPY message/build.gradle.kts message/build.gradle.kts
#COPY webapi/build.gradle.kts webapi/build.gradle.kts
#
## Just get build dependencies
#RUN gradle build -x bootJar -i --stacktrace
#
## Stage 2: Build a project with cached dependencies
#FROM docker-com AS builder
#COPY --from=cache /home/gradle/cache /home/gradle/.gradle
#WORKDIR /home/gradle/project
#COPY . .
#RUN gradle bootJar -x test -i --stacktrace
#
## Stage 3: Move build artifacts to the service directory
#COPY --from=builder /home/gradle/project/webapi/build/libs/webapi-*.jar /opt/hpcnt/service.jar
#ENTRYPOINT java ${JAVA_OPTIONS} -jar service.jar