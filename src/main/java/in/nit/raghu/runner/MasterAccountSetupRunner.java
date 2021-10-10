package in.nit.raghu.runner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import in.nit.raghu.constants.UserRoles;
import in.nit.raghu.entity.User;
import in.nit.raghu.service.UserService;
import in.nit.raghu.util.UserUtil;

@Component
public class MasterAccountSetupRunner implements CommandLineRunner {
	
	@Value("${master.user.name}")
	private String displayName;
	
	@Value("${master.user.email}")
	private String username;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserUtil userUtil;

	@Override
	public void run(String... args) throws Exception {
		if(!userService.findByUsername(username).isPresent()) {
			User user = new User();
			user.setDisplayName(displayName);
			user.setUsername(username);
			user.setPassword(userUtil.genPwd());
			user.setRole(UserRoles.ADMIN.name());
			userService.saveUser(user);
			//TODO : EMAIL SERVICE
		}

		
	}

}
