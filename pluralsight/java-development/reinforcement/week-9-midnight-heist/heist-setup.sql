-- =====================================================================
--  THE CASE OF THE DIAMOND CUP  —  "Midnight Heist" reinforcement database
--  Run this whole file ONCE before class (lightning-bolt in MySQL Workbench).
--
--  The data is engineered so the clues narrow to exactly ONE thief and
--  ONE mastermind. Do not show students this file.
-- =====================================================================

DROP DATABASE IF EXISTS midnight_heist;
CREATE DATABASE midnight_heist;
USE midnight_heist;

-- ---------------------------------------------------------------------
--  Tables
-- ---------------------------------------------------------------------
CREATE TABLE people (
    person_id   INT PRIMARY KEY,
    name        VARCHAR(100) NOT NULL,
    occupation  VARCHAR(100),
    city        VARCHAR(100)
);

CREATE TABLE crime_report (
    report_id   INT PRIMARY KEY,
    crime_type  VARCHAR(50),
    crime_date  DATE,
    location    VARCHAR(150),
    description VARCHAR(500)
);

CREATE TABLE witness_statements (
    statement_id INT PRIMARY KEY AUTO_INCREMENT,
    witness_id   INT,
    statement    VARCHAR(600),
    FOREIGN KEY (witness_id) REFERENCES people(person_id)
);

CREATE TABLE keycard_swipes (
    swipe_id   INT PRIMARY KEY AUTO_INCREMENT,
    person_id  INT,
    door       VARCHAR(50),           -- Main Entrance, Staff Door, Vault Room, North Exit
    direction  VARCHAR(3),            -- IN / OUT
    swipe_time DATETIME,
    FOREIGN KEY (person_id) REFERENCES people(person_id)
);

CREATE TABLE vehicles (
    plate     VARCHAR(15) PRIMARY KEY,
    person_id INT,
    make      VARCHAR(50),
    color     VARCHAR(30),
    type      VARCHAR(30),
    FOREIGN KEY (person_id) REFERENCES people(person_id)
);

CREATE TABLE gym_memberships (
    membership_id INT PRIMARY KEY AUTO_INCREMENT,
    person_id     INT,
    gym_name      VARCHAR(50),
    join_date     DATE,
    FOREIGN KEY (person_id) REFERENCES people(person_id)
);

CREATE TABLE phone_calls (
    call_id          INT PRIMARY KEY AUTO_INCREMENT,
    caller_id        INT,
    receiver_id      INT,
    call_time        DATETIME,
    duration_seconds INT,
    FOREIGN KEY (caller_id)   REFERENCES people(person_id),
    FOREIGN KEY (receiver_id) REFERENCES people(person_id)
);

-- ---------------------------------------------------------------------
--  People   (#3 Marcus Reed = THIEF,  #12 Vivian Cross = MASTERMIND)
-- ---------------------------------------------------------------------
INSERT INTO people (person_id, name, occupation, city) VALUES
(1,  'Olivia Bennett',  'Investor',        'Harborview'),
(2,  'Daniel Cho',      'Caterer',         'Harborview'),
(3,  'Marcus Reed',     'Security Guard',  'Harborview'),
(4,  'Priya Nair',      'Photographer',    'Harborview'),
(5,  'Tom Walsh',       'Bartender',       'Harborview'),
(6,  'Sofia Russo',     'Security Guard',  'Riverside'),
(7,  'Liam O''Connor',  'Janitor',         'Harborview'),
(8,  'Hannah Kim',      'Valet',           'Riverside'),
(9,  'Ethan Brooks',    'Guest',           'Harborview'),
(10, 'Grace Liu',       'Guest',           'Riverside'),
(11, 'Noah Patel',      'Security Guard',  'Harborview'),
(12, 'Vivian Cross',    'Gala Director',   'Harborview');

-- ---------------------------------------------------------------------
--  The crime report  (the opening clue)
-- ---------------------------------------------------------------------
INSERT INTO crime_report (report_id, crime_type, crime_date, location, description) VALUES
(1, 'Theft', '2024-11-15', 'Vault Room, Harbor Grand Hotel',
 'The Diamond Cup was stolen from the Vault Room during the annual gala. '
 'The theft took place shortly after 11:00 PM. Two witnesses gave statements to the police.');

-- ---------------------------------------------------------------------
--  Witness statements  (free text — search with LIKE)
-- ---------------------------------------------------------------------
INSERT INTO witness_statements (witness_id, statement) VALUES
(4,  'I was photographing near the north exit. Just after 11:10 PM I saw someone in a staff uniform slip out the North Exit carrying a large duffel bag, then jump into a red car that was waiting outside.'),
(5,  'I never caught their name, but I have seen that guard at FitZone Gym downtown plenty of times — always there lifting weights before an evening shift.'),
(10, 'Earlier in the night I noticed the Gala Director having a tense, whispered argument with one of the security guards near the coat room.'),
(9,  'The bartender vanished for a bit around 10:30, but honestly I think he was just sneaking a break.');

-- ---------------------------------------------------------------------
--  Keycard swipes  (the night of 2024-11-15, plus the days before)
-- ---------------------------------------------------------------------
-- Guests / staff arriving (noise)
INSERT INTO keycard_swipes (person_id, door, direction, swipe_time) VALUES
(1,  'Main Entrance', 'IN',  '2024-11-15 19:30:00'),
(9,  'Main Entrance', 'IN',  '2024-11-15 19:45:00'),
(10, 'Main Entrance', 'IN',  '2024-11-15 19:50:00'),
(6,  'Staff Door',    'IN',  '2024-11-15 18:00:00'),
(3,  'Staff Door',    'IN',  '2024-11-15 18:05:00'),
(11, 'Staff Door',    'IN',  '2024-11-15 18:10:00'),
(7,  'Staff Door',    'IN',  '2024-11-15 17:30:00'),
(12, 'Main Entrance', 'IN',  '2024-11-15 19:00:00');

-- Vault Room access this week  (Marcus = 3 has unusually many)
INSERT INTO keycard_swipes (person_id, door, direction, swipe_time) VALUES
(3,  'Vault Room', 'IN',  '2024-11-13 14:00:00'),
(3,  'Vault Room', 'IN',  '2024-11-14 15:30:00'),
(3,  'Vault Room', 'IN',  '2024-11-15 23:05:00'),   -- the theft
(6,  'Vault Room', 'IN',  '2024-11-12 10:00:00'),
(12, 'Vault Room', 'IN',  '2024-11-15 18:40:00');

-- North Exit departures the night of the heist
INSERT INTO keycard_swipes (person_id, door, direction, swipe_time) VALUES
(7,  'North Exit', 'OUT', '2024-11-15 21:30:00'),   -- too early
(8,  'North Exit', 'OUT', '2024-11-15 22:40:00'),   -- too early
(3,  'North Exit', 'OUT', '2024-11-15 23:12:00'),   -- THIEF, in the window
(11, 'North Exit', 'OUT', '2024-11-15 23:15:00'),   -- in the window (eliminated later)
(1,  'Main Entrance', 'OUT', '2024-11-15 23:40:00'),
(9,  'Main Entrance', 'OUT', '2024-11-15 23:55:00');

-- ---------------------------------------------------------------------
--  Vehicles  (red cars: Marcus 3, Sofia 6, Grace 10)
-- ---------------------------------------------------------------------
INSERT INTO vehicles (plate, person_id, make, color, type) VALUES
('RED-44X', 3,  'Honda',  'Red',   'Sedan'),
('BLU-21A', 11, 'Toyota', 'Blue',  'Sedan'),
('RED-99Z', 6,  'Mazda',  'Red',   'Hatchback'),
('RED-07K', 10, 'Kia',    'Red',   'SUV'),
('BLK-12M', 1,  'BMW',    'Black', 'Sedan'),
('WHT-33P', 12, 'Audi',   'White', 'Sedan'),
('GRN-88T', 8,  'Subaru', 'Green', 'Wagon');

-- ---------------------------------------------------------------------
--  Gym memberships  (FitZone: Marcus 3, Tom 5, Sofia 6)
-- ---------------------------------------------------------------------
INSERT INTO gym_memberships (person_id, gym_name, join_date) VALUES
(3,  'FitZone',    '2024-01-10'),
(5,  'FitZone',    '2023-09-01'),
(6,  'FitZone',    '2024-03-22'),
(8,  'IronWorks',  '2024-02-15'),
(11, 'IronWorks',  '2024-05-30'),
(10, 'PowerHouse', '2024-06-01');

-- ---------------------------------------------------------------------
--  Phone calls  (Marcus 3 -> Vivian 12: planning calls + one right after)
-- ---------------------------------------------------------------------
INSERT INTO phone_calls (caller_id, receiver_id, call_time, duration_seconds) VALUES
(3,  12, '2024-11-10 19:00:00', 200),   -- planning
(3,  12, '2024-11-12 20:30:00', 150),   -- planning
(3,  12, '2024-11-14 18:45:00', 300),   -- planning
(3,  12, '2024-11-15 23:28:00', 120),   -- right after the heist
(3,  5,  '2024-11-13 12:00:00',  60),   -- noise
(3,  2,  '2024-11-11 09:00:00',  45),   -- noise
(12, 1,  '2024-11-09 15:00:00',  90),   -- noise
(6,  8,  '2024-11-15 22:00:00',  30),   -- noise
(9,  10, '2024-11-15 21:00:00',  75),   -- noise
(11, 8,  '2024-11-15 23:50:00',  50);   -- noise (Noah, not the thief)

-- =====================================================================
--  Sanity check (optional):
--    SELECT COUNT(*) FROM people;          -- 12
--    SELECT COUNT(*) FROM keycard_swipes;  -- 19
--    SELECT COUNT(*) FROM phone_calls;     -- 10
-- =====================================================================
