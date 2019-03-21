package com.jcf.exceptionHandler;

import java.lang.reflect.UndeclaredThrowableException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import com.jcf.exception.ErrorPhoneLengthException;
import com.jcf.exception.NotSupportException;
//全局异常拦截
@Component
public class CustomHandlerExceptionResolver implements HandlerExceptionResolver {

	@Override
	public ModelAndView resolveException(HttpServletRequest request,
			HttpServletResponse respone, Object obj, Exception ex)
	{	
		if(ex instanceof NotSupportException)
		{
			 request.setAttribute("mes", "不支持此号码");
			 return new ModelAndView("notsupport");
		}
		if(ex instanceof UndeclaredThrowableException)
		{
			 return new ModelAndView("notsupport");
		}
		if(ex instanceof ErrorPhoneLengthException)
		{
			request.setAttribute("mes", "此号码长度错误");
			return new ModelAndView("notsupport");
		}
		if (ex instanceof EmptyResultDataAccessException)
		{
			request.setAttribute("mes", "蛮烦各位大佬不要乱添加数据！！！数据库数据错了！！");
			return new ModelAndView("notsupport");
		}
		return null;
	}
}
