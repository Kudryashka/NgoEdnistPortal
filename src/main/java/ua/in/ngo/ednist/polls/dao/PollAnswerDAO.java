package ua.in.ngo.ednist.polls.dao;

import java.util.List;

public interface PollAnswerDAO {

	void addAnswer(PollAnswer answer);
	
	List<PollAnswer> getPollAnswers(Poll poll);
}
