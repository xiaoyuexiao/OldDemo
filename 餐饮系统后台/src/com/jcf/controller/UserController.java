package com.jcf.controller;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jcf.implement.UserActionImplements;
import com.jcf.util.MemcachedUtils;

@Controller
public class UserController {
	@Autowired
	BasicDataSource dataSource;
	@Autowired
	JdbcTemplate jdbcTemplate;
	@Autowired
	UserActionImplements userActionImplements;
	@Autowired
	MemcachedUtils memcachedUtils;

	 
    // http://localhost:8080/snug/changepwd?phone=15700218161&snum=4033&pwd=330236184
	@ResponseBody 
	@RequestMapping(value="/changepwd",produces="text/html;charset=UTF-8",method = {RequestMethod.GET,RequestMethod.POST})
	public String chnagepwd(String phone,String snum,String pwd) {
		return userActionImplements.changepwd(phone, snum, pwd);
	}

	//http://localhost:8080/snug/verifycode?phone=15700218161
	@ResponseBody 
	@RequestMapping(value="/verifycode",produces="text/html;charset=UTF-8",method = {RequestMethod.GET,RequestMethod.POST})
	public String verifycode(String phone) {
		return userActionImplements.verifycode(phone);
	}
	//http://localhost:8080/snug/login?phone=15700218161&pwd=794594866
	@ResponseBody 
	@RequestMapping(value="/login",produces="text/html;charset=UTF-8",method = {RequestMethod.GET,RequestMethod.POST})
	public String login(String phone,String pwd) {
		return userActionImplements.login(phone, pwd);
	}
	//http://localhost:8080/snug/register?phone=15700218161&snum=2407&pwd=794594866&img=123
	@ResponseBody 
	@RequestMapping(value="/register",produces="text/html;charset=UTF-8",method ={RequestMethod.GET,RequestMethod.POST})
	public String register(String phone,String snum,String pwd,String img) {
		return userActionImplements.register(phone, snum, pwd, img);
	}
}
