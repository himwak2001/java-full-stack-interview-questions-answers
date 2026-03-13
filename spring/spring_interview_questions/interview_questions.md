**What is Spring Framework?**

- Spring Framework is a lightweight and open-source Java framework used to build enterprise applications easily.
- It helps developers write loosely coupled code so different parts of the application are independent.
- The two core concepts of Spring are:
  - **Dependency Injection (DI):** Spring creates objects and injects dependencies automatically.
  - **Aspect-Oriented Programming (AOP):** Used for cross-cutting concerns like logging, security, transactions.
- Spring can be used in normal Java applications as well as enterprise web applications.
- It provides many modules for different tasks:
  - **Spring MVC:** For building web applications.
  - **Spring JDBC:** simplifies database operations.
  - **Spring Core:** provides DI and IoC container.
  - **Spring AOP:** supports aspect-oriented programming.
- Spring helps to:
  - Reduce boilerplate code.
  - Improve testability.
  - Improve maintainability.


<br><br>

**What are some of the important features of Spring Framework?**

- **Lightweight Framework**
  - Spring is lightweight and adds very little overhead to applications.
  - It can be used in small standalone Java applications as well as large enterprise systems.
- **Dependency Injection (DI) / Inversion of Control (IoC)**
  - Spring automatically creates objects and injects dependencies.
  - This helps in achieving loose coupling between components.
- **Spring IoC Container**
  - Manages Spring Bean lifecycle.
  - Handles configuration, dependency injection, and resource lookup.
  - Example container: `org.springframework.context.ApplicationContext`.
- **Spring MVC Framework**
  - Used to build web applications and REST APIs.
  - Can return JSON and XML responses easily.
- **Transaction Management Support**
  - Spring provides easy transaction management using annotations.
  - Works with databases using JDBC, JPA, Hibernate.


<br><br>

**What is the advantage of using Spring Framework?**

- **Loose Coupling between components**
  - Spring reduces direct dependency between classes.
  - The IoC container creates objects and injects them where required.
- **Easy Unit Testing**
  - Since classes are loosely coupled, we can easily replace real objects with mock objects during testing.
  - This makes unit testing simple and faster.
- **Reduces Boilerplate Code**
  - Spring provides helper classes like org.springframework.jdbc.core.JdbcTemplate.
  - It removes repetitive JDBC code such as opening/closing connections, handling exceptions.
- **Modular Architecture**
  - Spring is divided into many independent modules.
  - Developers can include only required modules.
  - Examples:
    - **Spring Core** → Dependency Injection
    - **Spring MVC** → Web applications
    - **Spring JDBC** → Database operations
    - **Spring Security** → Authentication and authorization
    - This keeps the application lightweight.
- **Wide Technology Support**
  - Supports many Java EE features like transactions, web services, persistence.
  - Integrates easily with Hibernate, JPA, JMS, REST APIs, Microservices.
  - New projects like Spring Boot and Spring Cloud support modern architectures.
- Because of this, Spring acts as a complete ecosystem for Java development.


<br><br>

**What are the important features of Spring 5?**

- **Support for Java 8 and Higher**
  - Spring 5 requires Java 8 or later.
  - Developers can use lambda expressions and streams.
- **Support for Java EE 7 and Servlet 4.0**
  - Spring 5 supports Java EE 7 APIs.
  - It also supports Servlet 4.0, which enables HTTP/2 support for web applications.
- **Improved File Handling using NIO.2**
  - Spring 5 uses Java NIO.2 (`java.nio.file`) APIs for file operations.
  - This improves performance and scalability for file-heavy applications.
- **spring-jcl Logging Bridge**
  - Spring 5 introduced org.springframework:spring-jcl for logging.
  - It provides a single logging abstraction layer.
- **Support for Kotlin, Lombok, Reactor**
  - Spring 5 supports Kotlin programming language.
  - Works well with Lombok to reduce boilerplate code.
  - Supports Reactive libraries like:
    - `reactor.core.publisher.Flux`
    - `reactor.core.publisher.Mono`
    - RxJava
- **Spring WebFlux (Reactive Programming)**
  - Spring 5 introduced org.springframework.web.reactive WebFlux.
  - Used for building reactive and non-blocking web applications.
- **Support for JUnit 5**
  - Spring 5 fully supports org.junit.jupiter.api (JUnit 5).
  - Makes writing modern unit tests easier.
- **Spring Components Index**
  - Spring 5 allows component metadata in META-INF/spring.components.
  - This reduces classpath scanning time and improves startup performance.


<br><br>

**What is Spring WebFlux?**

- Spring WebFlux is a reactive web framework introduced in Spring 5.
- It is used to build asynchronous and non-blocking web applications.
- It is considered an alternative to Spring MVC when we need high scalability and reactive systems.
- It follows the Reactive Programming model, where applications process data as streams instead of waiting for complete results.
- N**on-Blocking and Asynchronous Processing**
  - In traditional Spring MVC, each request blocks a thread until response is ready.
  - In WebFlux, requests are handled without blocking threads, improving performance for high traffic systems.
  ```mermaid
  flowchart LR
    A[Client Request] --> B[Event Loop]
    B --> C[Reactive Processing]
    C --> D[Flux / Mono Stream]
    D --> E[Response to Client]
  ```
  - WebFlux uses an event-loop model to handle many requests with fewer threads.
  - It returns results using reactive streams.
- **Uses Reactive Types (Mono and Flux)**
  - `reactor.core.publisher.Mono` → represents 0 or 1 result.
  - `reactor.core.publisher.Flux` → represents 0 to many results (stream of data).
- **Event Loop Execution Model**
  ```mermaid
  flowchart TD
    A[Incoming Requests] --> B[Event Loop Thread]
    B --> C[Non Blocking Processing]
    C --> D[Return Reactive Stream]

  ```
  - A small number of threads handle many requests.
  - Threads are not blocked while waiting for I/O operations.
- **Supported Servers**
  - WebFlux can run on multiple reactive servers such as:
    - `org.springframework.http.server.reactive.ReactorHttpHandlerAdapter` (Netty)
    - Tomcat
    - Jetty
    - Undertow
- **When to use WebFlux**
  - Applications with very high concurrent users.
  - Streaming applications.
  - Microservices communication with reactive APIs.
- For simple CRUD applications, Spring MVC is usually sufficient.


<br><br>

**What do you understand by Dependency Injection?**

- Dependency Injection (DI) is a design pattern where the dependency of a class is provided by an external source instead of creating it inside the class.
- It helps in removing hard-coded dependencies and makes the application loosely coupled.
- In Spring, the IoC container (`org.springframework.context.ApplicationContext`) creates objects and injects the required dependencies at runtime.
- **Without Dependency Injection (Tight Coupling)**
  - The class creates its dependency itself using `new`.
  - This makes the code hard to modify and test.
  ```java
  // Tight Coupling Example (Without DI)

  public class UserService {

      // Hard-coded dependency
      private UserRepository repository = new UserRepository();

      public void getUser() {
          repository.findUser();
      }
  }

  class UserRepository {
      public void findUser() {
          System.out.println("Fetching user from DB");
      }
  }
  ```
  - `UserService` is directly dependent on `UserRepository`.
  - Changing implementation becomes difficult.
- **With Dependency Injection (Loose Coupling)**
  - Dependency is provided by Spring container instead of creating it manually.
  ```java
  // Dependency Injection using Constructor Injection

  @org.springframework.stereotype.Service
  public class UserService {

      private final UserRepository repository;

      // Dependency injected by Spring
      public UserService(UserRepository repository) {
          this.repository = repository;
      }

      public void getUser() {
          repository.findUser();
      }
  }

  @org.springframework.stereotype.Repository
  class UserRepository {
      public void findUser() {
          System.out.println("Fetching user from DB");
      }
  }
  ```
  - Spring creates objects and injects dependencies automatically.
- **Dependency Resolution**
  - In normal code → dependency is decided at compile time.
  - With DI → dependency is resolved at runtime by Spring container.
- **Benefits of Dependency Injection**
  - **Loose Coupling** → classes are independent.
  - **Separation of Concerns** → object creation and business logic are separated.
  - **Less Boilerplate Code** → framework manages object creation.
  - **Easy Unit Testing** → dependencies can be replaced with mock objects.
  - **Configurable Components** → implementations can be changed easily without modifying business logic.


<br><br>

**How do you implement DI in Spring Framework?**

- Dependency Injection (DI) in Spring can be implemented using two main approaches:
  - XML-based configuration
  - Annotation-based configuration
- In both cases, the Spring IoC container (`org.springframework.context.ApplicationContext`) creates objects and injects dependencies.
- XML-Based Dependency Injection
  - Dependencies are configured in an XML configuration file.
  - Spring reads the XML file and injects dependencies at runtime.
  ```xml
  <!-- beans.xml configuration file -->
  <beans xmlns="http://www.springframework.org/schema/beans">

      <!-- Defining repository bean -->
      <bean id="userRepository" class="com.example.repository.UserRepository"/>

      <!-- Injecting dependency into service -->
      <bean id="userService" class="com.example.service.UserService">
          <constructor-arg ref="userRepository"/>
      </bean>

  </beans>
  ```
  ```java
  // Service class
  package com.example.service;

  public class UserService {

      private UserRepository repository;

      // Constructor for dependency injection
      public UserService(UserRepository repository) {
          this.repository = repository;
      }
  }
  ```
  ```java
  // Loading Spring container
  org.springframework.context.ApplicationContext context =
          new org.springframework.context.support.ClassPathXmlApplicationContext("beans.xml");

  UserService service = context.getBean(UserService.class);
  ```
  - XML configuration defines beans and their dependencies.
  - Spring container creates objects and injects them.
- Annotation-Based Dependency Injection
  - Dependencies are injected using annotations instead of XML.
  - This is the most commonly used approach today.
  ```java
  // Repository class
  @org.springframework.stereotype.Repository
  public class UserRepository {

      public void findUser() {
          System.out.println("Fetching user from database");
      }
  }
  ```
  ```java
  // Service class with dependency injection
  @org.springframework.stereotype.Service
  public class UserService {

      private final UserRepository repository;

      // Constructor injection
      @org.springframework.beans.factory.annotation.Autowired
      public UserService(UserRepository repository) {
          this.repository = repository;
      }
  }
  ```
  ```java
  // Configuration class
  @org.springframework.context.annotation.Configuration
  @org.springframework.context.annotation.ComponentScan(basePackages = "com.example")
  public class AppConfig {
  }
  ```
  ```java
  // Loading Spring container

  org.springframework.context.ApplicationContext context =
          new org.springframework.context.annotation.AnnotationConfigApplicationContext(AppConfig.class);

  ```
  - Annotations like `@Service`, `@Repository`, and `@Autowired` help automatically detect and inject beans.
- **Common Types of DI used in Spring**
  - **Constructor Injection** → dependency passed through constructor (recommended).
  - **Setter Injection** → dependency injected through setter method.
  - **Field Injection** → dependency injected directly into field using @Autowired.