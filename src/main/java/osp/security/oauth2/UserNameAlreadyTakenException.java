package osp.security.oauth2;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@SuppressWarnings("serial")
@ResponseStatus(HttpStatus.FORBIDDEN)
public class UserNameAlreadyTakenException extends RuntimeException {
	
	public UserNameAlreadyTakenException( String userName){
		super( "UserName : " + userName + " already taken by someone else");
	}
}
