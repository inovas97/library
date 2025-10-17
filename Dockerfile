# =========================
# 1. Build stage
# =========================
FROM maven:3.9.6-amazoncorretto-21 AS builder

WORKDIR /build
COPY pom.xml .
COPY src ./src

RUN mvn clean package

# =========================
# 2. Runtime stage
# =========================
FROM payara/micro:6.2025.10-jdk21

# Copy built WAR to Payara's deployments folder
COPY --from=builder /build/target/Library-1.0-SNAPSHOT.war /opt/payara/deployments/

# Optional: expose port (default 8080)
EXPOSE 8080

# Run Payara Micro and deploy WAR automatically
ENTRYPOINT ["java", "-jar", "/opt/payara/payara-micro.jar", "--deploy", "/opt/payara/deployments/Library-1.0-SNAPSHOT.war", "--nocluster", "--contextroot", "/"]
