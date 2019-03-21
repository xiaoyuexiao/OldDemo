package com.jcf.interfaces;

public interface OaPowerInfoActionInfaces {

	
	//增加权限信息
	public String insertPower(
			String power_info_name,
			String power_info_flag,
			String department_id
			);
	//删除权限表信息
	public String deletePower(String power_info_name);
	//改权限表信息
	public String updatePower(
			Integer id,
			String power_info_name,
			String power_info_flag,
			String department_id
			);
	//查权限表信息
	public String queryPower(String power_info_name,String page);
}
