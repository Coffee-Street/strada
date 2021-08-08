FROM openjdk:11-jdk
LABEL maintainer="wnsgml972@gmail.com"

COPY api/build/libs/api*.jar /strada/service.jar
WORKDIR /strada
ENTRYPOINT java ${JAVA_OPTIONS} -jar service.jar
