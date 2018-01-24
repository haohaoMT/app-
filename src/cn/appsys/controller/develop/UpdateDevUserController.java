package cn.appsys.controller.develop;

import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.appsys.pojo.DevUser;
import cn.appsys.service.developer.DevUserService;
import cn.appsys.tools.Constants;

@Controller
@RequestMapping(value = "/dev/update")
public class UpdateDevUserController {

	@Resource
	private DevUserService service;
	private Logger logger = Logger.getLogger(UpdateDevUserController.class);

	/**进入修改页面
	 * @param session
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/updateDevUser", method = RequestMethod.GET)
	public String goUpdateDevUser(HttpSession session, Model model) {
		Integer id = ((DevUser) session.getAttribute(Constants.DEV_USER_SESSION)).getId();
		DevUser devUser =service.getDevUser(id,null,null);
		model.addAttribute("devUser", devUser);
		return "developer/devuserupadte";

	}

	
	/**修改保存
	 * @param devUser
	 * @param model
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/updateDevUser", method = RequestMethod.POST)
	public String getUpdateDevUser(DevUser devUser, Model model,HttpSession session) {
		if (devUser != null) {
			logger.debug("updateDevUser+++++++++++devUser"+devUser.getDevName());
			Integer id = ((DevUser) session.getAttribute(Constants.DEV_USER_SESSION)).getId();
			devUser.setModifyBy(id);
			devUser.setModifyDate(new Date());
			if (service.upadteDevUser(devUser)) {
				return "redirect:/dev/flatform/main";
			} else {
				model.addAttribute("error", "修改失败");
				return "developer/devuserupadte";
			}

		}

		return "developer/devuserupadte";

	}

	@RequestMapping(value = "/main", method = RequestMethod.GET)
	public String getMain(HttpSession session, Model model) {
		if ((DevUser) session.getAttribute(Constants.DEV_USER_SESSION) != null) {

			return "redirect:/dev/flatform/main";
		}
		return "redirect:/dev/logout";

	}
	
}
