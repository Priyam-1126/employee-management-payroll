FROM maven:3.9.11-eclipse-temurin-17 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn -q -DskipTests package

FROM eclipse-temurin:17-jre
WORKDIR /app
COPY --from=build /app/target/employee-management-payroll-system-1.0.0.jar app.jar
ENTRYPOINT ["java","-jar","app.jar"]
