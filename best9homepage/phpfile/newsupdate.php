<?php
	header("Content-type:text/html;charset=utf-8");
	//连接数据库
	include 'connect.php';
	$conDb = new Db();
	$con_stu = $conDb->connect();
	if (!$con_stu) {
	 	echo "数据库连接失败";
	}
	//接收数据
	$ti = isset($_POST['newstitle'])?$_POST['newstitle']:'';
	$te = isset($_POST['newstime'])?$_POST['newstime']:'';
	$mes = isset($_POST['richtext1'])?$_POST['richtext1']:'';
	$ice = isset($_POST['newsintroduce'])?$_POST['newsintroduce']:'';
	$nm= isset($_POST['newsnum'])?$_POST['newsnum']:'';

	//查询新闻编号
	$select = "select newsnum from news where newsnum = '$nm'";
	$querysel = mysqli_query($con_stu,$select);
	if ($querysel) {
	 	$arr = mysqli_fetch_array($querysel);
	 	if ($arr[0]==$nm) {
		 		//更新操作
		 	$update = "UPDATE news set title = '$ti',newsnum = '$nm',content = '$mes',introduce = '$ice',newsdate = '$te' where newsnum='$nm'";
		 	if ($ti!= '' && $te!='' && $mes!='') {
		 		$insquery = mysqli_query($con_stu,$update);
		 		if ($insquery==true) {
		 			echo true;
		 		}
		 		else{
		 			echo false;
		 		}
		 	}else{
		 		echo false;
		 	}
	 	}
	 } 
	
?>