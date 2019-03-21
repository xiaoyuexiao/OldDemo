<?php
	header("content-type:text/html;charset=utf-8");
	require_once 'connect.php';
	$conn = new Db();
	$con_db = $conn->connect();
	if (!$con_db) {
		echo "数据库连接失败！！！";
	}else{
		//接收新闻编号
		$innum = isset($_POST['inum'])?$_POST['inum']:'null';
		//条件查询newnum为innum的新闻
		$select = "select * from news where newsnum = $innum";
		//
		if ($innum!='null') {
			$query = mysqli_query($con_db,$select);
			if ($query) {
				$arr = mysqli_fetch_assoc($query);
				echo json_encode($arr);
			}
			else{
				echo '失败！！';
			}
		}
		
	}
?>