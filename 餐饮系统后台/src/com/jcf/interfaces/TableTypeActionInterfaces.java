package com.jcf.interfaces;

import org.springframework.web.multipart.commons.CommonsMultipartFile;


public interface TableTypeActionInterfaces {

	/*
	 * @author Lee
	 * @time 2018/1/12
	 * @fun 台桌分类查询(所有)
	 * testurl:http://localhost:8080/snug/queryTableType
	 * */
	public abstract String queryTableType();

	/*
	 * @author Lee
	 * @time 2018/1/12
	 * @fun 台桌分类添加
	 * testUrl:
		  http:localhost:8080/snug/insertTableType?table_type_name=厅&table_type_sub_name=包间&
		  table_type_img=5.jpg&table_type_sort=大小&table_type_recommand=推荐&table_type_imagestable_type_images=15204147347246.png
	 * */
	public abstract String insertTableType(String table_type_name,
			String table_type_sub_name,
			String table_type_sort, String table_type_recommand,
			String table_type_content,String table_type_flag,String table_type_images);

	/* 
	 * @author Lee
	 * @time 2018/1/12
	 * @fun 台桌分类隐藏(flag=0)
	 * testUrl:
		  http:localhost:8080/snug/deleteTableType?table_type_token=47897774
	 * */
	public abstract String deleteTableType(String table_type_token);

	/*
	 * @author Lee
	 * @time 2018/1/13
	 * @fun 台桌分类修改
	 * testUrl:
		  http://localhost:8080/snug/updateTableType?
		  table_type_name=1&
		  table_type_sub_name=11&
		  table_type_sort=大小&
		  table_type_recommand=推荐&
		  table_type_token=tableaddress75f633b78
	 * */
	public abstract String updateTableType(String table_type_name,
			String table_type_sub_name, String table_type_sort,
			String table_type_recommand, String table_type_token,
			String table_type_content,String table_type_flag,
			String table_type_images,String department_id);

}