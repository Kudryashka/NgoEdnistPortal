package ua.in.ngo.ednist.projects;

import java.util.List;

public interface ProjectDAO {

	/**
	 * Save project on persist level
	 * @return Project that was saved on persistent layer.
	 */
	Project addProject(Project project);
	
	List<Project> allProjects();
	
	Project getProjectByAlias(String alias);
	
	void updateProject(Project project);
}
