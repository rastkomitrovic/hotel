FROM maven:3.8.4-eclipse-temurin-17-alpine

WORKDIR /app

EXPOSE 9099

COPY  . .

RUN mvn clean verify

ENTRYPOINT ["java","-jar","/app/target/hotel-0.0.1-SNAPSHOT.jar"]