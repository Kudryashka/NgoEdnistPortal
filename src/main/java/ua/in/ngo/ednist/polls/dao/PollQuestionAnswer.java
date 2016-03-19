package ua.in.ngo.ednist.polls.dao;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "poll_question_answer")
public class PollQuestionAnswer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false, unique = true)
	private int id;
	
	@Column(name = "insert_time", nullable = false)
	private Date insertTime;
	
	@Column(name = "last_modify_time", nullable = false)
	private Date lastModifyTime;
	
	@ManyToOne
	@JoinColumn(name = "poll_answer_id", nullable = false)
	private PollAnswer parentPollAnswer;
	
	@OneToOne // unidirectional
	@JoinColumn(name = "question_id", nullable = false)
	private PollQuestion relativePollQuestion;
	
	@Column(name = "answer_value", columnDefinition = "TEXT")
	private String answerValue;
	
	@Column(name = "additional_input_value", columnDefinition = "TEXT")
	private String additionalInput;

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

	public PollAnswer getParentPollAnswer() {
		return parentPollAnswer;
	}

	public void setParentPollAnswer(PollAnswer parentPollAnswer) {
		this.parentPollAnswer = parentPollAnswer;
	}

	public PollQuestion getRelativePollQuestion() {
		return relativePollQuestion;
	}

	public void setRelativePollQuestion(PollQuestion relativePollQuestion) {
		this.relativePollQuestion = relativePollQuestion;
	}

	public String getAnswerValue() {
		return answerValue;
	}

	public void setAnswerValue(String answerValue) {
		this.answerValue = answerValue;
	}

	public String getAdditionalInput() {
		return additionalInput;
	}

	public void setAdditionalInput(String additionalInput) {
		this.additionalInput = additionalInput;
	}
}
