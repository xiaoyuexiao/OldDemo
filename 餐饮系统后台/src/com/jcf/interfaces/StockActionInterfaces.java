package com.jcf.interfaces;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

public interface StockActionInterfaces {
/*==================================库存信息=======================================*/	
	
	//默认商品列表的查询(名称，属性，库存数量，单位，出事单价，保质期，token)，默认是1级 ， 2级。
	public abstract String queryGoodsMes();
	//主材料的列表(firstToken：一级分类token,Towtoken:二级分类token)
	public abstract String queryGoodsByFirAndTow(String firstToken , String Towtoken);
	//模糊搜索（商品名称的模糊搜索）
	public abstract String queryAllGoodsMes(String key_word);
/*==================================入库管理=======================================*/	
	
	//默认显示入库记录的前20条,第一条详情显示
	public abstract String queryInGoodsMes(String status_flag);
	//单个申购单的查询
	public abstract String queryInGoodMes(String attence_info_token);
	//入库记录的模糊搜索（编号，时间）
	public abstract String queryLikeNameAndTime(String key_word);

/*==================================出库管理=======================================*/	
	
	//添加出库信息,减库存,out_material_mes:出库信息
	public abstract String addOutGoodsMes(String out_material_mes,String flag);
	//查询出库信息的历史记录a
	public abstract String queryHistoryOutGoods();
	//出入库的模糊查询(manage_type:表明是出库还是入库，status_type：表示是待审核等状态)
	public abstract String queryByTimeAndName(String key_name,String key_time,String manage_type,String status_type);
/*==================================报损中心=======================================*/
	//添加报损信息,
	
/*==================================物品管理=======================================*/
	//商品入库添加
	public abstract String addGoods
	(
		String name,
		String type_one,
		String type_sec,
		String unit,
		@RequestParam CommonsMultipartFile[] file,
		String in_price,
		String min_inventory,
		String max_inventory,
		String out_price,
		String guarantee,
		String maintain,
		String servicelife,
		String department_id
	);

}
