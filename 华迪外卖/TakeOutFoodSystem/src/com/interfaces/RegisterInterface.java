/**
 * 
 */
package com.interfaces;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

/**
 * @author lee
 * @time:2018骞�6鏈�9鏃�
 * @fun:
 * @param:
 */
public interface RegisterInterface
{
	public abstract String registerUser(String user_phone,String user_pwd,String user_school);
	public abstract String registerShop(String shop_phone,String shop_pwd,String shop_name,String shop_address);
}
