FROM gradle:7.6.1-jdk17-jammy AS build

COPY --chown=gradle:gradle . /home/gradle/
WORKDIR /home/gradle/

RUN gradle build --no-daemon

EXPOSE 3000

FROM openjdk:17.0.2-jdk

ARG DB_HOST
ENV DB_HOST=$DB_HOST

ARG DB_PASSWORD
ENV DB_PASSWORD=$DB_PASSWORD

ARG DB_USERNAME
ENV DB_USERNAME=$DB_USERNAME

COPY --from=build /home/gradle/build/libs/*-SNAPSHOT.jar ./app.jar

ENTRYPOINT ["java","-jar","/app.jar"]
