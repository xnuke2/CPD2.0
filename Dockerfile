FROM openjdk:21-oracle
ARG JAR_FILE=target/CPD2-0.0.1-SNAPSHOT.jar
WORKDIR /opt/app
COPY ${JAR_FILE} app.jar
ENV DB_USERNAME=server_cpd
ENV DB_PASSWORD=1234
ENV DB_NAME=postgres
ENV DB_HOST=localhost
ENV DB_PORT=5433
ENV APP_PORT=8080
#ENTRYPOINT ["java","-jar","app.jar"]
ENTRYPOINT ["java", "-Dspring.datasource.password=${DB_PASSWORD}", "-Dspring.datasource.username=${DB_USERNAME}", "-Dspring.datasource.url=jdbc:postgresql://${DB_HOST}:${DB_PORT}/${DB_NAME}", "-Dserver.port=${APP_PORT}", "-jar", "app.jar"]