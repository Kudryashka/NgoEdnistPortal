package ua.in.ngo.ednist.polls.dao;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

@Entity
@Table(name = "poll_block")
public class PollBlock {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id",nullable = false, unique = true)
	private int id;
	
	@Column(name = "insert_time", nullable = false)
	private Date insertTime;
	
	@Column(name = "last_modify_time", nullable = false)
	private Date lastModifyTime;
	
	@Column(name = "name", length = 150)
	private String name;
	
	@Column(name = "description", columnDefinition = "TEXT")
	private String description;
	
	@ManyToOne
	@JoinColumn(name = "poll_id", nullable = false)
	private Poll parentPoll;
	
	@Column(name = "poll_order", nullable = false)
	private int pollOrder;
	
	@OneToMany(mappedBy = "parentBlock", fetch = FetchType.EAGER)
	@OrderBy("blockOrder")
	private List<PollQuestion> blockQuestions;

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

	public Date getLastModifyTime() {
		return lastModifyTime;
	}

	public void setLastModifyTime(Date lastModifyTime) {
		this.lastModifyTime = lastModifyTime;
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

	public Poll getParentPoll() {
		return parentPoll;
	}

	public void setParentPoll(Poll parentPoll) {
		this.parentPoll = parentPoll;
	}

	public int getPollOrder() {
		return pollOrder;
	}

	public void setPollOrder(int pollOrder) {
		this.pollOrder = pollOrder;
	}

	public List<PollQuestion> getBlockQuestions() {
		return blockQuestions;
	}

	public void setBlockQuestions(List<PollQuestion> blockQuestions) {
		this.blockQuestions = blockQuestions;
	}
}
