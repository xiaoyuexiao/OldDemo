package com.xiaoyuexiao.domain;

import java.util.ArrayList;

public class BuysInformations
{
	public Buyer getBuyer() {
		return buyer;
	}
	public void setBuyer(Buyer buyer) {
		this.buyer = buyer;
	}
	public Buyer buyer;//存放客户的id
	private ArrayList<String> goods;//存放购买的商品id
	
	public ArrayList<String> getGoods() {
		return goods;
	}
	public void setGoods(ArrayList<String> goods) {
		this.goods = goods;
	}
}
