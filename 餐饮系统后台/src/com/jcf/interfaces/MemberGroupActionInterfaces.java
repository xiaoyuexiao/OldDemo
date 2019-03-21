package com.jcf.interfaces;

public interface MemberGroupActionInterfaces {
	/*
	 *  @author:Lee
	 *  @time:2018/1/17
	 *  @fun:会员分组添加
	 *  @param:
				member_group_name:分组名称
			    member_group_master：联系人
			    member_group_phone：联系电话
			    department_id：酒店id
	 * 
	 * */
	public abstract String addMemberGroup(
			String member_group_name,
			String member_group_master,
			String member_group_phone,
			String department_id,
			String member_group_token);
	
	/*
	 *  @author:Lee
	 *  @time:2018/1/17
	 *  @fun:会员分组查询(组名称，会员数量，联系人)
	 *  @param:
	 * */
	public abstract String queryMemberGroups();
	
	/*
	 *  @author:Lee
	 *  @time:2018/1/17
	 *  @fun:删除
	 *  @param:
	 * */
	public abstract String deleteMemberGroup(String member_group_token);
	
	//会员分组修改
	public abstract String updateMemberGroup(
			String member_group_name,
			String member_group_master,
			String member_group_phone,
			String department_id,
			String member_group_token
			);
	
}
