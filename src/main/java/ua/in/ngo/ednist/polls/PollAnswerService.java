package ua.in.ngo.ednist.polls;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ua.in.ngo.ednist.polls.dao.Poll;
import ua.in.ngo.ednist.polls.dao.PollAnswer;
import ua.in.ngo.ednist.polls.dao.PollAnswerDAO;

@Service
public class PollAnswerService {
	
	private PollAnswerDAO pollAnswerDAO;

	public void setPollAnswerDAO(PollAnswerDAO pollAnswerDAO) {
		this.pollAnswerDAO = pollAnswerDAO;
	}
	
	@Transactional
	public void addAnswer(PollAnswer answer) {
		pollAnswerDAO.addAnswer(answer);
	}
	
	@Transactional
	public List<PollAnswer> getPollAnswers(Poll poll) {
		return pollAnswerDAO.getPollAnswers(poll);
	}
}
