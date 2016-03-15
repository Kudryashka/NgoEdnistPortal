package ua.in.ngo.ednist.polls;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ua.in.ngo.ednist.polls.dao.Poll;
import ua.in.ngo.ednist.polls.dao.PollDAOImpl;

@Service
public class PollService {

	private PollDAOImpl pollDAO;
	
	public void setPollDAO(PollDAOImpl pollDAO) {
		this.pollDAO = pollDAO;
	}
	
	@Transactional
	public List<Poll> listPolls() {
		return pollDAO.listPolls();
	}
	
	@Transactional
	public Poll getPollByAlias(String alias) {
		return pollDAO.getPollByAlias(alias);
	}
}
