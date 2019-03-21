package com.jcf.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.jcf.interfaces.FoodTypeActionInterfaces;
@Controller
public class FoodTypeActionController {
	@Autowired
	FoodTypeActionInterfaces foodTypeActionInterfaces;
	
	@ResponseBody
	@RequestMapping(value="/insertFoodType",produces="text/html;charset=UTF-8",method = {RequestMethod.GET,RequestMethod.POST})
	public String insertFoodType(String food_type_name,String food_type_father,String food_type_sort,String food_type_runtime,String food_type_flag,String department_id,String food_type_images){
		return	foodTypeActionInterfaces.insertFoodType(food_type_name,food_type_father, food_type_sort, food_type_runtime, food_type_flag, department_id,food_type_images);
	}
	
	//http://localhost:8080/snug/queryFoodType
	@ResponseBody
	@RequestMapping(value="/queryFoodType",produces="text/html;charset=UTF-8",method = {RequestMethod.GET,RequestMethod.POST})
	public String queryFoodType(){
		return	foodTypeActionInterfaces.queryFoodType();
	}
	
	//http://localhost:8080/snug/updateFoodType?food_type_name=粤菜&food_type_father=某分类的token&food_type_sort=1&food_type_runtime=8:00-12:00,13:00-18:00,19:00-22:00&food_type_flag=1&department_id=001&food_type_images=foodtype775670abc8,foodtype775670abc7&food_type_token=foodtype775670abc
	@ResponseBody
	@RequestMapping(value="/updateFoodType",produces="text/html;charset=UTF-8",method = {RequestMethod.GET,RequestMethod.POST})
	public String updateFoodType(String food_type_name,String food_type_father,String food_type_sort,String food_type_runtime,String food_type_flag,String department_id,String food_type_images,String food_type_images_ref,String food_type_token)
	{
		return foodTypeActionInterfaces.updateFoodType(food_type_name, food_type_father, food_type_sort, food_type_runtime, food_type_flag, department_id, food_type_images, food_type_token);
	}
	
	//http://localhost:8080/snug/deleteFoodType?food_type_token=
	@ResponseBody
	@RequestMapping(value="/deleteFoodType",produces="text/html;charset=UTF-8",method = {RequestMethod.GET,RequestMethod.POST})
	public String deleteFoodType(String food_type_token) 
	{
		return foodTypeActionInterfaces.deleteFoodType(food_type_token);
	}
	//http://localhost:8080/snug/updateFoodTypeNoImg?food_type_name=粤菜&food_type_father=某分类的token&food_type_sort=1&food_type_runtime=8:00-12:00,13:00-18:00,19:00-22:00&food_type_flag=1&department_id=001&food_type_images_ref=foodtype775670abc8,foodtype775670abc7&food_type_token=foodtypee3043936f
	@ResponseBody
	@RequestMapping(value="/updateFoodTypeNoImg",produces="text/html;charset=UTF-8",method = {RequestMethod.GET,RequestMethod.POST})
	public String updateFoodTypeNoImg(String food_type_name,String food_type_father,String food_type_sort,String food_type_runtime,String food_type_flag,String department_id,String food_type_images_ref,String food_type_token)
	{
		return foodTypeActionInterfaces.updateFoodTypeNoImg(food_type_name, food_type_father, food_type_sort, food_type_runtime, food_type_flag, department_id, food_type_images_ref, food_type_token);
	};
}
