package com.jcf.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.jcf.interfaces.TableTypeActionInterfaces;
@Controller
public class SnugTableTypeController{
  @Autowired
  TableTypeActionInterfaces tableTypeInterfaces;
  /*
	 * @author Lee
	 * @time 2018/1/12
	 * @fun 台桌分类查询(所有)
	 * testurl:http://localhost:8080/snug/queryTableType
	 * */
  @ResponseBody 
  @RequestMapping(value="/queryTableType",produces="text/html;charset=UTF-8",method = {RequestMethod.GET,RequestMethod.POST})
	public String queryTableType() {
	  return tableTypeInterfaces.queryTableType();
	}
  
  /*
	 * @author Lee
	 * @time 2018/1/12
	 * @fun 台桌分类添加
	 * testUrl:
	  http:localhost:8080/snug/insertTableType?table_type_name=厅&table_type_sub_name=包间&
	  table_type_img=5.jpg&table_type_sort=大小&table_type_recommand=推荐
	 * */
  @ResponseBody 
  @RequestMapping(value="/insertTableType",produces="text/html;charset=UTF-8",method = {RequestMethod.GET,RequestMethod.POST})
	public String insertTableType(String table_type_name,String table_type_sub_name
								  ,String table_type_sort,
								  String table_type_recommand,String table_type_content,
								  String table_type_flag,String table_type_images)
    {
	  	
		return tableTypeInterfaces.insertTableType(table_type_name, table_type_sub_name, table_type_sort, table_type_recommand,table_type_content,
				   table_type_flag,table_type_images);
	}
    /* 
	 * @author Lee
	 * @time 2018/1/12
	 * @fun 台桌分类隐藏(flag=0)
	 * testUrl:
	  http:localhost:8080/snug/deleteTableType?table_type_token=47897774
	 * */
  @ResponseBody
  @RequestMapping(value="/deleteTableType",produces="text/html;charset=utf-8",method = {RequestMethod.GET,RequestMethod.POST})
  public String deleteTableType(String table_type_token){
	  return tableTypeInterfaces.deleteTableType(table_type_token);
  }
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
	  table_type_token=tableaddress75f633b78,
	  department_id=001
	 * */
  @ResponseBody 
  @RequestMapping(value="/updateTableType",produces="text/html;charset=UTF-8",method = {RequestMethod.GET,RequestMethod.POST})
  public String updateTableType(String table_type_name,String table_type_sub_name,
								  String table_type_sort,String table_type_recommand,
								  String table_type_token,String table_type_content,
								  String table_type_flag,String table_type_images,String department_id)
  {
		return tableTypeInterfaces.updateTableType(table_type_name, table_type_sub_name, table_type_sort, table_type_recommand, table_type_token, table_type_content, table_type_flag,table_type_images,department_id);
  }
  
}
