package com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.interfaces.QueryShopInterface;

@Controller
public class QueryShopController
{
	@Autowired
	QueryShopInterface qsi;
	//http:localhost:8080/TakeOutFoodSystem/queryShopBySchool?schoolid=xnkjdx&shop_type=0
	@ResponseBody
	@RequestMapping(value="/queryShopBySchool",produces="text/html;charset=UTF-8",method = {RequestMethod.GET,RequestMethod.POST})
	public String queryShopBySchool(String schoolid,String shop_type)
	{
		return	qsi.queryShopBySchool(schoolid, shop_type);
	}
	
	
	//http:localhost:8080/TakeOutFoodSystem/queryShopByUserPhone?user_phone=13123125487
	@ResponseBody
	@RequestMapping(value="/queryShopByUserPhone",produces="text/html;charset=UTF-8",method = {RequestMethod.GET,RequestMethod.POST})
	public String queryShopByUserPhone(String user_phone)
	{
		return	qsi.queryShopByUserPhone(user_phone);
	}
	
	
}
