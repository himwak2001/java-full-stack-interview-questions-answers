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