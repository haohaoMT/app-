package cn.appsys.service.developer;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.appsys.pojo.DevUser;

/**
 * @author admin
 *
 */
public interface DevUserService {

	/**
	 * 登陆验证
	 * 
	 * @param devCode
	 * @param password
	 * @return
	 */
	public DevUser login(String devCode, String password);

	/**
	 * 更新用户信息
	 * 
	 * @param devUser
	 * @return
	 */
	public boolean upadteDevUser(DevUser devUser);

	/**
	 * 根据id或邮箱或账号查询
	 * 
	 * @param id
	 * @param devEmail
	 * @param devCode
	 * @return
	 */
	public DevUser getDevUser(Integer id, String devEmail, String devCode);

	/**
	 * 添加用户
	 * 
	 * @param devUser
	 * @return
	 */
	public boolean add(DevUser devUser);

	/**
	 * 获取所有开发者账号
	 * 
	 * @return
	 */
	public List<DevUser> getListDevUser(Integer currentPageNo, Integer pageSize);

	/**
	 * 查询开发账号总数
	 * 
	 * @return
	 */
	public int getDevUserCount();

}
