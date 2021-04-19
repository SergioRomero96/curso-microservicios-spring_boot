FROM openjdk:8
VOLUME /tmp
ADD ./target/service-users-0.0.1-SNAPSHOT.jar service-users.jar
ENTRYPOINT ["java", "-jar", "service-users.jar"]