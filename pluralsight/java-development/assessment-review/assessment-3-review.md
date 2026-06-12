# SQL, JDBC & Spring Boot — Study Guide & Assessment Reference

This guide is designed to help you **study before the assessment** and serve as a **reference during the assessment**. It explains the core concepts behind SQL queries, JDBC database access, and Spring Boot REST APIs with JPA. It does **not** give you direct answers — you'll still need to think through each question. The goal is that if you understand the material in this guide, you can reason through any question on the assessment.

---

## How to Approach Problems on the Assessment

Before diving into the content, here are some general strategies that will serve you well regardless of the specific question:

### 1. Read the entire query or code before answering

Many SQL questions involve multiple clauses (`SELECT`, `WHERE`, `GROUP BY`, etc.). Read the full statement top to bottom and identify what each clause does before picking an answer.

### 2. For SQL, identify which type of statement it is

The four most common are:

- **`SELECT`** — read data
- **`INSERT`** — add new rows
- **`UPDATE`** — change existing rows
- **`DELETE`** — remove rows

Once you know which one you're dealing with, the rest of the syntax follows specific patterns.

### 3. Watch for typos and made-up functions in distractors

SQL has specific keywords. `SUM(*)` is invalid (use `COUNT(*)`). `ROWS(*)` is not a real function. `startsWith()` is a Java method, not SQL. These are common distractors.

### 4. For Spring Boot, identify the annotation's role

Each annotation has one specific purpose:

- `@RestController` — marks a class as a REST API controller.
- `@GetMapping`, `@PostMapping`, `@PutMapping`, `@DeleteMapping` — map HTTP methods to controller methods.
- `@RequestMapping` — sets a base URL for the controller.
- `@PathVariable` — extracts a value from the URL path.
- `@Entity` — marks a class as a JPA entity (a database table).
- `@Id` — marks a field as the primary key.

### 5. For HTTP status codes, think about what happened

- **200** OK — request succeeded normally.
- **201** Created — a new resource was created.
- **404** Not Found — the requested resource doesn't exist.
- **500** Internal Server Error — something went wrong on the server.

### 6. For JDBC/Spring, know the difference between Statement and PreparedStatement

- **Statement** — runs raw SQL strings. Risky if you concatenate user input.
- **PreparedStatement** — uses `?` placeholders that separate SQL from data. Safe against injection.

### 7. Match repository methods to their purpose

- `findAll()` — get all records.
- `findById(id)` — get one record by ID.
- `save(entity)` — insert or update.
- `deleteById(id)` — delete by ID.

These method names are standardized by Spring Data JPA — they're not optional names you can change.

### 8. Don't overthink "trick" options

If an option uses a method name that sounds plausible but isn't the standard one (`insert()`, `add()`, `persist()`, `remove()`, `drop()`), it's almost certainly a distractor. Stick with the standard JPA methods.

---

## 1. SQL Basics — SELECT and WHERE

`SELECT` is the SQL statement used to **read** data from a database.

### Basic SELECT Syntax

```sql
SELECT column1, column2, ...
FROM table_name;
```

### Examples

```sql
-- Return the title and author of every book
SELECT title, author
FROM books;

-- Return every column for every book
SELECT *
FROM books;
```

The `*` is a wildcard meaning "all columns."

### Filtering with WHERE

`WHERE` filters which rows are returned:

```sql
SELECT title, published_year
FROM books
WHERE published_year > 2000;
```

This returns the title and published year for books published after 2000.

### Common Comparison Operators

| Operator                  | Meaning                     |
| ------------------------- | --------------------------- |
| `=`                       | Equal to                    |
| `<>` or `!=`              | Not equal to                |
| `<`                       | Less than                   |
| `<=`                      | Less than or equal          |
| `>`                       | Greater than                |
| `>=`                      | Greater than or equal       |
| `BETWEEN x AND y`         | In a range (inclusive)      |
| `IN (x, y, z)`            | Matches any value in a list |
| `IS NULL` / `IS NOT NULL` | Checks for null             |

### Examples

```sql
SELECT title FROM books WHERE pages >= 500;
SELECT title FROM books WHERE author = 'Tolkien';
SELECT title FROM books WHERE published_year BETWEEN 2010 AND 2020;
SELECT title FROM books WHERE genre IN ('Fiction', 'Mystery', 'Sci-Fi');
```

### Combining Conditions

Use `AND` and `OR`:

```sql
SELECT title FROM books
WHERE published_year > 2000 AND pages < 300;

SELECT title FROM books
WHERE author = 'Tolkien' OR author = 'Lewis';
```

### ⚠️ Watch Out For

- `SELECT *` returns **all columns**; `SELECT specific_columns` returns only what you ask for.
- String values in conditions are wrapped in **single quotes**: `'Tolkien'`, not `"Tolkien"`.
- `WHERE` filters rows; without it, all rows match.

---

## 2. Pattern Matching with LIKE

The `LIKE` operator is used to match a pattern in text, instead of an exact value.

### Wildcards

| Wildcard | Meaning                             |
| -------- | ----------------------------------- |
| `%`      | Matches **zero or more** characters |
| `_`      | Matches **exactly one** character   |

### Examples

```sql
-- Titles that start with "The"
SELECT title FROM books WHERE title LIKE 'The%';

-- Titles that end with "Storm"
SELECT title FROM books WHERE title LIKE '%Storm';

-- Titles that contain "Magic" anywhere
SELECT title FROM books WHERE title LIKE '%Magic%';

-- Three-letter words starting with "C"
SELECT title FROM books WHERE title LIKE 'C__';
```

### Common Mistakes to Recognize

```sql
-- ❌ '*' is not a SQL wildcard for LIKE
WHERE title LIKE 'The*'

-- ❌ '.startsWith()' is Java, not SQL
WHERE title.startsWith('The')

-- ❌ This works but probably isn't what you want — it captures a range
WHERE title BETWEEN 'T' AND 'U'
```

### ⚠️ Watch Out For

- `%` matches zero or more characters; `_` matches exactly one.
- The wildcard for "starts with X" is `'X%'`, not `'X*'`.
- `LIKE` is meant for **text pattern matching** — for exact values, use `=`.

---

## 3. Aggregate Functions

Aggregate functions return a **single value** computed from a set of rows.

### The Main Aggregates

| Function        | What it does                       |
| --------------- | ---------------------------------- |
| `COUNT(*)`      | Counts the number of rows          |
| `COUNT(column)` | Counts non-null values in a column |
| `SUM(column)`   | Adds up numeric values             |
| `AVG(column)`   | Calculates the average             |
| `MIN(column)`   | Returns the smallest value         |
| `MAX(column)`   | Returns the largest value          |

### Examples

```sql
-- How many books are in the table?
SELECT COUNT(*) FROM books;

-- Total pages across all books
SELECT SUM(pages) FROM books;

-- Average page count
SELECT AVG(pages) FROM books;

-- Longest and shortest book
SELECT MAX(pages), MIN(pages) FROM books;
```

### Important: COUNT vs. SUM

These are **not interchangeable**:

- `COUNT(*)` counts **rows** — how many rows are there?
- `SUM(column)` adds up the **numeric values** in a column.

**Common mistake:** `SUM(*)` is **not valid SQL**. The `*` works with `COUNT`, not `SUM`. To count rows, always use `COUNT(*)`.

### Other Fake Functions to Watch For

- `ROWS(*)` — not a real SQL function.
- `LENGTH(*)` — `LENGTH` is real but for string lengths, not row counts.
- `TOTAL(*)` — not a standard SQL function.

### ⚠️ Watch Out For

- **`COUNT(*)`** = count rows.
- **`SUM(*)`** does **not** exist. Use `COUNT(*)` to count.
- Aggregates return a single value unless combined with `GROUP BY`.

---

## 4. GROUP BY

`GROUP BY` groups rows that share the same value in a column, so you can apply aggregate functions per group.

### Basic Pattern

```sql
SELECT column_to_group_by, AGGREGATE(other_column)
FROM table_name
GROUP BY column_to_group_by;
```

### Example

Suppose we have a `books` table with a `genre` column. To count books per genre:

```sql
SELECT genre, COUNT(*)
FROM books
GROUP BY genre;
```

This returns one row per genre, showing the count of books in each.

### GROUP BY with JOIN

When you need to group across two related tables:

```sql
SELECT g.genre_name, COUNT(*)
FROM genres g
JOIN books b ON g.genre_id = b.genre_id
GROUP BY g.genre_name;
```

This counts how many books exist in each genre.

### HAVING — Filtering Groups

`HAVING` filters **groups** (after grouping), while `WHERE` filters **rows** (before grouping):

```sql
SELECT genre, COUNT(*)
FROM books
GROUP BY genre
HAVING COUNT(*) > 10;
```

This returns only genres that have more than 10 books.

### ⚠️ Watch Out For

- Use `WHERE` to filter rows; use `HAVING` to filter groups (after aggregation).
- Columns in `SELECT` that aren't inside an aggregate function must usually appear in `GROUP BY`.
- `JOIN` requires an `ON` clause specifying how the tables relate.

---

## 5. JOINs

A `JOIN` combines rows from two tables based on a related column.

### Basic JOIN Syntax

```sql
SELECT columns
FROM table1
JOIN table2 ON table1.column = table2.column;
```

### Example

Suppose `books` has a `genre_id` column that matches `genres.genre_id`:

```sql
SELECT books.title, genres.genre_name
FROM books
JOIN genres ON books.genre_id = genres.genre_id;
```

For each book, this returns the title alongside its genre name.

### Types of JOINs

| JOIN Type                     | Returns                                                   |
| ----------------------------- | --------------------------------------------------------- |
| `INNER JOIN` (or just `JOIN`) | Only rows that match in both tables                       |
| `LEFT JOIN`                   | All rows from the left table, plus matches from the right |
| `RIGHT JOIN`                  | All rows from the right table, plus matches from the left |
| `FULL JOIN`                   | All rows from both tables                                 |

### ⚠️ Watch Out For

- A JOIN needs an `ON` clause; without it, you get a cartesian product (every combination).
- Specify column ownership when names overlap: `books.genre_id`, not just `genre_id`.

---

## 6. INSERT INTO

`INSERT INTO` adds a new row to a table.

### Syntax

```sql
INSERT INTO table_name (column1, column2, ...)
VALUES (value1, value2, ...);
```

### Example

```sql
INSERT INTO books (title, author, pages)
VALUES ('The Hobbit', 'Tolkien', 310);
```

This adds a new row with the specified values.

### Inserting Multiple Rows

```sql
INSERT INTO books (title, author, pages)
VALUES
  ('Book One', 'Author A', 200),
  ('Book Two', 'Author B', 350);
```

### Common Wrong Keywords

- `ADD TO` — not SQL.
- `CREATE IN` — not SQL.
- `APPEND TO` — not SQL.

The standard keyword pair is **`INSERT INTO`**.

### ⚠️ Watch Out For

- The keyword pair is `INSERT INTO`, followed by the table name, optional column list, and `VALUES`.
- String values use **single quotes**.
- The number of values must match the number of columns listed.

---

## 7. UPDATE ... SET ... WHERE

`UPDATE` modifies existing rows.

### Syntax

```sql
UPDATE table_name
SET column1 = new_value1, column2 = new_value2
WHERE condition;
```

### Example

```sql
UPDATE books
SET pages = 320
WHERE title = 'The Hobbit';
```

This changes the `pages` value to 320 for the row where the title matches.

### Updating Multiple Columns

```sql
UPDATE books
SET pages = 320, author = 'J.R.R. Tolkien'
WHERE book_id = 7;
```

### ⚠️ DANGER: Omitting WHERE

```sql
UPDATE books
SET pages = 0;     -- ❌ DANGEROUS — updates EVERY row!
```

Without `WHERE`, the UPDATE applies to every row in the table. Always include a `WHERE` clause unless you really mean to update all rows.

### Common Wrong Keywords

- `CHANGE` — used for renaming columns in some dialects, not for updating values.
- `LET` — not SQL.
- `ASSIGN` — not SQL.

The keyword is **`SET`**.

### ⚠️ Watch Out For

- Pattern: `UPDATE table SET column = value WHERE condition;`
- The keyword between table and column is **`SET`**.
- Always include `WHERE` to limit the update.

---

## 8. DELETE FROM ... WHERE

`DELETE` removes rows from a table.

### Syntax

```sql
DELETE FROM table_name
WHERE condition;
```

### Example

```sql
DELETE FROM books
WHERE published_year < 1900;
```

This removes all books published before 1900.

### ⚠️ DANGER: Omitting WHERE

```sql
DELETE FROM books;    -- ❌ DELETES ALL ROWS!
```

Without `WHERE`, every row in the table is deleted. The data is gone — usually permanently.

### ⚠️ Watch Out For

- `DELETE FROM` permanently removes data.
- Always include `WHERE` unless you intend to wipe the table.
- The condition in `WHERE` works the same as in `SELECT` — same operators, same patterns.

---

## 9. SQL Injection and PreparedStatement

### What is SQL Injection?

SQL injection happens when user input is mixed directly into a SQL query string, allowing a malicious user to manipulate the query.

### Vulnerable Code

```java
String login = userTypedSomething;
String sql = "SELECT * FROM accounts WHERE login = '" + login + "'";
```

If a malicious user types: `anything' OR '1'='1`

The resulting query becomes:

```sql
SELECT * FROM accounts WHERE login = 'anything' OR '1'='1'
```

`'1'='1'` is always true, so this returns **every row** — a security disaster. Worse attacks can drop tables, modify data, or expose sensitive information.

### The Fix: PreparedStatement

`PreparedStatement` uses **placeholders** (`?`) that separate the SQL structure from the data values:

```java
String sql = "SELECT * FROM accounts WHERE login = ?";
PreparedStatement stmt = conn.prepareStatement(sql);
stmt.setString(1, userTypedSomething);
ResultSet rs = stmt.executeQuery();
```

The database treats the user input as **data**, not as SQL code. Even if the user types something malicious, it's interpreted literally as a string value, not as SQL syntax.

### Why It's Safer

- The SQL structure is fixed at preparation time.
- User input fills in placeholders as **values only**.
- No matter what the user types, it can't change the structure of the query.

### Other Benefits of PreparedStatement

- **Performance**: the database can cache the query plan.
- **Type safety**: you call `setString()`, `setInt()`, `setDouble()`, etc., for each parameter.
- **Cleaner code**: no string concatenation needed.

### Setting Parameters

```java
PreparedStatement stmt = conn.prepareStatement(
    "SELECT * FROM books WHERE author = ? AND pages > ?"
);
stmt.setString(1, "Tolkien");    // first ? — index is 1, not 0!
stmt.setInt(2, 300);             // second ?
ResultSet rs = stmt.executeQuery();
```

**Note:** parameter indexes start at **1**, not 0.

### ⚠️ Watch Out For

- Never concatenate user input directly into SQL strings.
- `PreparedStatement` uses `?` placeholders to separate SQL from data.
- The primary reason to use `PreparedStatement` over `Statement` for user input is **security** (preventing SQL injection).
- Parameter indexes in `setString(i, ...)`, `setInt(i, ...)`, etc., start at **1**.

---

## 10. JDBC Connection Strings

A **connection string** (or JDBC URL) tells your Java program where the database is and what driver to use.

### Format

```
jdbc:<database-type>://<host>:<port>/<database-name>
```

### Examples

```
jdbc:mysql://localhost:3306/library
jdbc:postgresql://localhost:5432/library
jdbc:mysql://192.168.1.50:3306/cinema_db
```

### Breaking It Down

- `jdbc:` — always starts with this; it's the protocol prefix.
- `mysql` — the database type (also `postgresql`, `mariadb`, `oracle`, `sqlserver`, etc.).
- `localhost` — the host (your own machine); could be an IP or domain name.
- `3306` — the port (MySQL's default; PostgreSQL is 5432).
- `library` — the name of the database to connect to.

### Common Wrong Formats

- `jdbc://localhost:3306/library` — ❌ missing the database type.
- `mysql://localhost:3306/library` — ❌ missing `jdbc:` prefix.
- `library://localhost:3306/dbms=mysql` — ❌ wrong structure.

The correct shape is: **`jdbc:<dbtype>://<host>:<port>/<dbname>`**

### Connecting in Java

```java
String url = "jdbc:mysql://localhost:3306/library";
String dbUser = "root";
String dbPassword = "secretPassword";

Connection conn = DriverManager.getConnection(url, dbUser, dbPassword);
```

### ⚠️ Watch Out For

- Always starts with `jdbc:`.
- The database type comes right after (`mysql`, `postgresql`, etc.).
- Default MySQL port is 3306; PostgreSQL is 5432.
- The database name comes after the `/` at the end.

---

## 11. JDBC PreparedStatement Usage

Here's the full pattern for using a PreparedStatement to query data:

### Full Example

```java
String url = "jdbc:mysql://localhost:3306/library";
String query = "SELECT title, author FROM books WHERE pages > ?";

try (Connection conn = DriverManager.getConnection(url, user, pass);
     PreparedStatement stmt = conn.prepareStatement(query)) {

    stmt.setInt(1, 250);                  // set the ? parameter
    ResultSet rs = stmt.executeQuery();   // run the query

    while (rs.next()) {
        String title = rs.getString("title");
        String author = rs.getString("author");
        System.out.println(title + " by " + author);
    }

} catch (SQLException e) {
    e.printStackTrace();
}
```

### Key Steps

1. Build the SQL string with `?` placeholders.
2. Call `conn.prepareStatement(query)` to create the statement.
3. Use `setXxx(index, value)` to bind values to placeholders.
4. Call `executeQuery()` for `SELECT` (returns a `ResultSet`).
5. Call `executeUpdate()` for `INSERT`, `UPDATE`, `DELETE` (returns an `int` count).
6. Iterate over the `ResultSet` using `rs.next()` in a `while` loop.

### Reading from a ResultSet

```java
while (rs.next()) {
    int id = rs.getInt("book_id");
    String title = rs.getString("title");
    int pages = rs.getInt("pages");
}
```

### executeQuery vs. executeUpdate

| Method            | Use For                | Returns               |
| ----------------- | ---------------------- | --------------------- |
| `executeQuery()`  | SELECT                 | `ResultSet`           |
| `executeUpdate()` | INSERT, UPDATE, DELETE | `int` (rows affected) |

### ⚠️ Watch Out For

- Parameter indexes start at **1**.
- Use `executeQuery()` for SELECT; `executeUpdate()` for everything else.
- `ResultSet.next()` advances to the next row and returns `false` when done.
- Use `getString(columnName)`, `getInt(columnName)`, etc., to read column values.

---

## 12. Spring Boot — @RestController

Spring Boot is a framework for building REST APIs in Java. The basic structure of a REST controller looks like this:

### Anatomy of a Controller

```java
@RestController
@RequestMapping("/books")
public class BookController {

    @GetMapping
    public List<Book> getAllBooks() { ... }

    @GetMapping("/{id}")
    public Book getBook(@PathVariable int id) { ... }

    @PostMapping
    public Book createBook(@RequestBody Book newBook) { ... }
}
```

### What Each Annotation Does

| Annotation                          | Purpose                                                                  |
| ----------------------------------- | ------------------------------------------------------------------------ |
| `@RestController`                   | Marks a class as a REST controller; methods return data (not HTML pages) |
| `@RequestMapping("/path")`          | Sets a base URL for all methods in the controller                        |
| `@GetMapping`, `@PostMapping`, etc. | Maps a method to a specific HTTP method and (optional) path              |
| `@PathVariable`                     | Extracts a value from the URL path                                       |
| `@RequestBody`                      | Extracts the request body (JSON, usually) and converts it to an object   |

### How URLs Are Built

The full URL for a method is the **base path** (from `@RequestMapping` on the class) + the **method path** (from `@GetMapping`, etc.).

```java
@RestController
@RequestMapping("/movies")
public class MovieController {

    @GetMapping("/{id}")          // URL: /movies/{id}
    public Movie getMovie(@PathVariable int id) { ... }

    @GetMapping("/popular")        // URL: /movies/popular
    public List<Movie> getPopular() { ... }
}
```

### @RestController vs. @Controller

- `@RestController` returns **data** (typically JSON) directly to the client. Use this for APIs.
- `@Controller` returns **view names** for HTML rendering (used in traditional web apps).

For REST APIs, always use `@RestController`.

### ⚠️ Watch Out For

- `@RestController` marks REST API controllers; it returns data, not HTML.
- The full URL = `@RequestMapping` base + `@GetMapping`/`@PostMapping`/etc. path.
- `@PathVariable` reads from the URL; `@RequestBody` reads from the JSON body.

---

## 13. HTTP Methods and REST Endpoints

REST APIs use HTTP methods to indicate **what operation** is being performed.

### The Main HTTP Methods

| Method   | Annotation       | Purpose                        | Example                    |
| -------- | ---------------- | ------------------------------ | -------------------------- |
| `GET`    | `@GetMapping`    | Read data                      | Fetch a book by ID         |
| `POST`   | `@PostMapping`   | Create new data                | Add a new book             |
| `PUT`    | `@PutMapping`    | Update (replace) existing data | Replace a book's full info |
| `PATCH`  | `@PatchMapping`  | Update partial data            | Change just one field      |
| `DELETE` | `@DeleteMapping` | Remove data                    | Delete a book by ID        |

### Mapping Operations to Annotations

| You want to...       | Use...                    |
| -------------------- | ------------------------- |
| Fetch all items      | `@GetMapping`             |
| Fetch one item by ID | `@GetMapping("/{id}")`    |
| Create a new item    | `@PostMapping`            |
| Update an item       | `@PutMapping("/{id}")`    |
| Delete an item       | `@DeleteMapping("/{id}")` |

### Example Controller

```java
@RestController
@RequestMapping("/movies")
public class MovieController {

    @GetMapping
    public List<Movie> getAllMovies() { ... }

    @GetMapping("/{id}")
    public Movie getMovie(@PathVariable int id) { ... }

    @PostMapping
    public Movie createMovie(@RequestBody Movie m) { ... }

    @PutMapping("/{id}")
    public Movie updateMovie(@PathVariable int id, @RequestBody Movie m) { ... }

    @DeleteMapping("/{id}")
    public void deleteMovie(@PathVariable int id) { ... }
}
```

### ⚠️ Watch Out For

- `@PostMapping` for **creating**, `@PutMapping` for **updating/replacing**, `@DeleteMapping` for **deleting**, `@GetMapping` for **reading**.
- Each operation has a specific HTTP method — don't mix them up.

---

## 14. HTTP Status Codes

When your API responds, it includes a numeric status code that tells the client what happened.

### Status Code Ranges

| Range | Category     | Meaning                        |
| ----- | ------------ | ------------------------------ |
| 2xx   | Success      | The request worked             |
| 3xx   | Redirection  | The resource is elsewhere      |
| 4xx   | Client error | The client did something wrong |
| 5xx   | Server error | The server failed              |

### The Codes You Need to Know

| Code    | Name                  | When to use                                                  |
| ------- | --------------------- | ------------------------------------------------------------ |
| **200** | OK                    | Standard success — typically for GET requests                |
| **201** | Created               | A new resource was successfully created — typically for POST |
| **204** | No Content            | Success, but no data to return — often for DELETE            |
| **400** | Bad Request           | The client sent malformed input                              |
| **401** | Unauthorized          | Authentication required                                      |
| **403** | Forbidden             | Authenticated but not allowed                                |
| **404** | Not Found             | The requested resource doesn't exist                         |
| **500** | Internal Server Error | The server crashed or has a bug                              |

### Common Pairings

- **GET succeeds** → `200 OK`
- **POST creates** → `201 Created`
- **Resource doesn't exist** → `404 Not Found`
- **Something broke on the server** → `500 Internal Server Error`

### ⚠️ Watch Out For

- **200** = OK (general success).
- **201** = Created (specifically for creating new resources).
- **404** = Not Found (when the requested resource doesn't exist).
- **500** = Internal Server Error (something failed on the server side).

---

## 15. ResponseEntity

`ResponseEntity` is a Spring class that lets you control **both** the response body and the HTTP status code returned to the client.

### Returning an Object Directly

```java
@GetMapping("/{id}")
public Movie getMovie(@PathVariable int id) {
    return movieRepo.findById(id).orElse(null);
}
```

This works, but you can only return the object — the status code is always 200 (or whatever Spring picks by default).

### Returning a ResponseEntity

```java
@GetMapping("/{id}")
public ResponseEntity<Movie> getMovie(@PathVariable int id) {
    Movie movie = movieRepo.findById(id).orElse(null);
    if (movie == null) {
        return ResponseEntity.notFound().build();     // 404
    }
    return ResponseEntity.ok(movie);                   // 200 with the movie
}
```

Now you can:

- Return `200 OK` with the movie when found.
- Return `404 Not Found` when it isn't.
- Return any other status code as needed.

### Common ResponseEntity Methods

| Method                                                | Status | Use Case          |
| ----------------------------------------------------- | ------ | ----------------- |
| `ResponseEntity.ok(body)`                             | 200    | Success with body |
| `ResponseEntity.status(HttpStatus.CREATED).body(obj)` | 201    | Created           |
| `ResponseEntity.notFound().build()`                   | 404    | Not found         |
| `ResponseEntity.badRequest().body(error)`             | 400    | Bad request       |
| `ResponseEntity.noContent().build()`                  | 204    | Success, no body  |

### Why Use ResponseEntity?

- Control the HTTP status code explicitly.
- Set custom headers when needed.
- Make APIs more expressive about what happened.

### ⚠️ Watch Out For

- The benefit of `ResponseEntity` is control over **both the body and the status code**.
- Returning an object directly gives you only the body (status code is default).
- A method that declares `ResponseEntity<X>` as its return type **must return a `ResponseEntity`** — missing the return statement is a compiler error.

---

## 16. JPA — @Entity and @Id

JPA (Java Persistence API) lets you map Java classes to database tables. Spring Boot uses Hibernate as the default JPA provider.

### Marking a Class as an Entity

```java
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

@Entity
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int movieId;

    private String title;
    private String director;
    private int runtimeMinutes;

    // Default constructor required by JPA
    public Movie() { }

    // Constructor, getters, setters...
}
```

### Required Pieces

1. **`@Entity`** — marks the class as a JPA entity (a table). Without this, JPA won't manage the class.
2. **`@Id`** — marks a field as the primary key. Every entity needs exactly one.
3. **`@GeneratedValue`** — tells JPA to auto-generate values for the ID (typical for auto-increment IDs).
4. **A default (no-argument) constructor** — JPA requires this so it can create instances internally.

### Without @Entity, Nothing Happens

A class with `@Id` but no `@Entity` is just a regular Java class with a meaningless annotation. JPA won't manage it as a database table.

### Other Useful Annotations

| Annotation                       | Purpose                                               |
| -------------------------------- | ----------------------------------------------------- |
| `@Table(name = "...")`           | Specifies the table name (defaults to the class name) |
| `@Column(name = "...")`          | Specifies the column name for a field                 |
| `@GeneratedValue`                | Auto-generates the ID value                           |
| `@OneToMany`, `@ManyToOne`, etc. | Defines relationships between entities                |

### ⚠️ Watch Out For

- A class needs `@Entity` to be recognized as a JPA entity.
- Every entity needs exactly one field marked with `@Id`.
- JPA requires a default (no-argument) constructor.
- `@Entity` goes on the **class**; `@Id` goes on the **field** (the primary key).

---

## 17. JpaRepository Methods

Spring Data JPA provides a repository interface that gives you common database operations without writing any SQL.

### Defining a Repository

```java
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie, Integer> {
    // No method bodies needed — Spring implements them automatically
}
```

The generic types are:

- `Movie` — the entity class.
- `Integer` — the type of the entity's primary key.

### Standard Methods You Get for Free

| Method           | Purpose                                         |
| ---------------- | ----------------------------------------------- |
| `findAll()`      | Returns a `List` of all entities                |
| `findById(id)`   | Returns an `Optional<Entity>` for the given ID  |
| `save(entity)`   | Inserts a new entity OR updates an existing one |
| `deleteById(id)` | Deletes the entity with the given ID            |
| `delete(entity)` | Deletes the given entity object                 |
| `existsById(id)` | Returns true if an entity with that ID exists   |
| `count()`        | Returns the total number of entities            |

### These Are NOT JPA Methods

These names look plausible but are **not** part of JpaRepository:

| Wrong Name        | Correct Name     |
| ----------------- | ---------------- |
| `getAll()`        | `findAll()`      |
| `selectAll()`     | `findAll()`      |
| `fetchAll()`      | `findAll()`      |
| `insert(entity)`  | `save(entity)`   |
| `add(entity)`     | `save(entity)`   |
| `persist(entity)` | `save(entity)`   |
| `remove(id)`      | `deleteById(id)` |
| `destroy(id)`     | `deleteById(id)` |
| `drop(id)`        | `deleteById(id)` |

### Using a Repository

```java
@Autowired
private MovieRepository movieRepo;

// Get all
List<Movie> all = movieRepo.findAll();

// Get one by ID — returns Optional
Optional<Movie> maybeMovie = movieRepo.findById(5);
Movie movie = maybeMovie.orElse(null);

// Save (insert if new, update if exists)
Movie newMovie = new Movie("Inception", "Nolan", 148);
movieRepo.save(newMovie);

// Delete
movieRepo.deleteById(5);
```

### About save() — Insert AND Update

`save()` is used for both creating new entities and updating existing ones:

- If the entity has no ID (or an ID that doesn't exist in the database), it's **inserted**.
- If the entity has an ID that already exists, the existing record is **updated**.

This is why there's no separate `insert()` method — `save()` handles both cases.

### About findById() — Returns Optional

`findById()` returns an `Optional` because the ID might not match any row:

```java
Optional<Movie> result = movieRepo.findById(99);

if (result.isPresent()) {
    Movie m = result.get();
    // use m
} else {
    // not found
}

// Or, more concisely:
Movie m = result.orElse(null);
```

### ⚠️ Watch Out For

- `findAll()` for everything, `findById()` for one, `save()` for insert/update, `deleteById()` for delete.
- Method names are **standardized** — don't use `getAll()`, `insert()`, `add()`, `remove()`, etc.
- `findById()` returns an `Optional`, not the entity directly.
- `save()` handles both inserting and updating.

---

## Quick Reference Summary

### SQL Statement Skeletons

```sql
-- Read
SELECT columns FROM table WHERE condition;

-- Add
INSERT INTO table (col1, col2) VALUES (val1, val2);

-- Modify
UPDATE table SET col = value WHERE condition;

-- Remove
DELETE FROM table WHERE condition;
```

### Aggregate Functions

- `COUNT(*)` — count rows. **`SUM(*)` is invalid.**
- `SUM(column)` — add numeric values.
- `AVG(column)`, `MIN(column)`, `MAX(column)` — average, minimum, maximum.

### LIKE Wildcards

- `%` — zero or more characters.
- `_` — exactly one character.
- Starts with X: `'X%'`. Ends with X: `'%X'`. Contains X: `'%X%'`.

### JDBC Connection String

`jdbc:<dbtype>://<host>:<port>/<dbname>`
Example: `jdbc:mysql://localhost:3306/library`

### PreparedStatement

- Use `?` placeholders.
- Set values with `setString(1, ...)`, `setInt(1, ...)`, etc.
- Indexes start at **1**.
- Use `executeQuery()` for SELECT, `executeUpdate()` for INSERT/UPDATE/DELETE.

### Spring Boot Controller Annotations

- `@RestController` — marks a REST controller class.
- `@RequestMapping("/path")` — base URL.
- `@GetMapping`, `@PostMapping`, `@PutMapping`, `@DeleteMapping` — map HTTP methods.
- `@PathVariable` — read from URL path.
- `@RequestBody` — read from request body.

### HTTP Methods → REST Operations

- `GET` → read
- `POST` → create
- `PUT` → update (full replace)
- `DELETE` → remove

### HTTP Status Codes

- **200** OK
- **201** Created
- **400** Bad Request
- **404** Not Found
- **500** Internal Server Error

### JPA Entity Requirements

- Class needs `@Entity`.
- One field needs `@Id`.
- Needs a no-argument constructor.

### JpaRepository Methods

- `findAll()`, `findById(id)`, `save(entity)`, `deleteById(id)`.
- These names are **standard** — `getAll`, `insert`, `add`, `remove`, etc. are wrong.

---

## Final Study Tips

1. **Know your SQL statement types** — `SELECT`, `INSERT INTO`, `UPDATE ... SET`, `DELETE FROM`.
2. **`COUNT(*)`** counts rows; **`SUM(*)`** does not exist.
3. **`LIKE`** uses `%` (any number of chars) and `_` (one char).
4. **`WHERE`** filters rows; **`HAVING`** filters groups after aggregation.
5. **Never concatenate** user input into SQL strings — use `PreparedStatement` with `?`.
6. **JDBC URL** starts with `jdbc:<dbtype>://...`
7. **PreparedStatement parameter indexes start at 1.**
8. **`@RestController`** for REST APIs; **`@Controller`** for HTML views.
9. **HTTP methods**: GET (read), POST (create), PUT (update), DELETE (remove).
10. **Status codes**: 200 OK, 201 Created, 404 Not Found, 500 Server Error.
11. **`ResponseEntity`** lets you set both response body and status code.
12. **JPA entity** requires `@Entity` on the class and `@Id` on the primary key field.
13. **Standard JPA methods**: `findAll`, `findById`, `save`, `deleteById` — don't make up other names.
14. **`save()`** handles both insert and update.
15. **A method declaring `ResponseEntity<X>` as return type must actually return one** — missing return is a compiler error.

Good luck! Remember — this guide is a reference, not a substitute for understanding. The assessment tests whether you can **apply** these concepts, so read each question carefully, identify which topic it's about (SQL, JDBC, Spring Boot, JPA), and think about what's actually happening in the code.
