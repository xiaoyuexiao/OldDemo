package com.jcf.util;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.CoreConnectionPNames;

public class SendFile {
	 public static void sendSnumber(String phone,String msg)
	   {
		   try 
		   {
			   
				   HttpPost post = new HttpPost("http://api.jisuapi.com/sms/send");
			       HttpClient client = new DefaultHttpClient();
			       client.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 60000); 
			       client.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, 60000);
			       
			        List<NameValuePair> nvps = new ArrayList<NameValuePair>();
					nvps.add(new BasicNameValuePair("mobile",phone));
					nvps.add(new BasicNameValuePair("content","你的动态验证码是 "+msg+"【鑫集品公司】"));
					nvps.add(new BasicNameValuePair("appkey","c64e1b61fe2c0c41"));
				    InputStream is = null;
					String result = null;
			       try
			       {
			    	   post.setEntity(new UrlEncodedFormEntity(nvps,"utf-8"));
			       	   HttpResponse response = client.execute(post);
			           int statecode = response.getStatusLine().getStatusCode();
			           if(statecode == 200){
			        	   HttpEntity httpentity = response.getEntity(); 
			 	           String strentity = null;
			 	            if (httpentity != null){
			 	            	is = httpentity.getContent();
			 	            	byte[] b = new byte[1024]; 
			 	            	int length = 0;
			 	            	StringBuilder sb = new StringBuilder();
			 	            	while((length=is.read(b))!= -1){
			 	            		//sb.append(new String(b,0,length));
									//�������룬����������������
									sb.append(new String(b,0,length,"utf-8"));
			 	            	}
			 	            	strentity = sb.toString();
			 	            	result = strentity;
			 	            	System.out.println(result);
			 	            }
			           }
			       }
			       catch(Exception e)
			       {
			    	   e.printStackTrace();
			       }
			           
			} 
			catch (Exception e) 
			{
				e.printStackTrace();
			}
	   }
	 
	
	   
}
