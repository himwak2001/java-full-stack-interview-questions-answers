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

