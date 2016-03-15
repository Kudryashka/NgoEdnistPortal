package ua.in.ngo.ednist.polls.dao;

import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "poll_answer")
public class PollAnswer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false, unique = true)
	private int id;
	
	@Column(name = "insert_time", nullable = false)
	private Date insertTime;
	
	@Column(name = "last_modify_time", nullable = false)
	private Date lastModifyTime;
	
	@ManyToOne // unidirectional
	@JoinColumn(name = "poll_id", nullable = false)
	private Poll poll;
	
	@OneToMany(mappedBy = "parentPollAnswer", fetch = FetchType.EAGER)
	private Set<PollQuestionAnswer> pollQuestionAnswers;

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

	public Poll getPoll() {
		return poll;
	}

	public void setPoll(Poll poll) {
		this.poll = poll;
	}

	public Set<PollQuestionAnswer> getPollQuestionAnswers() {
		return pollQuestionAnswers;
	}

	public void setPollQuestionAnswers(Set<PollQuestionAnswer> pollQuestionAnswers) {
		this.pollQuestionAnswers = pollQuestionAnswers;
	}
}
