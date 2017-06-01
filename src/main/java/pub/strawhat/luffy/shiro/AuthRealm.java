package pub.strawhat.luffy.shiro;

import java.util.List;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import pub.strawhat.luffy.model.sys.RoleVO;
import pub.strawhat.luffy.model.sys.UserVO;
import pub.strawhat.luffy.service.UserService;

public class AuthRealm extends AuthorizingRealm {
	@Autowired
	private UserService userService;

	// 认证.登录
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		UsernamePasswordToken utoken = (UsernamePasswordToken) token;// 获取用户输入的token
		String username = utoken.getUsername();
		UserVO user = userService.findUserByName(username);
		if (null != user) {
			return new SimpleAuthenticationInfo(user, user.getPassword(), getName());// 放入shiro.调用CredentialsMatcher检验密码
		}
		return null;
	}

	// 授权
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principal) {
		String username = (String) super.getAvailablePrincipal(principal);
		UserVO user = userService.findAuthUserByName(username);
		if (user != null) {
			// 权限信息对象info,用来存放查出的用户的所有的角色（role）及权限（permission）
			SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
			// 用户的角色集合
			info.setRoles(user.getRolesName());
			// 用户的角色对应的所有权限，如果只使用角色定义访问权限，下面的四行可以不要
			List<RoleVO> roleList = user.getRoleList();
			for (RoleVO role : roleList) {
				info.addStringPermissions(role.getPermissionsName());
			}
			// 或者按下面这样添加
			// 添加一个角色,不是配置意义上的添加,而是证明该用户拥有admin角色
			// simpleAuthorInfo.addRole("admin");
			// 添加权限
			// simpleAuthorInfo.addStringPermission("admin:manage");
			// logger.info("已为用户[mike]赋予了[admin]角色和[admin:manage]权限");
			return info;
		}
		// 返回null的话，就会导致任何用户访问被拦截的请求时，都会自动跳转到unauthorizedUrl指定的地址
		return null;
	}

}