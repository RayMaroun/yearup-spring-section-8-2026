# Exercise 2 — Design the Conversation — Solution

A paper exercise mapping HTTP onto a small API.

## STEP 1 — Pick the verb
| Action | Method | Why |
|---|---|---|
| show every game | **GET** | a read; carries no body and changes nothing |
| add a new game | **POST** | creates a new resource; the data rides in the body |
| replace game 5's details | **PUT** | full replacement of an existing resource |
| remove game 5 | **DELETE** | removes the resource |

## STEP 2 — Pick the status code
| Outcome | Status | Family |
|---|---|---|
| a game was found and returned | **200 OK** | 2xx |
| a new game was created | **201 Created** | 2xx |
| a game was deleted, nothing to send back | **204 No Content** | 2xx |
| the client asked for a game that doesn't exist | **404 Not Found** | 4xx |
| the client sent a broken request body | **400 Bad Request** | 4xx |
| the server's code crashed | **500 Internal Server Error** | 5xx |

## STEP 3 — Safe vs. idempotent
- **Safe** = the method only *reads*; it never changes data.
- **Idempotent** = doing it many times leaves the same end state as doing it once.

| Method | Safe? | Idempotent? |
|---|---|---|
| GET | yes | yes |
| PUT | no | yes |
| DELETE | no | yes |
| POST | no | no |

**Why POST is neither:** it *creates*, so it changes data (not safe), and sending it five times makes five resources (not idempotent).

## STEP 4 — Read it raw
From the request/response text:
```
GET /api/games/3 HTTP/1.1
Host: localhost:8080
Accept: application/json
```
- **Method:** `GET`  ·  **Path:** `/api/games/3`  ·  **A header:** `Accept: application/json` (or `Host: …`)
```
HTTP/1.1 200 OK
Content-Type: application/json

{ "id": 3, "title": "Celeste", ... }
```
- **Status line:** `HTTP/1.1 200 OK`  ·  **Body:** the JSON object.

The single line that tells the client whether things went well is the **status line** (`200 OK`).
