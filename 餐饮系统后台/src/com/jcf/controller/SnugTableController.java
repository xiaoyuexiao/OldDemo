package com.jcf.controller;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jcf.interfaces.TableActionInterfaces;
@Controller
public class SnugTableController{
		@Autowired
		TableActionInterfaces tableInterfaces;
	  /*
		 * @author Lee
		 * @time 2018/1/13
		 * @fun 餐桌添加
		  testurl:http://localhost:8080/snug/insertTable?
		   table_info_name=1&
		   table_info_content=1&
		   table_info_quantity=1&
		   table_info_type=1&
		   table_info_numbers=1&
		   table_info_fee_own=1&
		   table_info_fee_over=1&
		   table_info_fee_low=1&
		   table_info_url=1&
		   table_type_sort=1&
		   table_type_sig=1&
		   table_info_order_rule=1&
		   department_id=1&
		   table_info_address=45&
		   table_info_flag=1&
		   table_info_sort_rule=20
		 * */
	  /* (non-Javadoc)
	 * @see com.jcf.service.TableInterfaces#insertTable(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	@ResponseBody 
	  @RequestMapping(value="/insertTable",produces="text/html;charset=UTF-8",method = {RequestMethod.GET,RequestMethod.POST})
		public String insertTable(		
										 String table_info_name,
										 String table_info_content,
										 String table_info_quantity,
										 String table_info_type,
										 String table_info_numbers,
										 String table_info_fee_own,
										 String table_info_fee_over,
										 String table_info_fee_low,
										 String table_info_url,
										 String table_type_sort,
										 String table_type_sig,
										 String table_info_order_rule,
										 String department_id,
										 String table_info_address,
										 String table_info_flag,
										 String table_info_sort_rule,
										 String table_last_num,
										 String table_fiter_num,
										 String table_info_images
								 )
	    {
			String string = tableInterfaces.insertTable(table_info_name, table_info_content, table_info_quantity, table_info_type, table_info_numbers, table_info_fee_own, table_info_fee_over, table_info_fee_low, table_type_sort, table_type_sig, table_info_order_rule, department_id,table_info_address,table_info_flag,
					table_info_sort_rule,table_last_num,table_fiter_num,table_info_images);
			return string;
		}
	  
	  /*
		 * @author Lee
		 * @time 2018/1/13
		 * @fun 餐桌隐藏
		 * url:http://localhost:8080/snug/deleteTable?token=tableaddress876241a0d
		 * */
	
	@ResponseBody 
	  @RequestMapping(value="/deleteTable",produces="text/html;charset=UTF-8",method = {RequestMethod.GET,RequestMethod.POST})
	  public String deleteTable(String token){
		String string = tableInterfaces.deleteTable(token); 
		return string;
	  }
	  
	  /*
		 * @author Lee
		 * @time 2018/1/15
		 * @fun 餐桌查询
		 * url:http://localhost:8080/snug/queryTable
		 * */

	@ResponseBody
	  @RequestMapping(value="/queryTable",produces="text/html;charset=UTF-8",method={RequestMethod.GET,RequestMethod.POST})
	  public String queryTable(){
		String string = tableInterfaces.queryTable();
		return string;	
	}
	
	/*
	 * @author Lee
	 * @time 2018/1/18
	 * @fun 餐桌查询(默认显示楼层厅的餐桌信息)
	 * url:http://localhost:8080/snug/queryTableDefault
	 * */
  @ResponseBody
  @RequestMapping(value="/queryTableDefault",produces="text/html;charset=UTF-8",method={RequestMethod.GET,RequestMethod.POST})
  public String queryTableDefault(){
	String string = tableInterfaces.queryTableDefault();
	return string;	
}
	  /*
		 * @author Lee
		 * @time 2018/1/15
		 * @fun 餐桌信息修改
		 * testUrl:
		  http://localhost:8080/snug/updateTableInfo?
				  table_info_name=大方桌1号&
				  table_info_content=边长2米&
				  table_info_quantity=20人&
				  table_info_type=聚餐类型&
				  table_info_numbers=20&
				  table_info_fee_own=20元&
				  table_info_fee_over=60元&
				  table_info_fee_low=40元&
				  table_info_url=www.baidu.com&
				  table_type_sort=003&
				  table_type_sig=好看，大气&
				  table_info_order_rule=大小&
				  department_id=001&
				  table_info_token=tableaddress498790bb7
		 * */
	  @ResponseBody 
	  @RequestMapping(value="/updateTableInfoNoImg",produces="text/html;charset=UTF-8",method = {RequestMethod.GET,RequestMethod.POST})
	  public String updateTableInfoNoImg(
			  	 String table_info_name,
				 String table_info_content,
				 String table_info_quantity,
				 String table_info_type,
				 String table_info_numbers,
				 String table_info_fee_own,
				 String table_info_fee_over,
				 String table_info_fee_low,
				 String table_info_url,
				 String table_type_sort,
				 String table_type_sig,
				 String table_info_order_rule,
				 String department_id,
				 String table_info_address,
				 String table_info_token,
				 String table_images_token_ref
			  ) 
	  {
		String string = tableInterfaces.updateTableInfoNoImg(table_info_name, table_info_content, table_info_quantity, table_info_type, table_info_numbers, table_info_fee_own, table_info_fee_over, table_info_fee_low, table_info_url, table_type_sort, table_type_sig, table_info_order_rule, department_id,table_info_address, table_info_token, table_images_token_ref);
		return string;
	  }
	  
	  
	  //餐桌信息修改有图片
	  @ResponseBody 
	  @RequestMapping(value="/updateTableInfo",produces="text/html;charset=UTF-8",method = {RequestMethod.GET,RequestMethod.POST})
	  public    String updateTableInfo(String table_info_name,
				String table_info_content, String table_info_quantity,
				String table_info_type, String table_info_numbers,
				String table_info_fee_own, String table_info_fee_over,
				String table_info_fee_low, String table_info_url,
				String table_type_sort, String table_type_sig,
				String table_info_order_rule, String department_id,
				String table_info_address,String table_info_token,
				String table_info_images)
	  {
		 return tableInterfaces.updateTableInfo(table_info_name, table_info_content, table_info_quantity, table_info_type, table_info_numbers, table_info_fee_own, table_info_fee_over, table_info_fee_low, table_info_url, table_type_sort, table_type_sig, table_info_order_rule, department_id, table_info_address, table_info_token,table_info_images);
	  };
	  
	  /*
		 * @author Lee
		 * @time 2018/1/15
		 * @fun 餐桌信息查询通过token值
		 * testUrl:http://localhost:8080/snug/queryTableByToken?table_info_token=tableaddress498790bb7
		 * */
	  @ResponseBody
	  @RequestMapping(value="/queryTableByToken",produces="text/html;charset=UTF-8",method = {RequestMethod.GET,RequestMethod.POST})
	  public String queryTableByToken(String table_info_token){
		return tableInterfaces.queryTableByToken(table_info_token);
	  }
	  /*
		 * @author Lee
		 * @time 2018/1/18
		 * @fun 通过token值修改餐桌是否可预订
		 * url:http://localhost:8080/snug/updateTableOrderable?table_info_token=1&table_info_orderable=1
		 * */
	  @ResponseBody
	  @RequestMapping(value="/updateTableOrderable",produces="text/html;charset=UTF-8",method = {RequestMethod.GET,RequestMethod.POST})
	  public String updateTableOrderable(String table_info_token,String table_info_orderable) {
		return tableInterfaces.updateTableOrderable(table_info_token, table_info_orderable);
	  }
	  
	  /*
		 * @author Lee
		 * @time 2018/1/18
		 * @fun 通过token值修改餐桌是否可预订
		 * url:http://localhost:8080/snug/updateTableFlag?table_info_token=1&table_info_flag=1
		 * */
	  @ResponseBody
	  @RequestMapping(value="/updateTableFlag",produces="text/html;charset=UTF-8",method = {RequestMethod.GET,RequestMethod.POST})
	  public String updateTableFlag(String table_info_token,String table_info_flag) {
		return tableInterfaces.updateTableFlag(table_info_token, table_info_flag);
	  }
	  
	  /*
		 * @author Lee
		 * @time 2018/1/20
		 * @fun 通过token值下载二维码
		 * url:http://localhost:8080/snug/downloadQrCode?table_info_token=tableaddress4becdb68e
		 * */
	  @ResponseBody
	  @RequestMapping(value="/downloadQrCode",produces="text/html;charset=UTF-8",method = {RequestMethod.GET,RequestMethod.POST})
	  public String downloadQrCode(String table_info_token,HttpServletResponse response){
		return tableInterfaces.downloadQrCode(table_info_token,response);
	  };
	  
		/*
		 * @author Lee
		 * @time 2018/1/25
		 * @fun 主页面餐桌状态查询,条件查询
		 * http://localhost:8080/snug/queryTableByAddressAndTime?table_info_address=45&time=2018-01-28 15:52:12&table_info_type=1
		 */
	  @ResponseBody
	  @RequestMapping(value="/queryTableByAddressAndTime",produces="text/html;charset=UTF-8",method = {RequestMethod.GET,RequestMethod.POST})
	  public String queryTableByAddressAndTime(String table_info_address,String time, String table_info_type)
		{
		return tableInterfaces.queryTableByAddressAndTime(table_info_address, time, table_info_type);
	  };
	  
	  
	  @ResponseBody
	  @RequestMapping(value="/queryTableDefaultByTime",produces="text/html;charset=UTF-8",method = {RequestMethod.GET,RequestMethod.POST})
	  public String queryTableDefault(String time)
	  {
		return tableInterfaces.queryTableDefaultByTime(time);
	   }
	  //http://localhost:8080/snug/orderTablesDefault?TableAddressToken=tableaddress2ee4caff0table_type_token=1
	  @ResponseBody
	  @RequestMapping(value="/orderTablesDefault",produces="text/html;charset=UTF-8",method = {RequestMethod.GET,RequestMethod.POST})
	  public String orderTablesDefault(String TableAddressToken,String table_type_token)
	  {
		return tableInterfaces.orderTablesDefault(TableAddressToken,table_type_token);
	  }
	  
	  @ResponseBody
	  @RequestMapping(value="/dealOrder",produces="text/html;charset=UTF-8",method = {RequestMethod.GET,RequestMethod.POST})
	  public String dealOrder(
			    String bookdinner_info_setnumber,
				String bookdinner_info_ordertime, String bookdinner_info_sailer,
				String bookdinner_info_channel, String operator_id,
				String bookdinner_info_plus, String tables,String bookdinner_info_costom,
				String bookdinner_info_phone,String department_id)
	  {
				return tableInterfaces.dealOrder(bookdinner_info_setnumber,bookdinner_info_ordertime, bookdinner_info_sailer, bookdinner_info_channel, operator_id, bookdinner_info_plus, tables,bookdinner_info_costom,bookdinner_info_phone,department_id);
	  }
	  
	  //http://localhost:8080/snug/getOrderMes?bookdinner_info_token=4
	  @ResponseBody
	  @RequestMapping(value="/getOrderMes",produces="text/html;charset=UTF-8",method = {RequestMethod.GET,RequestMethod.POST})
	  public String getOrderMes(String bookdinner_info_token)
	  {
				return tableInterfaces.getOrderMes(bookdinner_info_token);
	  }
	  
	  //http://localhost:8080/snug/queryTablesByAddressToken?address_token=tableaddress87e580f88
	  @ResponseBody
	  @RequestMapping(value="/queryTablesByAddressToken",produces="text/html;charset=UTF-8",method = {RequestMethod.GET,RequestMethod.POST})
	  public String queryTablesByAddressToken(String address_token)
	  {
				return tableInterfaces.queryTablesByAddressToken(address_token);
	  }
}
