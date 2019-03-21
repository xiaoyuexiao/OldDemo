package com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.implement.EvaluateImp;

@Controller
public class EvaluateController 
{
	@Autowired
	EvaluateImp ei;
	
	
	@ResponseBody
	@RequestMapping(value="/userEvaluate",produces="text/html;charset=UTF-8",method = {RequestMethod.GET,RequestMethod.POST})
	public String userEvaluate(String packing, String flavor, String distribution, String message, String orderid,
			String userphone)
	{
		return	ei.userEvaluate(packing, flavor, distribution, message, orderid, userphone);
	}
	
	//http://localhost:8080/TakeOutFoodSystem/queryEvlaluateListByOrderid?orderid=order4c840f58fd
	@ResponseBody
	@RequestMapping(value="/queryEvlaluateListByOrderid",produces="text/html;charset=UTF-8",method = {RequestMethod.GET,RequestMethod.POST})
	public String queryEvlaluateListByOrderid(String orderid)
	{
		return	ei.queryEvlaluateListByOrderid(orderid);
	}
}
