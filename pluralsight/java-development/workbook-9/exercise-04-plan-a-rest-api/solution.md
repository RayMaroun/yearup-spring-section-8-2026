# Exercise 4 — Plan a REST API — Solution

Designing the **Movie Catalog** API. A movie has an id, title, release year, rating, and belongs to one studio; a studio has an id and name. *(There is more than one workable answer — what matters is that the choices follow the conventions and can be justified.)*

## STEP 1 — Name the resources
- All movies: `/api/movies`
- One movie (id 7): `/api/movies/7`
- All studios: `/api/studios`
- One studio (id 3): `/api/studios/3`

Nouns, plural collections, the id in the path.

## STEP 2 — Map every operation
| Operation | Method | URL | Success status | Why that status |
|---|---|---|---|---|
| list all movies | GET | `/api/movies` | **200 OK** | a successful read returns data |
| get one movie | GET | `/api/movies/7` | **200 OK** | a successful read returns data |
| add a movie | POST | `/api/movies` | **201 Created** | a new resource was created |
| update a movie | PUT | `/api/movies/7` | **200 OK** | returns the updated resource |
| delete a movie | DELETE | `/api/movies/7` | **204 No Content** | success, nothing to send back |

## STEP 3 — Design the request body
Adding a new movie:
```json
{
  "title": "Dune",
  "releaseYear": 2021,
  "rating": 8.0,
  "studio": { "id": 4 }
}
```
Represent the studio by **its id only** (`{ "id": 4 }`), not the whole studio object. The studio already exists — the client just points at which one, and the server looks up the real studio by that id. Sending the whole studio would be redundant and could carry stale or conflicting data.

## STEP 4 — Choose the error status codes
| Situation | Status | Why |
|---|---|---|
| asks for a movie id that doesn't exist | **404 Not Found** | the resource isn't there |
| sends a new movie with a blank title | **400 Bad Request** | invalid input (failed validation) |
| sends a body with a single-quoted key | **400 Bad Request** | malformed JSON — rejected before your code runs |

*(Designing the matching error **response body** — one consistent shape for every failure — comes later, in Exercise 9 (Section 4–2), once error handling is covered.)*

## STEP 5 — Design two filters
Use query strings on the existing collection — not new paths:
- From a year: `/api/movies?year=2021`
- Above a rating: `/api/movies?minRating=8.0`
- **Both at once:** `/api/movies?year=2021&minRating=8.0`

## STEP 6 — Classify the operations
| Operation | Safe? | Idempotent? |
|---|---|---|
| list all movies (GET) | yes | yes |
| get one movie (GET) | yes | yes |
| add a movie (POST) | no | no |
| update a movie (PUT) | no | yes |
| delete a movie (DELETE) | no | yes |

**POST is the odd one out** — the only operation that is *neither* safe nor idempotent: it changes data, and repeating it creates a duplicate each time.
