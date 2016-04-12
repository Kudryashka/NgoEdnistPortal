package ua.in.ngo.ednist.projects;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

@Scope(WebApplicationContext.SCOPE_APPLICATION)
@Service
public class ProjectService {
	
	private ProjectDAO projectDAO;
	
	@Resource
	public void setProjectDAO(ProjectDAO projectDAO) {
		this.projectDAO = projectDAO;
	}

	@Transactional
	public Project saveNewProject(Project project) {
		return projectDAO.addProject(project);
	}
	
	@Transactional
	public void editProject(Project project) {
		projectDAO.updateProject(project);
	}

	@Transactional
	public List<Project> allProjects() {
		return projectDAO.allProjects();
	}
	
	@Transactional
	public Project getProjectByAlias(String alias) {
		return projectDAO.getProjectByAlias(alias);
	}
}
