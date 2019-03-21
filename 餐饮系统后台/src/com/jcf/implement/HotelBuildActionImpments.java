package com.jcf.implement;

import java.util.ArrayList;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.alibaba.fastjson.JSONObject;
import com.jcf.interfaces.HotelAcitonInfaces;
import com.jcf.interfaces.HotelBuildAcitonInfaces;
import com.jcf.interfaces.OaAdminAcitonInfaces;
import com.jcf.interfaces.OaRoleAcitonInfaces;
import com.jcf.util.DBHelper;
import com.jcf.util.Limit;
import com.jcf.util.MyUtil;

@Service
public class HotelBuildActionImpments implements HotelBuildAcitonInfaces {

	@Autowired
	JdbcTemplate jdbcTemplate;
	private ArrayList<Map<String, Object>> list;
	private int rows;

	@Override
	public String queryHotelBuild(String hotel_building_info_name) {
		if (jdbcTemplate != null) {
			if(hotel_building_info_name==null){
				String sql="select * from snug_hotel_building_info";
				list = (ArrayList<Map<String, Object>>) jdbcTemplate.queryForList(sql);
				return	DBHelper.ReturnList(list);    
			}else{
				String sql="select * from snug_hotel_building_info where hotel_building_info_name=?";
				ArrayList<Map<String, Object>> list=(ArrayList<Map<String, Object>>) jdbcTemplate.queryForList(sql,new Object[]{hotel_building_info_name});
				return	DBHelper.ReturnList(list);
			}

		}
		return DBHelper.ReturnList(list);
	}

	@Override
	public String insertHotelBuild(
			String hotel_building_info_name,
			String hotel_building_info_flag,
			String hotel_info_name,
			String hotel_info_token,
			String department_id
			) {
		if (jdbcTemplate != null) {	
			String sql="insert into snug_hotel_building_info (hotel_building_info_name,hotel_building_info_flag,hotel_building_info_token,hotel_info_token,department_id)values(?,?,?,?,?)";
			String hotel_building_info_token = "company" + MyUtil.GetToken().substring(0,9);
			rows = jdbcTemplate.update(sql,new Object[]{hotel_building_info_name,hotel_building_info_flag,hotel_building_info_token,hotel_info_token,department_id});
			
		}
		return DBHelper.ReturnRows(rows);
		
	}

	@Override
	public String deletHotelBuild(String hotel_building_info_name) {
		if (jdbcTemplate != null) {
			String sql="update snug_hotel_building_info set hotel_building_info_flag=0 WHERE hotel_building_info_name=? ";		
			rows=jdbcTemplate.update(sql,new Object[]{hotel_building_info_name});

		}
		return DBHelper.ReturnRows(rows);
	
	}

	@Override
	public String updateHotelBuild(String hotel_building_info_name,
			String hotel_building_info_flag, String hotel_building_info_name1) {
		if (jdbcTemplate != null) {
			System.out.println(hotel_building_info_name1+hotel_building_info_flag+hotel_building_info_name);
			String sql="update snug_hotel_building_info set hotel_building_info_name=?,hotel_building_info_flag=? WHERE hotel_building_info_name=? ";
			rows=jdbcTemplate.update(sql,new Object[]{hotel_building_info_name1,hotel_building_info_flag,hotel_building_info_name});
		}
		return DBHelper.ReturnRows(rows);
		
	}


}
