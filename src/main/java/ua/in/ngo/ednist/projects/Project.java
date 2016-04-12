package ua.in.ngo.ednist.projects;

import java.util.Date;

public interface Project {
	
	public int getId();
	
	public void setId(int id);
	
	public Date getInsertTime();
	
	public void setInsertTime(Date insertTime);
	
	public Date getModifyTime();
	
	public void setModifyTime(Date modifyTime);
	
	public String getUriAlias();
	
	public void setUriAlias(String uriAlias);
	
	public String getName();
	
	public void setName(String name);
	
	public String getDescription();
	
	public void setDescription(String description);
	
//	public ProjectStatus getStatus();
//	
//	public void setStatus(ProjectStatus status);
	
	/**
	 * Fill state via the project and return itself 
	 */
	public Project fill(Project project);
}
