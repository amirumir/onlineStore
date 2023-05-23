CREATE DATABASE online_store;

CREATE TABLE channel
(

    id        SERIAL PRIMARY KEY,
    messages  VARCHAR[],
    followers BIGINT[],
    name      VARCHAR,
    user_id   BIGINT

);
ALTER TABLE channel
ADD CONSTRAINT fk_channel_user_id
    FOREIGN KEY (user_id)
        REFERENCES "user" (id);

CREATE TABLE document
(
    owner_id BIGINT,
    doc_name VARCHAR,
    doc_path VARCHAR,
    doc_id   BIGINT
);

CREATE TABLE letter
(
    letter_type VARCHAR,
    sender      VARCHAR,
    receiver    VARCHAR,
    subject     VARCHAR,
    letter_body VARCHAR
);

CREATE TABLE "user"
(
    name       VARCHAR,
    nickname   VARCHAR,
    age        INT,
    password   VARCHAR,
    id         SERIAL PRIMARY KEY,
    role       VARCHAR,
    birth_date VARCHAR,
    mail       VARCHAR,
    avatar     SMALLINT[]
);

SELECT *
FROM "user"
WHERE nickname = '';
SELECT *
FROM "user"
WHERE name = '';
DELETE
FROM "user"
WHERE name = '';
SELECT *
FROM "user"
WHERE role = 'USER';
SELECT *
FROM "user"
WHERE role = 'ADMIN';

