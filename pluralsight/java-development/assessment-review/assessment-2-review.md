# Java OOP — Study Guide & Assessment Reference

This guide is designed to help you **study before the assessment** and serve as a **reference during the assessment**. It explains the core object-oriented programming concepts in Java, shows canonical examples, and highlights common pitfalls. It does **not** give you direct answers — you'll still need to think through each question. The goal is that if you understand the material in this guide, you can reason through any question on the assessment.

---

## How to Approach Problems on the Assessment

Before diving into the content, here are some general strategies that will serve you well regardless of the specific question:

### 1. Read the entire code before answering

Many OOP questions involve multiple classes interacting. Read each class definition, then trace how they connect. Don't just look at one method in isolation.

### 2. Identify the concept being tested

Most OOP questions test one of: encapsulation, inheritance, polymorphism, abstraction, or a specific keyword (`this`, `super`, `final`, etc.). Try to recognize the concept first; the answer often becomes obvious once you do.

### 3. Pay attention to access modifiers

`private` vs `public` vs `protected` controls what code can access what. If a field is `private`, code outside the class can't touch it directly — only methods inside the same class can.

### 4. Trace constructor calls carefully

When you create an object with `new`, a constructor runs. If the class extends another class, the parent constructor runs first (implicitly or explicitly through `super(...)`).

### 5. Distinguish overloading from overriding

- **Overloading**: same method name, different parameters, **same class**.
- **Overriding**: same method name, same parameters, **subclass redefines parent's method**.

These two are easy to confuse but very different mechanically.

### 6. For ArrayList questions, remember it's a class, not an array

ArrayList uses **methods** (with parentheses) like `.add()`, `.get()`, `.size()`, `.remove()`. Arrays use **brackets** like `arr[0]` and the **field** `.length` (no parentheses). They are NOT interchangeable.

### 7. For try/catch questions, think about flow

- If no exception is thrown → catch block is skipped.
- If an exception is thrown → execution jumps immediately to the matching catch block.
- The exception type in `catch (ExceptionType e)` must match (or be a parent of) the thrown exception.

### 8. Match method names to their classes

`getDayOfMonth()` is on `LocalDate`. `.size()` is on collections. `.length()` is on String. `.length` (no parentheses) is on arrays. Mixing these up is a common trap.

### 9. Don't overthink "trick" options

If an option has a typo, wrong keyword, or impossible syntax, it's almost always a distractor.

---

## 1. Classes and Objects

### The Blueprint Analogy

A **class** is a blueprint — it describes what something is and what it can do.
An **object** is an actual thing built from that blueprint.

Think of a house plan vs. an actual house:

- The blueprint (class) describes the structure.
- A specific house at 123 Main Street (object) is built from the blueprint.
- You can build many houses (objects) from the same blueprint (class).

### Anatomy of a Class

```java
public class Smartphone {
    // Fields — the data each object holds
    private String brand;
    private double screenSize;

    // Constructor — runs when a new object is created
    public Smartphone(String brand, double screenSize) {
        this.brand = brand;
        this.screenSize = screenSize;
    }

    // Methods — behaviors the object can perform
    public void powerOn() {
        System.out.println(brand + " is starting up.");
    }
}
```

### Creating Objects

Use the `new` keyword to create an instance (an object) of a class:

```java
Smartphone myPhone = new Smartphone("Samsung", 6.5);
Smartphone yourPhone = new Smartphone("Google", 6.2);
```

Each call to `new` creates a **separate object** with its own copy of the fields.

### ⚠️ Watch Out For

- A class is the blueprint; an object is the instance built from it. Don't mix these up.
- You can have many objects from the same class, each with different field values.
- Objects are created with `new`. Without `new`, you don't have an object.

---

## 2. Encapsulation

**Encapsulation** is the principle of hiding an object's internal data and exposing controlled access through methods.

### The Pattern

1. Make fields `private` so outside code can't touch them directly.
2. Provide `public` **getter** methods to read field values.
3. Provide `public` **setter** methods to change field values (with validation if needed).

### Example

```java
public class Employee {
    private String fullName;
    private double salary;

    public Employee(String fullName, double salary) {
        this.fullName = fullName;
        this.salary = salary;
    }

    // Getter for fullName
    public String getFullName() {
        return fullName;
    }

    // Setter for fullName
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    // Getter for salary
    public double getSalary() {
        return salary;
    }

    // Setter for salary — could include validation
    public void setSalary(double salary) {
        if (salary >= 0) {
            this.salary = salary;
        }
    }
}
```

### Why Encapsulate?

- **Control** — you decide how the field can be read or modified.
- **Validation** — setters can reject invalid values.
- **Flexibility** — you can change the internal representation later without breaking outside code.
- **Safety** — outside code can't put the object in an invalid state.

### Accessing a Private Field

```java
Employee emp = new Employee("Sarah Kim", 50000);

// emp.salary = 60000;  // ❌ Compiler error! salary is private
emp.setSalary(60000);   // ✓ Works — going through the setter

// double s = emp.salary;  // ❌ Compiler error
double s = emp.getSalary();  // ✓ Works
```

### ⚠️ Watch Out For

- `private` fields can be accessed **only within the same class**.
- Trying to access a `private` field from outside the class causes a **compiler error**.
- The encapsulation pattern is: `private` fields + `public` getter/setter methods.
- A class with all `public` fields is the **opposite** of encapsulation.

---

## 3. Access Modifiers

Access modifiers control who can see and use a class member (field, method, or constructor).

| Modifier                        | Same class | Same package | Subclass | Anywhere |
| ------------------------------- | :--------: | :----------: | :------: | :------: |
| `private`                       |     ✓      |      ✗       |    ✗     |    ✗     |
| (no modifier / package-private) |     ✓      |      ✓       |    ✗     |    ✗     |
| `protected`                     |     ✓      |      ✓       |    ✓     |    ✗     |
| `public`                        |     ✓      |      ✓       |    ✓     |    ✓     |

### When to Use What

- **`private`** — for fields (the default choice for encapsulation).
- **`public`** — for methods that outside code needs to call (getters, setters, behaviors).
- **`protected`** — for things subclasses need but outside code shouldn't touch.
- **No modifier** — rarely used intentionally; means "accessible within the same package."

### ⚠️ Watch Out For

- The most restrictive option is `private` (same class only).
- The most open option is `public` (anywhere).
- For encapsulation, fields are `private` and methods that need to be called from outside are `public`.

---

## 4. Constructors

A **constructor** is a special method that runs automatically when an object is created with `new`. Its main job is to initialize the object's fields.

### Constructor Rules

1. The constructor's **name must match the class name exactly**.
2. Constructors have **no return type** — not even `void`. Writing `public void ClassName(...)` would make it a regular method, not a constructor.
3. Constructors are not declared `static`.
4. A class can have multiple constructors with different parameter lists (this is called **constructor overloading**).
5. If you don't write any constructor, Java provides a default empty one.

### Correct Constructor Pattern

```java
public class Employee {
    private String fullName;
    private double salary;

    public Employee(String fullName, double salary) {
        this.fullName = fullName;
        this.salary = salary;
    }
}
```

Things to notice:

- No return type before the class name.
- Not `static`.
- Inside the body: `this.field = parameter;` (this assigns the parameter value to the field).

### Common Mistakes to Recognize

```java
// ❌ Has return type "void" — this is a method, not a constructor
public void Employee(String fullName, double salary) { ... }

// ❌ Static — constructors can't be static
public static Employee(String fullName, double salary) { ... }

// ❌ Assignments reversed — this assigns the field's (uninitialized) value to the parameter
public Employee(String fullName, double salary) {
    fullName = this.fullName;        // wrong direction!
    salary = this.salary;            // wrong direction!
}
```

### ⚠️ Watch Out For

- Constructor name must match class name **exactly** (including capitalization).
- **No return type**, not even `void`.
- Not `static`.
- The assignment direction matters: `this.field = parameter;` not `parameter = this.field;`.

---

## 5. The `this` Keyword

`this` refers to the **current object** — the object whose method or constructor is currently running.

### Most Common Use: Disambiguating Fields and Parameters

When a parameter has the same name as a field, `this.` makes it clear which one you mean:

```java
public class Rectangle {
    private double width;
    private double height;

    public Rectangle(double width, double height) {
        this.width = width;     // this.width = the field; width = the parameter
        this.height = height;
    }
}
```

Without `this.`, Java thinks both sides refer to the parameter, and the field never gets assigned.

### Other Uses of `this`

- Calling another constructor in the same class: `this(args);`
- Passing the current object to another method: `someMethod(this);`

For this assessment, focus on the field/parameter disambiguation use.

### ⚠️ Watch Out For

- `this` only exists inside instance methods and constructors (not in `static` methods).
- `this.field = field;` is the standard pattern: left side is the object's field, right side is the parameter.
- If parameter names don't collide with field names, `this.` is optional (but still good practice).

---

## 6. Inheritance

**Inheritance** lets one class (the **child** or **subclass**) reuse and extend another class (the **parent** or **superclass**).

### Syntax: `extends`

```java
public class Vehicle {
    protected String brand;

    public Vehicle(String brand) {
        this.brand = brand;
    }

    public void start() {
        System.out.println(brand + " is starting.");
    }
}

public class Motorcycle extends Vehicle {
    private boolean hasSidecar;

    public Motorcycle(String brand, boolean hasSidecar) {
        super(brand);            // calls the parent constructor
        this.hasSidecar = hasSidecar;
    }
}
```

`Motorcycle` automatically gets:

- The `brand` field (since it's `protected`).
- The `start()` method.

### Key Vocabulary

- **Superclass / Parent class**: the class being extended (`Vehicle` here).
- **Subclass / Child class**: the class that extends another (`Motorcycle` here).
- **`extends`**: the Java keyword that creates the inheritance relationship.

### Direction Matters

```java
public class Motorcycle extends Vehicle { ... }   // ✓ Motorcycle IS-A Vehicle
public class Vehicle extends Motorcycle { ... }   // ❌ Wrong direction!
```

A motorcycle is a kind of vehicle, not the other way around.

### Other Keywords That Are NOT Java

- `inherits` — not a Java keyword (it's used in other languages).
- `implements` — used for **interfaces**, not for class inheritance. Different concept.

### ⚠️ Watch Out For

- Use `extends` to make a subclass.
- The subclass goes on the left of `extends`, the parent goes on the right.
- `implements` is for interfaces, not for extending a class.
- Only one direct parent is allowed: `extends A, B` is **not** valid in Java.

---

## 7. The `super` Keyword

`super` refers to the **parent class**. Two common uses:

### 1. Calling the Parent Constructor

When a subclass constructor needs to initialize fields the parent owns, it calls the parent's constructor with `super(...)`:

```java
public class Vehicle {
    protected String brand;

    public Vehicle(String brand) {
        this.brand = brand;
    }
}

public class Motorcycle extends Vehicle {
    private boolean hasSidecar;

    public Motorcycle(String brand, boolean hasSidecar) {
        super(brand);                  // calls Vehicle(String brand)
        this.hasSidecar = hasSidecar;
    }
}
```

`super(brand)` runs the parent's constructor first, then the rest of the subclass constructor body runs.

### Rules for `super(...)`

- If you call `super(...)`, it must be the **first statement** in the constructor.
- If you don't call it explicitly, Java automatically calls `super()` (the no-argument parent constructor).
- If the parent has no no-argument constructor, you must call `super(...)` explicitly with appropriate arguments.

### 2. Calling a Parent Method

You can call a method from the parent class using `super.methodName()`:

```java
public class Motorcycle extends Vehicle {
    @Override
    public void start() {
        super.start();                                // calls Vehicle's start()
        System.out.println("Engine roaring!");
    }
}
```

### ⚠️ Watch Out For

- `super(...)` calls the **parent constructor** and passes arguments to it.
- `super.method()` calls the parent class's version of the method.
- `super` only works in subclasses — it's not meaningful in a class that doesn't extend anything.

---

## 8. Method Overloading vs. Overriding

These are two different concepts with similar names — make sure you can tell them apart.

### Method Overloading

**Same method name, different parameters, in the same class.**

The compiler picks which version to call based on the arguments you pass.

```java
public class Calculator {
    public int combine(int x, int y) {
        return x + y;
    }

    public int combine(int x, int y, int z) {        // different number of parameters
        return x + y + z;
    }

    public double combine(double x, double y) {       // different parameter types
        return x + y;
    }
}
```

All three methods are called `combine`, but they're distinguished by their parameter lists. This is **overloading**.

### Method Overriding

**Subclass redefines a method from its parent class with the same name AND same parameter list.**

The subclass version replaces the parent's version when called on a subclass object.

```java
public class Shape {
    public void draw() {
        System.out.println("Drawing a shape.");
    }
}

public class Triangle extends Shape {
    @Override
    public void draw() {                    // same name, same parameters
        System.out.println("Drawing a triangle.");
    }
}
```

When you call `draw()` on a `Triangle` object, the `Triangle` version runs, not the `Shape` version.

### Side-by-Side Comparison

|                       | Overloading     | Overriding                         |
| --------------------- | --------------- | ---------------------------------- |
| Where?                | Same class      | Subclass redefines parent's method |
| Method name?          | Same            | Same                               |
| Parameter list?       | **Must differ** | **Must be identical**              |
| Inheritance involved? | No              | Yes                                |
| Annotation?           | None needed     | `@Override` (recommended)          |

### ⚠️ Watch Out For

- Overloading = same class, different parameter lists.
- Overriding = subclass, identical signature.
- If method signatures are identical and the methods are in the same class — that's just two definitions of the same method, which is a compiler error, not overloading.

---

## 9. Polymorphism

**Polymorphism** means "many forms." In Java, it usually refers to the ability of a parent-type reference to point to a child-type object, and the child's version of a method runs.

### Example

```java
public class Bird {
    public void sing() {
        System.out.println("Generic bird sound");
    }
}

public class Parrot extends Bird {
    @Override
    public void sing() {
        System.out.println("Squawk! Hello!");
    }
}

public class Owl extends Bird {
    @Override
    public void sing() {
        System.out.println("Hoot hoot");
    }
}
```

Now watch this:

```java
Bird b1 = new Parrot();      // Bird reference, Parrot object
Bird b2 = new Owl();         // Bird reference, Owl object

b1.sing();    // Prints "Squawk! Hello!"
b2.sing();    // Prints "Hoot hoot"
```

Even though the references are declared as `Bird`, Java looks at the **actual object type** at runtime and calls the correct version of `sing()`. This is polymorphism in action.

### Why It Matters

Polymorphism lets you write code that works with a general type (`Bird`) without knowing the specific subtype. You can put different `Bird` subtypes in the same array, loop over them, and each one behaves correctly:

```java
Bird[] aviary = { new Parrot(), new Owl(), new Parrot() };
for (Bird b : aviary) {
    b.sing();    // Each one sings in its own way
}
```

### How It Connects to Overriding

Overriding is **how** polymorphism happens. Without overriding, all subclasses would just use the parent's method. Overriding lets each subclass have its own behavior, and polymorphism is what makes Java pick the right one at runtime.

### ⚠️ Watch Out For

- Polymorphism = parent reference holding a child object, child's overridden method runs.
- Overriding is the mechanism; polymorphism is the result.
- Don't confuse polymorphism with overloading — overloading is decided at compile time based on argument types, not at runtime based on object types.

---

## 10. The `final` Keyword

The `final` keyword means "this can't be changed." Where you put `final` determines what can't change.

### `final` on a Field

The field can be assigned **once** (typically in the constructor) and then never again.

```java
public class Point {
    private final int x;
    private final int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    // No setter — these can't be changed after construction
}
```

### `final` on a Variable

The variable can be assigned only once:

```java
final int MAX_USERS = 100;
MAX_USERS = 200;    // ❌ Compiler error — can't reassign a final variable
```

### `final` on a Method

The method cannot be overridden by subclasses:

```java
public class Base {
    public final void doSomething() { ... }
}

public class Child extends Base {
    public void doSomething() { ... }    // ❌ Compiler error
}
```

### `final` on a Class

The class cannot be extended:

```java
public final class Utility { ... }

public class MyUtility extends Utility { ... }    // ❌ Compiler error
```

### ⚠️ Watch Out For

- `final` on a field = field is immutable once set in the constructor.
- `final` on a class = class can't be extended (e.g., `String` is final).
- `final` on a method = method can't be overridden.
- These are three different things — context matters.

---

## 11. Getters and Setters

Getters and setters are the conventional way to expose private fields safely.

### Naming Convention

For a field named `xyz`:

- Getter: `getXyz()` (or `isXyz()` for boolean fields).
- Setter: `setXyz(...)`.

### Example

```java
public class Product {
    private String title;
    private double cost;

    public Product(String title, double cost) {
        this.title = title;
        this.cost = cost;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }
}
```

### Using Getters and Setters

```java
Product item = new Product("Headphones", 199.99);

item.setTitle("Wireless Headphones");
System.out.println(item.getTitle());     // Prints "Wireless Headphones"
System.out.println(item.getCost());      // Prints 199.99
```

### Why Bother Instead of Public Fields?

- The setter can validate input (e.g., reject negative prices).
- You can change the internal implementation later without breaking calling code.
- You can make a field read-only by providing a getter but no setter.

### ⚠️ Watch Out For

- Getters return the field's value; setters assign to it.
- Setters typically use `this.field = parameter;` to handle the parameter/field name collision.
- A field with only a getter is effectively read-only from outside.

---

## 12. ArrayList

`ArrayList` is a resizable list — like an array, but you can add and remove elements and it grows as needed.

### Why Use ArrayList Over an Array?

- Arrays have a **fixed size** chosen when they're created.
- ArrayList **grows and shrinks** automatically.
- ArrayList provides convenient methods for adding, removing, and searching.

### Importing

```java
import java.util.ArrayList;
```

### Declaring and Creating

```java
ArrayList<String> cities = new ArrayList<String>();
ArrayList<Integer> numbers = new ArrayList<Integer>();
```

The `<String>` part is the **type parameter** — it says what type of elements the list will hold.

### Common Methods

| Method                 | What it does                     | Example                                  |
| ---------------------- | -------------------------------- | ---------------------------------------- |
| `.add(element)`        | Adds element to the end          | `cities.add("Paris");`                   |
| `.get(index)`          | Returns element at index         | `String c = cities.get(0);`              |
| `.set(index, element)` | Replaces element at index        | `cities.set(0, "Rome");`                 |
| `.remove(index)`       | Removes element at index         | `cities.remove(0);`                      |
| `.size()`              | Returns number of elements       | `int n = cities.size();`                 |
| `.contains(element)`   | Returns true if list contains it | `boolean has = cities.contains("Rome");` |
| `.isEmpty()`           | Returns true if size is 0        | `boolean empty = cities.isEmpty();`      |
| `.clear()`             | Removes all elements             | `cities.clear();`                        |

### Example

```java
ArrayList<String> cities = new ArrayList<String>();
cities.add("Tokyo");      // ["Tokyo"]
cities.add("Madrid");     // ["Tokyo", "Madrid"]
cities.add("Cairo");      // ["Tokyo", "Madrid", "Cairo"]

System.out.println(cities.size());     // 3
System.out.println(cities.get(1));     // "Madrid"

cities.remove(0);                       // removes "Tokyo"
System.out.println(cities.size());     // 2
System.out.println(cities.get(0));     // "Madrid" (everything shifts down)
```

### Important: Removing Shifts Indices

When you remove an element, all elements after it shift down by one index:

```java
ArrayList<String> letters = new ArrayList<String>();
letters.add("X");
letters.add("Y");
letters.add("Z");
// indices:  0    1    2

letters.remove(0);    // Removes "X"
// Now:   "Y"  "Z"
// indices: 0    1
```

### Iterating Over an ArrayList

```java
ArrayList<String> cities = new ArrayList<String>();
cities.add("Tokyo");
cities.add("Madrid");

// Enhanced for loop
for (String city : cities) {
    System.out.println(city);
}

// Standard for loop with index
for (int i = 0; i < cities.size(); i++) {
    System.out.println(i + ": " + cities.get(i));
}
```

### ArrayList vs. Array — Method Names Differ

| Concept     | Array                             | ArrayList                       |
| ----------- | --------------------------------- | ------------------------------- |
| Size        | `.length` (field, no parentheses) | `.size()` (method, parentheses) |
| Get element | `arr[i]`                          | `list.get(i)`                   |
| Set element | `arr[i] = value;`                 | `list.set(i, value);`           |
| Add element | Can't — fixed size                | `list.add(value);`              |

### ⚠️ Watch Out For

- `.size()` for ArrayList; `.length` for arrays; `.length()` for strings. Three different things.
- Use `.get(i)` for ArrayList, not `list[i]` — brackets don't work.
- Use `.add()` not `.insert()` — `.insert()` is not a method.
- After `.remove(i)`, indices of later elements shift down by one.

---

## 13. Exception Handling (try / catch)

When something goes wrong at runtime, Java throws an **exception**. Without handling, the program crashes. With handling, you can catch the problem and recover.

### Basic try/catch Syntax

```java
try {
    // code that might throw an exception
} catch (ExceptionType e) {
    // code that runs if that exception is thrown
}
```

### Example

```java
try {
    String text = "abc";
    int number = Integer.parseInt(text);    // throws NumberFormatException
    System.out.println(number);
} catch (NumberFormatException e) {
    System.out.println("That wasn't a valid number!");
}
```

What happens:

1. `Integer.parseInt("abc")` throws a `NumberFormatException`.
2. Execution immediately jumps to the `catch` block.
3. The `catch` block prints the message.
4. The program continues normally after the catch block.

### What Happens When No Exception is Thrown?

If the `try` block runs without errors, the `catch` block is **skipped entirely**.

```java
try {
    int number = Integer.parseInt("42");     // works fine
    System.out.println(number);              // prints 42
} catch (NumberFormatException e) {
    System.out.println("Not a number");      // skipped
}
// Execution continues here
```

### Common Exceptions to Know

| Exception                        | When it's thrown                                                             |
| -------------------------------- | ---------------------------------------------------------------------------- |
| `NumberFormatException`          | When `Integer.parseInt()` or similar gets a string that isn't a valid number |
| `NullPointerException`           | When you try to use a method or field on a `null` reference                  |
| `ArrayIndexOutOfBoundsException` | When you access an array index that doesn't exist                            |
| `IndexOutOfBoundsException`      | When you access an invalid index in an ArrayList                             |
| `IOException`                    | When file or input/output operations fail                                    |
| `ArithmeticException`            | E.g., dividing an integer by zero                                            |

### Multiple Catch Blocks

You can chain catch blocks to handle different exception types:

```java
try {
    // risky code
} catch (NumberFormatException e) {
    System.out.println("Bad number format");
} catch (NullPointerException e) {
    System.out.println("Something was null");
}
```

### The `finally` Block (Optional)

A `finally` block runs whether an exception happened or not:

```java
try {
    // risky code
} catch (Exception e) {
    // handle exception
} finally {
    // always runs — used for cleanup like closing files
}
```

### ⚠️ Watch Out For

- The keyword is `catch`, not `error` or `handle`.
- If no exception is thrown, the catch block is **skipped**.
- The exception type in the catch must match (or be a parent of) what's thrown.
- `NumberFormatException` is what you catch for `Integer.parseInt()` failures.

---

## 14. Date and Time (LocalDate and LocalDateTime)

Java provides `LocalDate` and `LocalDateTime` classes for working with dates and times.

### Importing

```java
import java.time.LocalDate;
import java.time.LocalDateTime;
```

### Getting the Current Date/Time

```java
LocalDate today = LocalDate.now();              // just the date
LocalDateTime now = LocalDateTime.now();        // date and time
```

### Common LocalDate Methods

| Method             | Returns                           | Example output |
| ------------------ | --------------------------------- | -------------- |
| `.getDayOfMonth()` | Day of the month as an `int`      | `14`           |
| `.getMonthValue()` | Month as an `int` (1–12)          | `7`            |
| `.getMonth()`      | Month as a `Month` enum           | `JULY`         |
| `.getYear()`       | Year as an `int`                  | `2026`         |
| `.getDayOfWeek()`  | Day of week as a `DayOfWeek` enum | `TUESDAY`      |

### Common LocalDateTime Methods

`LocalDateTime` has all the LocalDate methods, plus time-related ones:

| Method         | Returns                   | Example output |
| -------------- | ------------------------- | -------------- |
| `.getHour()`   | Hour (0–23) as an `int`   | `15`           |
| `.getMinute()` | Minute (0–59) as an `int` | `42`           |
| `.getSecond()` | Second (0–59) as an `int` | `30`           |

### Example

```java
LocalDate today = LocalDate.now();
System.out.println(today.getYear());           // e.g., 2026
System.out.println(today.getMonthValue());     // e.g., 7 (number)
System.out.println(today.getMonth());          // e.g., JULY (name)
System.out.println(today.getDayOfMonth());     // e.g., 14

LocalDateTime now = LocalDateTime.now();
System.out.println(now.getHour());             // e.g., 15
System.out.println(now.getMinute());           // e.g., 42
```

### `getMonth()` vs. `getMonthValue()` — Easy to Confuse

- `.getMonth()` returns a `Month` enum like `JANUARY`, `FEBRUARY`, etc. **Not** a number.
- `.getMonthValue()` returns the month as a **number** from 1 to 12.

If you need a number, use `.getMonthValue()`. If you want the name, use `.getMonth()`.

### Hours Are 0–23

`LocalDateTime.getHour()` returns the hour in **24-hour format**, ranging from `0` (midnight) to `23` (11 PM). It's not the 1–12 format with AM/PM.

### ⚠️ Watch Out For

- `getMonth()` returns an enum; `getMonthValue()` returns a number.
- `getDayOfMonth()` returns the day number (1–31), not the day of the week.
- `getDayOfWeek()` returns the weekday name as an enum.
- `getHour()` is 0–23.

---

## Quick Reference Summary

### Class vs. Object

- **Class**: blueprint.
- **Object**: instance created from the blueprint using `new`.

### Encapsulation

- Fields `private`, getters/setters `public`.
- Outside code can't access `private` fields directly.

### Access Modifiers (most to least restrictive)

`private` → no modifier → `protected` → `public`

### Constructor Rules

- Name matches class name exactly.
- No return type (not even `void`).
- Not `static`.
- Pattern inside: `this.field = parameter;`

### `this` vs `super`

- `this` = current object (used for field/parameter disambiguation).
- `super` = parent class (used to call parent constructor or method).

### Inheritance

- Use `extends ParentClass`.
- Child gets parent's accessible fields and methods.
- `super(args)` calls parent's constructor.

### Overloading vs Overriding

- **Overloading**: same name, different parameters, **same class**.
- **Overriding**: same name, same parameters, **subclass redefining parent's method**.

### Polymorphism

- Parent reference points to child object.
- Child's overridden method runs at runtime.

### `final`

- On field: can only be assigned once.
- On method: can't be overridden.
- On class: can't be extended.

### ArrayList

- `.add(element)` — append
- `.get(index)` — retrieve
- `.set(index, value)` — replace
- `.remove(index)` — remove (later indices shift down)
- `.size()` — number of elements (NOT `.length` and NOT `.length()`)

### Exception Handling

- `try { } catch (ExceptionType e) { }`
- No exception → catch skipped.
- `Integer.parseInt()` failure → `NumberFormatException`.

### LocalDate / LocalDateTime

- `.now()` — current date/time
- `.getDayOfMonth()`, `.getMonthValue()`, `.getYear()`, `.getHour()`
- `.getMonth()` returns enum name; `.getMonthValue()` returns number.

---

## Final Study Tips

1. **Understand class vs. object.** A class is a recipe; an object is a baked cake from that recipe.
2. **Memorize the encapsulation pattern.** `private` fields + `public` getters/setters.
3. **Constructors: name matches class, no return type, not static.**
4. **`this.field = parameter;` is the standard constructor body.**
5. **Direction of `extends`:** child `extends` parent.
6. **`super(args)` calls the parent constructor;** it must be the first statement.
7. **Overloading vs overriding** — drill this until you can spot the difference instantly.
8. **Polymorphism = parent reference, child object, overridden method runs.**
9. **`final` on a field** locks it after construction.
10. **ArrayList uses `.size()`**, not `.length` or `.length()`.
11. **`.get(i)` retrieves; `.add()` appends; `.remove(i)` deletes.**
12. **Try/catch:** if no exception, catch is skipped.
13. **`NumberFormatException`** is for failed numeric parsing.
14. **`getMonth()`** is the enum; **`getMonthValue()`** is the number.

Good luck! Remember — this guide is a reference, not a substitute for understanding. The assessment tests whether you can **apply** these concepts, so read each question carefully, identify which OOP concept it's about, and think about what's actually happening in the code.
