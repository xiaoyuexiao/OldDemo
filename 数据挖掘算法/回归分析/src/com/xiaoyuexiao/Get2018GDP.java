package com.xiaoyuexiao;

import java.util.ArrayList;

public class Get2018GDP {
	
	public double getIntercept(ArrayList<Double> list)
	{
		double yi1 = 0;
		double t = 0;
		
		for (int i = 0; i < list.size(); i++)
		{
			yi1 = yi1 + list.get(i);
		}
		
		for (int j = 0; j < list.size(); j++)
		{
			t = t + (j+1); 
		}
		
		double y_ = yi1/list.size();//得到均值
		double t_ = t/list.size();//得到t均值
		Get2018GDP gt = new Get2018GDP();
		double b1 = gt.getSlope(list);
		return y_ - b1*t_;
	}


	public double getSlope(ArrayList<Double> list)
	{
		double fenzi_pre = 0;
		double fenzi_after = 0;
		double fenzi = 0;
		double t_all = 0;
		double y_all = 0;
		//得到分子
		for (int i = 0; i < list.size(); i++)
		{
			fenzi_pre = fenzi_pre + (i+1)*list.get(i);
		}
		
		for (int i = 0; i < list.size(); i++)
		{
			t_all = t_all + (i+1);
			y_all = y_all + list.get(i);
		}
		fenzi_after = t_all * y_all;
		
		fenzi = fenzi_pre * list.size() - fenzi_after;
		//得到分母
		double fenmu = 0;
		double fenmu_pre = 0;
		double fenmu_after = 0;
		double fenmu_t2 = 0;
		double fenmu_2t = 0;
		for (int i = 0; i < list.size(); i++)
		{
			fenmu_t2 = fenmu_t2 + (i+1)*(i+1);
			fenmu_2t = fenmu_2t + (i+1);
		} 
		fenmu_pre = fenmu_t2 * list.size();
		fenmu_after = fenmu_2t * fenmu_2t;
		fenmu = fenmu_pre - fenmu_after;
		return fenzi/fenmu;
	}

	public double getExpect(double b0,double b1,int years)
	{
		double e = b0 + b1*years;
		return e;
	}
	
}
