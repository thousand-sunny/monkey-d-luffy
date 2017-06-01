package pub.strawhat.luffy.model.sys;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;

import pub.strawhat.luffy.model.BaseVO;

@Entity(name = "sys_user_t")
public class UserVO extends BaseVO {
	private static final long serialVersionUID = 6487405214486192033L;

	/**
	 * 主键
	 */
	@Id
	@GeneratedValue
	private Integer id;

	/**
	 * 用户名
	 */
	@Column(nullable = false)
	private String username;

	/**
	 * 密码
	 */
	private String password;

	/**
	 * 真实姓名
	 */
	private String realName;

	/**
	 * 状态(1/正常，0/锁定)
	 */
	private String status;

	/**
	 * 邮箱
	 */
	private String email;

	/**
	 * 电话
	 */
	private String phone;

	/**
	 * 性别
	 */
	private String sex;

	/**
	 * 头像
	 */
	private String avatar;

	@Transient
	private List<RoleVO> roleList;

	/**
	 * 主键
	 * 
	 * @return id 主键
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * 主键
	 * 
	 * @param id
	 *            主键
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * 用户名
	 * 
	 * @return username 用户名
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * 用户名
	 * 
	 * @param username
	 *            用户名
	 */
	public void setUsername(String username) {
		this.username = username == null ? null : username.trim();
	}

	/**
	 * 密码
	 * 
	 * @return password 密码
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * 密码
	 * 
	 * @param password
	 *            密码
	 */
	public void setPassword(String password) {
		this.password = password == null ? null : password.trim();
	}

	/**
	 * 真实姓名
	 * 
	 * @return real_name 真实姓名
	 */
	public String getRealName() {
		return realName;
	}

	/**
	 * 真实姓名
	 * 
	 * @param realName
	 *            真实姓名
	 */
	public void setRealName(String realName) {
		this.realName = realName == null ? null : realName.trim();
	}

	/**
	 * 状态(1/正常，0/锁定)
	 * 
	 * @return status 状态(1/正常，0/锁定)
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * 状态(1/正常，0/锁定)
	 * 
	 * @param status
	 *            状态(1/正常，0/锁定)
	 */
	public void setStatus(String status) {
		this.status = status == null ? null : status.trim();
	}

	/**
	 * 邮箱
	 * 
	 * @return email 邮箱
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * 邮箱
	 * 
	 * @param email
	 *            邮箱
	 */
	public void setEmail(String email) {
		this.email = email == null ? null : email.trim();
	}

	/**
	 * 电话
	 * 
	 * @return phone 电话
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * 电话
	 * 
	 * @param phone
	 *            电话
	 */
	public void setPhone(String phone) {
		this.phone = phone == null ? null : phone.trim();
	}

	/**
	 * 性别
	 * 
	 * @return sex 性别
	 */
	public String getSex() {
		return sex;
	}

	/**
	 * 性别
	 * 
	 * @param sex
	 *            性别
	 */
	public void setSex(String sex) {
		this.sex = sex == null ? null : sex.trim();
	}

	/**
	 * 头像
	 * 
	 * @return avatar 头像
	 */
	public String getAvatar() {
		return avatar;
	}

	/**
	 * 头像
	 * 
	 * @param avatar
	 *            头像
	 */
	public void setAvatar(String avatar) {
		this.avatar = avatar == null ? null : avatar.trim();
	}

	public List<RoleVO> getRoleList() {
		return roleList;
	}

	public void setRoleList(List<RoleVO> roleList) {
		this.roleList = roleList;
	}

	public Set<String> getRolesName() {
		List<RoleVO> roles = getRoleList();
		Set<String> set = new HashSet<String>();
		if (null != roles) {
			for (RoleVO role : roles) {
				set.add(role.getRoleName());
			}
		}
		return set;
	}
}