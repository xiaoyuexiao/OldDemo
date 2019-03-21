package com.jcf.interfaces;


public interface DepartmentActionInterfaces {
	//餐厅基础设置
	public abstract String updateResturnant(
			String department_info_name,
			String department_info_detail,
			String department_info_address,
			String department_info_phone,
			String department_info_plus,
			String department_info_token,
			String department_info_logos,
			String department_info_images_token
			);
	//餐厅基础设置查询
	public abstract String queryResMes(String department_id);
	//餐厅基础设置没有图片的删除
	public abstract String updateResturnantNoImgs(
			String department_info_name,
			String department_info_detail,
			String department_info_address,
			String department_info_phone,
			String department_info_plus,
			String department_info_token,
			String department_info_logo,
			String department_info_images_token
			);
}
