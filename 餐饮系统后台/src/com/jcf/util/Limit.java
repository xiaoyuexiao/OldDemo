package com.jcf.util;

public class Limit {
	public static int QueryLimit(String page,int pagesize){
	
		int pagenum=Integer.parseInt(page);
		
		int pageIndex=(pagenum-1)*pagesize;
		
		return pageIndex;		
	}
}
