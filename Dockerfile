FROM eclipse-temurin:17-jdk
WORKDIR /app
COPY . .
RUN ./gradlew build --no-daemon
CMD ["java", "-jar", "build/libs/seismic-backend-0.0.1-SNAPSHOT.jar"]