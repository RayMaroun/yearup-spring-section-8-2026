# Exercise 11 — Design the Access Levels — Solution

A paper exercise — the security design for the Sneaker Drops API, the same way you'll do it for the capstone.

## STEP 1 — Assign a level to every endpoint
| Endpoint | Level | Why |
|---|---|---|
| list sneakers — `GET /api/sneakers` | **Public** | reads are usually public so a front-end can show data to visitors |
| get one sneaker — `GET /api/sneakers/{id}` | **Public** | same — reading data needs no login |
| register — `POST /api/auth/register` | **Public** | you have no token yet when you sign up |
| log in — `POST /api/auth/login` | **Public** | this is *how* you get a token; it can't require one |
| create a sneaker — `POST /api/sneakers` | **Authenticated** | any logged-in user; writing requires a valid token |
| update a sneaker — `PUT /api/sneakers/{id}` | **Authenticated** | same — a write that any logged-in user may do |
| delete a sneaker — `DELETE /api/sneakers/{id}` | **Admin-only** | destructive/sensitive — restricted to the `ADMIN` role |

This is the public-read / authenticated-write / admin-delete pattern you'll reuse in the capstone.

## STEP 2 — Predict the status code
| Attempt | Answer | Why |
|---|---|---|
| a logged-out client calls `POST /api/sneakers` | **401 Unauthorized** | no token — the server doesn't know who you are (authentication failed) |
| a logged-in regular user calls admin-only `DELETE /api/sneakers/5` | **403 Forbidden** | it knows who you are, but you lack the `ADMIN` role (authorization failed) |
| a client sends an expired token | **401 Unauthorized** | the token is invalid/expired, so identity isn't proven |

Remember: **401 = authentication failed (the token); 403 = authorization failed (the role).**

## STEP 3 — Justify statelessness
A token the client carries means every request brings its own proof of identity, so the server doesn't have to remember anyone between requests — any server in a pool can handle any request, which scales cleanly. A server-side session, by contrast, stores per-user state that has to be kept in sync, expired, and shared across machines, which fights REST's statelessness and doesn't scale as well.
