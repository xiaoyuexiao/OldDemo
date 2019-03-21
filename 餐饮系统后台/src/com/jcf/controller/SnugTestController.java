package com.jcf.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

public class SnugTestController {
	 @Autowired
	  JdbcTemplate jdbcTemplate;
	  //http://localhost:8080/snug/queryTableInfo?department_id=2000
	  //http://localhost:8080/snug/insertTableInfo?table_info_name=黎肖&table_info_img=lee&table_info_content=3
	  @ResponseBody 
	  @RequestMapping(value="/Test",produces="text/html;charset=UTF-8",method = {RequestMethod.GET,RequestMethod.POST})
		public String Test() {
		System.out.println("666");
		  if (jdbcTemplate!=null) {
				int ret = jdbcTemplate.update("insert into snug_table_info(table_info_name," +
						"table_info_img,table_info_content) values(?,?,?)", 
						new Object[]{123,456,789});
			    if(0==ret)
			    {
			    	System.out.println("error");
			    }
			    else 
			    {
			    	System.out.println("success");
				}
			}
		  
		  return "";
		}
}
