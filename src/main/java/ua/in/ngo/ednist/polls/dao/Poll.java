package ua.in.ngo.ednist.polls.dao;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;


@Entity
@Table(name="poll")
public class Poll {
	
	public static enum Status {
		draft, 
		active,
		obsolete
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false, unique = true)
	private int id;
	
	@Column(name = "insert_time", nullable = false)
	private Date insertTime;
	
	@Column(name = "last_modify_time", nullable = false)
	private Date modifyTime;
	
	@Column(name = "name", nullable = false, length = 150)
	private String name;
	
	@Column(name = "description", columnDefinition = "TEXT")
	private String description;
	
	@Column(name = "uri_alias", nullable = false, unique = true, length = 100)
	private String uriAlias;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "status", nullable = false)
	private Status status;
	
	@Column(name = "poll_start_time")
	private Date pollStartTime;
	
	@Column(name = "poll_obsolete_time")
	private Date pollObsoleteTime;
	
	/**
	 * TODO correlation to project after project entity description
	 */
	@Column(name = "corr_project_id")
	private Integer corrProjectId;

	@OneToMany(mappedBy = "parentPoll", fetch = FetchType.EAGER)
	@OrderBy("pollOrder")
	private List<PollBlock> pollBlocks;

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

	public Date getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getUriAlias() {
		return uriAlias;
	}

	public void setUriAlias(String uriAlias) {
		this.uriAlias = uriAlias;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Date getPollStartTime() {
		return pollStartTime;
	}

	public void setPollStartTime(Date pollStartTime) {
		this.pollStartTime = pollStartTime;
	}

	public Date getPollObsoleteTime() {
		return pollObsoleteTime;
	}

	public void setPollObsoleteTime(Date pollObsoleteTime) {
		this.pollObsoleteTime = pollObsoleteTime;
	}

	public Integer getCorrProjectId() {
		return corrProjectId;
	}

	public void setCorrProjectId(Integer corrProjectId) {
		this.corrProjectId = corrProjectId;
	}
	
	public List<PollBlock> getPollBlocks() {
		return pollBlocks;
	}

	public void setPollBlocks(List<PollBlock> pollBlocks) {
		this.pollBlocks = pollBlocks;
	}
}
