package com.jcf.util;

public class regxUtil {
    public static boolean isPhoneNumber(String phone)
    {
    	return phone.matches("^((13[0-9])|(14[5|7])|(15([0-3]|[5-9]))|(18[0-9]))\\d{8}$");
    }
}
