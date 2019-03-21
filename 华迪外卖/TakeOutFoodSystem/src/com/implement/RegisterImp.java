/**
 * 
 */
package com.implement;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.aspectj.weaver.ast.Var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.alibaba.fastjson.JSONObject;
import com.interfaces.RegisterInterface;
import com.util.MyUtil;

/**
 * @author lee
 * @time:2
 * @fun:
 * @param:
 */
@Service
public class RegisterImp implements RegisterInterface {

	@Autowired
	JdbcTemplate jt;
	@Override
	public String registerUser(String user_phone,String user_pwd,String user_school)
	{
		if (jt!=null)
		{	//1.手机号检测
			String regex = "^((13[0-9])|(14[5,7])|(15[0-3,5-9])|(17[0,3,5-8])|(18[0-9])|166|198|199|(147))\\d{8}$";  
		    Pattern p = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);  
		    Matcher m = p.matcher(user_phone);  
		    if(!m.matches())
		    {
		    	JSONObject object = new JSONObject();
				object.put("status","7");
				object.put("info","plase write correct phone !!");
				return object.toString();
		    }
		    if (user_pwd.length()<6)
		    {
		    	JSONObject object = new JSONObject();
				object.put("status","5");
				object.put("info","password is too short at least 6 words!!!");
				return object.toString();
			}
			//2.查询手机号是否存在
			String query_phone = "select * from user where phone = ?";
			List<Map<String, Object>> phone_row = jt.queryForList(query_phone,user_phone);
			if (phone_row.size() == 1)
			{
				JSONObject object = new JSONObject();
				object.put("status","4");
				object.put("info","phone has registered !!");
				return object.toString();
			}
			//3.查询学校是否存在
			String query_school = "select * from school where schoolId = ? or schoolName = ?";
			List<Map<String, Object>> school_row = jt.queryForList(query_school,new Object[]{user_school,user_school});
			if (school_row.size() == 0)
			{
				JSONObject object = new JSONObject();
				object.put("status","6");
				object.put("info","no this school!!");
				return object.toString();
			}
			//遍历查询结果得到学校id
			String school_id = "";
			for (Map<String, Object> map : school_row)
			{
				for (Entry<String, Object> entry : map.entrySet())
				{
					if (entry.getKey().equals("schoolId"))
					{
						school_id = (String) entry.getValue();
						break;
					}
				}
			}
 			//4.用户注册
			String addUser = "insert into user(phone,password,schoolid) values(?,?,?)";
			int row = jt.update(addUser, new Object[]{user_phone,user_pwd,school_id});
			if (row!=1)
			{
				JSONObject object = new JSONObject();
				object.put("status","2");
				object.put("info","fail");
				return object.toString();
			}
			JSONObject object = new JSONObject();
			object.put("status","1");
			object.put("info","success");
			return object.toString();
		}
		else
		{
			JSONObject object = new JSONObject();
			object.put("status","3");
			object.put("info","dbfail!!!");
			return object.toString();
		}
	}

	@Override
	public String registerShop(String shop_phone,String shop_pwd,String shop_name,String shop_address)
	{
		
		if (jt!=null)
		{	//1.手机号检测
			String regex = "^((13[0-9])|(14[5,7])|(15[0-3,5-9])|(17[0,3,5-8])|(18[0-9])|166|198|199|(147))\\d{8}$";  
		    Pattern p = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);  
		    System.out.println(shop_phone);
		    Matcher m = p.matcher(shop_phone);
		    if(!m.matches())
		    {
		    	JSONObject object = new JSONObject();
				object.put("status","7");
				object.put("info","plase write correct phone !!");
				return object.toString();
		    }
		    if (shop_pwd.length()<6)
		    {
		    	JSONObject object = new JSONObject();
				object.put("status","5");
				object.put("info","password is too short at least 6 words!!!");
				return object.toString();
			}
			//2.查询手机号是否被注册
			String query_phone = "select * from shopadminstor where shopphone = ?";
			List<Map<String, Object>> phone_row = jt.queryForList(query_phone,shop_phone);
			if (phone_row.size() == 1)
			{
				JSONObject object = new JSONObject();
				object.put("status","4");
				object.put("info","phone has registered !!");
				return object.toString();
			}
			//3.查询学校是否存在
			//String query_school = "select shopadminstor.shopaddress,school.schoolName from shopadminstor inner join school on shopadminstor.shopaddress = school.schoolId where school.schoolId = ? or school.schoolName = ?";
			String query_school = "select * from school where schoolId = ? or schoolName = ?";
			List<Map<String, Object>> school_row = jt.queryForList(query_school,new Object[]{shop_address,shop_address});
			if (school_row.size() == 0)
			{
				JSONObject object = new JSONObject();
				object.put("status","6");
				object.put("info","no this school!!");
				return object.toString();
			}
			//遍历查询结果得到学校id
			String school_id = "";
			for (Map<String, Object> map : school_row)
			{
				for (Entry<String, Object> entry : map.entrySet())
				{
					if (entry.getKey().equals("schoolId"))
					{
						school_id = (String) entry.getValue();
						break;
					}
				}
			}
 			//4.添加商家
			String addUser = "insert into shopadminstor(shopphone,shoppwd,schoolid,shopname) values(?,?,?,?)";
			int row = jt.update(addUser, new Object[]{shop_phone,shop_pwd,school_id,shop_name});
			if (row!=1)
			{
				JSONObject object = new JSONObject();
				object.put("status","2");
				object.put("info","fail");
				return object.toString();
			}
			JSONObject object = new JSONObject();
			object.put("status","1");
			object.put("info","success");
			return object.toString();
		}
		else
		{
			JSONObject object = new JSONObject();
			object.put("status","3");
			object.put("info","dbfail!!!");
			return object.toString();
		}
	}

}
