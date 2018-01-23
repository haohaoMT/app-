package cn.appsys.controller.backend;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import cn.appsys.pojo.BackendUser;
import cn.appsys.service.backend.BackendUserService;
import cn.appsys.tools.Constants;

@Controller
@RequestMapping(value="/manager")
public class UserLoginController {
	private Logger logger = Logger.getLogger(UserLoginController.class);
	
	@Resource
	private BackendUserService backendUserService;
	
	@RequestMapping(value="/login")
	public String login() {
		logger.debug("进入系统管理员登陆页面");
		return "backendlogin";
	}
	@RequestMapping(value="/dologin",method=RequestMethod.POST)
	public String doLogin(@RequestParam String userCode,@RequestParam String userPassword,HttpServletRequest request,HttpSession session) {
		logger.debug("登陆验证");
		BackendUser backendUser = null;
		try {
			backendUser = backendUserService.login(userCode, userPassword);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (backendUser != null) {//登陆成功
			//放入session中
			session.setAttribute(Constants.USER_SESSION, backendUser);
			return "redirect:/manager/backend/main";
		}else {
			//登录失败
			request.setAttribute("error", "用户名或密码不正确");
			return "backendlogin";
		}
	}
	@RequestMapping(value="/backend/main")
	public String main(HttpSession session) {
		if (session.getAttribute(Constants.USER_SESSION) != null) {
			return "/backend/main";
		}
		return "backendlogin";
	}
	@RequestMapping(value="/logout")
	public String logout(HttpSession session) {
		session.removeAttribute(Constants.USER_SESSION);
		return "backendlogin";
	}
	@RequestMapping(value="/logoutIndex")
	public void logoutIndex(HttpSession session,HttpServletResponse res,HttpServletRequest req) {
		//清除session 
		session.removeAttribute(Constants.DEV_USER_SESSION);
		try {
			res.sendRedirect(req.getContextPath()+
					"/index.jsp");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
