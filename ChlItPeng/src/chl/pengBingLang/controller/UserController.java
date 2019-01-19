package chl.pengBingLang.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import chl.pengBingLang.service.UserService;
import chl.pengBingLang.utils.HttpClient;
import net.sf.json.JSONObject;

/**
 * 用户相关的controller
 * 
 * @author PengBingLang 彭秉浪
 */
@Controller
@RequestMapping("user")
public class UserController {

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/login", method = { RequestMethod.POST })
	public void login(HttpServletRequest request, HttpServletResponse response, String userId, String userPwd)
			throws Exception {
		response.setContentType("text/json; charset=utf-8");

		// JSONObject json = new JSONObject();
		// 模拟调用service进行身份验证
		// if ("123456".equals(userId) && "qwe".equals(userPwd)) {
		// 登陆成功
		// request.getSession().setAttribute("userId", userId);
		// 用POST方式重定向
		// HttpClient http = new HttpClient(response);
		// http.setParameter("message", "登陆成功！");
		// http.sendByPost("/ChlItPeng/user/main.action");
		// return "redirect:/user/main.action";
		// json.put("message", "success");
		// response.getWriter().write(json.toString());
		// return;
		// }
		// 登陆失败，返回登陆页面
		// json.put("message", "用户名或密码错误！");

		JSONObject json = userService.loginCheck(userId, userPwd);
		if ("success".equals(json.get("message"))) {
			request.getSession().setAttribute("userId", json.get("userId"));
		}
		response.getWriter().write(json.toString());
	}

	@RequestMapping(value = "/main", method = { RequestMethod.GET })
	public String main(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 转发到主页
		return "/WEB-INF/main.html";
	}

	@RequestMapping(value = "/showUser", method = { RequestMethod.POST })
	public void showUser(HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setContentType("text/json; charset=utf-8");
		JSONObject json = new JSONObject();
		json.put("userId", request.getSession().getAttribute("userId").toString());
		response.getWriter().write(json.toString());
	}

	@RequestMapping(value = "/logout", method = { RequestMethod.POST })
	public String logout(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.getSession().removeAttribute("userId");
		// 重定向到登录页面
		return "redirect:/login.html";
	}

	@RequestMapping(value = "/updatePwd", method = { RequestMethod.POST })
	public void updatePwd(HttpServletRequest request, HttpServletResponse response, String oldPwd, String newPwd)
			throws IOException {
		String userName = (String) request.getSession().getAttribute("userId");
		String userId = userName.split("（")[0];
		JSONObject json = userService.updatePwd(userId, oldPwd, newPwd);
		response.setContentType("text/json; charset=utf-8");
		response.getWriter().write(json.toString());
		return;
	}
}
