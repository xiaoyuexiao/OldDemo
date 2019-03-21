package com.xiaoyuexiao.Apriori;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class AchieveApriori {
	/**
	 * 每一项中不同对象用"-"隔开，比如： A-B
	 * 					      A-B-C
	 * */
	//组合候选集，不统计次数
	public static ArrayList<String> Combination(Map<String, Integer> hmap)
	{
		//创建数组用来存储组合好的元素
		ArrayList<String> list = new ArrayList<String>();
		//根据传入参数判断是几项集（根据上一次的项数加一）
		int term_num = 0;
		for (Map.Entry<String, Integer> entry : hmap.entrySet())
		{
			if (entry.getKey() != null)
			{
				String[] strs = entry.getKey().split("-");//得到项元素的数组结果
				term_num = strs.length + 1;
			}
			break;
		}
		System.out.println("term_num（项数）:"+term_num);
		//组合
		for (Map.Entry<String, Integer> entry : hmap.entrySet())
		{
			for (Map.Entry<String, Integer> entry2 : hmap.entrySet())
			{
				//分解项元素
				String[] entry_strs = entry.getKey().split("-");
				String[] entry2_strs = entry2.getKey().split("-");
				//重新组合为term_num这么多项
					//先把第一个中的元素全部放到一个数组
				ArrayList<String> list2 = new ArrayList<String>();
				for (int i = 0; i < entry_strs.length; i++)
				{
					list2.add(entry_strs[i]);
				}
					//再一次放入（组合），不放重复的
				for (int j = 0; j < entry2_strs.length; j++) 
				{
					boolean flag = true;
					for (int z = 0; z < list2.size(); z++) 
					{
						if (entry2_strs[j].equals(list2.get(z)))
						{
							flag = false;
							break;
						}
					}
					if (flag)
					{
						list2.add(entry2_strs[j]);
					}
				}	
				//把list2中的数据用"-"组合成字符串
				String new_combaintion = "";
				if (list2.size()==term_num)
				{
					for (int i = 0; i < list2.size(); i++)
					{
						if(i<list2.size()-1)
						{
							new_combaintion = new_combaintion + list2.get(i) + "-";
						}
						else
						{
							new_combaintion = new_combaintion + list2.get(i);
						}
					}
				}
				//把组合元素添加到list中
				if (!new_combaintion.equals(""))
				{
					list.add(new_combaintion);
				}
			}
		}
		//System.out.println("去除重复元素之前C"+ term_num +":"+list);
		ArrayList<String> arr1 = list;
		ArrayList<String> arr2 = list;
		ArrayList<String> arr3 = new ArrayList<String>();
		for (int i = 0; i < arr1.size(); i++)
		{
			String str1 = arr1.get(i);
			boolean flag = true;
			String[] arr_str1 = str1.split("-");
			
			for (int j = i+1; j < arr2.size(); j++)
			{
				String str2 = arr2.get(j);
				String[] arr_str2 = str2.split("-");
				if (j!=i)
				{
					//判断两个字符串是否一样（顺序不影响）
					int count = 0;
					for (int k = 0; k < arr_str2.length; k++)
					{
						for (int k2 = 0; k2 < arr_str1.length; k2++)
						{
							if (arr_str2[k].equals(arr_str1[k2]))
							{
								++count;
								break;
							}
						}
					}
					if (count==arr_str2.length) 
					{
						flag = false;
						break;
					}
				}
				
			}
			
			if (flag==true)
			{
				arr3.add(str1);
			}
			
		}
		//System.out.println("去除重复元素之后C"+ term_num +":" + arr3);
		return arr3;
	}
	//查询(在数据记录中查询出现次数)
	public static Map<String,Integer> Match(Map<String, ArrayList<String>> hmap,ArrayList<String> list)
	{
		Map<String, Integer> map = new HashMap<String, Integer>();
		
		for (Entry<String, ArrayList<String>> entry:hmap.entrySet())
		{
			ArrayList<String> list2 = entry.getValue();
			//遍历项集
			for (String string : list)
			{
				String[] strs = string.split("-");
				int count1 = 0;
				for (int i = 0; i < strs.length; i++)
				{
					if (list2.indexOf(strs[i])!=-1)
					{
						//包含有
						++count1;
					}
					else
					{
						//不包含有
						break;
					}
				}
				if (count1==strs.length)
				{
					if (map.containsKey(string))
					{
						int value = map.get(string);
						map.put(string, ++value);
					}
					else
					{
						map.put(string, 1);
					}
				}
			}
		}
		return map;
	}
	//剪枝
	public static Map<String,Integer> Cut(int min_support,Map<String,Integer> map)
	{
		Map<String,Integer> map2 = new HashMap<String, Integer>();
		for (Map.Entry<String,Integer> entry : map.entrySet())
		{
			if (entry.getValue()>=min_support)
			{
				map2.put(entry.getKey(), entry.getValue());
			}
		}
		return map2;
	}
	//得到L1
	public static Map<String, Integer> getL1(Map<String,ArrayList<String>> data_record)
	{
		Map<String,Integer> C1 = new HashMap<String, Integer>();
		for (Map.Entry<String,ArrayList<String>> entry : data_record.entrySet())
		{
			ArrayList<String> valueStrings = entry.getValue();//消费记录
			//遍历每一条的记录
			for (String string : valueStrings)
			{
				//判断C1中是否存在此项商品
				boolean flag = true;
				for (Map.Entry<String,Integer> entry2 : C1.entrySet())
				{
					if (entry2.getKey().equals(string))
					{
						flag = false;
					}
				}
				if (flag)
				{
					C1.put(string,1);
				}
				else
				{
					int a = C1.get(string);
					C1.put(string,++a);
				}
			}
		}
		System.out.println("C1:"+C1);
		//剪枝
		Map<String,Integer> L1 = new HashMap<String, Integer>();
		for (Map.Entry<String,Integer> entry : C1.entrySet())
		{
			if (entry.getValue()>=2)
			{
				L1.put(entry.getKey(), entry.getValue());
			}
		}
		return L1;
	}
	
	public static void summary(Map<String,Integer> L1,Map<String,ArrayList<String>> data_record)
	{
		//组合
		ArrayList<String>  list = AchieveApriori.Combination(L1);
		//项数
		int a = list.get(0).split("-").length;
		//匹配
		Map<String, Integer> L2 = AchieveApriori.Match(data_record, list);
		System.out.println("C"+ a + ":" + L2);
		//剪枝
		Map<String, Integer> map2 = AchieveApriori.Cut(2,L2);
		System.out.println("剪枝后的L"+ a + ":" + map2);
		if(map2.size()>1)
		{
			summary(L2, data_record);
		}
	}
	
	public static void main(String[] args)
	{
		//购买记录数据
		Map<String,ArrayList<String>> data_record = new HashMap<String, ArrayList<String>>();//数据记录(购买记录)
		
		ArrayList<String> data_l1 = new ArrayList<String>();
		data_l1.add("A");
		data_l1.add("C");
		data_l1.add("D");
		
		data_l1.add("F");
		data_record.put("1", data_l1);
		
		ArrayList<String> data_l2 = new ArrayList<String>();
		data_l2.add("B");
		data_l2.add("C");
		data_l2.add("E");
		
		data_l2.add("F");
		data_record.put("2", data_l2);
		
		ArrayList<String> data_l3 = new ArrayList<String>();
		data_l3.add("A");
		data_l3.add("B");
		data_l3.add("C");
		data_l3.add("E");
		data_record.put("3", data_l3);
		
		ArrayList<String> data_l4 = new ArrayList<String>();
		data_l4.add("B");
		data_l4.add("E");
		
		data_l4.add("F");
		data_record.put("4", data_l4);
		System.out.println("分析记录有：" + data_record.toString());
		//分析的对象(商品信息)
		ArrayList<String> data_obj = new ArrayList<String>();
		data_obj.add("A");
		data_obj.add("B");
		data_obj.add("C");
		data_obj.add("D");
		data_obj.add("E");
		data_obj.add("F");
		System.out.println("原始对象有：" + data_obj);
		
		//找到C1
		Map<String, Integer> L1 = AchieveApriori.getL1(data_record);
		System.out.println("L1:"+L1);
		//L1组合
//		ArrayList<String>  list = AchieveApriori.Combination(L1);
//		System.out.println("C2:"+ list);
//		Map<String, Integer> L2 = AchieveApriori.Match(data_record, list);
//		System.out.println("L2:"+L2);
//		Map<String, Integer> map2 = AchieveApriori.Cut(2,L2);
//		System.out.println("剪枝后的L2：" + map2);
//		
//		ArrayList<String>  list2 = AchieveApriori.Combination(L2);
//		System.out.println("C3:"+ list2);
//		Map<String, Integer> L3 = AchieveApriori.Match(data_record, list2);
//		System.out.println("L3:"+L3);
//		Map<String, Integer> map3 = AchieveApriori.Cut(2,L3);
//		System.out.println("剪枝后的L3：" + map3);
		System.out.println("=================================");
		AchieveApriori.summary(L1, data_record);
		}

}
