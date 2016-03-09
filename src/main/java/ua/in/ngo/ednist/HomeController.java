package ua.in.ngo.ednist;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import static ua.in.ngo.ednist.UriConstants.*;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Selects the home view to render by returning its name.
	 */
	@RequestMapping(value = HOME, method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("message", "home" );
		
		return "home";
	}
	
	@RequestMapping(value = ALL_PROJECTS, method = RequestMethod.GET)
	public String projects(Locale locale, Model model) {
		
		model.addAttribute("message", "projects");
		
		return "home";
	}
	
	@RequestMapping(value = PROJECT, method = RequestMethod.GET)
	public String project(@PathVariable("id") String projectId, Locale locale, Model model) {
		
		model.addAttribute("message", "project " + projectId);
		
		return "home";
	}
	
	@RequestMapping(value = ALL_POLLS, method = RequestMethod.GET)
	public String polls(Locale locale, Model model) {
		
		model.addAttribute("message", "polls");
		
		return "home";
	}
	
	@RequestMapping(value = POLL, method = RequestMethod.GET)
	public String poll(@PathVariable("id") String pollId, Locale locale, Model model) {
		
		model.addAttribute("message", "poll " + pollId);
		
		return "home";
	}
}
