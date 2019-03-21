package com.jcf.implement;

import java.util.ArrayList;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.jcf.interfaces.HotelAcitonInfaces;
import com.jcf.util.DBHelper;
import com.jcf.util.MyUtil;

@Service
public class HotelActionImpments implements HotelAcitonInfaces {

	@Autowired
	JdbcTemplate jdbcTemplate;
	private ArrayList<Map<String, Object>> list;
	private int rows;

	@Override
	public String queryHotel(String hotelname) {
		if (jdbcTemplate != null) {
			if(hotelname==null){
				String sql="select * from snug_hotel_info";
				list = (ArrayList<Map<String, Object>>) jdbcTemplate.queryForList(sql);
			return	DBHelper.ReturnList(list);

			}else{
				String sql="select * from snug_hotel_info where hotel_info_name=?";
				ArrayList<Map<String, Object>> list=(ArrayList<Map<String, Object>>) jdbcTemplate.queryForList(sql,new Object[]{hotelname});
				return	DBHelper.ReturnList(list);
			}
			
			
		}
		
		
		
		return "";
	}

	@Override
	public String queryinsertHotel(
			String hotel_info_name,
			String hotel_info_flag,
			String department_id) {
		if (jdbcTemplate != null) {
			String sql="insert into snug_hotel_info (hotel_info_name,hotel_info_flag,hotel_info_token,department_id)values(?,?,?,?)";
			String hotel_info_token = "company" + MyUtil.GetToken().substring(0,9);
			rows = jdbcTemplate.update(sql,new Object[]{hotel_info_name,hotel_info_flag,hotel_info_token,department_id});
			
		}
		return DBHelper.ReturnRows(rows);
		
	}

	@Override
	public String deletHotelList(String hotel_info_name) {
		if (jdbcTemplate != null) {
			String sql="update snug_hotel_info set hotel_info_flag=0 WHERE hotel_info_name=? ";
			
			rows=jdbcTemplate.update(sql,new Object[]{hotel_info_name});

		}
		return DBHelper.ReturnRows(rows);
	}

	@Override
	public String updateHotelList(String hotel_info_name, String hotel_info_flag,String hotel_info_name1) {
		if (jdbcTemplate != null) {
			String sql="update snug_hotel_info set hotel_info_name=?,hotel_info_flag=? WHERE hotel_info_name=? ";
			rows=jdbcTemplate.update(sql,new Object[]{hotel_info_name1,hotel_info_flag,hotel_info_name});
		}
		return DBHelper.ReturnRows(rows);
	}
   
}
