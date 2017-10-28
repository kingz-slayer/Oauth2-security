package osp.security.oauth2.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import osp.security.oauth2.model.UserAccount;
import osp.security.oauth2.model.UserAccountRepository;

@Configuration
public class WebSecurityConfiguration extends GlobalAuthenticationConfigurerAdapter {
	
	@Autowired
	private UserAccountRepository userAccountRepo;
	
	
	@Override
	public void init(AuthenticationManagerBuilder auth) throws Exception {
		// TODO Auto-generated method stub
		auth.userDetailsService(userDetailsService());
	}
	
	
	
	
	@Bean
	UserDetailsService userDetailsService() {
		return new CustomUserDetailsService();
	}
	
	class CustomUserDetailsService implements UserDetailsService {

		@Override
		public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
			// TODO Auto-generated method stub
			UserAccount userAcc = userAccountRepo.findByUserName(userName);
			return new User(userAcc.getUserName(), userAcc.getPassword(),true, true, true, true,
					AuthorityUtils.createAuthorityList("USER", "write"));
			
		}
		
	}
	
	
	 @Configuration
	 public static class WebConfigurationAdapter extends WebSecurityConfigurerAdapter {

	        @Override
	        protected void configure(HttpSecurity http) throws Exception {
	            http
	                .authorizeRequests()
	                    .antMatchers("/newUser", "/checkUserName").permitAll()
	                    .anyRequest().authenticated()/*
	                    .and()
	                    .formLogin()
	                        .loginPage("/login").permitAll()
	                    .and()
	                    .logout().permitAll()*/
	            ;
	            http.csrf().disable();
	        }

	    }
}
