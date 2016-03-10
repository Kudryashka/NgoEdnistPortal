package ua.in.ngo.ednist.polls;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="polls")
public class Poll {

	public static enum ViewType {
		unique_page
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false, unique = true)
	private int id;
	
	@Column(name = "insert_time")
	private Date insertTime;
	
	@Column(name = "uri_alias", nullable = false, unique = true, length = 50)
	private String uriAlias;
	
	@Column(name = "name", nullable = false, unique = true, length = 150)
	private String name;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "view_type", nullable = false)
	private ViewType viewType;
	
	@Column(name = "view_name", nullable = false, length = 50)
	private String viewName;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getInsertTime() {
		return insertTime;
	}

	public void setInsertTime(Date insertTime) {
		this.insertTime = insertTime;
	}

	public String getUriAlias() {
		return uriAlias;
	}

	public void setUriAlias(String uriAlias) {
		this.uriAlias = uriAlias;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ViewType getViewType() {
		return viewType;
	}

	public void setViewType(ViewType viewType) {
		this.viewType = viewType;
	}

	public String getViewName() {
		return viewName;
	}

	public void setViewName(String viewName) {
		this.viewName = viewName;
	}
}
