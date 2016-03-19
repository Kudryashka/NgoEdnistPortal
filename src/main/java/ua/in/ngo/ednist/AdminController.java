package ua.in.ngo.ednist;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ua.in.ngo.ednist.polls.PollAnswerService;
import ua.in.ngo.ednist.polls.PollService;
import ua.in.ngo.ednist.polls.dao.Poll;
import ua.in.ngo.ednist.polls.dao.PollAnswer;

@Controller
@RequestMapping("admin")
public class AdminController {

	private static Logger logger = LoggerFactory.getLogger(AdminController.class);
	
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
	
	@RequestMapping(method = RequestMethod.GET)
	public String admin() {
		logger.info("Administration panel requested.");
		
		return "admin";
	}
	
	@RequestMapping(value="/polls/{alias}/answers", method = RequestMethod.GET)
	public String pollAnswers(@PathVariable("alias") String alias, Model model) {
		Poll poll = pollService.getPollByAlias(alias);
		List<PollAnswer> answers = pollAnswerService.getPollAnswers(poll);
		
		model.addAttribute("poll", poll);
		model.addAttribute("answers", answers);
		
		return "admin_poll_answers";
	}
}
