package com.jcf.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.jcf.interfaces.FoodActionInterfaces;
@Controller
public class SnugFoodController{
	@Autowired
	FoodActionInterfaces foodActionInterfaces;
	/*
	 * @author Lee
	 * @time 2018/1/14
	 * @fun 菜品插入控制器
	 * url:http://localhost:8080/snug/insertFood?
	  food_info_name=苦瓜肉片&
	  food_info_img=5.jpg&
	  food_info_sig=微辣&
	  food_info_content=此菜清火去热，味偏苦。&
	  food_info_quantity=20&
	  food_info_unit=份&
	  food_type_sort=1&
	  food_info_order=大份&
	  food_info_material=苦瓜&
	  food_info_sold=1000份&
	  food_info_type=1&
	  food_info_price=50元&
	  department_id=123
	  */
	@ResponseBody
	@RequestMapping(value="/insertFood",produces="text/html;charset=UTF-8",method = {RequestMethod.GET,RequestMethod.POST})
	public String insertFood(
				String	food_info_name,
				String  food_info_images,
				String	food_info_sig,
				String	food_info_content,
				String	food_info_quantity,
				String	food_info_unit,
				String	food_info_sort,
				String	food_info_order,
				String	food_info_material,
				String	food_info_sold,
				String	food_info_type,
				String	food_info_price,
				String	department_id,
				String food_info_material_cost,
				String food_info_image_word_content
			)
	{
		String string = foodActionInterfaces.insertFood(food_info_name,food_info_images,food_info_sig, food_info_content, food_info_quantity, food_info_unit, food_info_sort, food_info_order, food_info_material, food_info_sold, food_info_type, food_info_price, department_id,food_info_material_cost,food_info_image_word_content);
		return string;
	}
	/*
	 * @author Lee
	 * @time 2018/1/14
	 * @fun 菜品删除接口
	 * url:http://localhost:8080/snug/deleteFood?token=foods00e5129d7
	 * */

	@ResponseBody
	@RequestMapping(value="/deleteFood" , produces="text/html;charset=utf-8",method={RequestMethod.GET,RequestMethod.POST})
	public String deleteFood(String token){
		String string =	foodActionInterfaces.deleteFood(token);
		return string;
	}
	/*
	 * @author Lee
	 * @time 2018/1/14
	 * @fun 菜品删除接口(批量删除)
	 * url:http://localhost:8080/snug/deleteFoods?foods_token=foods00e5129d7,12345689
	 * */

	@ResponseBody
	@RequestMapping(value="/deleteFoods" , produces="text/html;charset=utf-8",method={RequestMethod.GET,RequestMethod.POST})
	public String deleteFoods(String foods_token){
		String string =	foodActionInterfaces.deleteFoods(foods_token);
		return string;
	}
	
	
	/*
	 * @author Lee
	 * @time 2018/1/14
	 * @fun 菜品查询接口
	 * url:http://localhost:8080/snug/queryFood
	 * */
	@ResponseBody
	@RequestMapping(value="/queryFood" , produces="text/html;charset=utf-8",method={RequestMethod.GET,RequestMethod.POST})
	public String queryFood(){
		String string = foodActionInterfaces.queryFood();
		return string;
	}
	/*
	 * @author Lee
	 * @time 2018/1/15
	 * @fun 菜品修改
	 * testUrl:
	  http://localhost:8080/snug/updateFoodInfo?
	  food_info_name = 水煮鱼&
	  food_info_sig=微辣，美味&
	  food_info_content=很好吃&
	  food_info_quantity=100&
	  food_info_unit=份&
	  food_info_sort=003&
	  food_info_order=大份&
	  food_info_material=鱼&
	  food_info_sold=1500&
	  food_info_type=家常菜&
	  food_info_price=100元&
	  food_info_token=foods649301f12&
	  food_info_material_cost=123&
	  food_info_image_word_content=5456&
	  food_info_images=15204100800884.png&
	  department_id=001
	 * */

  @ResponseBody 
  @RequestMapping(value="/updateFoodInfo",produces="text/html;charset=UTF-8",method = {RequestMethod.GET,RequestMethod.POST})
  public String updateFoodInfo(
		    String	food_info_name,
			String	food_info_sig,
			String	food_info_content,
			String	food_info_quantity,
			String	food_info_unit,
			String	food_info_sort,
			String	food_info_order,
			String	food_info_material,
			String	food_info_sold,
			String	food_info_type,
			String	food_info_price,
			String	department_id,
			String  food_info_token,
			String food_info_material_cost,
			String food_info_image_word_content,
			String food_info_images
		  ) 
  {
	  return foodActionInterfaces.updateFoodInfo(food_info_name, food_info_sig, food_info_content, food_info_quantity, food_info_unit, food_info_sort, food_info_order, food_info_material, food_info_sold, food_info_type, food_info_price, department_id, food_info_token,food_info_material_cost,food_info_image_word_content,food_info_images);
  }

@ResponseBody 
@RequestMapping(value="/updateFoodInfoNoImg",produces="text/html;charset=UTF-8",method = {RequestMethod.GET,RequestMethod.POST})
public String updateFoodInfoNoImg(
		    String	food_info_name,
			String	food_info_sig,
			String	food_info_content,
			String	food_info_quantity,
			String	food_info_unit,
			String	food_info_sort,
			String	food_info_order,
			String	food_info_material,
			String	food_info_sold,
			String	food_info_type,
			String	food_info_price,
			String	department_id,
			String  food_info_token,
			String food_info_material_cost,
			String food_info_image_word_content,
			String images_token_ref
		  ) 
{
	return foodActionInterfaces.updateFoodInfoNoImg(food_info_name, food_info_sig, food_info_content, food_info_quantity, food_info_unit, food_info_sort, food_info_order, food_info_material, food_info_sold, food_info_type, food_info_price, department_id, food_info_token,food_info_material_cost,food_info_image_word_content,images_token_ref);	 
}
	/*
	 * 查询默认的菜品信息
	 * http://localhost:8080/snug/queryDefaultFood
	 * */
	@ResponseBody
	@RequestMapping(value="/queryDefaultFood",produces="text/html;charset=utf-8",method={RequestMethod.GET,RequestMethod.POST})
	public String queryDefaultFood(){
		return foodActionInterfaces.queryDefaultFood();
	}
	/*
	 * 转菜
	 * http://localhost:8080/snug/turnFood?fromTableToken=1&toTableToken=0&bookdinner=2&foodInfo=3
	 * */
	@ResponseBody
	@RequestMapping(value="/turnFood",produces="text/html;charset=utf-8",method={RequestMethod.GET,RequestMethod.POST})
	public String turnFood(String fromTableToken,String toTableToken,String bookdinner,String turntobookdinner,String foodInfo){
		return foodActionInterfaces.turnFood(bookdinner,turntobookdinner,foodInfo);
	}
	//http://localhost:8080/snug/getFoodCost?material_mes=maojian001,10;dashuan001,10
	@ResponseBody
	@RequestMapping(value="/getFoodCost",produces="text/html;charset=utf-8",method={RequestMethod.GET,RequestMethod.POST})
	public String getFoodCost(String material_mes){
		return foodActionInterfaces.getFoodCost(material_mes);
	}
	
	//查询餐厅的供应时段
	//http://localhost:8080/snug/queryRuntime?department_info_token=resturnantf28e3f5e5
	@ResponseBody
	@RequestMapping(value="/queryRuntime",produces="text/html;charset=utf-8",method={RequestMethod.GET,RequestMethod.POST})
	public String queryRuntime(String department_info_token)
	{
		return foodActionInterfaces.queryRuntime(department_info_token);
	};
}
