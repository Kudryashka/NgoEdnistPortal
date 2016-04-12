package ua.in.ngo.ednist.projects.dao;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.persistence.NonUniqueResultException;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import ua.in.ngo.ednist.projects.Project;
import ua.in.ngo.ednist.projects.ProjectDAO;

@Repository
public class ProjectDAOImpl implements ProjectDAO{
	
	private static final Logger logger = LoggerFactory.getLogger(ProjectDAOImpl.class);

	private SessionFactory sessionFactory;
	
	@Resource
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public Project addProject(Project project) {
		if (!(project instanceof ProjectImpl)) {
			project = new ProjectImpl(project);
		}
		sessionFactory.getCurrentSession().persist(project);
		return project;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Project> allProjects() {
		List<ProjectImpl> projectImpls = sessionFactory.getCurrentSession().createQuery("from Project").list();
		List<Project> projects = new ArrayList<>();
		for (ProjectImpl impl : projectImpls) {
			projects.add((Project) impl);
		}
		return projects;
	}

	@Override
	public Project getProjectByAlias(String alias) {
		Query query = sessionFactory.getCurrentSession().createQuery("from Project where uriAlias=:alias");
		query.setParameter("alias", alias);
		Project project = null;
		try {
			project = (Project) query.uniqueResult();
		} catch (NonUniqueResultException e) {
			logger.error("Few projects found for project with alias=" + alias);
		}
		return project;
	}

	@Override
	public void updateProject(Project project) {
		if (!(project instanceof ProjectImpl)) {
			throw new IllegalArgumentException("Unsupported project realization for update operation. Project should be instance of " + ProjectImpl.class.getCanonicalName());
		}
		sessionFactory.getCurrentSession().update(project);
	}
}
