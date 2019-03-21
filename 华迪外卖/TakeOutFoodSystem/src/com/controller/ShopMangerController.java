package com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.interfaces.ShopMangerInterface;

@Controller
public class ShopMangerController
{
	@Autowired
	ShopMangerInterface smi;
	//http:localhost:8080/TakeOutFoodSystem/queryShopMesByPhone?shop_phone=13440146584
	@ResponseBody
	@RequestMapping(value="/queryShopMesByPhone",produces="text/html;charset=UTF-8",method = {RequestMethod.GET,RequestMethod.POST})
	public String queryShopMesByPhone(String shop_phone)
	{
		return	smi.queryShopMesByPhone(shop_phone);
	}
}
