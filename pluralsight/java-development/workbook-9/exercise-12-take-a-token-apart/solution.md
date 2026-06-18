# Exercise 12 — Take a Token Apart — Solution

The sample token:
```
eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiJhbGljZSIsInJvbGUiOiJVU0VSIiwiZXhwIjoxNzM1Njg5NjAwfQ.qZ3xY7Jr0sQ8m2bN1kP9wL5tV6cF4dH0aG7eR8sUiXo
```

## STEP 1 — Find the three parts
The token has **three** sections, separated by **dots (`.`)**:
- `eyJhbGci…J9` → **Header**
- `eyJzdWIi…fQ` → **Payload**
- `qZ3xY7…iXo` → **Signature**

## STEP 2 — Decode it
The header and payload are just **Base64-encoded** text (not encrypted), so anyone can decode them:

**Header** decodes to:
```json
{ "alg": "HS256", "typ": "JWT" }
```
→ signed with the **HS256** algorithm; type is JWT.

**Payload** decodes to:
```json
{ "sub": "alice", "role": "USER", "exp": 1735689600 }
```
→ the **claims**: subject (username) = `alice`, role = `USER`, and `exp` = `1735689600`, a Unix timestamp (seconds since 1970) for when the token **expires** — that value is Jan 1, 2025, 00:00 UTC.

*(The signature is not human-readable text — it's a cryptographic stamp, so there's nothing to "decode" there.)*

## STEP 3 — The key question
**What stops a user from editing the payload to `"role": "ADMIN"` and sending it back?** The **signature**.

When the server created the token, it produced the signature by hashing the header + payload together with a **secret key that only the server knows** (HMAC-SHA256). If you change even one character of the payload, the server recomputes the signature from your tampered payload and it **no longer matches** the signature on the token — and because you don't have the secret key, you can't forge a new valid one. So the server **rejects** the token.

In short: a JWT is **readable but not editable**. It's *signed, not encrypted* — what's protected is its **integrity** (proof it came from this server, unchanged), not its secrecy.

## STEP 4 — Two more to reason through
**(a) Why must the server's secret key never be shared with clients?** The secret key is what the server uses to **sign** tokens. Anyone who has it can forge their own valid token — for example, mint one with `"role": "ADMIN"` and sign it so it passes verification. The entire trust in the signature rests on *only the server* knowing the key; share it, and anyone could impersonate any user.

**(b) What attack does an expiry (`exp`) help limit?** A **stolen-token** (replay) attack. If a token leaks — intercepted in transit, or lifted from a client's storage — it can only be used until it expires. The `exp` caps that window, so a stolen token stops working after a while instead of granting access forever.
