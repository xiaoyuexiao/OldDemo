package com.jcf.interfaces;

public interface UserActionInterfaces {
	/*
	 * 发送验证码接口
	 * @param phone:电话
	 * */
	public abstract String verifycode(String phone);
	/*
	 * 注册
	 * @param phone:电话
	 * @param snum:验证码
	 * @param pwd:密码
	 * @param img:头像
	 * */
	public abstract String register(String phone,String snum,String pwd,String img);
	/*
	 * 登录
	 * @param phone:电话
	 * @param pwd:密码
	 * */
	public abstract String login(String phone,String pwd);
	/*
	 * 登录
	 * @param phone:电话
	 * @param snum:验证码
	 * @param pwd:新密码
	 * */
	public abstract String changepwd(String phone,String snum,String pwd);
}
