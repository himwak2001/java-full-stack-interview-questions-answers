## Basics

**What is Hibernate in Java?**
- Hibernate is an ORM (Object-Relational Mapping) framework. It acts as a middleman that maps your Java classes directly to database tables, so you don't have to write manual SQL for every operation.
- In Java, we use Objects, but databases use Tables. Hibernate bridges this gap by automatically converting "Rows" from a table into "Objects" in Java, and vice-versa.
- Hibernate uses a "Dialect" system. If you want to move your app from MySQL to Oracle, you just change one configuration line. Hibernate automatically adjusts the SQL syntax to match the new database.
- It eliminates "Boilerplate code." Instead of writing a 5-line JDBC `INSERT` statement, you simply call `session.save(userObject)`. Hibernate generates and executes the SQL for you.
- Using Annotations (like `@Entity` and `@Table`), you link your Java class to a database table. Once linked, any changes you make to the object can be automatically synchronized with the database.
- It manages the safety of your data. If an error occurs during a multi-step process (like a bank transfer), Hibernate ensures all changes are rolled back so the data doesn't get corrupted.
- **Analogy:** Hibernate as a Language Translator at a business meeting: Java speaks "Objects," the Database speaks "SQL," and Hibernate ensures they understand each other perfectly without you having to learn both languages.


<br><br>
**What is ORM (Object Relational Mapping)?**
- Java is Object-Oriented (data is held in objects with relationships), while Databases are Relational (data is held in tables with rows and columns). They don't speak the same language.
- ORM is a technique that bridges this gap. It maps a Java Class to a Database Table and a Java Member Variable to a Table Column.
- Instead of writing SQL strings like `SELECT * FROM users`, you interact with the database by calling methods on Java objects.
- The ORM tool (like Hibernate) automatically generates the `INSERT`, `UPDATE`, and `DELETE` SQL commands whenever you save or modify a Java object.
- You define the rules of how the mapping happens using Annotations (like `@Entity`) or XML files. This tells the ORM exactly which table belongs to which class.
- **Analogy:** ORM is like an Excel-to-Object Converter: Imagine you have an Excel sheet (Database Table). Instead of manually typing into cells, you have a magic Java object. Whatever you type into the object's fields automatically appears in the correct row and column of the Excel sheet.


<br><br>
**What are the advantages of using Hibernate over JDBC?**
- In JDBC, you must write repetitive code to open connections, create statements, and loop through `ResultSet`. Hibernate handles all this "plumbing" automatically, letting you focus on business logic.
- JDBC forces you to think in terms of Tables and Columns (Relational). Hibernate allows you to think in Classes and Objects (Object-Oriented), which is natural for Java developers.
- JDBC uses raw SQL, which is often database-specific (e.g., specific syntax for pagination in MySQL vs. Oracle). Hibernate uses HQL (Hibernate Query Language) and Dialects, allowing you to change your database by simply updating a configuration file.
- Hibernate offers First-level (Session) and Second-level (SessionFactory) caching. This prevents unnecessary database calls if the data hasn't changed. JDBC hits the database every single time you execute a query.
- Hibernate supports Lazy Loading (fetch data only when needed) and Eager Loading (fetch everything at once). JDBC requires you to manually write separate queries to achieve this kind of control.
- Hibernate can automatically create or update your database schema based on your Java entities (`hbm2ddl`). In JDBC, you must manually create tables via SQL scripts before running the code.
- **Analogy:**
    - JDBC is like Manual Transmission: You have to control every gear change, the clutch, and the speed yourself. It gives you total control, but it's exhausting in heavy traffic (large projects).
    - Hibernate is like Automatic Transmission: You just tell the car to "Go" (Save/Get), and the car handles the gears and engine timing for you.


<br><br>
**What are the main components of Hibernate?**
1. **Configuration**:
    - This is the starting point. It reads your settings (like `hibernate.cfg.xml`) and database credentials to set up the environment. Its main job is to boot up the `SessionFactory`.
2. **SessionFactory**:
    - A heavy, thread-safe object that acts as a factory for Sessions. You usually create only one per database. It stores all the mapping information and is created once during application startup.
3. **Session**:
    - This is the "Unit of Work." It is a short-lived, non-thread-safe object that creates a physical connection to the database. You use it to perform CRUD operations (save, update, delete).
4. **Transaction**:
    - This manages the "all or nothing" logic. It ensures that if you are saving five records and the fourth one fails, the first three are also canceled to keep data consistent.
5. **Query & Criteria**:
    - These are the tools to find data. Query uses HQL (like SQL but uses Class names), while Criteria is a programmatic, type-safe way to build queries without writing strings.
6. **Mappings**
    - These are the "Rulebooks" (using `@Annotations` or XML) that tell Hibernate exactly which Java field goes into which database column.
7. **Analogy**
    - Configuration: The Owner who decides the menu and hires staff.
    - SessionFactory: The Kitchen. It’s always there, but it doesn't serve the customer directly.
    - Session: The Waiter. Each customer gets a waiter (Session) for their visit. The waiter takes orders (CRUD) and then leaves once the bill is paid.
    - Transaction: The Receipt. It ensures that every item you ordered is accounted for and paid, or nothing is processed.


<br><br>
**Explain the architecture of Hibernate.**
1. **Configuration (`org.hibernate.cfg.Configuration`)**
    - This class is used to "bootstrap" Hibernate. It reads the configuration file (`hibernate.cfg.xml`) to understand database details (URL, Username, Dialect).
    - Syntax: `Configuration cfg = new Configuration().configure();`
2. **SessionFactory (`org.hibernate.SessionFactory`)**
    - An immutable, thread-safe cache of compiled mappings. It is a "heavyweight" object created once per database. It acts as a factory for Sessions.
    - Syntax: `SessionFactory factory = cfg.buildSessionFactory();`
3. **Session (`org.hibernate.Session`)**
    - The "Unit of Work." It is a short-lived, non-thread-safe object that wraps a JDBC connection. It is used to perform CRUD operations.
    - Syntax: `Session session = factory.openSession();`
4. **Transaction (`org.hibernate.Transaction`)**
    - Used to define the boundaries of a unit of work. It ensures atomicity (all or nothing).
    - Syntax: `Transaction tx = session.beginTransaction(); ... tx.commit();`
5. **Query (`org.hibernate.query.Query`) & Criteria (`org.hibernate.Criteria`)**
    - Interfaces to fetch data. Query uses HQL (Hibernate Query Language), while Criteria provides a programmatic way to build queries.
6. **Persistent Objects (POJOs)**
    - Simple Java classes mapped to database tables using annotations (like `@Entity`).
7. **Analogy**
    - Hibernate Architecture is like a Bank: The SessionFactory is the Bank building (heavy, one per city), while the Session is the individual Teller you talk to for a specific transaction.
8. Example:
    ```java
    @Override
    public String registerUserWithGetCurrentSession(User user) {
        String message = "User reg failed...";
        // user: in java heap, state: TRANSIENT
        // get the session from session factory
        Session session = getFactory().getCurrentSession(); // jdbc connection will be obtained from this connection pool
        // begin a transaction
        Transaction tx = session.beginTransaction(); // db connection is pooled out -- wrapped in the session object, L1 cache is created
        try {
            // org.hibernate.Session API : public Serializable save(Object transientObjRef) throws HibernateException
            Serializable userId = session.save(user); // userId : created and used by Hibernate based on auto generation strategy // user ref is added in the cache, state: PERSISTENT
            tx.commit(); // upon commit, hibernate perform "automatic dirty checking" : compare state of L1 cache with that of db: automatically DML will be fired (insert query)
            // close the connection -> so that pooled out connection return to the pool and L1 cache is destroyed
            message = "user registration successfully with ID: " + userId;
        } catch (RuntimeException e) {
            if (tx != null) {
                tx.rollback();
            }
            throw e;
        }
        return message;
    }
    ```

<br><br>
**What is a Hibernate configuration file?**
1. **Core Concept**
    - The File (`hibernate.cfg.xml`): This is the "Brain" of your Hibernate setup. It is an XML-based file that tells Hibernate where the database is, how to talk to it, and which Java classes to manage.
    - It is traditionally placed in the `src/main/resources` folder of a Java project.
    - Hibernate uses this class (`org.hibernate.cfg.Configuration`) to read the XML file and build the `SessionFactory`.
2. **Key Properties & Elements**
    - `hibernate.dialect`: Tells Hibernate which SQL "flavor" to use. Since every DB has slight syntax differences (e.g., MySQL vs. PostgreSQL), the Dialect ensures Hibernate generates the correct SQL.
    - `hibernate.connection.url/username/password`: The standard JDBC credentials required to establish a physical connection to your database.
    - `hibernate.hbm2ddl.auto`: A powerful property that controls the database schema:
        - `create`: Drops existing tables and creates new ones.
        - `update`: Updates the existing schema (adds new columns).
        - `validate`: Checks if the Java code matches the DB but makes no changes.
    - `hibernate.show_sql`: A debugging property. When set to true, it prints the Hibernate-generated SQL in your console.
    - Mapping Resources: Includes the `<mapping class="..."/>` tags to register your `@Entity` classes.
    ```xml
    <?xml version = "1.0" encoding = "utf-8"?>
    <!DOCTYPE hibernate-configuration SYSTEM 
    "http://www.hibernate.org/dtd/hibernate-configuration-3.0.dtd">

    <hibernate-configuration>
    <session-factory>
        <property name="hibernate.connection.driver_class">com.mysql.cj.jdbc.Driver</property>
        <property name="hibernate.connection.url">jdbc:mysql://localhost:3306/my_db</property>
        <property name="hibernate.connection.username">root</property>
        <property name="hibernate.connection.password">password</property>

        <property name="hibernate.dialect">org.hibernate.dialect.MySQL8Dialect</property>

        <property name="hibernate.hbm2ddl.auto">update</property>

        <mapping class="com.example.model.User"/>
    </session-factory>
    </hibernate-configuration>
    ```
3. **Analogy**:
    - The Configuration file is like a GPS for a Driver: It provides the coordinates (URL), the keys (Username/Password), and the rules of the road (Dialect) so the driver (Hibernate) knows exactly where to go and how to behave.


<br><br>
**What is the role of SessionFactory in Hibernate?**
- It is a factory interface (`org.hibernate.SessionFactory`) used to create Session objects. It serves as the central hub where all Hibernate configurations are compiled and stored.
- It is thread-safe, meaning multiple threads can access it simultaneously without issues. Because of this, only one instance is typically created per database.
- It is "expensive" to create because it has to read the mapping files, parse the configuration, and build the metadata for every table in your database.
- It manages the database connection pool (like C3P0 or HikariCP), ensuring that the application can efficiently reuse database connections.
- It holds the Second-Level Cache, which is global. If one session loads data, the SessionFactory can store it so other sessions don't have to hit the database again for that same data.
- Once initialized, its configuration cannot be changed at runtime. To change a property, the application must be restarted to rebuild the factory.
- **Analogy**: The SessionFactory is like a Main Power Grid for a city: It is massive and expensive to build, but once it's running, it provides the electricity (Sessions) to every individual house in the city.


<br><br>
**What is the purpose of the Session interface in Hibernate?**
- It is the primary interface (`org.hibernate.Session`) used by Java applications to communicate with the database. It acts as a wrapper around a physical JDBC connection.
- It represents a single conversation with the database. It is short-lived and should be opened and closed for every task (e.g., one HTTP request).
- Unlike the `SessionFactory`, the `Session` should never be shared between multiple threads. If two threads use the same session, it can lead to data inconsistency and errors.
- **First-Level Cache**: The Session acts as a mandatory cache. Any object you save or load is stored here first, reducing the number of SQL queries sent to the database within that single session.
- **Key Operations & Syntax**
    - `save(Object obj)` / `persist(Object obj)`: Adds a new object to the database.
    - `update(Object obj)` / `merge(Object obj)`: Updates an existing record.
    - `get(Class, id)` / `load(Class, id)`: Retrieves a record by its Primary Key.
    - `delete(Object obj)`: Removes a record from the database.
    - `beginTransaction()`: Returns a Transaction object to manage the unit of work.
```java
@Override
    public String registerUserWithGetCurrentSession(User user) {
        String message = "User reg failed...";
        // user: in java heap, state: TRANSIENT
        // get the session from session factory
        Session session = getFactory().getCurrentSession(); // jdbc connection will be obtained from this connection pool
        // begin a transaction
        Transaction tx = session.beginTransaction(); // db connection is pooled out -- wrapped in the session object, L1 cache is created
        try {
            // org.hibernate.Session API : public Serializable save(Object transientObjRef) throws HibernateException
            Serializable userId = session.save(user); // userId : created and used by Hibernate based on auto generation strategy // user ref is added in the cache, state: PERSISTENT
            tx.commit(); // upon commit, hibernate perform "automatic dirty checking" : compare state of L1 cache with that of db: automatically DML will be fired (insert query)
            // close the connection -> so that pooled out connection return to the pool and L1 cache is destroyed
            message = "user registration successfully with ID: " + userId;
        } catch (RuntimeException e) {
            if (tx != null) {
                tx.rollback();
            }
            throw e;
        }
        return message;
    }
```
- **Analogy**: The Session is like a Shopping Cart: You put items in (save), remove them (delete), or change them (update) during your visit. Once you "Check out" (Commit/Close), the final state is recorded in the store's system (Database).



<br><br>
**What are the different states of a Hibernate object?**
1. **Transient**
    - An object is created using the new keyword but is not yet associated with a Session i.e., is in Heap
    - It has no corresponding row in the database.
2. **Persistent**
    - The object is associated with an active `org.hibernate.Session` and also part of L1 cache. Hibernate "tracks" this object.
    -  It has a corresponding row in the database.
    - Any changes made to the object’s setters (e.g., `user.setName("New Name")`) will be automatically updated in the database upon commit (Dirty Checking).
3. **Detached State**
    - An object that was previously persistent but the `Session` has been closed or the object was explicitly removed from the session cache.
    - The row still exists in the database, but Hibernate is no longer watching the object for changes.
4. **Removed State**
    - An object marked for deletion via `session.delete()`.
    - The row still exists until the transaction is committed, at which point it is deleted from the table.
5. **Analogy**
    - Transient: Typing in a Notepad on your desktop (not saved anywhere).
    - Persistent: Typing in a Google Doc while online (every change is auto-saved).
    - Detached: Closing the browser tab (the doc exists, but changes you make offline won't sync).


<br><br>
> #### 💡 Interview Prep: Hibernate Dirty Checking
> **The Mechanism:** When an entity becomes **Persistent**, Hibernate stores a **Snapshot** (an exact copy) of its initial state in the **First-Level Cache (Session)**.
> 
> **The Detection:** During `session.flush()` or `transaction.commit()`, Hibernate performs a process called **Dirty Checking**. It compares the current state of the object against the stored snapshot.
> 
> **The Result:** If a difference is found (the object is "dirty"), Hibernate automatically schedules and executes the necessary `UPDATE` SQL statement.


<br><br>
**Explain the difference between load() and get() methods in Hibernate.**
1. **Database Hit**
    - `get()`: Hits the database immediately. It executes the SELECT query as soon as the method is called.
    - `load()`: Does not hit the database immediately. It returns a Proxy (a hollow placeholder) and only hits the database when you actually access a property of the object (like `user.getName()`).
2. **Return Value vs. Exception**
    - `get()`: Returns null if the record is not found in the database.
    - `load()`: Never returns null. It always returns a proxy. However, if the record doesn't exist in the DB, it throws an `ObjectNotFoundException` the moment you try to access the proxy's data.
3. **When to use**
    - `get()`: Use when you are not sure if the record exists and want to perform a null check.
    - `load()`: Use when you are certain the record exists, or when you just need the object's ID to establish a relationship.
```java
import org.hibernate.Session;
import org.hibernate.ObjectNotFoundException;

// Package: org.hibernate.Session
Session session = sessionFactory.openSession();

// 1. Using get()
User user1 = session.get(User.class, 1); 
if(user1 != null) {
    System.out.println(user1.getName());
}

// 2. Using load()
User user2 = session.load(User.class, 2); 
try {
    // Database is hit ONLY at this line
    System.out.println(user2.getName()); 
} catch (ObjectNotFoundException e) {
    System.out.println("User not found!");
}
```
4. **Analogy**
    - `get()` is like going to a restaurant and ordering a burger: you wait until the burger is cooked and served to you.
    - `load()` is like getting a token from the counter: you have the "promise" of a burger, but the actual cooking only starts when you present the token to the chef.


<br><br>
**What are the basic annotations used in Hibernate?**
- `@Entity` (`jakarta.persistence.Entity`):
  - Marks a Java class as a "Persistent Object." Hibernate will treat this class as a table in the database.
  - Every entity must have a no-argument constructor.
- `@Table` (`jakarta.persistence.Table`):
  - Specifies the exact name of the database table. If not used, Hibernate assumes the table name is the same as the class name.
  - `@Table(name = "users_data")`
- `@Id` (`jakarta.persistence.Id`):
  - Defines the Primary Key of the entity. Every `@Entity` must have exactly one `@Id`.
- `@GeneratedValue` (`jakarta.persistence.GeneratedValue`):
  - Specifies how the Primary Key should be generated (e.g., Auto-increment).
  - `IDENTITY` (DB handles it), `SEQUENCE` (uses DB sequences), `AUTO` (Hibernate decides).
- `@Column` (`jakarta.persistence.Column`):
  - Maps a field to a specific database column. You can define constraints like `length`, `nullable`, and `unique`.
  - `@Column(name = "user_email", nullable = false, length = 100)`
- `@Transient` (`jakarta.persistence.Transient`):
  - Tells Hibernate not to map this field to the database. It exists only in Java.

#### Relationship & Advanced Annotations
- `@OneToMany` / `@ManyToOne` / `@OneToOne`: Defines the cardinality between two entities (e.g., One Department has Many Employees).
- `@JoinColumn`: Defines the Foreign Key column in the table that connects to the related entity.
- `@Embeddable` / `@Embedded`: Used for Component Mapping.
  - `@Embeddable` is put on a class (like `Address`) that doesn't have its own table.
  - `@Embedded` is used in the main Entity to include those fields into the same table.
- `@Version`: Used for Optimistic Locking. Hibernate checks this version number before updating to prevent two users from overwriting each other's changes.

```java
// jpa compliant - Hibernate independent
// For Hibernate, Pojo - classes need not be serializable, the primary key property should be serializable
@Entity // mandatory class level annotation -> telling jvm this has to be persistent
@Table(name = "users_tbl") // specifies table name
public class User {
    @Id // mandatory specify the primary key of an entity
//    @GeneratedValue // Hibernate chooses the default db specific strategy for automatic primary key generation
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto_increment constraint suitable for MySQL DB
    @Column(name = "user_id")
    private Integer userId;

    @Column(length = 20) // varchar(20)
    private String name;

    @Column(length = 20, unique = true) // varchar(20), unique constraint
    private String email;

    @Column(length = 15, nullable = false) // varchar(15), NOT NULL constraint
    private String password;

    @Enumerated(EnumType.STRING) // column type varchar(20)
    @Column(name = "user_role", length = 20)
    private UserRole userRole;

    @Transient // skips from persistent (no corresponding column)
    private String confirmPassword;

    @Column(name = "reg_amount")
    private double regAmount;

    @Column(name = "reg_date")
    private LocalDate regDate; // column type : date

    @Lob // column type blob : medium blob
    private byte[] image;

    // must supply a default constructor
    public User() {
        System.out.println("In user constructor");
    }
}
```


<br><br>
**What is the significance of the `@Entity` annotation?**
- The `@Entity` annotation is one of the most important annotations in Hibernate, as it marks a class as a persistent entity. 
- When a Java class is annotated with `@Entity`, Hibernate recognizes that class as an entity and will automatically map it to a table in the database.
- It serves as a marker to tell Hibernate to handle this class in terms of ORM.
- Key Points:
  - It must be placed on a Java class.
  - The class must have a no-argument constructor (either explicit or implicit) to allow Hibernate to instantiate the object.
  - If no table name is provided, Hibernate will use the class name as the table name by default.
  - The `@Entity` annotation is typically used in conjunction with other annotations, such as `@Id` to define the primary key, and `@Table` to define the table name explicitly.


<br><br>
**What is `@Id` annotation used for?**
- This is a mandatory annotation used to define the Primary Key of an entity. Hibernate uses this field to uniquely identify, manage, and cache objects.
- Every class marked with `@Entity` must have exactly one `@Id` annotation (unless using a Composite Key). Without it, Hibernate will throw a `MappingException` at startup.
- It maps the Java field to the column in the database that has the `PRIMARY KEY` constraint.
- Hibernate uses the ID to check the First-Level Cache. Before hitting the database, it checks if an object with that specific ID already exists in the current `Session`.
- In Hibernate, two objects are considered "the same" if they have the same class type and the same ID value, even if their other fields (like name or salary) are different.


<br><br>
**What is the purpose of `@Table` annotation?**
- The `@Table` annotation is used to define the database table that a Hibernate entity is mapped to.
- It is not mandatory. If you don't use it, Hibernate follows a naming convention where the table name exactly matches the Java class name (e.g., class `User` maps to table `User`).
- It is primarily used when the Java class name and the Database table name are different (e.g., your class is named `Employee` but the existing database table is named `EMP_MASTER`).
- **Key Attributes of @Table**
  - `name`: Defines the actual name of the table in the database.
  - `schema`: Specifies the schema name if the table belongs to a specific one (e.g., `HR` or `FINANCE`).
  - `uniqueConstraints`: Allows you to define constraints that span multiple columns (e.g., the combination of `dept_id` and `emp_code` must be unique).
  - `indexes`: Used to define database indexes directly from the Java code to improve search performance.


<br><br>
**What is the role of @GeneratedValue in Hibernate?**
- The `@GeneratedValue` annotation is used in conjunction with the `@Id` annotation to define how the primary key of an entity is generated.
- Hibernate provides several strategies to generate the primary key value automatically:
- **Generation Strategy**: You can specify the strategy used to generate the primary key using the strategy attribute. Common strategies include:
  - `GenerationType.AUTO`: Let Hibernate choose the appropriate strategy based on the database.
  - `GenerationType.IDENTITY`: Use an auto-incrementing column for primary key generation.
  - `GenerationType.SEQUENCE`: Use a database sequence to generate primary keys (usually for databases like PostgreSQL, Oracle).
- **Name**: The name attribute specifies the name of the sequence or table used for generating values, particularly for `GenerationType.SEQUENCE` and `GenerationType.TABLE`.


<br><br>
> #### 💡 Deep Dive: The naming strategy
>In an interview, you might be asked: "What if I want all my tables to be lowercase and have underscores?" Deep Explanation: While @Table(name = "...") works for individual classes, Hibernate also uses a PhysicalNamingStrategy. This is a global setting that can automatically convert CamelCase Java names into snake_case database names. If you use @Table, you are manually overriding this strategy for that specific class.


<br><br>
**What are the types of inheritance mapping in Hibernate?**

Hibernate supports three types of inheritance mapping strategies to map the inheritance relationship in Java to a relational database:
1. **Single Table Strategy (`InheritanceType.SINGLE_TABLE`)**
   1. All classes in the hierarchy (Parent and all Children) are stored in one single table.
   2. Hibernate uses a special column (e.g., `DTYPE`) to store the "type" of the record so it knows which Java class to instantiate.
   3. Pros: High performance (no joins required); simplest to query.
   4. Violates data normalization; columns belonging to specific subclasses must allow NULL values because other subclasses won't use them.
   ```java
    @Entity
    @Inheritance(strategy = InheritanceType.SINGLE_TABLE)
    @DiscriminatorColumn(name = "emp_type", discriminatorType = DiscriminatorType.STRING)
    public class Employee { 
        @Id @GeneratedValue private Long id;
        private String name;
    }

    @Entity
    @DiscriminatorValue("MGR")
    public class Manager extends Employee {
        private String department;
    }
   ```

2. **Joined Table Strategy (`InheritanceType.JOINED`)**
   1. Each class has its own table. The parent table contains common fields, and subclass tables contain only specific fields plus a Foreign Key pointing to the parent.
   2. Most normalized approach; no wasted space with NULL columns.
   3. Performance overhead due to JOIN operations when fetching data.
   ```java
    @Entity
    @Inheritance(strategy = InheritanceType.JOINED)
    public class Vehicle {
        @Id @GeneratedValue private Long id;
        private String fuelType;
    }

    @Entity
    public class Car extends Vehicle {
        private int seatingCapacity; // This goes to a separate 'Car' table
    }
   ```

3. **Table Per Class Strategy (`InheritanceType.TABLE_PER_CLASS`)**
   1. Every concrete class gets its own table. Unlike Joined, each table contains all columns (both parent and child fields). There is no "Parent" table in the database.
   2. Clean for querying a specific subclass directly.
   3. Data redundancy (duplicate columns across tables); very expensive to run polymorphic queries (e.g., `SELECT * FROM Employee` requires a `UNION`).
   ```java
    @Entity
    @Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
    public class Employee {
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        private Long id;
        private String name;
    }

    @Entity
    public class Manager extends Employee {
        private String department;
    }
   ```

<br><br>
**How do you map a one-to-many relationship in Hibernate?**

In Hibernate, a One-to-Many relationship represents a scenario where one record in a table (e.g., Department) is associated with multiple records in another table (e.g., Employees).

- This is usually Bidirectional. One side has `@OneToMany` and the other has `@ManyToOne`.
- The entity with the `@ManyToOne` annotation is the Owner of the relationship. It physically holds the Foreign Key in the database.
- The entity with the `@OneToMany` annotation uses the `mappedBy` attribute to tell Hibernate that the relationship is already managed by the other side.
- Foreign Key (`@JoinColumn`): This annotation is placed on the "Many" side to define the name of the column that will link the two tables.
- The Many Side (Owner)
  ```java
    @Entity
    public class Employee {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @ManyToOne // Many employees for one department
        @JoinColumn(name = "dept_id") // The FK column name in EMP table
        private Department department;
    }
  ```
- The "One" Side (The Inverse)
  ```java
    @Entity
    public class Department {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        // mappedBy points to the VARIABLE name in the Employee class
        @OneToMany(mappedBy = "department", cascade = CascadeType.ALL)
        private List<Employee> employees = new ArrayList<>();
    }
  ```


<br><br>
**What is the difference between a persistent object and a transient object in Hibernate?**

In Hibernate, the primary difference between Transient and Persistent states lies in whether the object is currently "known" and "tracked" by the Hibernate Session.

- **Association with Session (`org.hibernate.Session`):**
  - **Transient**: Not associated with any Session. It is just a regular Java object.
  - **Persistent**: Actively associated with a Session. Hibernate maintains a "handle" on this object.
- **Database Representation:**
  - **Transient**: Does not have a corresponding row in the database.
  - **Persistent**: Has a corresponding row in the database, and its ID field is populated.  
- **Dirty Checking (Automatic Sync):**
  - **Transient**: Hibernate ignores it. If you change a value (e.g., `user.setName("New")`), nothing happens in the DB.
  - **Persistent**: Hibernate tracks all changes. If you call a setter, Hibernate automatically detects the change and updates the database during the next flush or commit.
- **Lifecycle Stage:**
  - **Transient**: Created using the new keyword but not yet passed to save(), persist(), or saveOrUpdate().
  - **Persistent**: Created after calling `session.save()`, `session.persist()`, or after being retrieved from the DB using `session.get()`.
- **Analogy**
  - A Transient object is like a draft email on your computer that hasn't been uploaded yet; a Persistent object is like a Google Doc—every time you type a letter, it is automatically synced to the server.

