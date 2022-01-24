FROM amazoncorretto:11

# Install on /opt/backend
WORKDIR /opt/backend

# Copy mvnw
COPY .mvn/ .mvn
COPY mvnw pom.xml ./

# Copy sources
COPY src ./src

# Copy frontend
COPY auth-frontend ./auth-frontend

# Expose port 8080
EXPOSE 8080

# Run application
CMD ["./mvnw", "spring-boot:run", "-Dspring.profiles.active=dev"]