--Projects related entities

-- NGO Projects main entity
CREATE TABLE project (
  id SERIAL PRIMARY KEY,
  insert_time TIMESTAMP NOT NULL,
  last_modified_time TIMESTAMP NOT NULL,
  name VARCHAR(150) UNIQUE NOT NULL,
  uri_alias VARCHAR(50) UNIQUE NOT NULL
);

--Polls related entities

--NGO Polls top level entity. Contains general information of entire poll. Has zero or more relative poll blocks.
CREATE TABLE poll (
  id SERIAL PRIMARY KEY,
  --modification information
  insert_time TIMESTAMP NOT NULL,
  last_modify_time TIMESTAMP NOT NULL,
  --general poll information
  name VARCHAR(150) UNIQUE NOT NULL,
  description TEXT,
  uri_alias VARCHAR(100) UNIQUE NOT NULL,
  status CHAR(8) CHECK (status IN ('draft', 'active', 'obsolete')) DEFAULT 'draft',
  poll_start_time TIMESTAMP,
  poll_obsolete_time TIMESTAMP,
  corr_project_id INTEGER REFERENCES project(id)
);

--Poll block is a logical grouped questions in a poll. Has zero or more relative poll questions.
CREATE TABLE poll_block (
  id SERIAL PRIMARY KEY, 
  --modification information
  insert_time TIMESTAMP NOT NULL,
  last_modify_time TIMESTAMP NOT NULL,
  --general block information
  name VARCHAR(150),
  description TEXT,
  --poll relative information
  poll_id INTEGER REFERENCES poll(id) ON DELETE CASCADE NOT NULL,
  poll_order INTEGER NOT NULL
);

--Poll question is a question general information. Like name, order in block, etc... Has zero or more relative poll question variants.
CREATE TABLE poll_question (
  id SERIAL PRIMARY KEY,
  --modification information
  insert_time TIMESTAMP NOT NULL,
  last_modify_time TIMESTAMP NOT NULL,
  --general question information
  name VARCHAR(300),
  description TEXT,
  answer_type CHAR(6) CHECK (answer_type IN ('text', 'single', 'multy')) DEFAULT 'text',
  --block relative information
  block_id INTEGER REFERENCES poll_block(id) ON DELETE CASCADE NOT NULL,
  block_order INTEGER NOT NULL
);

--Poll question variant is a one of available variants for the question.  
CREATE TABLE poll_question_variant (
  id SERIAL PRIMARY KEY,
  --modification information
  insert_time TIMESTAMP NOT NULL,
  last_modify_time TIMESTAMP NOT NULL,
  --general variant information
  name VARCHAR(150) NOT NULL,
  var_value VARCHAR(150) NOT NULL, 
  --question relative information
  question_id INTEGER REFERENCES poll_question(id) ON DELETE CASCADE NOT NULL,
  question_order INTEGER NOT NULL
);

--Poll answer entity is an answer related to the poll. Has one or more relative question answers.
CREATE TABLE poll_answer (
  id SERIAL PRIMARY KEY,
  --modification information
  insert_time TIMESTAMP NOT NULL,
  last_modify_time TIMESTAMP NOT NULL,
  --answer information
  poll_id INTEGER REFERENCES poll(id) ON DELETE CASCADE NOT NULL
);

--Poll question answer is an concrete answer related to the poll answer and to the question.
CREATE TABLE poll_question_answer (
  id SERIAL PRIMARY KEY,
  --modification information
  insert_time TIMESTAMP NOT NULL,
  last_modify_time TIMESTAMP NOT NULL,
  --answer information
  poll_answer_id INTEGER REFERENCES poll_answer(id) ON DELETE CASCADE NOT NULL,
  question_id INTEGER REFERENCES poll_question(id) ON DELETE CASCADE NOT NULL,
  answer_value TEXT
);
