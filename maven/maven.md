## Basic

**What is Maven ?**

- It is an open-source tool that automates the entire process of converting source code into a finished product (like a JAR or WAR file).
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


<br><br>
**What is Clean, Default, and Site in Maven?**

- **Clean Lifecycle**: Focuses on removing temporary files. It deletes the `target` folder to ensure that old, stale compiled classes do not interfere with the new build.
- **Default (Build) Lifecycle**: This is the main lifecycle responsible for the entire project build. It handles everything from source code compilation and testing to packaging (creating the JAR) and final deployment.
- **Site Lifecycle**: Used for generating technical documentation. It creates a dedicated website for the project containing Javadocs, unit test reports, and dependency graphs.


<br><br>
**What is a Maven Repository?**

- Repositories are directories (folders) where Maven stores all the packaged JAR files, plugins, and metadata (POM files).
- Every JAR in a repository is accompanied by a POM file. This metadata tells Maven the project's identity (GAV) and what other libraries it needs to function.
- Maven uses these repositories to automatically locate and download the dependencies you list in your `pom.xml`, saving you from manual downloads.
- **Three Types of Repositories**
  - **Local Repository**: A folder on your own machine (Default: `~/.m2/repository`).
  - **Central Repository**: The "Official Market" managed by the Apache Maven community.
  - **Remote Repository**: A custom repository hosted on a private server (e.g., Nexus or Artifactory).


<br><br>
**What are the different types of Maven Repositories?**

- **Local Repository:**
  - This is a folder located on your local machine (default: `~/.m2/repository`).
  - It stores all dependencies (JAR files) that Maven has already downloaded.
  - **Logic**: Before going to the internet, Maven always checks here first. If it finds the JAR, it uses it immediately to save time and bandwidth.
- **Central Repository:**
  - This is a public, web-based repository provided by the Apache Maven community.
  - It contains a massive collection of most common open-source libraries (e.g., Hibernate, JUnit, Spring).
  - Logic: If a dependency is missing from your Local Repository, Maven automatically connects to the internet to fetch it from here.
- **Remote Repository:**
  - This is a repository hosted on a private server, usually inside a company’s network (e.g., Nexus or Artifactory).
  - It is used to store internal project JARs that are not available to the public.
  - Logic: If a company-specific library is needed, Maven downloads it from this server into your Local Repository first, then uses it.


<br><br>
**How does Maven Architecture work?**

- Maven starts by reading the `pom.xml` file. This file contains all the project configurations, dependencies, and build instructions. Without this file, Maven has no "brain" to function.
- Maven checks the Local Repository (your machine) for the required JARs. If they are missing, it reaches out to the Central Repository (internet) or Remote Repository (company server) to download them into your local folder.
- Maven executes the requested Lifecycle (e.g., Default). This involves a sequence of Phases (Compile -> Test -> Package). Each phase is powered by Plugins that achieve specific Goals (e.g., the `compiler` plugin has a `compile` goal).
- Once the lifecycle finishes, Maven generates the final artifact (JAR/WAR) in the `target` folder and produces reports like test results or documentation.


<br><br>
**What are the different phases in the Maven Build Lifecycle?**

- In the Default (Build) Lifecycle, if you execute a specific phase, Maven will automatically execute every phase that comes before it in the sequence.
- `compile`: Translates the source code of the project from .java to .class files.
- `test-compile`: Specifically compiles the source code for the test classes (like JUnit or TestNG).
- `test`: Executes the unit tests using a suitable framework.
- `package`: Bundles the compiled code into a distributable format, such as a JAR or WAR file.
- `integration-test`: Processes and deploys the package into an environment where integration tests can be run.
- `verify`: Runs checks on the results of integration tests to ensure quality criteria are met.
- `install`: Installs the packaged code into your Local Repository, allowing it to be used as a dependency in other local projects.
- `deploy`: Copies the final package to a Remote Repository for sharing with other developers and teams.
- **Programmatic Execution**:
  ```bash
  # This command runs: compile -> test-compile -> test -> package
  mvn package 

  # This command runs everything up to and including 'install'
  mvn install
  ```
- **Flow Diagram**:
  ```mermaid
  graph TD
    A[<b>validate</b><br/>Check if project is correct] --> B
    B[<b>compile</b><br/>Convert .java to .class] --> C
    C[<b>test-compile</b><br/>Compile test source code] --> D
    D[<b>test</b><br/>Run unit tests] --> E
    E[<b>package</b><br/>Create JAR/WAR file] --> F
    F[<b>integration-test</b><br/>Run tests in environment] --> G
    G[<b>verify</b><br/>Check test results] --> H
    H[<b>install</b><br/>Move JAR to Local Repo] --> I
    I[<b>deploy</b><br/>Move JAR to Remote Repo]

    style A fill:#f9f,stroke:#333,stroke-width:2px
    style E fill:#bbf,stroke:#333,stroke-width:2px
    style H fill:#dfd,stroke:#333,stroke-width:2px
    style I fill:#fdd,stroke:#333,stroke-width:2px
  ```


<br><br>
**Which command is used to build a Maven site?**

- `mvn site`: This is the primary command used to generate a dedicated website for your Maven project.
- **Documentation Generation**: It automates the creation of project reports, including Javadocs, unit test results, and dependency lists.
- **Default Output Directory**: Once the command finishes, the generated site files are stored in the `target/site/` folder of your project.
- **Lifecycle Role**: The `site` phase is part of the Site Lifecycle, which is separate from the standard Default (Build) or Clean lifecycles.
- **Programmatic Execution:**
  ```bash
  # Basic site generation
  mvn site

  # Clean old reports and generate new ones
  mvn clean site
  ```


<br><br>
**What are the different conventions used while naming a project in Maven?**

- **Project Identity (GAV)**: Every project in Maven is uniquely identified by three mandatory coordinates: **G**roupId, **A**rtifactId, and **V**ersion.
- **GroupId**: This represents the organization or group that created the project. It usually follows Java package naming rules (e.g., `org.apache.maven`).
- **ArtifactId**: This is the name of the specific project or module (e.g., `maven`). It should be unique within the GroupId.
- **Version**: This identifies the specific release or stage of the project (e.g., `2.0.1`).
- **Full Name Format**: The complete name is written as `<GroupId>:<ArtifactId>:<Version>`.
- **Flow Diagram: GAV Hierarchy**
  ```mermaid
  graph LR
    A[<b>GroupId</b><br/>The Organization<br/>'org.apache.maven'] --> B
    B[<b>ArtifactId</b><br/>The Project Name<br/>'maven'] --> C
    C[<b>Version</b><br/>The Release Number<br/>'2.0.1']
    
    style A fill:#f9f,stroke:#333
    style B fill:#bbf,stroke:#333
    style C fill:#dfd,stroke:#333
  ```


<br><br>
**What is a Maven Artifact?**

- An artifact is a file produced by a Maven project, most commonly a JAR, WAR, or EAR file, that is deployed to a Maven repository.
- A single project build can generate one or more artifacts, such as a compiled binary JAR and a separate source JAR.
- Every artifact is uniquely identified by three mandatory elements: GroupId, ArtifactId, and a Version String.
- Together, these elements (e.g., `com.your.package`, `project-name`, and `1.0.0`) act as coordinates to locate the specific file in a repository.


<br><br>
**What are the phases of a Clean Life Cycle?**

- **Pre-clean**: This phase involves executing processes that are required before the actual project cleaning starts.
- **Clean**: This is the core phase where Maven removes all files generated by the previous build. It specifically deletes the `target` directory.
- **Post-clean**: This phase handles any final processes needed after the project has been successfully cleaned.
- **Flow Diagram**:
  ```mermaid
  graph LR
    A[<b>Pre-clean</b><br/>Prep work] --> B
    B[<b>Clean</b><br/>Delete 'target' folder] --> C
    C[<b>Post-clean</b><br/>Final cleanup]
    
    style B fill:#f96,stroke:#333,stroke-width:2px
  ```


<br><br>
**What are the phases of a Site Life Cycle?**

- **Pre-site**: This phase involves carrying out the necessary steps and preparation required before the actual site generation begins.
- **Site**: This is the core phase where the project's site documentation is generated. It creates files like Javadocs and unit test reports.
- **Post-site**: This phase handles any final tasks or processing needed once the site generation is complete.
- **Site-deploy**: This final phase is used to deploy the generated site documentation to a specified web server or repository.
- **Flow Diagram**:
  ```mermaid
  graph LR
    A[<b>Pre-site</b><br/>Setup & Prep] --> B
    B[<b>Site</b><br/>Generate Docs] --> C
    C[<b>Post-site</b><br/>Finalize Docs] --> D
    D[<b>Site-deploy</b><br/>Upload to Server]

    style B fill:#bbf,stroke:#333,stroke-width:2px
    style D fill:#dfd,stroke:#333,stroke-width:2px
  ```


<br><br>
**What is meant by Maven Plugins?**

- **Logic Reusability**: Maven plugins are essential features used to reuse common build logic across multiple projects.
- **The "Worker" of Maven**: While lifecycles and phases are just names of stages, plugins are the actual tools that perform the work.
- **Task Execution**: They are used to carry out specific tasks such as:
  - **Compiling** source code.
  - **Testing** the code.
  - **Creating JAR files** (packaging).
  - **Documenting** the projects.
- **Goal-Oriented**: Each plugin consists of one or more "goals" (specific tasks). For example, the `compiler` plugin has a `compile` goal.
- **Flow Diagram**:
  ```mermaid
  graph LR
    A[<b>Build Phase</b><br/>'compile'] -- triggers --> B[<b>Maven Plugin</b><br/>'maven-compiler-plugin']
    B -- executes --> C[<b>Goal</b><br/>'compile']
    
    style B fill:#bbf,stroke:#333,stroke-width:2px
  ```


<br><br>
**Why are Maven Plugins used?**

- **Execution of Tasks**: While Maven defines the stages (phases) of a build, the plugins are the actual workers that perform the tasks.
- **Compiling Code**: Plugins like the `maven-compiler-plugin` are used to translate source code into bytecode.
- **Unit Testing**: They facilitate the execution of unit tests to ensure code quality.
- **Packaging Artifacts**: Plugins are responsible for bundling the compiled code into distributable formats like JAR or WAR files.
- **Documentation & Reporting**: They automate the creation of project documentation and detailed project reports (e.g., test results).
- **Flow Diagram:**
  ```mermaid
  graph LR
    A[<b>Build Phase</b><br/>'test'] -- triggers --> B[<b>Maven Plugin</b><br/>'maven-surefire-plugin']
    B -- executes --> C[<b>Goal</b><br/>'test']
    
    style B fill:#bbf,stroke:#333,stroke-width:2px
  ```

<br><br>
**What are the types of Maven Plugins?**

- **Build Plugins**:
  - These plugins are executed during the standard build lifecycle.
  - They handle core tasks like compiling code, running tests, and packaging JARs.
  - They are configured within the `<build>` element of the `pom.xml`.
- **Reporting Plugins:**
  - These plugins are executed specifically during the site generation stage.
  - They are used to generate project reports, such as Javadocs or unit test coverage results.
  - They are configured within the `<reporting>` element of the `pom.xml`.
- **Flow Diagram**:
  ```mermaid
  graph TD
    A[<b>pom.xml</b>] --> B[<b>Build Plugins</b>]
    A --> C[<b>Reporting Plugins</b>]
    B --> D[Executed during 'mvn install']
    C --> E[Executed during 'mvn site']
    
    style B fill:#bbf,stroke:#333
    style C fill:#dfd,stroke:#333
  ```
- Example:
  ```xml
  <build>
    <plugins>
      <plugin>
        <artifactId>maven-compiler-plugin</artifactId>
      </plugin>
    </plugins>
  </build>

  <reporting>
    <plugins>
      <plugin>
        <artifactId>maven-javadoc-plugin</artifactId>
      </plugin>
    </plugins>
  </reporting>
  ```



<br><br>
**What is the difference between Convention and Configuration in Maven?**

- **Convention**: Convention occurs when developers are not required to create the build processes manually.
  - Users do not have to specify configuration in detail; once a project is created, Maven automatically creates a standard structure.
  - It simplifies the developer's work by assuming "default" behaviors and folder locations.
- **Configuration**: Configuration occurs when developers are expected to create the build processes manually.
  - Developers must specify every configuration detail explicitly to tell the tool exactly how to behave.
  - This is typically used when a project needs to deviate from the standard Maven defaults.
  - **Example**:
    ```xml
    <build>
      <sourceDirectory>src/scripts/java</sourceDirectory>
    </build>
    ```
- **Analogy**:
  - **Convention** is like ordering a "**Supreme Pizza**." You don't have to specify every topping; the shop already has a standard "convention" of what goes on it. You just say the name, and it’s done.
  - **Configuration** is like "**Build Your Own Pizza**." You must manually specify the crust type, the sauce, and every single topping in detail. If you don't mention it, you don't get it.


<br><br>
**Why is it said that “Maven uses convention over configuration”?**

- **Automatic Project Setup**: Developers only need to initiate a Maven project, and the entire directory structure is created automatically.
- **Reduced Manual Effort**: Because Maven follows a standard "Convention," developers don't have to specify build processes or configuration details manually unless they want to deviate from the standard.
- **Standardized Workflows**: There are pre-defined conventions for every stage, including project setup, building artifacts, releasing code, and running unit tests.
- **Instant Familiarity**: Any developer moving to a new Maven project immediately knows where the source code, resources, and tests are located because the "Convention" is always the same.
- Flow Diagram:
  ```mermaid
  graph LR
    A[<b>Start Project</b>] -- Follows Convention --> B[<b>Auto-Structure</b><br/>src/main/java]
    B --> C[<b>Auto-Build</b><br/>mvn compile]
    C --> D[<b>Auto-Test</b><br/>mvn test]
    
    style B fill:#bbf,stroke:#333
    style D fill:#dfd,stroke:#333
  ```


<br><br>
**What is Maven’s order of inheritance?**

In Maven, configurations can come from multiple sources. When there is a conflict, Maven follows a specific hierarchy to determine which setting "wins." The order of inheritance (from highest to lowest priority) is:

- **Settings**: These are the user-specific or global configurations found in the `settings.xml` file (usually in `~/.m2/settings.xml`).
- **CLI Parameters**: These are the arguments or properties passed directly through the Command Line Interface (CLI) during execution (e.g., `-DskipTests`).
- **Parent POM**: This refers to the configurations inherited from a parent project or the "Super POM" (Maven's internal default).
- **Project POM**: This is the specific `pom.xml` file located in the current project directory.
- **Flow Diagram**
  ```mermaid
  graph TD
    A[<b>1. Settings</b><br/>Global/User Config] --- B
    B[<b>2. CLI Parameters</b><br/>Command line arguments] --- C
    C[<b>3. Parent POM</b><br/>Inherited configurations] --- D
    D[<b>4. Project POM</b><br/>Local project settings]
    
    style A fill:#f9f,stroke:#333,stroke-width:2px
    style D fill:#bbf,stroke:#333,stroke-width:2px
  ```


<br><br>
**What do Build Life Cycles and phases imply in the basic concepts of Maven?**

In Maven, the build process is organized into a strict hierarchy of Lifecycles, Phases, and Goals.

- **Build Lifecycles**: These are the highest-level units of work in Maven, consisting of a predefined sequence of build phases.
- **Build Phases**: Each lifecycle is divided into specific stages called phases. When a specific phase is executed, Maven automatically runs all the preceding phases in that lifecycle's sequence.
- **Goals**: A build phase consists of a series of goals. Goals are the smallest, most specific tasks (like "compile" or "create a jar").
- **Execution Logic**: When a phase is run, all the goals related to that specific phase, along with their associated plugins, are executed.
- **Flow Diagram**:
  ```mermaid
  graph TD
    A[<b>Build Lifecycle</b><br/>The Big Process] --> B[<b>Build Phase</b><br/>A specific stage]
    B --> C[<b>Plugin Goal</b><br/>The actual task executed]
    
    style A fill:#f9f,stroke:#333
    style B fill:#bbf,stroke:#333
    style C fill:#dfd,stroke:#333
  ```

<br><br>
**What is the ‘Goal’ in the Maven terminology?**

- A goal refers to a specific, granular task that enables a project to be built and organized.
- While phases provide the structure, goals are the actual units of work executed by plugins.
- Phases, which represent stages in the lifecycle, define the specific sequence in which the desired goals are accomplished.
- A goal can be bound to one or more build phases, or it can be executed individually via the command line without running a full phase.
- Flow Diagram;
  ```mermaid
  graph LR
    A[<b>Build Lifecycle</b>] --> B[<b>Build Phase</b>]
    B --> C[<b>Plugin Goal 1</b>]
    B --> D[<b>Plugin Goal 2</b>]
    
    style B fill:#bbf,stroke:#333
    style C fill:#dfd,stroke:#333
    style D fill:#dfd,stroke:#333
  ```

<br><br>
**What is meant by the term ‘Dependencies and Repositories’ in Maven?**

In Maven, these two concepts work together to manage all the external code your project needs to function.

1. **Dependencies**
   - Dependencies are the external Java libraries (JAR files) that your project requires to compile and run.
   - Instead of manually managing files, you declare these libraries in your `pom.xml`. Maven then ensures the correct versions are available.
   - Maven also automatically handles "dependencies of dependencies," so you don't have to track down every sub-library yourself.
2. **Repositories**
   - Repositories are directories or servers that store these packaged JAR files along with their metadata (POM files).
   - **Local Repository**: A private cache on your own machine where Maven stores everything it has downloaded for quick access.
   - **Central Repository**: A massive, public online library managed by the Maven community containing most common open-source JARs.


<br><br>
**How dependencies and repositories work together?**

- **Check Local**: When you build a project, Maven first looks for the required Dependencies in your Local Repository.
- **Download if Missing**: If they are not found locally, Maven automatically connects to the Central Repository (or a configured Remote Repository) to download them.
- **Cache for Future**: Once downloaded, Maven stores them in your Local Repository so it doesn't have to download them again for future builds.
- **Flow Diagram**:
  ```mermaid
  graph TD
    A[<b>Project Needs JAR</b>] --> B{<b>Is it in Local Repo?</b>}
    B -- Yes --> C[Use it immediately]
    B -- No --> D[<b>Request from Central Repo</b>]
    D --> E[Download to Local Repo]
    E --> F[Use for build]
    
    style B fill:#f9f,stroke:#333
    style D fill:#bbf,stroke:#333
  ```


<br><br>
**What is a ‘Snapshot’ in Maven?**

- A Snapshot refers to a version available in a Maven remote repository that signals the latest development copy of a project.
- Unlike a "Release" version (which is permanent), a Snapshot indicates that the code is still being actively worked on and can change over time.
- **Continuous Updates**: The data service team updates the snapshot with new source code in the repository for each Maven build.
- For every new build, Maven automatically inspects the remote repository to see if a newer version of the Snapshot has been uploaded.
- **Internal Working**:
  
  When you use a version ending in `-SNAPSHOT` (e.g., `1.0-SNAPSHOT`), Maven handles it differently than a standard version:

  ```mermaid
  graph TD
    A[<b>Build Start</b>] --> B{<b>Is version a SNAPSHOT?</b>}
    B -- Yes --> C[Check Remote Repo for latest timestamped version]
    C --> D[Download if newer version exists]
    B -- No --> E[Use local version; never check remote again]
    
    style C fill:#bbf,stroke:#333
    style E fill:#dfd,stroke:#333
  ```


<br><br>
**What is a Maven Archetype?**

- A Maven Archetype is a specific Maven plugin designed to create a project structure based on a predefined template.
- These archetypes function as project templates that Maven generates whenever a new project is initiated.
- They help enforce consistency across projects by providing a "best-practice" directory layout and a pre-configured `pom.xml`.
- By using an archetype, developers avoid the manual setup of folders like `src/main/java` and `src/test/java`, as the plugin scaffolds them automatically.
- How it Works
  ```mermaid
  graph LR
    A[<b>User Command</b><br/>mvn archetype:generate] --> B[<b>Archetype Plugin</b>]
    B --> C{<b>Select Template</b><br/>e.g., Spring Boot, Webapp}
    C --> D[<b>Generated Project</b><br/>Folders + pom.xml]
    
    style B fill:#bbf,stroke:#333
    style D fill:#dfd,stroke:#333
  ```