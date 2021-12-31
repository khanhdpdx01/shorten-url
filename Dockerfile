# Specify base image
FROM openjdk:11-jdk-slim-bullseye AS build-env
LABEL maintainer="khanhdpdx@gmail.com"
EXPOSE 3000
WORKDIR /app
ENV LANG C.UTF-8
COPY ./target/shorten-url.jar .
#CMD ["java", "-jar", "shorten-url.jar"]

FROM gcr.io/distroless/java11-debian11
COPY --from=build-env /app /app
WORKDIR /app
CMD ["shorten-url.jar"]