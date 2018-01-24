package cn.appsys.controller.backend;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.appsys.pojo.AppCategory;
import cn.appsys.pojo.AppInfo;
import cn.appsys.pojo.AppVersion;
import cn.appsys.pojo.DataDictionary;
import cn.appsys.pojo.DevUser;
import cn.appsys.service.backend.AppService;
import cn.appsys.service.developer.AppCategoryService;
import cn.appsys.service.developer.AppInfoService;
import cn.appsys.service.developer.AppVersionService;
import cn.appsys.service.developer.DataDictionaryService;
import cn.appsys.service.developer.DevUserService;
import cn.appsys.tools.Constants;
import cn.appsys.tools.PageSupport;

@Controller
@RequestMapping("/manager/backend/devUser")
public class ManageDevUserController {

	private Logger logger = Logger.getLogger(ManageDevUserController.class);
	
	@Resource
	private AppService appService;
	@Resource
	private AppVersionService appVersionService;
	@Resource
	private DataDictionaryService dataDictionaryService;
	@Resource 
	private AppCategoryService appCategoryService;
	@Resource
	private DevUserService devUserService;
	@RequestMapping(value="/devUserList")
	public String getAppInfoList(Model model,HttpSession session,
							@RequestParam(value="pageIndex",required=false) String pageIndex){
		List<DevUser> devUserList=null;
		//页面容量
		int pageSize = Constants.pageSize;
		//当前页码
		Integer currentPageNo = 1;
		
		if(pageIndex != null){
			try{
				currentPageNo = Integer.valueOf(pageIndex);
			}catch (NumberFormatException e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		//总数量（表）
		int totalCount = 0;
		try {
			totalCount = devUserService.getDevUserCount();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//总页数
		PageSupport pages = new PageSupport();
		pages.setCurrentPageNo(currentPageNo);
		pages.setPageSize(pageSize);
		pages.setTotalCount(totalCount);
		int totalPageCount = pages.getTotalPageCount();
		//控制首页和尾页
		if(currentPageNo < 1){
			currentPageNo = 1;
		}else if(currentPageNo > totalPageCount){
			currentPageNo = totalPageCount;
		}
		try {
			devUserList = devUserService.getListDevUser((currentPageNo-1)*pageSize, pageSize);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		model.addAttribute("devUserList", devUserList);
		model.addAttribute("pages", pages);
		
		return "backend/devUserList";
	}
	

}
