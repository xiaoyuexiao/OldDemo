package com.jcf.implement;

import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.jcf.interfaces.MemberGroupActionInterfaces;
import com.jcf.util.DBHelper;
import com.jcf.util.MyUtil;
@Service
public class MemberGroupActionImplements implements MemberGroupActionInterfaces {
	@Autowired
	JdbcTemplate jdbcTemplate;
	//http://localhost:8080/snug/addMemberGroup?member_group_name=九次方科技有限公司&member_group_master=谢帅&member_group_phone=15700218161&department_id=001
	@Override
	public String addMemberGroup(
			String member_group_name,
			String member_group_master,
			String member_group_phone,
			String department_id,
			String member_group_token)
	{
		if (jdbcTemplate!=null)
		{
			String sqlString = "insert into snug_member_group(" +
					"member_group_name," +
					"member_group_master," +
					"member_group_phone," +
					"department_id," +
					"member_group_token) values(?,?,?,?,?)";
			String token = "member" + MyUtil.GetToken().substring(0,9);
			String group_name = null;
			String group_master = null;
			try {
				group_name = new String(member_group_name.toString().getBytes("iso-8859-1"),"utf-8");
				group_master = new String(member_group_master.toString().getBytes("iso-8859-1"),"utf-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
					
			Object[] params = new Object[]{group_name,group_master,member_group_phone,department_id,token};			
			int rows = jdbcTemplate.update(sqlString,params);
			//向用户表插入
			String group_token = "member_group_group" + MyUtil.GetToken().substring(0,9);
			String sqlString2 = "insert into snug_member_group_info(department_id,member_group_token,member_group_group_token,member_group_user_token) values(?,?,?,?)";
			jdbcTemplate.update(sqlString2,new Object[]{department_id,token,group_token,member_group_token});
			return DBHelper.ReturnRows(rows);
		}
		else
		{
			return DBHelper.FailConnectDatabase();
		}
	}

	@Override
	public String queryMemberGroups() {
		if (jdbcTemplate!=null)
		{
			String query = "select member_group_name,member_group_phone,snug_member_group.member_group_token,member_group_master,count(member_group_user_token) as member_group_num from snug_member_group,snug_member_group_info where snug_member_group.member_group_token=snug_member_group_info.member_group_token and snug_member_group.member_group_flag != '0' group by snug_member_group.member_group_token";
			List<Map<String, Object>> list = jdbcTemplate.queryForList(query);
			return DBHelper.ReturnList(list);
		}
		else
		{
			return DBHelper.FailConnectDatabase();
		}
	}

	@Override
	public String deleteMemberGroup(String member_group_token) {
		if (jdbcTemplate!=null)
		{
			String sql = "update snug_member_group set member_group_flag = 0 where member_group_token = ?";
			int rows = jdbcTemplate.update(sql,member_group_token);
			return DBHelper.ReturnRows(rows);
		}
		else
		{
			return DBHelper.FailConnectDatabase();
		}
	}

	@Override
	public String updateMemberGroup(String member_group_name,
			String member_group_master, String member_group_phone,
			String department_id,
			String member_group_token)
	{
		System.out.println("member_group_name1:"+member_group_name);
		try {
			System.out.println("member_group_name2:"+new String(member_group_name.getBytes(),"utf8"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(member_group_master);
		System.out.println(member_group_phone);
		System.out.println(department_id);
		System.out.println(member_group_token);
		if (jdbcTemplate != null)
		{
			String updatesqlString = "update snug_member_group set  member_group_name = ? ,"+
																	"member_group_master = ? ,"+
																	"member_group_phone = ? ,"+
																	"department_id = ? "+
																	" where member_group_token = ?";
			
			try {
				Connection conn=jdbcTemplate.getDataSource().getConnection();
				PreparedStatement preparedStatement=conn.prepareStatement(updatesqlString);
				preparedStatement.setString(1, member_group_name);
				preparedStatement.setString(2, member_group_master);
				preparedStatement.setString(3, member_group_phone);
				preparedStatement.setString(4, department_id);
				preparedStatement.setString(5, member_group_token);
				
				System.out.println(preparedStatement.toString());
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			System.out.println("sql:" + updatesqlString);
			int rows = jdbcTemplate.update(updatesqlString,new Object[]{member_group_name,
															member_group_master,
															member_group_phone,
															department_id,
															member_group_token
															});
			return DBHelper.ReturnRows(rows);
		} 
		else
		{
			return DBHelper.FailConnectDatabase();
		}
	}

}
