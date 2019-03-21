package com.jcf.implement;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.jcf.interfaces.MemberDegreeActionInterfaces;
import com.jcf.util.DBHelper;
import com.jcf.util.MyUtil;
@Service
public class MemberDegreeActionImplements implements MemberDegreeActionInterfaces {
	@Autowired
	JdbcTemplate jdbcTemplate;
	@Override
	public String addMemberDegree(String member_degree_name,
			String member_degree_discount, String member_degree_lower,
			String member_degree_real, String member_degree_unit,
			String department_id,String member_degree_credit,
			String member_degree_settlement_date) 
	{
		if (jdbcTemplate!=null)
		{
			String sqlString = "insert into snug_member_degree(" +
					"member_degree_name," +
					"member_degree_discount," +
					"member_degree_lower," +
					"member_degree_real," +
					"member_degree_unit," +
					"department_id," +
					"member_degree_token,member_degree_credit,member_degree_settlement_date) values(?,?,?,?,?,?,?,?,?)";
			String token = "memberdegree" + MyUtil.GetToken().substring(0,9);
			String degree_name = null;
			String degree_discount = null;
			String degree_lower = null;
			String degree_real = null;
			String degree_unit = null;
			try {
				degree_name = new String(member_degree_name.toString().getBytes("iso-8859-1"),"utf-8");
				degree_discount = new String(member_degree_discount.toString().getBytes("iso-8859-1"),"utf-8");
				degree_lower = new String(member_degree_lower.toString().getBytes("iso-8859-1"),"utf-8");
				degree_real = new String(member_degree_real.toString().getBytes("iso-8859-1"),"utf-8");
				degree_unit = new String(member_degree_unit.toString().getBytes("iso-8859-1"),"utf-8");
			} catch (UnsupportedEncodingException e)
			{
				e.printStackTrace();
			}
					
			Object[] params = new Object[]{degree_name,degree_discount,degree_lower,degree_real,degree_unit,department_id,token,member_degree_credit,member_degree_settlement_date};
			
			int rows = jdbcTemplate.update(sqlString,params);
			return DBHelper.ReturnRows(rows);
		}
		else
		{
			return DBHelper.FailConnectDatabase();
		}
	}

	@Override
	public String deleteMemberDegree(String member_degree_token) {
		if (jdbcTemplate!=null)
		{
			String sql = "update snug_member_degree set member_degree_flag = 0 where member_degree_token = ?";
			int rows = jdbcTemplate.update(sql,member_degree_token);
			return DBHelper.ReturnRows(rows);
		}
		else
		{
			return DBHelper.FailConnectDatabase();
		}
	}

	@Override
	public String queryMemberDegrees() {

		if (jdbcTemplate!=null)
		{
			String sqlString = "select member_degree_name," +
										"member_degree_discount," +
										"member_degree_real," +
										"member_degree_unit," +
										"member_degree_flag," +
										"member_degree_lower," +
										"member_degree_credit," +
										"member_degree_settlement_date ,member_degree_token,count(user_info_level) as user_info_levelnums from snug_user_info,snug_member_degree where member_degree_flag !='0' group by member_degree_real";
			List<Map<String, Object>> list = jdbcTemplate.queryForList(sqlString);
			System.out.println(list.toString());
			return DBHelper.ReturnList(list);
		}
		else
		{
			return DBHelper.FailConnectDatabase();
		}
	}

	@Override
	public String updateMemberDegrees(String member_degree_name,
			String member_degree_discount, String member_degree_lower,
			String member_degree_real, String member_degree_unit,
			String member_degree_flag, String member_degree_credit,
			String member_degree_settlement_date, String member_degree_token) {
		if (jdbcTemplate != null)
		{
			String updatesqlString = "update snug_member_degree set member_degree_name = ?," +
																	"member_degree_discount = ?," +
																	"member_degree_real = ?," +
																	"member_degree_unit = ?," +
																	"member_degree_flag = ?," +
																	"member_degree_lower = ?," +
																	"member_degree_credit = ?," +
																	"member_degree_settlement_date = ? " +
																	"where member_degree_token =?";
			int rows =	jdbcTemplate.update(updatesqlString,new Object[]{
						member_degree_name,
						member_degree_discount,
						member_degree_real,
						member_degree_unit,
						member_degree_flag,
						member_degree_lower,
						member_degree_credit,
						member_degree_settlement_date,
						member_degree_token});
			
			return DBHelper.ReturnRows(rows);
		} 
		else
		{
			return DBHelper.FailConnectDatabase();
		}
	}

}
