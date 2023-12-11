FROM maven:3.6.3-openjdk-8 AS builder

RUN mkdir /build

ADD src /build/src

ADD pom.xml /build

RUN cd /build && mvn -B -ntp package

FROM adoptopenjdk/openjdk8:alpine-jre

COPY --from=builder /build/target/ems-0.0.1-SNAPSHOT.jar /jizhang.jar

EXPOSE 8080

CMD ["java", "-jar", "/jizhang.jar"]
