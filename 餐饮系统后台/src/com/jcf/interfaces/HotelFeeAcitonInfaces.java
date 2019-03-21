package com.jcf.interfaces;

import java.util.ArrayList;
import java.util.Map;

import org.apache.taglibs.standard.lang.jstl.test.beans.PublicBean1;

/*
 *  @author:cht
 *  @time:2018/3/2
 *  @fun:分组(Hotel部分)
 *  @param:

 * 
 * */
public interface HotelFeeAcitonInfaces {

	public abstract String queryHotelFee(String hotel_room_info_name);


	public abstract String updateHotelFeeList(
			String hotel_room_info_name1,
			String hotel_fee_basic_money,
			String hotel_fee_money0,
			String hotel_fee_money1,
			String hotel_fee_money2,
			String hotel_room_info_name
			
			);
}
