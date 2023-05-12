CREATE DATABASE online_store.sql

CREATE TABLE channel(

id SERIAL PRIMARY KEY,
messages VARCHAR[],
followers BIGINT[],
name VARCHAR,
user_id BIGINT
);

CREATE TABLE document(

owner_id BIGINT,
doc_name VARCHAR,
doc_path VARCHAR,
doc_id BIGINT
);

CREATE TABLE letter(
letter_type VARCHAR,
sender VARCHAR,
receiver VARCHAR,
subject VARCHAR,
letter_body VARCHAR
)

CREATE TABLE "user" (
name VARCHAR,
nickname VARCHAR,
age INT,
password VARCHAR,
id SERIAL PRIMARY KEY,
role VARCHAR,
birth_date VARCHAR,
mail VARCHAR,
avatar SMALLINT[]
)

SELECT 'user' FROM "user" WHERE nickname = '';
SELECT 'user' FROM "user" WHERE name = '';
DELETE FROM "user" WHERE name = '';
SELECT 'user' FROM "user" WHERE role = 'USER';
SELECT 'user' FROM "user" WHERE role = 'ADMIN';

