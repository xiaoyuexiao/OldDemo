package com.jcf.interfaces;

public interface MemberDegreeActionInterfaces {
	
	/*
	 *  @author:Lee
	 *  @time:2018/1/17
	 *  @fun:添加会员等级
	 *  @param:
  		member_degree_name:会员等级名称
	    member_degree_discount :会员等级折扣
	    department_id ：酒店id
	    member_degree_lower ：此等级需要最低积分
	    member_degree_real ：会员等级
	    member_degree_unit ：换算比率
	    member_degree_credit ：会员信用额度
    	member_degree_settlement_date ：结算周期
	 * 
	 * */
	public abstract String addMemberDegree(
			String member_degree_name,
			String member_degree_discount,
			String member_degree_lower,
			String member_degree_real,
			String member_degree_unit,
			String department_id,
			String member_degree_credit,
			String member_degree_settlement_date);
	/*
	 *  @author:Lee
	 *  @time:2018/1/17
	 *  @fun:隐藏会员等级
	 *  @param:
  		member_degree_token:会员等级确定值
	 * 
	 * */
	public abstract String deleteMemberDegree(String member_degree_token);
	
	
	/*
	 *  @author:Lee
	 *  @time:2018/1/18
	 *  @fun:查询会员等级
	 *  @param:
	 * 
	 * */
	public abstract String queryMemberDegrees();
	
	/*
	 *  @author:Lee
	 *  @time:2018/1/18
	 *  @fun:查询会员等级
	 *  @param:
	 * 
	 * */
	public abstract String updateMemberDegrees(
			String member_degree_name,
			String member_degree_discount,
			String member_degree_lower,
			String member_degree_real,
			String member_degree_unit,
			String member_degree_flag,
			String member_degree_credit,
			String member_degree_settlement_date,
			String member_degree_token);
}
