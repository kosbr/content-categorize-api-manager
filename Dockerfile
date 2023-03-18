FROM openjdk:17-jdk-alpine
COPY build/libs/categorize-api.jar /deployments/categorize-api.jar
USER 185
ENV JAVA_OPTS="-Xmx512m"
EXPOSE 8082
ENTRYPOINT ["java","-jar","/deployments/categorize-api.jar"]