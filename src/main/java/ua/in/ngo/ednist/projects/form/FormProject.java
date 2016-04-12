package ua.in.ngo.ednist.projects.form;

import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

import ua.in.ngo.ednist.projects.Project;

public class FormProject implements Project {
	
	private int id;
	private Date insertTime;
	private Date modifyTime;
	private String uriAlias;
	private String name;
	private String description;
	private MultipartFile imgFile;

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
	
	public MultipartFile getImgFile() {
		return imgFile;
	}

	public void setImgFile(MultipartFile imgFile) {
		this.imgFile = imgFile;
	}

	@Override
	public Project fill(Project project) {
		this.id = project.getId();
		this.insertTime = project.getInsertTime();
		this.modifyTime = project.getModifyTime();
		this.uriAlias = project.getUriAlias();
		this.name = project.getName();
		this.description = project.getDescription();
		return this;
	}
}
