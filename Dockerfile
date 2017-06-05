FROM java:8

ADD . ./src

WORKDIR /src

RUN ./mvnw clean verify

RUN mkdir /app && chmod 777 /app

RUN mv target/demo-0.0.1-SNAPSHOT.jar /app/app.jar

ADD run.sh /run.sh

CMD /run.sh
