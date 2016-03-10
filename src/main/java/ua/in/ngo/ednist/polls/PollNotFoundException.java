package ua.in.ngo.ednist.polls;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason="Poll not found")
public class PollNotFoundException extends Exception {

	private static final long serialVersionUID = 4319145793457856899L;
	
	public PollNotFoundException(String id) {
		super(String.format("Poll with id=%s not found.", id));
	}
}
