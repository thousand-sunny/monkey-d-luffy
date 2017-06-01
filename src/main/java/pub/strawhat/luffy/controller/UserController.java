package pub.strawhat.luffy.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import pub.strawhat.luffy.druid.DruidConfiguration;
import pub.strawhat.luffy.model.sys.UserVO;
import pub.strawhat.luffy.service.UserService;

@Controller
@RequestMapping("/users")
public class UserController {

	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private UserService userService;

	@ResponseBody
	@RequestMapping
	@RequiresPermissions("UserService:Query")
	public UserVO findUserByName(String username) {
		return userService.findUserByName("luffy");
	}

	@ResponseBody
	@RequiresPermissions("UserService:Add")
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ResponseEntity<Page> list(Model model) {
		UserVO user = new UserVO();
		user.setUsername("luffy");
		user.setStatus("1");
		user.setPhone("16866668888");
		ExampleMatcher matcher = ExampleMatcher.matching().withMatcher("username", match -> match.startsWith());
		
		Example<UserVO> example = Example.of(user,matcher);
		Sort sort = new Sort(Direction.DESC, "id");
		Pageable pageable = new PageRequest(0, 5, sort);
		Page<UserVO> page = userService.findUserList(example,pageable);
		userService.findUserByName("luffy");
		return ResponseEntity.ok(page);
	}

//	@ResponseBody
//	@RequestMapping(value = "/findUsers", method = RequestMethod.GET)
//	public Map<String, Object> list(UserVO userVO, PageVO pageVO) {
//		List<UserVO> userList = userService.findUser(userVO, pageVO);
//		Map<String, Object> map = new HashMap<String, Object>();
//		map.put("total", userService.findUserCount(userVO));
//		map.put("rows", userList);
//		return map;
//	}
	
	@ResponseBody
	@RequestMapping(value = "/addUser", method = RequestMethod.POST)
	public int findUserByName(UserVO user) {
		userService.saveUser(user);
		return 1;
	}

	@ResponseBody
	@RequestMapping(value = "/refreshPermissions", method = RequestMethod.GET)
	public int RefreshPermissions() {
		userService.refreshPermissions();
		return 1;
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(UserVO user, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
		if (bindingResult.hasErrors()) {
			return "login";
		}

		String username = user.getUsername();
		UsernamePasswordToken token = new UsernamePasswordToken(user.getUsername(), user.getPassword());
		// 获取当前的Subject
		Subject currentUser = SecurityUtils.getSubject();
		try {
			// 在调用了login方法后,SecurityManager会收到AuthenticationToken,并将其发送给已配置的Realm执行必须的认证检查
			// 每个Realm都能在必要时对提交的AuthenticationTokens作出反应
			// 所以这一步在调用login(token)方法时,它会走到MyRealm.doGetAuthenticationInfo()方法中,具体验证方式详见此方法
			logger.info("对用户[" + username + "]进行登录验证..验证开始");
			currentUser.login(token);
			logger.info("对用户[" + username + "]进行登录验证..验证通过");
		} catch (UnknownAccountException uae) {
			logger.info("对用户[" + username + "]进行登录验证..验证未通过,未知账户");
			redirectAttributes.addFlashAttribute("message", "未知账户");
		} catch (IncorrectCredentialsException ice) {
			logger.info("对用户[" + username + "]进行登录验证..验证未通过,错误的凭证");
			redirectAttributes.addFlashAttribute("message", "密码不正确");
		} catch (LockedAccountException lae) {
			logger.info("对用户[" + username + "]进行登录验证..验证未通过,账户已锁定");
			redirectAttributes.addFlashAttribute("message", "账户已锁定");
		} catch (ExcessiveAttemptsException eae) {
			logger.info("对用户[" + username + "]进行登录验证..验证未通过,错误次数过多");
			redirectAttributes.addFlashAttribute("message", "用户名或密码错误次数过多");
		} catch (AuthenticationException ae) {
			// 通过处理Shiro的运行时AuthenticationException就可以控制用户登录失败或密码错误时的情景
			logger.info("对用户[" + username + "]进行登录验证..验证未通过,堆栈轨迹如下");
			ae.printStackTrace();
			redirectAttributes.addFlashAttribute("message", "用户名或密码不正确");
		}
		// 验证是否登录成功
		if (currentUser.isAuthenticated()) {
			logger.info("用户[" + username + "]登录认证通过(这里可以进行一些认证通过后的一些系统参数初始化操作)");
			return "redirect:/user";
		} else {
			token.clear();
			return "redirect:/login";
		}
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(RedirectAttributes redirectAttributes) {
		// 使用权限管理工具进行用户的退出，跳出登录，给出提示信息
		SecurityUtils.getSubject().logout();
		redirectAttributes.addFlashAttribute("message", "您已安全退出");
		return "redirect:/login";
	}

	@RequestMapping("/403")
	public String unauthorizedRole() {
		logger.info("------没有权限-------");
		return "403";
	}
}
