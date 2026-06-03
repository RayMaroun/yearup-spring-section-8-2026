# 🔍 The Case of the Diamond Cup — Solved

*A step-by-step walkthrough — for reviewing the case on your own after class.*

> Spoiler ahead! This is the full solution. If you still want to crack it yourself, go back to the
> case file first. Otherwise — let's retrace how the evidence cornered our two culprits.

Start here every time:
```sql
USE midnight_heist;
```

---

## ACT I — Open the Case

**1. The crime report.** First, the facts.
```sql
SELECT * FROM crime_report;
```
The **Diamond Cup** was taken from the **Vault Room** at the Harbor Grand Hotel on **2024-11-15**,
**shortly after 11:00 PM**. Two witnesses gave statements. That date and time drive every filter we
write later.

**2. The witness statements.**
```sql
SELECT ws.statement_id, p.name AS witness, ws.statement
FROM witness_statements ws
JOIN people p ON ws.witness_id = p.person_id;
```
Two statements matter:
- **Priya Nair:** the thief left by the **North Exit** *just after 11:10 PM*, in a **staff uniform**,
  and got into a **red car**.
- **Tom Walsh:** the suspect is a **guard** who trains at **FitZone Gym**.

(Grace's note about the Director arguing with a guard becomes important in Act IV. Ethan's
bartender comment is a red herring.)

So our suspect must tick **all** of these: left the North Exit right after 11:10, owns a red car,
and is a FitZone member. Let's chase each one.

---

## ACT II — Round Up the Suspects

**4. Who badged OUT of the North Exit just after the theft?** "Just after 11:10" → the 23:10–23:20
window on the night of the 15th.
```sql
SELECT ks.person_id, p.name, ks.swipe_time
FROM keycard_swipes ks
JOIN people p ON ks.person_id = p.person_id
WHERE ks.door = 'North Exit'
  AND ks.direction = 'OUT'
  AND ks.swipe_time BETWEEN '2024-11-15 23:10:00' AND '2024-11-15 23:20:00';
```
Two names come back: **Marcus Reed (23:12)** and **Noah Patel (23:15)**. Others used that exit
earlier in the night, so the time window already cleared them. Now we split these two.

---

## ACT III — Follow the Evidence

**5. Which of them owns a red car?**
```sql
SELECT p.name, v.color, v.type
FROM people p
JOIN vehicles v ON p.person_id = v.person_id
WHERE p.person_id IN (3, 11)
  AND v.color = 'Red';
```
Only **Marcus Reed** (red Honda). Noah's car is blue — **Noah is cleared.**

**6. Is the remaining suspect a FitZone member?**
```sql
SELECT p.name, g.gym_name
FROM people p
JOIN gym_memberships g ON p.person_id = g.person_id
WHERE g.gym_name = 'FitZone';
```
Marcus is on the list (Noah trains at IronWorks). Two independent clues — the red car and the gym —
both point to the same person.

**7. Corroboration: who hit the Vault Room most this week?**
```sql
SELECT p.name, COUNT(*) AS vault_visits
FROM keycard_swipes ks
JOIN people p ON ks.person_id = p.person_id
WHERE ks.door = 'Vault Room'
GROUP BY p.person_id, p.name
ORDER BY vault_visits DESC;
```
**Marcus Reed = 3 visits**, everyone else at most 1. A guard with that much vault access, who also
fits both witness descriptions? That's our thief.

> **Watch out — the cartesian-product trap.** A join *needs* an `ON` clause telling SQL how the
> tables relate. Leaving it out (`FROM people p JOIN vehicles v;`) pairs every person with every
> car. Always join on the matching key: `... ON p.person_id = v.person_id`.

**8. Prime suspect: Marcus Reed.** Every witness clue intersects on exactly one person.

| Suspect | Left North Exit 23:10–23:20 | Red car | FitZone | Verdict |
|---|---|---|---|---|
| **Marcus Reed** | ✅ 23:12 | ✅ | ✅ | **THIEF** |
| Noah Patel | ✅ 23:15 | ❌ blue | ❌ | cleared |
| Sofia Russo | ❌ | ✅ | ✅ | cleared (never at the exit) |
| Grace Liu | ❌ | ✅ | ❌ | cleared |
| Tom Walsh | ❌ | ❌ | ✅ | cleared |

---

## ACT IV — The Mastermind 🎭

**9. Who did the thief call right after the heist?** Here's a version where a **subquery** finds the
thief from the evidence, so we never hard-code his id:
```sql
SELECT receiver.name AS mastermind, pc.call_time
FROM phone_calls pc
JOIN people receiver ON pc.receiver_id = receiver.person_id
WHERE pc.caller_id IN (
        SELECT v.person_id
        FROM vehicles v
        JOIN gym_memberships g ON v.person_id = g.person_id
        JOIN keycard_swipes ks ON v.person_id = ks.person_id
        WHERE v.color = 'Red'
          AND g.gym_name = 'FitZone'
          AND ks.door = 'North Exit' AND ks.direction = 'OUT'
          AND ks.swipe_time BETWEEN '2024-11-15 23:10:00' AND '2024-11-15 23:20:00'
      )
  AND pc.call_time > '2024-11-15 23:20:00';
```
The call goes to **Vivian Cross**, the **Gala Director**, at **23:28** — minutes after the thief
slipped out.

The same answer, written with a **join** once we already know the thief's name (shorter, but it
assumes we've identified him):
```sql
SELECT receiver.name AS mastermind, pc.call_time
FROM phone_calls pc
JOIN people thief    ON pc.caller_id   = thief.person_id
JOIN people receiver ON pc.receiver_id = receiver.person_id
WHERE thief.name = 'Marcus Reed'
  AND pc.call_time > '2024-11-15 23:20:00';
```

**10. Was it a one-off, or a pattern?**
```sql
SELECT COUNT(*) AS prior_calls
FROM phone_calls
WHERE caller_id = 3 AND receiver_id = 12
  AND call_time < '2024-11-15 23:00:00';
```
**Three** earlier calls (Nov 10, 12, 14) — four in total with the one right after the heist. That's
not a coincidence; that's a plan.

---

## 🏆 Case Solved

- **The thief: Marcus Reed** — a security guard. He badged into the Vault Room three times that week,
  walked out the North Exit at 23:12 in his staff uniform, and drove off in his red Honda. He trains
  at FitZone, exactly as the witness said.
- **The mastermind: Vivian Cross** — the Gala Director. Marcus called her four times in five days,
  including 23:28 on the night of the heist. A witness even saw her arguing with a guard earlier
  that evening.

The Diamond Cup is coming home. 🥂

---

### What this case drilled
- **Act I:** `SELECT`, `WHERE`, `LIKE`
- **Act II:** filtering on date/time ranges with `BETWEEN`
- **Act III:** `JOIN` (and why every join needs `ON`), `GROUP BY`, `HAVING`, `COUNT`
- **Act IV:** subqueries with `IN (SELECT …)`, and subquery-vs-join trade-offs
