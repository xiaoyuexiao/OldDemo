package com.xiaoyuexiao.Data;

import java.util.ArrayList;
import java.util.Map;

public class GetData 
{
	private ArrayList<Map<String,Object>> data_record;//数据记录(购买记录)
	private ArrayList<String> data_obj;//分析的对象(商品信息)
	
	public ArrayList<String> get_data_obj()
	{
		data_obj.add("A");
		data_obj.add("B");
		return data_obj;
	}
	
	
}
