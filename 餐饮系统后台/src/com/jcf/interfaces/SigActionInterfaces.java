package com.jcf.interfaces;


public interface SigActionInterfaces {

	/*
	 * @author Lee
	 * @time 2018/1/12
	 * @fun 标签插入接口
	 * */
	//http:localhost:8080/snug/insertInfo?sig_name=很少见啊&sig_type=123&department_id=123
	public abstract String insertSig(String sig_name,String sig_type,
			String department_id,String sig_token);
	/*
	 * @author Lee
	 * @time 2018/1/15
	 * @fun 标签查询
	 * http://localhost:8080/snug/getsiglist
	 * */
	public abstract String querySigList(String department_id);
	
	/*
	 * @author Lee
	 * @time 2018/1/15
	 * @fun 标签修改接口
	 * */
	public abstract String updateSig(String sig_name,String sig_type,String department_id,String token);
	/*
	 * @author Lee
	 * @time 2018/1/15
	 * @fun 标签隐藏接口
	 * */
	public abstract String deleteSig(String token);
	/*
	 * @author Lee
	 * @time 2018/1/15
	 * @fun 标签隐藏接口
	 * */
	public abstract String insertSigs(String sigList,String sig_type,String department_id);
}