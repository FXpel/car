## TP4 - Book library

This project is a lightweight and easy-to-use skeleton to create a JEE application that uses [Apache TomEE](http://openejb.apache.org/apache-tomee.html), a complete JEE server based on Tomcat.

Web application for the sale and administration of books, authors and orders in Java (J2E).

Philippot Grégoire
Pelage Fraçois-Xavier

05/05/2020

### Introduction

During this project we set up a web applciation (with J2E standard) allowing the management of book stocks, the management of authors and book orders for any user.

### Structure

  * `main/java/car/tp4/entity`

    Contains all entities (EJB) (`Book` entity, `BookBean` bean examples).

  * `main/java/car/tp4/managers`

    Contains the class allowing the management of the project

  * `main/java/car/tp4/servlet`

    Contains all servlets (`BookServlet` example).

  * `resources/META-INF`

    Contains all the configuration files for the deployment.
    `persistence.xml` declares how to persist the app beans.
    We have to write a `persistence-unit` for each entity of the application.

  * `webapp/jsp`

  Contains all the jsp files, excepts the index.

  * `webapp/WEB-INF`

  Contains all the configuration files for the web application.

### How to?

To build the application and to start the server:
```
mvn clean package tomee:run
```

Once started, the application is now reachable at:
```
http://localhost:8080
```

A Servlet and a JSP file is available for testing at:
```
http://localhost:8080/books
```

When developing, all the static resources (html, css, jsp) are automatically re-deployed on the server (in few seconds).

For the Java class, you can open a new terminal (without to stop the server), and package the application (`mvn package`) for a new automatic redeployment.

To clean all data and remove the application, use `mvn:clean`.

To create class documentation, use `mvn javadoc:javadoc`.

To run Junit tests, use `mvn test`.

### Some code examples

### Retrieve the information of an entity by its ID
```java
  public Author findAuthorById(long id) {
    Query query = this.entityManager.createQuery("SELECT a FROM Author AS a WHERE a.id = :id").setParameter("id", id);
    return (Author) query.getSingleResult();
  }
```
