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

import com.implement.TestImp;

/**
 * @author Administrator
 * @time:2018骞�6鏈�5鏃�
 * @fun:
 * @param:
 */
@Controller
public class TestController
{
	@Autowired
	TestImp t;
	@ResponseBody
	@RequestMapping(value="/test",produces="text/html;charset=UTF-8",method = {RequestMethod.GET,RequestMethod.POST})
	public String test(@RequestParam CommonsMultipartFile file)
	{
		return	t.showTest(file);
	}
}
