package ua.in.ngo.ednist.polls.forms;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import ua.in.ngo.ednist.polls.dao.Poll;
import ua.in.ngo.ednist.polls.dao.PollAnswer;
import ua.in.ngo.ednist.polls.dao.PollBlock;
import ua.in.ngo.ednist.polls.dao.PollQuestion;
import ua.in.ngo.ednist.polls.dao.PollQuestionAnswer;

public class PollAnswerForm {

	private Map<Integer, Block> blocks; // key is block id

	public Map<Integer, Block> getBlocks() {
		return blocks;
	}

	public void setBlocks(Map<Integer, Block> blocks) {
		this.blocks = blocks;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("PollAnwerForm: ").append(super.toString()).append("\n ")
			.append("Blocks: ").append(blocks);
		return builder.toString();
	}
	
	public PollAnswer toPollAnswer(Poll poll) {
		Date time = new Date();
		PollAnswer pollAnswer = new PollAnswer();
		pollAnswer.setPoll(poll);
		pollAnswer.setInsertTime(time);
		pollAnswer.setLastModifyTime(time);
		Set<PollQuestionAnswer> pollQuestionAnswers = new HashSet<PollQuestionAnswer>();
		pollAnswer.setPollQuestionAnswers(pollQuestionAnswers);
		//initialize question answers
		List<PollBlock> pollBlocks = poll.getPollBlocks();
		for (PollBlock pollBlock : pollBlocks) {
			Block answerFormBlock = blocks.get(Integer.valueOf(pollBlock.getId()));
			if (answerFormBlock == null) {
				continue;
			}
			List<PollQuestion> pollQuestions = pollBlock.getBlockQuestions();
			for (PollQuestion question : pollQuestions) {
				// search for answer value for every question in every block
				String answerValue = null;
				String additionalInput = null;
				Integer questionIdKey = question.getId();
				switch(question.getAnswerType()) {
				case text:
					if (answerFormBlock.textTypeAnswers != null) {
						answerValue = answerFormBlock.textTypeAnswers.get(questionIdKey);
					}
					break;
				case single:
					if (answerFormBlock.singleTypeAnswers != null) {
						answerValue = answerFormBlock.singleTypeAnswers.get(questionIdKey);
						additionalInput = answerFormBlock.textTypeAnswers == null ? null : answerFormBlock.textTypeAnswers.get(questionIdKey);
					}
					break;
				case multy:
					if (answerFormBlock.multyTypeAnswers != null) {
						answerValue = answerFormBlock.multyTypeAnswers.get(questionIdKey);
						additionalInput = answerFormBlock.textTypeAnswers == null ? null : answerFormBlock.textTypeAnswers.get(questionIdKey);
					}
					break;
				}
				// initialize answer object if answer value not null
				if (answerValue != null) {
					PollQuestionAnswer pqa = new PollQuestionAnswer();
					pqa.setAnswerValue(answerValue);
					pqa.setAdditionalInput(additionalInput);
					pqa.setInsertTime(time);
					pqa.setLastModifyTime(time);
					pqa.setParentPollAnswer(pollAnswer);
					pqa.setRelativePollQuestion(question);
					pollQuestionAnswers.add(pqa);
				}
			}
		}
		return pollAnswer;
	}
	
	public static class Block {
	
		private Map<Integer, String> multyTypeAnswers; // key is question id
		private Map<Integer, String> singleTypeAnswers; // key is question id
		private Map<Integer, String> textTypeAnswers; // key is question id

		public Map<Integer, String> getMultyTypeAnswers() {
			return multyTypeAnswers;
		}

		public void setMultyTypeAnswers(Map<Integer, String> multyTypeAnswers) {
			this.multyTypeAnswers = multyTypeAnswers;
		}

		public Map<Integer, String> getSingleTypeAnswers() {
			return singleTypeAnswers;
		}

		public void setSingleTypeAnswers(Map<Integer, String> singleTypeAnswers) {
			this.singleTypeAnswers = singleTypeAnswers;
		}

		public Map<Integer, String> getTextTypeAnswers() {
			return textTypeAnswers;
		}

		public void setTextTypeAnswers(Map<Integer, String> textTypeAnswers) {
			this.textTypeAnswers = textTypeAnswers;
		}

		@Override
		public String toString() {
			StringBuilder builder = new StringBuilder();
			builder.append("Block: ").append(super.toString()).append("\n ")
				.append("Multy type answers: ").append(multyTypeAnswers).append("\n ")
				.append("Single type answers: ").append(singleTypeAnswers).append("\n ")
				.append("Text type answers: ").append(textTypeAnswers);
			return builder.toString();
		}
	}
}
