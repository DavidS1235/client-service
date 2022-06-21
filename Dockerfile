FROM openjdk:8
VOLUME /tmp
EXPOSE 8010
ADD ./target/client-service-0.0.1-SNAPSHOT.jar client-service.jar
ENTRYPOINT ["java", "-jar", "client-service.jar"]