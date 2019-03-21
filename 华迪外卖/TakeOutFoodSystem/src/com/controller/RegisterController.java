/**
 * 
 */
package com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.implement.RegisterImp;

/**
 * @author lee
 * @time:2018
 * @fun:
 * @param:
 */
@Controller
public class RegisterController
{
	@Autowired
	RegisterImp ri;
	
	
	@ResponseBody
	@RequestMapping(value="/userRegister",produces="text/html;charset=UTF-8",method = {RequestMethod.GET,RequestMethod.POST})
	public String userRegister(String user_phone,String user_pwd,String user_school)
	{
		return	ri.registerUser(user_phone, user_pwd,user_school);
	}
	
	@ResponseBody
	@RequestMapping(value="/shopRegister",produces="text/html;charset=UTF-8",method = {RequestMethod.GET,RequestMethod.POST})
	public String shopRegister(String shop_phone,String shop_pwd,String shop_name,String shop_address)
	{
		return	ri.registerShop(shop_phone,shop_pwd,shop_name,shop_address);
	}
}
