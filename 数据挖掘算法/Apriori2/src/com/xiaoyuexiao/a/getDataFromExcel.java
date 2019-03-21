/**
 * 
 */
package com.xiaoyuexiao.a;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

/**
 * @author Administrator
 * @time:2018年6月3日
 * @fun:
 * @param:
 */
public class getDataFromExcel
{
	

	  //读取excel
	   public static HashMap<String,ArrayList<String>> getExcelContent() throws Exception
	   {
		   try
		   {
			   POIFSFileSystem fs=new POIFSFileSystem(new FileInputStream("C:/Users/Administrator/Desktop/lee.xls"));  
			   //得到Excel工作簿对象    
			   HSSFWorkbook wb = new HSSFWorkbook(fs); 
			   //得到Excel工作表对象    
			   HSSFSheet sheet = wb.getSheetAt(0);
			   //得到Excel工作表的行    
			   int lineNum = sheet.getLastRowNum();
			   HashMap<String, ArrayList<String>> map = new HashMap<String,ArrayList<String>>();
			   ArrayList<String> list3 = null;
			   String[] content = null;
			   for (int i = 1; i < lineNum; i++)
			   {
				   list3 = new ArrayList<String>();
				   HSSFRow row = sheet.getRow(i);
				   //得到Excel工作表指定行的单元格    
				   HSSFCell cell = row.getCell((short) 0);
				   HSSFRichTextString cellContent =  cell.getRichStringCellValue();
				   String string = cellContent.getString();
				   content = string.split("/");
				   
				   for (int j = 0;j<content.length;j++)
				   {
					int pos =content[j].indexOf(",");
					list3.add(content[j].substring(0,pos));
				   }
				   map.put(i+"",list3);
			   }
			   
			   return map;
		   }
		   catch (Exception e) 
		   {
			   e.printStackTrace();
			   return null;
		   }
		   
	   }
	   
	   public static void main(String[] args)
	   {
		   try
		   {
			   HashMap<String, ArrayList<String>> map = getDataFromExcel.getExcelContent();
			   System.out.println(map.toString());
		   } 
		   catch (Exception e) 
		   {
				e.printStackTrace();
		   }
	   }
	   
}
