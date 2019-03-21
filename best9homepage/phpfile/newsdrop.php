<?php
header("content-type:text/html;charset=utf-8");
require_once 'connect.php';
$conn = new Db();
$con_db = $conn->connect();
if (!$con_db) {
	echo "数据库连接失败！！！";
}else{
	//接收新闻编号
	$innum = isset($_POST['newnum'])?$_POST['newnum']:'null';
	//条件查询newnum为innum的新闻
	$delete = "delete from news where newsnum = $innum";
	//
	if ($innum!='null') {
		$query = mysqli_query($con_db,$delete);
		if ($query) {
			echo true;
		}
		else{
			echo false;
		}
	}	
}
?>