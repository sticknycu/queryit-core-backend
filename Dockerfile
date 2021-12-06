# Defining some macros
ARG MAVEN_VERSION=3
ARG OPENJDK_VERSION=17
# Container image which we are going to use
FROM maven:${MAVEN_VERSION}-openjdk-${OPENJDK_VERSION}

# Directory we are going to work on
WORKDIR .

# Some tests to see if versions are good
RUN java --version
RUN mvn --version

# Copy files from project and move them to container to /src directory
COPY . .