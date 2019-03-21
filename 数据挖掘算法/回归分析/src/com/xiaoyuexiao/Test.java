package com.xiaoyuexiao;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Test {

	public static void main(String[] args)
	{
			Get2018GDP gt = new Get2018GDP();
			//原始数据数组
			ArrayList<Double> list = new ArrayList<Double>();
			list.add(319515.5);//2008
			list.add(349081.4);//2009
			list.add(413030.3);//2010
			list.add(489300.6);//2011
			list.add(540367.4);//2012
			list.add(595244.4);//2013
			list.add(643974.0);//2014
			list.add(689052.1);//2015
			list.add(743585.5);//2016
			list.add(827121.9);//2017
			//测试数据
//			list.add(17.56);
//			list.add(19.63);
//			list.add(23.98);
//			list.add(31.64);
//			list.add(43.72);
//			list.add(36.98);
//			list.add(47.18);
//			list.add(64.47);
//			list.add(58.35);
//			list.add(51.40);
//			list.add(71.42);
//			list.add(106.67);
//			list.add(129.85);
//			list.add(136.69);
//			list.add(145.27);
//			list.add(147.52);
//			list.add(158.25);
//			list.add(163.00);
			//设置了list值
			//得到截距
			double b0 = gt.getIntercept(list);
			//得到斜率
			double b1 = gt.getSlope(list);
			//得到预测值
			System.out.println("=======根据2008到2017年GDP预测=======");
			Scanner scanner = new Scanner(System.in);
			
			while(true)
			{
				System.out.println("请输入预测的年份（格式:xxxx）:");
				
				try
				{
					int year;//预测年-原始数据第一年(此处为2008)+1    比如：11 = 2018 - 2008 + 1
					year = scanner.nextInt();
					System.out.println(year + "年的GDP预测值为：" + gt.getExpect(b0, b1,year-2008+1));
				}
				catch (Exception e) 
				{
					if (e instanceof InputMismatchException)
					{
						System.out.println("请输入正确年份！！！");
					}
					break;
				}
				
			}
	}

}
