package pub.strawhat.luffy.model.sys;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;

import pub.strawhat.luffy.model.BaseVO;

@Entity(name = "sys_role_t")
public class RoleVO extends BaseVO {
	private static final long serialVersionUID = -2803528480706952679L;

	/**
	 * 主键
	 */
	@Id
	@GeneratedValue
	private Integer id;

	/**
	 * 角色名称
	 */
	private String roleName;

	/**
	 * 角色描述
	 */
	private String description;

	/**
	 * 排序
	 */
	private Integer orders;

	@Transient
	private List<PermissionVO> permissionList;
	@Transient
	private List<UserVO> userList;

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
	 * 角色名称
	 * 
	 * @return role_name 角色名称
	 */
	public String getRoleName() {
		return roleName;
	}

	/**
	 * 角色名称
	 * 
	 * @param roleName
	 *            角色名称
	 */
	public void setRoleName(String roleName) {
		this.roleName = roleName == null ? null : roleName.trim();
	}

	/**
	 * 角色描述
	 * 
	 * @return description 角色描述
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * 角色描述
	 * 
	 * @param description
	 *            角色描述
	 */
	public void setDescription(String description) {
		this.description = description == null ? null : description.trim();
	}

	/**
	 * 排序
	 * 
	 * @return orders 排序
	 */
	public Integer getOrders() {
		return orders;
	}

	/**
	 * 排序
	 * 
	 * @param orders
	 *            排序
	 */
	public void setOrders(Integer orders) {
		this.orders = orders;
	}

	public List<PermissionVO> getPermissionList() {
		return permissionList;
	}

	public void setPermissionList(List<PermissionVO> permissionList) {
		this.permissionList = permissionList;
	}

	public List<UserVO> getUserList() {
		return userList;
	}

	public void setUserList(List<UserVO> userList) {
		this.userList = userList;
	}

	public List<String> getPermissionsName() {
		List<String> list = new ArrayList<String>();
		List<PermissionVO> plist = getPermissionList();
		for (PermissionVO pvo : plist) {
			list.add(pvo.getValue());
		}
		return list;
	}

}