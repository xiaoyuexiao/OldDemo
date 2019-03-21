package com.interfaces;

public interface QueryShopInterface
{
	//根据学校和商家类型查询附近的商家
	public abstract String queryShopBySchool(String schoolid,String shop_type);
	//根据账号信息查询学校以及店铺信息
	public abstract String queryShopByUserPhone(String user_phone);
}
