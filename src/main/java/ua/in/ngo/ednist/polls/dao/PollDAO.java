package ua.in.ngo.ednist.polls.dao;

import java.util.List;

public interface PollDAO {

	/**
	 * Add new poll to persistent.
	 */
	void addPoll(Poll poll);
	
	/**
	 * Update exists poll.
	 */
	void updatePoll(Poll poll);
	
	/**
	 * List all available polls.
	 */
	List<Poll> listPolls(); 
	
	/**
	 * Get poll with specified URI alias.
	 */
	Poll getPollByAlias(String alias);
	
	/**
	 * Get poll with specified id.
	 */
	Poll getPollById(int id);
	
	/**
	 * Delete poll with specified id.
	 */
	void deletePoll(int id);
	
}
