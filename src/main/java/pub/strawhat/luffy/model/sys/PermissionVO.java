package pub.strawhat.luffy.model.sys;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import pub.strawhat.luffy.model.BaseVO;

@Entity(name = "sys_permission_t")
public class PermissionVO extends BaseVO {

	private static final long serialVersionUID = -232095674876754429L;

	/**
	 * 主键
	 */
	@Id
	@GeneratedValue
	private Integer id;

	/**
	 * 模块
	 */
	private String module;

	/**
	 * 操作
	 */
	private String operation;

	/**
	 * 值
	 */
	private String value;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getModule() {
		return module;
	}

	public void setModule(String module) {
		this.module = module;
	}

	public String getOperation() {
		return operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}