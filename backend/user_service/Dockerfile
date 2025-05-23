# Sử dụng image Maven chính thức để build
FROM maven:3.9.6-eclipse-temurin-17 AS build

# Tạo thư mục làm việc
WORKDIR /app

# Copy toàn bộ mã nguồn vào container
COPY . .

# Chạy lệnh build
RUN mvn clean package -DskipTests

# -----------------------

# Dùng JDK nhẹ để chạy ứng dụng
FROM eclipse-temurin:17-jdk-jammy

WORKDIR /app

# Copy file JAR đã build từ stage trước
COPY --from=build /app/target/*.jar app.jar

EXPOSE 9091

# Chạy app
ENTRYPOINT ["java", "-jar", "app.jar"]
