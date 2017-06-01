package pub.strawhat.luffy.controller;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {

	@RequestMapping("/")
	@RequiresPermissions("UserService:Query")
	public String defaultHome() {
		return "index";
	}

	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String home() {
		return "index";
	}

	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String home1() {
		return "home";
	}
 
}
