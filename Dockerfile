FROM openjdk:11-jdk

COPY api/build/libs/api*.jar /strada/service.jar
WORKDIR /strada
ENTRYPOINT java -jar --Dspring.profiles.active=feature service.jar
