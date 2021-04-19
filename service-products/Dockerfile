FROM openjdk:8
VOLUME /tmp
ADD ./target/service-products-0.0.1-SNAPSHOT.jar service-products.jar
ENTRYPOINT ["java", "-jar", "service-products.jar"]