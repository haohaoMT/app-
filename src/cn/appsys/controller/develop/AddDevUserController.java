package cn.appsys.controller.develop;

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
		devUser.setCreationDate(new Date());
		if (service.add(devUser)) {
			return "devlogin";
		}else {
			request.setAttribute("error", "注册失败");
			return "/developer/devuseradd";
		}
	}
	
	

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
