CREATE TABLE users (
  id BIGSERIAL NOT NULL CONSTRAINT user_pkey PRIMARY KEY,
  name VARCHAR(255) NOT NULL,
  login VARCHAR(50) NOT NULL,
  password VARCHAR(60) NOT NULL
);
