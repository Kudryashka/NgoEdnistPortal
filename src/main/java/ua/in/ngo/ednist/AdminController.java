package ua.in.ngo.ednist;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import ua.in.ngo.ednist.projects.Project;
import ua.in.ngo.ednist.projects.ProjectNotFoundException;
import ua.in.ngo.ednist.projects.ProjectService;
import ua.in.ngo.ednist.projects.form.FormProject;
import ua.in.ngo.ednist.self.SelfService;
import ua.in.ngo.ednist.self.form.SelfInfoForm;
import ua.in.ngo.ednist.upload.FileUploadService;
import ua.in.ngo.ednist.util.Link;

@Controller
@RequestMapping("admin")
public class AdminController {

	private static Logger logger = LoggerFactory.getLogger(AdminController.class);
	
	private static final Link logoutLink = new Link("/logout", "Logout");
	private static List<Link> adminNavigationMenu = new ArrayList<>(); //<path, name>
	static {
		adminNavigationMenu.add(new Link("/admin", "Help"));
		adminNavigationMenu.add(new Link("/admin/self", "Self Info"));
		adminNavigationMenu.add(new Link("/admin/polls", "Polls"));
		adminNavigationMenu.add(new Link("/admin/projects", "Projects"));
		adminNavigationMenu.add(new Link("/", "Exit panel"));
	}
	
	private SelfService selfService;
	private PollService pollService;
	private ProjectService projectService;
	private FileUploadService fileUploadService;
	
	@Resource
	public void setSelfService(SelfService selfService) {
		this.selfService = selfService;
	}
	
	@Resource
	public void setPollService(PollService pollService) {
		this.pollService = pollService;
	}
	
	@Resource
	public void setProjectService(ProjectService projectService) {
		this.projectService = projectService;
	}

	@Resource
	public void setFileUploadService(FileUploadService fileUploadService) {
		this.fileUploadService = fileUploadService;
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
		
		return "admin/admin_home";
	}
	
	/*
	 * SELF MAPPING
	 * 
	 * /admin/self 		:: admin_self_info
	 * /admin/self/edit :: admin_self_editor
	 */
	
	@RequestMapping(value = "/self", method = RequestMethod.GET)
	public String selfInfo(Model model) {
		logger.info("Self info called.");
		
		List<Link> controls = new ArrayList<>();
		controls.add(new Link("/admin/self/edit", "Edit"));
		controls.add(logoutLink);
		
		model.addAttribute("title", "Self info page");
		model.addAttribute("navigationMenu", adminNavigationMenu);
		model.addAttribute("controls", controls);
		model.addAttribute("pageName", "Self info");
		model.addAttribute("selfInfo", selfService.getSelfInfoForm());
		
		return "admin/admin_self_info";
	}
	
	@RequestMapping(value = "/self/edit", method = RequestMethod.GET)
	public String selfInfoEdit(Model model) {
		logger.info("Self info modification called.");
		
		List<Link> controls = new ArrayList<>();
		controls.add(new Link("/admin/self", "Cancel"));
		controls.add(logoutLink);
		
		model.addAttribute("title", "Self info edit page");
		model.addAttribute("controls", controls);
		model.addAttribute("pageName", "Self info editor");
		model.addAttribute("selfInfo", selfService.getSelfInfoForm());
		
		return "admin/admin_self_editor";
	}
	
	@RequestMapping(value = "/self/edit", method = RequestMethod.POST)
	public String selfInfoSubmitEdit(@ModelAttribute("selfInfo") SelfInfoForm selfInfo, Model model) {
		logger.info("Self info update submitted.");
		selfService.updateSelfInfo(selfInfo);
		return "redirect:/admin/self";
	}
	
	/*
	 * POLLS MAPPINGS
	 * 
	 * /admin/polls 				:: admin_polls_list
	 * /admin/polls/new 			:: admin_polls_editor
	 * /admin/polls/{alias}/answers :: admin_polls_answers
     * /admin/polls/{alias}/edit 	:: admin_polls_editor
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
		
		return "admin/admin_polls_list";
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
		
		return "admin/admin_polls_answers";
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
		
		return "admin/admin_polls_editor";
	}
	
	/*
	 * PROJECTS MAPPINGS
	 * 
	 * /admin/projects 					:: admin_projects_list
	 * /admin/projects/new 				:: admin_projects_editor
	 * /admin/projects/{alias}/edit 	:: admin_projects_editor
	 * /admin/projects/{alias}/preview 	:: admin_projects_preview
	 */
	
	@RequestMapping(value = "/projects", method = RequestMethod.GET)
	public String projectsList(Model model) {
		logger.info("Administration panel. Projects list.");
		
		List<Link> controls = new ArrayList<>();
		controls.add(new Link("/admin/projects/new", "Create new project"));
		controls.add(logoutLink);
		
		List<Project> projects = projectService.allProjects();
		
		model.addAttribute("title", "Projects list page");
		model.addAttribute("navigationMenu", adminNavigationMenu);
		model.addAttribute("controls", controls);
		model.addAttribute("pageName", "Projects list");
		model.addAttribute("projects", projects);
		
		return "admin/admin_projects_list";
	}
	
	@RequestMapping(value = "/projects/new", method = RequestMethod.GET)
	public String projectsNew(Model model) {
		logger.info("New project create form requested.");
		return prepareProjectsEditor(model, new FormProject());
	}
	
	@RequestMapping(value = "/projects/new", method = RequestMethod.POST)
	public String projectsSubmitNew(@ModelAttribute("project") FormProject project) {
		logger.info("New project submitted by user.");
		
		Project persistedProject = projectService.saveNewProject(project);
		
		String imgUploadLocation = persistedProject.getId() + File.separator + "img"; 
		String resolvedUploadLocation = fileUploadService.upload("project", imgUploadLocation, "img.png", project.getImgFile());
		
		return "redirect:/admin/projects";
	}
	
	@RequestMapping(value = "/projects/{alias}/edit", method = RequestMethod.GET)
	public String projectsEdit(@PathVariable("alias") String alias, Model model) 
			throws ProjectNotFoundException {
		logger.info("Project edit form requested.");
		
		Project persistedProject = projectService.getProjectByAlias(alias);
		if (persistedProject == null) {
			throw new ProjectNotFoundException(alias);
		}

		model.addAttribute("imgLocation", "/resources/project/" + persistedProject.getId() + File.separator + "img" + File.separator + "img.png");
		return prepareProjectsEditor(model, persistedProject);
	}
	
	@RequestMapping(value = "/projects/{alias}/edit", method = RequestMethod.POST)
	public String projectsSubmitEdit(@ModelAttribute FormProject project, @PathVariable("alias") String alias) 
			throws ProjectNotFoundException {
		logger.info("Project edit form submitted by user.");
		
		Project persistedProject = projectService.getProjectByAlias(alias);
		if (persistedProject == null) {
			throw new ProjectNotFoundException(alias);
		}
		persistedProject.fill(project);		
		projectService.editProject(persistedProject);
		
		String imgUploadLocation = persistedProject.getId() + File.separator + "img"; 
		String resolvedUploadLocation = fileUploadService.upload("project", imgUploadLocation, "img.png", project.getImgFile());
		
		return "redirect:/admin/projects";
	}
	
	private String prepareProjectsEditor(Model model, Project project) {
		if (project != null) {
			project = new FormProject().fill(project); 
		}
		
		List<Link> controls = new ArrayList<>();
		controls.add(new Link("/admin/projects", "Cancel"));
		controls.add(logoutLink);
		
		model.addAttribute("title", "Project editor");
		model.addAttribute("controls", controls);
		model.addAttribute("project", project);
		model.addAttribute("pageName", "Project editor");
		
		return "admin/admin_projects_editor";
	}
}
