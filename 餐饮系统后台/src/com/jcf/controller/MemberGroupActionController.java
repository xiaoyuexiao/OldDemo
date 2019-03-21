package com.jcf.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jcf.interfaces.MemberGroupActionInterfaces;

@Controller
public class MemberGroupActionController {
	@Autowired
	MemberGroupActionInterfaces memberGroupActionInterfaces;
	
	//http://localhost:8080/snug/addMemberGroup?member_group_name=wo&member_group_master=lee&member_group_phone=15700218161&department_id=001&member_group_token=330236184
	@ResponseBody 
	@RequestMapping(value="/addMemberGroup",produces="text/html;charset=UTF-8",method = {RequestMethod.GET,RequestMethod.POST})
	public String addMemberGroup(
			String member_group_name,
			String member_group_master,
			String member_group_phone,
			String department_id,
			String member_group_token)
	{
		return memberGroupActionInterfaces.addMemberGroup(member_group_name, member_group_master, member_group_phone, department_id,member_group_token);
	}
	
	//查询会员分组的信息
	@ResponseBody 
	@RequestMapping(value="/queryMemberGroups",produces="text/html;charset=UTF-8",method = {RequestMethod.GET,RequestMethod.POST})
	public String queryMemberGroups()
	{
		return memberGroupActionInterfaces.queryMemberGroups();
	}
	
	
	//删除会员分组的信息  http://localhost:8080/snug/deleteMemberGroup?member_group_token=123
	@ResponseBody 
	@RequestMapping(value="/deleteMemberGroup",produces="text/html;charset=UTF-8",method = {RequestMethod.GET,RequestMethod.POST})
	public String deleteMemberGroup(String token)
	{
		return memberGroupActionInterfaces.deleteMemberGroup(token);
	}
	
	//http://localhost:8080/snug/updateMemberGroup?member_group_name=煞笔大队&member_group_master=大黄&member_group_phone=1572245647&department_id=456&member_group_flag=1&member_group_token=1222222222
	@ResponseBody 
	@RequestMapping(value="/updateMemberGroup",produces="text/html;charset=UTF-8",method = {RequestMethod.GET,RequestMethod.POST})
	public String updateMemberGroup(
			String member_group_name,
			String member_group_master,
			String member_group_phone,
			String department_id,
			String member_group_token
			)
	{
		return memberGroupActionInterfaces.updateMemberGroup(member_group_name, member_group_master, member_group_phone, department_id, member_group_token);
	};
}
