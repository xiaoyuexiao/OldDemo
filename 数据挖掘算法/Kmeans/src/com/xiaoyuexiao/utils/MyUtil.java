/**
 * 
 */
package com.xiaoyuexiao.utils;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;

import com.xiaoyuexiao.Kmeans.Point;

/**
 * @author Lee
 * @time:2018-3-26
 * @fun:
 * @param:
 */
public class MyUtil 
{
	public static double getDistance(Point x,Point y)
	{
		return Math.sqrt((x.getX()-y.getX())*(x.getX()-y.getX())+(x.getY()-y.getY())*(x.getY()-y.getY()));	
	}
	//在list中随机取k个元素
	public static ArrayList<Point> getRandomK(ArrayList<Point> list,int k)
	{
		ArrayList<Point> list2 = new ArrayList<Point>();
		int random = 0;
		int len = 0;
		while(len!=k)
		{
			random = (int) (Math.random()*list.size());
			Point p = list.get(random);
			if (!list2.contains(p)) 
			{
				list2.add(p);
				len++;
			}
		}
		return list2;
	}
	
	public static void outName(ArrayList<Point> list)
	{
		for (Point point : list)
		{
			System.out.println("簇中心:  "+point.getPname() + "  x:  "+ point.getX() + "  y:  "+ point.getY() );
		}
	}
	
	public static double m2(double num)
	{ 
		return (double)Math.round(num*100)/100;
	}
}
