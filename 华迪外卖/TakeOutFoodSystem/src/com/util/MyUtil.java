package com.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.DriverManager;
import java.sql.SQLException;
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
import javax.jms.JMSException;
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
      * @闇�瑕佸紩鍏ヤ袱涓猨ar鍖卌ommons-fileupload-1.3.jar 鍜� commons-io-2.4.jar
      * @鍥剧墖涓婁紶
      * */
     public static String Upimg(CommonsMultipartFile file){
    	 	String newfilename = null;
	        try {
	        		newfilename = new Date().getTime()+file.getOriginalFilename();
	        		String path = "../view/img/";
	        		//String path = "C:/Users/Administrator/Desktop/img";
	        		InputStream in=file.getInputStream();
		            OutputStream out=new FileOutputStream(path + newfilename);
		            System.out.println(file.getSize());
//		            int temp;
//		            while((temp=is.read())!=(-1))
//		            {
//		                os.write(temp);
//		            }
//		            os.flush();
//			        os.close();
//			        is.close();
	           
			         byte[] buffer = new byte[1024 * 1024];   
	                 int length;   
	                 while ((length = in.read(buffer)) > 0) {   
	                     out.write(buffer, 0, length);   
	                 }
	                 in.close();   
	                 out.close();   
	        }
	        catch (Exception e) {
	            e.printStackTrace();
	        }
	        return newfilename;
     }
     
     /*
      * @author Lee
      * @time 2018/1/23
      * @闇�瑕佸紩鍏ヤ袱涓猨ar鍖卌ommons-fileupload-1.3.jar 鍜� commons-io-2.4.jar
      * @鍥剧墖涓婁紶
      * */
     public static String Upimgs(CommonsMultipartFile[] file,String token,JdbcTemplate jdbcTemplate){
	        
    	 long size;
    	 String[] types={"png","jpg","jpeg","gif","bpm"};
    	 
	    	if (jdbcTemplate!=null){
	    			
		        	for (int i = 0; i < file.length; i++)
		        	{
			        		try {
			        			
			        		    String newfilename = new Date().getTime()+file[i].getOriginalFilename();
			        		    
			        		    size = file[i].getSize();
			        		    
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
			        		    	//上传位置
					        		String path = "../WebContent/view/img/";
				        			//输出流
						            OutputStream os=new FileOutputStream(path + newfilename);
						            //输入流
						            InputStream is=file[i].getInputStream();
						            int temp;
						            while((temp=is.read())!=(-1))
						            {
						                os.write(temp);
						            }
						            os.flush();
							        os.close();
							        is.close();
			    					String sqlString = "insert into images(imageurl,imagetoken) values(?,?)";
			    					jdbcTemplate.update(sqlString,new Object[]{newfilename,token});
			        		    }
			        		    else
			        		    {
			        		    	if (size > 1024*1000)
			        		    	{
			        		    		return "big";
									}
									
								}
			        		} 
			        		catch (Exception e)
			        		{
					            e.printStackTrace();
					        }
		    		}
		        	return "success";
			}
	    	else
			{
				return "fail";
			}
	           
     }
     
     /*
      * @author Lee
      * @time 2018/1/20
      * @鏂囦欢涓嬭浇
      * */
     public static String downloadQrCode(String filename,HttpServletResponse response){
    	 try {
	    	 //寰楀埌绯荤粺鐨勭粷瀵硅矾寰�
	    	 String path = System.getProperty("user.dir");//杩斿洖鐨勬槸tomcat涓嬬殑bin鐩綍
	    	 //寰楀埌涓嬭浇鏂囦欢鐨勭粷瀵硅矾寰�
	    	 String realpath = path.substring(0,path.lastIndexOf("\\")) + "\\webapps\\snug\\view\\qrcode\\" + filename + ".png";
	    	 //鍒涘缓涓�涓狥ile鏂囦欢,寰楀埌瑕佷笅杞界殑鏂囦欢
	    	 File file = new File(realpath);
	    	 if (!file.exists()) {
				return "鏂囦欢涓嶅瓨鍦�";
			 }
	    	 //鍒涘缓杈撳叆娴侊紝灏嗘枃浠舵斁鍏ユ祦涓�
	    	 InputStream iStream = new FileInputStream(file);
	    	 //娓呯┖response瀵硅薄
	    	 response.reset();
	    	 //璁剧疆鍝嶅簲澶村唴瀹�
	    	 response.addHeader("Content-Disposition", "attachment;filename=" + new String((filename +".png").getBytes()));
		     response.addHeader("Content-Length", "" + file.length());
		     response.setContentType("application/octet-stream");
		     //閫氳繃response瀵硅薄鐨勫埌杈撳嚭娴�
		     OutputStream outputStream = response.getOutputStream();
		     //鍒涘缓涓�涓瓧鑺傛暟缁勭敤浜庡瓨鏀句粠杈撳叆娴佷腑璇诲彇鐨勫瓧鑺�
		     byte[] buffer = new byte[1024];
		     //灏嗚緭鍏ユ祦涓殑瀛楄妭璇诲叆鍒板瓧鑺傛暟缁勪腑
		     int n ;
			 while((n=iStream.read(buffer))!= -1){
				outputStream.write(buffer, 0, n);
			 }
			 outputStream.close();
			 iStream.close();
    	 }
    	 catch (Exception e) {
    		 e.printStackTrace();
    		 return "鏂囦欢涓嬭浇澶辫触锛侊紒";
		 }
		return "";
     }
     /*
      * 娣诲姞鏍囩
      * params:SigString:鏍囩瀛楃涓诧紝浠�','鍙烽殧寮�銆�
      * 	   SigType:鏍囩绫诲瀷锛堢儹鑿滐紝鍐疯彍銆傘�傘�傘�傘�傦級
      * 	   jdbcTemplate锛氭暟鎹簱鎿嶄綔瀵硅薄
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
      * 灏嗛泦鍚坙ist<Map<String,Object>>杞寲涓簀sonarray
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
	      * 灏哅ap杞寲鎴恓sonobject
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
		 * 杩囨护鐗规畩鏁板瓧
		 * 
		 * */	
			public static ArrayList<Integer> getlist(String table_last_num,String table_fiter_num){
				//寰楀埌缂栧彿鑼冨洿
				String[] munarr = table_last_num.split(",");
				int firstnum = Integer.parseInt(munarr[0]);
				int lastnum = Integer.parseInt(munarr[(munarr.length)-1]);
				//寰楀埌杩囨护鏁板瓧
				String[] fitermunarr = table_fiter_num.split(",");
				//瀛樻斁鎵�鏈夊彲鐢ㄧ紪鍙�
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
		 * 涓烘煇瀵硅薄娣诲姞鍥剧墖s
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
		
		
		public Connection getConnect() throws Exception{

		        Connection conn = null;
		        // MySQL的JDBC连接语句
		        // URL编写格式：jdbc:mysql://主机名称：连接端口/数据库的名称?参数=值
		        String url = "jdbc:mysql://localhost:3306/outfood?user=root&password=1234";
		        
		        try {
		            Class.forName("com.mysql.jdbc.Driver"); // 加载驱动
		            conn = (Connection) DriverManager.getConnection(url); // 获取数据库连接
		            return conn;
		        } catch (ClassNotFoundException e) {
		            System.out.println("加载驱动异常");
		            e.printStackTrace();
		        } catch (SQLException e) {
		            System.out.println("数据库异常");
		            e.printStackTrace();
		        } finally {
		                if (conn != null)
		                    conn.close(); // 关闭数据库连接
		            }
					return conn; 
		        }
		
}
