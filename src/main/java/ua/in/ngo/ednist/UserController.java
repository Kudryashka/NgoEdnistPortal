package ua.in.ngo.ednist;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ua.in.ngo.ednist.polls.PollNotFoundException;
import ua.in.ngo.ednist.polls.PollService;
import ua.in.ngo.ednist.polls.dao.Poll;
import ua.in.ngo.ednist.polls.dao.PollAnswer;
import ua.in.ngo.ednist.polls.forms.PollAnswerForm;
import ua.in.ngo.ednist.self.SelfService;
import ua.in.ngo.ednist.util.Link;

/**
 * Handles requests for the application home page.
 */
@Controller
public class UserController {
	
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	private static List<Link> navigationMenu = new ArrayList<>();
	static {
		navigationMenu.add(new Link("/", "Home"));
		navigationMenu.add(new Link("/projects", "Projects"));
		navigationMenu.add(new Link("/polls", "Polls"));
		navigationMenu.add(new Link("/about-us", "About us"));
	}
	
	private SelfService selfService;
	private PollService pollService;
	
	@Resource
	public void setSelfService(SelfService selfService) {
		this.selfService = selfService;
	}

	@Resource
	public void setPollService(PollService pollService) {
		this.pollService = pollService;
	}
	
	/**
	 * Selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Http. Welcome home! The client locale is {}.", locale);

		model.addAttribute("title", "Home page");
		model.addAttribute("navigationMenu", navigationMenu);
		model.addAttribute("selfInfo", selfService.getSelfInfoForm());
		
		return "user_home";
	}
	
	@RequestMapping(value = "/about-us", method = RequestMethod.GET)
	public String aboutUs(Model model) {
		logger.info("About-Us view requested.");
		model.addAttribute("title", "About us");
		model.addAttribute("navigationMenu", navigationMenu);
		model.addAttribute("selfInfo", selfService.getSelfInfoForm());
		return "user_about_us";
	}
	
	@RequestMapping(value = "/projects", method = RequestMethod.GET)
	public String projects(Locale locale, Model model) {
		logger.info("Http. All projects requested.");
		model.addAttribute("title", "Projects list");
		model.addAttribute("navigationMenu", navigationMenu);
		model.addAttribute("selfInfo", selfService.getSelfInfoForm());
		return "projects";
	}
	
	@RequestMapping(value = "/projects/{id}", method = RequestMethod.GET)
	public String project(@PathVariable("id") String projectId, Locale locale, Model model) {
		logger.info("Http. A project requested. Id: " + projectId);
		
		model.addAttribute("projectsUri", "/projects");
		model.addAttribute("projectId", projectId);
		
		return "project";
	}
	
	@RequestMapping(value = "/polls", method = RequestMethod.GET)
	public String polls(Locale locale, Model model) {
		logger.info("Http. All polls requested.");
		
		List<Poll> polls = pollService.listPolls();
		
		model.addAttribute("title", "Polls list");
		model.addAttribute("navigationMenu", navigationMenu);
		model.addAttribute("selfInfo", selfService.getSelfInfoForm());
		model.addAttribute("polls", polls);
		
		return "polls";
	}
	
	@RequestMapping(value = "/polls/{id}", method = RequestMethod.GET)
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
	
	@RequestMapping(value = "/polls/{id}", method = RequestMethod.POST)
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
		pollService.addAnswer(answer);
		
		return "redirect:/polls/"+pollId+"/thanks";
	}
	
	@RequestMapping(value = "/polls/{alias}/thanks", method = RequestMethod.GET)
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
