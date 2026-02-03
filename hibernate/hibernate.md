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



<br><br>
**How do you retrieve data from the database using Hibernate?**

There are several ways to retrieve data from the database using Hibernate. The most common methods are:

1. **Using HQL (Hibernate Query Language)**: 
   - An object-oriented query language. You write queries against Java Classes and Properties, not database tables and columns.
   - Interface: `org.hibernate.query.Query`
   - Best for static, readable queries involving joins and complex logic.
    ```java
    // Package: org.hibernate.query.Query
    Query<Employee> query = session.createQuery("from Employee where dept = :d", Employee.class);
    query.setParameter("d", "IT");
    List<Employee> results = query.getResultList();
    ```

2. **Using Criteria API:**
    - The Criteria API is used to build dynamic queries programmatically. 
    - It is especially useful when building queries based on conditions that are determined at runtime.
    - Interface: `jakarta.persistence.criteria.CriteriaQuery` (or the legacy `org.hibernate.Criteria`)
    - Best for Dynamic Search screens where filters (like "Min Salary", "Name") are optional and decided at runtime.
    ```java
    // Package: jakarta.persistence.criteria.*
    CriteriaBuilder cb = session.getCriteriaBuilder();
    CriteriaQuery<Employee> cq = cb.createQuery(Employee.class);
    Root<Employee> root = cq.from(Employee.class);
    cq.select(root).where(cb.gt(root.get("salary"), 50000));

    List<Employee> results = session.createQuery(cq).getResultList();
    ```

3. **Using Native SQL:**
   - Allows execution of raw database-specific SQL.
   - Interface: `org.hibernate.query.NativeQuery`
   - Used as a last resort for database-specific features (like window functions or stored procedures) that HQL doesn't support.
    ```java
    // Package: org.hibernate.query.NativeQuery
    NativeQuery<Employee> nq = session.createNativeQuery("SELECT * FROM emp_table WHERE sal > 50000", Employee.class);
    List<Employee> results = nq.list();
    ```

4. **Using get() and load()**
    - Used to fetch a single object based on its Primary Key.
    - Interface: `org.hibernate.Session`
    - Best for simple lookups where the ID is known.

<br><br>
**What is the Criteria API in Hibernate?**

The Criteria API provides a programmatic, type-safe way to define queries. It is the preferred choice when queries need to be built dynamically based on user input.

- It is an object-oriented API used to define queries. Instead of writing query strings (like HQL or SQL), you use Java methods to add "Restrictions" or "Conditions."
- Since it uses Java classes and methods, errors (like misspelling a field name) can often be caught at compile-time rather than runtime.
- It is ideal for "Search Screens" where a user might fill in some filters (e.g., Min Price, Category) and leave others blank. You can add conditions to the Criteria object using `if` statements.
- Modern (JPA Standard): `jakarta.persistence.criteria.CriteriaQuery` used along with `jakarta.persistence.criteria.CriteriaBuilder`.

```java
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import jakarta.persistence.criteria.Predicate;
import org.hibernate.Session;
import org.hibernate.query.Query;

public List<Employee> findDynamicEmployees(String name, Double minSalary) {
    Session session = sessionFactory.openSession();
    
    // 1. Initialize Builder and Query
    CriteriaBuilder cb = session.getCriteriaBuilder();
    CriteriaQuery<Employee> cq = cb.createQuery(Employee.class);
    Root<Employee> root = cq.from(Employee.class); // FROM Employee

    // 2. Build Dynamic Conditions
    List<Predicate> predicates = new ArrayList<>();
    
    if (name != null) {
        predicates.add(cb.equal(root.get("name"), name));
    }
    if (minSalary != null) {
        predicates.add(cb.gt(root.get("salary"), minSalary));
    }

    // 3. Apply WHERE clause and Execute
    cq.where(predicates.toArray(new Predicate[0]));
    Query<Employee> query = session.createQuery(cq);
    
    return query.getResultList();
}
```


<br><br>
**What is the difference between HQL and SQL in Hibernate?**

Hibernate Query Language (HQL) is an object-oriented query language similar to SQL but operates on Java objects (entities) rather than database tables. Here’s a breakdown of the key differences between HQL and SQL:

1. **Object-Oriented vs. Relational:**
    - **HQL** works with persistent objects (entities) and their properties, not directly with database tables or columns.
    - **SQL** is directly concerned with the database schema (tables, columns, joins, etc.).
2. **Database Independence:**
    - **HQL** abstracts the underlying database, meaning you can write a query in HQL and Hibernate will generate the appropriate SQL for the specific database being used.
    - **SQL** is database-specific, meaning queries need to be written in a way that matches the SQL dialect of the specific database.
3. **Use of Aliases:**
    - **HQL** allows you to work with object aliases. For example, when querying, you can refer to the entity and its properties.
    - **SQL** requires you to use table and column names.


<br><br>
**What is a Query in Hibernate and how does it work?**

- A Query in Hibernate is an interface used to create and execute queries to retrieve or manipulate data in the database. 
- Hibernate provides two main ways to create queries: using HQL (Hibernate Query Language) or using the Criteria API. 
- The Query interface is used to execute these queries.
- **Key points**:
  - **Creating a query**: A query is created using the session.createQuery() method for HQL queries, or session.createCriteria() for the Criteria API.
  - **Setting parameters**: You can pass parameters to the query using methods like setParameter().
  - **Executing a query**: Once the query is created, you can execute it using methods like `list()` to retrieve a list of results, or `uniqueResult()` to get a single result.
- **Example**:
    ```java
    Session session = sessionFactory.openSession();
    Query query = session.createQuery("FROM Employee WHERE salary > :salary");
    query.setParameter("salary", 50000);
    List<Employee> employees = query.list();
    session.close();
    ```

<br><br>
**What are the different types of caching mechanisms in Hibernate?**

Hibernate provides several levels of caching to improve performance by reducing the number of database queries and storing frequently accessed data in memory.

1. **First-Level Cache (Session Cache):**
   - It is a mandatory cache and exists for the duration of a Hibernate session.
   - The first-level cache is associated with a session, meaning it stores entities and their state for the lifetime of that session.
   - It is not shared across sessions, and when a session is closed, the cache is cleared.
2. **Second-Level Cache (SessionFactory Cache):**
   - The second-level cache is optional and is shared across multiple sessions. It caches entities, collections, and queries at the session factory level.
   - The second-level cache can be configured to use different caching providers, such as EHCache, Infinispan, or OSCache.
   - It improves performance by reducing database access, especially when the same data is frequently queried across multiple sessions.
3. **Query Cache:**
   - The query cache is a cache for storing the results of queries, which is particularly useful when running the same query repeatedly with different parameters.
   - It is not enabled by default and needs to be explicitly configured alongside the second-level cache.
4. **Shared Cache (Hibernate Cache):**
   - The shared cache allows for caching at the transaction level, beyond just individual sessions or query results.
```xml
<property name="hibernate.cache.region.factory_class">org.hibernate.cache.ehcache.EhCacheRegionFactory</property>
<property name="hibernate.cache.use_second_level_cache">true</property>
<property name="hibernate.cache.use_query_cache">true</property>
<property name="hibernate.cache.provider_class">org.hibernate.cache.EhCacheProvider</property>
```


<br><br>
**What is the purpose of the flush() method in Hibernate?**

- The `flush()` method in Hibernate is used to synchronize the in-memory state of the session with the database.
- When you perform changes to entities (e.g., `save`, `update`, `delete`), Hibernate does not immediately write those changes to the database. 
- Instead, it maintains them in the first-level cache. Calling `flush()` forces Hibernate to persist those changes to the database.
- Key Points
  - Flush does not commit a transaction. It only synchronizes the session’s state with the database.
  - Hibernate flushes automatically when a transaction is committed.
  - It can be invoked manually if you need to force synchronization before committing.
  ```java
    Session session = sessionFactory.openSession();
    session.beginTransaction();
    Employee employee = session.get(Employee.class, 1L);
    employee.setSalary(60000);
    session.flush();  // Forces the update to be written to the database immediately
    session.getTransaction().commit();
    session.close();
  ```


<br><br>
**What is lazy loading and eager loading in Hibernate?**

Lazy Loading and Eager Loading are two different strategies for loading associated entities in Hibernate.
1. **Lazy Loading**
   - In lazy loading, associated entities are **loaded on demand**. They are not fetched from the database until they are explicitly accessed.
   - This strategy can improve performance by deferring unnecessary database calls, but it can lead to the **N+1 select problem** if not handled carefully.
   - Example:
   ```java
    @ManyToOne(fetch = FetchType.LAZY)
    private Department department;
   ```

2. **Eager Loading:**
   - In eager loading, associated entities are loaded immediately with the parent entity. The data for the associated entities is retrieved in the same query as the parent.
   - It can result in better performance when you know that the associated data will be needed immediately but can lead to fetching more data than required.
   ```java
    @ManyToOne(fetch = FetchType.EAGER)
    private Department department;
   ```


<br><br>
**How do you handle transactions in Hibernate?**

A Transaction represents a logical unit of work that ensures data integrity by following the ACID properties (Atomicity, Consistency, Isolation, Durability).

- **`org.hibernate.Transaction`:** This interface is used by the application to define the boundaries of a unit of work. It abstracts the underlying transaction implementation (JDBC or JTA).
- The primary goal is to ensure that if you are performing multiple database operations, either all of them succeed together or none of them are applied.
- A Transaction is associated with a `Session`. You obtain it by calling `session.beginTransaction()`.
- **Commit vs. Rollback**:
  - `commit()`: Permanently saves the changes to the database.
  - `rollback()`: Undoes all changes made during the current transaction if an error occurs.
- **Analogy**: A Transaction is like an ATM Withdrawal: The system must deduct money from your account and give you the cash; if it fails to give you the cash, it must "rollback" and put the money back in your account.

<br><br>
> #### 💡Deep Dive: ACID Properties in Hibernate
> 
> In an interview, you should mention how Hibernate helps maintain ACID:
> - **Atomicity**: Through `commit()` and `rollback()`.
> - **Consistency**: Hibernate ensures the Persistence Context (First-level cache) matches the database state after a successful commit.
> - **Isolation**: Hibernate relies on the underlying database's isolation levels but provides Versioning (@Version) to handle concurrent updates (Optimistic Locking).
> - **Durability**: `Once commit()` is called, Hibernate ensures the data is flushed to the physical database.


<br><br>
**What is a first-level cache in Hibernate?**

- The first-level cache in Hibernate is the session cache and is enabled by default for each session.
- It is a cache that stores entities and their state during the lifecycle of the Session. 
- Once an entity is retrieved or persisted, it is stored in the first-level cache, and subsequent requests for the same entity within the session will retrieve it from this cache, rather than querying the database again.
- **Key Characteristics:**
  - **Session-scoped**: The cache is tied to the Session and is cleared when the session is closed.
  - **No configuration required**: It is automatically handled by Hibernate.
  - **Entity identity**: Only one instance of an entity is associated with a session for a given identifier, which ensures that any changes to an entity are tracked.


<br><br>
**What is the purpose of the `@JoinColumn` annotation?**

- The `@JoinColumn` annotation is used in Hibernate (and JPA) to specify the column that is used to join two entities in a relationship (usually for one-to-many, many-to-one, and many-to-many mappings). 
- It defines the foreign key column in the database that refers to the associated entity.
- Key Points:
  - It is used to specify the foreign key column for an entity's relationship.
  - It is commonly used with many-to-one and one-to-one relationships.
  - It can specify attributes like the column name, nullable constraint, and uniqueness.
- Example:
  ```java
    @Entity
    public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String name;

    @ManyToOne
    @JoinColumn(name = "department_id") // Foreign key column in the "employee" table
    private Department department;
    }
  ```
- In this example, the @JoinColumn annotation specifies that the department_id column in the employee table is used as the foreign key to link to the Department entity.


<br><br>
**How do you map a many-to-many relationship in Hibernate?**

Many-to-Many relationship occurs when multiple records in one table are associated with multiple records in another (e.g., Students and Courses).

- **The Join Table**: Relational databases cannot directly link two tables in a many-to-many fashion. Hibernate automatically creates a Third Table (Join Table) that holds the Primary Keys from both sides as Foreign Keys.
- **The Owner Side**: One entity is designated as the "owner." This side uses `@JoinTable` to define the name and columns of the middle table.
- **The Inverse Side**: The other entity uses the `mappedBy` attribute to point to the field in the owner class. This prevents Hibernate from creating two separate join tables.
- **Collection Type**: It is highly recommended to use a Set instead of a List to avoid duplicate entries in the join table and for better performance.
- **The Owner Side (Student)**
  ```java
    @Entity
    public class Student {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @ManyToMany
        @JoinTable(
            name = "student_course_map", // Name of the 3rd table
            joinColumns = @JoinColumn(name = "std_id"), // FK for Student
            inverseJoinColumns = @JoinColumn(name = "crs_id") // FK for Course
        )
        private Set<Course> courses = new HashSet<>();
    }
  ```
- **The Inverse Side (Course)**
  ```java
    @Entity
    public class Course {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        // mappedBy refers to the "courses" variable in Student class
        @ManyToMany(mappedBy = "courses")
        private Set<Student> students = new HashSet<>();
    }
  ```

<br><br>
> #### 💡What is the difference between joinColumns and inverseJoinColumns?
> - Deep Explanation: Inside the `@JoinTable` annotation:
>   - `joinColumns`: Refers to the Foreign Key of the Owner entity (the class where you are writing the annotation).
>   - `inverseJoinColumns`: Refers to the Foreign Key of the Target (other) entity.
> - If you don't provide `@JoinTable`, Hibernate will generate one for you using its default naming strategy (e.g., `Student_Course`).


<br><br>
**What is a second-level cache in Hibernate?**

- It is an optional, SessionFactory-scoped cache. While the first-level cache is restricted to a single session, the second-level cache is global and available to all sessions in the application.
- If Session 1 fetches an Employee with ID 101, it is stored in the Second-Level Cache. If Session 2 later asks for the same Employee, Hibernate retrieves it from this cache instead of hitting the database.
- Hibernate does not provide its own implementation for this cache. It provides an interface that allows you to plug in third-party providers like EHCache, Infinispan, or Hazelcast.
- You must explicitly mark which entities should be cached using the `@Cacheable` annotation. Not all data should be cached (e.g., frequently changing data).
- It significantly improves performance for Read-Heavy applications by reducing the number of expensive network calls to the database.
- **Syntax & Configurations**
  - **Dependency (Example: EHCache)**
    - **Package**: **`org.hibernate.orm:hibernate-ehcache`**
  - **Configuration (hibernate.cfg.xml)**
    ```xml
    <property name="hibernate.cache.use_second_level_cache">true</property>

    <property name="hibernate.cache.region.factory_class">
        org.hibernate.cache.jcache.internal.JCacheRegionFactory
    </property>
    ```
  - **Entity Mapping**
    ```java
    import jakarta.persistence.Entity;
    import jakarta.persistence.Cacheable;
    import org.hibernate.annotations.Cache;
    import org.hibernate.annotations.CacheConcurrencyStrategy;

    @Entity
    @Cacheable
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE) // Strategy
    public class Product {
        @Id
        private Long id;
        private String name;
    }
    ```

<br><br>
**What is the difference between save() and persist() methods in Hibernate?**

1. **Return Value:**
   - `save()`: Returns the Identifier (Primary Key) of the entity immediately. To do this, it may force an INSERT statement right away if using an IDENTITY generator.
   - `persist()`: Has a void return type. It does not return the ID.
2. **API Origin:**
   - `save()`: A native Hibernate method (`org.hibernate.Session`).
   - `persist()`: A JPA standard method (`jakarta.persistence.EntityManager`), making it better for application portability.
3. **Handling Detached Objects:**
   - `save()`: If called on a Detached object, it creates a new row in the database with a new ID (it treats it as a new entity).
   - `persist()`: If called on a Detached object, it throws an `EntityExistsException` or `PersistentObjectException`. It strictly demands a Transient (new) object.
4. **Transaction Context:**
   - `save()`: Can be called outside a transaction boundary; it will still return an ID, though the data won't be saved until a transaction starts.
   - `persist()`: Strictly follows JPA rules; it ensures the object is not saved if called outside a transaction.
   ```java
    Session session = sessionFactory.openSession();

    // NO TRANSACTION STARTED YET
    User user = new User("Swapnil");
    Long id = (Long) session.save(user); 
    System.out.println("I have an ID: " + id); // This prints 101

    // If I stop here and close the session, 'Swapnil' is NOT in the database.

    session.beginTransaction(); 
    // ... perform other work ...
    session.getTransaction().commit(); // NOW the data is permanently saved.
    session.close();
   ```
5. **Analogy**
   - `save()` is like calling the hotel and getting a Confirmation Number. You feel good because you have a number, but if you never show up with your credit card (the Transaction) to pay, you don't actually have a room.
   - `persist()` is like the hotel website saying, "You cannot even get a confirmation number until you enter your credit card details" (the Transaction). It forces the "payment" step to be part of the process.


<br><br>
**What is the role of HibernateUtil class?**

- The HibernateUtil class is a utility class that is typically used to configure and initialize the Hibernate SessionFactory.
- It ensures that only one instance of SessionFactory exists for the entire application.
- It uses a `static` block to configure and build the factory as soon as the class is loaded, ensuring the database connection settings are validated immediately at startup.
- It serves as a single place to handle `hibernate.cfg.xml` or programmatic mapping (like `addAnnotatedClass`).
- It provides a `shutdown()` method to close the SessionFactory and the underlying connection pool when the application stops, preventing memory leaks.
  ```java
  // bootstraping hibernate application
    public class HibernateUtils {
        private static SessionFactory factory;

        // how to ensure creation of singleton instance of the session factory ? Singleton and Eager
        static {
            System.out.println("In the static initializer block");
            // booting hibernate using the hibernate.cfg.xml and building the session factory
            factory = new Configuration().configure().buildSessionFactory();
        }

        // getter for SessionFactory, return only single/same instance
        public static SessionFactory getFactory() {
            return factory;
        }
    }
  ```

<br><br>
**What is a Detached object in Hibernate?**

- A detached object in Hibernate is an object that was once persistent but is no longer associated with a Session. 
- A detached object is an entity that has been saved to the database and then the Session has been closed or the object has been evicted.
- **Key points:**
  - Detached objects are not synchronized with the session's persistence context.
  - You can reattach a detached object to a new session using methods like `update()`, `merge()`, or `saveOrUpdate()`.
- Example:
  ```java
    // Persistent object
    Session session = sessionFactory.openSession();
    Employee emp = session.get(Employee.class, 1);
    session.close();

    // Detached object (after session is closed)
    Employee detachedEmp = emp;
  ```
- To reattach the detached object:
  ```java
    Session session2 = sessionFactory.openSession();
    session2.beginTransaction();
    session2.update(detachedEmp); // or session2.merge(detachedEmp);
    session2.getTransaction().commit();
    session2.close();
  ```

<br><br>
**How do you perform batch processing in Hibernate?**

- Batch processing in Hibernate allows you to execute multiple `insert`, `update`, or `delete` operations in a single database round-trip. 
- This can significantly improve performance when working with large amounts of data.
- To enable batch processing in Hibernate:
    1. Set the appropriate batch size in the configuration file.
    2. Use `Session.save()` or `Session.update()` in a loop, and periodically flush and clear the session to prevent memory issues.
- Example of enabling batch processing:
  ```xml
    <property name="hibernate.jdbc.batch_size">50</property>
    <property name="hibernate.order_inserts">true</property>
    <property name="hibernate.order_updates">true</property>
    <property name="hibernate.batch_versioned_data">true</property>
  ```
- Example of using batch processing in code:
  ```java
    Session session = sessionFactory.openSession();
    Transaction transaction = session.beginTransaction();
    for (int i = 0; i < 1000; i++) {
        Employee emp = new Employee("Employee" + i);
        session.save(emp);
        
        if (i % 50 == 0) { // Flush every 50 records
            session.flush();
            session.clear();
        }
    }
    transaction.commit();
    session.close();
  ```
- In this example, after every 50 operations, the session is flushed and cleared to free memory and execute the batch efficiently.


<br><br>
**How can you optimize the performance of a Hibernate-based application?**

To optimize the performance of a Hibernate-based application, consider the following strategies:

1. **Use Lazy Loading:**
   - Use lazy loading (FetchType.LAZY) to defer (delay) fetching related entities until they are actually accessed, reducing unnecessary database queries.
2. **Optimize Queries:**
   - Use HQL (Hibernate Query Language) or Criteria API to create efficient queries. Avoid N+1 select issues by using join fetch in HQL or Criteria queries to fetch related entities in a single query.
3. **Enable Caching:**
   - Enable second-level cache and query cache to reduce the number of database queries, especially for frequently accessed data.
4. **Batch Processing:**
   - For large amounts of data, use batch processing to reduce the number of database round-trips.
5. **Optimize Database Access:**
   - Minimize unnecessary database operations (e.g., redundant `flush()` or `clear()` calls). Use the `session.flush()` method only when necessary.
6. **Use Projection or DTO Pattern:**
   - Instead of loading entire entities, use projections or Data Transfer Object (DTO) pattern to fetch only the required fields.
7. **Proper Indexing:**
   - Ensure your database tables have proper indexes to speed up lookups, particularly for frequently queried columns.
8. **Use Connection Pooling**
   - Use a connection pooling mechanism (e.g., C3P0, HikariCP) to efficiently manage database connections.


<br><br>
**What is the difference between session.save() and session.saveOrUpdate()?**

The methods save() and saveOrUpdate() are used to persist objects in Hibernate, but they have key differences:

* **`save()`:**
  * The save() method is used to persist a new object to the database. It returns the identifier (ID) of the saved entity.
  * If the entity is already persistent (i.e., it already exists in the session or database), it will throw an exception (`org.hibernate.PersistentObjectException`).
  * Example:
    ```java
    session.save(employee);  // Saves a new employee to the database
    ```
* **`saveOrUpdate()`:**
  * The `saveOrUpdate()` method is more flexible. It either saves a new entity or updates an existing one if the entity already exists (based on the ID).
  * If the entity is already in the database, Hibernate performs an update. If the entity is not in the database, Hibernate performs an insert.
  * Example:
    ```java
    session.saveOrUpdate(employee);  // Either saves or updates the employee
    ```


<br><br>
**What is cascading in Hibernate and what are the different types of cascading?**

- Cascading in Hibernate refers to the propagation of certain operations (like persist, delete, update) from a parent entity to its associated entities. 
- This is useful when you want the same operation to be performed on related entities automatically.
- Without cascading, if you have a Department with 10 Employees, you would have to manually call `session.save()` 11 times. 
- With cascading, you save the Department, and Hibernate automatically saves all 10 Employees for you.
- There are different types of cascading in Hibernate, which are controlled using the `@Cascade` or `cascade` attribute in annotations or mapping files:
  - **`CascadeType.ALL:`**
    - This is a shorthand for all operations. Every action taken on the parent (save, delete, update, etc.) is mirrored on the children..
  - **`CascadeType.PERSIST`**:
    - When the parent is saved via `persist()`, all children are saved. This is commonly used for parent-child creation.
  - **`CascadeType.MERGE`**:
    - If you merge a detached parent, the children are also merged. Essential for updating entire object graphs.
  - **`CascadeType.REMOVE`**:
    - If you delete the parent, all its children are deleted.
    - Warning: Be careful with this on Many-to-Many relationships, as you might accidentally delete shared data.
  - **`CascadeType.REFRESH`**
    - If you reload the parent from the database, all children are reloaded to ensure the entire graph is synchronized.
  - **`CascadeType.DETACH`**
    - If the parent is removed from the session (becomes detached), all children also become detached.
  - Example:
    ```java
    @Entity
    public class Department {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        // Logic: Save/Update/Delete Department -> Do same for Employees
        @OneToMany(mappedBy = "department", 
                cascade = CascadeType.ALL, 
                orphanRemoval = true) 
        private List<Employee> employees = new ArrayList<>();
    }
    ```

<br><br>
> #### 💡Cascade Deep Dive
> Avoid using CascadeType.ALL blindly. In large enterprise systems, specifically for Many-to-One relationships (like many Employees to one Department), you almost never want to cascade a delete. If you delete an Employee, you don't want the entire Department to vanish! Use ALL only for strict "Composition" relationships where the child cannot exist without the parent.


<br><br>
**How do you handle concurrency in Hibernate?**

In high-concurrency environments, such as the Connected Vehicle Data Streaming Platform you are planning, handling simultaneous data updates is critical. Hibernate manages this through two primary locking mechanisms: Optimistic and Pessimistic locking.

1. **Optimistic Locking**
   - Optimistic locking assumes that multiple transactions can complete without affecting each other. It only checks for a conflict when saving the data.
   - Hibernate uses a version number or timestamp. When you read a row, Hibernate notes the version. When you update, it executes: `UPDATE table SET ..., version = version + 1 WHERE id = ? AND version = <old_version>`
   - If another transaction changed the row in the meantime, the `WHERE` clause fails because the version changed. Hibernate then throws a `StaleObjectStateException`.
   - **Pros**: High performance; no database-level locks are held, which prevents deadlocks.
   - **Best for**: Applications with more reads than writes or where conflicts are rare.
2. **Pessimistic Locking**
   - Pessimistic locking assumes a conflict will happen. it locks the record at the database level the moment you read it.
   - It uses database-specific syntax (like `SELECT ... FOR UPDATE` in MySQL/PostgreSQL).
   - **Lock Modes**:
     - **`PESSIMISTIC_READ`**: Others can read, but no one can update.
     - **`PESSIMISTIC_WRITE`**: No one else can read or update the row until your transaction ends.
   - **Pros**: Guarantees data consistency; no need to handle "retry" logic in Java.
   - **Cons**: Poor scalability; can lead to Deadlocks and performance bottlenecks if many threads wait for the same lock.
   - **Best for**: Financial systems or inventory management (e.g., booking the last seat on a flight).


<br><br>
**How do you configure Hibernate for a specific database dialect?**

In Hibernate, the database dialect defines how Hibernate generates SQL for a specific database. You can configure the dialect in the `hibernate.cfg.xml` file:

```xml
<property name="hibernate.dialect">org.hibernate.dialect.MySQLDialect</property>
```

Some commonly used dialects:
- **MySQL**: `org.hibernate.dialect.MySQLDialect`
- **PostgreSQL**: `org.hibernate.dialect.PostgreSQLDialect`
- **Oracle**: `org.hibernate.dialect.Oracle12cDialect`

By setting the correct dialect, Hibernate generates SQL optimized for the underlying database's syntax and behavior.


<br><br>
**What is the difference between flush() and clear() in Hibernate?**

- **`flush()`**:
  - The `flush()` method synchronizes the in-memory changes in the current session with the database. It forces Hibernate to execute any SQL statements that are pending in the session’s cache.
  - However, `flush()` does not commit the transaction; it just writes the changes to the database.
  - Example:
    ```java
    session.flush();
    ```
- **`clear()`**:
  - The `clear()` method clears the first-level cache of the session. It removes all objects from the session’s cache and effectively detaches them, meaning Hibernate will not track any changes to these objects after the call.
  - This is useful if you have a large number of entities and want to free up memory.
  - Example:
    ```java
    session.clear();
    ```


<br><br>
**What is the role of `Session.merge()` method in Hibernate?**

- The `merge()` method in Hibernate is used to either **update** an existing persistent entity or **insert** it if it does not already exist in the database, and it also **returns the merged entity**.
- It is particularly useful in situations where an entity is **detached** (i.e., it was previously persistent but the session was closed or the entity was evicted).
- **Key Points**:
  - **Detached Entity Handling**: If the entity passed to `merge()` is detached (i.e., its session is closed or it was evicted), `merge()` reattaches it to the current session.
  - **Return Value**: Unlike `save()` or `update()`, `merge()` returns the entity instance with the updated state, which might be different from the entity passed as the argument.
  - **No Exceptions**: It avoids `PersistentObjectException`, unlike `update()`, which throws an exception if the entity is detached.
- **Example**:
  ```java
  Employee detachedEmp = new Employee(1, "John");
  Session session = sessionFactory.openSession();
  session.beginTransaction();
  Employee mergedEmp = (Employee) session.merge(detachedEmp);  // mergedEmp is the updated entity
  session.getTransaction().commit();
  ```


<br><br>
**Explain the concept of Automatic Dirty Checking in Hibernate.**

- When an entity is loaded from the database (via `get()`, `load()`, or a `query`), Hibernate creates a "Snapshot" - an exact copy of the object's initial state - and stores it in the First-Level Cache (Persistence Context).
- As you modify the object's properties in your Java code, the object in the cache changes, but the Snapshot remains the same.
- During the Flush process (which happens automatically during `transaction.commit()`), Hibernate performs a "Dirty Check." It compares the current state of the object with its original Snapshot.
- If any differences are found, Hibernate marks the object as "Dirty" and generates the necessary `UPDATE` SQL statement to synchronize the database.
```java
Employee emp = session.get(Employee.class, 1);  // Assume emp is loaded from the DB
emp.setName("New Name");  // Change is detected by Hibernate
session.getTransaction().commit();  // Changes are flushed to DB during commit
```

<br><br>
> 💡 Note
> Hibernate L1 cache doubles memory usage by keeping both the active "Entity Instance" and a hidden "Original Snapshot" to enable automatic dirty-checking comparisons.


<br><br>
**When is Dirty Checking Triggered?**
Dirty checking is not a real-time background thread; it is a Pull mechanism triggered by the `flush()` operation. This typically happens when:

- `transaction.commit()` is called.
- You manually call `session.flush()`.
- A query is executed that might involve the modified entities (to ensure the query results are accurate).


<br><br>
**How can you handle the N+1 query problem in Hibernate?**

- The N+1 query problem occurs when Hibernate loads an entity and then performs additional queries to load associated entities, resulting in a total of N+1 queries (1 query for the parent and N additional queries for each associated child entity).
- This can significantly degrade performance, especially with large datasets.
- To handle this problem, you can use the following strategies:
  - **Use join fetch in HQL**:
    - You can use the fetch keyword in an HQL (Hibernate Query Language) query to fetch related entities in a single query.
    ```java
    // Logic: Fetch departments AND employees in 1 single SQL query
    String hql = "SELECT d FROM Department d JOIN FETCH d.employees";
    List<Department> depts = session.createQuery(hql, Department.class).list();
    ```
  - **Entity Graphs (`@EntityGraph`)**
    - Introduced in JPA 2.1, this allows you to define a "template" of what should be fetched. It is more flexible than JOIN FETCH because you can apply it to findById or repository methods.
    ```java
    @Entity
    @NamedEntityGraph(name = "Dept.employees", attributeNodes = @NamedAttributeNode("employees"))
    public class Department { ... }

    // In your service layer:
    EntityGraph graph = session.getEntityGraph("Dept.employees");
    Map<String, Object> hints = new HashMap<>();
    hints.put("jakarta.persistence.fetchgraph", graph);
    Department dept = session.find(Department.class, 1L, hints);
    ```
  - **Batch Fetching (`@BatchSize`)**:
    - If you can't use `JOIN FETCH`, you can use `@BatchSize`. Instead of firing 1 query for every 1 employee, Hibernate will wait and fetch them in "batches" (e.g., 10 at a time) using an IN clause.
    ```java
    CriteriaBuilder cb = session.getCriteriaBuilder();
    CriteriaQuery<Department> cq = cb.createQuery(Department.class);
    Root<Department> root = cq.from(Department.class);
    root.fetch("employees", JoinType.LEFT);
    List<Department> departments = session.createQuery(cq).getResultList();
    ```


<br><br>
**What is a Hibernate proxy and how does it work?**

- When you call `session.load()` or access a `@ManyToOne` association marked as `LAZY`, Hibernate doesn't run a SELECT query. Instead, it creates a dynamic subclass of your entity using a library like ByteBuddy or Javassist.
- The proxy object is mostly empty, but it does know the Primary Key (ID).
- When you call a getter (e.g., `emp.getName()`), the proxy intercepts the call.
  - It checks: "Is the data loaded?"
  - If **NO**, it triggers a SELECT query to the database (this is called Proxy Initialization).
  - If **YES**, it returns the data from the now-populated "real" object inside the proxy.
- **Key Exceptions**
  - **`LazyInitializationException`**: This happens if you try to access a getter on a proxy after the session has been closed. Since the session is gone, the proxy has no "bridge" to the database to load the data.
  - **`getClass()` Issue**: Since the proxy is a dynamic subclass, calling `emp.getClass()` will return something like `com.example.Employee$HibernateProxy$abc` instead of `com.example.Employee`. To get the real class, you must use `Hibernate.getClass(emp)`.
- **Analogy**
  - A Proxy is like a Gift Card. You have the card in your hand (it has a value/ID), but you don't actually have the item (the data) yet. You only get the actual item when you go to the store (the Database) and "redeem" the card.


<br><br>
**How does Hibernate handle object identity and equality?**

Hibernate handles object identity and equality by using the entity’s identifier (ID) to determine if two instances represent the same entity.
- Key Points:
  - **Identity**: Hibernate ensures that an entity's identifier (usually the primary key) is unique within a session. The same entity instance will be loaded only once per session.
  - **Equality**: By default, Hibernate uses the equals() and hashCode() methods of the entity to check for equality between objects. These methods should be overridden in your entity class based on the entity's identifier to avoid incorrect equality comparisons.
  
```java
@Entity
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return id != null && id.equals(employee.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
```


<br><br>
**What are User Types in Hibernate and how do you create them?**

JPA is the specification (the rules of the game), and Hibernate is the implementation (the actual player).

| Feature | Hibernate Session | JPA EntityManager |
| :--- | :--- | :--- |
| **API Origin** | Native Hibernate API (`org.hibernate.Session`) | Standard JPA API (`jakarta.persistence.EntityManager`) |
| **Standardization** | **Non-portable**: Your code is strictly locked to Hibernate. | **Portable**: High-level standard; can switch to EclipseLink or OpenJPA. |
| **Transaction** | `session.beginTransaction()` | `entityManager.getTransaction().begin()` |
| **Methods** | `save()`, `update()`, `saveOrUpdate()`, `load()` | `persist()`, `merge()`, `remove()`, `find()` |
| **Query** | Supports **HQL** (Hibernate Query Language) | Supports **JPQL** (Java Persistence Query Language) |
| **Exception** | Throws `HibernateException` (Unchecked) | Throws `PersistenceException` (Unchecked) |
| **Unwrap Support** | N/A (It is the core implementation) | Can be "unwrapped" to access the native Session |


<br><br>
> #### 💡The Relationship (The "Unwrap" Concept)
> If you are using `EntityManager`, but you suddenly need a feature that only Hibernate provides (like `session.saveOrUpdate()` or `StatelessSession`), you don't have to restart your project. You can "unwrap" the `EntityManager` to get the underlying `Session`.
> ```java
>  // Logic: Getting the Hibernate Session from JPA EntityManager
>  Session session = entityManager.unwrap(Session.class);
> ```


<br><br>
> #### 💡Critical Method Mappings
> Interviewers often ask how the methods map between the two APIs. Use this for quick recall:
> - `persist()` (JPA) $\approx$ `save()` / `persist()` (Hibernate)
> - `merge()` (JPA) $\approx$ `merge()` / `saveOrUpdate()` (Hibernate)
> - `find()` (JPA) $\approx$ `get()` (Hibernate)
> - `getReference()` (JPA) $\approx$ `load()` (Hibernate) - Returns a Proxy!


<br><br>
**How can you map an enum type in Hibernate?**

In Hibernate, enum types can be mapped to database columns in two main ways:

1. **`EnumType.ORDINAL (Default)`**
   - Stores the integer index of the enum (0, 1, 2...).
   - Extremely efficient in terms of database storage space.
   - If you change the order of your enums or add a new one in the middle, your existing database data will point to the wrong constants.
     - Example: If you have `ACTIVE(0)`, `INACTIVE(1)` and you insert `PENDING` at the start, `ACTIVE` becomes 1. Old records stored as `0` are now suddenly interpreted as `PENDING`.
2. **`EnumType.STRING`**
   - Stores the literal name (e.g., "ACTIVE", "INACTIVE").
   - Very readable in the database; safe to add new enums or reorder them.
   - Consumes more space; if you rename an enum constant in Java, you must run an SQL script to update the database values.


<br><br>
**What is the difference between @Embedded and @Embeddable in Hibernate?**

`@Embeddable` and `@Embedded` are two sides of the same coin used to implement the **Composition relationship**. This allows you to group related fields into a separate class (like Address or GPSCoordinates) without creating a separate database table.

1. **`@Embeddable`: Defining the Value Object**
   - You place this on the helper class. It tells Hibernate: *"This class doesn't have its own ID and doesn't deserve its own table. It only exists to be a part of something else."*
    ```java
    @Embeddable // Logic: I am a component, not an Entity.
    public class Address {
        private String street;
        private String city;
    }
    ```
2. **`@Embedded`: Using the Value Object**
   - You place this on the field inside your `@Entity`. It tells Hibernate: *"Take the fields inside the Address class and flatten them into my table."*
    ```java
    @Entity
    public class Employee {
        @Id
        private Long id;

        @Embedded // Logic: Put the Address fields into the Employee table.
        private Address address;
    }
    ```

<br><br>
> #### 💡 Note
> - **Null Handling**: If all fields of the embedded object (`street` and `city`) are null in the database, Hibernate will set the `address` field in your Java object to `null`.
> **`@AttributeOverrides`**: What if an `Employee` has two addresses (`homeAddress` and `officeAddress`)? You can't have two columns named `city`. You use `@AttributeOverride` to rename the columns for one of the fields.
> ```java
>@Entity
>public class Employee {
>
>    @Id
>    @GeneratedValue(strategy = GenerationType.IDENTITY)
>    private Long id;
>
>    // Uses default column names: street, city, zipCode
>    @Embedded
>    private Address homeAddress;
>
>    // Logic: Override default column names to avoid collision
>    @Embedded
>    @AttributeOverrides({
>        @AttributeOverride(name = "street", column = @Column(name = >"office_street")),
>        @AttributeOverride(name = "city", column = @Column(name = >"office_city")),
>        @AttributeOverride(name = "zipCode", column = @Column(name = >"office_zip"))
>    })
>    private Address officeAddress;
>}
> ```


<br><br>
**What is the difference between `@Entity` and `@MappedSuperclass`?**

1. **`@Entity`**:
   - Marks a class as a persistent entity. It means that the class will be mapped to a table in the database and instances of the class will be persisted.
   - The class must have an `@Id` field to represent the primary key.
   - Example:
     ```java
      @Entity
      public class Employee {
          @Id
          @GeneratedValue(strategy = GenerationType.IDENTITY)
          private Long id;
          
          private String name;
      }
     ```
2. **`@MappedSuperclass`**:
   - Marks a class as a superclass that contains persistent fields but is not itself an entity. It cannot be directly persisted or mapped to a table.
   - Other entity classes can inherit from this class and inherit its mapping, but `@MappedSuperclass` itself does not have a table.
   - Example:
     ```java
      @MappedSuperclass
      public class Person {
          private String name;
          private String address;
      }

      @Entity
      public class Employee extends Person {
          @Id
          @GeneratedValue(strategy = GenerationType.IDENTITY)
          private Long id;
      }
     ```
   - In this example, Employee inherits the name and address fields from Person, but Person is not directly mapped to a table.


<br><br>
**What is Transaction Management in Hibernate and how does it work?**

Transaction Management is the "safety net" for your data. It ensures that a series of database operations either all succeed together or all fail together (ACID properties).

Hibernate uses the `Transaction` interface to wrap the underlying database transaction. It follows a specific lifecycle:
- **Begin Transaction**: Start a transaction using `session.beginTransaction()`.
- **Perform Operations**: Perform entity manipulations (e.g., save, update, delete).
- **Commit Transaction**: Commit the transaction using `transaction.commit()`.
- Rollback: If there is an error, you can roll back the transaction using `transaction.rollback()`.

```java
Session session = sessionFactory.openSession();
Transaction tx = session.beginTransaction();
try {
    session.save(new Employee("John"));
    tx.commit();  // Commit the transaction
} catch (Exception e) {
    if (tx != null) {
        tx.rollback();  // Rollback in case of exception
    }
}
```


<br><br>
**What is the role of `@JoinTable` annotation in Hibernate?**

The `@JoinTable` annotation is the primary way to manage a Many-to-Many relationship. It creates a separate physical table in the database whose only job is to store the foreign keys of two other tables.

Without this "junction table," you cannot logically connect multiple records on both sides without violating database normalization rules.

#### Key Components of `@JoinTable`
- **`name`**: The physical name of the new table in the database (e.g., `student_course`).
- **`joinColumns`**: This defines the foreign key that points back to the Owning Entity (the class where you are writing the annotation).
- **`inverseJoinColumns`**: This defines the foreign key that points to the Target Entity (the other class in the relationship).

#### Example
```java
@OneToMany
@JoinTable(
    name = "dept_employee",
    joinColumns = @JoinColumn(name = "dept_id"),
    inverseJoinColumns = @JoinColumn(name = "emp_id")
)
private List<Employee> employees;
```

<br><br>
**What are the key differences between Hibernate and JPA?**

JPA is the Blueprint (the plan for the house), and Hibernate is the Builder (the one who actually pours the concrete).

| Feature | JPA (Java Persistence API) | Hibernate |
| :--- | :--- | :--- |
| **Scope** | A standard specification for ORM in Java. | A full-fledged ORM framework with its own API. |
| **API** | Provides standard interfaces (e.g., `EntityManager`, `Query`). | Provides native APIs (e.g., `Session`, `Transaction`). |
| **Configuration** | Uses `persistence.xml`. | Uses `hibernate.cfg.xml`. |
| **Vendor Status** | Specification (implemented by vendors like Hibernate, EclipseLink). | A specific implementation (provider) of JPA. |
| **Features** | Focuses on standard features (JPQL, Persistence Context). | Includes extras: L2 Caching, batching, and native HQL functions. |
| **Portability** | **High**: Portable way to switch between different providers. | **Low**: Vendor-specific; requires changes to migrate. |


<br><br>
**How do you handle `LazyInitializationException` in Hibernate?**

The `LazyInitializationException` is arguably the most famous error in Hibernate. It happens because you are trying to "redeem" a Proxy (that gift card we discussed) after the "store" (the Session) has already closed.

#### Top Strategies to Fix LazyInitializationException
1. **Join Fetch (The Performance-First Approach)**
   - Instead of letting Hibernate create a Proxy and fail later, you tell it to get the data now using a single SQL Join. This is the best way to avoid the N+1 problem and the exception simultaneously.
   ```java
    // Logic: Fetch the employee AND their projects in one go
    String hql = "SELECT e FROM Employee e JOIN FETCH e.projects WHERE e.id = :id";
    Employee emp = session.createQuery(hql, Employee.class)
                          .setParameter("id", id)
                          .getSingleResult();
   ```
2. **`Hibernate.initialize()` (The Manual Fix)**
   - If you are already inside a `@Transactional` method and realize you'll need the children after the method ends, you can force Hibernate to "fill" the Proxy.
   ```java
    Employee emp = session.get(Employee.class, id);
    Hibernate.initialize(emp.getProjects()); // Logic: Triggers the SQL SELECT for projects immediately
   ```
3. **Entity Graphs (The Modern Approach)**
   - Define exactly what "tree" of data you want at the repository level. This is cleaner than writing HQL.
   ```java
    @EntityGraph(attributePaths = {"projects", "department"})
    Optional<Employee> findWithProjectsById(Long id);
   ```
4. T**he "Open Session in View" (OSIV) Trap**
   
   Many Spring Boot apps have `spring.jpa.open-in-view=true` by default.
   - How it works: It keeps the database connection open until the JSON is fully rendered by the controller.
   - The Warning: In high-scale systems (like your Vehicle Platform), this is considered an Anti-Pattern. It keeps database connections busy for too long, leading to "Connection Pool Exhaustion."