package in.nit.raghu.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import in.nit.raghu.constants.UserRoles;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	private UserDetailsService userDetailsService;

      protected void configure(AuthenticationManagerBuilder auth) throws Exception{
    	   auth.
    	   userDetailsService(userDetailsService)
    	   .passwordEncoder(passwordEncoder);
    	   
      }
      
      protected void configure(HttpSecurity http) throws Exception{
    	  http.authorizeRequests()
    	    .antMatchers("/patient/register","/patient/save").permitAll()
  		    .antMatchers("/spec/**").hasAuthority(UserRoles.ADMIN.name())
  		    .antMatchers("/doctor/**").hasAuthority(UserRoles.ADMIN.name())
  		    .antMatchers("/appointment/register","/appointment/save","/appointment/all").hasAuthority(UserRoles.ADMIN.name())
  		    .antMatchers("/appointment/view","/appointment/viewSlot").hasAuthority(UserRoles.PATIENT.name())
            .antMatchers("/user/login","/login").permitAll()
            
  		 .anyRequest().authenticated()

  		 .and()
  		 .formLogin()
  		  //show login page
  		 .loginPage("/user/login") 
  		 .loginProcessingUrl("/login")  //POST ->do login
  		 .defaultSuccessUrl("/spec/setup",true)
  		 
  		 //if login failed 
  		 .failureUrl("/user/login?error=true")   

  		 .and()
  		 .logout()
  		  // for logout
    	 .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
    	 //on logout 
    	 .logoutSuccessUrl("/user/login?logout=true")
    	 ;
      }

	
}
