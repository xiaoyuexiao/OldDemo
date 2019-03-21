package com.jcf.interfaces;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

public interface ImgActionInterfaces {
	public abstract String upImg(CommonsMultipartFile[] file,String total_image_type,String department_id);
	public abstract String deleteImg(String image_name);
	public abstract String queryImages();
}