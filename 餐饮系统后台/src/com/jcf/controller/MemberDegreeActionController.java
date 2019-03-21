package com.jcf.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jcf.interfaces.MemberDegreeActionInterfaces;

@Controller
public class MemberDegreeActionController {
	@Autowired
	MemberDegreeActionInterfaces memberDegreeActionInterfaces;
	//token:123123,sewrtert
	@ResponseBody
	@RequestMapping(value="/insertMemberDegree",produces="text/html;charset=UTF-8",method = {RequestMethod.GET,RequestMethod.POST})
	public String insertMemberDegree(
			String member_degree_name,
			String member_degree_discount,
			String member_degree_lower,
			String member_degree_real,
			String member_degree_unit,
			String department_id,String member_degree_credit,
			String member_degree_settlement_date){
			return memberDegreeActionInterfaces.addMemberDegree(member_degree_name, member_degree_discount, member_degree_lower, member_degree_real, member_degree_unit, department_id,member_degree_credit,member_degree_settlement_date);
	}
	
	
	@ResponseBody
	@RequestMapping(value="/queryMemberDegrees",produces="text/html;charset=UTF-8",method = {RequestMethod.GET,RequestMethod.POST})
	public String queryMemberDegrees(){
			return memberDegreeActionInterfaces.queryMemberDegrees();
	}
	
	//http://localhost:8080/snug/deleteMemberDegree?member_degree_token=?
	@ResponseBody
	@RequestMapping(value="/deleteMemberDegree",produces="text/html;charset=UTF-8",method = {RequestMethod.GET,RequestMethod.POST})
	public String deleteMemberDegree(String token){
			return memberDegreeActionInterfaces.deleteMemberDegree(token);
	}
	
	//http://localhost:8080/snug/updateMemberDegrees?member_degree_name="白金会员"&member_degree_discount=5&member_degree_lower=100&member_degree_real=3&member_degree_unit=100:1&member_degree_flag=1&member_degree_credit=500&member_degree_settlement_date=2018-10-10&member_degree_token=memberdegreefc0a5cc25
		@ResponseBody
		@RequestMapping(value="/updateMemberDegrees",produces="text/html;charset=UTF-8",method = {RequestMethod.GET,RequestMethod.POST})
		public String updateMemberDegrees(String member_degree_name,
				String member_degree_discount, String member_degree_lower,
				String member_degree_real, String member_degree_unit,
				String member_degree_flag, String member_degree_credit,
				String member_degree_settlement_date, String member_degree_token
				)
		{
				return memberDegreeActionInterfaces.updateMemberDegrees(member_degree_name, member_degree_discount, member_degree_lower, member_degree_real, member_degree_unit, member_degree_flag, member_degree_credit, member_degree_settlement_date, member_degree_token);
		}
}
