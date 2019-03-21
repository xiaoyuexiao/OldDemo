package com.jcf.util;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class GetDataSource {
	//加载properties
	public Connection getConnnection()
	{
		    Connection conn = null;
		    Properties prop = new Properties(); 
	        try {  
	        	//读取属性文件a.properties
	             InputStream in = this.getClass().getResourceAsStream("/com/jcf/config/db.properties");
	             prop.load(in);     ///加载属性列表
	             String driver = (String) prop.get("jdbc.driverName");
	             String url = (String) prop.get("jdbc.url");
	             String user = (String) prop.get("jdbc.userName");
	             String pwd = (String) prop.get("jdbc.passWord");
	             System.out.println("driver:"+driver);
				 Class.forName(driver);
				 conn = DriverManager.getConnection(url, user, pwd);//获取连接  
				 
	        } catch (Exception e) {  
	            // TODO Auto-generated catch block  
	            e.printStackTrace();  
	        }  
	        return conn;
	}
}
