package ua.in.ngo.ednist;

public interface UriConstants {

	/*
	 * HTTP URI CONSTANTS
	 */
	
	/**
	 * NGO home page HTTP URI
	 */
	String HOME = "/";
	
	/**
	 * NGO projects list
	 */
	String ALL_PROJECTS = "/projects";
	
	/**
	 * Exactly one NGO's project with specified <i>id</i>  
	 */
	String PROJECT = "/project/{id}";
	
	/**
	 * NGO polls list
	 */
	String ALL_POLLS = "/polls";
	
	/**
	 * Exactly one NGO's poll with specified <i>id</id>
	 */
	String POLL = "/poll/{id}";
}
