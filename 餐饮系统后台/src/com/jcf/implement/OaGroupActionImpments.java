package com.jcf.implement;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.hibernate.type.TrueFalseType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.StaticApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.jcf.interfaces.OaAdminAcitonInfaces;
import com.jcf.interfaces.OaGroupAcitonInfaces;
import com.jcf.interfaces.OaRoleAcitonInfaces;
import com.jcf.util.DBHelper;
import com.jcf.util.MyUtil;

@Service
public class OaGroupActionImpments implements OaGroupAcitonInfaces {

	@Autowired
	JdbcTemplate jdbcTemplate;

	private String sub_department_id;
	private String role_info_type;
	private List<Map<String, Object>> deparmentlist;
	private JSONArray array;
	private int number;
	private String nums;
	private Map<String, String> mapc;

	public String queryGroupList(String department_id, String role_info_type,
			String sub_department_id) {

		if (jdbcTemplate != null) {
			if (department_id != null && sub_department_id == null
					&& role_info_type == null) {
				String sql1 = "SELECT sub_department_id FROM snug_admin_info where department_id=?";
				List<Map<String, Object>> list = jdbcTemplate.queryForList(
						sql1, new Object[] { department_id });
				array = new JSONArray();
				String[] value = new String[list.size()];
				int p = 0;
				for (Map<String, Object> map : list) 
				{

					for (Entry<String, Object> entry : map.entrySet()) 
					{
						String aString = (String) entry.getValue();
						value[p++] = aString;
					}
				}

				for (int i = 0; i < value.length - 1; i++) 
				{
					//String dj=value[i];
					if (value[i + 1] == null) 
					{// 判断外层数组是否为空，若为空就退出循环
						break;
					}

					for (int j = i + 1; j < value.length; j++) 
					{
						if (value[i].equals(value[j]))
						{
							for (int j2 = i; j2 < value.length - 1; j2++)
							{
								value[j2] = value[j2 + 1];// 遇到重复项，将后面的数据往前移动，覆盖重复项
							}
							value[value.length - 1] = null;
							i--;
							break;
						}

					}

				}
				array = new JSONArray();
				for (int g = 0; g < value.length; g++) {

					if (value[g] != null) {
						mapc = new HashMap<String, String>();

						String sql = "SELECT COUNT(*)  FROM  `snug_admin_info` WHERE sub_department_id=?";

						List<Map<String, Object>> groupList = jdbcTemplate
								.queryForList(sql, new Object[] { value[g] });
						//System.out.println(groupList);
						for (Map<String, Object> map : groupList) {
							for (Map.Entry<String, Object> entry : map
									.entrySet()) {
								//System.out.println("数量:" + entry.getValue());
								nums = entry.getValue().toString();
							}
							//System.out.println("数量:" + nums);
							mapc.put("number", nums);
							mapc.put("sub_department_id", value[g]);
							array.add(mapc);
						}
					}
				}
				JSONObject object = new JSONObject();
				object.put("status", "1");
				object.put("msg", "success");
				object.put("info", array);
				return object.toString();
			}
			if (sub_department_id != null && department_id != null
					&& role_info_type == null) {

				String sql1 = "SELECT role_info_type  FROM  `oa_role_info` WHERE sub_department_id=? and department_id=?";
				deparmentlist = jdbcTemplate.queryForList(sql1, new Object[] {
						sub_department_id, department_id });
				return DBHelper.ReturnList(deparmentlist);

			} else if (sub_department_id != null && role_info_type != null
					&& department_id != null) {

				String sqlString = "SELECT role_info_name FROM  `oa_role_info` WHERE role_info_type=?";
				List<Map<String, Object>> list1 = jdbcTemplate.queryForList(
						sqlString, new Object[] { role_info_type });
				array = new JSONArray();
				for (Map<String, Object> map : list1) {

					for (Map.Entry<String, Object> entry : map.entrySet()) {

						sqlString = "SELECT admin_info_name,admin_info_role FROM `snug_admin_info` WHERE admin_info_role=?";
						deparmentlist = jdbcTemplate.queryForList(sqlString,
								new Object[] { entry.getValue() });

						for (Map<String, Object> map1 : deparmentlist) {
							array.add(map1);
						}
					}

				}

				JSONObject object = new JSONObject();
				object.put("status", "1");
				object.put("mes", "success");
				object.put("info", array);
				return object.toString();
			}

			return DBHelper.ReturnRows(0);
		}
		return DBHelper.ReturnRows(0);
	}
}
