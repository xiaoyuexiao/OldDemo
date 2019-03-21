/**
 * 
 */
package com.interfaces;

/**
 * @author lee
 * @time:2018骞�6鏈�11鏃�
 * @fun:
 * @param:
 */
public interface LoginInterface 
{
	public abstract String userLogin(String phone,String password);
	public abstract String shopLogin(String phone,String password);
	public abstract String userQuite(String phone);//用户退出登录
	public abstract String shopQuite(String phone);//商家退出登录
}
