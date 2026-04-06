**Explain the concept of "convention over configuration" in Spring Boot.**

- **Convention over Configuration (CoC)** is a software design paradigm that seeks to decrease the number of decisions that a developer is required to make without necessarily losing flexibility.
- Spring Boot implements this by providing sensible defaults for your project, so you don't have to manually configure every single bean or setting unless you want something non-standard.
- It eliminates the massive XML files or complex `@Configuration` classes required in traditional Spring by "guessing" what you need based on the dependencies you add to your classpath.
- **Auto-configuration** is the core mechanism of CoC; if Spring Boot sees `spring-boot-starter-web` on your classpath, it automatically configures an embedded Tomcat server and `org.springframework.web.servlet.DispatcherServlet`.
- Component Scanning is another convention: if you place your classes in sub-packages of the main class (the one with `@SpringBootApplication`), Spring Boot automatically finds and registers your `@RestController`, `@Service`, and `@Repository` beans.
- **How CoC Simplifies Development**
  ```mermaid
  graph LR
    A[Add Starter Dependency] --> B{Classpath Check}
    B -- Found H2 Driver --> C[Auto-configure DataSource]
    B -- Found Web Starter --> D[Auto-configure Tomcat/MVC]
    C --> E[Ready to Run]
    D --> E
    
    style A fill:#f96,stroke:#333,stroke-width:2px
    style B fill:#69f,stroke:#333,stroke-width:2px
    style E fill:#6f6,stroke:#333,stroke-width:2px
  ```
  - **Standardized Workflow:** Developers focus on business logic immediately instead of infrastructure setup.
  - **Overridable Defaults:** If you define your own `javax.sql.DataSource` bean, Spring Boot’s "convention" backs away and uses your custom "configuration."
- **Code Comparison (The CoC Effect)**
  - **Traditional Spring (Manual Configuration):**
  ```java
    // You had to manually define the ViewResolver, DispatcherServlet, etc.
    @Configuration
    public class WebConfig {
        @Bean
        public InternalResourceViewResolver viewResolver() {
            InternalResourceViewResolver resolver = new InternalResourceViewResolver();
            resolver.setPrefix("/WEB-INF/views/");
            resolver.setSuffix(".jsp");
            return resolver;
        }
    }
  ```
  - **Spring Boot (Convention):**
  ```java
    // No configuration class needed! 
    // Just add 'spring-boot-starter-web' and put this in the base package.
    package com.example.demo.controller;

    import org.springframework.web.bind.annotation.GetMapping;
    import org.springframework.web.bind.annotation.RestController;

    @RestController
    public class HelloController {
        @GetMapping("/hello")
        public String sayHello() {
            return "Hello World!"; // Convention: Spring knows how to handle the String return
        }
    }
  ```


<br><br>

**What is the difference between Spring and Spring Boot?**

- **Spring Framework** is an open-source application framework that provides comprehensive infrastructure support (like Dependency Injection and Transaction Management) for developing Java applications.
- **Spring Boot** is an extension of the Spring framework that aims to simplify the development, unit testing, and deployment process through **Auto-configuration** and **Starter Dependencies**.
- **Configuration:** In Spring, you must manually define beans and configurations (XML or `@Configuration`). In Spring Boot, the `@SpringBootApplication` annotation triggers auto-configuration based on the classpath.
- **Server Support:** Spring requires you to manually install and configure an external web server (like Tomcat or Jetty). Spring Boot comes with an Embedded Server, allowing you to run the app as a simple JAR file.
- **Dependency Management:** Spring requires manual management of compatible library versions. Spring Boot uses Starter POMs (e.g., `spring-boot-starter-data-jpa`) which automatically pull in a curated set of compatible dependencies.
- **Boilerplate Code:** Spring involves significant boilerplate code for setting up DispatcherServlets, ViewResolvers, and EntityManagers. Spring Boot eliminates this using "opinionated" defaults.
- **Architecture Comparison:**
  ```mermaid
  graph TD
    subgraph Spring_Boot ["Spring Boot (The Wrapper)"]
        A[Embedded Servers: Tomcat/Jetty]
        B[Auto-Configuration]
        C[Starter Dependencies]
        D[Actuator - Health Checks]
    end

    subgraph Spring_Core ["Spring Framework (The Core)"]
        E[Inversion of Control - IoC]
        F[Dependency Injection - DI]
        G[AOP / Transactions]
    end

    Spring_Boot --> Spring_Core
    
    style Spring_Boot fill:#d4f1f4,stroke:#05445e,stroke-width:2px
    style Spring_Core fill:#ffd1dc,stroke:#800020,stroke-width:2px
  ```
  - **Core vs. Utility:** Spring is the "engine," while Spring Boot is the "dashboard and key" that makes the engine start instantly.
  - **Deployment:** Spring apps are typically packaged as WAR files; Spring Boot apps are typically packaged as self-contained executable JAR files.


<br><br>

**How does Spring Boot simplify the development of Java applications?**

- **Auto-configuration:** Spring Boot looks at the libraries available on your classpath and automatically configures beans. For example, if it sees `H2` in your dependencies, it automatically sets up an in-memory database connection without you writing a single line of `javax.sql.DataSource` configuration.
- **Starter Dependencies:** Instead of searching for compatible versions of various libraries, you use "Starters." These are curated sets of dependency descriptors (e.g., `spring-boot-starter-data-jpa`) that pull in all required jars for a specific feature, ensuring version compatibility.
- **Embedded Servers:** Traditional Java web apps require a separate installation of a servlet container like Apache Tomcat. Spring Boot embeds these servers directly into the application, allowing you to run your app as a standalone Executable JAR using the `java -jar` command.
- **Opinionated Defaults:** Spring Boot follows the "Convention over Configuration" principle. It provides a pre-defined production-ready setup for logging, security, and connection pools, so you only write configuration for the parts that differ from the norm.
- **Actuator (Production Readiness):** It provides built-in HTTP endpoints (via `org.springframework.boot.actuate`) to monitor application health, view environment properties, and check metrics without requiring manual implementation of these features.
- **Spring Boot CLI & DevTools:** Tools like `spring-boot-devtools` enable Hot Swapping, which automatically restarts the application whenever code changes are detected, significantly speeding up the development cycle.


<br><br>

**What is the role of the `@SpringBootApplication` annotation?**

- **`@org.springframework.boot.autoconfigure.SpringBootApplication`** is a convenience annotation that acts as a wrapper for three core Spring annotations, reducing boilerplate code in the main entry point of the application.
  - **`@org.springframework.context.annotation.Configuration`:** This tells Spring that the class contains `@Bean` definition methods. It allows the Spring container to process the class and generate bean definitions for the application context.
  - **`@org.springframework.boot.autoconfigure.EnableAutoConfiguration`:** This is the "magic" of Spring Boot. It tells Spring to automatically configure beans based on the dependencies found in the `pom.xml` or `build.gradle` file (e.g., setting up a database if an H2 driver is present).
  - **`@org.springframework.context.annotation.ComponentScan`:** This instructs Spring to look for components, configurations, and services in the current package and all its sub-packages. This is why it is "convention" to place your main class in the root package.
- **Attributes:** It also allows you to customize behavior, such as using `exclude` to disable specific auto-configuration classes that you don't want to run.
- **Annotation Composition**
  ```mermaid
  graph TD
    A["@SpringBootApplication"] --- B["@Configuration"]
    A --- C["@EnableAutoConfiguration"]
    A --- D["@ComponentScan"]
    
    B --> B1[Allows defining Beans]
    C --> C1[Configures based on Classpath]
    D --> D1[Scans Packages for @Component]

    style A fill:#f96,stroke:#333,stroke-width:4px
    style B fill:#fff,stroke:#333
    style C fill:#fff,stroke:#333
    style D fill:#fff,stroke:#333
  ```
  - **Single Point of Entry:** By combining these, Spring Boot ensures that your application is configured, components are found, and external libraries are integrated with just one line of code.
  - **Root Package Importance:** Because `@ComponentScan` defaults to the package of the annotated class, placing `@SpringBootApplication` in `com.example` ensures all classes in `com.example.service`, `com.example.controller`, etc., are discovered.