package ua.in.ngo.ednist;

import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ua.in.ngo.ednist.polls.PollAnswerService;
import ua.in.ngo.ednist.polls.PollNotFoundException;
import ua.in.ngo.ednist.polls.PollService;
import ua.in.ngo.ednist.polls.dao.Poll;
import ua.in.ngo.ednist.polls.dao.PollAnswer;
import ua.in.ngo.ednist.polls.forms.PollAnswerForm;

import static ua.in.ngo.ednist.UriConstants.*;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	private PollService pollService;
	private PollAnswerService pollAnswerService;
	
	@Autowired
	public void setPollService(PollService pollService) {
		this.pollService = pollService;
	}
	
	@Autowired
	public void setPollAnswerService(PollAnswerService pollAnswerService) {
		this.pollAnswerService = pollAnswerService;
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
		
		Poll poll = pollService.getPollByAlias(pollId);
		if (poll == null) {
			throw new PollNotFoundException(pollId);
		}
		
		model.addAttribute("poll", poll);
		model.addAttribute("pollAnswerForm", new PollAnswerForm());
		return "poll_page";
	}
	
	@RequestMapping(value = POLL, method = RequestMethod.POST)
	public String poll(@PathVariable("id") String pollId,
			@ModelAttribute PollAnswerForm pollAnswerForm, 
			Locale locale, Model model) throws Exception {
		logger.info(String.format("Http. An answer for a poll with id=%s received.", pollId));
		
		Poll poll = pollService.getPollByAlias(pollId);
		if (poll == null) {
			throw new PollNotFoundException(pollId);
		}
		
		PollAnswer answer = pollAnswerForm.toPollAnswer(poll);
		logger.info("answers size: " + answer.getPollQuestionAnswers().size());
		pollAnswerService.addAnswer(answer);
		
		return "redirect:/polls/"+pollId+"/thanks";
	}
	
	@RequestMapping(value = POLL_THANKS, method = RequestMethod.GET)
	public String pollSubmitThanks(@PathVariable("alias") String alias, 
			Model model) throws Exception {
		logger.info("Thanks page requested for poll with alias: " + alias);
		
		Poll poll = pollService.getPollByAlias(alias);
		if (poll == null) {
			throw new PollNotFoundException(alias);
		}
		model.addAttribute("poll", poll);
		
		return "poll_thanks";
	}
	
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpServletRequest request, HttpServletResponse response) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null) {
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}
		return "redirect: /";
	}
	
	@RequestMapping(value = "/Access_Denied", method = RequestMethod.GET)
	public String accessDenied() {
		return "accessDenied";
	}
}
