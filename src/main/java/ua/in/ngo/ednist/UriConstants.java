package ua.in.ngo.ednist;

public interface UriConstants {
	
	/**
	 * <b>NGO home URI</b>
	 * <hr>
	 * Actions:
	 * <ol>
	 * <li>GET - get home page</li>
	 * </ol>
	 */
	String HOME = "/";
	
	/**
	 * <b>NGO projects list</b>
	 */
	String ALL_PROJECTS = "/projects";
	
	/**
	 * <b>Exactly one NGO's project with specified <i>id</i></b>  
	 */
	String PROJECT = "/projects/{id}";
	
	/**
	 * <b>NGO polls list</b>
	 * <hr>
	 * Actions:
	 * <ol>
	 * <li>GET - get all polls</li>
	 * <li>POST - add new poll to the list</li>
	 * </ol>
	 */
	String ALL_POLLS = "/polls";
	
	/**
	 * <b>Exactly one NGO's poll with specified <i>id</i></b>
	 * <hr>
	 * Actions:
	 * <ol>
	 * <li>GET - get single poll with the id</li>
	 * <li>POST - add new answer for the poll</li>
	 * </ol>
	 */
	String POLL = "/polls/{id}";
	
	/**
	 * <b>Answers for a poll with specified <i>id</i></b>
	 * <hr>
	 * Actions:
	 * <ol>
	 * <li>GET - get all answers for the poll</li>
	 * </ol>
	 */
	String POLL_ANSWERS = "/polls/{id}/answers";
	
	/**
	 * <b>Thanks page for poll answer</b>
	 * <hr>
	 * Actions:
	 * <ol>
	 * <li>GET - get thanks page</li>
	 * </ol>
	 */
	String POLL_THANKS = "/polls/{alias}/thanks";
}
