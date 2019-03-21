package com.interfaces;

public interface EvaluateInterface
{
	public abstract String userEvaluate(String packing,String flavor,String distribution,String message,String orderid,String userphone);
	public abstract String queryEvlaluateListByOrderid(String orderid);
}
