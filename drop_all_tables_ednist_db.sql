--Commands to drop all tables one by one.
--Entities delete in reverce order to prevent fail via relations.

--Poll relative tables
DROP TABLE poll_question_answer;
DROP TABLE poll_answer;
DROP TABLE poll_question_variant;
DROP TABLE poll_question;
DROP TABLE poll_block;
DROP TABLE poll;

--Project relative tables
DROP TABLE project;