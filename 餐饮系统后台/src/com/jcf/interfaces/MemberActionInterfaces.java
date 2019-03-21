package com.jcf.interfaces;


public interface MemberActionInterfaces
{
	//查询所有分组会员
	public abstract String queryMembers();
	//修改会员
	public abstract String updateMember(
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
			);
	//删除会员
	public abstract String deleteMember(String token);
	//增加会员（都是通过注册而来）
	
	//会员模糊搜索
	public abstract String queryFuzzyMembers(String search_condition);
	//查询所有会员
	public abstract String queryMembersList();
	//挂账账单查询
	public abstract String queryBillsList();
	//挂账账单模糊查询(昵称和电话)
	public abstract String queryBillsListLikeName(String key_word);
	//查询所有会员的所有信息
	public abstract String queryMembersAllMes();
}
