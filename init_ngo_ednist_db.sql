CREATE TABLE projects (
  id SERIAL PRIMARY KEY,
  insert_time TIMESTAMP,
  uri_alias VARCHAR(50) UNIQUE NOT NULL,
  name VARCHAR(150) UNIQUE NOT NULL
);

CREATE TABLE polls (
  id SERIAL PRIMARY KEY,
  insert_time TIMESTAMP,
  uri_alias VARCHAR(50) UNIQUE NOT NULL,
  name VARCHAR(150) UNIQUE NOT NULL,
  view_type CHAR(11) CHECK (view_type IN ('unique_page')) DEFAULT 'unique_page',
  view_name VARCHAR(50) NOT NULL
);

CREATE TABLE poll_data (
  id SERIAL PRIMARY KEY,
  insert_time TIMESTAMP,
  poll_id INTEGER REFERENCES polls (id) ON DELETE CASCADE NOT NULL,
  data TEXT
);
