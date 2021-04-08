FROM openjdk:8
VOLUME /app/log
ADD target/drug-product-microservice-resourse-server-0.0.1-SNAPSHOT.jar drug-product-microservice-resourse-server-0.0.1-SNAPSHOT.jar
EXPOSE 8085
ENTRYPOINT ["java", "-jar", "drug-product-microservice-resourse-server-0.0.1-SNAPSHOT.jar"]
