package ua.in.ngo.ednist.polls.dao;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "poll_question_variant")
public class PollQuestionVariant {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false, unique = true)
	private int id;
	
	@Column(name = "insert_time", nullable = false)
	private Date insertTime;
	
	@Column(name = "last_modify_time", nullable = false)
	private Date lastModifyTime;
	
	@Column(name = "name", nullable = false, length = 150)
	private String name;
	
	@Column(name = "var_value", nullable = false, length = 150)
	private String varValue;
	
	@Column(name = "on_choose_relative_info", columnDefinition = "TEXT")
	private String onChooseRelativeInfo;
	
	@ManyToOne
	@JoinColumn(name = "question_id", nullable = false)
	private PollQuestion parentQuestion;
	
	@Column(name = "question_order", nullable = false)
	private int questionOrder;

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

	public String getVarValue() {
		return varValue;
	}

	public void setVarValue(String varValue) {
		this.varValue = varValue;
	}

	public String getOnChooseRelativeInfo() {
		return onChooseRelativeInfo;
	}

	public void setOnChooseRelativeInfo(String onChooseRelativeInfo) {
		this.onChooseRelativeInfo = onChooseRelativeInfo;
	}

	public PollQuestion getParentQuestion() {
		return parentQuestion;
	}

	public void setParentQuestion(PollQuestion parentQuestion) {
		this.parentQuestion = parentQuestion;
	}

	public int getQuestionOrder() {
		return questionOrder;
	}

	public void setQuestionOrder(int questionOrder) {
		this.questionOrder = questionOrder;
	}
}
