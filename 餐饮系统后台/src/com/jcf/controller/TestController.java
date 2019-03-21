package com.jcf.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;




@Controller
public class TestController {
	@Autowired
	JdbcTemplate jdbcTemplate;
	@Autowired
	DataSourceTransactionManager  txManager;
	//http://localhost:8080/snug/GetBulletinList?s=13320947472
	@ResponseBody 
	@RequestMapping(value="/GetBulletinList",produces="text/html;charset=UTF-8",method ={ RequestMethod.GET,RequestMethod.POST})
	public String GetBulletinList(final String s) {
//		 TransactionTemplate tt = new TransactionTemplate(txManager);
//		 
//		   tt.execute(new TransactionCallback<Object>() {
//			   
//		      public Object doInTransaction(TransactionStatus status) {
//		    	  jdbcTemplate.update(
//		            "insert into xx1 (xx) values ('xiaxin');");
//		    	  jdbcTemplate.update(
//		    		"insert into xx1 (xx,xx2) values ('xiaxin','2323');");
//		         return null;
//		      }
//		   });
		   
//		TransactionTemplate tt1 = new TransactionTemplate(txManager);
		
		return "134645646";
	}
	
	@ResponseBody 
	@RequestMapping(value="/Testtomcat",produces="text/html;charset=UTF-8",method ={ RequestMethod.GET,RequestMethod.POST})
	public String Testtomcat() 
	{
		System.out.println(123);
		return "123";
	}
	
}
