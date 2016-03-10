package ua.in.ngo.ednist.polls;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PollService {

	private PollDAO pollDAO;
	
	public void setPollDAO(PollDAO pollDAO) {
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
