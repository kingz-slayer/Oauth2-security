package osp.security.oauth2;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import osp.security.oauth2.model.RoleRepository;
import osp.security.oauth2.model.Roles;
import osp.security.oauth2.model.User;
import osp.security.oauth2.model.UserAccount;
import osp.security.oauth2.model.UserAccountRepository;
import osp.security.oauth2.model.UserRepository;
import osp.security.oauth2.model.UserRole;
import osp.security.oauth2.model.UserRoleRepository;

@RestController
@RequestMapping
public class AuthController {
	
	private UserAccountRepository userInfo;
	
	private UserRepository userRepo;
	
	private UserRoleRepository userRoleRepo;
	
	private RoleRepository roleRepo;
	
	@Autowired
	AuthController(UserAccountRepository userInfo, UserRepository userRepo,
			UserRoleRepository userRoleRepo, RoleRepository roleRepo) {
		this.userInfo = userInfo;
		this.userRepo = userRepo;
		this.userRoleRepo = userRoleRepo;
		this.roleRepo = roleRepo;
	}
	
	@RequestMapping(value="/userInfo" , method = RequestMethod.GET)
	public User getUserInfo(Principal principal){
		return userInfo.findByUserName(principal.getName()).getUser();
	}
	
	
	@RequestMapping(value="/newUser",  method = RequestMethod.POST)
	public User newUserRegistration(@RequestParam String userName, @RequestParam String password, @RequestParam String emailAddress,
			@RequestParam String firstName, @RequestParam String lastName){
		
		if(userRepo.findByEmailAddress(emailAddress) != null)
			throw new UserEmailAlreadyRegisteredException(emailAddress);
		
		if(checkUserName(userName) != null)
			throw new UserNameAlreadyTakenException(userName);
		
		UserAccount userAcc = userInfo.save(new UserAccount(userName, password));
		
		User newUser = userRepo.save(new User(firstName, lastName, emailAddress, userAcc));
		
		 
		 
		 Roles role = roleRepo.findByRoleId("TRAINER");
		 
		 UserRole userRole = new UserRole(newUser, role);
		 
		 userRoleRepo.save(userRole);
		 
		 return newUser;
	}
	
	@RequestMapping(value="/checkUserName", method = RequestMethod.GET)
	public String checkUserNameAvailability(@RequestParam String userName) {
		
		String availabilityStatus = checkUserName(userName);
		
				
		return availabilityStatus == null ? "Available" : "";
	}
	
	private String checkUserName(String userName){
		if(userInfo.findByUserName(userName) != null){
			throw new UserNameAlreadyTakenException(userName);
		}
		
		return null;
	}
}
