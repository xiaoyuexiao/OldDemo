package com.jcf.interfaces;

import java.util.ArrayList;
import java.util.Map;

/*
 *  @author:cht
 *  @time:2018/1/29
 *  @fun:角色表(oa部分)
 *  @param:
			role_info_token:唯一 ();
		    role_info_flag：(不可用--0,可用--1)
		    role_info_type：角色类型
		    role_info_name：角色名称
		    department_id:部分编号
 * 
 * */



/**
 * @author Administrator
 * @time:2018-3-15
 * @fun:
 * @param:
 */
public interface OaRoleAcitonInfaces {
	
		/*
		 *  @author:cht
		 *  @time:2018/1/29
		 *  @fun:OA查询角色
		 *  @param:
		 * 
		 * */

        public abstract String queryRoleList(String sub_department_id,String page);
           /*
       	 *  @author:cht
       	 *  @time:2018/1/29
       	 *  @fun:OA删除角色
       	 *  @param:
       	 * 
       	 * */
		
        public abstract String deletRoleList(String role_info_token);
           
       /*
      	 *  @author:cht
      	 *  @time:2018/1/29
      	 *  @fun:OA修改角色
      	 *  @param:
      	 * 
      	 * */
       public abstract String updateRoleList(
    			String role_info_token,
    			String role_info_flag,
    			String role_info_type,
    			String role_info_name,
    			String operator_id,
    			String department_id,
    			String sub_department_id
    		   );
           
           
       public abstract String insertRoleList(
    		   String role_info_flag,
    		   String role_info_type,
    		   String role_info_name,
    		   String operator_id,
    		   String department_id,
    		   String sub_department_id
    		   );
    
	       /**
	     * @author lee
	     * @time:2018-3-15
	     * @fun:查询所有职员
	     * @param department_id：酒店的标识
	     * @param sub_department_id：子部门的
	     * @param role_info_token
	     * @return:
	     */
	    public abstract String queryAdmins(
	    		String department_id,
	    		String sub_department_id,
	    		String role_info_token
	    		);
 }
