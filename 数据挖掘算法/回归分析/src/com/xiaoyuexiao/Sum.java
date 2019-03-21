package com.xiaoyuexiao;

import java.util.ArrayList;

public class Sum
{
	private ArrayList<Double> list;
	public Sum(ArrayList<Double> list)
	{
		this.list = list;
	}
	public double getSum()
	{
		double sum = 0;
		for (int i = 0; i < list.size(); i++) 
		{
			sum = sum + list.get(i);
		}
		return sum;
	}
}
