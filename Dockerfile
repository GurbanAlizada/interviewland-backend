FROM openjdk:11-jdk-slim AS build

COPY pom.xml mvnw ./
COPY .mvn .mvn
RUN ./mvnw dependency:resolve

COPY src src
RUN ./mvnw package

FROM openjdk:11-jdk-slim
WORKDIR interviewland-backend
COPY --from=build target/*.jar interviewland-backend-release-1.5.jar
ENTRYPOINT ["java", "-jar", "interviewland-backend-release-1.5.jar"]