# Workbook 8b — "Test Your Understanding" — Answer Key

## STEP 1 — Match the verb

| Action                   | Method | Annotation                | Success status |
| ------------------------ | ------ | ------------------------- | -------------- |
| show every game          | GET    | `@GetMapping`             | 200 OK         |
| add a new game           | POST   | `@PostMapping`            | 201 Created    |
| replace game 5's details | PUT    | `@PutMapping("/{id}")`    | 200 OK         |
| remove game 5            | DELETE | `@DeleteMapping("/{id}")` | 204 No Content |

## STEP 2 — Read the controller

- **Full URL that reaches `getById`:** `/api/books/{id}` — the class's `@RequestMapping("/api/books")` plus the method's `@GetMapping("/{id}")`. So e.g. `/api/books/12`.
- **What `@PathVariable Long id` does for `/api/books/12`:** it pulls the `12` out of the URL and sets the method's `id` parameter to `12`.
- **The two status codes, and when each happens:**
  - **200 OK** — when the book exists (`bookService.byId(id)` returns it) → `ResponseEntity.ok(book)`.
  - **404 Not Found** — when it doesn't (`byId` returns `null`) → `ResponseEntity.notFound().build()`.

## STEP 3 — Pick the status code

- a game was found and returned → **200 OK**
- a new game was created → **201 Created**
- a game was deleted with nothing to send back → **204 No Content**
- the client asked for a game id that doesn't exist → **404 Not Found**
- the client sent a broken request body → **400 Bad Request**
- something threw an unexpected error on the server → **500 Internal Server Error**

## STEP 4 — Spot the problem

- **`GET /api/deleteGame/5`** — breaks two conventions: it uses **GET to delete** (GET must be _safe_ / read-only and never change data), and it puts a **verb in the URL**. Fix: **`DELETE /api/games/5`**, returning **204 No Content**.
- **`POST /api/games` that returns 200** — wrong **status code**: creating a new resource should return **201 Created** (ideally with the new resource and a `Location` header), not 200.
- **The method with no `return`** — a method declared `public ResponseEntity<Game> getById(...)` must **return a value on every path**. One that looks up the game but has no `return` statement won't compile (and conceptually would hand the client nothing). Fix: return the result — e.g. `return ResponseEntity.ok(game);` — and handle the missing case with a 404.

## STEP 5 — In your own words

- **Purpose of `@RestController`:** it marks the class as a REST controller — it **handles HTTP requests**, and every method **returns its data directly as JSON** (not a web page).
- **Benefit of `ResponseEntity`:** it lets the method control **both the response body and the HTTP status code** — so you can return a 201, 404, or 204 when appropriate, instead of the default 200.
