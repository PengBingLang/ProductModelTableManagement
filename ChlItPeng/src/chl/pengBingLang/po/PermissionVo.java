package chl.pengBingLang.po;

/**
 * @author PengBingLang 彭秉浪
 */
public class PermissionVo {

	private String userId;
	private String moduleId;
	private String isGrant;
	private String refuse;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getModuleId() {
		return moduleId;
	}

	public void setModuleId(String moduleId) {
		this.moduleId = moduleId;
	}

	public String getIsGrant() {
		return isGrant;
	}

	public void setIsGrant(String isGrant) {
		this.isGrant = isGrant;
	}

	public String getRefuse() {
		return refuse;
	}

	public void setRefuse(String refuse) {
		this.refuse = refuse;
	}
}
