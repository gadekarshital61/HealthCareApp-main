package in.nit.raghu.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	private UserDetailsService userDetailsService;

      protected void configure(AuthenticationManagerBuilder auth) throws Exception{
    	  
      }
      
    protected void configure(HttpSecurity http) throws Exception{
    	  
      }
}
