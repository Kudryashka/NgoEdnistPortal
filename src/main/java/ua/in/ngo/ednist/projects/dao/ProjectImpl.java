package ua.in.ngo.ednist.projects.dao;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import ua.in.ngo.ednist.projects.Project;
import ua.in.ngo.ednist.util.CompareUtil;

@Entity(name = "Project")
@Table(name = "project")
public class ProjectImpl implements Project {
	
	public ProjectImpl() {
		//should be available for hibernate
	}
	
	public ProjectImpl(Project project) {
		fill(project);
		Date time = new Date();
		insertTime = time;
		modifyTime = time;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false, unique = true)
	private int id;
	
	@Column(name = "insert_time", nullable = false)
	private Date insertTime;
	
	@Column(name = "last_modify_time", nullable = false)
	private Date modifyTime;
	
	@Column(name = "uri_alias", nullable = false, unique = true, length = 100)
	private String uriAlias;
	
	@Column(name = "name", nullable = false, unique = true, length = 150)
	private String name;
	
	@Column(name = "description", columnDefinition = "TEXT")
	private String description;
	
	//TODO
//	@Enumerated(EnumType.STRING)
//	@Column
//	private ProjectStatus status;
	
	@Override
	public int getId() {
		return id;
	}

	@Override
	public void setId(int id) {
		this.id = id;
	}

	@Override
	public Date getInsertTime() {
		return insertTime;
	}

	@Override
	public void setInsertTime(Date insertTime) {
		this.insertTime = insertTime;
	}

	@Override
	public Date getModifyTime() {
		return modifyTime;
	}

	@Override
	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}

	@Override
	public String getUriAlias() {
		return uriAlias;
	}

	@Override
	public void setUriAlias(String uriAlias) {
		this.uriAlias = uriAlias;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String getDescription() {
		return description;
	}

	@Override
	public void setDescription(String description) {
		this.description = description;
	}

//	@Override
//	public ProjectStatus getStatus() {
//		return status;
//	}
//
//	@Override
//	public void setStatus(ProjectStatus status) {
//		this.status = status;
//	}

	@Override
	public Project fill(Project project) {
		String uriAlias = project.getUriAlias();
		String name = project.getName();
		String description = project.getDescription();
		
		boolean isStateChanged = !CompareUtil.equalOrBothNull(
				new Object[]{uriAlias, name, description}, 
				new Object[]{this.uriAlias, this.name, this.description});
		
		if (isStateChanged) {
			this.modifyTime = new Date();
			this.uriAlias = project.getUriAlias();
			this.name = project.getName();
			this.description = project.getDescription();
		}
		
		return this;
	}
}
