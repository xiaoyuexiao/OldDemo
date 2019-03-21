package com.jcf.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.jcf.interfaces.CompanyActionInterfaces;
@Controller
public class CompanyActionController {
	@Autowired
	CompanyActionInterfaces companyActionInterfaces;
	//http://localhost:8080/snug/insertCompany?company_info_name=kairui&company_info_content=local zhong jiang &company_info_phone=1377789327&company_info_address=zhongjiang&company_info_images=1519801094163,1519801099691
	@ResponseBody 
	@RequestMapping(value="/insertCompany",produces="text/html;charset=UTF-8",method ={ RequestMethod.GET,RequestMethod.POST})
	public String insertCompany(String company_info_name,
			String company_info_content,
			String company_info_phone,
			String company_info_address,
			String company_info_images)
	{
			return companyActionInterfaces.insertCompany(company_info_name, company_info_content, company_info_phone, company_info_address, company_info_images);
	}
}
