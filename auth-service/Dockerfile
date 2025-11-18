FROM eclipse-temurin:25-jdk-alpine AS builder

RUN apk add --no-cache curl

WORKDIR /app

COPY .mvn/ .mvn
COPY mvnw pom.xml ./

RUN chmod +x mvnw

RUN ./mvnw dependency:resolve

COPY src ./src

RUN ./mvnw package -DskipTests


FROM eclipse-temurin:25-jre-alpine

WORKDIR /app
COPY --from=builder /app/target/*.jar app.jar
EXPOSE 8081
ENTRYPOINT ["java", "-jar", "app.jar"]