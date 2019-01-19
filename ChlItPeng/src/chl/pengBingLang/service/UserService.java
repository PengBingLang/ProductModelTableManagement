package chl.pengBingLang.service;

import net.sf.json.JSONObject;

/**
 * 用户相关的service
 * 
 * @author PengBingLang 彭秉浪
 */
public interface UserService {
	/**
	 * 登录校验
	 * 
	 * @param id
	 * @param pwd
	 * @return
	 * @author PengBingLang 彭秉浪
	 */
	public JSONObject loginCheck(String id, String pwd);

	/**
	 * 修改密码
	 * 
	 * @param id
	 * @param pwd
	 * @return
	 * @author PengBingLang 彭秉浪
	 */
	public JSONObject updatePwd(String id, String oldPwd, String newPwd);

	/**
	 * 权限校验
	 * 
	 * @param id
	 * @param pwd
	 * @return
	 * @author PengBingLang 彭秉浪
	 */
	public boolean PermissionCheck(String userId, String moduleId);
}
