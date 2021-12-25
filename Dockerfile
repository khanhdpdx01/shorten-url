# Specify base image
FROM maven:3.6.3-jdk-11

LABEL maintainer="khanhdpdx@gmail.com"

EXPOSE 3000

WORKDIR /app

ENV LANG C.UTF-8

COPY ./target/shorten-url.jar .

CMD ["java", "-jar", "shorten-url.jar"]
