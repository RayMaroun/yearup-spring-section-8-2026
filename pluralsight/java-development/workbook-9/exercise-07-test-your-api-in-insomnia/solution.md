# Exercise 7 — Test Your API in Insomnia — Solution

A hands-on exercise — the "solution" is the requests you send and the status codes you observe. (Your API is the read-only Sneaker Drops API from Exercises 5–6: base `/api/sneakers`, with filters for `year`, `model`, `brand`, and a `minPrice`/`maxPrice` price range.)

## STEP 1 — Send the read requests
In Insomnia, send a request to each read endpoint and confirm the data is what you expect:

| What | Method + URL |
|---|---|
| All sneakers | `GET http://localhost:8080/api/sneakers` |
| One by id | `GET http://localhost:8080/api/sneakers/1` |
| By brand | `GET http://localhost:8080/api/sneakers?brand=Nike` |
| By year | `GET http://localhost:8080/api/sneakers?year=2023` |
| By model | `GET http://localhost:8080/api/sneakers?model=Air` |
| Price range | `GET http://localhost:8080/api/sneakers?minPrice=80&maxPrice=200` |
| Combined filter | `GET http://localhost:8080/api/sneakers?brand=Nike&maxPrice=200` |

For each: choose the method (`GET`), type the URL, click **Send**, and read the JSON in the response panel. The combined filter should genuinely narrow the list by *both* conditions.

## STEP 2 — Read the status codes
| Request | Status you get |
|---|---|
| a successful list | **200 OK** |
| one sneaker by id | **200 OK** |
| a missing id (`/api/sneakers/9999`) | **200 OK** with an **empty body** |

The missing-id request is the one reporting the **wrong** status: asking for something that doesn't exist should be a **404 Not Found**, not a 200. That is exactly what Module 4 (Exercise 9) fixes, by throwing `ResponseStatusException(HttpStatus.NOT_FOUND)` when the service returns `null`.

**The habit to keep:** glance at the status code on *every* request, not just the body — a correct body with the wrong status code is still a bug.

> *Note:* a **collection** in Insomnia just groups saved requests so you don't retype them. You don't need to build one here — in the capstone you'll be given a ready-made collection to import and use. The skill that matters now is sending a request and reading its response and status code.
