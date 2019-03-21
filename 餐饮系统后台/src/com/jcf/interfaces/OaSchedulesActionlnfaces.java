package com.jcf.interfaces;

/**
 * @author cht
 * @time:2018/2/5
 * @fun:分组(oa部分)
 * @prama 加班排表
 */

public interface OaSchedulesActionlnfaces {
    //添加排班表	
	public String OaInsertSchedules(
			String schedules_info_flag,
			String schedules_info_name,
			String schedules_info_work_time_type,
			String schedules_info_work_time_first_start,
			String schedules_info_work_time_first_end,
			String schedules_info_work_time_second_start,
			String schedules_info_work_time_second_end,
			String schedules_info_work_time_third_start,
			String schedules_info_work_time_third_end,
			String schedules_info_work_time_first_rest_start,
			String schedules_info_work_time_first_rest_end,
			String schedules_info_work_time_second_rest_start,
			String schedules_info_work_time_second_rest_end,
			String schedules_info_work_time_third_rest_start,
			String schedules_info_work_time_third_rest_end,
			String schedules_info_duty,
			String schedules_info_delay_time,
			String operator_id,
			String department_id
			);
    //修改表
	public String OaUpdateSchedules(
			String schedules_info_token,
			String schedules_info_flag,
			String schedules_info_name,
			String schedules_info_work_time_type,
			String schedules_info_work_time_first_start,
			String schedules_info_work_time_first_end,
			String schedules_info_work_time_second_start,
			String schedules_info_work_time_second_end,
			String schedules_info_work_time_third_start,
			String schedules_info_work_time_third_end,
			String schedules_info_work_time_first_rest_start,
			String schedules_info_work_time_first_rest_end,
			String schedules_info_work_time_second_rest_start,
			String schedules_info_work_time_second_rest_end,
			String schedules_info_work_time_third_rest_start,
			String schedules_info_work_time_third_rest_end,
			String schedules_info_duty,
			String schedules_info_delay_time,
			String operator_id,
			String department_id
			);
	
	  //查看排班表
	public String OaQuerySchedules(String schedules_info_token,String page);
	
			
	 //删除排班表
	public String OaDelSchedules(String schedules_info_token);
	




	
}
