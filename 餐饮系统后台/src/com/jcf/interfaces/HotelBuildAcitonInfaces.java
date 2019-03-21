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
public interface HotelBuildAcitonInfaces {

	public abstract String queryHotelBuild(String hotelname);

	public abstract String insertHotelBuild(
			String hotel_building_info_name,
			String hotel_building_info_flag,
			String hotel_info_name,
			String hotel_info_token,
			String department_id);

	public abstract String deletHotelBuild(String hotel_info_name);

	public abstract String updateHotelBuild(String hotel_building_info_name,
			String hotel_building_info_flag,
			String hotel_building_info_name1);
}
