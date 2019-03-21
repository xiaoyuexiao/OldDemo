package com.jcf.interfaces;


public interface CompanyActionInterfaces {
	//酒店添加
	public abstract String insertCompany(
			String company_info_name,
			String company_info_content,
			String company_info_phone,
			String company_info_address,
			String company_info_images);
}
