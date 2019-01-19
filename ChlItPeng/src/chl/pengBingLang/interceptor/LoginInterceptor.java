package chl.pengBingLang.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * 登录拦截器
 * 
 * @author PengBingLang 彭秉浪
 */
public class LoginInterceptor implements HandlerInterceptor {

	@Value("${loginInterceptor.openUrl}")
	private String openUrl;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object arg2) throws Exception {
		// 进入Handler方法之前执行
		// 可以用于身份认证、身份授权
		// return false表示不放行
		// return true表示放行

		String url = request.getRequestURI();// 获取当前请求的url
		// 判断当前地址是否是公开地址（应该配置在配置文件中）
		if (openUrl.length() > 1) {
			String[] urls = openUrl.split("\\|");
			for (String s : urls) {
				if (url.indexOf(s) >= 0) {
					return true;
				}
			}
		}

		// 判断当前是否登陆
		String userName = (String) request.getSession().getAttribute("userId");
		String type = request.getHeader("X-Requested-With");// XMLHttpRequest
		if (null != userName && userName.length() > 5) {
			// 已经登录
			return true;
		}

		String path = request.getContextPath();
		String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path
				+ "/";
		if (StringUtils.equals("XMLHttpRequest", type)) {
			// ajax请求
			response.setHeader("SESSIONSTATUS", "TIMEOUT");
			response.setHeader("CONTEXTPATH", basePath + "login.html");
			response.setStatus(HttpServletResponse.SC_FORBIDDEN);
		} else {
			// 不是ajax请求，重定向
			response.sendRedirect(basePath + "login.html");
		}
		return false;
	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {
		// 进入Handler方法之后，返回modelAndView之前执行
		// 可以添加统一的数据到模型
	}

	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		// 执行Handler完成执行此方法
		// 应用场景：统一异常处理，统一日志处理
	}
}
