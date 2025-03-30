##To Run Without Docker in anywhere

#You MUST have JDK 21 or above (23 or 24 preferred)
https://docs.aws.amazon.com/corretto/latest/corretto-24-ug/downloads-list.html
Maven version 3.9.x or above (due to docker goals)

#Open CMD LINE/POWERSHELL/BASH then type the following.
#select your choice of directory. CD into directory

git clone https://github.com/gayankaveenda/transaction-service.git
mvn clean install
java -jar .\target\transaction-service-0.0.1-SNAPSHOT.jar


##To Run In Docker
mvn clean package docker:build
docker run -d --name transaction-service -p 8080:8080 gayankaveenda/transaction-service:latest



#How to Test
when you load the container or run the jar it will
automatically populate product and customer data that is defined in the assignment.

go to resource/postman, and copy the postman collection to your postman.
This allows you to comprehensive testing suite.

You have all the apis required for customer, product and transactions
Also the requested reporting apis

I have also given an option to add 1M or any amount of records using a bulk insert api
in postman called "bulk-insert-automatic-1M-transactions"

this will generate 1M records, or as much as you like.

#Reports
you can access all report apis under reporting.

#Security
most of the APIs will contain the API_KEY. without this token you will not be able to invoke the api

#Error and Exceptions
All requested error scenarios are handled.

#Comments
I have used java 21 features such as virtual threading to speed up the bulk inserts.
I have used native SQL and joins using spring built in support with JPA to speed up the reporting
I have used the Global level exception handling so all controllers will have similar error messaging
All validations are done via custom annotation so that reuseability is the key
Security can be done in many ways. I have used a static token,
but shown that filter chain is capable of selecting which rest service can be chosen for secuirty.
I have included security for product and transaction apis to demonstrate that.

I used H2, and most basic form of security to keep it simple (KISS Concept)

In the production, I would have used Discovery services, Load Balancers, Postgres, Rabbit or Kafka.
