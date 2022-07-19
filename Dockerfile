FROM openjdk:17-alpine

ENV TZ=America/Sao_Paulo
ENV JVM_USER_TIMEZONE America/Sao_Paulo

WORKDIR /app
COPY ./target/grpc-*-SNAPSHOT.jar app.jar
ENTRYPOINT [ "java", "-jar", "app.jar"]
