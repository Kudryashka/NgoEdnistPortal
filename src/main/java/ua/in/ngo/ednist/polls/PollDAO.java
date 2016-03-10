package ua.in.ngo.ednist.polls;

import java.util.List;

import org.hibernate.NonUniqueResultException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

@Repository
public class PollDAO {

	private static final Logger logger = LoggerFactory.getLogger(PollDAO.class);
	
	private SessionFactory sessionFactory;
	
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
