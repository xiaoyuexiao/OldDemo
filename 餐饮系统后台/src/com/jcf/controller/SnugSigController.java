package com.jcf.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jcf.interfaces.SigActionInterfaces;

@Controller
public class SnugSigController{
	@Autowired
	SigActionInterfaces sigActionInterfaces;
	/*
	 * @author Lee
	 * @time 2018/1/12
	 * @fun 标签插入接口
	 * http:localhost:8080/snug/insertSig?sig_name=很少见啊&sig_type=123&department_id=123
	 * */
	@ResponseBody 
	@RequestMapping(value="/insertSig",produces="text/html;charset=UTF-8",method = {RequestMethod.GET,RequestMethod.POST})
	public String insertSig(String sig_name,String sig_type,String department_id,String token) {
		return sigActionInterfaces.insertSig(sig_name, sig_type, department_id,token);
	}
	
	/*
	 * @author Lee
	 * @time 2018/1/15
	 * @fun 标签查询
	 * http://localhost:8080/snug/querySigList
	 * */
	@ResponseBody 
	@RequestMapping(value="/querySigList",produces="text/html;charset=UTF-8",method = {RequestMethod.GET,RequestMethod.POST})
	public String querySigList(String department_id) {
		return sigActionInterfaces.querySigList(department_id);
	}
	/*
	 * @author Lee
	 * @time 2018/1/15
	 * @fun 标签修改接口
	 * url:http://localhost:8080/snug/updateSig?sig_name=微辣，好吃&sig_type=食物&department_id=001&token=456
	 * */
	@ResponseBody 
	@RequestMapping(value="/updateSig",produces="text/html;charset=UTF-8",method = {RequestMethod.GET,RequestMethod.POST})
	public String updateSig(String sig_name,String sig_type,String department_id,String token) {
		return sigActionInterfaces.updateSig(sig_name, sig_type, department_id, token);
	}
	
	/*
	 * @author Lee
	 * @time 2018/1/15
	 * @fun 标签隐藏
	 * url:http://localhost:8080/snug/deleteSig?token=123
	 * */
	@ResponseBody 
	@RequestMapping(value="/deleteSig",produces="text/html;charset=UTF-8",method = {RequestMethod.GET,RequestMethod.POST})
	public String deleteSig(String token) {
		return sigActionInterfaces.deleteSig(token);
	}
	
	/*
	 * @author Lee
	 * @time 2018/1/12
	 * @fun 标签插入接口
	 * http:localhost:8080/snug/insertInfo
	 * */
	@ResponseBody 
	@RequestMapping(value="/insertSigs",produces="text/html;charset=UTF-8",method = {RequestMethod.GET,RequestMethod.POST})
	public String insertSigs(String sigList,String sig_type,String department_id) {
		return sigActionInterfaces.insertSigs(sigList,sig_type,department_id);
	}
}
