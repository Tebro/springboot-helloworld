FROM java:8

ADD . ./src

WORKDIR /src

RUN ./mvnw clean verify

RUN mv target/demo-0.0.1-SNAPSHOT.jar /app.jar

CMD java -jar /app.jar
