package ua.in.ngo.ednist.projects;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Project not found")
public class ProjectNotFoundException extends Exception {

	private static final long serialVersionUID = -3940975481010224335L;

	public ProjectNotFoundException(String alias) {
		super(String.format("Project with alias='%s' not found", alias));
	}
}
