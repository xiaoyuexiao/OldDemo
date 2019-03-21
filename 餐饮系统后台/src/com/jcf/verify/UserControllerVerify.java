package com.jcf.verify;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import com.jcf.exception.ErrorPhoneLengthException;
import com.jcf.exception.NotSupportException;
//@Aspect指定为切面程序，@Component将类注入到spring中
@Aspect
@Component 
public class UserControllerVerify {
	//@Before表示 在某个方法执行之前执行
	@Before("execution(public * com.jcf.controller.UserController.verifycode(..))")
	public void CheckPhoneToVerify(JoinPoint joinPoint) throws Throwable
	{
		String str = joinPoint.getArgs()[0].toString();
		if(!str.startsWith("183"))
		{
			throw new NotSupportException("不支持此号段号码");
		}
	}
	//电话号码长度异常拦截
	@Before("execution(public * com.jcf.controller.UserController.verifycode(..))")
	public void CheckPhoneLength(JoinPoint joinPoint)
	{
		int phone = joinPoint.getArgs()[0].toString().length();
		if(phone!=11)
		{
			throw new ErrorPhoneLengthException("号码长度错误");
		}
	}
}