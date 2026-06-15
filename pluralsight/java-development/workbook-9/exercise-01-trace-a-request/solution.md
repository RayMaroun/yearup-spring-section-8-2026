# Exercise 1 — Trace a Request — Solution

A paper exercise. The goal is to make the vocabulary automatic.

## STEP 1 — Take an address apart
`http://localhost:8080/api/games`
- **Protocol:** `http` — the language the client and server use to talk.
- **Machine (host):** `localhost` — the name for "this computer."
- **Program (port):** `8080` — which program on the machine (your Spring Boot app).
- (`/api/games` is the path — *which* resource — but that wasn't asked here.)

**`127.0.0.1` and `localhost`:** `127.0.0.1` is the *loopback* IP address that means "this same machine." `localhost` is just the human-friendly **name** for that address — resolving `localhost` gives you `127.0.0.1`. They point to the same place.

## STEP 2 — Follow a name to a number
Typing `google.com`:
1. The browser checks whether it already knows the IP (a local cache). If not…
2. It asks **DNS** (the Domain Name System) to translate the name `google.com` into an **IP address**.
3. DNS answers with the number (e.g. `142.250.x.x`).
4. The browser opens a connection to that IP (port 443 for HTTPS) and sends the HTTP request.
5. The server sends back the response.

**Where DNS fits:** the name → number translation in step 2. The everyday "contact list" is exactly DNS — you know the *name*, it looks up the *number*, the way your phone's contacts turn a name into a phone number.

## STEP 3 — Why two numbers, not one
Using the apartment-building analogy:
- The **IP address** gets you to the right **building** (the machine).
- The **port** gets you to the right **apartment** (the program) inside it.

One machine runs many programs at once, so reaching the building isn't enough — you still need to know which door to knock on. A complete address needs **both** the IP (which machine) and the port (which program on it).

The two ports you use this week:
- **8080** — your Spring Boot app (the API).
- **3306** — MySQL (the database).

## STEP 4 — Predict an error
The second copy **fails to start** with a "port already in use" / "address already in use" error. Only **one** program can **bind** to and **listen** on a given port at a time. The first app already owns 8080, so the second can't bind it — Spring Boot reports the port is taken and the app exits. (Fix: stop the first app, or run the second on a different port.)
