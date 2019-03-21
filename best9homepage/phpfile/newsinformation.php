<?php
	header("content-type:text/html;charset=utf-8");
	//连接数据库
	require_once 'connect.php';
	$conn = new Db();
	$con_db = $conn->connect();
	if (!$con_db) {
		echo '数据库连接失败';
	}
	//查询新闻
	$select = "select newsnum,introduce from news order by newsnum asc";
	$query = mysqli_query($con_db,$select);
	if ($query) {
		
		$newarr = array();
		while ($selarr = mysqli_fetch_assoc($query)) {
			$se = $selarr;
			array_push($newarr, $se);
		}
		echo json_encode($newarr);
	}

?>