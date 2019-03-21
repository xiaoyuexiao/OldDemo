package com.jcf.interfaces;



import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

/*
 *  @author:cht
 *  @time:2018/1/29
 *  @fun:人物表(oa部分)
 *  @param:
		
 * 
 * */
         public interface OaAdminAcitonInfaces {
	
	/*
	 *  @author:cht
	 *  @time:2018/1/29
	 *  @fun:查询酒店人物
	 *  @param:
	 * 
	 * */
           public  abstract String queryAdminList(String admin_info_name,String page);
           /*
       	 *  @author:cht
       	 *  @time:2018/1/29
       	 *  @fun:删除酒店人物
       	 *  @param:
       	 * 
       	 * */
		
           public abstract String deletAdminList(String admin_info_name);
           
           /*
          	 *  @author:cht
          	 *  @time:2018/1/29
          	 *  @fun:修改酒店人物
          	 *  @param:
          	 * 
          	 * */
           public abstract String updateAdminList(
        		   @RequestParam("file") CommonsMultipartFile[] file, 
        		   String admin_info_name,
        		   String admin_info_phone,
        		   String admin_info_birthday,
        		   String admin_info_idcard,
        		   String admin_info_pwd,
        		   String admin_info_flag,
        		   String department_id,
        		   String admin_info_role,
        		   String admin_info_master,
        		   String sub_department_id
        		   );
           /*
         	 *  @author:cht
         	 *  @time:2018/1/29
         	 *  @fun:OA增加酒店人物
         	 *  @param:
         	 *  role_info_flag(不可用--0,可用--1)
         	 *  
         	 * */
           
           public abstract String insertAdminList(
        		   @RequestParam("file") CommonsMultipartFile[] file, 
        		   String admin_info_name,
        		   String admin_info_phone,
        		   String admin_info_birthday,
        		   String admin_info_idcard,
        		   String admin_info_pwd,
        		   String admin_info_flag,
        		   String department_id,
        		   String admin_info_role,
        		   String admin_info_master,
        		   String sub_department_id
        		   );
           
           /*
         	 *  @author:cht
         	 *  @time:2018/1/29
         	 *  @fun:OA酒店人物登录
         	 *  @param:
         	 *  role_info_flag(不可用--0,可用--1)
         	 *  
         	 * */
           
           public abstract String loginAdminList(String admin_info_name,String admin_info_pwd);
}
