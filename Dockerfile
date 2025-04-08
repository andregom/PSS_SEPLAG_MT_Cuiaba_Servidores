# Etapa 1: build
FROM maven:3.9.6-eclipse-temurin-21 AS build

WORKDIR /app

# Copia o POM e resolve dependências antecipadamente (melhora cache)
COPY pom.xml .

RUN mvn dependency:go-offline -B

# Copia o restante do código do projeto
COPY . .

# Compila o projeto, sem rodar os testes
RUN mvn clean package -DskipTests

# Etapa 2: runtime
FROM eclipse-temurin:21-jdk

WORKDIR /app

# Copia o .jar da etapa anterior
COPY --from=build /app/target/servidores*.jar app.jar

# Expõe a porta padrão do Spring Boot
EXPOSE 8080

# Comando para iniciar o app
ENTRYPOINT ["java", "-jar", "app.jar"]
