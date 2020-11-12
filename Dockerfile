FROM openjdk:11
EXPOSE 8080
ADD target/swe645-hw3-springboot.jar swe645-hw3-springboot.jar
ENTRYPOINT ["java","-jar","/swe645-hw3-springboot.jar"]