package com.jcf.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;
import java.util.Map.Entry;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;

/**
 * @author Administrator
 *
 */
/**
 * @author Administrator
 *
 */
/**
 * @author Administrator
 *
 */
public class MyUtil
{
	@Autowired
	JdbcTemplate jdbcTemplate;
    
	public static MessageConsumer messageConsumer;
	
	public static  ConnectionFactory connectionFactory;
	public static  Connection connection1 = null;
	public static Session session;
	public static Destination destination;
	public static MessageProducer producer;
   
    
	
	public static  String ecodeByMD5(String originstr)
	{
			String result = null;
			char hexDigits[] = {
			'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'}; 
			if(originstr != null){try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] source = originstr.getBytes("utf-8");
			 md.update(source);
			byte[] tmp = md.digest();
			char[] str = new char[32];
			for(int i=0,j=0; i < 16; i++){
			byte b = tmp[i];
			str[j++] = hexDigits[b>>>4 & 0xf];
			str[j++] = hexDigits[b&0xf];
			}
			result = new String(str);
			} catch (NoSuchAlgorithmException e) 
			{
			      e.printStackTrace();
			} catch (UnsupportedEncodingException e)
			{
			    e.printStackTrace();
			}
	     }
	       return result;
	}
	
	
	
	
    	
	
	

	public static String GetToken()
	{
		String s = UUID.randomUUID().toString(); 
        String s2= s.replace("-",""); 
        return s2;
	}
	
	 public static String GetRandomFour()
	   {
			Random random=new Random();
			StringBuilder sBuilder=new StringBuilder();
	    	for(int index=0;index<4;index++)
	    	{
	    		sBuilder.append(random.nextInt(10));
	    	}
		   return sBuilder.toString();
	   }
	
	 
	 public static String GetRandomSix()
	   {
			Random random=new Random();
			StringBuilder sBuilder=new StringBuilder();
	    	for(int index=0;index<6;index++)
	    	{
	    		sBuilder.append(random.nextInt(10));
	    	}
		   return sBuilder.toString();
	   }
	
	 

	public static String GetRandomUserName()
	{
	    StringBuilder sBuilder=new StringBuilder();
	    sBuilder.append("BG");
	    sBuilder.append(GetPhoneSnumber2(5));
		return sBuilder.toString();
	}
	
	 public static String GetDateTime()
     {
    	 SimpleDateFormat formatter; 
    	 formatter = new SimpleDateFormat ("yyyy-MM-dd HH:mm"); 
    	 String ctime = formatter.format(new Date()); 
    	 return ctime;
     }
	 
	 public static String GetTime()
     {
    	 SimpleDateFormat formatter; 
    	 formatter = new SimpleDateFormat ("HH:mm:ss"); 
    	 String ctime = formatter.format(new Date()); 
    	 return ctime;
     }
	 
	 public static String GetDate()
     {
    	 SimpleDateFormat formatter; 
    	 formatter = new SimpleDateFormat ("yyyy-MM-dd"); 
    	 String ctime = formatter.format(new Date()); 
    	 return ctime;
     }
     public static String GetPhoneNumber()
     {
    	 Random random=new Random();
    	 StringBuilder sBuilder=new StringBuilder();
    	 sBuilder.append("133");
    	 for(int index=0;index<8;index++)
    	 {
    		 sBuilder.append(random.nextInt(10)); 
    	 }
    	 return sBuilder.toString();
     }
     
     //HashMap ----> JSON
     public static String hashMapToJson(HashMap map)
     {         
    	 String string = "{";          
    	 for (Iterator it = map.entrySet().iterator(); it.hasNext();) 
    	 {             
    		 Entry e = (Entry) it.next();
    		 string += "'" + e.getKey() + "':";
    		 string += "'" + e.getValue() + "',";          
    	 }          
    	 string = string.substring(0, string.lastIndexOf(","));
    	 string += "}";
    	 return string;      
     }  
     
     
     public  static  String decodeutf8(String strinput)
     {
    	 StringBuffer output = new StringBuffer();
         for (int i = 0; i < strinput.length(); i++)
         {
             output.append("\\u" +Integer.toString(strinput.charAt(i), 16));
         }        
         return output.toString();
     }
     
     public static  String decode(String unicodeStr) {  
		    if (unicodeStr == null) {  
		        return null;  
		    }  
		   StringBuffer retBuf = new StringBuffer();  
		    int maxLoop = unicodeStr.length();  
	    for (int i = 0; i < maxLoop; i++) {  
		        if (unicodeStr.charAt(i) == '\\') {  
		            if ((i < maxLoop - 5)  
		                    && ((unicodeStr.charAt(i + 1) == 'u') || (unicodeStr  
	                            .charAt(i + 1) == 'U')))  
		                try {  
		                    retBuf.append((char) Integer.parseInt(  
		                            unicodeStr.substring(i + 2, i + 6), 16));  
		                    i += 5;  
		                } catch (NumberFormatException localNumberFormatException) {  
		                   retBuf.append(unicodeStr.charAt(i));  
		                }  
		            else  
		                retBuf.append(unicodeStr.charAt(i));  
		        } else {  
		            retBuf.append(unicodeStr.charAt(i));  
		        }  
		    }  
		    return retBuf.toString();  
		}  
     
     public static String GetPhoneSnumber()
     {
    	 Random random=new Random();
    	 StringBuilder sBuilder=new StringBuilder();
    	 for(int index=0;index<6;index++)
    	 {
    		 sBuilder.append(random.nextInt(10)); 
    	 }
    	 return sBuilder.toString();
     }
     
     public static String GetPhoneSnumber2(int top)
     {
    	 Random random=new Random();
    	 StringBuilder sBuilder=new StringBuilder();
    	 for(int index=0;index<top;index++)
    	 {
    		 sBuilder.append(random.nextInt(10)); 
    	 }
    	 return sBuilder.toString();
     }
     
     public static String AddPushMessage(String MSG_TO,String OBJECT_NO,String SUMMARY,String MSG_TEXT)
     {
    	try 
    	{
    		
    		 
    		 
		   return "";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
     }	
     
     /*
      * @author Lee
      * @time 2018/1/13
      * @需要引入两个jar包commons-fileupload-1.3.jar 和 commons-io-2.4.jar
      * @图片上传
      * */
     public static String Upimg(CommonsMultipartFile file){
    	 	String newfilename = null;
	        try {
	        		newfilename = new Date().getTime()+file.getOriginalFilename();
	        		//图片存储路径
	        		String path = "../webapps/snug/view/images/";
	        		//获取输出流
		            OutputStream os=new FileOutputStream(path + newfilename);
		            //获取输入流 CommonsMultipartFile 中可以直接得到文件的流
		            InputStream is=file.getInputStream();
		            int temp;
		            //一个一个字节的读取并写入
		            while((temp=is.read())!=(-1))
		            {
		                os.write(temp);
		            }
		            os.flush();
			        os.close();
			        is.close();
	           
	        }
	        catch (Exception e) {
	            e.printStackTrace();
	        }
	        return newfilename; 
     }
     
     /*
      * @author Lee
      * @time 2018/1/23
      * @需要引入两个jar包commons-fileupload-1.3.jar 和 commons-io-2.4.jar
      * @图片上传
      * */
     public static String Upimgs(CommonsMultipartFile[] file,String token,JdbcTemplate jdbcTemplate){
	        
    	 long size;
    	 String[] types={"png","jpg","jpeg","gif","bpm"};
    	 
	    	if (jdbcTemplate!=null){
	    			
		        	for (int i = 0; i < file.length; i++) {
			        		try {
			        			//得到文件原名称
			        		    String newfilename = new Date().getTime()+file[i].getOriginalFilename();
			        		    //得到文件大小（字节）
			        		    size = file[i].getSize();
			        		    //得到文件后缀名
			        		    String prefix = file[i].getOriginalFilename().substring(file[i].getOriginalFilename().lastIndexOf(".")+1);
			        		   
								if (types[0].equals(prefix)||types[1].equals(prefix)||types[2].equals(prefix)||types[3].equals(prefix)||types[4].equals(prefix))
								{
									
								}
								else
								{
									return "type_error";
								}
			        		    
			        		    if(size > 0 && size < 1024*1000 )
			        		    {
			        		    	//图片存储路径
					        		String path = "../webapps/snug/view/images/";
				        			//获取输出流
						            OutputStream os=new FileOutputStream(path + newfilename);
						            //获取输入流 CommonsMultipartFile 中可以直接得到文件的流
						            InputStream is=file[i].getInputStream();
						            int temp;
						            //一个一个字节的读取并写入
						            while((temp=is.read())!=(-1))
						            {
						                os.write(temp);
						            }
						            os.flush();
							        os.close();
							        is.close();
			    					String sqlString = "insert into snug_images_info(image_info_name,image_info_token_ref) values(?,?)";
			    					String prxString = token.substring(0,token.length()-9);
			    					String imgnumsql = "select count(*) as num from snug_images_info where image_info_token_ref like '"+prxString+"%'";
			    					int imgnum = jdbcTemplate.queryForInt(imgnumsql);
			    					jdbcTemplate.update(sqlString,new Object[]{newfilename,token + (imgnum + 1)});
			        		    }
			        		    else
			        		    {
			        		    	if (size > 1024*1000) {
			        		    		return "big";
									}
									
								}
			        		} catch (Exception e) {
					            e.printStackTrace();
					        }
		    		}
		        	return "success";
	        	
			}
	    	else
			{
				return DBHelper.FailConnectDatabase();
			}
	           
     }
     
     /*
      * @author Lee
      * @time 2018/1/20
      * @文件下载
      * */
     public static String downloadQrCode(String filename,HttpServletResponse response){
    	 try {
	    	 //得到系统的绝对路径
	    	 String path = System.getProperty("user.dir");//返回的是tomcat下的bin目录
	    	 //得到下载文件的绝对路径
	    	 String realpath = path.substring(0,path.lastIndexOf("\\")) + "\\webapps\\snug\\view\\qrcode\\" + filename + ".png";
	    	 //创建一个File文件,得到要下载的文件
	    	 File file = new File(realpath);
	    	 if (!file.exists()) {
				return "文件不存在";
			 }
	    	 //创建输入流，将文件放入流中
	    	 InputStream iStream = new FileInputStream(file);
	    	 //清空response对象
	    	 response.reset();
	    	 //设置响应头内容
	    	 response.addHeader("Content-Disposition", "attachment;filename=" + new String((filename +".png").getBytes()));
		     response.addHeader("Content-Length", "" + file.length());
		     response.setContentType("application/octet-stream");
		     //通过response对象的到输出流
		     OutputStream outputStream = response.getOutputStream();
		     //创建一个字节数组用于存放从输入流中读取的字节
		     byte[] buffer = new byte[1024];
		     //将输入流中的字节读入到字节数组中
		     int n ;
			 while((n=iStream.read(buffer))!= -1){
				outputStream.write(buffer, 0, n);
			 }
			 outputStream.close();
			 iStream.close();
    	 }
    	 catch (Exception e) {
    		 e.printStackTrace();
    		 return "文件下载失败！！";
		 }
		return "";
     }
     /*
      * 添加标签
      * params:SigString:标签字符串，以','号隔开。
      * 	   SigType:标签类型（热菜，冷菜。。。。。）
      * 	   jdbcTemplate：数据库操作对象
      * */
     public static String addSigs(String SigString,String SigType,JdbcTemplate jdbcTemplate,String department_id)
     {
    	//String st = SigType.substring(SigType.lastIndexOf("-"),SigType.length());
    	 
		String[] sigarr = SigString.split(",");
		 
		for (int i = 0; i < sigarr.length; i++) {
			String token = "sig" + MyUtil.GetToken().substring(0, 9);
			String insertSig = "insert into snug_sig_info(" +
					"sig_name," +
					"sig_type," +
					"sig_flag," +
					"department_id," +
					"sig_token) values(?,?,?,?,?)";
			jdbcTemplate.update(insertSig, new Object[]{sigarr[i],SigType,"1",department_id,token});
		}
		
		JSONObject object = new JSONObject();
		try {
			object.put("status", "1");
			object.put("mes", "success");
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return object.toString();
     }
     /*
      * 
      * 将集合list<Map<String,Object>>转化为jsonarray
      * */
		public static JSONArray getJsonArray(List<Map<String, Object>> list)
		{
			JSONArray array = new JSONArray();
			
			for (Map<String, Object> map : list) {
				JSONObject object = new JSONObject();
				for (Map.Entry<String, Object> entry : map.entrySet()) {
					try {
						object.put(entry.getKey(), entry.getValue());
					} catch (JSONException e) {
						e.printStackTrace();
					}
				}
				array.add(object);
			}
			return array;
		}
		/*
	      * 
	      * 将Map转化成jsonobject
	      * */
			public static JSONObject getJsonObject(Map<String, Object> str)
			{
				JSONObject object = new JSONObject();
				for (Map.Entry<String, Object> entry : str.entrySet()) {
					object.put(entry.getKey(), entry.getValue());
				}
				
				return object;
			}
			
		/*
		 * 过滤特殊数字
		 * 
		 * */	
			public static ArrayList<Integer> getlist(String table_last_num,String table_fiter_num){
				//得到编号范围
				String[] munarr = table_last_num.split(",");
				int firstnum = Integer.parseInt(munarr[0]);
				int lastnum = Integer.parseInt(munarr[(munarr.length)-1]);
				//得到过滤数字
				String[] fitermunarr = table_fiter_num.split(",");
				//存放所有可用编号
				ArrayList<Integer> after_num_arr = new ArrayList<Integer>();
				
				for (Integer i = firstnum; i <= lastnum; i++)
				{
					 String prenum = i.toString();
					 ArrayList<String> prearr = new ArrayList<String>();
					 for (int j = 0; j < prenum.length(); j++)
					 {
						 prearr.add(prenum.substring(j,j+1));
					 }
					 
					 boolean flag = true;
					 for (int j = 0; j < prearr.size(); j++)
					 {
						for (int j2 = 0; j2 < fitermunarr.length; j2++) {
							if (fitermunarr[j2].equals(prearr.get(j)))
							{
								flag=false;
								break;
							}
						}
					 }
					 
				 	if (flag==false)
					{
						
					}
					if(flag==true)
					{
						after_num_arr.add(i);
						System.out.println(i);
					}
				}
				return after_num_arr;
			}	
		
		/*
		 * 
		 * 
		 * */
//			public static String upimgBybase64(String filename){
//				Random random=new Random();
//				int ra=random.nextInt(9999);
//				
//				String path = System.getProperty("user.dir");
//				String realpath = path.substring(0,path.lastIndexOf("\\")) + "\\webapps\\snug\\view\\images\\" + ra + filename +".png";
//				File desFile = new File(realpath);
//				
//				FileOutputStream  fos = null;
//				try {
//					BASE64Decoder decode = new BASE64Decoder();
//			        byte [] b = decode.decodeBuffer(imgs);
//				    fos = new FileOutputStream(desFile);
//				    fos.write(b);
//				    fos.close();
//				} catch (Exception e) {
//				    e.printStackTrace();
//				}
//
//			}
			
		/*
		 * 为某对象添加图片s
		 * */	
		public static String addImgs(String image_names,String to_object_token,JdbcTemplate jdbcTemplate)
		{
			System.out.println("image_names:"+image_names);
			String[] images = null;
			if (image_names.length()>0)
			{
				images = image_names.split(",");
			}
			String sqlString = "insert into snug_images_info(image_info_name,image_info_token_ref) values(?,?)";
			
			for (int i = 0; i < images.length; i++)
			{
				String queryimgs = "select * from snug_total_image where total_image_name = ?";
				List<Map<String, Object>> list = jdbcTemplate.queryForList(queryimgs,images[i]);
				if (list.size() > 0) 
				{
					jdbcTemplate.update(sqlString, new Object[]{images[i],to_object_token});
				}
				else
				{
					return "error";
				}
			}
			return "success";
		}
}
