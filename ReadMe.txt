mvn clean package docker:build
docker run -d --name transaction-service -p 8080:8080 gayankaveenda/transaction-service:latest
