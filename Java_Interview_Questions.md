# Core Java Interview Q&A

### Java Platform

**What is Java ?**

- Java is a high-level programming language that was developed by James Gosling in the year 1982.
- It is based on the principles of object-oriented programming and can be used to develop large-scale applications.


<br><br>
**What are the top Java features ?**

- **Simple:** Easy to learn with clean, readable syntax.
- **Platform Independent:** Write once, run anywhere - same output across systems.
- **Interpreted & Compiled:** Combines both for better performance and portability.
- **Robust:** Built-in garbage collection and exception handling ensure reliability.
- **Object-Oriented:** Supports classes, objects, and all four pillars of OOP.
- **Secure:** Bytecode and sandboxing prevent unauthorized access to code.
- **High Performance:** Faster than traditional interpreted languages due to JIT compiler.
- **Dynamic:** Supports dynamic loading of classes at runtime.
- **Distributed:** Enables remote method invocation and file access across networks.
- **Multithreaded:** Handles multiple tasks simultaneously using threads.
- **Architecture Neutral:** Not tied to any specific hardware or OS

<br><br>
**What is JVM ?**

- **JVM (Java Virtual Machine)** is a program that loads, verifies(ensure it is type-safe, structurally correct, and adheres to Java’s security and access rules before execution), and runs Java bytecode.
- It provides the **runtime environment**, including Class Loader, Execution Engine, and Memory Areas (Heap, Stack, Method Area).
- JVM uses both an **Interpreter** (for quick startup) and a **JIT Compiler** (for optimized performance).
- It handles the full execution cycle: **loading classes**, **verifying bytecode**, **executing code**, and **garbage collection**.
- Though JVM is **platform dependent**, it enables Java to be **platform independent** by abstracting system-specific details


<br><br>
**What are Memory storages available with JVM?**

![image.png](image.png)

1. **Class Loader:**
    1. It loads `.class` file into memory.
    2. It verifies byte code instructions.
    3. It allocates memory for program execution.
2. **Runtime data areas**:
    1. **Method Area**: Method area stores class code and method code. (called Metaspace in Java 8+).
    2. **Heap**: Objects are created on heap; shared across threads.
    3. **Java Stack**: It is the place where the Java methods are executed. A Java stack contains frames. On each frame, a separate method is executed. Each thread has its own Stack.
    4. **Program counter register**: The program counter registers store memory address of the instruction to be executed by the micro processor (CPU).
    5. **Native method stacks**: The native method stacks are places where native methods (for example, C language programs) are executed. Native method is a function, which is written in another language other than Java.(We use native methods when we need **speed, system access, or existing native libraries** that Java alone can’t provide.)
3. **Execution Engine**: 
    1. Execution engine contains interpreter and JIT compiler, which converts byte code into machine code. 
    2. JVM uses optimization technique to decide which part to be interpreted and which part to be used with JIT compiler. 
    3. The HotSpot represent the block of code executed by JIT compiler.

<br><br>
**What is Platform Independence ?**

- Platform Independence is also called build once, run anywhere.
- Once we compile a java program and build a jar, we can run the jar (compiled java program) in any Operating System where a JVM is installed.
- On compiling a java file the output is a class file, which contains an internal java representation called bytecode.
- JVM converts bytecode to executable instructions, and the executable instructions are different in different operating systems, so there are different JVMs for different operating systems.
- A JVM for windows is different from a JVM for mac; however, both the JVMs understand the bytecode and convert it to the executable code for the respective operating system.

<br><br>
**Why is Java not a pure object oriented language?**

Java supports primitive data types - byte, boolean, char, short, int, float, long, and double and hence it is not a pure **object oriented language**.

<br><br>
**Difference between Heap and Stack Memory in java. And how java utilizes this.**


<br><br>
**Difference between JVM, JRE, and JDK.**

- **JVM**:
    - JVM also known as Java Virtual Machine is a part of JRE.
    - JVM is a type of interpreter responsible for converting bytecode into machine-readable code.
    - JVM itself is platform dependent but it interprets the bytecode which is the platform-independent reason why Java is platform-independent.
- **JRE**:
    - JRE stands for Java Runtime Environment, it is an installation package that provides an environment to run the Java program or application on any machine.
- **JDK:**
    - JDK stands for Java Development Kit which provides the environment to develop and execute Java programs.
    - JDK is a package that includes two things, Development Tools to provide an environment to develop your Java programs and, JRE to execute Java programs or applications.

<br><br>
**Explain public static void main(String args[]) in Java.**

![image.png](image%201.png)

1. **static**: JVM call this method directly with class name to avoid unnecessary allocation of the memory(in case of non-static).
2. **String args[]:** It stores Java command-line arguments and is an array of type `java.lang.String` class.

<br><br>
**Regarding Packages in Java**

- **Packages** are used to group related classes and interfaces, helping with **access control**, **namespace management**, and **code organization**.
- They help **avoid naming conflicts**, make it easier to **locate and use related classes**, and allow **hiding internal classes** not meant for external use.
- Java supports two types of packages:
    - **Built-in packages** (e.g., `java.util`, `java.io`)
    - **User-defined packages** created by developers to organize custom code

<br><br>
**How is Java different from C++?**

1. Java is platform independent. C++ is not platform independent.
2. Java & C++ are both NOT pure Object Oriented Languages. However, Java is more purer Object
Oriented Language (except for primitive variables). In C++, one can write structural programs
without using objects.
3. C++ has pointers (access to internal memory). Java has no concept called pointers.
4. In C++, programmer has to handle memory management. A programmer has to write code to
remove an object from memory. In Java, JVM takes care of removing objects from memory
using a process called Garbage Collection.
5. C++ supports Multiple Inheritance. Java does not support Multiple Inheritance.

<br><br>
**What is the role for a `ClassLoader` in Java ?**

- A Java program is made up of a number of custom classes (written by programmers like us) and core classes (which come pre-packaged with Java).
- When a program is executed, JVM needs to load the content of all the needed classes, and it uses a ClassLoader to find the classes.
- There are three class loader:
    - System Class Loader - Loads all classes from CLASSPATH
    - Extension Class Loader - Loads all classes from extension directory
    - Bootstrap Class Loader - Loads all the Java core files
- When JVM needs to find a class, it starts with the System Class Loader; if it is not found, it checks with the Extension Class Loader; if it is still not found, it goes to the Bootstrap Class Loader.
- If a class is still not found, a `ClassNotFoundException` is thrown.

<br><br>
**What do you understand by an instance variable and a local variable?**

1. **Instance Variable**:
    1. This variable are declared outside the method and inside the class.
    2. These variables describe the properties of an object and accessible by all the methods directly in the class except static methods for which we need to create an object.
    
    ```java
    public class Athlete {
        public String athleteName;
        public double athleteSpeed;
        public int athleteAge;
    }
    ```
    
2. **Local Variable**:
    1. Are those variables present within a block, function or constructor and can be accessible only inside them.
    2. They are restricted to the block scope only.
    
    ```java
    public void athlete(){
            String athleteName;
            double athleteSpeed;
            int athleteAge;
        }
    ```
    
<br><br>
**What are the default values assigned to variables and instances in java?**

- There are no default value assigned to the *local variables* in Java. We need to initialize it before using it, otherwise it will throw compilation error of (Variable might not be initialized)
    
    ```java
    public static void main(String[] args) {
        int sum;
        System.out.println(sum); // Variable 'sum' might not have been initialized
    }
    ```
    
- But in case of the *instance variable*, if we create the object, then the default value will be initialized by the default constructor depending on the data type i.e., for reference it will be null, for numeric it will be 0, for boolean it will be false.
    
    ```java
    public class Athlete {
        public String athleteName;
        public double athleteSpeed;
        public int athleteAge;
    
        public static void main(String[] args) {
            Athlete athlete = new Athlete();
            System.out.println(athlete.athleteName); // null
            System.out.println(athlete.athleteSpeed); // 0.0
            System.out.println(athlete.athleteAge); // 0
        }
    }
    ```
    
<br><br>
**Tell us something about JIT compiler.**

- The JIT (Just-In-Time) Compiler is a part of the JVM designed to *significantly improve runtime speed* of Java applications after they've started.
- It monitors the running code and identifies *frequently executed methods or blocks* (called 'Hotspots'). It ignores code that runs only once or rarely.
- When a Hotspot is found, the JIT **immediately compiles** that bytecode into highly optimized **native machine code** (the CPU's language). This optimized code is then stored in memory.
- The next time that specific code is needed, the JVM skips the slower, line-by-line **Interpreter** and directly executes the **fast, pre-compiled native code**. This hybrid approach gives Java both fast startup and fast sustained execution.

![image.png](image%202.png)

<br><br>
### Strings
---

**What is Java String Pool?**

![image.png](image%203.png)

- A Java String Pool is a place in heap memory where all the strings defined in the program are stored.
- The variable holding the string reference is stored in the stack memory.
- Whenever we create a new string object, JVM checks for the presence of the object in the String pool, If String is available in the pool, the same object reference is shared with the variable, else a new object is created.


<br><br>
**Are all String’s immutable ?**

- Value of a String Object once created cannot be modified. Any modification on a String object creates a new String object.
    
    ```java
    String str3 = "value1";
    str3.concat("value2");
    System.out.println(str3); //value1
    ```
    
- Note that the value of str3 is not modified in the above example. The result should be assigned to a new reference variable (or same variable can be reused). All wrapper class instances are immutable too!
    
    ```java
    String concat = str3.concat("value2");
    System.out.println(concat); //value1value2
    ```
    

<br><br>
**Where are string values stored in memory?**

The location where the string values are stored in memory depends on how we create them.

- **Approach 1**
    - In the example below we are directly referencing a String literal.
        
        ```java
        String str1 = "value";
        ```
        
    - This value will be stored in a "String constant pool" - which is inside the Heap memory. If compiler finds a String literal, it checks if it exists in the pool. If it exists, it is reused.
        
        ```java
        String str5 = "value";
        ```
        
    - In above example, when str5 is created - the existing value from String Constant Pool is reused.
- **Approach 2**
    - However, if new operator is used to create string object, the new object is created on the heap. There will not be any reuse of values.
        
        ```java
        //String Object - created on the heap
        String str2 = new String("value");
        ```
        

<br><br>
**Why should you be careful about String Concatenation(+) operator in Loops?**

- Consider the code below
    
    ```java
    String s3 = "Value1";
    String s2 = "Value2";
    for (int i = 0; i < 100000; ++i) {
    		s3 = s3 + s2;
    }
    ```
    
- How many objects are created in memory? More than 100000 Strings are created. This will have a huge performance impact.

<br><br>
**How do you solve above problem?**

- The easiest way to solve above problem is using `StringBuffer`. On my machine `StringBuffer` version took 0.5 seconds. String version took 25 Seconds. That’s a 50 fold increase in performance.
    
    ```java
    StringBuffer s3 = new StringBuffer("Value1");
    String s2 = "Value2";
    for (int i = 0; i < 100000; ++i) {
    		s3.append(s2);
    }
    ```
    

<br><br>
**What are the differences between String and `StringBuffer`?**

- Objects of type String are immutable; `StringBuffer` is used to represent values that can be modified.
- In situations where values are modified a number of times, `StringBuffer` yields significant performance benefits.
- Both String and `StringBuffer` are thread-safe.
- `StringBuffer` is implemented by using the synchronized keyword on all methods.


<br><br>
**What are the difference between StringBuffer and StringBuilder ?**

| StringBuffer | StringBuilder |
| --- | --- |
| Provides functionality to work with **mutable strings**. | Also used for **building mutable strings**, generally in single-threaded applications. |
| **Synchronized**, so it is **thread-safe**; two threads cannot call its methods simultaneously. | **Not synchronized**, so it is **not thread-safe**; two threads can access methods concurrently. |
| **Slower** due to synchronization overhead. | **Faster** because it is non-synchronized. |
| Suitable for **multi-threaded programs** where string modifications happen concurrently. | Suitable for **single-threaded programs** where performance is important. |


<br><br>
**Can you give examples of different utility methods in String class?**

- String class defines a number of methods to get information about the string content.
    
    ```java
    String str = "abcdefghijk";
    // Following methods help to get information from a String.
    //char charAt(int paramInt)
    System.out.println(str.charAt(2)); //prints a char - c
    System.out.println("ABCDEFGH".length());//8
    System.out.println("abcdefghij".toString()); //abcdefghij
    System.out.println("ABC".equalsIgnoreCase("abc"));//true
    
    //Get All characters from index paramInt
    //String substring(int paramInt)
    System.out.println("abcdefghij".substring(3)); //cdefghij
    
    //All characters from index 3 to 6
    System.out.println("abcdefghij".substring(3,7)); //defg
    ```
    

<br><br>
**Which among String Builder or String Buffer should be preferred when there are a lot of updates required to be done in the data?**

- String is immutable, making it inefficient for scenarios requiring frequent updates.
- Instead, we can use StringBuilder or StringBuffer. If thread safety is required (synchronized operations), StringBuffer should be used.
- However, if performance is a priority in a single-threaded context, StringBuilder is the better choice since it is faster and does not incur synchronization overhead.

<br><br>
**How is the creation of a String using new() different from that of a literal?**

- A string literal stores its value in the **string pool**, so the same content can be reused.
- A string created with `new()` always creates a **new object in the heap**, even if the same content exists.
- Literals are more **memory-efficient**, while `new()` always allocates fresh memory.
- Using `new()` is **slightly slower** because it creates a new object each time.

![image.png](image%204.png)


<br><br>
**Can you tell the difference between equals() method and equality operator (==) in Java?**

- **`==` (Operator) ↔ Reference Comparison:** The `==` operator checks if two object variables point to the **exact same memory location** (i.e., they are the same object).
- **`.equals()` (Method) ↔ Content Comparison:** The `.equals()` method checks for **logical equivalence** (if the data inside the objects is the same).
- **Default vs. Overridden:** The default implementation of `equals()` in the `Object` class is the same as `==`. However, it must be **overridden** in classes like `String` or our custom classes to enable meaningful content comparison.

```java
String s1 = new String("Hi");
String s2 = new String("Hi");

// == checks references (different memory slots)
System.out.println(s1 == s2);      // Output: false 

// .equals() checks content (same characters)
System.out.println(s1.equals(s2)); // Output: true
```

<br><br>
**What do you mean by data encapsulation?**

- Data Encapsulation is an Object-Oriented concept  of wrapping the data attributes and their behaviors in a single unit.
- It helps developer to follow modularity approach that is each object is independent of other objects by having its own methods, attributes and functionality.
- It also contain private properties of an object, hence also serves the purpose of data hiding. Thus, Data hiding is achieved by making the class fields `private` and Encapsulation is achieved by providing public methods (called Getters and Setters) to access and modify the private data.

```java
public class Student {
    
    // 1. Data Hiding: Data is PRIVATE
    private String name;
    private int score;

    // Constructor (to set initial values)
    public Student(String name, int score) {
        this.name = name;
        // Uses the controlled setter method
        setScore(score); 
    }

    // --- ACCESSORS (Getters) ---
    // Read-only access to private fields
    public String getName() {
        return name;
    }
    
    public int getScore() {
        return score;
    }
}
```




**Briefly explain the concept of constructor overloading**

- Constructor overloading is the process of creating multiple constructor in the class consisting of the same name but different in parameters.
- Compiler distinguish this constructor based on number of parameters and their corresponding types.

```java
public class Hospital {
    int variable1, variable2;
    double variable3;

    public Hospital(int doctors, int nurses) {
        variable1 = doctors;
        variable2 = nurses;
    }

    public Hospital(int doctors) {
        variable1 = doctors;
    }

    public Hospital(double salaries) {
        variable3 = salaries;
    }
}
```

Three constructors are defined here but they differ on the basis of parameter type and their numbers.

**Can the main method be Overloaded?**
Yes, the main method **can be overloaded**. However, the **JVM will only call** the method with the **exact signature** `public static void main(String[] args)` as the program's starting point.

```java
class Main {
    public static void main(String args[]) {
        System.out.println(" Main Method");
    }
    public static void main(int[] args){
        System.out.println("Overloaded Integer array Main Method");
    }
    public static void main(char[] args){
        System.out.println("Overloaded Character array Main Method");
    }
    public static void main(double[] args){
        System.out.println("Overloaded Double array Main Method");
    }
    public static void main(float args){
        System.out.println("Overloaded float Main Method");
    }
}
```

**A single try block and multiple catch blocks can co-exist in a Java Program. Explain.**

Yes, multiple catch blocks can exist but specific approaches should come prior to the general approach because only the first catch block satisfying the catch condition is executed.

```java
public class MultipleCatch {
  public static void main(String args[]) {
    try {
      int n = 1000, x = 0;
      int arr[] = new int[n];
      for (int i = 0; i <= n; i++) {
        arr[i] = i / x;
      }
    } catch (ArrayIndexOutOfBoundsException exception) {
      System.out.println("1st block = ArrayIndexOutOfBoundsException");
    } catch (ArithmeticException exception) {
      System.out.println("2nd block = ArithmeticException");
    } catch (Exception exception) {
      System.out.println("3rd block = Exception");
    }
  }
}
```

Here, the second catch block will be executed because of division by 0 (i / x). In case x was greater than 0 then the first catch block will execute because for loop runs till i = n and array index are till n-1.

**Explain the use of final keyword in variable, method and class.**

In Java, the final keyword is used as defining something as constant /final and represents the non-access modifier.

- **final variable:**
    - When a variable is declared as final in Java, the value can’t be modified once it has been assigned.
    - If any value has not been assigned to that variable, then it can be assigned only by the constructor of the class.
- **final method:**
    - A method declared as final cannot be overridden by its children's classes.
    - A constructor cannot be marked as final because whenever a class is inherited, the constructors are not inherited. Hence, marking it final doesn't make sense. Java throws compilation error saying - `modifier final not allowed here`
- **final class:**
    - No classes can be inherited from the class declared as final. But that final class can extend other classes for its usage.

**Do final, finally and finalize keywords have the same function?**

1. **Final**:
    1. The **`final` keyword** is used to make a **class non-inheritable**, a **method non-overridable**, and a **variable value fixed (constant)**
    
    ```java
    final int a=100;
    a = 0;  // error
    ```
    
2. **Finally**:
    1. It is the block present in a program where all the codes written inside it get executed irrespective of handling of exceptions.
    
    ```java
    try {
      int variable = 5;
    } catch (Exception exception) {
      System.out.println("Exception occurred");
    } finally {
      System.out.println("Execution of finally block");
    }
    ```
    
3. **Finalize:**
    1. Prior to the garbage collection of an object, the finalize method is called so that the clean-up activity is implemented.
    
    ```java
    public static void main(String[] args) {
      String example = new String("InterviewBit");
      example = null;
      System.gc(); // Garbage collector called
    }
    public void finalize() {
      // Finalize called
    }
    ```
    

**Is it possible that the ‘finally’ block will not be executed? If yes then list the case.**

Yes. It is possible that the ‘finally’ block will not be executed. The cases are

1. Suppose we use **`System.exit()`** in the above statement.
2. If there are fatal errors like Stack overflow, Memory access error, etc.

**When can you use super keyword?**

- The super keyword is used to access the hidden fields and overridden method or attributes of the parent class.
- Following are the cases when this keyword can be used:
    - Accessing the data members of the parent class when child class is also having the same named data members.
    - To call the default or parameterized constructor of the parent class inside the child class constructor.
    - Accessing the parent class method when child classes have overridden method.

```java
public class Parent {
    protected int num = 20;

    public Parent() {
        System.out.println("Parent class default constructor.");
    }

    public Parent(String str) {
        System.out.println("Parent class parameterised constructor.");
    }

    public void foo() {
        System.out.println("Parent class foo() method");
    }
}

public class Child extends Parent {
    private int num = 30;

    public Child() {
        //  super(); // super constructor call should always be in the first line
        super("hello"); // Either call default super() to call default parent constructor OR call parameterised super to call parameterised parent constructor.
        System.out.println("Child class default constructor.");
    }

    public void printNum() {
        System.out.println("Child class num: " + this.num);
        System.out.println("Parent class num: " + super.num);
    }

    @Override
    public void foo() {
        System.out.println("Child class foo() method");
        super.foo();
    }
}

public class DemoClass {
    public static void main(String[] args) {
        Child demoChild = new Child();
        demoChild.foo();
        /*
          This would print -
          Parent class parameterised constructor.
          Child class default Constructor
          Child class foo() method
          Parent class foo() method
         */
    }
}
```

**Can the static methods be overloaded?**

Yes! There can be two or more static methods in a class with the same name but differing input parameters.

```java
public class StaticOverloadDemo {

    // 1. One int
    public static int multiply(int a) {
        return a * 10;
    }

    // 2. Two ints (Overloaded)
    public static int multiply(int a, int b) {
        return a * b;
    }

    // 3. Two doubles (Overloaded)
    public static double multiply(double a, double b) {
        return a * b;
    }

    public static void main(String[] args) {
        System.out.println(multiply(5));       // Calls #1
        System.out.println(multiply(5, 4));    // Calls #2
        System.out.println(multiply(5.0, 4.5)); // Calls #3
    }
}
```

**Why is the main method static in Java?  / What will happen if we don't declare the main as static?**

- The `main` method is `static` so the JVM can call it directly using the class name without creating an object.
- If it weren’t static, every object would have its own `main` method, which is not acceptable for program execution.
- Since execution starts from `main`, having it static ensures a single, consistent entry point.

**Can the static methods be overridden?**

- **Static methods cannot be overridden** because they are resolved at **compile-time** using **static binding**, unlike instance methods which use **runtime polymorphism**.
- Since static methods belong to the **class**, not the object, they are accessed via the class name and don’t participate in inheritance-based dispatch.
- Defining a static method with the same signature in a subclass results in **method hiding**, not overriding - the method called depends on the **reference type**, not the actual object.

**Difference between static methods, static variables, and static classes in java.**

- **Static Variable:** Shared across all instances; memory allocated once when the class is loaded; accessed using the class name (e.g., `ClassName.count`).
- **Static Method:** Belongs to the class; called using the class name (e.g., `Math.max()`); cannot access non-static members directly; commonly used for utility or entry-point methods.
- **Static Class (Nested):** Only nested classes can be static; accessed without creating an instance of the outer class; cannot access non-static members of the outer class; useful for grouping related utility code.
    
    ```java
    public class OuterClass {
        static class StaticNestedClass {
            void display() {
                System.out.println("Hello from static nested class!");
            }
        }
    
        public static void main(String[] args) {
            // Creating an object of the static nested class
            OuterClass.StaticNestedClass nested = new OuterClass.StaticNestedClass();
            nested.display();
        }
    }
    ```
    

**What is the main objective of garbage collection?**

- The main objective of garbage collection is to **automatically free memory** by deleting **unreachable or unused objects** during program execution.
- This helps in **efficient memory management**, but it does **not guarantee** that sufficient memory will always be available for the program to run.

**What is a ClassLoader?**

- **ClassLoader** is a part of the **Java Runtime Environment (JRE)** responsible for **dynamically loading classes and interfaces** into the JVM during program execution.
- It loads classes **on demand**, meaning only when they’re needed—like loading the `Scanner` class when reading input from the console.

<br><br>
### Wrapper Classes
---
**What are Wrapper Classes in Java, and why are they needed?**

Wrapper classes in Java are object representations of primitive data types. Java provides 8 built-in wrapper classes: `Byte`, `Short`, `Integer`, `Long`, `Float`, `Double`, `Character`, and `Boolean`.

We need wrapper classes because:

- They allow primitives to be used in object-based APIs like collections.
- They support **autoboxing** and **unboxing**, simplifying conversions between primitives and objects.
- They provide utility methods like `parseInt()`, `valueOf()`, etc.
- All wrapper classes are **immutable** and **final**, ensuring thread safety.
- Custom wrapper classes can also be created to encapsulate multiple values, similar to C-style structs.

Example:

```java
int x = 5;                  // primitive
Integer obj = Integer.valueOf(x);  // object representation
```

Now `obj` is an object that holds the value `5`, and you can call methods on it like:

```java
obj.toString();      // returns "5"
obj.compareTo(10);   // compares with another Integer
```

<br><br>
**What are the different ways of creating Wrapper Class Instance  ?**

Two ways of creating Wrapper Class Instances are described below.

- **Using a wrapper class constructor**
    
    ```java
    Integer number = new Integer(55);//int
    Integer number2 = new Integer("55");//String
    
    Float number3 = new Float(55.0);//double argument
    Float number4 = new Float(55.0f);//float argument
    Float number5 = new Float("55.0f");//String
    
    Character c1 = new Character('C');//Only char constructor
    //Character c2 = new Character(124);//COMPILER ERROR
    
    Boolean b = new Boolean(true);
    
    //"true" "True" "tRUe" - all String Values give True
    //Anything else gives false
    Boolean b1 = new Boolean("true");//value stored - true
    Boolean b2 = new Boolean("True");//value stored - true
    Boolean b3 = new Boolean("False");//value stored - false
    Boolean b4 = new Boolean("SomeString");//value stored - false
    ```
    
- **Using `valueOf` Static Methods**
    
    ```java
    Integer hundred = Integer.valueOf("100");//100 is stored in variable
    Integer seven = Integer.valueOf("111", 2);//binary 111 is converted to 7
    ```
    

<br><br>
**What are differences in the two ways of creating Wrapper Classes?**

- The difference is that using the Constructor you will always create a new object, while using valueOf() static method, it may return you a cached value within a range.
- For example: The cached values for long are between [-128 to 127].
- We should prefer the static valueOf method, because it may save you some memory.
- To understand it further, here is an implementation of valueOf method in the Long class.
    
    ```java
    /**
    * Returns an {@code Integer} instance representing the specified
    * {@code int} value. If a new {@code Integer} instance is not
    * required, this method should generally be used in preference to
    * the constructor {@link #Integer(int)}, as this method is likely
    * to yield significantly better space and time performance by
    * caching frequently requested values.
    *
    * This method will always cache values in the range -128 to 127,
    * inclusive, and may cache other values outside of this range.
    *
    * @param i an {@code int} value.
    * @return an {@code Integer} instance representing {@code i}.
    * @since 1.5
    */
    public static Integer valueOf(int i) {
    		if (i >= IntegerCache.low && i <= IntegerCache.high )
    				return IntegerCache.cache [i + (-IntegerCache.low )];
    		return new Integer(i);
    }
    ```
    

<br><br>
**What is Auto Boxing?**

- Autoboxing is the automatic conversion that the Java compiler makes between the primitive types and their corresponding object wrapper classes.
- For example, converting an int to an Integer, a double to a Double, and so on.
- If the conversion goes the other way, this is called unboxing.
- Example 1:
    
    ```java
    Integer nineC = 9;
    ```
    
- Example 2:
    
    ```java
    Integer ten = new Integer(10);
    ten++;//allowed. Java does had work behind the screen for us
    ```
    
<br><br>
**What are the advantages of Auto Boxing?**

- Auto Boxing helps in saving memory by reusing already created Wrapper objects.
- Auto Boxing uses the static valueOf methods.
- However, wrapper classes created using new are not reused.
- Two wrapper objects created using new are not same object.
    
    ```java
    Integer nineA = new Integer(9);
    Integer nineB = new Integer(9);
    System.out.println(nineA == nineB);//false
    System.out.println(nineA.equals(nineB));//true
    ```
    
- Two wrapper objects created using boxing are same object.
    
    ```java
    Integer nineC = 9;
    Integer nineD = 9;
    System.out.println(nineC == nineD);//true
    System.out.println(nineC.equals(nineD));//true
    ```
    

<br><br>
**What is casting ?**

Casting is used when we want to convert one data type to another.
There are two types of Casting

- Implicit Casting
- Explicit Casting


<br><br>
**What is Implicit Casting?**

- Implicit Casting is done by the compiler.
- Good examples of implicit casting are all the automatic widening conversions, i.e., storing smaller values in larger variable types.
- Example:
    
    ```java
    int value = 100;
    long number = value; //Implicit Casting
    float f = 100; //Implicit Casting
    ```
    

<br><br>
**What is Explicit Casting?**

- Explicit Casting is done through code.
- Good examples of explicit casting are the narrowing conversions i.e., storing larger values into smaller variable types.
    
    ```java
    long number1 = 25678;
    int number2 = (int)number1;//Explicit Casting
    //int x = 35.35;//COMPILER ERROR
    int x = (int)35.35;//Explicit Casting
    ```
    
- Explicit casting would cause truncation of value if the value stored is greater than the size of the variable.
    
    ```java
    int bigValue = 280;
    byte small = (byte) bigValue;
    System.out.println(small);//output 24. Only 8 bits remain.
    ```
    

<br><br>
### Object Oriented Programming Basics
---
```java
package com.rithus;
public class CricketScorer {
//Instance Variables - constitute the state of an object
private int score;
		//Behavior - all the methods that are part of the class
		//An object of this type has behavior based on the
		//methods four, six and getScore
		public void four(){
				score = score + 4;
		}
		
		public void six(){
				score = score + 6;
		}
		
		public int getScore() {
				return score;
		}
		
		
		public static void main(String[] args) {
				CricketScorer scorer = new CricketScorer();
				scorer.six();
				//State of scorer is (score => 6)
				scorer.four();
				//State of scorer is (score => 10)
				System.out.println(scorer.getScore());
		}
}
```

<br><br>
**What is a Class ?**

- A class is a Template.
- In the above example, Class CricketScorer is the template for creating multiple objects.
- A class defines state and behavior that an object can exhibit.
- A class provides the blueprint from which individual objects are created.
- A class enables code reusability by allowing multiple objects to share the same structure and behavior.


<br><br>
**What is an Object ?**

- An instance of a class.
- In the above example, we create an object using new CricketScorer(), and the reference of the created object is stored in the scorer variable.
- We can create multiple objects of the same class.
- An object encapsulates both data (state) and methods (behavior) defined in the class.
- Each object maintains its own independent copy of instance variables, even though all objects share the same class definition.


<br><br>
**What is state of an Object?**

- Values assigned to instance variables of an object.
- Consider the following code snippets from the above example.
- The value in score variable is initially 0; it changes to 6 and then 10.
- State of an object might change with time.
    
    ```java
    scorer.six();
    //State of scorer is (score => 6)
    scorer.four();
    //State of scorer is (score => 10)
    ```
    
<br><br>
**What is behavior of an Object?**

- Methods supported by an object.
- In the above example, the behavior supported is six(), four() and getScore().
- Behavior represents the actions an object can perform based on its methods.
- Different objects of the same class can invoke the same behavior, but the effect may vary based on their individual state.


<br><br>
**What is the super class of every class in Java?**

- Every class in Java is a sub class of the class Object.
- When we create a class we inherit all the methods and properties of Object class.
- Object is the root class in Java, and all classes directly or indirectly extend it.
- Methods like `toString()`, `equals()`, `hashCode()` and `getClass()` come from the Object class and can be overridden for custom behavior.
- Let’s look at a simple example:
    
    ```java
    String str = "Testing";
    System.out.println(str.toString());
    System.out.println(str.hashCode());
    System.out.println(str.clone());
    if(str instanceof Object){
    		System.out.println("I extend Object");//Will be printed
    }
    ```
    
- In the above example, toString, hashCode and clone methods for String class are inherited from Object class and overridden.


<br><br>
**Explain about `toString` method ?**

- toString method is used to print the content of an Object.
- If the `toString` method is not overridden in a class, the default `toString` method from Object class is invoked.
- This would print fully qualified class name followed by `hashcode` i.e., `com.rithus.Animal@f7e6a96` as shown in the example below.
- However, if `toString` method is overridden, the content returned by the `toString` method is printed.
- Consider the class given below:
    
    ```java
    class Animal {
    		public Animal(String name, String type) {
    				this.name = name;
    				this.type = type;
    		}
    		String name;
    		String type;
    }
    ```
    
- Run this piece of code
    
    ```java
    Animal animal = new Animal("Tommy", "Dog");
    System.out.println(animal);//com.rithus.Animal@f7e6a96
    ```
    
- Output does NOT show the content of animal (what name? and what type?). To show the content of the animal object, we can override the default implementation of `toString` method provided by Object class.
    
    ```java
    @Override
    public String toString() {
    		return "Animal [name=" + name + ", type=" + type
    		+ "]";
    }
    ```
    
- Run this piece of code
    
    ```java
    Animal animal = new Animal("Tommy","Dog");
    System.out.println(animal);//Animal [name=Tommy, type=Dog]
    ```
    
<br><br>
**What is the use of equals method in Java?**

- Equals method is used when we compare two objects.
- Default implementation of equals method is defined in Object class.
- The implementation is similar to == operator.
- Two object references are equal only if they are pointing to the same object.
- We need to override equals method, if we would want to compare the contents of an object.
- Consider the example Client class provided below.
    
    ```java
    class Client {
    		private int id;
    		
    		public Client(int id) {
    				this.id = id;
    		}
    }
    ```
    
- == comparison operator checks if the object references are pointing to the same object. It does NOT look at the content of the object.
    
    ```java
    Client client1 = new Client(25);
    Client client2 = new Client(25);
    Client client3 = client1;
    
    //client1 and client2 are pointing to different client objects.
    System.out.println(client1 == client2);//false
    
    //client3 and client1 refer to the same client objects.
    System.out.println(client1 == client3);//true
    
    //similar output to ==
    System.out.println(client1.equals(client2));//false
    System.out.println(client1.equals(client3));//true
    ```
    
- We can override the equals method in the Client class to check the content of the objects. Consider the example below: The implementation of equals method checks if the id's of both objects are equal. If so, it returns true. Note that this is a basic implementation of equals and more needs to be done to make it foolproof.
    
    ```java
    class Client {
    		private int id;
    		
    		public Client(int id) {
    				this.id = id;
    		}
    		
    		@Override
    		public boolean equals(Object obj) {
    				Client other = (Client) obj;
    				if (id != other.id)
    						return false;
    				return true;
    		}
    }
    ```
    
- Consider running the code below:
    
    ```java
    Client client1 = new Client(25);
    Client client2 = new Client(25);
    Client client3 = client1;
    
    //both id's are 25
    System.out.println(client1.equals(client2));//true
    
    //both id's are 25
    System.out.println(client1.equals(client3));//true
    ```
    
    Above code compares the values (id's) of the objects.
    

<br><br>
**What are the important things to consider when implementing equals method?**

- Any equals implementation should satisfy these properties:
- Reflexive. For any reference value x, `x.equals(x)` returns true.
- Symmetric. For any reference values x and y, `x.equals(y)` should return true if and only if `y.equals(x)` returns true.
- Transitive. For any reference values x, y, and z, if `x.equals(y)` returns true and `y.equals(z)` returns true, then `x.equals(z)` must return true.
- Consistent. For any reference values x and y, multiple invocations of `x.equals(y)` consistently return true or consistently return false, if no information used in equals is modified.
- For any non-null reference value x, `x.equals(null)` should return false.
- Our earlier implementation of equals method will not satisfy condition 5. It would throw an exception if an object of different class (other than Client) is used for comparison.
- Let's now provide an implementation of equals which satisfy these properties:
    
    ```java
    //Client class
    @Override
    public boolean equals(Object obj) {
    		if (this == obj)
    				return true;
    		if (obj == null)
    				return false;
    		if (getClass() != obj.getClass())
    				return false;
    		Client other = (Client) obj;
    		if (id != other.id)
    				return false;
    		return true;
    }
    ```
    

<br><br>
**What is the hashCode method used for in Java?**

- HashCode's are used in hashing to decide which group (or bucket) an object should be placed into.
- A group of object's might share the same hashcode.
- The implementation of hash code decides effectiveness of Hashing.
- A good hashing function evenly distributes object's into different groups (or buckets).
- A good hashCode method should have the following properties:
    - If `obj1.equals(obj2)` is true, then `obj1.hashCode()` should be equal to `obj2.hashCode()`
    - `obj.hashCode()` should return the same value when run multiple times, if values of obj used in equals() have not changed.
    - If `obj1.equals(obj2)` is false, it is NOT required that `obj1.hashCode()` is not equal to `obj2.hashCode()`. Two unequal objects MIGHT have the same hashCode.
- A sample hashcode implementation of Client class which meets above constraints is given below:
    
    ```java
    //Client class
    @Override
    public int hashCode() {
    		final int prime = 31;
    		int result = 1;
    		result = prime * result + id;
    		return result;
    }
    ```
    

<br><br>
**What is Method Overloading?**

Method overloading in Java is a feature that allows a class to have **multiple methods with the same name but different parameters** (number, type, or order). It helps increase code readability and flexibility. Here, method resolution happens during compile-time. It is an example of Compile-time Polymorphism (or Static Polymorphism), as the compiler determines which method to bind based on the arguments.

It is also known as:

- **Compile-time polymorphism**
- **Static polymorphism**
- **Early binding**
    
    ```java
    class MathOperations {
    
        // Method with one parameter
        int add(int a) {
            return a + a;
        }
    
        // Method with two parameters
        int add(int a, int b) {
            return a + b;
        }
    
        // Method with different data types
        double add(double a, double b) {
            return a + b;
        }
    
        public static void main(String[] args) {
            MathOperations obj = new MathOperations();
            System.out.println(obj.add(5));        // 10
            System.out.println(obj.add(5, 3));     // 8
            System.out.println(obj.add(2.5, 4.5)); // 7.0
        }
    }
    ```
    

<br><br>
**What is method overriding?**

Method overriding in Java is a feature that allows a **child class to provide a specific implementation of a method already defined in its parent class**.

- The method in the subclass must have the **same name, same parameters, and same return type (or subtype)** as in the superclass.
- It is used to achieve **runtime polymorphism**.
- When the method is called using a **parent class reference pointing to a child object**, the **child class version of the method is executed**.
- It is an example of **Run-time Polymorphism** (or Dynamic Polymorphism), as the JVM determines which method version to execute based on the actual object type at run time.
- Let’s define an Animal class with a method shout.
    
    ```java
    public class Animal {
    		public String bark() {
    				return "Don't Know!";
    		}
    }
    ```
    
- Let’s create a sub class of Animal - Cat - overriding the existing shout method in Animal.
    
    ```java
    class Cat extends Animal {
    		public String bark() {
    				return "Meow Meow";
    		}
    }
    ```
    
<br><br>
**Can super class reference variable can hold an object of sub class?**

- Yes.
- Actor reference variables actor1, actor2 hold the reference of objects of sub classes of Comedian and Hero.
- Since Object is super class of all classes, an Object reference variable can also hold an instance of any class.
    
    ```java
    //Object is super class of all java classes
    Object object = new Hero();
    public class Actor {
    		public void act(){
    				System.out.println("Act");
    		};
    }
    
    //IS-A relationship. Hero is-a Actor
    public class Hero extends Actor {
    		public void fight(){
    				System.out.println("fight");
    		};
    }
    
    //IS-A relationship. Comedian is-a Actor
    public class Comedian extends Actor {
    		public void performComedy(){
    				System.out.println("Comedy");
    		};
    }
    
    Actor actor1 = new Comedian();
    Actor actor2 = new Hero();
    ```
    

**Is Multiple Inheritance allowed in Java?**

- Multiple Inheritance results in a number of complexities. Java does not support Multiple Inheritance through classes but support through interface.
    
    ```java
    class Dog extends Animal, Pet { //COMPILER ERROR
    }
    ```
    
- However, we can create an Inheritance Chain
    
    ```java
    class Pet extends Animal {
    }
    
    class Dog extends Pet {
    }
    ```
    

**What is an interface ?**

- An interface in Java is a blueprint of a class that contains public abstract methods and public static final constants.
- It defines a set of rules or behaviors that a class must follow, describing what a class should do but not **how** it should do it.
- A class implements an interface using the `implements` keyword and must provide implementations for all of its abstract methods.
- Interfaces are mainly used to achieve abstraction and multiple inheritance in Java.
- Syntax:
    
    ```java
    interface InterfaceName {
        // constant fields
        // abstract methods (implicitly public and abstract)
    }
    ```
    
- Example:
    
    ```java
    interface Animal {
        void makeSound(); // abstract method
    }
    
    class Dog implements Animal {
        public void makeSound() {
            System.out.println("Bark");
        }
    
        public static void main(String[] args) {
            Animal a = new Dog();
            a.makeSound(); // Output: Bark
        }
    }
    ```
    

**Can you explain a few tricky things about interfaces?**

- Variables in an interface are always public, static, final. Variables in an interface cannot be declared as private.
    
    ```java
    interface ExampleInterface1 {
    		//By default - public static final. No other modifier allowed
    		//value1,value2,value3,value4 all are - public static final
    		int value1 = 10;
    		public int value2 = 15;
    		public static int value3 = 20;
    		public static final int value4 = 25;
    		//private int value5 = 10;//COMPILER ERROR
    }
    ```
    
- Interface methods are by default public and abstract. Before Java 8, A concrete method (fully defined method) cannot be created in an interface. Consider the example below:
    
    ```java
    interface ExampleInterface1 {
    		//By default - public abstract. No other modifier allowed
    		void method1();//method1 is public and abstract
    		//private void method6();//COMPILER ERROR!
    		//This method, uncommented, would have given COMPILER ERROR!
    		//in Java 7. Allowed from Java 8.
    		default void method5() {
    				System.out .println("Method5");
    		}
    }
    ```
    

**Can you extend an interface?**

- An interface can extend another interface. Consider the example below:
    
    ```java
    interface SubInterface1 extends ExampleInterface1{
    		void method3();
    }
    ```
    
- Class implementing SubInterface1 should implement both methods - method3 and method1(from ExampleInterface1)
- An interface cannot extend a class.
    
    ```java
    /* //COMPILE ERROR IF UnCommented
    //Interface cannot extend a Class
    interface SubInterface2 extends Integer{
    void method3();
    }
    */
    ```
    

**Can a class extend multiple interfaces?**

- A class can implement multiple interfaces.
- It should implement all the method declared in all Interfaces being implemented.
- An example of a class in the JDK that implements several interfaces is HashMap, which implements the interfaces Serializable, Cloneable, and Map.
- By reading this list of interfaces, you can infer that an instance of HashMap (regardless of the developer or company who implemented the class) can be cloned, is serializable (which means that it can be converted into a byte stream; see the section Serializable Objects), and has the functionality of a map.
    
    ```java
    interface ExampleInterface2 {
    		void method2();
    }
    
    class SampleImpl implements ExampleInterface1,ExampleInterface2{
    		/* A class should implement all the methods in an interface.
    		If either of method1 or method2 is commented, it would
    		result in compilation error.
    		*/
    		public void method2() {
    				System.out.println("Sample Implementation for Method2");
    		}
    		public void method1() {
    				System.out.println("Sample Implementation for Method1");
    		}
    }
    ```
    

**What is Abstract class?**

- An **abstract class** in Java is a class declared with the `abstract` keyword and **cannot be instantiated directly**, meaning you cannot create its object.
- It can contain both **abstract methods** (without implementation) and **non-abstract methods** (with implementation).
- If a class contains **at least one abstract method**, the class itself must be declared **abstract**.
- Abstract classes are mainly used to provide a **common base or template** for subclasses to implement specific behaviors.
    
    ```java
    // Abstract class
    abstract class Fruit {
        abstract void taste();  // Abstract method
    }
    
    // Subclass providing implementation
    class Apple extends Fruit {
        void taste() {
            System.out.println("Apple tastes sweet");
        }
    
        public static void main(String[] args) {
            Fruit obj = new Apple();
            obj.taste();  // Output: Apple tastes sweet
        }
    }
    ```
    

**When Abstract methods are used?**

- An abstract class is used when you want to share common, already-implemented functionality among all subclasses.
- It allows partial implementation: some methods can be fully implemented, others can be abstract.
- You use an abstract class when all subclasses should inherit certain behavior or fields.
- An abstract class can have constructors, instance variables, and concrete methods.
- You cannot create an object of an abstract class; doing so causes a compilation error.
- Abstract classes often define at least one abstract method that subclasses must implement.
- A JDK example is `AbstractMap`, which provides common implementations like `get`, `put`, `isEmpty`, `containsKey`, and `containsValue`.
- `AbstractMap` also defines abstract methods such as `public abstract Set<Entry<K,V>> entrySet();` which subclasses must implement.
- It is correct that `HashMap`, `TreeMap`, and `ConcurrentHashMap` extend `AbstractMap`.
- These subclasses inherit shared functionality from `AbstractMap` while implementing their own map-specific behaviors.
- They are commonly used in **frameworks and APIs** where the base class defines structure and subclasses fill in details.
    
    ```java
    abstract class Animal {
        abstract void sound();  // Abstract method
    }
    
    class Dog extends Animal {
        void sound() {
            System.out.println("Dog barks");
        }
    }
    
    class Cat extends Animal {
        void sound() {
            System.out.println("Cat meows");
        }
    
        public static void main(String[] args) {
            Animal a1 = new Dog();
            Animal a2 = new Cat();
            a1.sound();  // Output: Dog barks
            a2.sound();  // Output: Cat meows
        }
    }
    ```
    

**What are the differences between abstract class and interface?**

| **Basis** | **Abstract Class** | **Interface** |
| --- | --- | --- |
| **Method Type** | Can contain both **abstract** and **non-abstract (concrete)** methods. | Contains only **abstract methods** (till Java 7). From Java 8 onward, can also have **default** and **static** methods. |
| **Keyword Used** | Declared using the **`abstract`** keyword. | Declared using the **`interface`** keyword. |
| **Inheritance Keyword** | Other classes use the **`extends`** keyword to inherit from an abstract class. | Classes use the **`implements`** keyword to implement an interface. |
| **Access Modifiers** | Can have members with **private, protected, or public** access modifiers. | All methods are **public and abstract** by default; all variables are **public, static, and final**. |
| **Multiple Inheritance** | Does **not support** multiple inheritance (a class can extend only one abstract class). | **Supports** multiple inheritance (a class can implement multiple interfaces). |
| **Final Methods** | Can contain **final methods**, which cannot be overridden. | **Cannot** have final methods because all methods are abstract by nature. |
| **Use Case** | Used when classes share **common behavior** or **partial implementation**. | Used to define a **contract** or **set of rules** that implementing classes must follow. |

**What is the constructor?**

- Constructor is a special method that is used to initialize objects.
- It has the same name as the class and does not have a return type, not even `void`.
- It is automatically called when an object is instantiated using the `new` keyword.
- Constructors are mainly used to assign initial values to instance variables or perform setup tasks.
    
    ```java
    class Animal {
      String name;
      // This is called a one argument constructor.
      public Animal(String name) {
        this.name = name;
      }
      public static void main(String[] args) {
        // Since we provided a constructor, compiler does not
        // provide a default constructor.
        // Animal animal = new Animal();//COMPILER ERROR!
        // The only way we can create Animal1 object is by using
        Animal animal = new Animal("Tommy");
      }
    }
    ```
    

**What is a Default Constructor ?**

- A default constructor is a no-argument constructor automatically provided by the compiler.
- It is generated only when the class has no explicitly defined constructors.
- It allows objects of the class to be created even when no constructor is written.
- It initializes all instance variables to their default values (such as 0, null, or false).
    
    ```java
    public class Animal {
      String name;
      public static void main(String[] args) {
        // Compiler provides this class with a default no-argument constructor.
        // This allows us to create an instance of Animal class.
        Animal animal = new Animal();
      }
    }
    ```
    

**Will this code compile?**

```java
class Animal {
  String name;
  public Animal() {
    this.name = "Default Name";
  }
  // This is called a one argument constructor.
  public Animal(String name) {
    this.name = name;
  }
  public static void main(String[] args) {
    Animal animal = new Animal();
  }
}
```

- The class `Animal` defines **two constructors**:
    1. A **no-argument constructor** (`public Animal()`)
    2. A **one-argument constructor** (`public Animal(String name)`)
- Since a no-argument constructor is explicitly defined, the code `new Animal()` in `main` can correctly call it.
- There are no syntax errors, and all variables are properly initialized.

**How do you call a Super Class Constructor from a Constructor?**

- A subclass constructor can invoke its superclass constructor using **super()**.
- The **super()** call must appear as the **first statement** in the constructor.
- Example of a constructor calling the superclass constructor:
    
    ```java
    public Animal() {
        super();     // Calls the superclass constructor
        this.name = "Default Name";
    }
    ```
    

**Will this code compile?**

```java
public Animal() {
    this.name = "Default Name";
    super();
}
```

No,  the code will **not compile** because **super() must be the first statement** in any constructor. No code is allowed before a call to `super()` or `this()`.

**What is the use of this()?**

- `this()` is used inside a constructor to call **another constructor of the same class**.
- It must always be the **first statement** in the constructor.
- It helps avoid code duplication by allowing one constructor to reuse the logic of another.
    
    ```java
    public Animal() {
        this("Default Name");  // Calls the one-argument constructor
    }
    
    public Animal(String name) {
        this.name = name;
    }
    ```
    
    Here, the no-argument constructor calls the one-argument constructor, passing `"Default Name"`.
    

**Can a constructor be called directly from a method?**

No, a constructor **cannot** be called directly from a regular method. It can only be invoked:

- **Implicitly** using `new ClassName()`
- **Or explicitly** using `this()` or `super()` **inside another constructor only**

**Example showing the error:**

```java
class Animal {
    String name;

    public Animal() {
    }

    public void method() {
        Animal();   // Compiler error — cannot call constructor this way
    }
}
```

**The correct way to create an object inside a method is:**

```java
public void method() {
    Animal a = new Animal();  // Valid
}
```

**Is a super class constructor called even when there is no explicit call from a sub class constructor?**

- If a subclass constructor does **not** explicitly call a superclass constructor using `super(...)`, then the compiler automatically inserts a call to the **no-argument constructor** of the superclass.
- This automatic call is placed as the **first statement** in the subclass constructor.
- Therefore, constructor calls happen in a **chain**, starting from the topmost superclass.
    
    ```java
    class Animal {
        public Animal() {
            System.out.println("Animal Constructor");
        }
    }
    
    class Dog extends Animal {
        public Dog() {
            System.out.println("Dog Constructor");
        }
    }
    
    class Labrador extends Dog {
        public Labrador() {
            System.out.println("Labrador Constructor");
        }
    }
    
    public class ConstructorExamples {
        public static void main(String[] args) {
            Labrador labrador = new Labrador();
        }
    }
    ```
    
- **Output:**
    
    ```bash
    Animal Constructor
    Dog Constructor
    Labrador Constructor
    
    ```
    

### Advanced Object Oriented Concepts

**What is Polymorphism?**

- Polymorphism is defined as “Same Code” giving “Different Behavior” i.e., **same code** (same method call) to behave **differently** depending on the object.
- In Java, this is achieved when a superclass reference variable refers to a subclass object, and the method that gets executed depends on the **actual object**, not the reference type.
- At runtime, Java decides which overridden method to call - this is **runtime polymorphism** or **dynamic method dispatch**.
- Superclass
    
    ```java
    public class Animal {
        public String shout() {
            return "Don't Know!";
        }
    }
    ```
    
- Subclass overriding the method
    
    ```java
    class Cat extends Animal {
        public String shout() {
            return "Meow Meow";
        }
    }
    
    class Dog extends Animal {
        public String shout() {
            return "BOW BOW";
        }
    
        public void run() {
        }
    }
    ```
    
- Case 1: Superclass reference → Superclass object
    
    ```java
    Animal animal1 = new Animal();
    System.out.println(animal1.shout());  
    // Output: Don't Know!
    ```
    
- Case 2: Superclass reference → Subclass object (Polymorphism)
    
    ```java
    Animal animal2 = new Dog();
    System.out.println(animal2.shout());
    // Output: BOW BOW
    ```
    
- Here, the `shout()` method of **Dog** is invoked because the actual object is a `Dog`, even though the reference type is `Animal`.
- **Important:** You cannot call subclass-specific methods using a superclass reference:
    
    ```java
    // animal2.run(); // COMPILE ERROR
    ```
    
    This is because the reference type (`Animal`) does not have a `run()` method.
    

**What is the use of `instanceof` Operator in Java?**

- The `instanceof` operator checks whether an object is an **instance of a specific class**, **subclass**, or **interface**.
- It returns **true** if the object is compatible with the given type; otherwise, it returns **false**.
- It works with **classes** as well as **interfaces**.
- If the compared types are **unrelated**, a **compile-time error** occurs.
- Example:
    
    ```java
    class SuperClass {}
    class SubClass extends SuperClass {}
    
    interface Interface {}
    
    class SuperClassImplementingInteface implements Interface {}
    class SubClass2 extends SuperClassImplementingInteface {}
    
    class SomeOtherClass {}
    ```
    
- Let’s consider the code below. We create a few instances of the classes declared above.
    
    ```java
    SubClass subClass = new SubClass();
    Object subClassObj = new SubClass();
    
    SubClass2 subClass2 = new SubClass2();
    SomeOtherClass someOtherClass = new SomeOtherClass();
    ```
    
- Let’s now run `instanceof` operator on the different instances created earlier.
    
    ```java
    System.out.println(subClass instanceof SubClass);//true
    System.out.println(subClass instanceof SuperClass);//true
    System.out.println(subClassObj instanceof SuperClass);//true
    System.out.println(subClass2 instanceof SuperClassImplementingInteface);//true
    ```
    
- `instanceof` can be used with interfaces as well. Since Super Class implements the interface, below code prints true.
    
    ```java
    System.out.println(subClass2 instanceof Interface);//true
    ```
    
- If the type compared is unrelated to the object, a compilation error occurs.
    
    ```java
    //System.out.println(subClass instanceof SomeOtherClass);//Compiler Error
    ```
    
- Object referred by `subClassObj(SubClass)`- NOT of type `SomeOtherClass`
    
    ```java
    System.out.println(subClassObj instanceof SomeOtherClass);//false
    ```
    

**What is Coupling?**

- **Coupling** refers to how much one class depends on another class.
- High coupling means many classes rely heavily on each other → harder to maintain and modify code.
- Low coupling means classes depend less on each other → easier to maintain, extend, and test.
- Good object-oriented design always aims for **low coupling**.
- **High Coupling**
    
    ```java
    class ShoppingCartEntry {
      float price;
      int quantity;
    }
    class ShoppingCart {
      ShoppingCartEntry[] items;
    }
    class Order {
      float cartTotalPrice = 0;
      
      // This method know the internal details of ShoppingCartEntry and
      // ShoppingCart classes. If there is any change in any of those
      // classes, this method also needs to change.
      for (int i = 0; i < cart.items.length; i++) {
        cartTotalPrice += cart.items[i].price *
          cart.items[i].quantity;
      }
      cartTotalPrice += cartTotalPrice * salesTax;
      return cartTotalPrice;
    }
    ```
    
    Inside `Order.orderTotalPrice()`:
    
    - The method directly accesses:
        - `cart.items`
        - `price`
        - `quantity`
    
    **Issues:**
    
    - Order class knows too many internal details of `ShoppingCart` and `ShoppingCartEntry`.
    - If any property name or structure changes, `Order` must also be modified.
    - This creates **high coupling**, which is bad for maintenance.
- **Low Coupling**
    
    ```java
    class ShoppingCartEntry {
      float price;
      int quantity;
      public float getTotalPrice() {
        return price * quantity;
      }
    }
    
    class CartContents {
      ShoppingCartEntry[] items;
      public float getTotalPrice() {
        float totalPrice = 0;
        for (ShoppingCartEntry item: items) {
          totalPrice += item.getTotalPrice();
        }
        return totalPrice;
      }
    }
    
    class Order {
      private CartContents cart;
      private float salesTax;
      public Order(CartContents cart, float salesTax) {
        this.cart = cart;
        this.salesTax = salesTax;
      }
      public float totalPrice() {
        return cart.getTotalPrice() * (1.0 f + salesTax);
      }
    }
    ```
    
    **Why this is better:**
    
    - `Order` does not access `price`, `quantity`, or internal array details.
    - It only calls `cart.getTotalPrice()` → a single, simple method.
    - Internal changes in `ShoppingCartEntry` or `CartContents` **won’t affect `Order`**.
    - This design has **low coupling** and is easier to maintain.

**What is Cohesion?**

- **Cohesion** measures how closely related and focused the responsibilities of a class are.
- A class with **high cohesion** has methods that perform tasks belonging to the **same purpose**.
- A class with **low cohesion** mixes unrelated responsibilities, making it harder to maintain, test, and reuse.
- Good object-oriented design aims for **high cohesion**.
- **Low cohesion example**
    
    ```java
    class DownloadAndStore {
        void downloadFromInternet() { }
        void parseData() { }
        void storeIntoDatabase() { }
        void doEverything() {
            downloadFromInternet();
            parseData();
            storeIntoDatabase();
        }
    }
    ```
    
    **Issue:**
    
    - This class is doing **three unrelated jobs**:
        - Downloading data
        - Parsing data
        - Storing data
    - The responsibilities are unrelated → **low cohesion**.
    - If one responsibility changes, this class becomes harder to maintain.
- **High cohesion example:** responsibilities are split into separate, focused classes
    
    ```java
    class InternetDownloader {
        public void downloadFromInternet() { }
    }
    
    class DataParser {
        public void parseData() { }
    }
    
    class DatabaseStorer {
        public void storeIntoDatabase() { }
    }
    
    class DownloadAndStore {
        void doEverything() {
            new InternetDownloader().downloadFromInternet();
            new DataParser().parseData();
            new DatabaseStorer().storeIntoDatabase();
        }
    }
    ```
    
    **Why this is better:**
    
    - Each class has **one clear responsibility** → high cohesion.
    - Easier to maintain, test, and reuse.
    - Changes in one process don’t affect other processes.
    - The main class (`DownloadAndStore`) simply coordinates the workflow.

**What is Encapsulation ?**

- **Encapsulation** means **hiding the internal details** (implementation) of a class and exposing only a **controlled interface**.
- It protects data by restricting direct access to class variables.
- Encapsulation allows changing the internal implementation **without affecting other code** that uses the class.
- Achieved using:
    - **private variables**
    - **public getter/setter methods** or
    - **public methods that perform actions**
- **Approach 1 - No Encapsulation (Worst)**
    
    ```java
    public class CricketScorer {
        public int score;
    }
    ```
    
    Main code:
    
    ```java
    scorer.score = scorer.score + 4;
    ```
    
    **Issues:**
    
    - Variables are public → anyone can modify them directly.
    - No control over how score changes.
    - Implementation cannot be changed safely → **no encapsulation**.
- **Approach 2 - Partial Encapsulation**
    
    ```java
    private int score;
    
    public int getScore() { return score; }
    public void setScore(int score) { this.score = score; }
    ```
    
    Main code still does the logic:
    
    ```java
    scorer.setScore(scorer.getScore() + 4);
    ```
    
    **Better but still not ideal:**
    
    - Internal variable is protected (private).
    - But logic remains outside the class → still some leakage of implementation.
- **Approach 3 — Full Encapsulation (Best)**
    
    Class handles both data and logic:
    
    ```java
    private int score;
    
    public void four() {
        score += 4;
    }
    ```
    
    Main code:
    
    ```java
    scorer.four();
    ```
    
    **Why this is best:**
    
    - External code does not know the internal variable `score` exists.
    - Implementation can change freely (e.g., tracking overs, wickets, bonus rules) without modifying other classes.
    - Clear separation of responsibilities → **high encapsulation**.
- **Final Summary:**
    - **Approach 3 > Approach 2 > Approach 1**
    - Higher encapsulation = better maintainability, safety, and flexibility.
    - Encapsulation hides complexity and exposes only necessary behavior.

**What is an Inner Class ?**

- An **Inner Class** is a class defined **inside another class**.
- It helps logically group classes that are only used by the outer class.
- Inner classes have access to **all members** (including private members) of the outer class.
- They improve **encapsulation**, readability, and organization of code.
- Java supports different types of inner classes:
    - **Non-static inner class**
        - Belongs to an **instance** of the outer class.
        - Can access all members of the outer class (including private).
            
            ```java
            class Outer {
                private String message = "Hello from Inner!";
            
                class Inner {
                    void display() {
                        System.out.println(message);
                    }
                }
            }
            
            class Test {
                public static void main(String[] args) {
                    Outer outer = new Outer();
                    Outer.Inner inner = outer.new Inner();
                    inner.display();
                }
            }
            ```
            
    - **Static nested class**
        - Behaves like a normal static member.
        - Cannot access non-static members of the outer class directly.
            
            ```java
            class Outer {
                static class Nested {
                    void show() {
                        System.out.println("Inside Static Nested Class");
                    }
                }
            }
            
            class Test {
                public static void main(String[] args) {
                    Outer.Nested obj = new Outer.Nested();
                    obj.show();
                }
            }
            ```
            
    - **Local inner class**
        - Defined **inside a method**.
        - Only accessible within that method.
            
            ```java
            class Outer {
                void outerMethod() {
                    class LocalInner {
                        void message() {
                            System.out.println("Inside Local Inner Class");
                        }
                    }
            
                    LocalInner inner = new LocalInner();
                    inner.message();
                }
            }
            
            class Test {
                public static void main(String[] args) {
                    new Outer().outerMethod();
                }
            }
            ```
            
    - **Anonymous inner class (special case)**
        - A class declared **without a name**, used for one-time use.
        - Often used with interfaces or abstract classes.
            
            ```java
            interface Greeting {
                void sayHello();
            }
            
            class Test {
                public static void main(String[] args) {
            
                    Greeting g = new Greeting() {
                        public void sayHello() {
                            System.out.println("Hello from Anonymous Class");
                        }
                    };
            
                    g.sayHello();
                }
            }
            ```
            

**Are classes allowed inside interfaces?**

**Yes.**

Java allows you to define **classes inside interfaces**, and these classes are **implicitly static**.

That means:

- They do **not** need an instance of the interface.
- They can be instantiated directly.
- They are often used for utility or helper purposes.

**Why is this allowed?**

Because interfaces can contain:

- **constants**
- **static methods**
- **static nested classes**

A class inside an interface is **automatically a static nested class**.

**Examples:**

- Class inside Interface
    
    ```java
    interface Vehicle {
    
        // Class inside interface (implicitly static)
        class Engine {
            void start() {
                System.out.println("Engine Started");
            }
        }
    }
    
    class Test {
        public static void main(String[] args) {
            Vehicle.Engine engine = new Vehicle.Engine();
            engine.start();
        }
    }
    ```
    
- Using for Constants / Helper Logic
    
    ```java
    interface MathConstants {
        double PI = 3.14;
    
        class Util {
            static double circleArea(double r) {
                return PI * r * r;
            }
        }
    }
    
    class Test {
        public static void main(String[] args) {
            double area = MathConstants.Util.circleArea(5);
            System.out.println(area);
        }
    }
    ```
    
- Interface + Embedded DTO (Common in real projects)
    
    ```java
    interface Response {
        class Data {
            int id;
            String message;
        }
    }
    ```
    

**What is an Anonymous class ?**

An **anonymous class** is a **class without a name**, created **on the fly**, usually when:

1. You want to **override a method** quickly
2. You need **one-time use** implementation
3. You don't want to formally create a separate class file

Anonymous classes are commonly used with:

- Interfaces
- Abstract classes
- Normal classes
    
    ```java
    class Animal {
        void bark() {
            System.out.println("Animal Bark");
        }
    }
    
    public class Test {
        public static void main(String[] args) {
    
            // 1. Anonymous class implementing an interface
            Comparator<String> reverse = new Comparator<String>() {
                @Override
                public int compare(String a, String b) {
                    return b.compareTo(a);
                }
            };
    
            String[] arr = { "Apple", "Cat", "Boy" };
            Arrays.sort(arr, reverse);
    
            // 2. Anonymous class extending a class
            Animal a = new Animal() {
                @Override
                void bark() {
                    System.out.println("Subclass bark");
                }
            };
    
            a.bark(); // prints: Subclass bark
        }
    }
    ```
    

### Modifiers

**What is default class modifier?**

- When a class has **no access modifier**, it automatically gets **default access**.
- Default access is also called **package-private** access.
- A **default (package-private)** class is visible **only within the same package**.
- It is call package-private because its visibility is limited **to the package in which it is declared**.
- Examples:
    - **Class with Default Access**
        
        ```java
        // package com.example.a;
        
        /* No 'public' keyword → default class */
        class DefaultClass {
        }
        ```
        
    - **Class in the SAME package - Allowed**
        
        ```java
        // package com.example.a;
        
        public class SamePackageClass {
            DefaultClass obj;  // ✔ Works
        }
        ```
        
    - **Class in a DIFFERENT package - Not Allowed**
        
        ```java
        // package com.example.b;
        
        public class DifferentPackageClass {
            // DefaultClass obj;  // ❌ Compile Error — class not visible
        }
        ```
        

**What is private access modifier?**

- Private variables and methods can be accessed only in the class they are declared.
- Private variables and methods from SuperClass are NOT available in SubClass.
    
    ```java
    class A {
        private int x = 10;
    
        private void display() {
            System.out.println("Private Method");
        }
    }
    
    class B extends A {
        void test() {
            // System.out.println(x);        // ❌ Compile Error
            // display();                    // ❌ Compile Error
        }
    }
    ```
    

**What is protected access modifier ?**

- `protected` members are accessible to **all classes in the same package** (just like *default/package-private* members).
- When accessed from a **different package**, a protected member can be accessed **only through inheritance**, not through a superclass object.

**What is public access modifier?**

- Public variables and methods can be accessed from every other Java classes.
- Public variables and methods from SuperClass are all available directly in the SubClass

**What is the use of a final modifier on a class?**

- A class declared with the `final` modifier **cannot be extended** (no subclass can be created).
- `final` is typically used when a class is meant to be **immutable** or its behavior should not be changed through inheritance.
- Final classes are used rarely because they **prevent reusability and extension** through inheritance.
- Common Java final classes include **String**, **Integer**, **Double**, and other wrapper classes.
- If a class tries to extend a final class, the code will **not compile**.
    
    ```java
    final public class FinalClass {
    }
    ```
    
- Below class will not compile if uncommented. FinalClass cannot be extended.
    
    ```java
    /*
    class ExtendingFinalClass extends FinalClass{ //COMPILER ERROR
    }
    */
    ```
    

**What is the use of a final modifier on a method?**

- A method declared with the `final` modifier **cannot be overridden** in any subclass.
- Final methods are used when the parent class wants to **prevent subclasses from changing the method’s behavior**.
- This helps maintain consistency, security, or correct functioning of critical methods.
- If a subclass tries to override a final method, the code will result in a **compile-time error**.
    
    ```java
    public class FinalMemberModifiersExample {
    		final void finalMethod(){
    		}
    }
    
    class SubClass extends FinalMemberModifiersExample {
      //final method cannot be over-riddent
      //Below method, uncommented, causes compilation Error
      /*
      final void finalMethod(){
      }
      */
    }
    ```
    

**What is a Final Variable ?**

- A variable declared with the `final` modifier becomes a **constant**, meaning its value cannot be changed after initialization.
- A final variable must be **assigned exactly once**—either at the point of declaration or inside a constructor (for instance variables).
- Attempting to reassign a final variable results in a **compile-time error**.
- Final variables are commonly used to represent fixed, unchangeable values.
    
    ```java
    final int finalValue = 5;
    //finalValue = 10; //COMPILER ERROR
    ```
    
- Final Variable example : java.lang.Math.PI

**What is a final argument ?**

- A **final argument** is a method parameter declared with the `final` keyword.
- The value of a final argument **cannot be changed** inside the method body.
- Attempting to reassign a final argument results in a **compile-time error**.
- It helps ensure that the parameter is treated as a **read-only value** within the method.
    
    ```java
    void testMethod(final int finalArgument){
    		//final argument cannot be modified
    		//Below line, uncommented, causes compilation Error
    		//finalArgument = 5;//COMPILER ERROR
    }
    ```
    

**What happens when a variable is marked as volatile?**

1. The `volatile` keyword can be applied **only to instance variables**, not to local variables.
2. A volatile variable’s value is **always read from and written to main memory**, not from a thread’s local cache.
3. This ensures **visibility of changes across threads** - when one thread updates a volatile variable, other threads immediately see the updated value.
4. Volatile prevents caching of the variable in thread-local memory, reducing certain concurrency issues.

**What is a Static variable ?**

- A **static variable** belongs to the class, not to individual objects.
- Only **one shared copy** of a static variable exists for the entire class, regardless of how many objects are created.
- Static methods also belong to the class and can access **only static variables** directly.
- Every time a new object is created, static variables do **not** get a new copy; they retain the shared value.
- In the example, each time a `Cricketer` object is created, the constructor increments the static `count`, and calling `Cricketer.getCount()` returns the total number of objects created.
    
    ```java
    public class Cricketer {
      private static int count;
      public Cricketer() {
        count++;
      }
      static int getCount() {
        return count;
      }
      public static void main(String[] args) {
        Cricketer cricketer1 = new Cricketer();
        Cricketer cricketer2 = new Cricketer();
        Cricketer cricketer3 = new Cricketer();
        Cricketer cricketer4 = new Cricketer();
        System.out.println(Cricketer.getCount()); //4
      }
    }
    ```
    

### Exception handling

**Why is Exception handling important ?**

- Large and complex applications inevitably encounter defects, even when written by good programmers.
- Exceptions will occur in real-world applications, so the system must handle them gracefully.
- Users should receive a **friendly, non-technical error message** instead of a system crash or confusing error screen.
- The message to the user should ideally include a **unique error ID** and clear instructions on how to contact support.
- The support team should receive **detailed information** (usually in logs) to help them debug the issue.
- When writing code, developers should think about **what information would be needed to diagnose failures** and ensure that information is captured when exceptions occur.
- The logged information should be tied to the **same unique error ID** that was shown to the user, making debugging easier.

**What design pattern is used to implement Exception handling Features in most languages?**

- When a method throws an exception and does not handle it, the exception is automatically **passed to the calling method**.
- If the calling method also does not handle the exception, it continues to propagate upward through the **call stack**.
- This process continues until the exception reaches a method that contains an appropriate **exception handler** (`try–catch`).
- If no method in the chain handles the exception, it eventually reaches the **JVM**, which terminates the program and prints a stack trace.
- This behavior is an example of the **Chain of Responsibility design pattern**, where a request (here, the exception) is passed along a chain of handlers.
- A real-world analogy is a **loan approval process**, where if a clerk cannot handle a request, it gets forwarded to the manager, then higher levels.
    
    ```java
    public static void main(String[] args) {
      method1();
    }
    private static void method1() {
      method2();
    }
    private static void method2() {
      String str = null;
      str.toString();
    }
    ```
    
- In the example, a `NullPointerException` is thrown in `method2()` and propagates to `method1()`, then to `main()` because none of the methods handle it.
- The stack trace shows the **path of propagation** clearly, listing each method in reverse order (from where the exception occurred up to the entry point).
    
    ```
    Exception in thread "main" java.lang.NullPointerException at
    com.rithus.exceptionhandling.ExceptionHandlingExample1.method2(ExceptionHandlingExample1.java:15) 
    at com.rithus.exceptionhandling.ExceptionHandlingExample1.method1(ExceptionHandlingExample1.java:10)
    at com.rithus.exceptionhandling.ExceptionHandlingExample1.main(ExceptionHandlingExample1.java:6)
    ```
    

**What is the need for finally block?**

- The `finally` block is used when some code must run **regardless of whether an exception occurs or not**.
- Without a `finally` block, important cleanup steps—such as closing files, database connections, or releasing resources—may be skipped when an exception occurs.
- In the below example, the connection is opened but not closed because the exception stops execution before `connection.close()` is reached.
- This results in a **dangling (unclosed) connection**, which is a resource leak and bad for application performance.
    
    ```java
    package com.rithus.exceptionhandling;
    class Connection {
      void open() {
        System.out.println("Connection Opened");
      }
      void close() {
        System.out.println("Connection Closed");
      }
    }
    public class ExceptionHandlingExample1 {
      // Exception Handling Example 1
      // Let's add a try catch block in method2
      public static void main(String[] args) {
        method1();
        System.out.println("Line after Exception - Main");
      }
      private static void method1() {
        method2();
        System.out.println("Line after Exception - Method 1");
      }
      private static void method2() {
        try {
          Connection connection = new Connection();
          connection.open();
          // LOGIC
          String str = null;
          str.toString();
          connection.close();
        } catch (Exception e) {
          // NOT PRINTING EXCEPTION TRACE- BAD PRACTICE
          System.out.println("Exception Handled - Method 2");
        }
      }
    }
    ```
    
    Output:
    
    ```
    Connection Opened
    Exception Handled - Method 2
    Line after Exception - Method 1
    Line after Exception - Main
    ```
    
- By placing `connection.close()` inside a `finally` block, it is guaranteed to run whether the try block completes normally or an exception is thrown.
    
    ```java
    private static void method2() {
      Connection connection = new Connection();
      connection.open();
      try {
        // LOGIC
        String str = null;
        str.toString();
      } catch (Exception e) {
        // NOT PRINTING EXCEPTION TRACE - BAD PRACTICE
        System.out.println("Exception Handled - Method 2");
      } finally {
        connection.close();
      }
    }
    ```
    
    Output:
    
    ```
    Connection Opened
    Exception Handled - Method 2
    Connection Closed
    Line after Exception - Method 1
    Line after Exception - Main
    ```
    
- The `finally` block executes even when an exception is caught in the `catch` block.
- The only rare situations where `finally` may not run are JVM crash, `System.exit()`, or hardware failure.
- Therefore, the `finally` block is ideal for **cleanup tasks** such as closing connections, releasing resources, or resetting states.

**In what scenarios is code in finally not executed?**

- The code inside a `finally` block is **almost always** executed, but there are a few scenarios where it will **not** run.
- **If an exception is thrown inside the finally block itself**, and it is not handled, the rest of the finally block may not execute.
- **If the JVM is forcibly terminated**, the finally block will not execute. This includes cases such as:
    - Calling `System.exit()` —> is **not** a JVM crash — it is an instructed shutdown — and it prevents the finally block from running.
    - Power failure or OS crash
    - JVM crash due to internal fatal error
- **If the thread executing the finally block is killed abruptly**, the code will not run.
- **If the program enters an infinite loop or hangs** before reaching finally, it also won’t execute.

**Will finally be executed in the program below?**

```java
private static void method2() {
  Connection connection = new Connection();
  connection.open();
  try {
    // LOGIC
    String str = null;
    str.toString();
    return;
  } catch (Exception e) {
    // NOT PRINTING EXCEPTION TRACE - BAD PRACTICE
    System.out.println("Exception Handled - Method 2");
    return;
  } finally {
    connection.close();
  }
}
```

Yes. It will be. Finally will be executed even when there is a return statement in try or catch.

**Is try without a catch is allowed?**

- A `try` block **can exist without a `catch` block**, but only if it is followed by a **`finally` block**.
- This pattern is useful when you want to perform cleanup actions (like closing a connection) **without handling the exception yourself**.
- In such cases, the exception will still be **thrown to the caller**, but the `finally` block ensures cleanup code runs before the exception propagates.
- The example shows that the connection is opened, the exception occurs, the `finally` block runs and closes the connection, and then the exception is thrown upward.
    
    ```java
    private static void method2() {
      Connection connection = new Connection();
      connection.open();
      try {
        // LOGIC
        String str = null;
        str.toString();
      } finally {
        connection.close();
      }
    }
    ```
    
    Output
    
    ```
    Connection Opened
    Connection Closed
    Exception in thread "main" java.lang.NullPointerException at
    com.rithus.exceptionhandling.ExceptionHandlingExample1.method2(ExceptionHandlingExample1.java:3
    3) at
    com.rithus.exceptionhandling.ExceptionHandlingExample1.method1(ExceptionHandlingExample1.java:2
    2) at
    com.rithus.exceptionhandling.ExceptionHandlingExample1.main(ExceptionHandlingExample1.java:17)
    ```
    
- This approach is common when cleanup is needed but actual exception handling is done by the calling method.

**Is try without catch and finally allowed?**

- No. Below method would give a Compilation Error!! (End of try block)
    
    ```java
    private static void method2() {
      Connection connection = new Connection();
      connection.open();
      try {
        // LOGIC
        String str = null;
        str.toString();
      } //COMPILER ERROR!!
    }
    ```
    
- A `try` block is used only when you are either
    - *handling* an exception (`catch`), or
    - *cleaning up resources* regardless of exceptions (`finally`).
- Without `catch` or `finally`, the `try` serves no purpose, hence the compile-time error.

**Explain the hierarchy of Java Exception Classes**

```
                       Throwable
                           |
        -----------------------------------
        |                                 |
       Error                          Exception
                                          |
                           --------------------------------
                           |                              |
                   RuntimeException          Checked Exceptions
                           |                          (Compile-time)
      ---------------------------------       ----------------------------
      |               |             |         |            |             |
 NullPointer   Arithmetic    IndexOutOf   IOException   SQLException   etc.
 Exception     Exception     BoundsException
```

- **`Throwable` (java.lang package) is the root class** of all errors and exceptions in Java.
    
    It defines basic behaviors like `printStackTrace()`, `getMessage()`, and all throwable types inherit from it.
    
- **`Throwable` has two main subclasses: `Error` and `Exception`, both in `java.lang` package.**
    
    These represent two different types of problems in Java programs.
    
- **`Error` (java.lang) represents serious, unrecoverable system issues** such as `OutOfMemoryError` and `StackOverflowError`.
    
    These are **unchecked** and should not be handled in application code because they are thrown by the JVM.
    
- **`Exception` (java.lang) represents issues that programs can handle**, often arising from external factors (I/O, database, logic).
    
    This branch contains both **checked** and **unchecked** exceptions.
    
- **`RuntimeException` (java.lang) is a subclass of Exception** and represents **unchecked**, programming-error-based issues.
    
    Examples: `NullPointerException`, `ArithmeticException`, `ArrayIndexOutOfBoundsException`.
    
- **Checked exceptions (mostly in java.io, java.sql, java.net, etc.)** must be handled using try–catch or declared with `throws`.
    
    Examples:
    
    - `IOException` → **java.io**
    - `FileNotFoundException` → **java.io**
    - `SQLException` → **java.sql**
    - `ClassNotFoundException` → **java.lang**
- **User-defined exceptions** can be created by extending `Exception` (checked) or `RuntimeException` (unchecked).
    
    They are usually created in the developer’s own package (e.g., `com.myapp.exceptions`).
    

**What is the difference between Error and Exception?**

| **Error** | **Exception** |
| --- | --- |
| Errors represent serious system-level problems and **cannot be recovered**. | Exceptions represent issues in program logic and **can be handled** using try-catch or throws. |
| **Always unchecked** in Java. | Includes **both checked and unchecked** types. |
| Caused mainly by the **environment or JVM** (e.g., memory issues). | Caused mostly by the **program itself** (invalid logic, wrong input, etc.). |
| Cannot be handled by application code. | Can be handled to allow normal program continuation. |
| Defined in **java.lang.Error**. | Defined in **java.lang.Exception**. |
| Examples: **OutOfMemoryError, StackOverflowError**. | Examples: Checked – **IOException, SQLException**; Unchecked – **NullPointerException, ArithmeticException**. |

**How many types of exceptions can occur in a Java program?**

- Java supports **two main types of exceptions: Built-in Exceptions and User-Defined Exceptions**.
- **Built-in Exceptions** come from Java libraries and are of two categories:
    - **Checked Exceptions** → Detected by the compiler; Java forces handling using try-catch or throws (e.g., IOException, FileNotFoundException, ClassNotFoundException).
    - **Unchecked Exceptions** → Not checked by the compiler; occur only at runtime (e.g., NullPointerException, ArithmeticException, ArrayIndexOutOfBoundsException, RuntimeException).
- **Checked & Unchecked exceptions both occur at runtime**, but the **compiler (javac) only enforces handling of checked exceptions**.
- **User-Defined Exceptions** are custom exceptions created by extending Exception or RuntimeException to handle application-specific scenarios not covered by built-in exceptions.
- Examples include: `InvalidAgeException`, `InsufficientBalanceException`, etc.

**What is the difference between Checked Exception and Unchecked Exception?**

| **Checked Exception** | **Unchecked Exception** |
| --- | --- |
| Checked at **compile time**. | Checked **only at runtime**. |
| Must be **handled using try-catch** or **declared using `throws`** in the method signature. | No need to handle or declare; compiler does **not** force handling. |
| Represent **recoverable conditions** (e.g., file missing, network issues). | Represent **programming errors** (e.g., null access, divide by zero). |
| All exceptions **outside `RuntimeException` and `Error`** are checked. | All exceptions under **`RuntimeException`** and **`Error`** are unchecked. |
| Includes **fully checked exceptions** (e.g., `IOException`, `InterruptedException`) and **partially checked ones** (e.g., `Exception`). | Examples: `NullPointerException`, `ArithmeticException`, `ArrayIndexOutOfBoundsException`, `IllegalArgumentException`. |

**How do you throw an exception from a method?**

1. **Use the `throw` keyword** followed by an exception object.
    
    Example:
    
    ```java
    throw new RuntimeException("Currencies don't match");
    ```
    
2. **Throwing an exception stops the normal flow** of the method immediately.
3. The exception is **propagated to the calling method**, unless it is handled with a `try–catch`.
4. In the example, the method `addAmounts()` throws a `RuntimeException` when currencies differ:
    
    ```java
    static Amount addAmounts(Amount amount1, Amount amount2) {
        if (!amount1.currency.equals(amount2.currency)) {
            throw new RuntimeException("Currencies don't match");
        }
        return new Amount(amount1.currency, amount1.amount + amount2.amount);
    }
    ```
    
5. Since `main()` does **not** handle the exception, it is propagated to the JVM, which prints:
    
    ```java
    Exception in thread "main" java.lang.RuntimeException: Currencies don't match
    
    ```
    
6. The printed message contains:
    - **Exception type** → `java.lang.RuntimeException`
    - **Custom message** → `"Currencies don't match"`
    - **Stack trace** showing where the exception occurred.

**What happens when you throw a Checked Exception from a method?**

- **Checked Exceptions must be declared or handled.**
    
    Unlike `RuntimeException` (unchecked), a checked exception **cannot be thrown freely**.
    
- If you throw a checked exception like `Exception`, the compiler requires that you handle it using:
    - a **try–catch block**, OR
    - a **throws clause** in the method signature.
- In your example:
    
    ```java
    static Amount addAmounts(Amount amount1, Amount amount2) {
        if (!amount1.currency.equals(amount2.currency)) {
            throw new Exception("Currencies don't match");  // COMPILER ERROR
        }
        return new Amount(amount1.currency, amount1.amount + amount2.amount);
    }
    ```
    
- This gives a **compiler error**:
    
    ```java
    Unhandled exception type Exception
    ```
    
    because `Exception` is a **checked exception**, and you did not:
    
    - catch it inside the method,
    - nor declare `throws Exception` in the method signature.
- To fix it, you must **declare the exception**:
    
    ```java
    static Amount addAmounts(Amount amount1, Amount amount2) throws Exception {
        if (!amount1.currency.equals(amount2.currency)) {
            throw new Exception("Currencies don't match");
        }
        return new Amount(amount1.currency, amount1.amount + amount2.amount);
    }
    ```
    
- **Or handle it locally** using try-catch:
    
    ```java
    static Amount addAmounts(Amount amount1, Amount amount2) {
        try {
            if (!amount1.currency.equals(amount2.currency)) {
                throw new Exception("Currencies don't match");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Amount(amount1.currency, amount1.amount + amount2.amount);
    }
    ```
    

**What are the options you have to eliminate compilation errors when handling checked exceptions?**
A **checked exception** (any exception that extends `Exception` but NOT `RuntimeException`) must be:

Either,

1. Declared (throws)
2. or, Handled (try-catch)

If you do neither → **Compilation Error**.

**Option 1: Declare the exception using `throws`**

- In the called method
    
    ```java
    static Amount addAmounts(Amount a1, Amount a2) throws Exception {
        if (!a1.currency.equals(a2.currency)) {
            throw new Exception("Currencies don't match");
        }
        return new Amount(a1.currency, a1.amount + a2.amount);
    }
    ```
    
- **But now the caller (main) also has a problem**
    
    Main must either declare or handle the exception because it calls a method that throws a checked exception.
    
- **Main declares the exception**
    
    ```java
    public static void main(String[] args) throws Exception {
        AmountAdder.addAmounts(new Amount("RUPEE", 5), new Amount("DOLLAR", 5));
    }
    ```
    
    Output:
    
    ```java
    Exception in thread "main" java.lang.Exception: Currencies don't match
    ```
    

**Option 2: Handle using try–catch**
Instead of declaring `throws`, the caller can **handle** the exception:

```java
public static void main(String[] args) {
    try {
        AmountAdder.addAmounts(new Amount("RUPEE", 5), new Amount("DOLLAR", 5));
    } catch (Exception e) {
        System.out.println("Exception Handled in Main");
    }
}
```

Output:

```java
Exception Handled in Main
```

**How do you create a Custom Exception?**

- **A custom exception is created by defining a new class that extends either `Exception` or `RuntimeException`.**
    - Extending `Exception` → **Checked exception** (must be declared with `throws` and handled).
    - Extending `RuntimeException` → **Unchecked exception** (no mandatory handling).
- **Creating a checked custom exception requires extending `Exception`.**
    
    ```java
    class CurrenciesDoNotMatchException extends Exception {
    }
    ```
    
    Because it is a checked exception, any method that may throw it must declare it using `throws`.
    
- Updated method that throws the custom checked exception:
    
    ```java
    class AmountAdder {
        static Amount addAmounts(Amount amount1, Amount amount2)
                throws CurrenciesDoNotMatchException {
            if (!amount1.currency.equals(amount2.currency)) {
                throw new CurrenciesDoNotMatchException();
            }
            return new Amount(amount1.currency, amount1.amount + amount2.amount);
        }
    }
    ```
    
    This signature change is mandatory because checked exceptions must be declared.
    
- **Calling code must handle or declare the checked exception.**
    
    Example with explicit catch:
    
    ```java
    public class ExceptionHandlingExample2 {
        public static void main(String[] args) {
            try {
                AmountAdder.addAmounts(
                    new Amount("RUPEE", 5),
                    new Amount("DOLLAR", 5)
                );
            } catch (CurrenciesDoNotMatchException e) {
                System.out.println("Exception Handled in Main " + e.getClass());
            }
        }
    }
    ```
    
    Using a `catch(Exception e)` block also works because **Exception is the parent class**.
    
- Changing the custom exception to extend `RuntimeException` makes it unchecked.
    
    ```java
    class CurrenciesDoNotMatchException extends RuntimeException {
    }
    ```
    
    - No `throws` declaration is needed.
    - The calling method is not forced to handle it.
- Method can remove the `throws` clause entirely:
    
    ```java
    class AmountAdder {
        static Amount addAmounts(Amount amount1, Amount amount2) {
            if (!amount1.currency.equals(amount2.currency)) {
                throw new CurrenciesDoNotMatchException();
            }
            return new Amount(amount1.currency, amount1.amount + amount2.amount);
        }
    }
    ```
    
- Calling code does not need a try–catch anymore (but it can still have one if desired).
    
    ```java
    public class ExceptionHandlingExample2 {
        public static void main(String[] args) {
            AmountAdder.addAmounts(
                new Amount("RUPEE", 5),
                new Amount("DOLLAR", 5)
            );
        }
    }
    ```
    
    If currencies do not match, the program will terminate with a runtime exception.
    
- **Checked vs Unchecked: Why choose one over the other?**
    - Use **checked** exceptions when:
        - The caller is expected to handle the error (e.g., invalid input, missing file).
    - Use **unchecked** exceptions when:
        - The error indicates a programming mistake or precondition violation (e.g., null values, illegal state).
- Best practice: include a message in the custom exception.
    
    ```java
    class CurrenciesDoNotMatchException extends Exception {
        public CurrenciesDoNotMatchException(String message) {
            super(message);
        }
    }
    ```
    

**What is the output of the program below?**

- **A `try` block can have multiple `catch` blocks, but their order must go from *most specific* to *most general*.**
    - If a superclass exception (e.g., `Exception`) appears *before* its subclass (e.g., `CurrenciesDoNotMatchException`),
        
        the subclass catch block becomes unreachable.
        
- **In Java, unreachable code inside exception handling blocks causes a *compilation error*, not a runtime error.**
    - Because `Exception` catches *all* exceptions, including `CurrenciesDoNotMatchException`,
        
        the compiler knows the specific catch block will **never** execute.
        
- **Given code:**
    
    ```java
    public static void main(String[] args) {
        try {
            AmountAdder.addAmounts(
                new Amount("RUPEE", 5),
                new Amount("DOLLAR", 5)
            );
        } catch (Exception e) {
            System.out.println("Handled Exception");
        } catch (CurrenciesDoNotMatchException e) {
            System.out.println("Handled CurrenciesDoNotMatchException");
        }
    }
    ```
    
- Corrected version (specific → general):
    
    ```java
    try {
        AmountAdder.addAmounts(
            new Amount("RUPEE", 5),
            new Amount("DOLLAR", 5)
        );
    } catch (CurrenciesDoNotMatchException e) {
        System.out.println("Handled CurrenciesDoNotMatchException");
    } catch (Exception e) {
        System.out.println("Handled Exception");
    }
    ```
    

**Can you explain about try with resources?**

- **Try-with-resources is a Java feature introduced in Java 7 that automatically closes resources when the try block finishes.**
    - The resource is closed whether the block completes normally or an exception occurs.
    - It reduces boilerplate code by removing the need for a `finally` block that manually closes resources.
- **A *resource* is anything that must be closed after use** — typically objects that hold system resources like:
    - File streams (`FileReader`, `BufferedReader`)
    - Database connections (`Connection`, `Statement`, `ResultSet`)
    - Network sockets
    - Zip streams and others
- **The resource must implement `AutoCloseable` or `Closeable`.**
    - These interfaces define the `close()` method that Java will automatically call at the end of the try block.
    - Most standard Java resource classes already implement these interfaces.
- **Syntax rules:**
    - Resources are declared inside parentheses after the `try` keyword.
    - Each resource is implicitly `final` (not required to be declared as such, but cannot be reassigned).
    - Multiple resources can be declared, separated by semicolons.
- **Corrected version of the provided code:**
    
    ```java
    try (BufferedReader br = new BufferedReader(new FileReader("FILE_PATH"))) {
        String line;
        while ((line = br.readLine()) != null) {
            System.out.println(line);
        }
    } catch (IOException e) {
        e.printStackTrace();
    }
    ```
    
    No `finally` block is required — the `BufferedReader` (and underlying `FileReader`) is automatically closed.
    

**How does try with resources work?**

```java
Start try-with-resources
        │
        ▼
Create resource(s)
        │
        ▼
Execute try block
        │
        ├── If no exception → proceed to close resources
        │
        └── If exception → run catch block, then close resources
        │
        ▼
Java automatically calls resource.close()
        │
        ▼
Continue execution
```

### Miscellaneous Topics

**What are the default values in an array?**

- **When you create a new array in Java, all elements are automatically initialized with default values.**
    - This happens even if you do **not** explicitly assign values.
    - Java guarantees this behavior for all array types.
- **Example:**
    
    ```java
    int marks2[] = new int[5];
    System.out.println(marks2[0]); // Output: 0
    ```
    
    - Since `marks2` is an `int` array, every element starts with `0`.
- **The default values depend on the type of the array.**
    - Primitive arrays have predefined defaults.
    - Object arrays default to `null`.
- **List of Default Values:**
    
    
    | Data Type Category | Types | Default Value |
    | --- | --- | --- |
    | Integer types | byte, short, int, long | `0` |
    | Floating types | float, double | `0.0` |
    | Character | char | `'\u0000'` (null character) |
    | Boolean | boolean | `false` |
    | Object references | Any object type (String, custom) | `null` |
- **char default value is often forgotten in interviews.**
    - A `char` array element prints as a blank because `'\u0000'` is a non-printable null character.
- **Object arrays always default to `null`.**
    
    Example:
    
    ```java
    String[] names = new String[3];
    System.out.println(names[0]); // null
    ```
    
- **Why does Java assign these defaults?**
    - Arrays are created in the **heap**, where fields always get default values.
    - Ensures predictable behavior and prevents uninitialized memory access.

**How do you loop around an array using enhanced for loop?**

- **An enhanced for loop (also called the “for-each” loop) is used to iterate over arrays or collections in a simple, readable way.**
    - You don’t need an index variable (`i`).
    - You don’t need to access elements using `array[i]`.
    - It automatically moves through the array from the first element to the last.
- **Syntax structure:**
    
    ```java
    for (Type element : array) {
        // use element
    }
    ```
    
    - `Type` → the type of elements in the array
    - `element` → a temporary variable that holds the value of each element during each loop iteration
    - `array` → the array you want to iterate over
- **Correct example:**
    
    ```java
    for (int mark : marks) {
        System.out.println(mark);
    }
    ```
    
    - `mark` is the variable that receives each value.
    - `marks` is the array we are looping through.
- **Enhanced for loop is best used when:**
    - You only need to **read** the values.
    - You do **not** need the index of the element.
    - You do not need to modify the array structure.
- **Enhanced for loop does *not* allow modifying the array index or skipping elements.**
    - For those operations, a classic `for` loop is required.
- **It avoids common mistakes like index out-of-bounds.**
    - Because you don’t manage indexes manually, the iteration is safer.

**How do you compare two arrays?**

- **Arrays in Java should not be compared using `==`.**
    - `==` only checks whether two array variables refer to the **same memory location**, not whether their contents match.
- **To compare the contents of two arrays, use `Arrays.equals()` from `java.util.Arrays`.**
    - This method checks:
        - Whether both arrays have the **same length**
        - Whether each corresponding element is **equal**
    - Only if both conditions match does it return `true`.
- **Correct example:**
    
    ```java
    int[] numbers1 = {1, 2, 3};
    int[] numbers2 = {4, 5, 6};
    
    System.out.println(Arrays.equals(numbers1, numbers2)); // false
    ```
    
- **Arrays are equal only when the size and each element match exactly.**
    
    ```java
    int[] numbers3 = {1, 2, 3};
    System.out.println(Arrays.equals(numbers1, numbers3)); // tru
    ```
    
- **For multidimensional arrays, use `Arrays.deepEquals()`.**
    - Example:
        
        ```java
        int[][] a = {{1,2}, {3,4}};
        int[][] b = {{1,2}, {3,4}};
        System.out.println(Arrays.deepEquals(a, b)); // true
        
        ```
        
- **`Arrays.equals()` works for:**
    - Primitive arrays (`int[]`, `double[]`, etc.)
    - Object arrays (`String[]`, `Integer[]`, etc.)
    - It uses `equals()` internally for object comparisons.
- **Why not use loops?**
    - Java’s `Arrays.equals()` is optimized and handles all edge cases (null checks, length mismatch, etc.).
    - It produces cleaner, more readable code.

**What is an Enum?**

- **An enum (short for “enumeration”) is a special data type in Java used to define a fixed set of constant values.**
    - It is used when a variable should hold only one value from a predefined set.
- **Enums improve type safety.**
    - Without enums, constants might be represented using integers or strings, which can lead to invalid values.
    - With an enum, only valid constant values are permitted.
- **Enums are more powerful than simple constants (`final static`).**
    - They can contain:
        - Fields
        - Methods
        - Constructors
        - Implement interfaces
- **Enums internally extend `java.lang.Enum`, not `Object`, and cannot extend other classes.**
    - But they can implement interfaces if needed.
- **Correct example of an enum:**
    
    ```java
    enum Season {
        WINTER, SPRING, SUMMER, FALL
    }
    ```
    
    - `Season` is a type that can take only one of the four values.
- **Usage example:**
    
    ```java
    Season s = Season.SUMMER;
    System.out.println(s); // SUMMER
    ```
    
- **Enums can be used in switch statements:**
    
    ```java
    switch (s) {
        case WINTER:
            System.out.println("Cold season");
            break;
        case SUMMER:
            System.out.println("Hot season");
            break;
    }
    ```
    
- **Enums provide built-in methods:**
    - `values()` → returns all enum constants as an array
    - `valueOf(String name)` → returns enum constant of the given name
    - `ordinal()` → returns the index of the enum constant (starting from 0)
    
    Example:
    
    ```java
    for (Season season : Season.values()) {
        System.out.println(season);
    }
    ```
    
- **Enums are commonly used for:**
    - State machines
    - Card suits (HEARTS, SPADES, …)
    - Directions (NORTH, SOUTH, …)
    - Status values (ACTIVE, INACTIVE, PENDING)
    - Configuration and command groups

**Can you use a Switch Statement around an Enum?**

- **Java allows using `switch` directly on enum types.**
    - The `switch` expression can be of an enum type.
    - Each `case` corresponds to one enum constant.
    - You do **not** need to prefix enum constants with the enum name inside the switch.
- **Inside the enum class, `this` refers to the current enum constant.**
    - So the switch works on the *current value* of the enum instance.
- **Corrected example of a switch on an enum:**
    
    ```java
    enum Season {
        WINTER, SPRING, SUMMER, FALL;
    
        public int getExpectedMaxTemperature() {
            switch (this) {
                case WINTER:
                    return 5;
    
                case SPRING:
                case FALL:
                    return 10;
    
                case SUMMER:
                    return 20;
            }
            return -1; // Fallback (although logically unreachable)
        }
    }
    ```
    
- **Multiple enum constants can share the same case block.**
    - As shown with `SPRING` and `FALL`, both return `10`.
- **Why the last `return -1` may still be needed?**
    - Java requires all code paths to return a value.
    - Even though all enum constants are covered, the compiler doesn’t perform exhaustiveness checks (until newer versions with enhanced switch).
    - Therefore, a fallback return is required for compilation.

**What are Variable Arguments or varargs ?**

- **Variable arguments (varargs) allow a method to accept zero or more arguments of the same type.**
    - Instead of defining multiple overloaded methods for different numbers of parameters, varargs handle all cases with a single method.
- **Syntax:**
    
    ```java
    returnType methodName(Type... parameterName)
    ```
    
    - The three dots `...` after the type tell the compiler that the method can receive multiple values of that type.
    - Internally, Java treats the argument as an **array**.
- **Inside the method, a varargs parameter behaves exactly like an array.**
    - You can iterate over it using an enhanced for loop or index-based loop.
    - If *no arguments* are passed, it is treated as an empty array (length = 0).
- **Example:**
    
    ```java
    public class VariableArgumentExamples {
    
        // Varargs method: accepts 0 or more int values
        public int sum(int... numbers) {
            int sum = 0;
            for (int number : numbers) {
                sum += number;
            }
            return sum;
        }
    
        public static void main(String[] args) {
            VariableArgumentExamples example = new VariableArgumentExamples();
    
            // 3 arguments
            System.out.println(example.sum(1, 4, 5));       // 10
    
            // 4 arguments
            System.out.println(example.sum(1, 4, 5, 20));   // 30
    
            // 0 arguments
            System.out.println(example.sum());              // 0
        }
    }
    ```
    
- **Varargs must always be the last parameter in the method signature.**
    
    Example (valid):
    
    ```java
    public void show(String label, int... values) { }
    ```
    
    Example (invalid):
    
    ```java
    public void show(int... values, String label) { } // ❌ compilation error
    ```
    
- **Only one varargs parameter is allowed in a method.**
    - Having more than one varargs parameter is not permitted.

**What are Asserts used for?**

- **Assertions are used to test assumptions that *must* be true in the program.**
    - They are mainly used during **development and testing**, not in production.
    - If an assumption is violated, it indicates a programmer error, not a user-level error.
- **When an assertion fails, Java throws `AssertionError`.**
    - But this happens **only if assertions are enabled** at runtime.
    - If assertions are disabled, the `assert` statement does nothing.
- **Syntax of assert:**
    
    ```java
    assert condition;
    ```
    
    or
    
    ```java
    assert condition : "Error message";
    ```
    
    - If the condition is `false`, an `AssertionError` is thrown.
- **Corrected example from your snippet:**
    
    ```java
    private int computeSimpleInterest(int principal, float interest, int years) {
        assert principal > 0 : "Principal must be positive";
        return 100; // simplified logic
    }
    ```
    
    - If `principal <= 0`, the assertion will fail (when enabled).

**What is Garbage Collection?**

- **Garbage Collection (GC) is Java’s automatic memory management mechanism.**
    - Java programs do not need to explicitly free memory.
    - The JVM automatically identifies and removes objects that are no longer needed.
- **GC frees memory by deleting objects that are no longer reachable.**
    - An object becomes “garbage” when no live part of the program holds a reference to it.
    - This prevents memory leaks and allows new objects to be created.
- **Goal of Garbage Collection:**
    - To keep as much heap memory free as possible.
    - To ensure efficient program execution by reducing memory pressure.
- **Garbage Collection works only on heap memory.**
    - Stack memory (local variables, method frames) is not garbage-collected.
    - Only dynamically allocated objects stored in the heap are eligible for GC.
- **GC uses reachability tests to determine which objects are alive.**
    
    A simplified flow looks like this:
    
    ```
    [Root References]
          |
          v
     Reachable Objects ----> Alive (kept in memory)
          |
          v
     Unreachable Objects --> Garbage --> Removed by GC
    ```
    
- **GC runs automatically but can be requested manually.**
    - You can call `System.gc()`, but the JVM may ignore it.
    - GC is not guaranteed to run immediately or predictably.
- **Garbage Collectors use different algorithms (depending on JVM version).**
    
    Common GC algorithms include:
    
    - Serial GC
    - Parallel GC
    - CMS (deprecated)
    - G1GC (default in modern JVMs)
    - ZGC / Shenandoah (low-latency collectors)

**Can you explain Garbage Collection with an example?**

- **When the method runs, it creates an object on the heap:**
    
    ```java
    Calendar calendar = new GregorianCalendar(2000, 10, 30);
    ```
    
    - A `GregorianCalendar` object is created in heap memory.
    - The reference variable `calendar` (stored on the stack) points to it.
- **After the method finishes, the local variable `calendar` goes out of scope.**
    - Method stack frame is removed.
    - No reference points to the `GregorianCalendar` object anymore.
    - The object becomes **unreachable**.
- **Unreachable objects become eligible for Garbage Collection.**
    - The JVM identifies that no active part of the program can access this object.
    - At a later time (not immediately), the Garbage Collector removes it from the heap.

**When is Garbage Collection run?**

- **GC runs automatically whenever the JVM decides it is necessary.**
    
    There is no fixed time schedule; the JVM continuously monitors memory usage.
    
- **Common situations when GC may run:**
    - **When heap memory is running low**
        
        If free memory drops below a threshold, JVM triggers GC to reclaim unused objects.
        
    - **When the CPU is relatively idle**
        
        If the system is not busy, JVM may perform background or low-priority garbage collection.
        
    - **When an allocation request cannot be satisfied**
        
        If a new object creation fails due to insufficient space, JVM immediately triggers GC.
        
- **GC may also run based on the garbage collector’s internal algorithm.**
    
    Different collectors (G1, Parallel GC, ZGC, Shenandoah) have their own heuristics and patterns of when to trigger GC cycles.
    
- **Manual request is possible but not guaranteed:**
    
    ```java
    System.gc();
    ```
    
    - This **suggests** GC to the JVM but does **not force** it.

**What are best practices on Garbage Collection?**

- **Avoid relying on `System.gc()`**
    - Calling `System.gc()` only *suggests* that the JVM run Garbage Collection; it does **not** guarantee it.
    - Overusing it can hurt performance because it may trigger unnecessary GC cycles.
    - JVM’s built-in GC algorithms are usually smarter than manual requests.
- **JVM may throw `OutOfMemoryError` if memory is full and no objects are eligible for GC**
    - GC runs automatically, but if all objects are still reachable or heap size is too small, GC cannot free enough space.
    - This results in `OutOfMemoryError`.
    - The solution is typically to fix memory leaks or increase heap size.
- **Do not use `finalize()` — avoid it completely**
    - `finalize()` is unreliable, slow, and unpredictable.
    - JVM does not guarantee when (or even *if*) `finalize()` will run.
    - It complicates object cleanup and can delay garbage collection.
    - **Modern Java versions (Java 9+) have deprecated `finalize()`** due to these problems.
- **Prefer try-with-resources or explicit close() over finalize() for releasing resources**
    - For files, sockets, streams, and connections, use:
        
        ```java
        try (FileInputStream fis = new FileInputStream("file.txt")) {
            // use resource
        }
        ```
        
    - This ensures deterministic resource cleanup, unlike GC.

**What are Initialization Blocks?**

- **Initialization Blocks are special code blocks inside a class that run automatically**
    
    They are executed either when the class is loaded or when an object is created, depending on their type.
    
    They allow you to run setup code without putting it inside a constructor or static method.
    
- **Types of Initialization Blocks:**
    
    **a) Static Initializer Block**
    
    - Runs **once** when the class is loaded into memory by the JVM.
    - Used to initialize static variables or run one-time setup code.
    - Syntax:
        
        ```java
        static {
            // executes when class is loaded
        }
        ```
        
    
    **b) Instance Initializer Block**
    
    - Runs **every time** a new object is created.
    - Executes **before the constructor**.
    - Used when multiple constructors need to run the same initialization code.
    - Syntax:
        
        ```java
        {
            // executes when object is created
        }
        ```
        

**What is Tokenizing?**

- **Tokenizing means breaking a string into smaller pieces (tokens) based on a delimiter.**
    - Example:
        
        String: `"ac;bd;def;e"`
        
        Delimiter: `";"`
        
        Tokens: `["ac", "bd", "def", "e"]`
        
- **In Java, tokenizing is commonly done using `String.split(regex)`**, which splits the string based on a **regular expression delimiter**.
    - The delimiter can be a simple character (e.g., `;`) or a complex regex (e.g., `\\s+`, `[.,;]`, etc.).
- **`split()` returns an array of Strings**, each representing one token extracted from the original string.
- Example:
    
    ```java
    private static void tokenize(String string, String regex) {
        String[] tokens = string.split(regex);
        System.out.println(Arrays.toString(tokens));
    }
    ```
    
    ```java
    tokenize("ac;bd;def;e", ";");
    ```
    

**What is Serialization?**

**Serialization** is the process of converting an object’s state into a **byte stream**, usually for:

- saving it to a file
- sending it over a network
- storing it for later use

**De-serialization** is the reverse process—reconstructing an object from its byte stream.

**How serialization works in Java ?**

1. A class must implement **`java.io.Serializable`** to allow its objects to be serialized.
2. JVM converts the object into bytes and writes it using:
    
    ```java
    ObjectOutputStream.writeObject(object);
    ```
    
3. JVM reconstructs the object using:
    
    ```java
    ObjectInputStream.readObject();
    ```
    

**Example** 

```java
class Person implements Serializable {
    String name;
    int age;
}
```

**Serialization:**

```java
ObjectOutputStream oos =
        new ObjectOutputStream(new FileOutputStream("person.ser"));
oos.writeObject(new Person());
oos.close();
```

**De-serialization:**

```java
ObjectInputStream ois =
        new ObjectInputStream(new FileInputStream("person.ser"));
Person p = (Person) ois.readObject();
ois.close();
```

**How do you serialize an object using Serializable interface?**

To serialize an object in Java:

1. **The class must implement `Serializable`**
    
    `Serializable` is a **marker interface** (it has *no methods*)—its presence tells the JVM that the object is allowed to be serialized.
    
    ```java
    class Rectangle implements Serializable {
        int length;
        int breadth;
        int area;
    
        public Rectangle(int length, int breadth) {
            this.length = length;
            this.breadth = breadth;
            this.area = length * breadth;
        }
    }
    ```
    
2. **Write the object using `ObjectOutputStream`**
    
    ```java
    FileOutputStream fileStream = new FileOutputStream("Rectangle.ser");
    ObjectOutputStream objectStream = new ObjectOutputStream(fileStream);
    
    objectStream.writeObject(new Rectangle(5, 6));  // serialization happens here
    
    objectStream.close();
    ```
    
3. **What happens internally?**
    - `ObjectOutputStream.writeObject()` converts the `Rectangle` object into a **byte stream**.
    - This byte stream is saved to the file `Rectangle.ser`.

**How do you de-serialize in Java?**

1. Deserialization is the process of converting the stored byte data back into a Java object exactly as it was during serialization.
2. To deserialize an object, you create a `FileInputStream` to read the serialized file and wrap it with an `ObjectInputStream`, which knows how to reconstruct objects.
3. The class of the object being deserialized must be available in the classpath and must implement `Serializable`, otherwise deserialization will fail.
4. The method `readObject()` returns an `Object`, so it must be explicitly cast to the correct type (in this case, `Rectangle`).
5. After reading the object, the stream must be closed to free system resources.
6. The field values of the reconstructed object will be the same as when it was serialized, unless some fields were marked `transient`.

**Example:**

```java
FileInputStream fileInputStream = new FileInputStream("Rectangle.ser");
ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

Rectangle rectangle = (Rectangle) objectInputStream.readObject();

objectInputStream.close();

System.out.println(rectangle.length);   // 5
System.out.println(rectangle.breadth);  // 6
System.out.println(rectangle.area);     // 30
```

**What do you do if only parts of the object have to be serialized?**

1. When only certain fields of an object should be saved during serialization, the fields that should **not** be stored are marked with the `transient` keyword.
2. A transient field is skipped during serialization, meaning its value is not written to the serialized file, and during deserialization it is restored with its **default value** (e.g., `0` for int, `null` for objects, `false` for boolean).
3. This is especially useful for values that can be re-calculated (like `area = length * breadth`), sensitive data (like passwords), or any information that should not be persisted.
4. After deserialization, since the transient field was not saved, the program should recompute or reinitialize that field if it is needed.

**Example:**

```java
class Rectangle implements Serializable {

    int length;
    int breadth;
    transient int area;

    public Rectangle(int length, int breadth) {
        this.length = length;
        this.breadth = breadth;
        this.area = length * breadth;
    }
}
```

**Output after deserialization:**

```java
System.out.println(rectangle.length);   // 5
System.out.println(rectangle.breadth);  // 6
System.out.println(rectangle.area);     // 0  (transient → not serialized)
```

**How do you serialize a hierarchy of objects?**

1. When an object contains other objects (object hierarchy), Java serialization requires **every object in that chain** to be serializable. If even one nested object does not implement `Serializable`, serialization will fail.
2. In the given example, `House` implements `Serializable`, but `Wall` does not. Since `House` contains a `Wall` reference, serialization throws `NotSerializableException` for `Wall`.
3. To fix this, either make the nested class serializable or mark the field as transient, depending on whether the nested object’s state should be preserved or ignored.
4. If `Wall` is marked `transient`, Java will skip it during serialization. After deserialization, the `wall` reference becomes `null`, meaning its state is lost.
5. If `Wall` implements `Serializable`, then the entire object graph (`House` along with its `Wall`) is serialized. During deserialization, both objects are recreated with their original values.

**Correct code option 1 (Wall not serialized):**

```java
class House implements Serializable {
    int number;
    transient Wall wall;

    public House(int number) {
        this.number = number;
    }
}

class Wall {
    int length;
    int breadth;
    int color;
}
```

**Correct code option 2 (Wall also serialized):**

```java
class House implements Serializable {
    int number;
    Wall wall;

    public House(int number) {
        this.number = number;
    }
}

class Wall implements Serializable {
    int length;
    int breadth;
    int color;
}
```

1. During deserialization, Option 2 preserves the wall's properties, while Option 1 results in `wall = null`.

**Are the constructors in an object invoked when it is de-serialized?**

- During deserialization, Java does **not** call the constructors of the class. The object is rebuilt directly from the serialized byte stream, restoring its fields exactly as they were when serialized.
- Because constructors are skipped, no initialization logic (including instance initializers or default field assignments) is executed. The object's state is taken purely from the serialized data.
- This means any values that were not serialized (such as `transient` fields) will be restored with their default values, not the values set by a constructor.
- This behavior ensures that the object’s state after deserialization matches exactly what it was at the time of serialization, without re-executing creation logic.
- If special initialization is needed after deserialization, a class can define `readObject(ObjectInputStream in)` to perform custom work, but this is separate from normal constructor execution.

**Are the values of static variables stored when an object is serialized?** 

- Static variables belong to the **class**, not to any specific object instance, so they are not included in an object’s serialized state.
- Because serialization captures only instance-level data, static fields are completely ignored during both serialization and deserialization.
- If you change the value of a static variable after serialization, the deserialized object will still see the updated static value, not the value that existed at serialization time.
- If you need a static value to be persisted, it must be handled manually (e.g., writing it separately to a file), because Java serialization will never include it.

### Collections

**Why do we need Collections in Java?**

- Arrays in Java have a fixed size. Once an array is created, its size cannot be increased or decreased, making it inconvenient when data size changes dynamically.
- To add or remove elements in an array, a new array must be created and all existing elements must be copied, which is inefficient and error-prone.
- Collections are designed to handle dynamic data, allowing elements to be added, removed, or modified easily at runtime.
- The Java Collections Framework provides multiple collection types (such as `List`, `Set`, and `Map`), so developers can choose the most suitable data structure based on the use case.
- Collections also provide built-in methods for common operations like searching, sorting, iteration, and synchronization, which reduces manual coding and improves reliability.

**What are the important interfaces in the Collection Hierarchy?**

- The **Collection interface** is the root interface of the collection hierarchy. It represents a group of objects (elements) and extends `Iterable`, which allows collections to be traversed using enhanced for-loops.
- The **Set interface** extends `Collection` and represents a collection that does **not allow duplicate elements**. If two objects are equal according to `equals()`, only one of them can exist in the set.
- The **List interface** extends `Collection` and represents an **ordered collection**. It allows duplicate elements and maintains insertion order. Elements can be added, accessed, or removed using their index positions.
- The **Queue interface** extends `Collection` and represents a collection designed for **processing elements in a specific order**, typically FIFO (First-In-First-Out). It is commonly used for task scheduling, messaging, and buffering.
- The **Map interface** is **not part of the Collection hierarchy**. It stores data as **key–value pairs**, where keys are unique and each key maps to exactly one value. It is used when data needs to be accessed using a unique identifier rather than position.
- Unlike `Collection`, `Map` does not extend `Iterable`, so it is traversed using views such as `keySet()`, `values()`, or `entrySet()`.
    
    ```java
    interface Collection < E > extends Iterable < E > {}
    // Unique things only - Does not allow duplication.
    // If obj1.equals(obj2) then only one of them can be in the Set.
    interface Set < E > extends Collection < E > {}
    // LIST OF THINGS
    // Cares about which position each object is in
    // Elements can be added in by specifying position - where should it be added
    in
    // If element is added without specifying position - it is added at the end
    interface List < E > extends Collection < E > {}
    // Arranged in order of processing - A to-do list for example
    // Queue interface extends Collection. So, it supports all Collection
    Methods.
    interface Queue < E > extends Collection < E > {}
    // A,C,A,C,E,C,M,D,H,A => {("A",5),("C",2)}
    // Key - Value Pair {["key1",value1],["key2",value2],["key3",value3]}
    // Things with unique identifier
    interface Map < K, V > {}
    ```
    

**What are the important methods that are declared in the Collection Interface?**

- The `Collection` interface defines the **basic operations** that can be performed on a group of objects, regardless of the specific collection type (List, Set, or Queue).
- `add(E e)` is used to **insert an element** into the collection. It returns `true` if the collection changes as a result of the call.
- `remove(Object o)` removes a **single matching element** from the collection, if it exists, and returns `true` if an element was removed.
- `size()` returns the **number of elements** currently present in the collection.
- `isEmpty()` checks whether the collection contains **no elements**.
- `clear()` removes **all elements** from the collection, leaving it empty.
- `contains(Object o)` checks whether a given object exists in the collection, using `equals()` for comparison.
- `containsAll(Collection<?> c)` returns `true` if the collection contains **all elements** of another collection.
- `addAll(Collection<? extends E> c)` adds **all elements** from another collection into the current collection.
- `removeAll(Collection<?> c)` removes from the collection **all elements** that are present in another collection.
- `retainAll(Collection<?> c)` removes all elements **except** those that are present in the specified collection (keeps the intersection).
- `iterator()` returns an `Iterator`, which allows **sequential traversal** of the collection and safe removal of elements during iteration.
    
    ```
    Collection
       |
       +-- add / addAll        -> insert elements
       +-- remove / removeAll -> delete elements
       +-- retainAll          -> keep common elements
       +-- contains           -> search elements
       +-- size / isEmpty     -> collection state
       +-- iterator           -> traverse elements
    ```
    

**Can you explain briefly about the List Interface?**

- The `List` interface extends the `Collection` interface, so it inherits all basic collection operations such as add, remove, size, and iteration.
- A `List` represents an **ordered collection**, meaning it maintains the **insertion order** of elements.
- When elements are added without specifying a position, they are inserted **at the end of the list**.
- A `List` allows **duplicate elements**, unlike `Set`.
- The `List` interface supports **index-based operations**, allowing elements to be accessed, inserted, updated, or removed using their position.
- The method `add(int index, E element)` inserts an element at a specific position, shifting existing elements to the right.
- The method `get(int index)` retrieves the element at the specified position.
- The method `set(int index, E element)` replaces the element at a specific position and returns the previous element.
- The method `remove(int index)` removes and returns the element at the specified position.
- `indexOf(Object o)` returns the index of the **first occurrence** of an element, while `lastIndexOf(Object o)` returns the index of the **last occurrence**.
- `ListIterator` allows **bidirectional traversal** of the list and supports element modification during iteration.
- `subList(int fromIndex, int toIndex)` returns a view of a portion of the list from `fromIndex` (inclusive) to `toIndex` (exclusive).
    
    ```java
    interface List < E > extends Collection < E > {
      boolean addAll(int paramInt, Collection < ? extends E > paramCollection);
      E get(int paramInt);
      E set(int paramInt, E paramE);
      void add(int paramInt, E paramE);
      E remove(int paramInt);
      int indexOf(Object paramObject);
      int lastIndexOf(Object paramObject);
      ListIterator < E > listIterator();
      ListIterator < E > listIterator(int paramInt);
      List < E > subList(int paramInt1, int paramInt2);
    }
    ```
    

**Explain about ArrayList with an example?**

- `ArrayList` is a resizable array implementation of the `List` interface, so it maintains **insertion order** and allows **duplicate elements**.
- Since `ArrayList` implements `List`, elements can be **added, accessed, updated, or removed using index positions**.
- An `ArrayList` grows dynamically when elements are added, unlike arrays which have a fixed size.
- An `ArrayList` of integers can be created as shown below:
    
    ```java
    List<Integer> integers =newArrayList<Integer>();
    ```
    
- Due to **auto-boxing**, primitive values like `int` are automatically converted into their wrapper class (`Integer`) when added to an `ArrayList`.
- When an element is added without specifying an index, it is appended to the **end of the list**.
- Example showing basic `ArrayList` operations:
    
    ```java
    List<Integer> integers =newArrayList<>();
    
    integers.add(5);// auto-boxed to Integer
    integers.add(10);
    integers.add(1,7);// inserted at index 1
    
    System.out.println(integers);// [5, 7, 10]
    
    integers.remove(0);// removes element at index 0
    
    System.out.println(integers);// [7, 10]
    ```
    
- `ArrayList` allows fast **random access** using indexes, making it suitable when frequent reads are required.

**Can an ArrayList have Duplicate elements?**

1. Yes, an `ArrayList` **can contain duplicate elements** because it implements the `List` interface, and `List` allows duplicates.
2. Elements in an `ArrayList` are stored in **insertion order**, even when duplicate values are added.
3. When an element is added without specifying an index, it is appended to the **end of the list**.
4. When an element is added with an index, it is inserted at that position and existing elements are shifted to the right.
5. Duplicate elements are treated as **separate entries**, each occupying its own position in the list.
6. The `size()` method returns the total number of elements, including duplicates.
7. The `contains(Object o)` method checks whether at least one matching element exists in the list.

**Verified example code:**

```java
List<String> arraylist =newArrayList<String>();

arraylist.add("Sachin");// [Sachin]
arraylist.add("Dravid");// [Sachin, Dravid]
arraylist.add(0,"Ganguly");// [Ganguly, Sachin, Dravid]
arraylist.add("Sachin");// [Ganguly, Sachin, Dravid, Sachin]

System.out.println(arraylist.size());// 4
System.out.println(arraylist.contains("Dravid"));// true
```

**How do you sort an ArrayList?**

1. An `ArrayList` can be sorted using the static `Collections.sort()` method provided by the Java Collections Framework.
2. The `Collections.sort(List)` method sorts the list **in place**, meaning it modifies the original `ArrayList`.
3. When sorting a list of `String` objects, the default sorting order is **natural (lexicographical/alphabetical) order**.
4. The elements must implement the `Comparable` interface for default sorting to work (which `String` already does).
5. After sorting, the elements are rearranged according to their natural order.

**Correct and verified example:**

```java
List<String> numbers =newArrayList<String>();
numbers.add("one");
numbers.add("two");
numbers.add("three");
numbers.add("four");

System.out.println(numbers);// [one, two, three, four]

// Sort the list alphabetically
Collections.sort(numbers);

System.out.println(numbers);// [four, one, three, two]
```

**How do you sort elements in an ArrayList using Comparable interface?**

1. To sort elements in an `ArrayList` using the **Comparable interface**, the class of the elements must implement `Comparable<T>` and provide an implementation of the `compareTo()` method.
2. The `compareTo()` method defines the **natural ordering** of objects of that class. It should return:
    - a positive number if the current object is greater than the other,
    - a negative number if it is smaller,
    - `0` if both are equal with respect to sorting.
3. In the given example, the `Cricketer` class implements `Comparable<Cricketer>` and defines sorting logic based on the `runs` field.
4. The `Collections.sort(List)` method uses the `compareTo()` method internally to reorder the elements of the list.
5. Since `Collections.sort()` sorts the list **in place**, the original `ArrayList` is modified.
6. After sorting, cricketers are ordered by increasing number of runs.

**Correct and verified code:**

```java
class Cricketer implements Comparable<Cricketer> {
    int runs;
    String name;

    public Cricketer(String name, int runs) {
        this.name = name;
        this.runs = runs;
    }

    @Override
    public String toString() {
        return name + " " + runs;
    }

    @Override
    public int compareTo(Cricketer that) {
        if (this.runs > that.runs) {
            return 1;
        }
        if (this.runs < that.runs) {
            return -1;
        }
        return 0;
    }
}
```

```java
List<Cricketer> cricketers =newArrayList<Cricketer>();
cricketers.add(newCricketer("Bradman",9996));
cricketers.add(newCricketer("Sachin",14000));
cricketers.add(newCricketer("Dravid",12000));
cricketers.add(newCricketer("Ponting",11000));

System.out.println(cricketers);
// [Bradman 9996, Sachin 14000, Dravid 12000, Ponting 11000]

Collections.sort(cricketers);

System.out.println(cricketers);
// [Bradman 9996, Ponting 11000, Dravid 12000, Sachin 14000]
```

**How do you sort elements in an ArrayList using Comparator interface?**

1. The **Comparator interface** is used when we want to define a **custom or multiple sorting orders** without changing the class whose objects are being sorted.
2. Unlike `Comparable`, which defines a natural order inside the class, `Comparator` is implemented in a **separate class** and contains the `compare()` method.
3. The `compare()` method compares two objects and returns:
    - a negative value if the first object should come before the second,
    - a positive value if the first object should come after the second,
    - `0` if both are considered equal.
4. In the given example, `DescendingSorter` sorts `Cricketer` objects by `runs` in **descending order**, so it returns `1` when the first cricketer has more runs.
5. The `Collections.sort(List, Comparator)` method uses the provided comparator to sort the list **in place** according to the custom logic.
6. This approach allows sorting the same list in **different ways** (ascending, descending, by name, etc.) without modifying the `Cricketer` class.

**Correct and verified code:**

```java
class DescendingSorter implements Comparator<Cricketer> {

    @Override
    public int compare(Cricketer cricketer1, Cricketer cricketer2) {
        if (cricketer1.runs > cricketer2.runs) {
            return -1;
        }
        if (cricketer1.runs < cricketer2.runs) {
            return 1;
        }
        return 0;
    }
}
```

```java
Collections.sort(cricketers,newDescendingSorter());
System.out.println(cricketers);
// [Sachin 14000, Dravid 12000, Ponting 11000, Bradman 9996]
```

**What is LinkedList? What interfaces does it implement? How is it different from an ArrayList?**

1. `LinkedList` is a Java collection class that stores elements using a **doubly linked list** structure, where each element holds references to both the previous and next elements.
2. `LinkedList` implements the **List** and **Queue** interfaces (and indirectly `Deque`), which means it supports list operations (index-based access) as well as queue and deque operations such as `add`, `remove`, `peek`, and `poll`.
3. Because of its linked structure, `LinkedList` is an **ideal choice for stack, queue, and deque implementations**, where frequent insertions and deletions are required.
4. Unlike `ArrayList`, `LinkedList` does not use an internal array. Each element is stored as a separate node linked to others, so **insertions and deletions are faster**, especially in the middle of the list.
5. Searching or accessing elements by index in a `LinkedList` is **slower** than in an `ArrayList` because it requires sequential traversal from the start or end.
6. `ArrayList` uses a dynamic array internally, which provides **fast random access**, but insertion or deletion in the middle is expensive because elements must be shifted.
7. Iteration over a `LinkedList` is generally **slower than an ArrayList** due to pointer traversal overhead.

**Simple visual comparison:**

```
ArrayList:
[ A ][ B ][ C ][ D ]  → contiguous memory

LinkedList:
A <-> B <-> C <-> D  → nodes linked in both directions
```

1. In summary, use `ArrayList` when **read/search operations are frequent**, and use `LinkedList` when **insertions and deletions are frequent**.

**Can you briefly explain about the Set Interface?**

1. The `Set` interface extends the `Collection` interface and therefore inherits all basic collection operations such as add, remove, size, and iteration.
2. The most important characteristic of a `Set` is that it **does not allow duplicate elements**.
3. If two objects are considered equal according to the `equals()` method, only one of them can exist in the set.
4. Unlike `List`, a `Set` does **not provide index-based access**, because ordering is not its primary concern.
5. Different implementations of `Set` handle ordering differently—for example, some maintain insertion order while others do not maintain any order.
6. `Set` is commonly used when uniqueness is more important than position, such as storing unique IDs, usernames, or distinct values.

**Simple visual representation:**

```
Add:  A, B, A, C

Set:
[A, B, C]   // duplicate A removed
```

**What are the important interfaces related to the Set Interface?**

1. The `Set` interface extends `Collection` and represents a collection that **does not allow duplicate elements**. If two objects are equal according to `equals()`, only one of them can exist in the set.
2. The `Set` interface itself does **not guarantee any ordering** of elements. Ordering depends on the specific implementation.
3. `SortedSet` extends `Set` and represents a set that maintains its elements in a **sorted order** according to their natural ordering or a provided `Comparator`.
4. `SortedSet` provides methods such as `first()` and `last()` to access the smallest and largest elements, and range-view methods like `subSet()`, `headSet()`, and `tailSet()`.
5. `NavigableSet` extends `SortedSet` and adds **navigation methods** to find elements relative to a given value.
6. Methods like `lower()`, `floor()`, `ceiling()`, and `higher()` help locate the closest matching elements before or after a given element.
7. `NavigableSet` also allows removal and retrieval of boundary elements using `pollFirst()` and `pollLast()`.

**Simple visual hierarchy:**

```
Collection
    |
    Set
     |
     SortedSet
          |
       NavigableSet
```

1. These interfaces are useful when working with **unique elements** that also require **sorting and efficient navigation**.

**What is the difference between Set and SortedSet interfaces?**

1. `SortedSet` extends the `Set` interface, so **both do not allow duplicate elements**.
2. A `Set` **does not guarantee any order** of elements. The order depends on the specific implementation.
3. A `SortedSet` **always maintains elements in sorted order**, either by natural ordering or by a provided `Comparator`.
4. If elements `4, 5, 3` are added to a `Set`, the iteration order can be unpredictable.
5. If the same elements are added to a `SortedSet`, they are automatically arranged in sorted order as `3, 4, 5`.
6. `SortedSet` provides additional methods like `first()`, `last()`, and range views (`subSet`, `headSet`, `tailSet`) which are not available in `Set`.

**Simple illustration:**

```
Insert: 4, 5, 3

Set       →  [5, 3, 4]  (order not guaranteed)
SortedSet →  [3, 4, 5]  (always sorted)
```

**Can you give examples of classes that implement the Set Interface?**

1. **HashSet**
    - Implements `Set`
    - Does **not maintain insertion order**
    - Does **not sort elements**
    - Uses `hashCode()` and `equals()` for storage and lookup
    - Fast for add, remove, and search operations
    
    ```
    Insertion order: A, X, B
    Stored order:    X, A, B (can vary)
    ```
    
2. **LinkedHashSet**
    - Implements `Set` and extends HashSet.
    - Maintains **insertion order**
    - Does **not sort elements**
    - Slightly slower than `HashSet` due to linked structure
    
    ```
    Insertion order: A, X, B
    Stored order:    A, X, B
    ```
    
3. **TreeSet**
    - Implements `Set`, `SortedSet`, and `NavigableSet`
    - Stores elements in **sorted order**
    - Sorting is natural or via `Comparator`
    - Slower than `HashSet` due to tree-based structure
    
    ```
    Insertion order: A, C, B
    Stored order:    A, B, C
    ```
    

```
Set
 ├── HashSet        → unordered, unsorted
 ├── LinkedHashSet  → ordered (insertion order), unsorted
 └── TreeSet        → sorted (natural / comparator)
```

**What is a HashSet?**

1. `HashSet` is a class that implements the **Set interface**, so it **does not allow duplicate elements**.
2. `HashSet` does **not maintain any order**. The order in which elements are inserted is not guaranteed to be the order in which they are stored or iterated.
3. `HashSet` internally uses a **hashing mechanism** (`hashCode()` and `equals()`) to store and identify elements efficiently.
4. When an element is added, `HashSet` first checks for duplicates using `hashCode()` and then `equals()`. If an equal element already exists, the new one is not added.
5. The `add()` method returns:
    - `true` if the element was successfully added
    - `false` if the element was a duplicate and not added
6. `HashSet` provides **fast performance** for basic operations like add, remove, and contains, making it suitable when order is not important.

**Correct example:**

```java
Set<String> hashset =newHashSet<String>();

hashset.add("Sachin");
System.out.println(hashset);// [Sachin]

hashset.add("Dravid");
System.out.println(hashset);// [Sachin, Dravid]

// Duplicate element
hashset.add("Sachin");// returns false
System.out.println(hashset);// [Sachin, Dravid]
```

**What is a `LinkedHashSet`? How is different from a HashSet?**

1. `LinkedHashSet` is an implementation of the **Set interface**, so it **does not allow duplicate elements**, just like `HashSet`.
2. The key difference between `LinkedHashSet` and `HashSet` is **ordering**.
    
    `LinkedHashSet` **maintains insertion order**, whereas `HashSet` does **not guarantee any order**.
    
3. Internally, `LinkedHashSet` uses a **hash table + doubly linked list**.
    - Hash table → ensures uniqueness and fast lookup
    - Linked list → preserves insertion order
4. When iterating over a `LinkedHashSet`, elements are returned **in the same order in which they were added**.
5. `HashSet`, on the other hand, only uses hashing. The iteration order can appear random and may change over time.
6. Because of the extra linked structure, `LinkedHashSet` uses slightly more memory than `HashSet`, but lookup and insertion performance remain close to `HashSet`.

**Example:**

```java
Set<String> linkedHashSet =newLinkedHashSet<>();

linkedHashSet.add("Sachin");
linkedHashSet.add("Dravid");
linkedHashSet.add("Ganguly");

System.out.println(linkedHashSet);
// [Sachin, Dravid, Ganguly]
```

**Comparison with HashSet:**

```java
Set<String> hashSet =newHashSet<>();

hashSet.add("Sachin");
hashSet.add("Dravid");
hashSet.add("Ganguly");

System.out.println(hashSet);
// Order not guaranteed
```

**What is a TreeSet? How is different from a HashSet?**

1. `TreeSet` is an implementation of the `Set` interface that also implements `SortedSet` and `NavigableSet`, which means it stores **unique elements in sorted order**.
2. Like all `Set` implementations, `TreeSet` **does not allow duplicate elements**. If an element already exists (as determined by comparison), it will not be added.
3. `TreeSet` maintains elements in their **natural ordering** (for example, alphabetical order for strings or ascending order for numbers), or according to a provided `Comparator`.
4. Unlike `HashSet`, `TreeSet` **guarantees sorted order** when iterating over elements.
5. `HashSet` stores elements based on hash codes and **does not guarantee any order**—neither insertion order nor sorted order.
6. Internally, `TreeSet` uses a **balanced tree structure (Red-Black Tree)**, which makes operations like add, remove, and search slower than `HashSet` but enables sorting and navigation.
7. `HashSet` is generally faster for add, remove, and search operations because it uses a **hash table** internally.
8. `TreeSet` supports navigation methods such as `first()`, `last()`, `higher()`, and `lower()`, which are not available in `HashSet`.

**Correct and verified example:**

```java
Set<String> treeSet =newTreeSet<String>();

treeSet.add("Sachin");
System.out.println(treeSet);// [Sachin]

treeSet.add("Dravid");
System.out.println(treeSet);// [Dravid, Sachin]

treeSet.add("Ganguly");
System.out.println(treeSet);// [Dravid, Ganguly, Sachin]

// Duplicate element
treeSet.add("Sachin");// returns false
System.out.println(treeSet);// [Dravid, Ganguly, Sachin]
```

**Can you give examples of implementations of NavigableSet?**

- `NavigableSet` is an interface that extends `SortedSet` and provides **navigation methods** to locate elements relative to a given value (lower, higher, floor, ceiling).
- **`TreeSet` is the most common and standard implementation of the `NavigableSet` interface**. It stores elements in **sorted order** (natural ordering or using a Comparator).
- When elements are added to a `TreeSet`, they are automatically sorted:
    
    ```java
    TreeSet<Integer> numbersTreeSet =newTreeSet<Integer>();
    numbersTreeSet.add(55);
    numbersTreeSet.add(25);
    numbersTreeSet.add(35);
    numbersTreeSet.add(5);
    numbersTreeSet.add(45);
    ```
    
    Internally stored as:
    
    ```
    [5, 25, 35, 45, 55]
    ```
    
- The `lower(E e)` method returns the **greatest element strictly less than** the given element.
    
    ```java
    System.out.println(numbersTreeSet.lower(25));// 5
    ```
    
- The `floor(E e)` method returns the **greatest element less than or equal to** the given element.
    
    ```java
    System.out.println(numbersTreeSet.floor(25));// 25
    ```
    
- The `higher(E e)` method returns the **smallest element strictly greater than** the given element.
    
    ```java
    System.out.println(numbersTreeSet.higher(25));// 35
    ```
    
- The `ceiling(E e)` method returns the **smallest element greater than or equal to** the given element.
    
    ```java
    System.out.println(numbersTreeSet.ceiling(25));// 25
    ```
    
- These navigation methods are especially useful for **range queries**, **closest-value searches**, and **ordered data processing**.

**Explain briefly about Queue Interface?**

1. The `Queue` interface extends the `Collection` interface and represents a collection designed to **hold elements for processing in a specific order**, typically **FIFO (First-In-First-Out)**.
2. A `Queue` is commonly used in scenarios such as task scheduling, request handling, message processing, and buffering.
3. Elements are usually **added at the tail** of the queue and **processed from the head**.
4. The `add(E e)` method inserts an element into the queue and **throws an exception** if the insertion fails.
5. The `offer(E e)` method also inserts an element into the queue but **returns `false` instead of throwing an exception** if the insertion fails.
6. The `remove()` method retrieves **and removes** the head of the queue and **throws an exception** if the queue is empty.
7. The `poll()` method retrieves **and removes** the head of the queue but **returns `null`** if the queue is empty.
8. The `element()` method retrieves the head of the queue **without removing it** and throws an exception if the queue is empty.
9. The `peek()` method retrieves the head of the queue **without removing it** and returns `null` if the queue is empty.
10. The main difference between `peek()` and `poll()` is that `peek()` only looks at the head element, while `poll()` looks at **and removes** the head element.

**Simple visual representation of a Queue (FIFO):**

```
Insert --> [ A ][ B ][ C ] --> Remove
             ↑
           Head
```

This makes the `Queue` interface ideal for **ordered, sequential processing of elements**.

**What are the important interfaces related to the Queue Interface?**

1. The **Queue interface** has two important sub-interfaces that extend its functionality: **Deque** and **BlockingQueue**.
2. **Deque (Double Ended Queue)** extends `Queue` and allows insertion and removal of elements **from both ends** of the queue.
    - Elements can be added or removed from the **front or rear**.
    - It can be used to implement both **stack (LIFO)** and **queue (FIFO)** behavior.
    - Common implementations include `ArrayDeque` and `LinkedList`.
3. **BlockingQueue** extends `Queue` and is mainly used in **multi-threaded environments**.
    - It supports operations that **wait (block)** if the queue is full (while adding) or empty (while removing).
    - This makes it ideal for **producer–consumer** problems.
    - Common implementations include `ArrayBlockingQueue`, `LinkedBlockingQueue`, and `PriorityBlockingQueue`.
4. Unlike a normal `Queue`, a `BlockingQueue` handles thread coordination automatically, so explicit synchronization is usually not required.

**Simple visual representation:**

```
Collection
     |
    Queue
     |
     +---- Deque           (Insert / Remove at both ends)
     |
     +---- BlockingQueue   (Thread-safe, blocking operations)

```

These interfaces extend the basic queue concept to support **double-ended access** and **concurrent processing** scenarios.

**Explain about the Deque interface?**

1. `Deque` stands for **Double Ended Queue** and extends the `Queue` interface, meaning it supports all queue operations plus additional ones.
2. A `Deque` allows **insertion and removal of elements at both ends** — the front and the rear.
3. Because of this flexibility, a `Deque` can be used to implement **both Stack (LIFO)** and **Queue (FIFO)** behavior.
4. Methods like `addFirst(E e)` and `addLast(E e)` insert elements at the beginning and end of the deque respectively. These methods throw an exception if the operation fails.
5. Methods `offerFirst(E e)` and `offerLast(E e)` also insert elements at the beginning or end, but instead of throwing an exception, they return `false` if insertion fails.
6. `removeFirst()` and `removeLast()` retrieve and remove elements from the front or rear of the deque and throw an exception if the deque is empty.
7. `pollFirst()` and `pollLast()` retrieve and remove elements from the front or rear, but return `null` if the deque is empty.
8. `getFirst()` and `getLast()` retrieve elements from the front or rear **without removing them**, and throw an exception if the deque is empty.
9. `peekFirst()` and `peekLast()` retrieve elements from the front or rear **without removing them**, but return `null` if the deque is empty.
10. Methods `removeFirstOccurrence(Object o)` and `removeLastOccurrence(Object o)` remove the first or last matching element from the deque.
11. Common implementations of `Deque` include **`ArrayDeque`** and **`LinkedList`**.

**Simple visual representation:**

```
Front <-> [ A ] <-> [ B ] <-> [ C ] <-> Rear

addFirst -> [ X A B C ]
addLast  -> [ A B C Y ]

removeFirst -> removes X
removeLast  -> removes Y
```

This makes `Deque` very useful when elements need to be processed efficiently from **both ends**.

**Explain the `BlockingQueue` interface?**

1. `BlockingQueue` is a sub-interface of `Queue` that is designed for **thread-safe communication between producer and consumer threads**.
2. Unlike a normal `Queue`, a `BlockingQueue` supports **blocking operations**, meaning a thread can wait:
    - while trying to **add an element** if the queue is full
    - while trying to **remove an element** if the queue is empty
3. This makes `BlockingQueue` very useful in **concurrent programming**, especially in producer–consumer scenarios.
4. The methods `add(E e)` and `offer(E e)` behave the same as in the `Queue` interface:
    - `add()` inserts immediately and throws an exception if the queue is full
    - `offer()` inserts immediately and returns `false` if the queue is full
5. The `put(E e)` method inserts an element but **waits (blocks)** if the queue is currently full until space becomes available.
6. The overloaded `offer(E e, long timeout, TimeUnit unit)` waits for a specified time to insert the element and returns `false` if the timeout expires.
7. The `take()` method retrieves and removes the head of the queue, **waiting indefinitely** if the queue is empty until an element becomes available.
8. The `poll(long timeout, TimeUnit unit)` method waits up to the specified time to retrieve an element and returns `null` if no element becomes available within that time.
9. `remainingCapacity()` returns the number of additional elements that the queue can accept without blocking.
10. Methods like `drainTo()` transfer elements from the queue to another collection efficiently, often used for batch processing.
11. Common implementations of `BlockingQueue` include `ArrayBlockingQueue`, `LinkedBlockingQueue`, `PriorityBlockingQueue`, and `SynchronousQueue`.

**Simple producer–consumer flow using BlockingQueue:**

```
Producer Thread
     |
     |  put(e)  (waits if queue is full)
     v
-------------------
|  BlockingQueue  |
-------------------
     ^
     |  take() (waits if queue is empty)
     |
Consumer Thread
```

1. Because all blocking behavior is handled internally, `BlockingQueue` helps write **cleaner, safer multithreaded code** without manual synchronization.

**What is a `PriorityQueue`?**

1. `PriorityQueue` is an implementation of the `Queue` interface where **elements are ordered based on priority**, not insertion order.
2. By default, a `PriorityQueue` orders elements according to their **natural ordering** (for numbers, smaller values have higher priority).
3. Internally, `PriorityQueue` is implemented using a **heap data structure**, which ensures efficient retrieval of the highest-priority element.
4. When elements are printed, the internal structure may look unsorted, but **peek() and poll() always return the highest-priority element**.
5. The `offer()` method is used to insert elements into the priority queue.
6. The `peek()` method retrieves the element with the **highest priority** without removing it from the queue.
7. The `poll()` method retrieves **and removes** the element with the highest priority from the queue.

**Verified example using natural ordering:**

```java
PriorityQueue<Integer> priorityQueue =newPriorityQueue<Integer>();

priorityQueue.offer(24);
priorityQueue.offer(15);
priorityQueue.offer(9);
priorityQueue.offer(45);

System.out.println(priorityQueue);// [9, 24, 15, 45] (internal heap order)

System.out.println(priorityQueue.peek());// 9
System.out.println(priorityQueue);// [9, 24, 15, 45]

System.out.println(priorityQueue.poll());// 9
System.out.println(priorityQueue);// [15, 24, 45]
```

1. A custom priority order can be defined by passing a `Comparator` to the `PriorityQueue` constructor.
2. The following comparator gives **higher priority to larger numbers** (reverse order):

```java
Comparator<Integer> reverseComparator =newComparator<Integer>() {
publicintcompare(Integer paramT1, Integer paramT2) {
return paramT2 - paramT1;
    }
};

PriorityQueue<Integer> maxPriorityQueue =
newPriorityQueue<Integer>(reverseComparator);
```

1. With this comparator, calling `peek()` or `poll()` will return the **largest element first**.

**Simple visual representation:**

```
Elements added: 24, 15, 9, 45

Priority (natural order):
9 → 15 → 24 → 45

peek() / poll() always return → 9
```

1. `PriorityQueue` does **not allow null elements** and is **not thread-safe**.
2. It is commonly used in scheduling tasks, job processing, and algorithms like Dijkstra’s or Huffman coding where priority matters.

**Can you give example implementations of the BlockingQueue interface?**

- `BlockingQueue` is commonly used in **producer–consumer** scenarios where one thread produces data and another consumes it, and the queue handles waiting automatically.
- `ArrayBlockingQueue` is a **bounded** blocking queue backed by an **array**.
    - Its capacity must be specified at creation time and cannot grow.
    - It provides predictable performance because it uses a fixed-size array.
    - Threads trying to add elements will **block** if the queue is full, and threads trying to remove elements will block if the queue is empty.
    
    Example:
    
    ```java
    BlockingQueue<Integer> queue =newArrayBlockingQueue<>(5);
    ```
    
- `LinkedBlockingQueue` is a blocking queue backed by a **linked list**.
    - It can be **bounded or unbounded** (if no capacity is specified).
    - It typically offers **higher throughput** than `ArrayBlockingQueue` in concurrent applications.
    - Performance is less predictable because it dynamically allocates nodes.
    
    Example:
    
    ```java
    BlockingQueue<Integer> queue =newLinkedBlockingQueue<>();
    ```
    
- Both `ArrayBlockingQueue` and `LinkedBlockingQueue` support blocking methods like `put()` and `take()`, which automatically wait for space or elements to become available.
- The key difference between them lies in **internal data structure and capacity handling**:
    - `ArrayBlockingQueue` → fixed-size, array-based, predictable memory usage.
    - `LinkedBlockingQueue` → linked-list-based, flexible size, higher concurrency throughput.
- These implementations are part of `java.util.concurrent` and are designed to be **thread-safe without external synchronization**.

**Can you briefly explain about the Map Interface?**

1. The `Map` interface is **not part of the Collection hierarchy**. It does not extend `Collection`, so it does not inherit methods like `add()`, `remove()`, or `iterator()`.
2. A `Map` stores data as **key–value pairs**, where each key is a **unique identifier** and each key maps to exactly one value.
3. Keys in a `Map` **cannot be duplicated**. If you put a value using an existing key, the old value is replaced with the new one.
4. A key–value pair in a `Map` is represented by the nested `Map.Entry<K, V>` interface.
5. The `put(K key, V value)` method is used to add or update a key–value pair in the map.
6. The `get(Object key)` method retrieves the value associated with a given key. If the key does not exist, it returns `null`.
7. The `containsKey(Object key)` and `containsValue(Object value)` methods are used to check for the existence of keys or values in the map.
8. The `remove(Object key)` method removes the key–value pair associated with the given key.
9. The `keySet()` method returns a `Set` of all keys, `values()` returns a collection of all values, and `entrySet()` returns a `Set` of key–value pairs.
10. The `entrySet()` method is the **most efficient way to iterate** over a map because it gives direct access to both keys and values.

**Simplified visual representation of a Map:**

```
Map
--------------------------------
Key        →        Value
--------------------------------
"IN"       →        "India"
"US"       →        "America"
"FR"       →        "France"
```

**Important Map interface methods (verified):**

```java
interfaceMap<K, V> {
intsize();
booleanisEmpty();
booleancontainsKey(Object key);
booleancontainsValue(Object value);
    Vget(Object key);
    Vput(K key, V value);
    Vremove(Object key);
voidputAll(Map<? extends K, ? extends V> m);
voidclear();
    Set<K>keySet();
    Collection<V>values();
    Set<Entry<K, V>>entrySet();

interfaceEntry<K, V> {
        KgetKey();
        VgetValue();
        VsetValue(V value);
    }
}
```

1. `Map` is commonly used for **fast lookups**, configuration data, caching, counting occurrences, and associating identifiers with values.

**What is difference between Map and SortedMap?**

1. `Map` is an interface that stores data as **key–value pairs**, where keys are unique, but it does **not guarantee any ordering** of keys.
2. `SortedMap` extends the `Map` interface, so it supports all `Map` operations, but **adds the guarantee that keys are maintained in sorted order**.
3. In a `Map`, the iteration order of keys depends on the implementation (for example, `HashMap` does not guarantee any order).
4. In a `SortedMap`, keys are sorted either by:
    - their **natural ordering**, or
    - a **Comparator** provided at map creation time.
5. `SortedMap` provides **range-view operations** that are not available in `Map`, such as:
    - `subMap(fromKey, toKey)` – keys within a specific range
    - `headMap(toKey)` – keys less than `toKey`
    - `tailMap(fromKey)` – keys greater than or equal to `fromKey`
6. `SortedMap` also provides methods to access boundary keys:
    - `firstKey()` – smallest key
    - `lastKey()` – largest key
7. A common implementation of `Map` is `HashMap`, while a common implementation of `SortedMap` is `TreeMap`.

**Simple visual difference:**

```
Map (HashMap):
Insert:  4, 5, 3
Store :  5, 3, 4   (order not guaranteed)

SortedMap (TreeMap):
Insert:  4, 5, 3
Store :  3, 4, 5   (always sorted by key)
```

1. Use `Map` when **ordering does not matter** and performance is the main concern; use `SortedMap` when **sorted keys and range-based queries** are required.

**What is a HashMap?**

1. `HashMap` is a concrete implementation of the `Map` interface and stores data as **key–value pairs**.
2. Each **key must be unique**, but values can be duplicated. If the same key is inserted again, the old value is replaced with the new one.
3. `HashMap` does **not maintain any order** of keys or values. The iteration order may change over time and should not be relied upon.
4. Internally, `HashMap` uses a **hashing mechanism** (`hashCode()` and `equals()`) to store and retrieve elements efficiently.
5. `HashMap` allows **one null key** and **multiple null values**.
6. Common operations like `put()`, `get()`, and `remove()` generally run in **constant time O(1)** under normal conditions.
7. `HashMap` is **not synchronized**, so it is not thread-safe unless externally synchronized.

**Example code (correct and verified):**

```java
Map<String, Cricketer> hashmap =newHashMap<String, Cricketer>();

hashmap.put("sachin",newCricketer("Sachin",14000));
hashmap.put("dravid",newCricketer("Dravid",12000));
hashmap.put("ponting",newCricketer("Ponting",11500));
hashmap.put("bradman",newCricketer("Bradman",9996));
```

1. Each entry in the map is stored internally as a `Map.Entry<K, V>`, where the key is used to locate the corresponding value.

**Simple visual representation:**

```
Key        → Value
"sachin"   → Sachin 14000
"dravid"   → Dravid 12000
"ponting"  → Ponting 11500
"bradman"  → Bradman 9996

```

1. `HashMap` is best used when **fast lookup by key** is required and **ordering is not important**.

**What are the different methods in a Hash Map?**

- `put(K key, V value)`
    
    Adds a key–value pair to the map.
    
    If the key already exists, the old value is replaced.
    
- `get(Object key)`
    
    Returns the value associated with the given key.
    
    Returns `null` if the key is not present.
    
- `remove(Object key)`
    
    Removes the mapping for the specified key.
    
- `containsKey(Object key)`
    
    Checks whether the given key exists in the map.
    
- `containsValue(Object value)`
    
    Checks whether the given value exists in the map.
    
- `size()`
    
    Returns the number of key–value pairs.
    
- `isEmpty()`
    
    Checks whether the map is empty.
    
- `keySet()`
    
    Returns a `Set` of all keys.
    
- `values()`
    
    Returns a `Collection` of all values.
    
- `entrySet()`
    
    Returns a `Set` of `Map.Entry<K,V>` (key–value pairs).
    

**Simple visual representation of HashMap:**

```
Key        Value
-------------------------
"sachin"-> Sachin14000
"dravid"-> Dravid12000
"ponting"-> Ponting11800
"bradman"-> Bradman9996
```

- Keys are **unique**
- Values **can be duplicated**
- Order of entries is **not guaranteed**

**What is a TreeMap? How is different from a HashMap?**

- TreeMap implements **Map, SortedMap, NavigableMap**; HashMap implements **Map** only
- TreeMap stores **keys in sorted order** (natural order or via Comparator); HashMap has **no ordering guarantee**
- TreeMap sorting is based on **key comparison**; HashMap ordering depends on **hashing**
- TreeMap operations are generally **slower (O(log n))**; HashMap operations are generally **faster (O(1) average)**
- TreeMap **does not allow null keys** (if using natural ordering); HashMap **allows one null key**
- TreeMap is useful when **sorted traversal, range queries** are needed; HashMap is best for **fast lookup**
- Both allow **duplicate values but unique keys**

Visual representation:

```
HashMap (unordered)
{ ponting=11500, sachin=14000, dravid=12000 }

TreeMap (sortedby key)
{ bradman=9996, dravid=12000, ponting=11500, sachin=14000 }
```

**Can you give an example of implementation of NavigableMap Interface?**

- NavigableMap is an extension of SortedMap that provides navigation methods to find closest matching keys.
- Keys are always stored in **sorted order** (natural order or by a Comparator).
- It supports searching keys **lower than, equal to, or higher than** a given key.
- It also allows retrieving and removing **first and last entries** efficiently.
- TreeMap is the **most common implementation** of NavigableMap.

**Key methods (meaning in simple terms):**

- `lowerKey(k)` → greatest key **less than** k
- `floorKey(k)` → greatest key **less than or equal to** k
- `higherKey(k)` → smallest key **greater than** k
- `ceilingKey(k)` → smallest key **greater than or equal to** k
- `firstKey()` / `lastKey()` → first / last key in sorted order
- `pollFirstEntry()` / `pollLastEntry()` → get and remove first / last entry
- `descendingMap()` → reverse order view of the map

**Example implementation:**

- `TreeMap<K, V>` implements `NavigableMap<K, V>`, `SortedMap<K, V>`, and `Map<K, V>`.

**Simple visual (keys in TreeMap):**

```
Keys inserted:55,25,35,5,45

Sorted order inside TreeMap:
5 ──25 ──35 ──45 ──55

lowerKey(25)   →5
floorKey(25)   →25
higherKey(25)  →35
ceilingKey(25) →25
```

- Best used when you need **sorted keys + fast range and nearest-key queries**.

**What are the static methods present in the Collections class?**

- **binarySearch(List, key)**
    - Searches an element using binary search
    - List **must be sorted**
    - Returns index if found, otherwise negative value
- **binarySearch(List, key, Comparator)**
    - Binary search using a custom Comparator
    - List must be sorted according to the same Comparator
- **reverse(List)**
    - Reverses the order of elements in a List
- **reverseOrder()**
    - Returns a Comparator that sorts elements in **reverse (descending) order**
- **sort(List)**
    - Sorts the list in **natural order** (ascending for numbers, alphabetical for strings)
- **sort(List, Comparator)**
    - Sorts the list using a **custom Comparator**

**Quick visual idea**

```
OriginalList :[1, 2, 3, 4]
reverse()     :[4, 3, 2, 1]

sort()        :[A, B, C]
reverseOrder():[C, B, A]
```

### Advanced Collections

**What is the difference between synchronized and concurrent collections in Java?**

- **Synchronized collections**
    - Implemented using `synchronized` methods/blocks.
    - Only one thread can access the collection at a time.
    - Causes thread contention and reduced performance.
    - Examples: `Hashtable`, `Vector`, `Collections.synchronizedList()`.
- **Concurrent collections**
    - Introduced after Java 5 (`java.util.concurrent` package).
    - Use fine-grained locking or lock-free algorithms.
    - Allow multiple threads to read/write concurrently.
    - Provide much better scalability and performance.
    - Examples: `ConcurrentHashMap`, `CopyOnWriteArrayList`, `BlockingQueue`.
- **Key difference**
    - Synchronized collections use **single lock → low concurrency**.
    - Concurrent collections use **advanced concurrency control → high performance and scalability**.

**Explain about the new concurrent collections in Java?**

Introduced after Java 5 to support better concurrency and performance

- Designed to allow multiple threads to access collections with minimal blocking
- Avoid coarse-grained synchronization used in old synchronized collections
- **Copy-On-Write approach**
    - On modification, a new copy of the collection is created
    - Read operations are very fast and non-blocking
    - Best suited for read-heavy scenarios
    - Example: `CopyOnWriteArrayList`, `CopyOnWriteArraySet`
- **Compare-And-Swap (CAS)**
    - Uses low-level atomic operations
    - Avoids locking by retrying updates when conflicts occur
    - Improves scalability under high contention
    - Example: `ConcurrentHashMap` (internally uses CAS for updates)
- **Locks (fine-grained locking)**
    - Uses multiple locks instead of one global lock
    - Allows higher concurrency by locking only parts of the collection
    - Example: `ConcurrentHashMap` (segment-based / bucket-level locking)
- **Blocking collections**
    - Support thread coordination using waiting mechanisms
    - Threads can wait when collection is empty or full
    - Common in producer–consumer problems
    - Examples: `ArrayBlockingQueue`, `LinkedBlockingQueue`, `PriorityBlockingQueue`

Simple visual overview:

```
OldApproach (Synchronized)
[ One Big Lock ]
   |
Allthreadswait

ConcurrentCollections
[ Lock A ][ Lock B ][ CAS ]
   |         |
Multiplethreadsproceed
```

- Overall benefit: higher throughput, better scalability, safer multithreaded behavior

**Explain about CopyOnWrite concurrent collections approach?**

- `CopyOnWrite` is a concurrent collections approach introduced after Java 5
- All elements are stored in an internal immutable array
- On any modification (add, remove, update), a **new copy of the array** is created
- Read operations are **not synchronized**
- Write operations are **synchronized**
- Readers always see a consistent snapshot of the collection
- Iteration is safe without explicit synchronization
- Very efficient when **reads are far more frequent than writes**
- Write operations are expensive due to array copying
- Memory usage is higher because of multiple array copies
- Common use case: **Observer / Listener patterns**
- Implementations: `CopyOnWriteArrayList`, `CopyOnWriteArraySet`

**Visual representation:**

```
InitialArray(Immutable)
[A|B|C]

Thread1(Read)--->usessamearray
Thread2(Read)--->usessamearray

Thread3(Write:addD)
|
v
NewArrayCreated
[A|B|C|D]

Oldreaderscontinueusing:
[A|B|C]
```

- Readers are never blocked
- Writers do not affect ongoing read operations

**What is CompareAndSwap approach?**

- Compare and Swap (CAS) is a concurrency technique introduced in Java 5 to reduce locking.
- Instead of synchronizing an entire method or block, it works on a single shared variable.
- The current value of the variable is first read and cached.
- A new value is computed based on the cached value.
- Before updating, the cached value is compared with the actual current value.
- If both values are the same, the update succeeds.
- If another thread has changed the value meanwhile, the update fails.
- On failure, the operation can be retried or skipped based on logic.
- This approach avoids blocking and improves performance under high concurrency.
- It is widely used in non-blocking concurrent collections.
- Example: `ConcurrentLinkedQueue` uses the Compare and Swap approach.

<br><br>
**What is a Lock? How is it different from using synchronized approach?**

<br><br>
**When does a Java Collection throw `UnsupportedOperationException`?**

- All Java collections implement the **Collection** interface.
- Hence, they must provide implementations for all Collection methods.
- However, **not all collections support all operations**.
- Some collections are **restricted or optimized** for specific use cases.
- When an **unsupported operation** (like `add`, `remove`, or `clear`) is invoked on such a collection, it throws  
  **`UnsupportedOperationException`**.


#### Common Scenario: Fixed-size Collections

- `Arrays.asList()` returns a **fixed-size List** backed by an array.
- This list **allows modification of elements** (`set()`),  
  but **does NOT allow structural modifications** (`add()` / `remove()`).

#### Example:
```java
List<String> list = Arrays.asList(new String[]{"ac", "bddefe"});
list.remove(); // throws UnsupportedOperationException
```

<br><br>
**Difference between Fail-Fast and Fail-Safe Iterators**
#### Fail-Fast Iterators
- Throw `ConcurrentModificationException` if the underlying collection is structurally modified during iteration.
- Work directly on the original collection.
- Use an internal modification count (`modCount`) to detect concurrent changes.
- Default behavior of most non-concurrent collections.
- Faster but not thread-safe.

#### Examples:
`ArrayList`, `HashMap`, `HashSet`

```java
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class FailFastExample {
    public static void main(String[] args) {
        Map<String, String> map = new HashMap<>();
        map.put("key1", "value1");
        map.put("key2", "value2");

        Iterator<String> iterator = map.keySet().iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
            map.put("key3", "value3"); // ConcurrentModificationException
        }
    }
}
```

#### Fail-Safe Iterators
- Do not throw ConcurrentModificationException even if the collection is modified during iteration.
- Iterate over a copied snapshot of the collection.
- Changes made during iteration are not reflected in the iterator.
- Thread-safe but slower and memory intensive.
- Suitable when read operations vastly outnumber write operations.

#### Examples:
`ConcurrentHashMap`, `CopyOnWriteArrayList`

```java
import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;

public class FailSafeExample {
    public static void main(String[] args) {
        ConcurrentHashMap<String, String> map = new ConcurrentHashMap<>();
        map.put("key1", "value1");
        map.put("key2", "value2");

        Iterator<String> iterator = map.keySet().iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
            map.put("key3", "value3"); // Allowed
        }
    }
}
```


<br><br>
**Atomic Operations in Java**

- An atomic operation is one that happens completely in a single step.
- It cannot be interrupted or observed in an intermediate state.
- Either the entire operation succeeds, or it does not happen at all.
- No partial changes are visible to other threads.

#### Why atomic operations are needed
- In a multi-threaded environment, multiple threads may access and modify the same variable.
- Even simple operations like `i++` are **not thread-safe**.
- `i++` actually consists of multiple steps:
  1. Read the current value of `i`
  2. Increment the value by 1
  3. Write the updated value back to `i`
- If multiple threads execute these steps concurrently, the final result can be incorrect due to race conditions.

#### How Java supports atomic operations
- Java provides classes in the `java.util.concurrent.atomic` package.
- These classes use low-level, hardware-supported atomic instructions.
- They ensure operations happen as a single, indivisible unit.

#### Example: AtomicInteger
- `AtomicInteger` provides atomic operations on an integer value.
- Common atomic methods include:
  - `incrementAndGet()`
  - `decrementAndGet()`
  - `getAndIncrement()`
  - `compareAndSet()`

```java
import java.util.concurrent.atomic.AtomicInteger;

AtomicInteger count = new AtomicInteger(0);

// Atomic increment
count.incrementAndGet();
```
- The incrementAndGet() method guarantees:
    - No other thread can interfere during the increment
    - The value is updated safely and consistently


<br><br>
### Generics
**What are Generics?**
- Generics allow classes, interfaces, and methods to work with different data types.
- They enable writing **type-safe** and **reusable** code.
- The type is specified as a parameter when the class or method is used.


<br><br>
**Why do we need Generics?**

- Without generics, classes are tied to a single data type, reducing reusability.
- To support another data type, we must create a new class, leading to code duplication.
- Generics allow writing **one class or method** that works with **multiple data types**.
- They improve **type safety**, **flexibility**, and **maintainability**.

#### Problem without Generics

```java
class MyList {
    private List<String> values;

    void add(String value) {
        values.add(value);
    }

    void remove(String value) {
        values.remove(value);
    }
}
```
- This class can store only String values.
- This results in duplicated logic for different data types.

#### Usage:
```java
MyList myList = new MyList();
myList.add("Value 1");
myList.add("Value 2");
```

#### Solution using Generics
```java
class MyListGeneric<T> {
    private List<T> values;

    void add(T value) {
        values.add(value);
    }

    void remove(T value) {
        values.remove(value);
    }

    T get(int index) {
        return values.get(index);
    }
}
```
- `T` is a type parameter.
- The class can now work with any reference type.

#### Usage Example
```java
MyListGeneric<String> myListString = new MyListGeneric<>();
myListString.add("Value 1");
myListString.add("Value 2");

MyListGeneric<Integer> myListInteger = new MyListGeneric<>();
myListInteger.add(1);
myListInteger.add(2);
```

<br><br>
**How do you declare a Generic Class?**
- A generic class is declared by adding **type parameters** within angle brackets (`<>`) after the class name.
- The type parameter represents a placeholder for a data type.
- The most commonly used type parameter name is `T` (Type), but **any valid identifier** can be used.

#### Syntax

```java
class MyListGeneric<T> {
    // class body
}
```

#### Using a different type parameter name
```java
class MyListGeneric<E> {
    // E stands for Element
}
```
```java
class Pair<K, V> {
    // K stands for Key, V stands for Value
}
```


<br><br>
**What are the restrictions in using generic type that is declared in a class declaration?**
- A generic type parameter declared at the **class level** (for example, `T`) can be used anywhere a type is used inside the class:
  - As a **method parameter**
  - As a **method return type**
  - As a **member variable type**
  - Inside method implementations

#### Example

```java
class MyListGeneric<T> {
    private List<T> values;

    void add(T value) {
        values.add(value);
    }

    T get(int index) {
        return values.get(index);
    }
}
```

#### Restrictions
- Generic type parameters cannot be used in static context
    - Static methods and static variables belong to the class, not to a specific type instance.
    ```java
        static T value;        // ❌ Compile-time error
        static T getValue() {  // ❌ Compile-time error
            return value;
        }
    ```
    - Generic type parameters cannot be instantiated
    ```java
    T obj = new T(); // ❌ Not allowed
    ```
    - Generic type parameters cannot be used with primitive types
        - Wrapper classes must be used instead (`Integer`, `Double`, etc.)
    ```java
    MyListGeneric<int> list; // ❌ Not allowed
    ```
    - Generic type parameters cannot be used in instanceof checks
    ```java
    if (obj instanceof T) { } // ❌ Not allowed
    ```
    - Arrays of generic types cannot be directly created
    ```java
    T[] array = new T[10]; // ❌ Not allowed
    ```


<br><br>
**How can we restrict Generics to a super class of particular class?**

In Java generics, you can restrict a type to be a **superclass** of a given class using the `super` keyword. This is commonly used in **wildcards** (`? super Type`) rather than in class declarations.

#### Using `? super Type` with Methods or Collections

```java
class MyListSuper<T> {
    private List<T> values;

    void add(T value) {
        values.add(value);
    }

    void remove(T value) {
        values.remove(value);
    }

    T get(int index) {
        return values.get(index);
    }
}
```
#### Example: Restricting to a Superclass of `Number`
```java
List<? super Number> list = new ArrayList<Object>();
list.add(10);         // Integer is allowed (subclass of Number)
list.add(3.14);       // Double is allowed (subclass of Number)

// Number itself is allowed
list.add(new Integer(5));

// Retrieving elements is limited to Object type
Object obj = list.get(0);
// Number n = list.get(0); // ❌ Compiler Error
```


<br><br>
**Can you give an example of a Generic Method?**

In Java, a method can declare its own generic type, independent of any class-level generics. This allows flexibility in the types the method can handle.

#### Generic Method Syntax

```java
// Generic method declaration
static <X extends Number> X doSomething(X number) {
    X result = number;
    // perform operations on result
    return result;
}
```
#### Calling the Generic Method
```java
Integer i = 5;
Integer k = doSomething(i);   // Works with Integer

Double d = 3.14;
Double e = doSomething(d);    // Works with Double
```

<br><br>
### Functional Programming - Lamdba Expressions and Streams

**What is functional programming?**

Functional programming is a programming paradigm that focuses on writing programs using **pure functions** and **immutable data**.

- Computation is treated as the evaluation of mathematical functions
- Functions do not change state or modify data (no side effects)
- Output of a function depends only on its input
- Emphasizes immutability instead of changing variables
- Leads to code that is easier to understand, test, and parallelize

In Java, functional programming concepts were introduced mainly in **Java 8** using:
- Lambda expressions
- Functional interfaces
- Stream API

<br><br>
**Can you give an example of functional programming?**

- The first example uses the **traditional imperative approach**.
- The second example uses **functional programming concepts** introduced in Java 8.

#### Imperative (Usual) Approach
```java
@Test
public void sumOfOddNumbers_Usual() {
    List<Integer> numbers = Arrays.asList(1, 3, 4, 6, 2, 7);
    int sum = 0;
    for (int number : numbers)
        if (number % 2 != 0)
            sum += number;
    assertEquals(11, sum);
}
```
- Uses a mutable variable sum
- Explicit loop (for)
- Explicit conditional logic (if)
- State (sum) changes during iteration
- This style is not functional, as it relies on mutation and control flow statements

#### Functional Programming Approach
```java
@Test
public void sumOfOddNumbers_FunctionalProgrammingExample() {
    List<Integer> numbers = Arrays.asList(1, 3, 4, 6, 2, 7);
    int sum = numbers.stream()
                     .filter(Test123::isOdd)
                     .reduce(0, Integer::sum);
    assertEquals(11, sum);
}

static boolean isOdd(int number) {
    return number % 2 != 0;
}
```
- Uses Stream API
- No explicit loops or mutable state
- `filter` selects only odd numbers
- `reduce` combines values into a single result
- Uses method references (`Test123::isOdd`, `Integer::sum`)
- Each operation returns a new result without modifying existing data



<br><br>
**What is a Stream?**
- A **Stream** in Java represents a **sequence of elements** that can be processed in a functional style.
  - It is **not a data structure**
  - It does **not store data**
  - It works on data provided by a **source** such as a `List`, `Set`, `Array`, or I/O channel.
- In the given example, the stream is created from a `List` using:
  ```java
  numbers.stream();
  ```
- A stream processes data through a pipeline of operations, which consists of:
  - Source
  - Intermediate operations
  - Terminal operation
- **Intermediate operations:**
  - Transform a stream into another stream
  - Are lazy (they do not execute immediately)
  - Can be chained together
  - Example:
    ```java
    filter(Test123::isOdd)
    ```  
- **Terminal operations:**
  - Trigger the execution of the stream pipeline
  - Produce a result or a side effect
  - Once a terminal operation is called, the stream cannot be reused
  - Example:
    ```java
    reduce(0, Integer::sum)
    ```
- Execution happens only when a terminal operation is invoked, not when intermediate operations are defined.
- Streams can be sequential or parallel:
  ```java
    numbers.stream();        // sequential
    numbers.parallelStream(); // parallel
  ```


<br><br>
**Explain about streams with an example?**
```java
Arrays.stream(new String[] { "Ram", "Robert", "Rahim" })
      .filter(s -> s.startsWith("Ro"))
      .map(String::toLowerCase)
      .sorted()
      .forEach(System.out::println);
```
#### Step-by-step explanation of the example:
- Step 1: Source
  - An array of strings is converted into a stream using `Arrays.stream(...)`
- Step 2: Intermediate operation - filter
  - Filters elements starting with `"Ro"`
  - Uses a lambda expression
  - Returns a new stream
- Step 3: Intermediate operation – map
  - Converts each string to lowercase
  - Uses a method reference
  - Returns a new stream
- Step 4: Intermediate operation – sorted
  - Sorts the elements alphabetically
  - Returns a new stream
- Step 5: Terminal operation – forEach
  - Consumes the stream
  - Prints each element
  - Ends the stream pipeline


<br><br>
**What are Intermediate Operations in Streams?**
- **Intermediate Operations** are operations that:
  - Take a stream as input
  - Return **another stream** as output
  - Are **lazy** (they do not execute immediately)
  - Are executed only when a **terminal operation** is invoked

- Because they return a stream, multiple intermediate operations can be **chained together** to form a pipeline.

- Common examples of intermediate operations:
  - `map`
  - `filter`
  - `distinct`
  - `sorted`
  - `limit`
  - `skip`

- **Distinct Example**
  ```java
  @Test
    public void streamExample_Distinct() {
        List<Integer> numbers = Arrays.asList(1, 1, 2, 6, 2, 3);
        numbers.stream()
            .distinct()
            .forEach(System.out::print);
        // Output: 1263
    }
  ```
  - `distinct()` is an intermediate operation
  - Removes duplicate elements
  - It is a stateful operation because it needs to remember previously seen elements
  - Returns a new stream without duplicates

- **Sorted Example**
  ```java
  @Test
    public void streamExample_Sorted() {
        List<Integer> numbers = Arrays.asList(1, 1, 2, 6, 2, 3);
        numbers.stream()
            .sorted()
            .forEach(System.out::print);
        // Output: 112236
    }
  ```
  - `sorted()` is an intermediate operation.
  - Sorts elements using natural ordering (or a comparator)
  - It is a stateful operation because elements must be compared with each other
  - Returns a new sorted stream

- **Filter Example**
  ```java
  @Test
    public void streamExample_Filter() {
        List<Integer> numbers = Arrays.asList(1, 3, 4, 6, 2, 7);
        numbers.stream()
            .filter(Test123::isOdd)
            .forEach(System.out::print);
        // Output: 137
    }
  ```
  - `filter()` is an intermediate operation
  - Selects elements that match a condition
  - It is a stateless operation because each element is processed independently
  - Returns a new stream containing only matching elements


<br><br>
**What are Terminal Operations in Streams?**
- **Terminal Operations**:
  - End the stream pipeline
  - Trigger execution of all intermediate operations
  - Either **produce a result** or **cause a side effect**
  - Consume the stream (the stream cannot be reused afterward)

- **reduce() – Produces a result**
  ```java
  @Test
    public void sumOfOddNumbers_FunctionalProgramming() {
        List<Integer> numbers = Arrays.asList(1, 3, 4, 6, 2, 7);
        int sum = numbers.stream()
                        .filter(Test123::isOdd)
                        .reduce(0, Integer::sum);
        assertEquals(11, sum);
    }
  ```
  - `reduce()` combines all elements into a single value
  - `0` is the identity value
  - `Integer::sum`  defines how elements are combined
  - Commonly used for sum, min, max, or custom aggregation

- **forEach() – Creates a side effect**
  ```java
  @Test
    public void streamExample_Filter() {
        List<Integer> numbers = Arrays.asList(1, 3, 4, 6, 2, 7);
        numbers.stream()
            .filter(Test123::isOdd)
            .forEach(System.out::print);
        // Output: 137
    }
  ```
  - `forEach()` performs an action on each element
  - Used for:
    - Printing
    - Logging
    - Writing to files or databases
  - Does not return a result

- **collect() – Transforms stream into a collection**
  ```java
  @Test
    public void streamExample_Collect() {
        List<Integer> numbers = Arrays.asList(1, 3, 4, 6, 2, 7);
        List<Integer> oddNumbers = numbers.stream()
                                        .filter(Test123::isOdd)
                                        .collect(Collectors.toList());
        System.out.println(oddNumbers);
        // Output: [1, 3, 7]
    }
  ```
  - `collect()` gathers stream elements into a data structure
  - Uses a Collector (e.g., `toList`, `toSet`, `groupingBy`)
  - Very flexible and commonly used terminal operation



<br><br>
**What are Method References?**
- **Method References** are a shorthand notation of **Lambda Expressions**.
- They allow referring to a method **without invoking it**.
- Introduced in **Java 8**, mainly used with **Streams** and **Functional Interfaces**.
- They improve **readability** and **conciseness** of code.

### General Syntax

```java
ClassName::methodName
```
or
```java
objectReference::methodName
```


<br><br>
**What are Lambda Expressions?**
- A **Lambda Expression** is an **anonymous function**.
- It is a method **without a name**, **access modifier**, or **return type declaration**.
- Lambda expressions are mainly used to provide **implementation of functional interfaces**.
- Introduced in **Java 8** to support **functional programming**.

#### General Syntax

```java
(parameters) -> { executed code }
```

#### Example
```java
(a, b) -> a + b
() -> System.out.println("Hello")
```

#### Lambda Expression in Streams Example
```java
numbers.stream()
       .filter(n -> n % 2 != 0)
       .forEach(System.out::println);
```

<br><br>
**Can you give an example of Lambda Expression?**

A **Lambda Expression** provides a concise way to represent an anonymous function.

#### Example Code

```java
@Test
public void lambdaExpression_simpleExample() {
    List<Integer> numbers = Arrays.asList(1, 3, 4, 6, 2, 7);

    numbers.stream()
           .filter(Test123::isOdd)
           .forEach(number -> System.out.print(number));
    // Output: 137
}
```
- `number -> System.out.print(number)` is a lambda expression
- `number` is the parameter
- `System.out.print(number)` is the executed code
- This lambda is passed to the `forEach` method, which applies it to every element in the stream


<br><br>
**Can you explain the relationship between Lambda Expression and Functional Interfaces?**
- A **Lambda Expression** in Java is a concise way to implement a **Functional Interface**.
- A **Functional Interface** is an interface that has **exactly one abstract method**.

Example from Java API:

```java
@FunctionalInterface
public interface Consumer<T> {
    void accept(T t);
}
```

- Takes one input
- Returns no result
- Usually performs side effects (like printing, logging, etc.)


#### How Lambda Expressions Fit In

Consider this lambda expression:
```java
number -> System.out.print(number)
```
This lambda:
- Takes one input (number)
- Returns nothing
- Performs a side effect (printing)

#### Connection Through forEach

The forEach method is defined as:
```java
void forEach(Consumer<? super T> action)
```
```java
numbers.stream().forEach(number -> System.out.print(number));
```
Java does the following:
- Sees that forEach expects a `Consumer<T>`
- Matches the lambda expression to `Consumer.accept(T)`
- Automatically creates an implementation of `Consumer<T>`


<br><br>
**What is a Predicate?**

A **Predicate** is a functional interface in Java that represents a condition (boolean-valued function).

```java
@FunctionalInterface
public interface Predicate<T> {
    boolean test(T t);
}
```
- Takes one argument
- Returns true or false
- Commonly used for filtering conditions

#### Predicate Example with Streams
```java
@Test
public void lambdaExpression_predicate() {
    List<Integer> numbers = Arrays.asList(1, 3, 4, 6, 2, 7);
    numbers.stream()
           .filter((number) -> (number % 2 != 0))
           .forEach(number -> System.out.print(number));
    // 137
}
```

#### Explanation
```java
(number) -> (number % 2 != 0)
```
- This is a Predicate
- Input: `number`
- Logic: checks if the number is odd
- Output: `true` or `false`



<br><br>
**What is the functional interface - Function?**

`Function` is a **functional interface** in Java that represents a function which:

- Takes **one input**
- Produces **one result**

```java
@FunctionalInterface
public interface Function<T, R> {
    R apply(T t);
}
```
#### Type Parameters
- `T` → Type of input
- `R` → Type of result (output)

#### Example Using `Function`
```java
@Test
public void functionExample() {
    List<String> names = Arrays.asList("Ram", "Robert", "Rahim");

    names.stream()
         .map(String::length)
         .forEach(System.out::println);
}
```
- `String::length` is a `Function<String, Integer>`
- Input: `String`
- Output: `Integer` (length of the string)


<br><br>
**What is a Consumer?**

`Consumer` is a **functional interface** in Java that represents an operation which:

- Takes **one input**
- Returns **no result**
- Is typically used for **side effects** (printing, logging, storing data, etc.)

```java
@FunctionalInterface
public interface Consumer<T> {
    void accept(T t);
}
```

#### Example
```java
@Test
public void consumerExample() {
    List<Integer> numbers = Arrays.asList(1, 3, 4, 6, 2, 7);

    numbers.stream()
           .forEach(number -> System.out.print(number));
}
```


<br><br>
**What is a Supplier?**

`Supplier` is a **functional interface** in Java that:

- Takes **no input**
- Returns a **result**
- Is used to **supply or generate values**

```java
@FunctionalInterface
public interface Supplier<T> {
    T get();
}
```
#### Example
```java
Supplier<String> supplier = () -> "Hello Supplier";
System.out.println(supplier.get());
```

#### Real-World Use Cases
- Generating default values
- Lazy initialization
- Factory methods
- Supplying random values or timestamps


<br><br>
**Can you give examples of functional interfaces with multiple arguments?**

Java provides **functional interfaces that accept more than one argument**. These are commonly used
with **lambda expressions** and **method references**.


#### 1. BiFunction<T, U, R>

Takes **two input arguments** and returns a result.

```java
@FunctionalInterface
public interface BiFunction<T, U, R> {
    R apply(T t, U u);
}
```
#### Example
```java
BiFunction<Integer, Integer, Integer> add = (a, b) -> a + b;
System.out.println(add.apply(10, 20)); // 30
```

#### 2. BiPredicate<T, U>
Takes two arguments and returns a boolean.
```java
@FunctionalInterface
public interface BiPredicate<T, U> {
    boolean test(T t, U u);
}
```
#### Example
```java
BiPredicate<Integer, Integer> isGreater =
        (a, b) -> a > b;

System.out.println(isGreater.test(10, 5)); // true
```

#### 3. BiConsumer<T, U>
Takes two arguments and returns nothing (side effects only).
```java
@FunctionalInterface
public interface BiConsumer<T, U> {
    void accept(T t, U u);
}
```
#### Example
```java
BiConsumer<String, Integer> print =
        (name, age) -> System.out.println(name + " : " + age);

print.accept("Sachin", 45);
```


<br><br>
### Multi-threading
---

**What is the need for Threads in Java?**

- **Parallel Execution**: Threads allow a program to perform multiple tasks at the same time. This is essential for improving performance in applications that perform time-consuming operations.
- **Reduced Execution Time**: When tasks are independent, running them in parallel reduces the total time taken. Instead of waiting for one task to finish before starting the next, multiple tasks progress simultaneously.
- **Better Resource Utilization**: Modern CPUs have multiple cores. Threads allow Java to utilize these cores effectively, ensuring that the CPU is not sitting idle while waiting for I/O operations (like downloading data).
- **Asynchronous Processing**: Threads help in keeping an application responsive. For example, a GUI can remain interactive while a background thread handles a heavy file download.

#### Understanding with an Example (Cricket Statistics Application)
If we run the following tasks sequentially, the total time is 160 minutes ($60 + 60 + 15 + 25$).
- **Sequential Execution (Without Threads)**:
  ```java
  // Total Time: 160 Minutes
    downloadAndStoreBattingStatistics(); // Starts at 0, ends at 60
    downloadAndStoreBowlingStatistics(); // Starts at 60, ends at 120
    downloadAndStoreFieldingStatistics();// Starts at 120, ends at 135
    mergeAndAnalyze();                   // Starts at 135, ends at 160
  ```
- **Parallel Execution (With Threads)**:
By running the first three steps in separate threads, they run at the same time. The longest task takes 60 minutes. Therefore, the total time reduces to 85 minutes ($60 + 25$).
  ```java
  // Conceptual Thread Usage
    Thread t1 = new Thread(() -> downloadAndStoreBattingStatistics());
    Thread t2 = new Thread(() -> downloadAndStoreBowlingStatistics());
    Thread t3 = new Thread(() -> downloadAndStoreFieldingStatistics());

    t1.start();
    t2.start();
    t3.start();

    // Wait for all three to finish
    t1.join(); t2.join(); t3.join();

    mergeAndAnalyze(); // Runs only after parallel tasks finish
  ```


<br><br>
**How do you create a thread?**

In Java, threads are the smallest units of execution within a process. There are two ways of creating threads:

1. **Extending the Thread Class**
   - Create a class that extends `java.lang.Thread`.
   - Override the `run()` method with the code you want to execute.
   - Start the thread using the `start()` method.
   ```java
    class BattingStatisticsThread extends Thread {
        public void run() {
            System.out.println("Processing batting stats...");
        }
    }

    // Execution
    BattingStatisticsThread thread = new BattingStatisticsThread();
    thread.start();
   ```

2. **Implementing the Runnable Interface**
    - Create a class that implements `java.lang.Runnable`.
    - Pass an instance of this class to a Thread object constructor.
    - This is generally preferred because Java doesn't support multiple inheritance; by implementing an interface, your class can still extend another class.
    ```java
    class BowlingStatisticsThread implements Runnable {
        public void run() {
            System.out.println("Processing bowling stats...");
        }
    }

    // Execution
    Thread thread = new Thread(new BowlingStatisticsThread());
    thread.start();
    ```

<br><br>
**How do you create thread by extending Thread class?**
- Subclassing: Your class must extend java.lang.Thread. This gives your class access to thread management methods like interrupt(), join(), and setPriority().
- Overriding run(): You must override the public void run() method. This method acts as the entry point for the new thread.
- Initialization: You instantiate your subclass object.
- The start() Method: To execute the thread, you call start(). This tells the JVM to create a new call stack and then execute the run() method.
```java
class BattingStatisticsThread extends Thread {
    // This method contains the code to be executed by the thread
    @Override
    public void run() {
        for (int i = 1; i <= 1000; i++) {
            System.out.println("Running Batting Statistics Thread: " + i);
        }
    }
}

// In your Main class:
BattingStatisticsThread thread1 = new BattingStatisticsThread();
thread1.start(); // Correct: Starts a new thread
```


<br><br>
**How do you create a thread by implementing Runnable interface?**

Implementing the Runnable interface is the most common and flexible way to create threads in Java. This approach separates the Task (what the code does) from the Runner (the thread object that executes it).
- **Implement Runnable**: Create a class that implements the java.lang.Runnable interface.
- **Define `run()`**: Provide the logic inside the public void run() method.
- **Wrap in Thread**: Since Runnable is just an interface, it doesn't have a start() method. You must pass your Runnable instance into a Thread constructor.
- **Execute**: Call the start() method on the Thread object.

```java
class BowlingStatisticsThread implements Runnable {
    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
            System.out.println("Running Bowling Statistics Thread " + i);
        }
    }
}

// Execution logic
BowlingStatisticsThread task = new BowlingStatisticsThread();
Thread thread = new Thread(task); // Passing the task to the worker
thread.start();
```


<br><br>
**Why Runnable is Preferred over Extending Thread**
- **Multiple Inheritance**: Java classes can only extend one class. By implementing `Runnable`, your class is still free to extend another class (e.g., `BaseStats`).
- **Resource Sharing**: You can pass a single `Runnable` instance to multiple `Thread` objects to share data or state between them.
- **Thread Pooling**: Modern Java development uses ExecutorServices. These frameworks are designed to accept `Runnable` tasks, not `Thread` subclasses.
- **Clean Design**: It follows the Single Responsibility Principle. The `Runnable` class handles the logic, and the `Thread` class handles the concurrency.


<br><br>
**What are the different states of a Thread?**
![alt text](image-1.png)
**new** 

- thread has been created but not started, just an object in memory

**Runnable** 

- thread is ready to run, waiting for the CPU time

**Running**

- when thread executing its code

**Blocked**

- I/O : reading from the file or database
- Lock acquired : if thread want a lock on a resource which is locked by another thread, it has to wait.
- Releases all the MONITOR lock.

**Waiting** 

- thread goes to this state when we call the wait() method, makes it non-runnable
- its goes back to runnable, once we call `notify()` or `notifyAll()` method.
- Releases all the MONITOR lock.

**Timed Waiting**

- Thread waits for specific period of time and comes back to runnable state, after specific conditions met like `sleep()`, `join()`.
- Do not releases any MONITOR locks.

**Terminated**

- Life of thread is completed, it can not be started back again.


<br><br>
**What is priority of a thread? How do you change the priority of a thread?**
In Java, thread priority is a "hint" to the Operating System's thread scheduler about the relative importance of a thread. While it can influence CPU allocation, it does not guarantee a specific order of execution.

#### Core Concepts of Thread Priority
- **The Range**: Priorities are integers ranging from 1 (Lowest) to 10 (Highest).
- **Default Value**: Every new thread inherits the priority of its parent thread. By default, the main thread has a priority of 5.
- **The Scheduler's Role**: When multiple threads are in the RUNNABLE state, the scheduler prefers the thread with the highest priority. If priorities are equal, a Round-Robin or "First-Come-First-Served" approach is typically used.

#### How to Change Priority
You use the `setPriority(int)` method. Java provides three constants to keep code readable:
- `Thread.MIN_PRIORITY` (Value: 1)
- `Thread.NORM_PRIORITY` (Value: 5)
- `Thread.MAX_PRIORITY` (Value: 10)

```java
ThreadExample thread1 = new ThreadExample();

// Setting priority using an integer
thread1.setPriority(8); 

// Setting priority using constants (Recommended)
thread1.setPriority(Thread.MAX_PRIORITY); 

thread1.start();
```

<br><br>
**What is ExecutorService?**
- It is an Interface present in `java.util.concurrent` package.
- It's main task is to manage the thread pool and has many method like `stop()`, `shutdown()`, etc.


<br><br>
**Can you give an example for ExecutorService?**
```java
// Creating a fixed pool of 3 threads
ExecutorService executor = Executors.newFixedThreadPool(3);

// Submitting a Runnable task
executor.execute(() -> {
    System.out.println("Processing stat in: " + Thread.currentThread().getName());
});

// Shutting down the service
executor.shutdown();
```
- **Submission**: The task is sent to the `ThreadPoolExecutor`.
- **Core Pool Check**: If fewer than the "core" number of threads are running, a new thread is created.
- **Queueing**: If the core threads are busy, the task is stored in a Blocking Queue.
- **Max Pool Check**: If the queue is full and threads are below the "maximum" limit, a new thread is created.
- **Rejection**: If the queue is full and max threads are reached, the `RejectedExecutionHandler` is triggered.