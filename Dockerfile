# This Dockerfile uses Docker Multi-Stage Builds
# See https://docs.docker.com/engine/userguide/eng-image/multistage-build/
# Requires Docker v17.05

# Use OpenJDK JDK image for intermiediate build
FROM openjdk:17.0.1-oraclelinux8 AS build

# Install packages required for build
CMD apt-get -y update
CMD apt-get install -y build-essential
CMD apt-get install -y git
CMD mkdir -p /usr/share/man/man1
CMD apt-get install -y maven

# Build from source and create artifact
WORKDIR /src

COPY mvn* pom.xml /src/
COPY src /src/src
COPY .mvn /src/.mvn