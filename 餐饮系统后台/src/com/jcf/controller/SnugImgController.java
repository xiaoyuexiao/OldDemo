package com.jcf.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.jcf.interfaces.ImgActionInterfaces;
//http://localhost:8080/snug/upImg?token=100
@Controller
public class SnugImgController{
	@Autowired
	ImgActionInterfaces imgActionInterfaces ;
	@ResponseBody
	@RequestMapping(value="/upImg",produces="text/html;charset=utf-8",method={RequestMethod.GET,RequestMethod.POST})
	public String upImg(@RequestParam CommonsMultipartFile[] file,String total_image_type,String department_id)
	{
		return imgActionInterfaces.upImg(file, total_image_type, department_id);
	}
	//http://localhost:8080/snug/deleteImg?image_name=1519543336979
	@ResponseBody
	@RequestMapping(value="/deleteImg",produces="text/html;charset=utf-8",method={RequestMethod.GET,RequestMethod.POST})
	public String deleteImg(String image_name)
	{
		return imgActionInterfaces.deleteImg(image_name);
	};
	//http://localhost:8080/snug/queryImages
	@ResponseBody
	@RequestMapping(value="/queryImages",produces="text/html;charset=utf-8",method={RequestMethod.GET,RequestMethod.POST})
	public String queryImages()
	{
		return imgActionInterfaces.queryImages();
	};
}
