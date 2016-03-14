--Insert dummy data into ngo_ednist_db
BEGIN TRANSACTION;
	--polls
	INSERT 
		INTO poll (
			insert_time, last_modify_time, 
			name, description, uri_alias,
			status, poll_start_time, poll_obsolete_time) 
		VALUES (
			'now', 'now',
			'dummy poll', 'it is a dummy poll', 'dummy',
			'active', '2020-01-01 01:00', '2021-01-01 23:00');
	INSERT
		INTO poll (
			insert_time, last_modify_time,
			name, uri_alias)
		VALUES (
			'now', 'now',
			'dummy poll 2', 'dummy2');
	--poll blocks
	INSERT 
		INTO poll_block (
			insert_time, last_modify_time,
			name, description, 
			poll_id, poll_order)
		VALUES (
			'now', 'now',
			'poll block 1', 'block description', 
			1, 1);
	INSERT 
		INTO poll_block (
			insert_time, last_modify_time,
			name, description, 
			poll_id, poll_order)
		VALUES (
			'now', 'now',
			'poll block 2', 'block description', 
			1, 2);
	INSERT 
		INTO poll_block (
			insert_time, last_modify_time,
			name, poll_id, poll_order)
		VALUES (
			'now', 'now',
			'poll block without description', 1, 0);
	--poll questions
	INSERT 
		INTO poll_question (
			insert_time, last_modify_time, 
			name, description, answer_type, 
			block_id, block_order) 
		VALUES (
			'now', 'now',
			'question 1', 'description', 'text', 1, 1);
	INSERT 
		INTO poll_question (
			insert_time, last_modify_time, 
			name, answer_type, 
			block_id, block_order) 
		VALUES (
			'now', 'now',
			'question 2', 'multy', 1, 2);
	--poll question variants
	INSERT 
		INTO poll_question_variant (
			insert_time, last_modify_time,
			name, var_value, 
			question_id, question_order) 
		VALUES (
			'now', 'now',
			'first choose', 'first',
			2, 1);
	INSERT 
		INTO poll_question_variant (
			insert_time, last_modify_time,
			name, var_value, 
			question_id, question_order) 
		VALUES (
			'now', 'now',
			'second choose', 'second',
			2, 2);
	--poll answers
	INSERT 
		INTO poll_answer (
			insert_time, last_modify_time, poll_id) 
		VALUES (
			'now', 'now', 1);
	--poll question answers
	INSERT 
		INTO poll_question_answer (
			insert_time, last_modify_time,
			poll_answer_id, question_id, 
			answer_value) 
		VALUES (
			'now', 'now', 1, 1, 'just text');
	INSERT 
		INTO poll_question_answer (
			insert_time, last_modify_time,
			poll_answer_id, question_id, 
			answer_value) 
		VALUES (
			'now', 'now', 1, 2, 'second');
END;