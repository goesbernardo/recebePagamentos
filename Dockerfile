FROM openjdk:17
EXPOSE 8081
COPY /target/recebepagamentos-0.0.1-SNAPSHOT.jar /app/recebepagamentos.jar
WORKDIR /app
ENTRYPOINT ["java", "-jar" , "/app/recebepagamentos.jar"]