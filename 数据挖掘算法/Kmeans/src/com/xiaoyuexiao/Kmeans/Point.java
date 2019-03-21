/**
 * 
 */
package com.xiaoyuexiao.Kmeans;

/**
 * @author Administrator
 * @time:2018-3-26
 * @fun:
 * @param:
 */
public class Point 
{
	private String pname;
	private double x = 0;
	private double y = 0;
	
	public Point(int x,int y,String pname)
	{
		this.x = x;
		this.y = y;
		this.pname = pname;
	}
	
	public Point(double x,double y,String pname)
	{
		this.x = x;
		this.y = y;
		this.pname = pname;
	}
	
	public String getPname() {
		return pname;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}
	public double getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public double getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
}
