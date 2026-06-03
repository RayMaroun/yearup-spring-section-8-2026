# 🕵️ The Case of the Diamond Cup

*A Midnight Heist — SQL Reinforcement*

---

## The Briefing

Last night, at the annual gala in the **Harbor Grand Hotel**, the priceless **Diamond Cup**
vanished from the locked Vault Room. Hundreds of guests. Dozens of staff. One thief.

The police have handed us everything they pulled from the hotel's systems — door logs, witness
statements, vehicle records, gym memberships, phone records. It's all sitting in a database called
**`midnight_heist`**. Somewhere in that data is a trail that leads to exactly one person... and to
the mastermind who put them up to it.

You're the detectives. **SQL is your only tool.** We'll crack this together, one query at a time.

> **How we'll work:** I'll call on detectives to propose the next query. We'll write it on screen,
> run it, and discuss what it tells us before we move on. There are no printed hints — the evidence
> *is* the hint. Read it carefully and it will point you to the next move.

---

## Your Access (the database)

Start every session with:

```sql
USE midnight_heist;
```

You have these records to work with:

- **`crime_report`** — `report_id`, `crime_type`, `crime_date`, `location`, `description`
- **`people`** — `person_id`, `name`, `occupation`, `city`
- **`witness_statements`** — `statement_id`, `witness_id`, `statement`
- **`keycard_swipes`** — `swipe_id`, `person_id`, `door`, `direction` (`IN`/`OUT`), `swipe_time`
- **`vehicles`** — `plate`, `person_id`, `make`, `color`, `type`
- **`gym_memberships`** — `membership_id`, `person_id`, `gym_name`, `join_date`
- **`phone_calls`** — `call_id`, `caller_id`, `receiver_id`, `call_time`, `duration_seconds`

The doors in the building are: `Main Entrance`, `Staff Door`, `Vault Room`, `North Exit`.

---

## ACT I — Open the Case
*Get the facts. (single table: `SELECT`, `WHERE`, `LIKE`)*

**1. Pull the crime report.** What exactly was taken, from where, on what date — and what time does
it say the theft happened?

**2. Read every witness statement.** Two people saw something useful. Pull the statements and read
them out loud as a group. As a class, build a **fact board**: every concrete detail a witness gave
us (a place, a time, a description, a habit) is a lead we can chase in the data.

> 💬 **Discuss:** Which details can we actually *search the database for*, and which are just
> background? Circle the ones that map to a table or a column.

**3.** One statement mentions a habit, not a name. Another mentions a *direction* someone left and
what they got into. Make sure the room agrees on the list of leads before Act II.

---

## ACT II — Round Up the Suspects
*Put people at the scene. (filtering: `WHERE`, date/time, `ORDER BY`)*

**4.** A witness saw the thief leave through a specific door **just after 11:10 PM** on the night of
the theft. Get the list of everyone who badged **OUT** of that door in the minutes right after the
theft.

> 🔮 **Predict first:** Before we run it — how many people do you expect to see? Write your guess
> on the board, then we run the query and check.

> 💬 **Discuss:** We've got more than one name. Good — a real case rarely narrows in one step. What
> *other* clue from our fact board could tell these suspects apart?

---

## ACT III — Follow the Evidence
*Cross-reference until one name is left. (`JOIN`, `GROUP BY`, `HAVING`)*

**5.** A witness said the thief jumped into a **red car**. Of our Act II suspects, who actually owns
a red vehicle? (You'll need to connect two tables.)

**6.** The other witness placed the thief at a specific gym. Of who's left, who holds a membership
there?

**7.** Hunch check: the thief had to get *into* the Vault Room. Which person badged into the **Vault
Room** the most times this week? Does that line up with your suspect?

> 🧨 **Spot the broken query.** A rookie detective wrote this to list each suspect's car, but it
> spits out *every car in the city* attached to *every* person. What's wrong with it?
> ```sql
> SELECT p.name, v.color, v.type
> FROM people p
> JOIN vehicles v;
> ```

> 💬 **Discuss:** Walk the room through it — for each *other* suspect, name the single piece of
> evidence that clears them. If we can eliminate everyone but one person, we have our thief.

**8. Name your prime suspect.** Be ready to defend it with the queries that put them — and only
them — at the center of every clue.

---

## ACT IV — The Mastermind 🎭
*This was an inside job. (subqueries — the boss)*

A guard doesn't pull off a heist like this alone. One witness saw the **Gala Director** arguing with
a guard earlier that night. Let's prove the connection.

**9.** Using the phone records, find out **who the thief called right after the heist** (after they
slipped out the North Exit). Try to write it so the query figures out *who the thief is on its own*
— a subquery inside the lookup — rather than you typing their id by hand.

> ⚖️ **Two ways, one answer:** Solve #9 once with a **subquery** and once with a **JOIN**. Which
> reads more clearly to you? Why?

**10.** Confirm the partnership: how many times had the thief called that same person in the days
*before* the heist? A one-off call is a coincidence; a pattern is a conspiracy.

**11. 🏆 Close the case.** Announce both names to the room:
- **The thief** — and the chain of evidence that proves it.
- **The mastermind** — and the call records that tie them in.

---

## Case Closed

Write up your final accusation: the **two names**, and beneath each, the **queries** that prove it.
A good detective doesn't just name a suspect — they can show exactly how the data corners them.

*Nice work, Detective. The Diamond Cup is coming home.*
