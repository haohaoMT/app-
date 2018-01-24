package cn.appsys.controller.develop;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.HashMap;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.mysql.jdbc.StringUtils;

import cn.appsys.pojo.AppInfo;
import cn.appsys.pojo.DevUser;
import cn.appsys.service.developer.DevUserService;
import cn.appsys.tools.MD5;

@Controller
@RequestMapping(value="/dev/add")
public class AddDevUserController {

	@Resource
	private DevUserService service;
	private Logger logger = Logger.getLogger(UpdateDevUserController.class);

	@RequestMapping(value="addDevUser",method=RequestMethod.GET)
	public String doAddDevUser() {
		return "/developer/devuseradd";
	}
	@RequestMapping(value="addDevUser",method=RequestMethod.POST)
	public String getAddDevUser(DevUser devUser,HttpServletRequest request) {
		devUser.setCreatedBy(devUser.getId());
		try {
			devUser.setDevPassword(MD5.EncoderByMd5(devUser.getDevPassword()));
		} catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		devUser.setCreationDate(new Date());
		if (service.add(devUser)) {
			return "redirect:/dev/login";
		}else {
			request.setAttribute("error", "注册失败");
			return "/developer/devuseradd";
		}
	}
	
	

	/**判断开发者账号是否存在
	 * @param devCode
	 * @return
	 */
	@RequestMapping(value = "selectByDevCode.json", method = RequestMethod.GET)
	@ResponseBody
	public Object selectByDevCode(@RequestParam String devCode) {
		logger.debug("selectByDevCode=====" + devCode);
		HashMap<String, String> resultMap = new HashMap<String, String>();
		// 判断为空值
		if (StringUtils.isNullOrEmpty(devCode)) {
			resultMap.put("devCode", "empty");
		} else {
			DevUser devUser = null;
			try {
				devUser = service.getDevUser(null, null, devCode);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (devUser != null) {
				resultMap.put("devCode", "exist");
			} else {
				resultMap.put("devCode", "noexist");
			}
		}
		return JSONArray.toJSON(resultMap);
	}
}
