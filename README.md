# Tracks Suggestion
A simple Java Spring boot backend with Spotify and Open Weather Map integration.

## Architecture
The app is a microservice and was build using Jhipster code generator. Bellow is a simple explanation of the two different applications in the repository:

- The JHipster Registry: it ties all the other components together and enables them to communicate with each other.

- The Microservice Application: contains the back-end code. Once running it will expose the API for the domain it is concerned with.

![alt text](http://www.baeldung.com/wp-content/uploads/2017/05/JHipster-Microservice-Architecture.png)

To this specific repository there is no Gateway application.

For more informations: [JHipster with a Microservice Architecture][].

[JHipster with a Microservice Architecture]: http://www.baeldung.com/jhipster-microservices

## Running the Application

To test the app we need to run both applications, Jhipster Registry first and then the Tracks microservice application. To do so, just run:

**./mvnw** 

inside the respectively folders.
