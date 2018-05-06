FROM openjdk:8-jre-alpine
RUN mkdir /usr/guild-post
WORKDIR /usr/guild-post
ADD ./target/guild-post-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]