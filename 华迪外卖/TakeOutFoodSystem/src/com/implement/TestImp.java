/**
 * 
 */
package com.implement;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.interfaces.TestInterface;
import com.util.MyUtil;

/**
 * @author Administrator
 * @time:May 22, 2018
 * @fun:
 * @param:
 */
@Service
public class TestImp implements TestInterface{

	/* (non-Javadoc)
	 * @see com.interfaces.Test#showTest()
	 */
	public String showTest(CommonsMultipartFile file) {
		System.out.println("==============");
		return MyUtil.Upimg(file);
	}
}
