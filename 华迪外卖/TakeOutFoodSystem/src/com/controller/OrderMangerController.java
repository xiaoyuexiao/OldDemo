package com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.implement.OrderMangerImp;

@Controller
public class OrderMangerController 
{
	@Autowired
	OrderMangerImp omi;
	
	//http://localhost:8080/TakeOutFoodSystem/addOrder?
	@ResponseBody
	@RequestMapping(value="/addOrder",produces="text/html;charset=UTF-8",method = {RequestMethod.GET,RequestMethod.POST})
	public String addOrder(String clear_address, String allmoney, String userphone, String shopphone, String good_str)
	{
		return	omi.addOrder(clear_address, allmoney,userphone,shopphone,good_str);
	}
	//http://localhost:8080/TakeOutFoodSystem/queryOrdersByUserphone?userphone=13778246098
	@ResponseBody
	@RequestMapping(value="/queryOrdersByUserphone",produces="text/html;charset=UTF-8",method = {RequestMethod.GET,RequestMethod.POST})
	public String queryOrdersByUserphone(String userphone)
	{
		return	omi.queryOrdersByUserphone(userphone);
	}
	
	//http://localhost:8080/TakeOutFoodSystem/queryShopNameAndStarByorderid?orderid=orderd5a96c629a
	@ResponseBody
	@RequestMapping(value="/queryShopNameAndStarByorderid",produces="text/html;charset=UTF-8",method = {RequestMethod.GET,RequestMethod.POST})
	public String queryShopNameAndStarByorderid(String orderid)
	{
		return	omi.queryShopNameAndStarByorderid(orderid);
	}
}
