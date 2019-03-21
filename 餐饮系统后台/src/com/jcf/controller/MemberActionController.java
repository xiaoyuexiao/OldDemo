package com.jcf.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jcf.interfaces.MemberActionInterfaces;

@Controller
public class MemberActionController {
	@Autowired
	MemberActionInterfaces memberActionInterfaces;
	@ResponseBody
	@RequestMapping(value="/queryMembers",produces="text/html;charset=UTF-8",method = {RequestMethod.GET,RequestMethod.POST})
	public String queryMembers(){
		return	memberActionInterfaces.queryMembers();
	}
	
	@ResponseBody
	@RequestMapping(value="/deleteMember",produces="text/html;charset=UTF-8",method={RequestMethod.GET,RequestMethod.POST})
	public String deleteMember(String token){
		return memberActionInterfaces.deleteMember(token);
	}
	
	
	@ResponseBody
	@RequestMapping(value="/queryFuzzyMembers",produces="text/html;charset=UTF-8",method={RequestMethod.GET,RequestMethod.POST})
	public String queryFuzzyMembers(String search_condition){
		return memberActionInterfaces.queryFuzzyMembers(search_condition);
	}
	
	//查询所有会员
	//http://localhost:8080/snug/queryMembersList
	@ResponseBody
	@RequestMapping(value="/queryMembersList",produces="text/html;charset=UTF-8",method={RequestMethod.GET,RequestMethod.POST})
	public String queryMembersList()
	{
		return memberActionInterfaces.queryMembersList();
	}
	
	//挂账账单查询
	//http://localhost:8080/snug/queryBillsList
	@ResponseBody
	@RequestMapping(value="/queryBillsList",produces="text/html;charset=UTF-8",method={RequestMethod.GET,RequestMethod.POST})
	public String queryBillsList()
	{
		return memberActionInterfaces.queryBillsList();
	}
	
	//挂账账单查询
	//http://localhost:8080/snug/queryBillsListLikeName?key_word=157
	@ResponseBody
	@RequestMapping(value="/queryBillsListLikeName",produces="text/html;charset=UTF-8",method={RequestMethod.GET,RequestMethod.POST})
	public String queryBillsListLikeName(String key_word)
	{
		return memberActionInterfaces.queryBillsListLikeName(key_word);
	}
	//修改会员
	@ResponseBody
	@RequestMapping(value="/updateMember",produces="text/html;charset=UTF-8",method={RequestMethod.GET,RequestMethod.POST})
	public  String updateMember(
			String  user_info_phone,
			String	user_info_token,
			String	user_info_level,
			String	user_info_score,
			String	user_info_flag,
			String	user_info_name,
			String	user_info_balance,
			String	user_info_total,
			String	user_info_trade_count,
			String  user_info_group
	)
	{
		return memberActionInterfaces.updateMember(user_info_phone,user_info_token, user_info_level, user_info_score, user_info_flag, user_info_name,user_info_balance, user_info_total, user_info_trade_count,user_info_group);
	}
	
	//http://localhost:8080/snug/queryMembersAllMes
	@ResponseBody
	@RequestMapping(value="/queryMembersAllMes",produces="text/html;charset=UTF-8",method={RequestMethod.GET,RequestMethod.POST})
	public String queryMembersAllMes()
	{
		return memberActionInterfaces.queryMembersAllMes();
	};
}
