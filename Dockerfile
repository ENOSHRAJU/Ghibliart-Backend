FROM maven:3.4.11-eclipse-temurin-21 AS BUILDER

WORKDIR /app

COPY pom.xml .
RUN mvn dependency:go-offline -B

COPY src ./src
RUN mvn clean package -DskipTests

FROM openjdk:21-jdk as runner

WORKDIR /app

COPY --from=builder ./app/target/appname.0.0.1-SNAPSHOT.jar ./app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]