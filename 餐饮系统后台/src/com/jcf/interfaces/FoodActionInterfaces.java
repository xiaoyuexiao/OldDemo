package com.jcf.interfaces;


public interface FoodActionInterfaces {

	/*
	 * @author Lee
	 * @time 2018/1/14
	 * @fun 菜品插入接口
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
	public abstract String insertFood(
			String food_info_name,String food_info_images,String food_info_sig,
			String food_info_content, String food_info_quantity,
			String food_info_unit, String food_info_sort,
			String food_info_order, String food_info_material,
			String food_info_sold, String food_info_type,
			String food_info_price, String department_id,
			String food_info_material_cost, String food_info_image_word_content);

	/*
	 * @author Lee
	 * @time 2018/1/14
	 * @fun 菜品删除接口
	 * url:http://localhost:8080/snug/deleteFood?token=foods00e5129d7
	 * */
	
	public abstract String deleteFood(String token);
	
	/*
	 * @author Lee
	 * @time 2018/2/8
	 * @fun 菜品删除接口，批量删除
	 * url:http://localhost:8080/snug/deleteFoods?foods_token=foods00e5129d7
	 * */
	
	public abstract String deleteFoods(String foods_token);

	public abstract String queryFood();

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
	  food_type_sort=003&
	  food_info_order=大份&
	  food_info_material=鱼&
	  food_info_sold=1500&
	  food_info_type=家常菜&
	  food_info_price=100元&
	  department_id=001&
	  food_info_token=foods17d970958
	 * */
	public abstract String updateFoodInfo(String food_info_name,
			String food_info_sig, String food_info_content,
			String food_info_quantity, String food_info_unit,
			String food_info_sort, String food_info_order,
			String food_info_material, String food_info_sold,
			String food_info_type, String food_info_price,
			String department_id, String food_info_token,
			String food_info_material_cost, String food_info_image_word_content,
			String food_info_images);
	/*
	 * @author Lee
	 * @time 2018/1/15
	 * @fun 菜品修改
	 * testUrl:
	  http://localhost:8080/snug/updateFoodInfoNoImg?
	  food_info_name = 水煮鱼&
	  food_info_sig=微辣，美味&
	  food_info_content=很好吃&
	  food_info_quantity=100&
	  food_info_unit=份&
	  food_type_sort=003&
	  food_info_order=大份&
	  food_info_material=鱼&
	  food_info_sold=1500&
	  food_info_type=家常菜&
	  food_info_price=100元&
	  department_id=001&
	  food_info_token=foods17d970958
	 * */
	public abstract String updateFoodInfoNoImg(String food_info_name,
			String food_info_sig, String food_info_content,
			String food_info_quantity, String food_info_unit,
			String food_type_sort, String food_info_order,
			String food_info_material, String food_info_sold,
			String food_info_type, String food_info_price,
			String department_id, String food_info_token,
			String food_info_material_cost, String food_info_image_word_content,
			String images_token_ref);
	
	//菜品设置默认显示
	public abstract String queryDefaultFood();
	//菜品成本计算(material_mes:每组包含材料token和数量，token和数量以逗号隔开，不同材料以分好隔开)比如：牛肉,200;土豆,100
	public abstract String getFoodCost(String material_mes);
	//转菜(foodInfo:(菜的token+份数))
	public abstract String turnFood(String bookdinner,String turntobookdinner,String foodInfo);
	//查询餐厅的供应时段
	public abstract String queryRuntime(String department_info_token);
	//查询原材料
	public abstract String queryFoodMaterial();
	//点菜,bookdinner_token参数为传入订单的token(一张桌子一个订单),foods_mes:添加菜品的json字符串
	public abstract String orderFoods(String bookdinner_token,String foods_mes);
}