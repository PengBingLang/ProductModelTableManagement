package chl.pengBingLang.interceptor;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.HandlerInterceptor;

import chl.pengBingLang.mapper.PermissionMapper;
import chl.pengBingLang.po.PermissionVo;
import chl.pengBingLang.service.UserService;
import net.sf.json.JSONObject;

/**
 * 权限校验拦截器
 * 
 * @author PengBingLang 彭秉浪
 */
public class PermissionInterceptor implements HandlerInterceptor {

	@Value("${permissionInterceptor.openUrl}")
	private String openUrl;
	@Value("${permissionInterceptor.srcUrl}")
	private String srcUrl;
	@Value("${permissionInterceptor.destUrl}")
	private String destUrl;

	@Autowired
	private UserService userService;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object arg2) {
		String url = request.getRequestURI();// 获取当前请求的url
		if (openUrl.length() > 1) {
			String[] urls = openUrl.split("\\|");
			for (String s : urls) {
				if (url.indexOf(s) >= 0) {
					return true;
				}
			}
		}
		String[] s1 = url.split(request.getContextPath());
		String[] s2 = s1[s1.length - 1].split("\\.");
		String moduleId = s2[0];
		String[] srcSS = srcUrl.split("\\|");
		String[] destSS = destUrl.split("\\|");
		for (int i = 0; i < srcSS.length; i++) {
			if (srcSS[i].equals(moduleId)) {
				moduleId = destSS[i];
				break;
			}
		}

		String userName = (String) request.getSession().getAttribute("userId");
		String userId = userName.split("（")[0];
		boolean check = userService.PermissionCheck(userId, moduleId);
		if (check) {
			return true;
		}
		try {
			JSONObject json = new JSONObject();
			json.put("message", "权限不足！");
			response.setContentType("text/json; charset=utf-8");
			response.getWriter().write(json.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}
}
