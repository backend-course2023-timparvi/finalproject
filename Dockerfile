# Käytä virallista Maven-pohjaa Java-sovelluksen rakentamiseen
FROM maven:3.8.2-jdk-17-slim AS build

# Kopioi pom.xml ja lähdekoodi
COPY pom.xml /home/app
COPY src /home/app/src

# Aseta työskentelyhakemisto
WORKDIR /home/app/

# Rakenna sovellus
RUN mvn clean package -DskipTests

# Käytä virallista OpenJDK-pohjaa suorittaaksesi sovelluksen
FROM openjdk:17-jre-slim

# Kopioi rakennettu JAR-tiedosto edellisestä vaiheesta
COPY --from=build /home/app/target/*.jar /usr/app/app.jar


# Aseta portti, jonka kontti avaa (esim. jos sovellus käynnistyy portissa 8080)
EXPOSE 8080

# Aseta komento, joka ajetaan, kun kontti käynnistetään
ENTRYPOINT ["java", "-jar", "/usr/app/app.jar"]
