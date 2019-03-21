package com.jcf.util;

import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class DBHelper
{
     /*
 	 * @author Lee
 	 * @time 2018/1/14
 	 * @fun 数据库连接失败调用函数
 	 * */
     public static String FailConnectDatabase(){
    	JSONObject object = new JSONObject();
		object.put("ststus", "0");
		object.put("mes", "fail to connect db !!");
		return object.toString();
     }
     
     /*
  	 * @author Lee
  	 * @time 2018/1/14
  	 * @fun 返回影响行数时调用函数
  	 * */
      public static String ReturnRows(int rows){
    	  if (rows == 0) 
			{
				JSONObject object = new JSONObject();
				object.put("status","2");
				object.put("mes", "fail");
				return object.toString();
			}
			else
			{
				JSONObject object = new JSONObject();
				object.put("status","1");
				object.put("mes", "success");
				return object.toString();
			}
      }
      
      /*
    	 * @author Lee
    	 * @time 2018/1/22
    	 * @fun 数据库查询返回list集合时
    	 * */
     public static String ReturnList(List<Map<String, Object>> list){
    	 	//判断list是否为空
    	    if(list.size()<=0){
				JSONObject object = new JSONObject();
				JSONArray array = new JSONArray();
				object.put("status", "2");
				object.put("info",array);
				return object.toString();
			}
    	    //不为空时
			JSONArray array = new JSONArray();
			for (Map<String, Object> map : list) {
				JSONObject object = new JSONObject();
				for (Map.Entry<String, Object> entry : map.entrySet()) {
						object.put(entry.getKey(),entry.getValue());
				}
				array.add(object);
			}
			
			JSONObject object = new JSONObject();
			object.put("status", "1");
			object.put("mes", "success");
			object.put("info", array);
			return object.toString();
     }
}
