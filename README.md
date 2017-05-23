# Demo project

This is a simple hello world SpringBoot project for testing stuff with

### Build Artifact

    docker run -it --rm -u $(id -u) -v $(pwd):/src -w /src java:jdk ./mvnw clean verify


### Build Image

    docker build -t demo .


### Run image

    docker run -it --rm -p 8080:8080 demo
