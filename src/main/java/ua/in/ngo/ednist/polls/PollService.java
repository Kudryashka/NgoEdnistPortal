package ua.in.ngo.ednist.polls;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import ua.in.ngo.ednist.polls.dao.Poll;
import ua.in.ngo.ednist.polls.dao.PollAnswer;
import ua.in.ngo.ednist.polls.dao.PollAnswerDAO;
import ua.in.ngo.ednist.polls.dao.PollDAOImpl;

@Scope(WebApplicationContext.SCOPE_APPLICATION)
@Service
public class PollService {

	private PollDAOImpl pollDAO;
	private PollAnswerDAO pollAnswerDAO;
	
	@Resource
	public void setPollDAO(PollDAOImpl pollDAO) {
		this.pollDAO = pollDAO;
	}
	
	@Resource
	public void setPollAnswerDAO(PollAnswerDAO pollAnswerDAO) {
		this.pollAnswerDAO = pollAnswerDAO;
	}
	
	@Transactional
	public List<Poll> listPolls() {
		return pollDAO.listPolls();
	}
	
	@Transactional
	public Poll getPollByAlias(String alias) {
		return pollDAO.getPollByAlias(alias);
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
