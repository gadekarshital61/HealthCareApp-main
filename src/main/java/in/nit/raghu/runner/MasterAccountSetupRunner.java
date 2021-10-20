package in.nit.raghu.runner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import in.nit.raghu.constants.UserRoles;
import in.nit.raghu.entity.User;
import in.nit.raghu.service.UserService;
import in.nit.raghu.util.MyMailUtil;
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
	
	@Autowired
	private MyMailUtil mailUtil ;

	@Override
	public void run(String... args) throws Exception {
		if(!userService.findByUsername(username).isPresent()) {
			String pwd = userUtil.genPwd();
			User user = new User();
			user.setDisplayName(displayName);
			user.setUsername(username);
			user.setPassword(pwd);
			user.setRole(UserRoles.ADMIN.name());
			Long genId  = userService.saveUser(user);
			if(genId!=null)
				new Thread(new Runnable() {
					public void run() {
						String text = "Your uname is " + username +", password is "+ pwd;
						mailUtil.send(username, "ADMIN ADDED", text);
					}
				}).start();
		}
	}	

}
