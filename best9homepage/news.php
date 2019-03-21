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
	$title = isset($_POST['newstitle'])?$_POST['newstitle']:'';
	$time = isset($_POST['newstime'])?$_POST['newstime']:'';
	$message = isset($_POST['richtext1'])?$_POST['richtext1']:'null';
	$introduce = isset($_POST['newsintroduce'])?$_POST['newsintroduce']:'';
	$newsnum= isset($_POST['newsnum'])?$_POST['newsnum']:'';
	//插入操作
	$insert = "INSERT INTO `news`(`title`, `content`,`newsdate`,`introduce`,`newsnum`) VALUES ('$title','$message','$time','$introduce','$newsnum')";

	if ($title!= '' && $time!='' && $message!='') {
		$insquery = mysqli_query($con_stu,$insert);
		if ($insquery==true) {
			echo true;
		}
		else{
			echo false;
		}
	}else{
		echo false;
	}
?>