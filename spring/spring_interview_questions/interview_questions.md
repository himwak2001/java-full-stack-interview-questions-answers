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


<br><br>

**Name some of the important Spring Modules.**

- Spring Framework is divided into multiple modules, each designed for a specific purpose.
- Developers can use only the modules they need, which keeps the application lightweight and flexible.
- **Spring Core / Spring Context Module**
  - Provides Dependency Injection (DI) and IoC container.
  - Responsible for creating and managing beans.
  - `org.springframework.context.ApplicationContext`
  ```java
  // Creating Spring IoC container

  org.springframework.context.ApplicationContext context =
          new org.springframework.context.annotation.AnnotationConfigApplicationContext(AppConfig.class);
  ```
  - The container creates objects and injects dependencies automatically.
- **Spring AOP Module**
  - Provides support for Aspect-Oriented Programming.
  - Used for cross-cutting concerns like:
    - Logging
    - Security
    - Transaction management
- **Spring DAO Module**
  - Provides DAO (Data Access Object) abstraction layer.
  - Simplifies database exception handling.
  - Converts database exceptions into Spring’s consistent exception hierarchy.
  - Example:
    - `org.springframework.dao.DataAccessException`. This helps in handling database errors easily.
- **Spring JDBC Module**
  - Simplifies JDBC database operations.
  - Provides `org.springframework.jdbc.core.JdbcTemplate` to remove boilerplate code.
  ```java
  // Using JdbcTemplate for database query

  @org.springframework.beans.factory.annotation.Autowired
  private org.springframework.jdbc.core.JdbcTemplate jdbcTemplate;

  public int countUsers() {
      return jdbcTemplate.queryForObject(
          "SELECT COUNT(*) FROM users",
          Integer.class
      );
  }

  ```
  - `JdbcTemplate` removes repetitive code like opening/closing connections and exception handling.
- **Spring ORM Module**
  - Provides integration with ORM frameworks such as:
    - Hibernate
    - JPA
    - MyBatis
  - Examples:
    - `org.springframework.orm.jpa.JpaTransactionManager`. Helps manage transactions and session handling easily.
- **Spring Web Module**
  - Provides basic support for web-based applications.
  - Includes features such as:
    - File upload
    - Web application context
    - Integration with web frameworks
  - Examples:
    - `org.springframework.web.context.WebApplicationContext`
- **Spring MVC Module**
  - Provides Model-View-Controller architecture for building web applications and REST APIs.
  - Separates:
    - **Model** → business logic
    - **View** → UI
    - **Controller** → request handling
  ```mermaid
  flowchart LR
    A[Client Request] --> B[Controller]
    B --> C[Service / Model]
    C --> D[View or JSON Response]
  ```


<br><br>

**What do you understand by Aspect-Oriented Programming?**

- **Aspect-Oriented Programming (AOP)** is a programming technique used to separate cross-cutting concerns from business logic.
- Cross-cutting concerns are functionalities that are required in multiple parts of the application, such as:
  - Logging
  - Transaction management
  - Security
  - Authentication
  - Data validation
- In Object-Oriented Programming (OOP), modularity is achieved using classes.
- In AOP, modularity is achieved using Aspects.
- **Problem without AOP**
  - Cross-cutting logic (like logging) must be called manually in every class.
  - This creates tight coupling and code duplication.
  ```java
  // Without AOP (Logging inside business logic)

  public class UserService {

      public void createUser() {
          System.out.println("Logging: createUser method called"); // logging code
          System.out.println("Creating user...");
      }
  }
  ```
  - Logging code is mixed with business logic.
- **Solution with AOP**
  - Logging logic is written in a separate Aspect class.
  - Spring automatically executes it when the target method runs.
  ```mermaid
  flowchart LR
    A[Client Call] --> B[Aspect - Logging]
    B --> C[Business Method]
  ```
  - Aspect runs before or after the business method automatically.
  - Business classes remain clean and focused on logic.
- **Example of Spring AOP**
  ```java
  // Aspect class

  @org.aspectj.lang.annotation.Aspect
  @org.springframework.stereotype.Component
  public class LoggingAspect {

      // Advice executed before method
      @org.aspectj.lang.annotation.Before("execution(* com.example.service.*.*(..))")
      public void logBefore() {
          System.out.println("Logging before method execution");
      }
  }
  ```
  ```java
  // Business class

  @org.springframework.stereotype.Service
  public class UserService {

      public void createUser() {
          System.out.println("User created");
      }
  }
  ```
  - The logging method runs automatically before service methods.
- **Flow:**
  ```mermaid
  flowchart TD
    A[Client Request] --> B[Spring AOP Proxy]
    B --> C[Logging Aspect]
    C --> D[Business Method Execution]
  ```
  - Spring creates a proxy around the target object.
  - The proxy executes aspects before/after the actual method.


<br><br>

**What is Aspect, Advice, Pointcut, JointPoint and Advice Arguments in AOP?**

- **Aspect:**
  - An Aspect is a class that contains cross-cutting logic such as logging, security, or transaction management.
  - It is applied to different parts of the application without modifying business classes.
  - In Spring, a class becomes an aspect using the annotation `@org.aspectj.lang.annotation.Aspect`.
  ```java
  // Aspect class

  @org.aspectj.lang.annotation.Aspect
  @org.springframework.stereotype.Component
  public class LoggingAspect {

      // advice will be written here
  }
  ```
  - The aspect class contains advice methods that run automatically.
- **Advice**
  - Advice is the actual action executed when a joint point is reached.
  - It defines what should happen when a specific method is executed.
  - **Common advice types:**
    - **`@Before`** → runs before method execution
    - **`@After`** → runs after method execution
    - **`@AfterReturning`** → runs after successful execution
    - **`@AfterThrowing`** → runs when exception occurs
    - **`@Around`** → runs before and after method execution
  ```java
  // Advice example

  @org.aspectj.lang.annotation.Before("execution(* com.example.service.*.*(..))")
  public void logBeforeMethod() {
      System.out.println("Logging before method execution");
  }
  ```
  - This advice runs before any method in the service package.
- **Pointcut**
  - A Pointcut defines where the advice should be applied.
  - It uses AspectJ expression syntax to match specific methods.
  ```java
  // Pointcut expression

  @org.aspectj.lang.annotation.Pointcut(
      "execution(* com.example.service.*.*(..))"
  )
  public void serviceMethods() {}
  ```
  - The pointcut selects all methods inside `com.example.service` package.
- **Join Point**
  - A Join Point is a specific point in program execution.
  - Example:
    - Method execution
    - Exception handling
    - Object field modification
  - In Spring AOP, a join point is always method execution.
  ```java
  // JoinPoint object example

  @Before("execution(* com.example.service.*.*(..))")
  public void logMethod(JoinPoint joinPoint) {

      // Getting method name
      String methodName = joinPoint.getSignature().getName();

      System.out.println("Executing method: " + methodName);
  }
  ```
  - `org.aspectj.lang.JoinPoint` provides method details at runtime.
- **Advice Arguments**
  - Advice methods can receive parameters from the target method.
  - This is done using the `args()` expression in pointcut.
  ```java
  // Target class

  @Service
  public class UserService {

      public void createUser(String name) {
          System.out.println("Creating user: " + name);
      }
  }
  ```
  ```java
  // Advice receiving argument

  @Before(
      "execution(* com.example.service.UserService.createUser(..)) && args(name)"
  )
  public void logUser(String name) {
      System.out.println("User name passed: " + name);
  }

  ```
  - The `args(name)` expression captures the method argument.
- **Flow**
  ```mermaid
  flowchart LR
    A[Client Call] --> B[Join Point - Method Execution]
    B --> C[Pointcut Matches Method]
    C --> D[Advice Executes]
    D --> E[Business Method Runs]
  ```
  - **Join point** → location where method runs.
  - **Pointcut** → rule that selects the join point.
  - **Advice** → action executed at that point.


<br><br>

**What is Spring IOC Container?**

- **Spring IoC Container** is the core component of the Spring Framework responsible for creating, configuring, and managing objects (beans).
- It implements the **Inversion of Control (IoC)** principle, where:
  - Object creation and dependency management are handled by the container instead of the application code.
- The container injects dependencies into objects at runtime, making the application loosely coupled and flexible.
- **Main Responsibilities of Spring IoC Container**
  - Create and manage Spring Beans.
  - Inject dependencies between beans.
  - Manage bean lifecycle.
  - Provide configuration through XML, annotations, or Java classes.
  - Example package locations:
    - `org.springframework.beans`
    - `org.springframework.context`
- **Flow**
  ```mermaid
  flowchart LR
    A[Configuration Metadata<br>XML / Annotation / Java Config] --> B[Spring IoC Container]
    B --> C[Create Beans]
    B --> D[Inject Dependencies]
    B --> E[Manage Bean Lifecycle]
    C --> F[Application Uses Beans]
  ```
  - The container reads configuration metadata.
  - The container reads configuration metadata.
- **Common IoC Container Implementations**
  - **`org.springframework.context.annotation.AnnotationConfigApplicationContext`**
    - Used for standalone Java applications with annotation-based configuration.
    ```java
    org.springframework.context.ApplicationContext context =
        new org.springframework.context.annotation.AnnotationConfigApplicationContext(AppConfig.class);
    ```
  - **`org.springframework.context.support.ClassPathXmlApplicationContext`**
    - Used when configuration is defined in XML files located in the classpath.
    ```java
    org.springframework.context.ApplicationContext context =
        new org.springframework.context.support.ClassPathXmlApplicationContext("beans.xml");
    ```
  - **`org.springframework.context.support.FileSystemXmlApplicationContext`**
    - Similar to `ClassPathXmlApplicationContext`, but XML configuration file can be loaded from any location in the file system.
    ```java
    org.springframework.context.ApplicationContext context =
        new org.springframework.context.support.FileSystemXmlApplicationContext("config/beans.xml");
    ```
  - **Web Application Containers**
    - Used for Spring-based web applications.
    - Examples:
      - `org.springframework.web.context.support.AnnotationConfigWebApplicationContext`
      - `org.springframework.web.context.support.XmlWebApplicationContext`
      - These containers integrate Spring with web servers like Tomcat.



<br><br>

**What is a Spring Bean?**

- A Spring Bean is a normal Java object that is created and managed by the Spring IoC Container.
- When the Spring container (`org.springframework.context.ApplicationContext`) creates an object, that object is called a Spring Bean.
- Spring beans are used throughout the application and dependencies between beans are automatically injected by the container.
- **How a Spring Bean is Created**
  - Define a class.
  - Mark it with Spring annotations or configure it in XML.
  - The Spring IoC container initializes and manages it.
  ```java
  // A simple Spring Bean

  @Service
  public class UserService {

      public void getUser() {
          System.out.println("User fetched");
      }
  }

  ```
  - The UserService class becomes a Spring Bean because Spring container manages it.
- **Getting a Spring Bean from IoC Container**
  ```java
  // Creating Spring IoC container

  org.springframework.context.ApplicationContext context =
          new org.springframework.context.annotation.AnnotationConfigApplicationContext(AppConfig.class);

  // Getting bean instance
  UserService userService = context.getBean(UserService.class);

  // Calling method
  userService.getUser();

  ```
  - `getBean()` method retrieves the Spring Bean from the container.
- **Flow**
  ```mermaid
  flowchart LR
    A[Java Class] --> B[Spring IoC Container]
    B --> C[Spring Bean Created]
    C --> D[Dependency Injection]
    D --> E[Application Uses Bean]

  ```
  - Spring container creates and manages bean objects.
  - Dependencies between beans are automatically injected.


<br><br>

**What is the importance of Spring bean Configuration file?**

- Spring Bean Configuration File is used to define and configure Spring Beans that will be managed by the Spring IoC container.
- It tells the Spring container which classes to create as beans and how their dependencies should be injected.
- When the Spring container (`org.springframework.context.ApplicationContext`) starts, it reads this configuration file and initializes all beans.
- **Example: Spring Bean XML Configuration**
  ```xml
  <!-- beans.xml -->

  <beans xmlns="http://www.springframework.org/schema/beans">

      <!-- Defining repository bean -->
      <bean id="userRepository" class="com.example.repository.UserRepository"/>

      <!-- Injecting dependency into service -->
      <bean id="userService" class="com.example.service.UserService">
          <constructor-arg ref="userRepository"/>
      </bean>

  </beans>
  ```
  - `userRepository` and `userService` are Spring Beans defined in XML.
- **Loading the Configuration File**
  ```java
  // Loading Spring container using XML configuration

  org.springframework.context.ApplicationContext context =
          new org.springframework.context.support.ClassPathXmlApplicationContext("beans.xml");

  // Getting bean from container
  UserService service = context.getBean(UserService.class);

  ```
  - The container reads the XML file and creates beans automatically.
- **Flow**
  ```mermaid
  flowchart LR
    A[beans.xml Configuration] --> B[Spring ApplicationContext]
    B --> C[Create Beans]
    B --> D[Inject Dependencies]
    C --> E[Beans Ready to Use]
  ```
  - Spring reads bean definitions from XML.
  - Then it creates and manages bean objects.