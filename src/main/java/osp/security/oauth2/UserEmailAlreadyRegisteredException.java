package osp.security.oauth2;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@SuppressWarnings("serial")
@ResponseStatus(HttpStatus.FORBIDDEN)
public class UserEmailAlreadyRegisteredException extends RuntimeException {
	
	public UserEmailAlreadyRegisteredException(String userEmailAddress){
		super("User with " + userEmailAddress + " email address is already registered");
	}
	
}
