package com.jcf.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.jcf.interfaces.TableAddressActionInterfaces;


@Controller
public class SnugTableAddressController{
		@Autowired
		TableAddressActionInterfaces tableAddressInterfaces;
	  /*
		 * @author Lee
		 * @time 2018/1/12
		 * @fun 台桌分区添加
		 * */
	   @ResponseBody 
	   @RequestMapping(value="/insertTableAddress",produces="text/html;charset=UTF-8",method = {RequestMethod.GET,RequestMethod.POST})
		public String insertTableAddress(String department_address_name,
										 String department_address_content,String department_address_sort,
										 String department_address_recommand,String department_address_flag,String department_address_parent,
										 String department_id,String department_address_images)
	    {
		   return tableAddressInterfaces.insertTableAddress(department_address_name, department_address_content, department_address_sort, department_address_recommand,department_address_flag,department_address_parent, department_id,department_address_images); 
		}
	  
	  /*
		 * @author Lee
		 * @time 2018/1/13
		 * @fun 获取分区
		 * http://localhost:8080/snug/queryTableAddress
		 * 
		 * */
	@ResponseBody 
	  @RequestMapping(value="/queryTableAddress",produces="text/html;charset=UTF-8",method = {RequestMethod.GET,RequestMethod.POST})
		public String queryTableAddress() {
			String string = tableAddressInterfaces.queryTableAddress();
			return string;
		}
	  
	  /*
		 * @author Lee
		 * @time 2018/1/15
		 * @fun 分区隐藏
		 * http://localhost:8080/snug/deleteTableAddress?token=tableaddress2985209d1
		 * */
	  /* (non-Javadoc)
	 * @see com.jcf.service.TableAddressInterfaces#deleteTableAddress(java.lang.String)
	 */
	@ResponseBody
	  @RequestMapping(value="/deleteTableAddress",produces="text/html;charset=UTF-8",method = {RequestMethod.GET,RequestMethod.POST})
	  public String deleteTableAddress(String token){
		String string = tableAddressInterfaces.deleteTableAddress(token);
		return string;
	  }
	  
	  /*
		 * @author Lee
		 * @time 2018/1/15
		 * @fun 台桌分区修改
		 * testUrl:
		  http:localhost:8080/snug/updateTableAddress?department_address_name=A栋&department_address_content=武城大厦，五星级酒店&department_address_sort=001&department_address_recommand=推荐&department_address_parent=0001&department_id=001&token=tableaddress3c21cc2a4
	  */
	@ResponseBody 
	  @RequestMapping(value="/updateTableAddress",produces="text/html;charset=UTF-8",method = {RequestMethod.GET,RequestMethod.POST})
	  public String updateTableAddress(String department_address_name,
				 String department_address_content,String department_address_sort,
				 String department_address_recommand,String department_address_parent,
				 String department_id,String token,String department_address_images)
	  {
			String string = tableAddressInterfaces.updateTableAddress(department_address_name, department_address_content, department_address_sort, department_address_recommand, department_address_parent, department_id, token,department_address_images);
			return string;
	  }
	/*
	 * @author Lee
	 * @time 2018/1/27
	 * @fun 查询子分区通过父分区的token值
	 * http://localhost:8080/snug/queryTableChildAddress?token=12543
	 * */
  @ResponseBody 
  @RequestMapping(value="/queryTableChildAddress",produces="text/html;charset=UTF-8",method = {RequestMethod.GET,RequestMethod.POST})
  public String queryTableChildAddress(String token)
  {
	return tableAddressInterfaces.queryTableChildAddress(token);
  } 
//http:localhost:8080/snug/updateTableAddressNoImg?department_address_name=A栋&department_address_content=武城大厦，五星级酒店&department_address_sort=001&department_address_recommand=推荐&department_address_parent=0001&department_id=001&token=tableaddress3c21cc2a4&department_address_images=12345,45678
  @ResponseBody 
  @RequestMapping(value="/updateTableAddressNoImg",produces="text/html;charset=UTF-8",method = {RequestMethod.GET,RequestMethod.POST})
  public String updateTableAddressNoImg(String department_address_name,
			 String department_address_content,String department_address_sort,
			 String department_address_recommand,String department_address_parent,
			 String department_id,String token,String department_address_images)
  {
		String string = tableAddressInterfaces.updateTableAddressNoImg(department_address_name, department_address_content, department_address_sort, department_address_recommand, department_address_parent, department_id, token,department_address_images);
		return string;
  }
}
