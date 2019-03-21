package com.jcf.interfaces;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

public interface TableAddressActionInterfaces {

	/*
	 * @author Lee
	 * @time 2018/1/12
	 * @fun 台桌分区添加
	 * testurl:http://localhost:8080/snug/insertTableAddress?
		  department_address_name=c栋
		  &department_address_content=001&department_address_sort=001
		  &department_address_recommand=001&department_address_parent=12543&department_id=001
	 * */
	public abstract String insertTableAddress(String department_address_name,
			String department_address_content, String department_address_sort,
			String department_address_recommand,String department_address_flag,
			String department_address_parent, String department_id,
			String department_address_images);

	/*
	 * @author Lee
	 * @time 2018/1/13
	 * @fun 获取分区
	 * http://localhost:8080/snug/queryTableAddress
	 * 
	 * */
	public abstract String queryTableAddress();

	/*
	 * @author Lee
	 * @time 2018/1/15
	 * @fun 分区隐藏
	 * http://localhost:8080/snug/deleteTableAddress?token=tableaddress2985209d1
	 * */
	public abstract String deleteTableAddress(String token);

	/*
	 * @author Lee
	 * @time 2018/1/15
	 * @fun 台桌分区修改
	 * testUrl:
		  http:localhost:8080/snug/updateTableAddress?department_address_name=A栋&department_address_content=武城大厦，五星级酒店&department_address_sort=001&department_address_recommand=推荐&department_address_parent=0001&department_id=001&token=tableaddress3c21cc2a4
		 
	 * */
	public abstract String updateTableAddress(String department_address_name,
			String department_address_content, String department_address_sort,
			String department_address_recommand,String department_address_parent,
			String department_id, String token,String department_address_images);
	/*
	 * @author Lee
	 * @time 2018/2/8
	 * @fun 台桌分区无图片修改
	 * testUrl:
		  http:localhost:8080/snug/updateTableAddress?department_address_name=A栋&department_address_content=武城大厦，五星级酒店&department_address_sort=001&department_address_recommand=推荐&department_address_parent=0001&department_id=001&token=tableaddress3c21cc2a4
		 
	 * */
	public abstract String updateTableAddressNoImg(String department_address_name,
			String department_address_content, String department_address_sort,
			String department_address_recommand,String department_address_parent,
			String department_id, String token,String department_address_images);
	/*
	 * @author Lee
	 * @time 2018/1/27
	 * @fun 查询子分区以及分区有哪些分类通过父分区的token值
	 * http://localhost:8080/snug/queryTableChildAddress?token=？
	 * */
	public abstract String queryTableChildAddress(String token);
}