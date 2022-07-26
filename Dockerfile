FROM openjdk:11

EXPOSE 8080

RUN mkdir app

COPY build/libs/gif-service-1.0.jar app

CMD java -jar app/gif-service-1.0.jar
