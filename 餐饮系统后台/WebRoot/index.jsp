<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'index.jsp' starting page</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<style type="text/css">
		body{color:blue}
		h1{color:red}
		h2{color:green}
		a,input{color:blue}
	</style>
  </head>
  
  <body>
  
  <h1>=======================部门===================</h1>
  
  <h2>餐厅基础设置</h2>
  <ol>
  	
  	<li>
  		<a href="http://localhost:8080/snug/updateResturnant/?department_info_name=%E9%A4%90%E5%8E%85&department_info_token=resturnant7f0400893&department_info_detail=%E9%A4%90%E5%8E%85%E4%BD%8D%E5%B1%85%E4%BA%8C%E6%A5%BC&department_info_address=a%E6%A0%8B3,4,5%E6%A5%BC&department_info_phone=15700218161&department_info_plus=%E6%97%A9%E5%B8%82,8:00-10:00;%E6%99%9A%E5%B8%82,16:00-18:00&department_info_logos=15203470059191.png&department_info_images_token=15203470187052.png,15203470187052.png">（修改/保存）第一次访问时：department_info_token值为空，修改图片传过来的token值用“，”隔开</a>
		
  	</li>
  		
  	<li>
  		<a href="http://localhost:8080/snug/updateResturnantNoImgs/?department_info_name=餐厅&department_info_token=resturnantc8e52ebdd4&department_info_logo=5.jpg&department_info_detail=餐厅位居二楼&department_info_address=a栋3,4,5楼&department_info_phone=15700218161&department_info_plus=早市,8:00-10:00;晚市,16:00-18:00&department_info_logo=123&department_info_images_token=resturnantc8e52ebdd4">无图片餐厅基础设置（token值没有表示第一次设置）</a>
  	</li>
  	<li>
  		<a href = "http://localhost:8080/snug/queryResMes?department_id=001">查询餐厅基础设置数据</a>
  	</li>
  	
  </ol>
  
  <h1>======================桌台分区==================</h1>
  <h2>查询：</h2>
  <ol>
	<li><a href=" http://localhost:8080/snug/queryTableAddress"> 查询所有台桌分区</a></li>
	<li><a href="http://localhost:8080/snug/queryTableChildAddress?token=12543">查询子分区以及分区，有哪些分类，通过父分区的token值</a></li>
  </ol>
  <h2>增加：</h2>
  <ol>
  	<li>
  		 <form action="http://localhost:8080/snug/insertTableAddress?department_address_name=c%E6%A0%8B&department_address_content=001&department_address_sort=001&department_address_recommand=001&department_address_flag=1&department_address_parent=12543&department_id=00111&department_address_images=15204076548241.png,15204076548301.png" method="post" enctype="multipart/form-data" >
		    	<input type="submit" value="添加">
  		 </form>
  	</li>
  </ol>
  <h2>修改：</h2>
  <ol>
  	<li>
  		<form action="http://localhost:8080/snug/updateTableAddress?department_address_name=A栋&department_address_content=武城大厦，五星级酒店&department_address_sort=001&department_address_recommand=推荐&department_address_parent=0001&department_id=001&token=tableaddress07c8ec0c6&department_address_images=15204147347246.png,15204147276482.png" method="post" enctype="multipart/form-data" >
		    	<input type="submit" value="修改">
  		 </form>
  	</li>
  	<li><a href="http://localhost:8080/snug/updateTableAddressNoImg?department_address_name=A栋&department_address_content=武城大厦，五星级酒店&department_address_sort=001&department_address_recommand=推荐&department_address_parent=0001&department_id=001&token=tableaddress3c21cc2a4&department_address_images=12345,45678">无图片修改</a></li>
  </ol>
  <h2>删除：</h2>
  <ol>
  	<li>
  		<a href="http://localhost:8080/snug/deleteTableAddress?token=tableaddress2985209d1">分区连级隐藏</a>
  	</li>
  </ol>
  <h1>======================桌台分类===================</h1>
  <h2>查询：</h2>
  <ol>
  	<li><a href="http://localhost:8080/snug/queryTableType"> 查询所有台桌分类(没有参数)</a></li>
  </ol>
   <h2>增加：</h2>
  <ol>
  	<li>
  		<form action="http://localhost:8080/snug/insertTableType?table_type_name=%E5%8E%85&table_type_sub_name=%E5%8C%85%E9%97%B4&table_type_sort=%E5%A4%A7%E5%B0%8F&table_type_recommand=%E6%8E%A8%E8%8D%90&table_type_content=4564564&table_type_flag=1&table_type_images=15204100808406.png,15204100808406.png" method = "post" enctype="multipart/form-data">
	    	<input type="submit" value="添加">
   		 </form>
  	</li>
  </ol>
   <h2>修改：</h2>
  <ol>
  	<li>
  		<form action="http://localhost:8080/snug/updateTableType?table_type_name=1&table_type_sub_name=11&table_type_sort=大小&table_type_recommand=推荐&table_type_content=没有描述&table_type_flag=1&table_type_token=tabletype587cc7fdb&table_type_images=15204146888655.png,15204146888655.png&department_id=001" method = "post" enctype="multipart/form-data">
	    	<input type="submit" value="修改">  
    	</form>
  	</li>
  	<li><a href="http://localhost:8080/snug/updateFoodTypeNoImg?food_type_name=粤菜&food_type_father=某分类的token&food_type_sort=1&food_type_runtime=8:00-12:00,13:00-18:00,19:00-22:00&food_type_flag=1&department_id=001&food_type_images_ref=foodtype775670abc8,foodtype775670abc7&food_type_token=foodtypee3043936f">菜品修改无图片</a></li>
  </ol>
   <h2>删除：</h2>
  <ol>
  	<li><a href="http://localhost:8080/snug/deleteTableType?table_type_token=47897774"> 隐藏台桌分类（通过token）</a><br></li>
  </ol>
  
<h1>======================桌台===================</h1>
<h2>查询：</h2>
<ol>

	<li>
		黄：<a href="http://localhost:8080/snug/queryTablesByAddressToken?address_token=tableaddressb76d80e2c">根据分区的token值查询该分区下面的所有桌子</a>
	</li>
	<li>
		<a href="http://localhost:8080/snug/queryTableByToken?table_info_token=tableaddress498790bb7">通过token值查询(二维码的链接)</a>
	</li>
	<li>
		 <a href="http://localhost:8080/snug/queryTableDefault">黄：管理/桌台设置默认数据（丽景楼一层厅下面的所有餐桌数据）</a><br>
	</li>
</ol>
<h2>增加：</h2> 
<ol>
	<li>
		<form action="http://localhost:8080/snug/insertTable?table_info_name=1&table_info_content=1&table_info_quantity=1&table_info_type=1&table_info_numbers=1&table_info_fee_own=1&table_info_fee_over=1&table_info_fee_low=1&table_info_url=1&table_type_sort=1&table_type_sig=1&table_info_order_rule=1&department_id=1&table_info_address=45&table_info_flag=1&table_info_sort_rule=20&table_last_num=10,20&table_fiter_num=2,3&table_info_images=15204100800884.png" method="post" enctype="multipart/form-data" >	   		
	    	<input type="submit" value="添加">
   		</form>
	</li>
</ol> 
<h2>修改：</h2> 
<ol>
 	<li>
 		<form action="http://localhost:8080/snug/updateTableInfo?table_info_name=%E5%A4%A7%E6%96%B9%E6%A1%8C1%E5%8F%B7&table_info_content=%E8%BE%B9%E9%95%BF2%E7%B1%B3&table_info_quantity=20%E4%BA%BA&table_info_type=%E8%81%9A%E9%A4%90%E7%B1%BB%E5%9E%8B&table_info_numbers=20&table_info_fee_own=20%E5%85%83&table_info_fee_over=60%E5%85%83&table_info_fee_low=40%E5%85%83&table_info_url=www.baidu.com&table_type_sort=003&table_type_sig=%E5%A5%BD%E7%9C%8B%EF%BC%8C%E5%A4%A7%E6%B0%94&table_info_order_rule=%E5%A4%A7%E5%B0%8F&department_id=001&table_info_address=002&table_info_token=tableaddress165e760d9&table_info_images=15204146888655.png,15204146888655.png" method="post" enctype="multipart/form-data" >
	    	<input type="submit" value="修改">
   		</form>
 	</li>
 	<li>
 		<a href="http://localhost:8080/snug/updateTableInfoNoImg?table_info_name=%E5%A4%A7%E6%96%B9%E6%A1%8C1%E5%8F%B7&table_info_content=%E8%BE%B9%E9%95%BF2%E7%B1%B3&table_info_quantity=20%E4%BA%BA&table_info_type=%E8%81%9A%E9%A4%90%E7%B1%BB%E5%9E%8B&table_info_numbers=20&table_info_fee_own=20%E5%85%83&table_info_fee_over=60%E5%85%83&table_info_fee_low=40%E5%85%83&table_info_url=www.baidu.com&table_type_sort=003&table_type_sig=%E5%A5%BD%E7%9C%8B%EF%BC%8C%E5%A4%A7%E6%B0%94&table_info_order_rule=%E5%A4%A7%E5%B0%8F&department_id=001&table_info_address=002&table_info_token=tableaddress498790bb7&table_images_token_ref=789456123,456">无图片餐桌修改（token）</a>
 	</li>
</ol>
<h2>删除：</h2> 
<ol>
 	<li>
 		<a href="http://localhost:8080/snug/deleteTable?token=tableaddress876241a0d"> 餐桌隐藏（token）</a>
 	</li>
 	<li>
 		
 	</li>
</ol>
<h2>其他：</h2>
<ol>
	<li><a href="http://localhost:8080/snug/updateTableFlag?table_info_token=1&table_info_flag=1">设置餐台是否启用(toekn,启用值)</a><br></li>
	 <a href="http://localhost:8080/snug/updateTableOrderable?table_info_token=1&table_info_orderable=1">餐台是否可预定（token,预定值）</a><br>
</ol>
<h2>二维码</h2> 
<ol>
 	<li>
 		<a href="http://localhost:8080/snug/downloadQrCode?table_info_token=tableaddress4becdb68e">单个二维码下载（token）</a>
 	</li>
 	<li>
 		批量下载（未做）
 	</li>
</ol>
<h1>=====================标签=========================</h1>
<h2>查询</h2> 
<ol>
	<li>根据类型查询（sigType）</li>
</ol>

<h2>增加</h2> 
<ol>
	<li>
		<a href="http://localhost:8080/snug/insertSig?sig_name=很少见啊&sig_type=123&department_id=123">增加标签</a>
	</li>
</ol> 

<h2>删除</h2> 
<ol>
	<li>
		<a href="http://localhost:8080/snug/deleteSig?token=123">删除标签(token)</a>
	</li>
</ol>
<h1>====================菜品分类======================</h1>
 <h2>查询</h2> 
<ol>
	<li>
		<a href="http://localhost:8080/snug/queryFoodType">查询所有菜品分类（分了层级）</a>
	</li>
	<li>
		查询所有菜品分类
	</li>
</ol>
 <h2>增加</h2> 
<ol>
	<li>
		<form name="serForm" action="http://localhost:8080/snug/insertFoodType?food_type_name=%E5%B7%9D%E8%8F%9C&food_type_father=%E5%B7%9D%E8%8F%9C&food_type_sort=%E5%B7%9D%E8%8F%9C&food_type_runtime=%E5%B7%9D%E8%8F%9C&food_type_flag=1&department_id=%E5%B7%9D%E8%8F%9C&food_type_images=15204017242602.png,15204021887711.png" method="post"  enctype="multipart/form-data">
			<input type="submit" value="添加"/>
		</form>
		
	</li>
	<li>
		
	</li>
</ol>
 <h2>修改</h2> 
<ol>
	<li>
		<form name="serForm" action="http://localhost:8080/snug/updateFoodType?food_type_name=粤菜&food_type_father=某分类的token&food_type_sort=1&food_type_runtime=8:00-12:00,13:00-18:00,19:00-22:00&food_type_flag=1&department_id=001&food_type_images=1520402217364touxiang.png,15204076548241.png&food_type_token=foodtypee83956427" method="post"  enctype="multipart/form-data">
			<input type="submit" value="修改"/>
		</form>
	</li>
	<li>
		<a href="http://localhost:8080/snug/updateFoodTypeNoImg?food_type_name=粤菜&food_type_father=某分类的token&food_type_sort=1&food_type_runtime=8:00-12:00,13:00-18:00,19:00-22:00&food_type_flag=1&department_id=001&food_type_images_ref=foodtype775670abc8,foodtype775670abc7&food_type_token=foodtypee3043936f">不带图片的修改</a>
	</li>
</ol> 
<h2>删除</h2>
<ol>
	<li>
		<a href="http://localhost:8080/snug/deleteFoodType?food_type_token=foodtype430b67868">菜品分类删除</a>
	</li>
</ol>
<h1>====================菜品======================</h1>
<h2>查询</h2>
<ol>
	<li>
		<a href="http://localhost:8080/snug/queryDefaultFood">默认显示的所有数据（菜品设置）</a>
	</li>
	<li>
		<a href="http://localhost:8080/snug/queryRuntime?department_info_token=resturnantf28e3f5e5">供应时段查询(根据部门的token值)</a>
	</li>
</ol>
<h2>增加</h2>
<ol>
	<li>
		<a href="http://localhost:8080/snug/insertFood?food_info_name=%E8%8B%A6%E7%93%9C%E8%82%89%E7%89%87&food_info_sig=%E5%BE%AE%E8%BE%A3,%E5%A5%BD%E5%90%83&food_info_content=%E6%AD%A4%E8%8F%9C%E6%B8%85%E7%81%AB%E5%8E%BB%E7%83%AD%EF%BC%8C%E5%91%B3%E5%81%8F%E8%8B%A6%E3%80%82&food_info_quantity=20&food_info_unit=%E4%BB%BD&food_info_sort=1&food_info_order=%E5%A4%A7%E4%BB%BD&food_info_material=%E8%8B%A6%E7%93%9C&food_info_sold=1000%E4%BB%BD&food_info_type=%E7%83%AD%E8%8F%9C&food_info_price=50%E5%85%83&department_id=123&food_info_material_cost=50&food_info_image_word_content=图文描述&food_info_images=15204076548241.png,15204076548301.png">添加菜品</a>
	</li>
	<li>
	
	</li>
</ol> 

<h2>修改</h2>
<ol>
	<li>
		<form name="serForm" action="http://localhost:8080/snug/updateFoodInfo?food_info_name=222222&food_info_sig=微辣，美味&food_info_content=很好吃&food_info_quantity=100&food_info_unit=份&food_info_sort=003&food_info_order=大份&food_info_material=鱼&food_info_sold=1500&food_info_type=家常菜&food_info_price=100元&department_id=001&food_info_token=foods649301f12&food_info_material_cost=123&food_info_image_word_content=5456&food_info_images=15204146980616.png,15204146980626.png" method="post"  enctype="multipart/form-data">
			
			<input type="submit" value="upload"/>
		</form>
	</li>
	<li>
		<a href="http://localhost:8080/snug/updateFoodInfoNoImg?food_info_name=水煮鱼&food_info_sig=微辣，美味&food_info_content=很好吃&food_info_quantity=100&food_info_unit='份'&food_info_sort=003&food_info_order=大份&food_info_material=鱼&food_info_sold=1500&food_info_type=家常菜&food_info_price=100元&department_id=001&food_info_token=32232&food_info_material_cost=123&food_info_image_word_content=5456&images_token_ref=456">没有图片的菜品修改</a>
	</li>
</ol>  
<h2>删除</h2>
<ol>
	<li>
		<a href="http://localhost:8080/snug/deleteFood?token=foods00e5129d7">菜品隐藏（token）</a>
	</li>
	<li>
		<a href="http://localhost:8080/snug/deleteFoods?foods_token=foods00e5129d7,12345689">菜品批量删除</a>
	</li>
</ol>   
 <h2>其他</h2>
 <ol>
	 <li>
		 <a href="http://localhost:8080/snug/getFoodCost?material_mes=maojian001,10;dashuan001,10">菜品计算成本</a>
	 </li> 
 </ol>
<h1>=======================以上是黄养疼的数据========================</h1>
<h1 style="color:blue">==========================以下是王娟霞的的数据=======================</h1>
 <h1>=====会员分组=======</h1>
   <a href="http://localhost:8080/snug/queryMemberGroups">会员分组列表查询</a><br>
   <a href="http://localhost:8080/snug/updateMemberGroup?member_group_name=煞笔大队&member_group_master=大黄&member_group_phone=1572245647&department_id=456&member_group_flag=1&member_group_token=1222222222">会员修改</a>
   <a href="http://localhost:8080/snug/deleteMemberGroup?member_group_token=123">会员分组删除</a><br>
   <a href="http://localhost:8080/snug/addMemberGroup?member_group_name=九次方科技有限公司&member_group_master=谢帅&member_group_phone=15700218161&department_id=001">添加分组</a><br>
   <h1>=====会员=======</h1>
   <a href="http://localhost:8080/snug/queryMembers">会员列表查询（含有分组）</a><br>
   <a href="http://localhost:8080/snug/deleteMember?token=11">会员删除（token）</a><br>
   <a href="#">会员修改</a>
   <a href="http://localhost:8080/snug/queryFuzzyMembers?search_condition=15700218161">模糊查询</a><br>
   <a href="http://localhost:8080/snug/queryMembersList">会员列表查询</a>
   <a href="http://localhost:8080/snug/queryBillsList">挂账账单查询</a>
   <a href="http://localhost:8080/snug/queryBillsListLikeName?key_word=157">挂账模糊查询</a>
   <a href="http://localhost:8080/snug/queryMembersAllMes">所有会员的所有信息</a>
   <h1>=====会员等级=========</h1>
   <a href="http://localhost:8080/snug/insertMemberDegree?member_degree_name=%E7%99%BD%E9%87%91%E4%BC%9A%E5%91%98&member_degree_discount=5%E6%8A%98&member_degree_lower=500%E7%A7%AF%E5%88%86&member_degree_real=5%E7%BA%A7&member_degree_unit=100:1&department_id=001&member_degree_credit=500元&member_degree_settlement_date=30天">添加会员等级</a><br>
   <a href="http://localhost:8080/snug/queryMemberDegrees">会员等级查询</a><br>
   <a href="http://localhost:8080/snug/deleteMemberDegree?member_degree_token=?">会员等级删除</a><br>
   <a href="http://localhost:8080/snug/updateMemberDegrees?member_degree_name=白金会员&member_degree_discount=5&member_degree_lower=100&member_degree_real=3&member_degree_unit=100:1&member_degree_flag=1&member_degree_credit=500&member_degree_settlement_date=2018-10-10&member_degree_token=memberdegreefc0a5cc25">会员等级修改</a>
 <h1>=============库存==============</h1>
   <a href="http://localhost:8080/snug/queryGoodsMes">库存列表默认显示（商品的列表的显示）</a><br>
   <a href="http://localhost:8080/snug/queryGoodsByFirAndTow?firstfather=1&towtoken=123">库存二级分类数据查询（数据时已经入库的：2）</a><br>
   <a href="http://localhost:8080/snug/queryAllGoodsMes?key_word=123">库存列表模糊查询（根据名称查询的）</a><br>
   <a href="http://localhost:8080/snug/queryInGoodsMes?status_flag=4">入库管理，入库单查询（传入对应状态值）</a><br>
   <a href="http://localhost:8080/snug/queryLikeNameAndTime?key_word=3">入库管理模糊搜索</a><br>
   <a href="http://localhost:8080/snug/queryInGoodMes?attence_info_token=3">通过token值查询申购单下面的库存信息</a>
   <form name="serForm" action="http://localhost:8080/snug/addGoods?name=商品名称&type_one=1&type_sec=0001&unit=公斤&in_price=100&out_price=200&min_inventory=20&max_inventory=50&guarantee=120&maintain=100&servicelife=30&department_id=001" method="post"  enctype="multipart/form-data">
		选择文件：<input type="file" name="file">
		<input type="file" name="file">
		<input type="submit" value="添加">
   </form>
   <a href="http://localhost:8080/snug/addOutGoodsMes?out_material_mes=%5B%7B%22food_material_out_type%22:%220001%22,%22food_material_out_good_token%22:%22商品的token%22,%22food_material_out_flag%22:%223%22,%22food_material_out_quantity%22:%2220%22,%22food_material_out_unit%22:%22瓶%22,%22food_material_out_to_department%22:%22去处%22,%22food_material_out_to_people%22:%22去处人%22,%22food_material_out_plus%22:%22备注%22,%22food_material_out_time%22:%22时间%22,%22food_material_out_sort%22:%22序号，排序%22,%22food_material_out_good_token%22:%22货物的token值%22,%22food_material_out_name%22:%22商品名称%22,%22department_id%22:%22部门id%22%7D,%7B%22food_material_out_type%22:%220001%22,%22food_material_out_good_token%22:%22商品的token%22,%22food_material_out_flag%22:%223%22,%22food_material_out_quantity%22:%2220%22,%22food_material_out_unit%22:%22瓶%22,%22food_material_out_to_department%22:%22去处%22,%22food_material_out_to_people%22:%22去处人%22,%22food_material_out_plus%22:%22备注%22,%22food_material_out_time%22:%22时间%22,%22food_material_out_sort%22:%22序号，排序%22,%22food_material_out_good_token%22:%22货物的token值%22,%22food_material_out_name%22:%22商品名称%22,%22department_id%22:%22部门id%22%7D%5D&flag=3
   ">出库和报损</a>
 <h1 style="color:green">=======================以下是沈佳明的的数据===========================</h1>
  	<h3>==========餐桌预定操作==========</h3>
    <a href="http://localhost:8080/snug/dealOrder?bookdinner_info_setnumber=10&bookdinner_info_ordertime=2018-1-28 10:10:20&bookdinner_info_sailer=小王&bookdinner_info_channel=微信&operator_id=大黄&bookdinner_info_plus=无备注&tables=123,234,345&bookdinner_info_costom=沈佳明&bookdinner_info_phone=18284000035&department_id=0001">餐桌预定处理</a><br>
    <a href="http://localhost:8080/snug/getOrderMes?bookdinner_info_token=4">通过订单的token值，查询订单的食物信息（不止用于预定）</a><br>
 	<a href="http://localhost:8080/snug/queryTableByAddressAndTime?table_info_address=45&time=2018-01-28 15:52:12&table_info_type=1">主页面根据分区，类型，时间段来查询的桌台信息和订单信息</a><br>
    <a href="http://localhost:8080/snug/queryTableDefaultByTime?time=2018-01-28%2015:52:12">主页面默认查询的数据（所有桌台，以及订单状态）</a><br>
 	<a href="http://localhost:8080/snug/turnFood?&bookdinner=2000&turntobookdinner=2&foodInfo=0001,%E6%B8%85%E8%92%B8%E8%82%89,6,123%E5%85%83,%E5%B7%B2%E7%BB%8F%E5%82%AC%E5%8D%95;0002,%E5%87%A4%E5%B0%BE,5,13%E5%85%83,%E5%B7%B2%E7%BB%8F%E5%82%AC%E5%8D%95">转菜（参数：bookdinner：哪一个订单的token,turntobookdinner:转向哪一个订单的token，foodInfo：0001,清蒸肉,6,123元,已经催单【菜品token,菜品名称,菜品数量，菜品单价】;）</a><br>
 <h1 style="color:yellow">=======================其他数据===========================</h1>
   <h1>========用户注册登录========</h1>
   <a href="http://localhost:8080/snug/verifycode?phone=15700218161">获取验证码(phone:电话)</a><br>
   <a href="http://localhost:8080/snug/register?phone=15700218161&snum=2407&pwd=794594866&img=123">用户注册(phone:电话,snum:验证码,pwd:密码,img:头像)</a><br>
   <a href="http://localhost:8080/snug/login?phone=15700218161&pwd=794594866">用户登录(phone:电话,pwd:密码)</a><br>
   <a href="http://localhost:8080/snug/changepwd?phone=15700218161&snum=4033&pwd=330236184">修改密码(phone：电话，snum：验证码，pwd:密码)</a><br>
 	<h1>=====酒店=========</h1>
	<form name="serForm" action="http://localhost:8080/snug/insertCompany" method="post"  enctype="multipart/form-data">
		选择文件：<input type="file" name="file">
		选择文件：<input type="file" name="file">
		name:<input type="text" name="company_info_name">
		company_info_content:<input type="text" name="company_info_content">
		company_info_phone:<input type="text" name="company_info_phone">
		company_info_address:<input type="text" name="company_info_address">
		<input type="submit" value="酒店添加"/>
	</form>
 
 
 
 
 
 
 
 
  
 
  <div style="float:left;width:50%">
  	<a href="http://localhost:8080/snug/queryTable">所有餐桌查询666</a><br>
	<a href="http://localhost:8080/snug/queryFood">菜品查询</a><br>
  </div>
  <div style="float:left;width:50%;padding-bottom:80px">
  	<h1>========图片上传=========</h1>
   <form name="serForm" action="http://localhost:8080/snug/upImg" method="post"  enctype="multipart/form-data">
		选择文件：<input type="file" name="file">
		图片类型：<input type="text" name="total_image_type">
		部门id：<input type="text" name="department_id">
		<input type="submit" value="上传">
	</form>
	<a href="http://localhost:8080/snug/deleteImg?image_name=1519543336979">图片删除</a>
	<a href="http://localhost:8080/snug/queryImages">图片查询</a>
   
   <h1>=========标签基本功能==========</h1>
   <br>
   <a href="http://localhost:8080/snug/querySigList?department_id=001">查询全部标签</a><br>
   <a href="http://localhost:8080/snug/insertSigs?sigList=1,2,3&department_id=001&sig_type=1">添加标签（多个标签以，隔开）</a><br>
   <a href="http://localhost:8080/snug/updateSig?sig_name=微辣，好吃&sig_type=1&department_id=001&token=sig794a5e8b0">修改标签(token)</a><br>
  </div>
  </body>
</html>
