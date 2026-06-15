# Exercise 3 — Model and Fix JSON — Solution

## STEP 1 — Model one object
A `Genre` with id 5 and name "Roguelike":
```json
{
  "id": 5,
  "name": "Roguelike"
}
```

## STEP 2 — Nest an object
A `Game` (id 12, "Hades", 2020, 9.3) that belongs to that genre — the genre object nested *inside* the game:
```json
{
  "id": 12,
  "title": "Hades",
  "releaseYear": 2020,
  "rating": 9.3,
  "genre": {
    "id": 5,
    "name": "Roguelike"
  }
}
```

## STEP 3 — Build a list
A response that returns *two* games is a JSON **array**:
```json
[
  { "id": 12, "title": "Hades", "releaseYear": 2020, "rating": 9.3 },
  { "id": 13, "title": "Dead Cells", "releaseYear": 2018, "rating": 9.0 }
]
```
The single character at the very start that signals a list is **`[`** (the opening square bracket — an array, not one object).

## STEP 4 — Fix the broken JSON
| Broken | What's wrong | Fixed |
|---|---|---|
| `{ 'title': 'Celeste' }` | single quotes — JSON uses **double** quotes on keys *and* string values | `{ "title": "Celeste" }` |
| `{ "id": 3, "rating": 9.1, }` | a **trailing comma** after the last pair | `{ "id": 3, "rating": 9.1 }` |
| `{ title: "Hades", "releaseYear": 2020 }` | the key `title` is **unquoted** | `{ "title": "Hades", "releaseYear": 2020 }` |
| `{ "rating": "9.3" }` (fills a `double`) | the number is **in quotes**, making it a string | `{ "rating": 9.3 }` |

Each of these makes the JSON invalid, so the request would come back as a **400 Bad Request** before your code ever runs.
