package ua.in.ngo.ednist;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ua.in.ngo.ednist.polls.PollNotFoundException;
import ua.in.ngo.ednist.polls.PollService;
import ua.in.ngo.ednist.polls.dao.Poll;
import ua.in.ngo.ednist.polls.dao.PollAnswer;
import ua.in.ngo.ednist.util.Link;

@Controller
@RequestMapping("admin")
public class AdminController {

	private static Logger logger = LoggerFactory.getLogger(AdminController.class);
	
	private static final Link logoutLink = new Link("/logout", "Logout");
	private static List<Link> adminNavigationMenu = new ArrayList<>(); //<path, name>
	static {
		adminNavigationMenu.add(new Link("/admin", "Help"));
		adminNavigationMenu.add(new Link("/admin/polls", "Polls"));
		adminNavigationMenu.add(new Link("/admin/projects", "Projects"));
		adminNavigationMenu.add(new Link("/", "Exit panel"));
	}
	
	private PollService pollService;
	
	@Resource
	public void setPollService(PollService pollService) {
		this.pollService = pollService;
	}
	
	//HOME PAGE MAPPING
	
	@RequestMapping(method = RequestMethod.GET)
	public String admin(Model model) {
		logger.info("Administration panel requested.");
		
		List<Link> controls = new ArrayList<>();
		controls.add(logoutLink);
		
		model.addAttribute("title", "Help page");
		model.addAttribute("navigationMenu", adminNavigationMenu);
		model.addAttribute("controls", controls);
		model.addAttribute("pageName", "help");
		
		return "admin_home";
	}
	
	/*
	 * POLLS MAPPINGS
	 * 
	 * /admin/polls 				:: admin_polls_list
	 * /admin/polls/new 			:: admin_polls_new_form
	 * /admin/polls/{alias}/answers :: admin_polls_answers
     * /admin/polls/{alias}/edit 	:: admin_polls_edit_form
	 * /admin/polls/{alias}/preview :: admin_polls_preview
	 */
	
	@RequestMapping(value = "/polls", method = RequestMethod.GET)
	public String pollsList(Model model) {
		logger.info("Administration panel. Polls list.");

		List<Link> controls = new ArrayList<>();
		controls.add(new Link("/admin/polls/new", "Create new poll"));
		controls.add(logoutLink);
		
		List<Poll> pollsToShow = pollService.listPolls();
		List<Integer> answerCounts = new ArrayList<>();
		for (Poll poll : pollsToShow) {
			answerCounts.add(pollService.getAnswersCount(poll));
		}
		
		model.addAttribute("title", "Polls list page");
		model.addAttribute("navigationMenu", adminNavigationMenu);
		model.addAttribute("controls", controls);
		model.addAttribute("pageName", "Polls list");
		model.addAttribute("polls", pollsToShow);
		model.addAttribute("pollAnswerCounts", answerCounts);
		
		return "admin_polls_list";
	}
	
	@RequestMapping(value="/polls/{alias}/answers", method = RequestMethod.GET)
	public String pollAnswers(@PathVariable("alias") String alias, Model model) throws PollNotFoundException {
		logger.info("Administration panel. Poll answers requested.");
		
		List<Link> controls = new ArrayList<>();
		controls.add(logoutLink);
		
		Poll poll = pollService.getPollByAlias(alias);
		if (poll == null) {
			throw new PollNotFoundException(alias);
		}
		List<PollAnswer> answers = pollService.getPollAnswers(poll);
		
		model.addAttribute("title", "Answers");
		model.addAttribute("controls", controls);
		model.addAttribute("poll", poll);
		model.addAttribute("answers", answers);
		
		return "admin_polls_answers";
	}
	
	@RequestMapping(value="/polls/new")
	public String pollsNew(Model model) {
		logger.info("New poll creation requested.");
		return preparePollEditor(model, null);
	}
	
	@RequestMapping(value="/polls/{alias}/edit")
	public String pollsEdit(@PathVariable("alias") String alias, Model model) throws PollNotFoundException {
		logger.info("Poll editing requested.");
		
		Poll poll = pollService.getPollByAlias(alias);
		if (poll == null) {
			throw new PollNotFoundException(alias);
		}
		
		return preparePollEditor(model, poll);
	}
	
	//if poll is null, then new poll creation requested.
	private String preparePollEditor(Model model, Poll poll) {
		List<Link> controls = new ArrayList<>();
		controls.add(new Link("/admin/polls", "Cancel"));
		controls.add(logoutLink);
		
		model.addAttribute("title", "Poll editor");
		model.addAttribute("controls", controls);
		model.addAttribute("poll", poll);
		
		return "admin_polls_editor";
	}
	
	//PROJECTS MAPPINGS
	
	@RequestMapping(value = "/projects", method = RequestMethod.GET)
	public String projectsList(Model model) {
		logger.info("Administration panel. Projects list.");
		
		List<Link> controls = new ArrayList<>();
		controls.add(logoutLink);
		
		//Add projects as list with name 'projects'
		
		model.addAttribute("title", "Projects list page");
		model.addAttribute("navigationMenu", adminNavigationMenu);
		model.addAttribute("controls", controls);
		model.addAttribute("pageName", "Projects list");
		
		return "admin_projects_list";
	}
}
