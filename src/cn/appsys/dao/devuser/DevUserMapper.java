package cn.appsys.dao.devuser;

import org.apache.ibatis.annotations.Param;

import cn.appsys.pojo.DevUser;

public interface DevUserMapper {

	/**根据devCode获取用户记录
	 * @param devCode
	 * @return
	 */
	public DevUser getLoginUser(@Param("devCode") String devCode);
	
	/**更新用户表
	 * @param devUser
	 * @return
	 */
	public Integer upadteDevUser(DevUser devUser);
	

	/**用id，邮箱，账号查询
	 * @param id
	 * @param devEmail
	 * @param devCode
	 * @return
	 */
	public DevUser getDevUser(@Param(value="id")Integer id,@Param(value="devEmail")String devEmail,@Param(value="devCode")String devCode);
	
	/**添加用户
	 * @param devUser
	 * @return
	 */
	public Integer add(DevUser devUser);
}
