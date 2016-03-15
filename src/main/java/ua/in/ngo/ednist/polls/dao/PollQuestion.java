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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

@Entity
@Table(name = "poll_question")
public class PollQuestion {

	public static enum AnswerType {
		text,
		single,
		multy
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false, unique = true)
	private int id;
	
	@Column(name = "insert_time", nullable = false)
	private Date insertTime;
	
	@Column(name = "last_modify_time", nullable = false)
	private Date lastModifyTime;

	@Column(name = "name", length = 300)
	private String name;
	
	@Column(name = "description", columnDefinition = "TEXT")
	private String description;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "answer_type", nullable = false)
	private AnswerType answerType;
	
	@ManyToOne
	@JoinColumn(name = "block_id", nullable = false)
	private PollBlock parentBlock;
	
	@Column(name = "block_order", nullable = false)
	private int blockOrder;
	
	@OneToMany(mappedBy = "parentQuestion", fetch = FetchType.EAGER)
	@OrderBy("questionOrder")
	private List<PollQuestionVariant> questionVariants;
	
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

	public AnswerType getAnswerType() {
		return answerType;
	}

	public void setAnswerType(AnswerType answerType) {
		this.answerType = answerType;
	}

	public PollBlock getParentBlock() {
		return parentBlock;
	}

	public void setParentBlock(PollBlock parentBlock) {
		this.parentBlock = parentBlock;
	}

	public int getBlockOrder() {
		return blockOrder;
	}

	public void setBlockOrder(int blockOrder) {
		this.blockOrder = blockOrder;
	}

	public List<PollQuestionVariant> getQuestionVariants() {
		return questionVariants;
	}

	public void setQuestionVariants(List<PollQuestionVariant> questionVariants) {
		this.questionVariants = questionVariants;
	}
}
