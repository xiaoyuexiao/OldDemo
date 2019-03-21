package com.jcf.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.jcf.interfaces.StockActionInterfaces;

@Controller
public class StockActionController {
	@Autowired
	StockActionInterfaces stockActionInterfaces;
	
	//http://localhost:8080/snug/queryGoodsMes
	@ResponseBody
	@RequestMapping(value="/queryGoodsMes",produces="text/html;charset=UTF-8",method = {RequestMethod.GET,RequestMethod.POST})
	public String queryGoodsMes(){
		return stockActionInterfaces.queryGoodsMes();
	}
	//http://localhost:8080/snug/queryGoodsByFirAndTow?firstfather=1&towtoken=123
	@ResponseBody
	@RequestMapping(value="/queryGoodsByFirAndTow",produces="text/html;charset=UTF-8",method = {RequestMethod.GET,RequestMethod.POST})
	public String queryGoodsByFirAndTow(String firstfather , String towtoken){
		return stockActionInterfaces.queryGoodsByFirAndTow(firstfather ,towtoken);
	}
	//http://localhost:8080/snug/queryAllGoodsMes?key_word=123
	@ResponseBody
	@RequestMapping(value="/queryAllGoodsMes",produces="text/html;charset=utf-8",method={RequestMethod.GET,RequestMethod.POST})
	public String queryAllGoodsMes(String key_word)
	{
		return stockActionInterfaces.queryAllGoodsMes(key_word);
	}
	//http://localhost:8080/snug/queryInGoodsMes?status_flag=3
	@ResponseBody
	@RequestMapping(value="/queryInGoodsMes",produces="text/html;charset=utf-8",method={RequestMethod.GET,RequestMethod.POST})
	public String queryInGoodsMes(String status_flag)
	{
		return stockActionInterfaces.queryInGoodsMes(status_flag);
	}
	
	//http://localhost:8080/snug/queryLikeNameAndTime?key_word=3
	@ResponseBody
	@RequestMapping(value="/queryLikeNameAndTime",produces="text/html;charset=utf-8",method={RequestMethod.GET,RequestMethod.POST})
	public String queryLikeNameAndTime(String key_word)
	{
		return stockActionInterfaces.queryLikeNameAndTime(key_word);
	}
	
	//http://localhost:8080/snug/addOutGoodsMes?out_material_mes=[{%22food_material_out_type%22:%22分类的token%22,%22food_material_out_good_token%22:%22商品的token%22,%22food_material_out_quantity%22:%2220%22,%22food_material_out_unit%22:%22瓶%22,%22food_material_out_to_department%22:%22去处%22,%22food_material_out_to_people%22:%22去处人%22,%22food_material_out_plus%22:%22备注%22,%22food_material_out_time%22:%22时间%22,%22food_material_out_sort%22:%22序号，排序%22,%22food_material_out_good_token%22:%22货物的token值%22,%22food_material_out_name%22:%22商品名称%22,%22department_id%22:%22部门id%22}]
	@ResponseBody
	@RequestMapping(value="/addOutGoodsMes",produces="text/html;charset=utf-8",method={RequestMethod.GET,RequestMethod.POST})
	public String addOutGoodsMes(String out_material_mes,String flag)
	{
		return stockActionInterfaces.addOutGoodsMes(out_material_mes,flag);
	}
	//http://localhost:8080/snug/queryInGoodMes?attence_info_token=3
	@ResponseBody
	@RequestMapping(value="/queryInGoodMes",produces="text/html;charset=utf-8",method={RequestMethod.GET,RequestMethod.POST})
	public String queryInGoodMes(String attence_info_token)
	{
		return stockActionInterfaces.queryInGoodMes(attence_info_token);
	}
	//http://localhost:8080/snug/queryHistoryOutGoods
	@ResponseBody
	@RequestMapping(value="/queryHistoryOutGoods",produces="text/html;charset=utf-8",method={RequestMethod.GET,RequestMethod.POST})
	public String queryHistoryOutGoods()
	{
		return stockActionInterfaces.queryHistoryOutGoods();
	}
	//http://localhost:8080/snug/addGoods?name=商品名称&type_one=1&type_sec=0001&unit=公斤&in_price=100&out_price=200&min_inventory=20&max_inventory=50&guarantee=120&maintain=100&servicelife=30&department_id=001
	@ResponseBody
	@RequestMapping(value="/addGoods",produces="text/html;charset=utf-8",method={RequestMethod.GET,RequestMethod.POST})
	public String addGoods(String name,
			String type_one,
			String type_sec,
			String unit,
			@RequestParam CommonsMultipartFile[] file,
			String in_price,
			String min_inventory,
			String max_inventory,
			String out_price,
			String guarantee,
			String maintain,
			String servicelife,
			String department_id)
	{
		return stockActionInterfaces.addGoods(name,
				 type_one,
				 type_sec,
				 unit,
				 file,
				 in_price,
				 out_price,
				 min_inventory,
				 max_inventory,
				 guarantee,
				 maintain,
				 servicelife,
				 department_id);
	}
	
	
	//出入库的模糊查询(manage_type:表明是出库还是入库，status_type：表示是待审核等状态)
	//http://localhost:8080/snug/queryByTimeAndName?key_name=234&key_time=&manage_type=2&status_type=1
	/*
	 * params:
	 * 		key_name:商品名称的关键字
	 * 		key_time:时间段，用逗号分割开，格式为：YYYY-MM-DD HH-MM-SS
	 * 		manage_type:表示出库还是入库，1：出库  2：入库
	 * 		status_type：表示需要查询的物品状态：  出库：  
	 * 											4：待审核 5：未通过 6：通过 "":全部
	 * 									      入库：
	 * 											1：待审核  4：通过 5：未通过 "":全部
	 * */
	@ResponseBody
	@RequestMapping(value="/queryByTimeAndName",produces="text/html;charset=utf-8",method={RequestMethod.GET,RequestMethod.POST})
	public String queryByTimeAndName(String key_name,String key_time,String manage_type,String status_type)
	{
		return stockActionInterfaces.queryByTimeAndName(key_name, key_time, manage_type, status_type);
	};
}
