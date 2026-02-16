## Basic

**What is Maven ?**

- t is an open-source tool that automates the entire process of converting source code into a finished product (like a JAR or WAR file).
- It eliminates the need to manually download libraries. You list what you need in a file, and Maven fetches them and their sub-requirements (Transitive Dependencies) automatically.
- It handles everything from compiling and testing to packaging and deploying the project.
- Maven provides a standard project structure. If you follow it, you don't have to write custom scripts to tell Maven where your code or resources are.
- All project settings, including library versions and build instructions, are centralized in one XML file: the `pom.xml`.


<br><br>
**What does Maven help with?**

- It manages the entire process from compiling source code to packaging it into a JAR/WAR and distributing the final product.
- It eliminates manual JAR downloads. You simply list the library in the `pom.xml`, and Maven automatically fetches it along with any required sub-libraries (Transitive Dependencies).
- It allows developers to build projects across different environments (Windows, Linux, Dev, Prod) without manually changing paths or library settings.
- It provides easy access to project metadata, documentation (Javadocs), and unit test reports in a standardized format.
- Since Maven enforces a standard directory structure, any developer can join a new project and instantly know where the code and configurations are located.
- It simplifies the process of releasing new versions of a project and ensures that all team members are using the exact same versions of external libraries.
- **Example**
  Instead of manually adding a JAR to the build path, you simply add this to your pom.xml:

  ```xml
    <dependency>
        <groupId>org.springframework</groupId>
        <artifactId>spring-core</artifactId>
        <version>6.1.0</version>
    </dependency>
  ```
- **Analogy**:
  
    Think of Maven as a Smart Logistics Manager for a construction site.
    Instead of the workers (Developers) driving to different shops to buy bricks, cement, and tools (JARs), they simply write a list (the pom.xml). The Logistics Manager (Maven) checks the local warehouse (Local Repo), orders missing items from the global supplier (Central Repo), ensures the items are delivered in the right order, and sets up the machinery (Build Phases) to complete the building.


<br><br>
**What are the different elements that Maven takes care of?**

- **Builds**: It automates the technical steps to transform code into a product. This includes compiling `.java` files, running unit tests, and bundling everything into a JAR or WAR file.
- **Dependencies**: It handles all external libraries. Maven ensures the correct version is used and automatically downloads "Transitive Dependencies" (the libraries your libraries need).
- **Reports**: It generates essential project data, such as test execution results, code coverage, and documentation (Javadocs), providing a clear picture of project health.
- **Distribution**: It manages how the final product is stored. It can automatically send the finished JAR to a shared repository so other teams can use it.
- **Releases**: It streamlines the versioning process, helping you move from a development version (Snapshot) to a final, stable release version.
- **Mailing List**: It stores project communication metadata in the `pom.xml`. This allows automated systems to know who to notify if a build fails or a new version is released.


<br><br>
**What is POM**

- It is an XML file (`pom.xml`) that contains all the essential information about the project and its configuration.
- It defines the project's unique coordinates: **G**roupId (organization), **A**rtifactId (project name), and **V**ersion.
- It lists every external library (JAR) the project needs. Maven reads this list and downloads them automatically.
- It contains instructions on how to build the project, such as the Java version to use or special plugins for packaging (e.g., creating a WAR file).
- It must reside in the Root Directory of the project. Maven looks for it here to start any task.
- **Example**:
  ```xml
    <project>
        <modelVersion>4.0.0</modelVersion>
        
        <groupId>com.practice.bank</groupId>
        <artifactId>payment-service</artifactId>
        <version>1.0.0</version>
    </project>
  ```
- **Analogy**:
  
  Think of the POM as the Blueprint of a House. It doesn't contain the actual bricks or wood (code), but it tells you exactly what materials are needed (Dependencies) and how high the walls should be (Configuration). When a contractor (Maven) arrives, they check the blueprint first to know what to build.


<br><br>
**What all is included in the POM?**

- **Project Identity (GAV)**: Defines the unique **G**roupId, **A**rtifactId, and **V**ersion of your project.
- **Dependencies**: A list of external JARs (like Hibernate or Spring) required to run your application.
- **Plugins**: Additional tools Maven uses to perform specific tasks, like compiling code or generating reports.
- **Plugin Configuration**: Custom settings for those tools (e.g., specifying Java 17 for the compiler plugin).
- **Resources**: Instructions on how to handle non-Java files like `application.properties` or XML configs.
- **Developers & Contributors**: Metadata about the people working on the project for documentation purposes.
- **Profiles**: Custom configurations for different environments (e.g., Dev, Test, Prod).
- Example:
  ```xml
    <project>
        <groupId>com.app</groupId>
        <artifactId>service</artifactId>
        <version>1.0</version>

        <dependencies>
            <dependency>
                <groupId>org.hibernate</groupId>
                <artifactId>hibernate-core</artifactId>
                <version>6.0.0.Final</version>
            </dependency>
        </dependencies>

        <build>
            <plugins>
                <plugin>
                    <artifactId>maven-compiler-plugin</artifactId>
                    <configuration>
                        <source>17</source>
                        <target>17</target>
                    </configuration>
                </plugin>
            </plugins>
        </build>
    </project>
  ```


<br><br>
**What are the minimum required elements for POM?**

- `<project>` root: The wrapper element that identifies the file as a Maven Project Object Model.
- `modelVersion`: Must be set to 4.0.0. This defines which version of the POM structure Maven is using.
- `groupId`: The unique identifier for your organization or project group (e.g., `com.company.module`).
- `artifactId`: The name of the specific project or "artifact" (e.g., `user-service`).
- `version`: The specific version of the project (e.g., `1.0-SNAPSHOT`).
- **Example**:
  ```xml
    <project xmlns="http://maven.apache.org/POM/4.0.0">
        <modelVersion>4.0.0</modelVersion>
        
        <groupId>com.java.prep</groupId>
        <artifactId>basic-app</artifactId>
        <version>1.0.0</version>
    </project>
  ```
- **Analogy**:
  
  Think of this as a Newborn's Birth Certificate. To be valid, it must have the Hospital Name (groupId), the Baby's Name (artifactId), and the Date of Birth (version). Without these basics, the system (Maven) won't recognize that the project even exists.


<br><br>
**What is meant by the term ‘Build Tool’?**

- A build tool is software that automates the repetitive tasks required to turn raw source code into an executable application.
- It can automatically create Java classes or other source files from metadata or database schemas.
- It generates technical manuals or API documentation (like Javadoc) directly from the comments in your code.
- It handles the translation of high-level source code (`.java`) into machine-readable bytecode (`.class`).
- It bundles all compiled classes and resources into a single distributable file, such as a JAR or WAR.
- It automates the process of moving the finished package to a local machine, a shared team server, or a production environment.
- **Example**:
  ```bash
    mvn compile   # Compiles all source files automatically
    mvn package   # Compiles, tests, and creates the JAR file
  ```