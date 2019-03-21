package com.jcf.interfaces;

import javax.servlet.http.HttpServletResponse;

public interface TableActionInterfaces {
	/*
	 * @author Lee
	 * @time 2018/1/13
	 * @fun 餐桌添加
	 * table_last_num:名称后缀编号段
	 * table_fiter_num:过滤的数字
	 * */
	public abstract String insertTable(
			String table_info_name,
			String table_info_content,
			String table_info_quantity,
			String table_info_type,
			String table_info_numbers,
			String table_info_fee_own, 
			String table_info_fee_over,
			String table_info_fee_low,
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
			);

	/*
	 * @author Lee
	 * @time 2018/1/13
	 * @fun 餐桌隐藏
	 * */
	public abstract String deleteTable(String token);

	/*
	 * @author Lee
	 * @time 2018/1/15
	 * @fun 餐桌查询
	 * */
	public abstract String queryTable();
	/*
	 * @author Lee
	 * @time 2018/1/15
	 * @fun 无图片餐桌信息修改
	 * */
	public abstract String updateTableInfoNoImg(String table_info_name,
			String table_info_content, String table_info_quantity,
			String table_info_type, String table_info_numbers,
			String table_info_fee_own, String table_info_fee_over,
			String table_info_fee_low, String table_info_url,
			String table_type_sort, String table_type_sig,
			String table_info_order_rule, String department_id,
			String table_info_address,String table_info_token,String table_images_token_ref);
	/*
	 * @author Lee
	 * @time 2018/1/15
	 * @fun 餐桌信息修改
	 * */
	public abstract String updateTableInfo(String table_info_name,
			String table_info_content, String table_info_quantity,
			String table_info_type, String table_info_numbers,
			String table_info_fee_own, String table_info_fee_over,
			String table_info_fee_low, String table_info_url,
			String table_type_sort, String table_type_sig,
			String table_info_order_rule, String department_id,
			String table_info_address,String table_info_token,
			String table_info_images);
	
	/*
	 * @author Lee
	 * @time 2018/1/16
	 * @fun 餐桌查询
	 * */
	public abstract String queryTableByToken(String table_info_token);
	
	/*
	 * @author Lee
	 * @time 2018/1/18
	 * @fun 餐桌查询(默认显示楼层厅的餐桌信息)
	 * */
	public abstract String queryTableDefault();
	/*
	 * @author Lee
	 * @time 2018/1/20
	 * @fun 通过token值修改餐桌是否可预订
	 */
	public abstract String updateTableOrderable(String table_info_token,String table_info_orderable);
	/*
	 * @author Lee
	 * @time 2018/1/20
	 * @fun 通过token值修改餐桌是否可用
	  */
	public abstract String updateTableFlag(String table_info_token,String table_info_flag);
	/*
	 * @author Lee
	 * @time 2018/1/20
	 * @fun 通过token值下载二维码
	 */
	public abstract String downloadQrCode(String table_info_token,HttpServletResponse response);
	/*
	 * @author Lee
	 * @time 2018/1/25
	 * @fun 主页面餐桌状态查询,条件查询
	 */
	public abstract String queryTableByAddressAndTime(String table_info_address,String time,String table_info_type);
	/*
	 * @author Lee
	 * @time 2018/1/26
	 * @fun 主页面餐桌状态查询,条件查询
	 */
	public abstract String queryTableDefaultByTime(String time);
	/*
	 * @author Lee
	 * @time 2018/1/26
	 * @fun 桌台预约时需要请求的数据(所有空桌)
	 * param:table_address_token:分区的token值,table_type_token:分类的token值
	 */
	public abstract String orderTablesDefault(String table_address_token,String table_type_token);
	/*
	 * @author Lee
	 * @time 2018/1/28
	 * @fun 处理预订提交来的数据
	 * param:
	 * bookdinner_info_setnumber:人数
	 * bookdinner_info_ordertime:用餐时间
	 * bookdinner_info_sailer：营销员	
	 * bookdinner_info_channel:渠道
	 * operator_id：服务员	
	 * bookdinner_info_plus:备注
	 * tables:预定的桌子
	 */
	public abstract String dealOrder(String bookdinner_info_setnumber,
			String bookdinner_info_ordertime,
			String bookdinner_info_sailer,
			String bookdinner_info_channel,
			String operator_id,
			String bookdinner_info_plus,
			String tables,
			String bookdinner_info_costom,
			String bookdinner_info_phone,
			String department_id
			);
	/*
	 * @author Lee
	 * @time 2018/1/29
	 * @fun 处理预订提交来的数据
	 * param:bookdinner_info_token
	
	 */
	public abstract String getOrderMes(String bookdinner_info_token);
	/*
	 * @author Lee
	 * @time 2018/2/7
	 * @fun 
	 * param:address_token:分区的token值
	 */
	public abstract String queryTablesByAddressToken(String address_token);
}