package ua.in.ngo.ednist.polls;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

@Repository
public class PollDAO {

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
}
