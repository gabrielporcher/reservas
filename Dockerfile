FROM maven:3.8.2-jdk-11
WORKDIR /app
COPY . .

RUN mvn package
WORKDIR /tmp/log
ENTRYPOINT java -jar /app/target/reservas2-1.0-SNAPSHOT.jar