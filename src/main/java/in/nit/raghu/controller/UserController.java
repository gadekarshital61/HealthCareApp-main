package in.nit.raghu.controller;

import java.security.Principal;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import in.nit.raghu.entity.User;
import in.nit.raghu.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService service;
      
	@GetMapping("/login")
	public String showlogin() {
		return "UserLogin";
	}
	
	@GetMapping("/setup")
	public String setup(HttpSession session, Principal p) 
	{

		//read current username
		String username = p.getName();

		//load user object
		User user = service.findByUsername(username).get();

		//store in HttpSession
		session.setAttribute("userOb", user);

		return "UserHome";
	}
	
}
