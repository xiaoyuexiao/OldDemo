<?php
	/*
	查询所有新闻信息
	*/
	header("content-type:text/html;charset=utf-8");
	require_once "connect.php";
	$conn = new Db();
	$conn_db = $conn->connect();
	if (!$conn_db) {
	echo "数据库链接失败";
	}
	else{
		$select = "select * from news";
		$query = mysqli_query($conn_db,$select);
		$arr = array();
		while ($one_news = mysqli_fetch_assoc($query)) {
			array_push($arr,$one_news);
		}
		echo json_encode($arr);
	}
?>