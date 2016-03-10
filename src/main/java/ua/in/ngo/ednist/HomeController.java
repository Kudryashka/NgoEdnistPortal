package ua.in.ngo.ednist;

import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ua.in.ngo.ednist.polls.Poll;
import ua.in.ngo.ednist.polls.PollService;

import static ua.in.ngo.ednist.UriConstants.*;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	private PollService pollService;
	
	@Autowired
	public void setPollService(PollService pollService) {
		this.pollService = pollService;
	}
	
	/**
	 * Selects the home view to render by returning its name.
	 */
	@RequestMapping(value = HOME, method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Http. Welcome home! The client locale is {}.", locale);
		
		model.addAttribute("projectsUri", ALL_PROJECTS);
		model.addAttribute("pollsUri", ALL_POLLS);
		
		return "home";
	}
	
	@RequestMapping(value = ALL_PROJECTS, method = RequestMethod.GET)
	public String projects(Locale locale, Model model) {
		logger.info("Http. All projects requested.");
		
		model.addAttribute("homeUri", HOME);
		
		return "projects";
	}
	
	@RequestMapping(value = PROJECT, method = RequestMethod.GET)
	public String project(@PathVariable("id") String projectId, Locale locale, Model model) {
		logger.info("Http. A project requested. Id: " + projectId);
		
		model.addAttribute("projectsUri", ALL_PROJECTS);
		model.addAttribute("projectId", projectId);
		
		return "project";
	}
	
	@RequestMapping(value = ALL_POLLS, method = RequestMethod.GET)
	public String polls(Locale locale, Model model) {
		logger.info("Http. All polls requested.");
		
		List<Poll> polls = pollService.listPolls();
		
		model.addAttribute("homeUri", HOME);
		model.addAttribute("polls", polls);
		
		return "polls";
	}
	
	@RequestMapping(value = POLL, method = RequestMethod.GET)
	public String poll(@PathVariable("id") String pollId, Locale locale, 
			Model model) throws Exception {
		logger.info("Http. A poll requested. Id: " + pollId);
		
		model.addAttribute("pollsUri", ALL_POLLS);
		model.addAttribute("pollId", pollId);
		
		return "poll";
	}
}
