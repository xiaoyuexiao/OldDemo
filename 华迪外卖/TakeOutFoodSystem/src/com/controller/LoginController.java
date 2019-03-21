/**
 * 
 */
package com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.implement.LoginImp;

/**
 * @author lee
 * @time:
 * @fun:
 * @param:
 */
@Controller
public class LoginController {
	@Autowired
	LoginImp li;
	
	//http:localhost:8080/TakeOutFoodSystem/userLogin?user_phone=111&user_pwd=222
	@ResponseBody
	@RequestMapping(value="/userLogin",produces="text/html;charset=UTF-8",method = {RequestMethod.GET,RequestMethod.POST})
	public String userLogin(String user_phone,String user_pwd)
	{
		return	li.userLogin(user_phone, user_pwd);
	}
	
	//http:localhost:8080/TakeOutFoodSystem/shopLogin?user_phone=111&user_pwd=222
	@ResponseBody
	@RequestMapping(value="/shopLogin",produces="text/html;charset=UTF-8",method = {RequestMethod.GET,RequestMethod.POST})
	public String shopLogin(String shop_phone,String shop_pwd)
	{
		return	li.shopLogin(shop_phone, shop_pwd);
	}
	
	@ResponseBody
	@RequestMapping(value="/userQuite",produces="text/html;charset=UTF-8",method = {RequestMethod.GET,RequestMethod.POST})
	public String userQuite(String user_phone)
	{
		return	li.userQuite(user_phone);
	}
}
