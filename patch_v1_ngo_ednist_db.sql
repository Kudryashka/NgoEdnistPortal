ALTER TABLE project
    ADD COLUMN overview TEXT;
    
ALTER TABLE project
	ADD COLUMN status CHAR(8) CHECK (status IN ('draft', 'active', 'complete')) DEFAULT 'draft';

CREATE TABLE self_info (
	id SERIAL PRIMARY KEY,
	last_modify_time TIMESTAMP NOT NULL,	
	name VARCHAR(150) NOT NULL,
	short_name VARCHAR(100) NOT NULL,
	overview TEXT
);