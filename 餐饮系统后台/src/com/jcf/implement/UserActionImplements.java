package com.jcf.implement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.dbcp.BasicDataSource;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jcf.bean.UserBean;
import com.jcf.interfaces.UserActionInterfaces;
import com.jcf.util.MyUtil;
import com.jcf.util.SendFile;

@Service
public class UserActionImplements implements UserActionInterfaces {

	@Autowired
	BasicDataSource dataSource;
	
	@Autowired
	UserBean userBean;
	
	@Override
	public String verifycode(String phone) {
		try {
			String num=MyUtil.GetRandomFour();
			SendFile.sendSnumber(phone, num);
			Connection connection=null;
			if(dataSource!=null)
			{
				connection = dataSource.getConnection();
			}
			else
			{
				JSONObject object=new JSONObject();
				object.put("msg","数据库连接失败");
				object.put("status","0");
				return object.toString();
			}
				if(connection!=null)
				   {
					  String str="insert into snug_snumbers_info(snumbers_info_phone,snumbers_info_snumber,snumbers_info_flag,snumbers_info_time) values(?,?,?,?)";
					      PreparedStatement preparedStatement=(PreparedStatement)connection.prepareStatement(str);
					      preparedStatement.setString(1, phone);
					      preparedStatement.setString(2, num);
					      preparedStatement.setString(3,"1");
					      preparedStatement.setString(4,MyUtil.GetDateTime());
					      if(!preparedStatement.execute())
					      {
					    	  JSONObject object=new JSONObject();
							  object.put("msg", "发送验证码成功");
							  object.put("status", "1");
							  return object.toString();
					      }
					      else
					      {
					    	  JSONObject object=new JSONObject();
							  object.put("msg", "发送验证码失败");
							  object.put("status", "0");
							  return object.toString();
					      }
					   
				   }
				   else {
					    JSONObject object=new JSONObject();
						  object.put("msg", "发送验证码失败");
						  object.put("status", "0");
						  return object.toString();
				   }
		   
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "发送成功！！";
	}

	@Override
	public String register(String phone, String snum, String pwd, String img) {
		String encodePWD=MyUtil.ecodeByMD5(pwd);
		//String userid="";
		//String channelid="";
        Connection conn = null;
      /*  File desFile = new File("../webapps/crpweb/view/"+phone+"_icon.png");
		FileOutputStream  fos = null;
		try {
			BASE64Decoder decode = new BASE64Decoder();
	        byte [] b = decode.decodeBuffer(img);
		    fos = new FileOutputStream(desFile);
		    fos.write(b);
		    fos.close();
		} catch (Exception e) {
		    e.printStackTrace();
		}*/
		
		try {
			if(dataSource!=null)
			{
				conn = dataSource.getConnection();
			}
			else
			{
				JSONObject object=new JSONObject();
				object.put("msg","数据库连接失败");
				object.put("status","0");
				return object.toString();
			}
			
		} catch (Exception e1) {
			e1.printStackTrace();
		}
        
        try 
        {
        	if(conn!=null)
    		{	
        	    conn.setAutoCommit(true);
        		String SELECTUSER="SELECT admin_info_phone FROM snug_admin_info WHERE admin_info_phone = ?";
				PreparedStatement pSELECTUSER=conn.prepareStatement(SELECTUSER);
				pSELECTUSER.setString(1, phone);//PHONE
				ResultSet rpSELECTUSER=pSELECTUSER.executeQuery();
				if(rpSELECTUSER.next())
				{
					//已经被注册过
					rpSELECTUSER.close();
					rpSELECTUSER=null;
					
					    JSONObject object=new JSONObject();
						object.put("msg","该手机已经注册过");
						object.put("status","0");
						System.out.println(object.toString());
						return object.toString();
				}
				else
				{
					rpSELECTUSER.close();
					rpSELECTUSER=null;
					//T_SNUMBERS  t_snumbers
					String SELECTSNUMBER="SELECT * FROM snug_snumbers_info WHERE snumbers_info_phone = ? AND snumbers_info_snumber = ? AND snumbers_info_flag ='1'";
	    			PreparedStatement pSELECTSNUMBER=conn.prepareStatement(SELECTSNUMBER);
	    			pSELECTSNUMBER.setString(1,phone);
	    			pSELECTSNUMBER.setString(2,snum);//SNUMBER
	    			ResultSet rpSELECTSNUMBER=pSELECTSNUMBER.executeQuery();
	    		
	    				if(rpSELECTSNUMBER.next())
	        			{
	    					
							    String datetime=rpSELECTSNUMBER.getString("snumbers_info_time");
						
							    SimpleDateFormat myFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
						        long day = 0;
						        
						         java.util.Date date = myFormatter.parse(datetime);
						         java.util.Date mydate = new java.util.Date();
						    
						         day = (mydate.getTime()- date.getTime()) / 1000;
						        
						         if(day > 60*100)
						         {
						        		ConcurrentHashMap<String,String> message=new ConcurrentHashMap<String,String>();
										message.put("usercode","");
										message.put("token","");
										message.put("type","");
										JSONObject result=new JSONObject();
										result.put("status", "5");
										result.put("msg", "验证码过期");	
										result.put("info", message);
										System.out.println(result.toString());
										return result.toString();
						         }
						         else 
						         {
						        	 
						        	    String usernames=MyUtil.GetRandomUserName();
						        		String ADDUSER="INSERT INTO snug_admin_info(admin_info_name,admin_info_phone,admin_info_pwd,admin_info_img) VALUES(?,?,?,?)";
						        		//Random random=new Random();
						        		//int index=random.nextInt(6);
						        		PreparedStatement pADDUSER=conn.prepareStatement(ADDUSER);
						        		
						        		pADDUSER.setString(1,usernames); 
						        		pADDUSER.setString(2,phone);
						        		pADDUSER.setString(3,encodePWD);
						        		pADDUSER.setString(4,img);
						        		
						        		
						        		///////////////////////////////////////////
//						        		pADDUSER.setString(1,phone); //手机号码
//						        		pADDUSER.setString(2,"phone");   //注册方法
//						        		pADDUSER.setString(3,MyUtil.GetDate());//日期
//						        		pADDUSER.setString(4,MyUtil.GetTime());//时间
//						        		pADDUSER.setString(5,"1");//状态
//						        		pADDUSER.setString(6,encodePWD);//密码
//			        					pADDUSER.setString(7, phone);
//			        					pADDUSER.setString(8,"1");//等级
//			        					pADDUSER.setInt(9,500); //积分
//			        					pADDUSER.setString(10,"01"); 
//			        					pADDUSER.setString(11,usernames); 
//			        					pADDUSER.setString(12,channelid); 
//			        					pADDUSER.setString(13,userid); 
//			        					pADDUSER.setString(14,"0"); //0 不接收  1 接收
//			        					pADDUSER.setString(15,phone);
//			        					pADDUSER.setString(16,"0");
//			        					pADDUSER.setString(17,"");
//			        					pADDUSER.setString(18,"");
//			        					pADDUSER.setString(19,"");
//			        					pADDUSER.setString(20,"");
//			        					pADDUSER.setString(21,"1");//0 女  1 男
//			        					pADDUSER.setString(22,"");
//			        					pADDUSER.setString(23,ConstantValue.img_icons[index]);
//			        					System.out.println("img:"+ConstantValue.img_icons[index]);
			        					if(!pADDUSER.execute())
			        				    {
			        				    	try 
			        				    	{
													//修改标示符FLAG
													String UPDATESNUMBER="UPDATE snug_snumbers_info SET snumbers_info_flag='0' WHERE snumbers_info_phone = ? AND snumbers_info_snumber = ?";
									    			PreparedStatement pUPDATESNUMBER=conn.prepareStatement(UPDATESNUMBER);
									    			pUPDATESNUMBER.setString(1,phone);//PHONE
									    			pUPDATESNUMBER.setString(2,snum);//SNUMBER
									    			pUPDATESNUMBER.execute();
									    			ConcurrentHashMap<String,String> message=new ConcurrentHashMap<String,String>();
					        						message.put("usercode",usernames);
					        						message.put("nickname",phone);
					        				    	message.put("token",phone);
					        				    	message.put("type","phone");
					        				    	message.put("phone",phone);
					        				    	JSONObject result=new JSONObject();
													result.put("status", "1");
													result.put("msg", "注册成功");	
													result.put("info", message);
													System.out.println(result.toString());
													return result.toString();
									    	
											} catch (Exception e) {
												e.printStackTrace();
											}
										
			        				    }
			        				    else
			        				    {
			        				    	ConcurrentHashMap<String,String> message=new ConcurrentHashMap<String,String>();
											message.put("usercode","");
											message.put("token","");
											message.put("nickname","");
											message.put("type","");
											message.put("phone","");
											JSONObject result=new JSONObject();
											result.put("status", "0");
											result.put("msg", "注册失败");	
											result.put("info", message);
											System.out.println(result.toString());
											return result.toString();
			        				    }
						        	   
								 }
	        				
	        			}
	    				else
	    				{
	    					ConcurrentHashMap<String,String> message=new ConcurrentHashMap<String,String>();
							message.put("usercode","");
							message.put("token","");
							message.put("type","");
							JSONObject result=new JSONObject();
							result.put("status", "6");
							result.put("msg", "验证码错误");	
							result.put("info", message);
							System.out.println(result.toString());
							return result.toString();
	    				}
				}
    		}
		}
        catch (Exception e) {
			e.printStackTrace();
			
		}
        System.out.println("error");
		return "";
	}

	@Override
	public String login(String phone, String pwd) {
		 String pwdMD5=MyUtil.ecodeByMD5(pwd);
		 System.out.println("======pwdMD5"+pwdMD5+"======");
		 Connection connection=null;
		 if(dataSource!=null)
		 {
			 try {
				connection=dataSource.getConnection();
			} catch (Exception e) {
				e.printStackTrace();
			}
		 }
		 else
		 {
			 try {
				 JSONObject object=new JSONObject();
					object.put("msg","数据库连接失败");
					object.put("status","0");
					return object.toString();
			} catch (Exception e) {
				e.printStackTrace();
			}
			   
		 }
		    
		   String username="";
		   String nickname="";
//		   String HOBBY="";
//		   String NEED="";
					if(connection!=null)
					{

						try
						{
							String SELECTLOG="SELECT admin_info_phone FROM snug_admin_info WHERE admin_info_phone=? ";
							PreparedStatement pSELECTLOG=connection.prepareStatement(SELECTLOG);
							pSELECTLOG.setString(1, phone);
							ResultSet rSELECTLOG=pSELECTLOG.executeQuery();
							if(rSELECTLOG.next())
							{
							
								String token=rSELECTLOG.getString("admin_info_phone");
								String SELECTUSER="SELECT * FROM snug_admin_info WHERE admin_info_phone=?";
								PreparedStatement pSELECTUSER=connection.prepareStatement(SELECTUSER);
								pSELECTUSER.setString(1,token);
								ResultSet rpSELECTUSER=pSELECTUSER.executeQuery();
								if(rpSELECTUSER.next()) 
								{
								
									String USR_PW=rpSELECTUSER.getString("admin_info_pwd");
									nickname=rpSELECTUSER.getString("admin_info_name");
									//HOBBY=rpSELECTUSER.getString("HOBBY");
									//NEED=rpSELECTUSER.getString("NEED");
									if(!USR_PW.equals(pwdMD5))
									{
										ConcurrentHashMap<String,String> message=new ConcurrentHashMap<String,String>();
										message.put("type","");
										message.put("token","");
										message.put("username","");
//										message.put("nickname","");
//										message.put("HOBBY","");
//										message.put("NEED","");
										JSONObject result=new JSONObject

();
										result.put("status", "2");
										result.put("msg", "密码错误");	
										result.put("info", message);
										return result.toString();
									}
									if(USR_PW.equals(pwdMD5))
									{
									
										ConcurrentHashMap<String,String> message=new ConcurrentHashMap<String,String>();
										message.put("type","");
										message.put("token",token);
										message.put("username",username);
										message.put("nickname",nickname);
										message.put("phone",phone);
										//message.put("HOBBY",HOBBY);
										//message.put("NEED",NEED);
										
//										message.put("phone",phone);
//										message.put("ZFB",rpSELECTUSER.getString("ZFB"));
//										message.put("WX",rpSELECTUSER.getString("WX"));
//										message.put("BANK",rpSELECTUSER.getString("BANK"));
//										message.put("AGE",rpSELECTUSER.getString("AGE"));
//										message.put("MARRIAGE",rpSELECTUSER.getString("MARRIAGE"));
//										message.put("OCCUPATION",rpSELECTUSER.getString("OCCUPATION"));
//										message.put("USR_INTEGRATION",rpSELECTUSER.getString("USR_INTEGRATION"));
//										
//										message.put("QUAN",rpSELECTUSER.getString("QUAN"));
//										message.put("ACCEPT_MSG",rpSELECTUSER.getString("ACCEPT_MSG"));
//										message.put("USR_LEVEL",rpSELECTUSER.getString("USR_LEVEL"));
										
										
										
										JSONObject result=new JSONObject();
										result.put("status", "1");
										result.put("msg", "登录成功");	
										result.put("info", message);
										System.out.println(result.toString());
										return result.toString();
											
									}
								}
								else {
									ConcurrentHashMap<String,String> message=new ConcurrentHashMap<String,String>();
									message.put("type","");
									message.put("token","");
									message.put("username","");
									message.put("nickname","");
									message.put("phone","");
//									message.put("HOBBY","");
//									message.put("NEED","");
									JSONObject result=new JSONObject();
									result.put("status", "0");
									result.put("msg", "没有这个用户");	
									result.put("info", message);
									return result.toString();
								}
						 }
							else
							{
								ConcurrentHashMap<String,String> message=new ConcurrentHashMap<String,String>();
								message.put("type","");
								message.put("token","");
								message.put("username","");
								message.put("nickname","");
								message.put("phone","");
								message.put("HOBBY","");
								message.put("NEED","");
								JSONObject result=new JSONObject();
								result.put("status", "0");
								result.put("msg", "没有这个用户");	
								result.put("info", message);
								return result.toString();
							}
						}
							catch (Exception e) {
						  e.printStackTrace();
						}

					}
		return "fail";
	}

	@Override
	public String changepwd(String phone, String snum, String pwd) {
		String encodePWD=MyUtil.ecodeByMD5(pwd);
        Connection conn = null;
		try {
			if(dataSource!=null)
			{
				conn = dataSource.getConnection();
			}
			else
			{
				JSONObject object=new JSONObject();
				object.put("msg","数据库连接失败");
				object.put("status","0");
				return object.toString();
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		}
        try 
        {
        	if(conn!=null)
    		{	
        	    conn.setAutoCommit(true);
        		String SELECTUSER="SELECT admin_info_phone FROM snug_admin_info WHERE admin_info_phone = ?";
				PreparedStatement pSELECTUSER=conn.prepareStatement(SELECTUSER);
				pSELECTUSER.setString(1, phone);//PHONE
				ResultSet rpSELECTUSER=pSELECTUSER.executeQuery();
				if(!rpSELECTUSER.next())
				{
					rpSELECTUSER.close();
					rpSELECTUSER=null;
					
					    JSONObject object=new JSONObject();
						object.put("msg","没有该用户");
						object.put("status","2");
						return object.toString();
				}
				else
				{
					rpSELECTUSER.close();
					rpSELECTUSER=null;
					
					String SELECTSNUMBER="SELECT * FROM snug_snumbers_info WHERE snumbers_info_phone = ? AND snumbers_info_snumber = ? AND snumbers_info_flag='1'";
	    			PreparedStatement pSELECTSNUMBER=conn.prepareStatement(SELECTSNUMBER);
	    			pSELECTSNUMBER.setString(1,phone);
	    			pSELECTSNUMBER.setString(2,snum);
	    			ResultSet rpSELECTSNUMBER=pSELECTSNUMBER.executeQuery();
	    		
	    				if(rpSELECTSNUMBER.next())
	        			{
	    					
							    String datetime=rpSELECTSNUMBER.getString("snumbers_info_time");
						
							    SimpleDateFormat myFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
						        long day = 0;
						         java.util.Date date = myFormatter.parse(datetime);
						         java.util.Date mydate = new java.util.Date();
						    
						         day = (mydate.getTime()- date.getTime()) / 1000;
						        
						         if(day > 60*100)
						         {
						        		ConcurrentHashMap<String,String> message=new ConcurrentHashMap<String,String>();
										message.put("usercode","");
										message.put("token","");
										message.put("type","");
										JSONObject result=new JSONObject();
										result.put("status", "0");
										result.put("msg", "验证码过期");	
										result.put("info", message);
										return result.toString();
						         }
						         else 
						         {
						        	 
						        	    String usernames=MyUtil.GetRandomUserName();
						        		String UPDATES="update snug_admin_info set admin_info_pwd = ? where admin_info_phone=?";
						        		
						        		PreparedStatement pADDUSER=conn.prepareStatement(UPDATES);
						        		pADDUSER.setString(1,encodePWD); 
						        		pADDUSER.setString(2,phone);   
			        					if(!pADDUSER.execute())
			        				    {
			        				    	try 
			        				    	{

													//修改标示符FLAG
			        				    			String UPDATESNUMBER="UPDATE snug_snumbers_info SET snumbers_info_flag='0' WHERE snumbers_info_phone = ? AND snumbers_info_snumber = ?";
									    			PreparedStatement pUPDATESNUMBER=conn.prepareStatement(UPDATESNUMBER);
									    			pUPDATESNUMBER.setString(1,phone);//PHONE
									    			pUPDATESNUMBER.setString(2,snum);//SNUMBER
									    			pUPDATESNUMBER.execute();
									    		
									    			ConcurrentHashMap<String,String> message=new ConcurrentHashMap<String,String>();
					        						message.put("usercode",usernames);
					        						message.put("nickname",phone);
					        				    	message.put("token",phone);
					        				    	message.put("type","phone");
					        				    	message.put("phone",phone);
					        				    	JSONObject result=new JSONObject();
													result.put("status", "1");
													result.put("msg", "修改成功");	
													result.put("info", message);
													return result.toString();
									    	
											} catch (Exception e) {
												e.printStackTrace();
											}
										
			        				    }
			        				    else
			        				    {
			        				    	ConcurrentHashMap<String,String> message=new ConcurrentHashMap<String,String>();
											message.put("usercode","");
											message.put("token","");
											message.put("nickname","");
											message.put("type","");
											message.put("phone","");
											JSONObject result=new JSONObject();
											result.put("status", "0");
											result.put("msg", "修改失败");	
											result.put("info", message);
											return result.toString();
			        				    }
						        	   
								 }
	        				
	        			}
	    				else
	    				{
	    					ConcurrentHashMap<String,String> message=new ConcurrentHashMap<String,String>();
							message.put("usercode","");
							message.put("token","");
							message.put("type","");
							JSONObject result=new JSONObject();
							result.put("status", "0");
							result.put("msg", "验证码错误");	
							result.put("info", message);
							return result.toString();
	    				}
	    			
				}
    		}
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		return "fail";
	}

}
