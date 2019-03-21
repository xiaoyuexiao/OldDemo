package com.jcf.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.jcf.interfaces.OaSchedulesActionlnfaces;
@Controller
public class OaSchedulesActionController {
	@Autowired
	OaSchedulesActionlnfaces oaSchedules;
	
	@ResponseBody
	@RequestMapping(value="/insertSchtedulesList",produces="text/html;charset=UTF-8",method={RequestMethod.GET,RequestMethod.POST})
	public String insertSchtedulesList(
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
			){
	
	        return oaSchedules.OaInsertSchedules(schedules_info_flag, schedules_info_name, schedules_info_work_time_type, schedules_info_work_time_first_start, schedules_info_work_time_first_end, schedules_info_work_time_second_start, schedules_info_work_time_second_end, schedules_info_work_time_third_start, schedules_info_work_time_third_end, schedules_info_work_time_first_rest_start, schedules_info_work_time_first_rest_end, schedules_info_work_time_second_rest_start, schedules_info_work_time_second_rest_end, schedules_info_work_time_third_rest_start, schedules_info_work_time_third_rest_end, schedules_info_duty, schedules_info_delay_time, operator_id, department_id);
		
				  
	}
	
	@ResponseBody
	@RequestMapping(value="/deleteSchedules",produces="text/html;charset=UTF-8",method={RequestMethod.GET,RequestMethod.POST})
	public String deleteSchedules(String schedules_info_token){
		return oaSchedules.OaDelSchedules(schedules_info_token);	
	}

	@ResponseBody
	@RequestMapping(value="/querySchedules",produces="text/html;charset=UTF-8",method={RequestMethod.POST,RequestMethod.GET})
	public String querySchedules(String schedules_info_token,String page){
		return oaSchedules.OaQuerySchedules(schedules_info_token,page);
		
	}
	
	@ResponseBody
	@RequestMapping(value="/updateSchtedulesList",produces="text/html;charset=UTF-8",method = {RequestMethod.GET,RequestMethod.POST})
	public String updateSchtedulesList(
		String	schedules_info_token,
		String	schedules_info_flag,
		String	schedules_info_name, 
		String	schedules_info_work_time_type,
		String	schedules_info_work_time_first_start,
		String	schedules_info_work_time_first_end,
		String	schedules_info_work_time_second_start,
		String	schedules_info_work_time_second_end, 
		String	schedules_info_work_time_third_start, 
		String	schedules_info_work_time_third_end,
		String	schedules_info_work_time_first_rest_start, 
		String	schedules_info_work_time_first_rest_end, 
		String	schedules_info_work_time_second_rest_start,
		String	schedules_info_work_time_second_rest_end, 
		String	schedules_info_work_time_third_rest_start,
		String	schedules_info_work_time_third_rest_end,
		String	schedules_info_duty,
		String	schedules_info_delay_time,
		String  operator_id, 
		String 	department_id
			){
		return oaSchedules.OaUpdateSchedules(
				schedules_info_token,
				schedules_info_flag,
				schedules_info_name,
				schedules_info_work_time_type,
				schedules_info_work_time_first_start,
				schedules_info_work_time_first_end,
				schedules_info_work_time_second_start,
				schedules_info_work_time_second_end,
				schedules_info_work_time_third_start,
				schedules_info_work_time_third_end, 
				schedules_info_work_time_first_rest_start,
				schedules_info_work_time_first_rest_end,
				schedules_info_work_time_second_rest_start,
				schedules_info_work_time_second_rest_end,
				schedules_info_work_time_third_rest_start,
				schedules_info_work_time_third_rest_end,
				schedules_info_duty,
				schedules_info_delay_time,
				operator_id,
				department_id
				);
	};
	
	

}