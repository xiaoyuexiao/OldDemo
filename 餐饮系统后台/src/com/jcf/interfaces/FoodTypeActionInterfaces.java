package com.jcf.interfaces;


public interface FoodTypeActionInterfaces {
	//添加分类
	public abstract String insertFoodType(String food_type_name,String food_type_father,String food_type_sort,String food_type_runtime,String food_type_flag,String department_id,String food_type_images);
	//隐藏分类
	public abstract String deleteFoodType(String food_type_token);
	//修改分类
	public abstract String updateFoodType(String food_type_name,String food_type_father,String food_type_sort,String food_type_runtime,String food_type_flag,String department_id,String food_type_images,String food_type_token);
	//修改分类，无图片
	public abstract String updateFoodTypeNoImg(String food_type_name,String food_type_father,String food_type_sort,String food_type_runtime,String food_type_flag,String department_id,String food_type_images_ref,String food_type_token);
	//分类显示（层级）
	public abstract String queryFoodType();
}
