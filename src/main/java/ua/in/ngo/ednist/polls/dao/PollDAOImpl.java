package ua.in.ngo.ednist.polls.dao;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.NonUniqueResultException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

@Repository
public class PollDAOImpl {

	private static final Logger logger = LoggerFactory.getLogger(PollDAOImpl.class);
	
	private SessionFactory sessionFactory;
	
	@Resource
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@SuppressWarnings("unchecked")
	public List<Poll> listPolls() {
		Session session = sessionFactory.getCurrentSession();
		List<Poll> polls = session.createQuery("from Poll").list();
		return polls;
	}
	
	public Poll getPollByAlias(String alias) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from Poll where uriAlias=:alias");
		query.setParameter("alias", alias);
		Poll result = null;
		try {
			result = (Poll) query.uniqueResult();
		} catch (NonUniqueResultException ne) {
			logger.error("Retrive non unique result for poll with alias = " + alias);
		}
		return result;
	}
}
