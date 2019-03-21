<?php
	/*
	查询所有留言信息
	*/
	header("content-type:text/html;charset=utf-8");
	require_once "connect.php";
	$conn = new Db();
	$conn_db = $conn->connect();
	if (!$conn_db) {
		echo "数据库链接失败";
	}else{
		$select = "select * from connectus";
		$query = mysqli_query($conn_db,$select);
		$arr = array();
		while ($sel_query = mysqli_fetch_assoc($query)) {
			array_push($arr, $sel_query);
		}
		echo json_encode($arr);
	}

?>