package ua.in.ngo.ednist.self.dao;

import javax.annotation.Resource;

import org.hibernate.NonUniqueResultException;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import ua.in.ngo.ednist.self.SelfInfo;
import ua.in.ngo.ednist.self.SelfInfoDao;

@Repository
public class SelfInfoDaoImpl implements SelfInfoDao {
	
	private static Logger logger = LoggerFactory.getLogger(SelfInfoDaoImpl.class);

	private SessionFactory sessionFactory;
	
	@Resource
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public SelfInfo getSelfInfo() {
		Query query = sessionFactory.getCurrentSession().createQuery("from SelfInfo");
		SelfInfo info = null;
		try {
			info = (SelfInfo) query.uniqueResult();
		} catch (NonUniqueResultException e) {
			logger.error("Few variants of self info found.");
		}
		return info;
	}

	@Override
	public void updateSelfInfo(SelfInfo info) {
		SelfInfo persistedInfo = getSelfInfo();
		if (persistedInfo == null) {
			persistedInfo = new SelfInfoImpl();
			persistedInfo.fill(info);
			sessionFactory.getCurrentSession().persist(persistedInfo);
		} else {
			if (persistedInfo.fill(info)) {
				sessionFactory.getCurrentSession().update(persistedInfo);
			}
		}
	}
}
