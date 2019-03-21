package com.jcf.implement;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.jcf.interfaces.MemberActionInterfaces;
import com.jcf.util.DBHelper;
import com.jcf.util.MyUtil;
@Service
public class MemberActionImplements implements MemberActionInterfaces {
	@Autowired
	JdbcTemplate jdbcTemplate;
	//会员列表
	@Override
	public String queryMembers() {
		if (jdbcTemplate!=null)
		{
			String sqlString = "select distinct  user_info_phone," +
										"user_info_name," +
										"user_info_score," +
										"user_info_level," +
										"member_degree_name," +
										"user_info_balance," +
										"user_info_flag," +
										"user_info_total," +
										"user_info_trade_count," +
										"user_info_token," +
										"member_group_name from snug_user_info,snug_member_group,snug_member_group_info,snug_member_degree where snug_user_info.user_info_level = snug_member_degree.member_degree_token and snug_user_info.user_info_token = snug_member_group_info.member_group_user_token and snug_member_group_info.member_group_token=snug_member_group.member_group_token and user_info_flag !='0'";
			List<Map<String, Object>> list = jdbcTemplate.queryForList(sqlString);
			return DBHelper.ReturnList(list);
		}
		else
		{
			return DBHelper.FailConnectDatabase();
		}
	}

	
	@Override
	public String deleteMember(String token) {
		
		if (jdbcTemplate!=null)
		{
			String sql = "update snug_user_info set user_info_flag = 0 where user_info_token = ?";
			int rows = jdbcTemplate.update(sql,token);
			return DBHelper.ReturnRows(rows);
		}
		else
		{
			return DBHelper.FailConnectDatabase();
		}
	}

	@Override
	public String queryFuzzyMembers(String search_condition) {
		if (jdbcTemplate!=null) 
		{
			if (search_condition=="") 
			{
				JSONObject object = new JSONObject();
				object.put("status", "1");
				object.put("mes", "success");
				object.put("info", "[]");
				return object.toString();
			}
			String sqlString = "select " +
					"user_info_name," +
					"user_info_phone," +
					"user_info_alipay," +
					"user_info_weixinpay," +
					"user_info_qq," +
					"member_group_name," +
					"user_info_level," +
					"member_degree_name," +
					"user_info_flag," +
					"user_info_token," +
					"user_info_balance," +
					"user_info_score," +
					"user_info_trade_count," +
					"user_info_total " +
					"from " +
					"snug_user_info,snug_member_group_info,snug_member_degree," +
					"snug_member_group where" +
					" snug_user_info.user_info_level = snug_member_degree.member_degree_token and " +
					"snug_user_info.user_info_token = snug_member_group_info.member_group_user_token and snug_member_group.member_group_token = snug_member_group_info.member_group_token and snug_user_info.user_info_flag != '0' and" +
					" (user_info_qq like '%"+ search_condition +"%'" +
					"or user_info_alipay like '%" + search_condition +"%'" +
					"or user_info_weixinpay like '%" + search_condition +"%'" +
					"or user_info_phone like '%" + search_condition +"%')";
			
			List<Map<String, Object>> list = jdbcTemplate.queryForList(sqlString);
			return DBHelper.ReturnList(list);
		}
		else
		{
			return DBHelper.FailConnectDatabase();
		}
	}

	@Override
	public String queryMembersList() {
		if (jdbcTemplate != null) 
		{
			String sqlString = "select user_info_phone," +
					"user_info_score," +
					"user_info_level," +
					"user_info_balance," +
					"user_info_flag," +
					"user_info_total," +
					"user_info_trade_count," +
					"user_info_token" +
					" from snug_user_info where user_info_flag != '0'";
					List<Map<String, Object>> list = jdbcTemplate.queryForList(sqlString);
					return DBHelper.ReturnList(list);
		} 
		else
		{
			return DBHelper.FailConnectDatabase();
		}
	}

	@Override
	public String queryBillsList()
	{
		if (jdbcTemplate != null)
		{
			String queryBillsList = "select user_info_name,user_info_alipay,user_info_weixinpay,user_info_qq,bookdinner_info_phone," +
					"bookdinner_info_cost_type,bookdinner_info_times,bookdinner_info_bills_cost from snug_user_info,snug_bookdinner_info" +
					" where snug_user_info.user_info_phone = snug_bookdinner_info.bookdinner_info_phone and bookdinner_info_flag = '2'";
			List<Map<String, Object>> list = jdbcTemplate.queryForList(queryBillsList);
			JSONArray array = MyUtil.getJsonArray(list);
			JSONObject object = new JSONObject();
			object.put("status", "1");
			object.put("mes", "success");
			object.put("info", array);
			return object.toString();
		} 
		else 
		{
			return DBHelper.FailConnectDatabase();
		}
	}

	@Override
	public String queryBillsListLikeName(String key_word) {
		if (jdbcTemplate != null)
		{
			String queryString = "select user_info_name,user_info_alipay,user_info_weixinpay,user_info_qq,bookdinner_info_phone," +
					"bookdinner_info_cost_type,bookdinner_info_times,bookdinner_info_bills_cost from snug_user_info,snug_bookdinner_info" +
					" where snug_user_info.user_info_phone = snug_bookdinner_info.bookdinner_info_phone and bookdinner_info_flag = '2' and (user_info_weixinpay like '%"+key_word+"%' or" +
							" user_info_qq like '%"+ key_word +"%' or bookdinner_info_phone like '%"+ key_word + "%' or user_info_alipay like '%"+ key_word +"%')";
			List<Map<String, Object>> list = jdbcTemplate.queryForList(queryString);
			JSONArray array = MyUtil.getJsonArray(list);
			JSONObject object = new JSONObject();
			object.put("status", "1");
			object.put("mes", "success");
			object.put("info", array);
			return object.toString();
		}
		else
		{
			return DBHelper.FailConnectDatabase();
		}
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRES_NEW)
	public String updateMember(
			String user_info_phone,
			String user_info_token, String user_info_level,
			String user_info_score, String user_info_flag,
			String user_info_name, 
			String user_info_balance, String user_info_total,
			String user_info_trade_count,String  user_info_group)
	{
		if (jdbcTemplate != null)
		{
			//修改分组
			String updateString = "update snug_member_group_info set member_group_token = ? where member_group_user_token = ?";
			jdbcTemplate.update(updateString, new Object[]{user_info_group,user_info_token});
			
			String sqlupdate = "update snug_user_info set " +
						"user_info_phone =?,"+
						"user_info_token =?,"+
						"user_info_level =?,"+
						"user_info_score =?,"+
						"user_info_flag =?,"+
						"user_info_name =?,"+
						"user_info_balance =?,"+
						"user_info_total =?,"+
						"user_info_trade_count =? where user_info_token = ?";
			int rows = jdbcTemplate.update(sqlupdate,new Object[]{
						user_info_phone,
						user_info_token,
						user_info_level,
						user_info_score,
						user_info_flag,
						user_info_name,
						user_info_balance,
						user_info_total,
						user_info_trade_count,
						user_info_token
					});
			return DBHelper.ReturnRows(rows);
		} 
		else 
		{
			return DBHelper.FailConnectDatabase();
		}
	}


	@Override
	public String queryMembersAllMes() {
		if (jdbcTemplate != null) 
		{
			String sqlString = "select * from snug_user_info where user_info_flag !='0'";
			List<Map<String, Object>> list = jdbcTemplate.queryForList(sqlString);
			return DBHelper.ReturnList(list);
		} 
		else
		{
			return DBHelper.FailConnectDatabase();
		}
	}

}
