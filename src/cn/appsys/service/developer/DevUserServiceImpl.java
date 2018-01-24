package cn.appsys.service.developer;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;


import cn.appsys.dao.devuser.DevUserMapper;
import cn.appsys.pojo.DevUser;
@Service
public class DevUserServiceImpl implements DevUserService {

	@Resource
	private DevUserMapper mapper;
	@Override
	public DevUser login(String devCode, String password) {
		DevUser user =null;
		user = mapper.getLoginUser(devCode);
		if (user != null) {
			if (!user.getDevPassword().equals(password)) {
				return null;
			}
		}
		
		return user;
	}
	@Override
	public boolean upadteDevUser(DevUser devUser) {
		boolean flag = false;
		if(mapper.upadteDevUser(devUser) > 0){
			flag = true;
		}
		return flag;
	}


	@Override
	public DevUser getDevUser(Integer id, String devEmail, String devCode) {
		// TODO Auto-generated method stub
		return mapper.getDevUser(id, devEmail, devCode);
	}
	@Override
	public boolean add(DevUser devUser) {
		boolean flag = false;
		if(mapper.add(devUser) > 0){
			flag = true;
		}
		return flag;
	}
	@Override
	public List<DevUser> getListDevUser(Integer currentPageNo, Integer pageSize) {
		// TODO Auto-generated method stub
		return mapper.getListDevUser(currentPageNo, pageSize);
	}
	@Override
	public int getDevUserCount() {
		// TODO Auto-generated method stub
		return mapper.getDevUserCount();
	}
	

	
}
