package cn.appsys.controller.develop;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


import cn.appsys.pojo.DevUser;
import cn.appsys.service.developer.DevUserService;
import cn.appsys.tools.Constants;
import cn.appsys.tools.MD5;

@Controller
@RequestMapping(value="/dev")
public class DevUserController {

	@Resource
	private DevUserService service;
	private Logger logger = Logger.getLogger(DevUserController.class);
	
	@RequestMapping(value="/login")
	public String login() {
		logger.debug("gogo======");
		return "devlogin";
	}
	/**登陆到开发者页面
	 * @param devCode
	 * @param devPassword
	 * @param session
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/dologin")
	public String dologin(@RequestParam String devCode,@RequestParam String devPassword,HttpSession session,
			HttpServletRequest request) {
		logger.debug("dologin======");
		DevUser user = null;
		//判断账号密码
		try {
			user = service.login(devCode,MD5.EncoderByMd5(devPassword) );
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (user != null) {
			//存入session
			session.setAttribute(Constants.DEV_USER_SESSION,user);
			//跳转页面
			return "redirect:/dev/flatform/main";
		}else {
			//重新输入，提示信息
			request.setAttribute("error", "用户名或密码不正确");
			return "devlogin";
		}
	}
	/**判断session是否为空
	 * @param session
	 * @return
	 */
	@RequestMapping(value="/flatform/main")
	public String main(HttpSession session) {
		if (session.getAttribute(Constants.DEV_USER_SESSION) == null) {
			return "redirect:/dev/login";
		}
		return "developer/main";
	}
	
	
	/**登出
	 * @param session
	 * @return
	 */
	@RequestMapping(value="/logout")
	public String logout(HttpSession session) {
		//清除session 
		session.removeAttribute(Constants.DEV_USER_SESSION);
		return "devlogin";
	}
	
	@RequestMapping(value="/logoutIndex")
	public void logoutIndex(HttpSession session,HttpServletResponse res,HttpServletRequest req) {
		//清除session 
		session.removeAttribute(Constants.DEV_USER_SESSION);
		session.removeAttribute(Constants.USER_SESSION);
		try {
			res.sendRedirect(req.getContextPath()+
					"/index.jsp");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
}
