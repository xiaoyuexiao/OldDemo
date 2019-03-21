<?php
	header("content-type:text/html;charset=utf-8");
	require_once 'connect.php';
	$conn = new Db();
	$conn_db = $conn->connect();
	if (!$conn_db) {
		echo '数据库链接失败';
	}
	else{
		$select = "select * from connectus";
		$query = mysqli_query($conn_db,$select);
		if ($query) {
			$arr = array();
			while ($arr_one = mysqli_fetch_assoc($query)) {
				array_push($arr, $arr_one);
			}
			echo json_encode($arr);
		}
	}
?>