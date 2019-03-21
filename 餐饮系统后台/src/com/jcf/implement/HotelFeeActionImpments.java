package com.jcf.implement;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.alibaba.fastjson.JSONObject;
import com.jcf.interfaces.HotelAcitonInfaces;
import com.jcf.interfaces.HotelFeeAcitonInfaces;
import com.jcf.interfaces.OaAdminAcitonInfaces;
import com.jcf.interfaces.OaRoleAcitonInfaces;
import com.jcf.util.DBHelper;
import com.jcf.util.Limit;
import com.jcf.util.MyUtil;

@Service
public class HotelFeeActionImpments implements HotelFeeAcitonInfaces {

	@Autowired
	JdbcTemplate jdbcTemplate;
	private ArrayList<Map<String, Object>> list;
	private int rows;

	@Override
	public String queryHotelFee(String hotel_room_info_name) {
		if (jdbcTemplate != null) {
			if(hotel_room_info_name==null){
				String sql="select * from snug_hotel_fee";
				list = (ArrayList<Map<String, Object>>) jdbcTemplate.queryForList(sql);
			return	DBHelper.ReturnList(list);

			}else{
				String sql="select * from snug_hotel_fee where hotel_room_info_name=?";
				ArrayList<Map<String, Object>> list=(ArrayList<Map<String, Object>>) jdbcTemplate.queryForList(sql,new Object[]{hotel_room_info_name});
				return	DBHelper.ReturnList(list);
			}

		}
		
		return	DBHelper.ReturnList(list);
	}


	@Override
	public String updateHotelFeeList(
			String hotel_room_info_name1,
			String hotel_fee_basic_money,
			String hotel_fee_money0,
			String hotel_fee_money1,
			String hotel_fee_money2,
			String hotel_room_info_name
			) {
		if (jdbcTemplate != null) {
			String sql="update snug_hotel_fee set hotel_room_info_name=?,hotel_fee_basic_money=?,hotel_fee_money0=?,hotel_fee_money1=?,hotel_fee_money2=? WHERE hotel_room_info_name=? ";
			rows=jdbcTemplate.update(sql,new Object[]{
					hotel_room_info_name1,
					hotel_fee_basic_money,
					hotel_fee_money0,
					hotel_fee_money1,
					hotel_fee_money2,
					hotel_room_info_name});
		}
		return DBHelper.ReturnRows(rows);
	}
   
}
