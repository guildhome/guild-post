FROM openjdk:8-jre-alpine
RUN mkdir /usr/guild-post
WORKDIR /usr/guild-post
ADD ./target/guild-post.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-Dspring.profile.active=docker", "-jar", "app.jar"]