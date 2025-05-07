FROM maven:3.9.6-eclipse-temurin-17-alpine AS builder
WORKDIR /build
COPY . .
RUN mvn clean package -DskipTests

FROM openjdk:17-jdk-slim
WORKDIR /app
COPY --from=builder /build/target/*.jar app.jar
EXPOSE 8080
CMD ["java", "-jar", "app.jar"]



# Para buildar a imagem e rodar o container, cole os comandos abaixo no terminal de forma separada:

#1
# sudo docker build -t vannbora/backend:dev .

#2
# docker run -it -p 80:80 --name vannbora_backend vannbora/backend:dev