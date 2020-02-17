# Spring AOP Logging Demo

Demonstrates the use of Spring AOP to implement logging on method and on class level:
* annotate `@AllPublicMethodsTraced` to a class, and a starting (ENTRY) and an ending (RETURN) log statement are produced
* annotate `@PublicMethodDuration` to a public method, and its execution duration is logged

## REST web service
A simple REST web service with two GET actions */acme/{productId}* and */acme/all* serves as test application to demonstrate the two aspects.
Run the Spring Boot application with the built-in Tomcat server.
Note the log level configured in *application.properties*.

## Sample curl requests
Besides a web browser you can use **curl** to invoke the REST API:
* `curl http://localhost:8080/acme/42`
* `curl http://localhost:8080/acme/all`