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
public interface HotelAcitonInfaces {

	public abstract String queryHotel(String hotelname);

	public abstract String queryinsertHotel(String hotel_info_name,
			String hotel_info_flag, String department_id);

	public abstract String deletHotelList(String hotel_info_name);

	public abstract String updateHotelList(String hotel_info_name,
			String hotel_info_flag,
			String hotel_info_name1);
}
