package com.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.util.MyUtil;

@Controller
public class UpImg 
{
	@ResponseBody
	@RequestMapping(value="/upImg",produces="text/html;charset=UTF-8",method = {RequestMethod.GET,RequestMethod.POST})
	public String upImg(@RequestParam CommonsMultipartFile file)
	{
		System.out.println("==============");
		return MyUtil.Upimg(file);
	}
}
