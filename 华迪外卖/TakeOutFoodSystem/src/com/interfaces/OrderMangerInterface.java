package com.interfaces;

public interface OrderMangerInterface {
	//用户下单
	public abstract String addOrder(String clear_address,String allmoney,String userphone,String shopphone,String good_str);
	//通过用户电话查询历史订单
	public abstract String queryOrdersByUserphone(String user_phone);
	//通过订单编号查询店铺信息和评价星级
	public abstract String queryShopNameAndStarByorderid(String orderid);
	
}
