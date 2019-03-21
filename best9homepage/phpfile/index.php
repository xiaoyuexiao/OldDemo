<?php
	header("Content-type:text/html;charset=utf-8");
	//连接数据库
	include 'connect.php';
	$conDb = new Db();
	$con_stu = $conDb->connect();
	if (!$con_stu) {
	 	echo "数据库连接失败";
	}else{
		$email = isset($_POST['email'])?$_POST['email']:'';
		$mes = isset($_POST['text'])?$_POST['text']:'';
		$time = isset($_POST['date'])?$_POST['date']:'';
		if ($email!='' && $mes!='' && $time!='') {
			$insert = "INSERT INTO `connectus`(`email`, `message`,`mesdate`) VALUES ('$email','$mes','$time')";
			$query = mysqli_query($con_stu,$insert);
			if ($query) {
				echo '留言成功！';
			}else{
				echo '留言失败！';
			}
		}else{
			echo '插入失败';
		}
	}

?>