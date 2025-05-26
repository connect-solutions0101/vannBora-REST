FROM maven:3.9.6-eclipse-temurin-17 AS builder
WORKDIR /build
COPY . .
RUN mvn clean package -DskipTests

FROM openjdk:17-jdk-slim
WORKDIR /app
COPY --from=builder /build/target/*.jar app.jar
COPY .env .env
EXPOSE 8080
CMD ["java", "-jar", "app.jar"]



# Para buildar a imagem e rodar o container, cole os comandos abaixo no terminal de forma separada:

#1
# sudo docker build -t vannbora/backend:dev .

#2 Se não tiver o container use:
# docker run -it -p 80:80 --name vannbora_backend vannbora/backend:dev

#Se tiver o container use:
#docker start -a vannbora_backend