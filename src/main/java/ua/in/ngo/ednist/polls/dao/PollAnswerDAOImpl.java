package ua.in.ngo.ednist.polls.dao;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

@Repository
public class PollAnswerDAOImpl implements PollAnswerDAO {

	private static final Logger logger = LoggerFactory.getLogger(PollAnswerDAOImpl.class);
	
	private SessionFactory sessionFactory;
	
	@Resource
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@Override
	public void addAnswer(PollAnswer answer) {
		sessionFactory.getCurrentSession().persist(answer);
		logger.info("Answer successfully saved.");
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<PollAnswer> getPollAnswers(Poll poll) {
		Session session = sessionFactory.getCurrentSession();
		//TODO select polls by poll
		List<PollAnswer> answers = session.createQuery("from PollAnswer").list();
		logger.info("Answer list requested. Poll " + poll.getName());
		return answers;
	}

	@Override
	public int getPollAnswersCount(Poll poll) {
		return getPollAnswers(poll).size();
	}
}
