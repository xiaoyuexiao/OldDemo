package com.jcf.implement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


import com.alibaba.fastjson.JSONObject;
import com.jcf.interfaces.CompanyActionInterfaces;
import com.jcf.util.DBHelper;
import com.jcf.util.MyUtil;
@Service
public class CompanyActionImplements implements CompanyActionInterfaces {
	@Autowired
	JdbcTemplate jdbcTemplate;
	@Autowired
	DataSourceTransactionManager  txManager;
	
	@Transactional(propagation=Propagation.REQUIRES_NEW)
	@Override
	public String insertCompany( String company_info_name,
			 String company_info_content, String company_info_phone,
			 String company_info_address, String company_info_images)
	{
		
			if (jdbcTemplate!=null) 
			{
	    	    //得到token值
			    String company_info_token = "company" + MyUtil.GetToken().substring(0,9);
	    	    
				//将酒店信息存入表
				String sql = "insert into snug_company_info(" +
						"company_info_name," +
						"company_info_content," +
						"company_info_phone," +
						"company_info_address," +
						"company_info_token) values(?,?,?,?,?)";
				
				Object[] params = new Object[]{company_info_name,company_info_content,company_info_phone,company_info_address,company_info_token};
				int rows = jdbcTemplate.update(sql,params);
				
				String result = MyUtil.addImgs(company_info_images, company_info_token, jdbcTemplate);
				if (result.equals("error"))
				{
					JSONObject object = new JSONObject();
					object.put("status", "2");
					object.put("mes", "参数错误，图片库没有所传图片！！！");
					return object.toString();
				}
				return DBHelper.ReturnRows(rows);
	    	}
			else
			{
				return DBHelper.FailConnectDatabase();
			}
	}
	
}
