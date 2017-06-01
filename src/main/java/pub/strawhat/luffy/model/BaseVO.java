package pub.strawhat.luffy.model;

import java.io.Serializable;
import java.util.Date;

public class BaseVO implements Serializable {

	private static final long serialVersionUID = 3692746161863894551L;

	/**
	 * 创建时间
	 */
	private Date createTime;

	/**
	 * 最后更新时间
	 */
	private Date lastUpdateTime;

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getLastUpdateTime() {
		return lastUpdateTime;
	}

	public void setLastUpdateTime(Date lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}

}
