package com.jcf.interfaces;

import java.util.ArrayList;
import java.util.Map;

/*
 *  @author:cht
 *  @time:2018/1/30
 *  @fun:分组(oa部分)
 *  @param:
		
 * 
 * */
         public interface OaGroupAcitonInfaces {
        	 
         public abstract String queryGroupList
         (
        		 String department_id,
        		 String role_info_type,
        		 String sub_sub_department_id	 
         );
}
