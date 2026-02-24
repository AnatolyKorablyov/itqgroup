FROM eclipse-temurin:17.0.16_8-jre-jammy
EXPOSE 8080
COPY target/*.jar app.jar
ENTRYPOINT ["java","-Xmx512m","-Xms128m","-XX:+UseG1GC","-Dspring.aot.enabled=true","-jar","/app.jar"]
