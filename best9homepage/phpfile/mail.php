<?php
require '/PHPMailer/PHPMailerAutoload.php';
//获取发送的邮箱地址
$email_address = $_POST['replyer'];
$email_title = $_POST['replytitle'];
$email_message = $_POST['message'];
//实例化
$mail = new PHPMailer;
//$mail->SMTPDebug = 3;                               // Enable verbose debug output
$mail->isSMTP();                                      // Set mailer to use SMTP
$mail->Host = 'smtp.qq.com';  // Specify main and backup SMTP servers
$mail->SMTPAuth = true;
$mail->CharSet = 'UTF-8';                               // Enable SMTP authentication
$mail->Username = '794594866@qq.com';                 // SMTP username
$mail->Password = 'ynwvckjqrqhrbfbc';                           // SMTP password
//$mail->SMTPSecure = 'tls';                            // Enable TLS encryption, `ssl` also accepted
//$mail->Port = 587;                                    // TCP port to connect to

$mail->setFrom('794594866@qq.com', 'lee');
$mail->addAddress($email_address, 'xiao');     // Add a recipient
//$mail->addAddress('ellen@example.com');               // Name is optional
$mail->addReplyTo('794594866@qq.com', 'Information');
// $mail->addCC('cc@example.com');
// $mail->addBCC('bcc@example.com');

// $mail->addAttachment('/var/tmp/file.tar.gz');         // Add attachments
// $mail->addAttachment('/tmp/image.jpg', 'new.jpg');    // Optional name
$mail->isHTML(true);                                  // Set email format to HTML

$mail->Subject = $email_title;
$mail->Body    = '<b>'.$email_message.'</b>';
// $mail->AltBody = 'This is the body in plain text for non-HTML mail clients';

if(!$mail->send()) {
    echo 'Message could not be sent.';
    echo 'Mailer Error: ' . $mail->ErrorInfo;
} else {
    echo '回复成功！<button onclick="window.history.back()">返回</button>';
}
?>