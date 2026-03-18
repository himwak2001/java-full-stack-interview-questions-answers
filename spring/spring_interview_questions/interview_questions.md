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


<br><br>

**What are different ways to configure a class as a Spring Bean?**

1. **XML Configuration**
   - Uses the `<bean>` tag within an XML file.
   - Useful for legacy projects or when you don't have access to the library's source code to add annotations.
   - **Example:**
      ```xml
      <bean id="myService" class="com.example.services.MyServiceImpl">
          <property name="repository" ref="myRepository" />
      </bean>
      ```
2. **Java-Based Configuration**
   - Type-safe and allows for programmatic logic during bean creation (e.g., `if-else` blocks for different environments).
   - Centralizes configuration in a few Java classes rather than scattered XML files.
   - **Example:**
      ```java
      package com.example.config;

      import org.springframework.context.annotation.Bean;
      import org.springframework.context.annotation.Configuration;

      @Configuration
      public class AppConfig {
          @Bean
          public MyService myService() {
              // Programmatic instantiation
              return new MyServiceImpl();
          }
      }
      ```
3. **Annotation-Based (Component Scan)**
   - The most common modern approach; uses "Stereotypes" to mark classes as beans.
   - Requires `org.springframework.context.annotation.ComponentScan` to tell Spring where to look.
   - **Key Annotations:**
     - `@Component`: Generic bean.
     - `@Service`: Service layer (business logic).
     - `@Repository`: DAO layer (exception translation).
     - `@Controller`: Presentation layer (Web/MVC).
   - **Example:**
      ```java
      package com.example.services;

      import org.springframework.stereotype.Service;

      @Service // Spring detects this automatically via Component Scanning
      public class MyServiceImpl implements MyService {
          // Logic here
      }
      ```
4. **Bean Configuration Workflow**
   ```mermaid
   graph TD
    A[Configuration Source] --> B{Spring Container}
    B --> C[Bean Definition]
    B --> D[Dependency Injection]
    B --> E[Ready-to-use Bean]
    
    subgraph Sources
    S1[XML: bean tag]
    S2[Java: @Bean method]
    S3[Annotation: @Component]
    end
    
    S1 -.-> A
    S2 -.-> A
    S3 -.-> A
   ```
   - **Sources:** Metadata is gathered from XML files, Java classes, or scanned packages.
   - **Container:** org.springframework.context.ApplicationContext processes this metadata to manage the bean lifecycle.
      

<br><br>

**What are the different scopes on Spring Bean?**

- **`singleton` (Default):** Spring creates exactly one instance of the bean per `org.springframework.context.ApplicationContext`. All requests for that bean return the same shared instance.
- **`prototype`:** A new instance is created every single time the bean is requested from the container (e.g., via `getBean()` or injection).
- **`request`:** A new bean instance is created for each HTTP request. Only valid in web-aware Spring contexts.
- **`session`:** A bean instance is created for the lifecycle of an HTTP Session.
- **`application`:** A bean is scoped to the lifecycle of a `javax.servlet.ServletContext` (one instance per web application).
- **`websocket`:** Scoped to the lifecycle of a WebSocket session.
- **Scope Lifecycle Visualization**
  ```mermaid
  graph LR
    subgraph Singleton
    S1[Request A] --> Instance1((Bean Instance))
    S2[Request B] --> Instance1
    end

    subgraph Prototype
    P1[Request A] --> Instance2((New Bean))
    P2[Request B] --> Instance3((New Bean))
    end
    
    subgraph Web_Request
    W1[HTTP Req 1] --> Instance4((Req Bean 1))
    W2[HTTP Req 2] --> Instance5((Req Bean 2))
    end
  ```
  - **Singleton:** Shared instance; efficiency is high but state management requires thread-safety.
  - **Prototype:** Independent instances; Spring handles creation but does not call the destruction lifecycle callbacks.
  - **Request:** Short-lived; strictly tied to the individual user interaction.
- **Code Implementation:**
  ```java
  import org.springframework.context.annotation.Bean;
  import org.springframework.context.annotation.Configuration;
  import org.springframework.context.annotation.Scope;
  import org.springframework.beans.factory.config.ConfigurableBeanFactory;

  @Configuration
  public class ProjectConfig {

      @Bean
      // Using FQCN constant for singleton
      @Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
      public MyService myService() {
          return new MyServiceImpl();
      }

      @Bean
      @Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
      public MyTask myTask() {
          return new MyTask();
      }
  }
  ```


<br><br>

**What is the Spring Bean's life cycle?**

The Spring Bean lifecycle represents the series of steps a bean goes through from its instantiation (creation) to its destruction (cleanup) by the `org.springframework.context.ApplicationContext`.

- **Instantiation:** The container creates an instance of the bean using its constructor.
- **Populate Properties:** Spring injects all defined dependencies (via `@Autowired` or XML setters).
- **Awareness Interfaces:** If the bean implements interfaces like `org.springframework.beans.factory.BeanNameAware`, Spring injects the container's internal metadata.
- **BeanPostProcessor (Before Initialization):**`postProcessBeforeInitialization()` is called for any custom logic before the bean is officially "ready."
- **Initialization Callbacks:**
  - **`@PostConstruct`:** The modern, preferred annotation-based approach.
  - **`InitializingBean`:** Implementing the `afterPropertiesSet()` method.
  - **Custom `init-method`:** Defined in XML or `@Bean(initMethod="...")`.
- **BeanPostProcessor (After Initialization):** `postProcessAfterInitialization()` is called; this is often where AOP Proxies are created.
- **Destruction Callbacks:**
  - **`@PreDestroy`:** Annotation-based cleanup.
  - **`DisposableBean`:** Implementing the destroy() method.
  - **Custom `destroy-method`:** Defined in configuration.
- **Lifecyle Workflow diagram:**
  ```mermaid
  graph TD
    A[1. Instantiation] --> B[2. Populate Properties]
    B --> C[3. Awareness Interfaces]
    C --> D[4. BeanPostProcessor - Pre]
    D --> E[5. Custom Init Methods]
    E --> F[6. BeanPostProcessor - Post]
    F --> G[7. Bean is Ready]
    G --> H[8. Destruction Callbacks]
    
    style A fill:#f9f,stroke:#333
    style G fill:#9f9,stroke:#333
    style H fill:#f66,stroke:#333
  ```
  - **Initialization Phase:** This covers steps 1 through 6, ensuring the bean is fully configured and validated before use.
- **Destruction Phase:** Triggered when the container is closed, allowing for resource cleanup (e.g., closing DB connections).


<br><br>

**How to get `ServletContext` and `ServletConfig` object in a Spring Bean?**

There are two primary ways to access these web-container-specific objects within a Spring bean. Both methods require the application to be running in a Web-aware ApplicationContext.

1. **Using `@Autowired` (Modern Approach)**
   - The simplest and most common method in modern Spring/Spring Boot applications.
   - Spring automatically resolves these types if the bean is managed within a `WebApplicationContext`.
    ```java
    package com.example.controller;

    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.stereotype.Component;
    import javax.servlet.ServletContext;
    import javax.servlet.ServletConfig;

    @Component
    public class WebHelperBean {

        @Autowired
        private ServletContext servletContext; // Global application context

        @Autowired(required = false)
        private ServletConfig servletConfig; // Only available in Controller/Servlet scope

        public void printInfo() {
            System.out.println("Context Path: " + servletContext.getContextPath());
        }
    }
    ```
2. **Implementing Aware Interfaces (Classic Approach)**
   - The bean implements specific interfaces to receive callbacks from the container.
   - This is "Spring-aware," meaning the class becomes explicitly coupled to Spring's API.
    ```java
    package com.example.service;

    import org.springframework.web.context.ServletContextAware;
    import org.springframework.web.context.ServletConfigAware;
    import javax.servlet.ServletContext;
    import javax.servlet.ServletConfig;

    public class LegacyWebBean implements ServletContextAware, ServletConfigAware {

        private ServletContext context;
        private ServletConfig config;

        @Override
        public void setServletContext(ServletContext servletContext) {
            this.context = servletContext; // Spring calls this during bean initialization
        }

        @Override
        public void setServletConfig(ServletConfig servletConfig) {
            this.config = servletConfig;
        }
    }
    ```
3. **Retrieval Mechanism Workflow**
   ```mermaid
   graph LR
    A[Web Container] --> B[ServletContext]
    A --> C[ServletConfig]
    B --> D{Spring Context}
    C --> D
    D --> E[Spring Bean]
    
    style E fill:#fff4dd,stroke:#d4a017
   ```
   - **ServletContext:** Shared across the entire web application (global).
   - **ServletConfig:** Specific to a single `org.springframework.web.servlet.DispatcherServlet` instance.


<br><br>

**What is Bean wiring and @Autowired annotation?**

- The process of creating associations between application components (Beans) within the Spring container. It is essentially Dependency Injection (DI) in action—telling Spring which bean needs which dependency.
- Manual wiring is done via XML `(<property ref="...">)` or Java Config. Auto-wiring allows Spring to resolve and inject collaborating beans into your bean automatically.
- **`@Autowired`:** A Spring annotation used to mark a dependency that the container should fulfill. It primarily works byType. If multiple beans of the same type exist, Spring uses byName or `@Qualifier` to resolve the conflict.
- **Wiring Mechanism Workflow**
  ```mermaid
  graph TD
    A[Bean Definition Registry] --> B{Dependency Match?}
    B -- Yes: Single Match --> C[Inject Bean]
    B -- Yes: Multiple Matches --> D{Check @Qualifier / Name}
    B -- No --> E{Is required=false?}
    D -- Match Found --> C
    D -- No Match --> F[Throw NoUniqueBeanDefinitionException]
    E -- Yes --> G[Leave as Null]
    E -- No --> H[Throw NoSuchBeanDefinitionException]
    
    style C fill:#d4edda,stroke:#28a745
    style F fill:#f8d7da,stroke:#dc3545
    style H fill:#f8d7da,stroke:#dc3545
  ```
  - **Resolution Process:** Spring first looks for a bean of the exact class/interface type.
  - **Fail-Fast:** If Spring cannot find a dependency, it fails at startup unless explicitly told otherwise.
- **Miscellneous Points:**
  - **`required` attribute:** By default, `@Autowired(required = true)`. If set to `false`, Spring will not throw an exception if the bean is missing; the field will simply remain `null`.
  - **Enabling Autowiring:**
    - **In XML:** Use `<context:annotation-config />`.
    - **In Java Config:** Using `@org.springframework.context.annotation.ComponentScan` automatically enables it.
  - **Self-Injection:** Using `@Autowired` on a field of the same class type is generally a "code smell" and can cause circular dependency issues.


<br><br>

**What are different types of Spring Bean autowiring?**

Spring provides several strategies to automatically resolve and inject dependencies. While modern development favors annotations, understanding the XML-based "modes" is crucial for interviews.

1. **`autowire="byName"`**
   - Spring looks for a bean with the exact same ID as the property name in the class.
   - **Example:** If your class has a field `private MyService myService;`, Spring looks for a bean defined with id="myService".
2. **`autowire="byType"`**
   - Spring looks for a bean that matches the Class or Interface type of the property.
   - **Constraint:** If more than one bean of the same type exists, Spring throws a `NoUniqueBeanDefinitionException`.
3. **`autowire="constructor"`**
   - Similar to `byType` but applies to constructor arguments.
   - Spring searches for beans matching the types of the parameters in the bean's constructor.
4. **`@Autowired` and `@Qualifier` (Annotation-driven)**
   - The modern standard. It uses `byType` by default.
   - **`@Qualifier`:** Used alongside `@Autowired` to specify the exact bean name when multiple beans of the same type exist (resolving ambiguity).


<br><br>

**Does Spring Bean provide thread safety?**

- Spring beans are not thread-safe by default. Because the default scope is `singleton`, every thread in your application sharing that bean accesses the same instance.
- If a singleton bean has mutable instance variables (state), multiple threads can modify them simultaneously, leading to "Race Conditions" and data inconsistency.
- Most Spring beans (like `@Service` or `@Repository`) are thread-safe only because they are stateless—they only contain logic and no shared data members.
- **How to Achieve Thread Safety**
  - **Keep Beans Stateless:** Don't use instance variables to store user-specific data. Use local variables inside methods instead.
  - Use `prototype`, `request`, or `session` scopes so threads don't share the same object.
  - Use Java's `synchronized` keyword or `java.util.concurrent.locks.ReentrantLock` (not recommended as it hurts performance).
  - Use `java.lang.ThreadLocal` to store data that is unique to the current thread.
- **Code Comparison:**
  - **Unsafe Singleton (Shared State)**
    ```java
    @org.springframework.stereotype.Service
    public class CounterService {
        private int count = 0; // DANGER: Shared across all threads

        public void increment() {
            count++; // Race condition occurs here
        }
    }
    ```
  - **Safe Singleton (No State)**
    ```java
    @org.springframework.stereotype.Service
    public class CalculationService {
        public int add(int a, int b) {
            int result = a + b; // SAFE: Local variable is on the thread stack
            return result;
        }
    }
    ```
  - **Safe Prototype (New Instance)**
    ```java
    @org.springframework.stereotype.Component
    @org.springframework.context.annotation.Scope("prototype")
    public class UserTask {
        private String status; // SAFE: Each request gets a fresh object
    }
    ```
- **Thread Access Visualization**
  ```mermaid
  graph TD
    subgraph Singleton_Scope
    T1[Thread 1] --> S((Shared Bean Instance))
    T2[Thread 2] --> S
    T3[Thread 3] --> S
    S --- State[Shared Variable: count++]
    end

    subgraph Prototype_Scope
    T4[Thread 4] --> P1((New Bean Instance A))
    T5[Thread 5] --> P2((New Bean Instance B))
    end

    style S fill:#f8d7da,stroke:#dc3545
    style P1 fill:#d4edda,stroke:#28a745
    style P2 fill:#d4edda,stroke:#28a745
  ```
  - **Singleton Risk:** Multiple threads "collide" on the same memory address of the shared variable.
  - **Prototype Safety:** Each thread gets its own isolated instance, preventing data leakage between threads.


<br><br>

**What is a Controller in Spring MVC?**

- A Controller acts as a Coordinator. It intercept incoming client requests (HTTP), processes the input, calls the business logic (Service layer), and returns a response (View or Data).
- **DispatcherServlet (Front Controller):** The "Main Entry Point." It receives every request first and delegates it to the specific Controller that matches the URL.
- **`@Controller` Annotation:** Used to mark a class as a web requester handler. Spring's `org.springframework.context.annotation.ClassPathBeanDefinitionScanner` detects these during a component scan.
- **`@RequestMapping`:** Maps a specific URL or HTTP method (GET, POST, etc.) to a specific method inside the Controller.
- **Spring MVC Request Flow**
  ```mermaid
  sequenceDiagram
    participant C as Client (Browser)
    participant DS as DispatcherServlet
    participant H as HandlerMapping
    participant CO as Controller
    participant V as ViewResolver

    C->>DS: 1. Sends HTTP Request
    DS->>H: 2. Which Controller handles this URL?
    H-->>DS: 3. Return Controller Info
    DS->>CO: 4. Execute Method
    CO-->>DS: 5. Return ModelAndView
    DS->>V: 6. Resolve View Name to Page
    V-->>DS: 7. Return JSP/Thymeleaf
    DS-->>C: 8. Return Final HTML Response
  ```
  - **DispatcherServlet:** Centralizes the logic so individual controllers don't have to handle low-level HTTP details.
  - **HandlerMapping:** The "Map" that tells the DispatcherServlet which `@Controller` owns which URL.