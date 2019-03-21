package com.jcf.interfaces;

/*
 *  @author:cht
 *  @time:2018/2/7
 *  @fun:出勤表(oa部分)
 *  @param:	
 * 
 * */
public interface OaAttenceActionInfaces {

	/*
	 *  @author:cht
	 *  @time:2018/2/7
	 *  @fun:出勤表(oa部分)
	 *  @param:attence_group_name(考勤组名)
	 * 
	 * */
	public  abstract String queryAttenceList(String attence_group_name,String page);
	
	
	
	
	public  abstract String deleteAttenceList(String attence_group_name);




	public abstract String insertAttenceList(
			String attence_group_name,
			String attence_group_flag,
			String attence_group_type,
			String attence_group_enclude,
			String attence_group_exclude,
			String attence_group_master,
			String attence_group_workday,
			String attence_group_restday,
			String attence_group_wifilist,
			String department_id
			);




	String updateAttenceList(String attence_group_name,
			String attence_group_flag, String attence_group_type,
			String attence_group_enclude, String attence_group_exclude,
			String attence_group_master, String attence_group_workday,
			String attence_group_restday, String attence_group_wifilist,
			String department_id);
	
	
}
