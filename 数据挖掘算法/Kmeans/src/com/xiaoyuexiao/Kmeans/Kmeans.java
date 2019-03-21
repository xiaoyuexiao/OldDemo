/**
 * 
 */
package com.xiaoyuexiao.Kmeans;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.xiaoyuexiao.utils.MyUtil;

/**
 * @author lee
 * @time:2018-3-26
 * @fun:1.原始数据用list来存，数据元素为Point对象
 * 		2.创建k个list来表示k个簇中的元素
 * 		3.用一个Map来存k个簇，map中entry的键为簇中心，值为C（簇）=> [{簇中心1：[簇元素1，出元素2]},{簇中心2：[簇元素1，出元素2]}]
 * @param:
 */
public class Kmeans
{
	//private Map<Integer, Integer> centerPoint;
	private int k = 3;//初始化为2
	private static int center_count = 1;
	//Point 表示质心，ArrayList<Point>表示簇
	//得到第一次聚类的Map
	public Map<Point, ArrayList<Point>> getC(ArrayList<Point> data_soure)
	{
		Map<Point, ArrayList<Point>> map = new HashMap<Point,ArrayList<Point>>();//用于存储簇
		//1.从原始数据中随机取k个元素(不能重复)作为k个簇的中心
		ArrayList<Point> list_k = MyUtil.getRandomK(data_soure, k);
		for (int i = 0; i < k; i++)
		{
			//取随机数，随机取一个数据源中的数据作为质心
			List<Point> list = new ArrayList<Point>();
			map.put(list_k.get(i),(ArrayList<Point>) list);
			//输出簇
			System.out.println("第1次随机选择的簇：" + list_k.get(i).getPname());
		}
		
		//2.遍历原始数据，将不同的所有元素添加到对应的簇中
		Point point_flag = null;//用来存距离较近的簇的点
		for (Point point : data_soure)
		{
			for (Entry<Point, ArrayList<Point>> entry : map.entrySet())
			{
				if (point_flag == null)
				{
					point_flag = entry.getKey();
				}
				else
				{
					double dis_entry = MyUtil.getDistance(entry.getKey(),point);
					double dis_last = MyUtil.getDistance(point_flag, point);
					if (dis_entry<dis_last)
					{
						point_flag = entry.getKey();
					}
				}
			}
			map.get(point_flag).add(point);
			System.out.println("向簇"+point_flag.getPname()+"添加了距离最近的元素："+ point.getPname() + "  距离为：  " + MyUtil.getDistance(point_flag,point));
		}
		//输出簇中的元素
		System.out.println("簇中的值有：");
		for (Entry<Point, ArrayList<Point>> entry : map.entrySet())
		{
			System.out.print("key:" + entry.getKey().getPname() + "  value:  ");
			for (Point point : entry.getValue()) {
				System.out.print(point.getPname()+"  ");
			}
			System.out.println();
		}
		return map;
	}
	//多次聚类
	public Map<Point, ArrayList<Point>> getLastResult(Map<Point, ArrayList<Point>> map,ArrayList<Point> data_soure)
	{
		//1.计算新的簇中心
		ArrayList<Point> new_center_list = new ArrayList<Point>();
		if (center_count==1)
		{
			int z = 0;
			for (Entry<Point, ArrayList<Point>> entry : map.entrySet())
			{
				z++;
				ArrayList<Point> list = entry.getValue();
				double all_x = 0;
				double all_y = 0;
				int len = list.size();
				for (Point point : list)
				{
					all_x = all_x + point.getX();
					all_y = all_y + point.getY();
				}
				
				//簇中心
				Point new_center = new Point(all_x/len, all_y/len,"C"+z);
				new_center_list.add(new_center);
			}
		}
		else
		{
			for (Entry<Point, ArrayList<Point>> entry : map.entrySet())
			{
				ArrayList<Point> list = entry.getValue();
				double all_x = 0;
				double all_y = 0;
				int len = list.size();
				for (Point point : list)
				{
					all_x = all_x + point.getX();
					all_y = all_y + point.getY();
				}
				
				//簇中心
				Point new_center = new Point(all_x/len, all_y/len,entry.getKey().getPname());
				new_center_list.add(new_center);
			}
		}
		
		System.out.println("==================更新簇中心"+(center_count++)+"次==================");
		MyUtil.outName(new_center_list);
		//2.创建存簇的map
		//3.遍历原数据，计算原数据中的值并归簇
		Map<Point, ArrayList<Point>> map2 = new HashMap<Point,ArrayList<Point>>();//用于存储簇
		for (int i = 0; i < new_center_list.size(); i++)
		{
			List<Point> list = new ArrayList<Point>();
			map2.put(new_center_list.get(i),(ArrayList<Point>) list);
			//输出簇
			//System.out.println("第"+center_count+"次的簇中心：" + new_center_list.get(i).getPname());
		}
		
		Point point_flag2 = null;//用来存距离较近的簇的点
		for (Point point : data_soure)
		{
			for (Entry<Point, ArrayList<Point>> entry2 : map2.entrySet())
			{
				if (point_flag2 == null)
				{
					point_flag2 = entry2.getKey();
				}
				else
				{
					double dis_entry = MyUtil.getDistance(entry2.getKey(),point);
					double dis_last = MyUtil.getDistance(point_flag2, point);
					if (dis_entry<dis_last)
					{
						point_flag2 = entry2.getKey();
					}
				}
			}
			map2.get(point_flag2).add(point);
			System.out.println("向簇"+point_flag2.getPname()+"添加了距离最近的元素："+ point.getPname() + "  距离为：  " + MyUtil.getDistance(point_flag2,point));
		}
		
		System.out.println("============簇中的值有：=========");
		for (Entry<Point, ArrayList<Point>> entry : map2.entrySet())
		{
			System.out.print("key:" + entry.getKey().getPname() + "  value:  ");
			for (Point point : entry.getValue()) {
				System.out.print(point.getPname()+"  ");
			}
			System.out.println();
		}
		
		//4.求簇的方差和并判断是否继续
		double sum_variance = 0;//方差和
		for (Entry<Point,ArrayList<Point>> entry : map2.entrySet())
		{
			double ave = 0;
			Point p_key = entry.getKey();
			ArrayList<Point> list = entry.getValue();
			ArrayList<Double> dis = new ArrayList<Double>();
			for (Point point : list)
			{
				ave = ave + MyUtil.getDistance(p_key, point);
				dis.add(MyUtil.getDistance(p_key, point));
			}
			ave = ave/list.size();
			System.out.println(p_key.getPname() + "平均值为："+ ave);
			
			double variance = 0;
			for (Double double1 : dis)
			{
				variance = variance + (ave - double1)*(ave - double1);
			}
			System.out.println("方差为：" + variance/dis.size() );
			sum_variance = sum_variance + variance/dis.size();
		}
		System.out.println("方差和为：" + sum_variance);
		if (center_count<25)
		{
			Kmeans kmeans = new Kmeans();
			kmeans.getLastResult(map2,data_soure);
		}
		else
		{
			//5.返回最终的簇
			return map2;
		}
		return null;
	}
	
	public static void main(String[] args)
	{
//		Point p1 = new Point(14.4, 19.0,"p1");
//		Point p2 = new Point(24.2, 48.0,"p2");
//		Point p3 = new Point(34.4, 48.20,"p3");
//		Point p4 = new Point(14.44, 44.50,"p4");
//		Point p5 = new Point(75.1, 87.70,"p5");
//		Point p6 = new Point(65.8, 10,"p6");
//		Point p7 = new Point(17.4, 12.50,"p7");
//		Point p8 = new Point(84.65, 15.230,"p8");
//		Point p9 = new Point(15.65, 14.40,"p9");
//		Point p10 = new Point(17.65, 12.50,"p10");
//		Point p11 = new Point(18.55, 25.0,"p11");
//		Point p12 = new Point(23.85, 11.0,"p12");
		ArrayList<Point> list = new ArrayList<Point>();
		int count = 0;
		for (int i = 0; i < 4; i++)
		{
			double x = MyUtil.m2(Math.random()*4);
			double y = MyUtil.m2(Math.random()*4);
			Point point = new Point(x, y,"p"+(++count));
			list.add(point);
		}
		
		for (int i = 0; i < 4; i++)
		{
			double x = MyUtil.m2(10 + Math.random()*4);
			double y = MyUtil.m2(10 + Math.random()*4);
			Point point = new Point(x, y,"p"+(++count));
			list.add(point);
		}
		
		
		for (int i = 0; i < 4; i++)
		{
			double x = MyUtil.m2(100 + Math.random()*4);
			double y = MyUtil.m2(100 + Math.random()*4);
			Point point = new Point(x, y,"p"+(++count));
			list.add(point);
		}
		
//		Point p1 = new Point(1.2, 1.6,"p1");
//		Point p2 = new Point(2, 3.0,"p2");
//		Point p3 = new Point(34.4, 48.20,"p3");
//		Point p4 = new Point(14.44, 44.50,"p4");
//		Point p5 = new Point(75.1, 87.70,"p5");
//		Point p6 = new Point(65.8, 10,"p6");
//		Point p7 = new Point(17.4, 12.50,"p7");
//		Point p8 = new Point(84.65, 15.230,"p8");
//		Point p9 = new Point(15.65, 14.40,"p9");
//		Point p10 = new Point(17.65, 12.50,"p10");
//		Point p11 = new Point(18.55, 25.0,"p11");
//		Point p12 = new Point(23.85, 11.0,"p12");
//		
//		list.add(p1);
//		list.add(p2);
//		list.add(p3);
//		list.add(p4);
//		list.add(p5);
//		list.add(p6);
//		list.add(p7);
//		list.add(p8);
//		list.add(p9);
//		list.add(p10);
//		list.add(p11);
//		list.add(p12);
		System.out.println("===========原始数据为：=============");
		for (Point point : list) {
			System.out.println("name:" + point.getPname() + "  x:" + point.getX() + "  y:"+point.getY());
		}
		Kmeans kmeans = new Kmeans();
		Map<Point, ArrayList<Point>> map = kmeans.getC(list);
		kmeans.getLastResult(map, list);
//		System.out.println("map:"+map.toString());
//		System.out.println(map.get(p).get(0).getPname());
//		MyUtil.outName(MyUtil.getRandomK(list, 3));
	}
}
