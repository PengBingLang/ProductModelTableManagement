package chl.pengBingLang.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import chl.pengBingLang.mapper.PermissionMapper;
import chl.pengBingLang.mapper.SysUserMapper;
import chl.pengBingLang.po.PermissionVo;
import chl.pengBingLang.po.SysUser;
import chl.pengBingLang.service.UserService;
import net.sf.json.JSONObject;

/**
 * @author PengBingLang 彭秉浪
 */
public class UserServiceImpl implements UserService {

	@Autowired
	private SysUserMapper userMapper;
	@Autowired
	private PermissionMapper permissionMapper;

	@Override
	public JSONObject loginCheck(String id, String pwd) {
		JSONObject json = new JSONObject();
		try {
			SysUser user = userMapper.selectByPrimaryKey(id);
			if (null == user) {
				json.put("message", "用户名或密码错误！");
			} else if (!user.getPassword().equals(pwd)) {
				json.put("message", "用户名或密码错误！");
			} else if (!"1".equals(user.getEnable())) {
				json.put("message", "用户已禁用！");
			} else {
				json.put("message", "success");
				json.put("userId", id + "（" + user.getName() + "）");
			}
		} catch (Exception e) {
			e.printStackTrace();
			json.put("message", "用户名或密码错误！");
		}
		return json;
	}

	@Override
	public JSONObject updatePwd(String id, String oldPwd, String newPwd) {
		JSONObject json = new JSONObject();
		if (6 > newPwd.length()) {
			json.put("message", "新密码长度至少6位字符！");
			return json;
		}
		if ("guest".equals(id)) {
			json.put("message", "修改失败，guest（访客）的密码只能由管理员修改！");
			return json;
		}
		SysUser user = userMapper.selectByPrimaryKey(id);
		if (null != user && user.getPassword().equals(oldPwd)) {
			SysUser user2 = new SysUser();
			user2.setId(user.getId());
			user2.setPassword(newPwd);
			int i = userMapper.updateByPrimaryKeySelective(user2);
			if (1 == i) {
				json.put("message", "success");
			} else {
				json.put("message", "密码修改失败！");
			}
		} else {
			json.put("message", "原密码错误！");
		}
		return json;
	}

	@Override
	public boolean PermissionCheck(String userId, String moduleId) {
		try {
			PermissionVo p = new PermissionVo();
			p.setUserId(userId);
			p.setModuleId(moduleId);
			int i = permissionMapper.selectIsRefuse(p);
			if (1 == i) {// 存在拒绝权限
				return false;
			}
			int j = permissionMapper.selectIsGrant(p);
			if (1 == j) {// 授权通过
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
}
