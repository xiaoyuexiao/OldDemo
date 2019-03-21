package com.jcf.controller;

import javax.json.Json;

import org.junit.Ignore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;



@Controller
public class BasicInfoController {
	@Autowired
	JdbcTemplate jdbcTemplate;
	@Autowired
	DataSourceTransactionManager  txManager;
	//http://localhost:8080/snug/GetAllProvince
	
	@ResponseBody 
	@RequestMapping(value="/GetAllProvince",produces="text/html;charset=UTF-8",method ={ RequestMethod.GET,RequestMethod.POST})
	public String GetAllProvince() {
		try {
			org.json.JSONObject object=new org.json.JSONObject();
			object.put("status", "1");
			object.put("msg", "获取成功");
			object.put("info", "北京,上海,天津,重庆,河北,山西,内蒙古,辽宁,吉林,黑龙江,江苏,浙江,安徽,福建,江西,山东,河南,湖北,湖南,广东,广西,海南,四川,贵州,云南,西藏,陕西,甘肃,宁夏,青海,新疆,香港,澳门,台湾");
			
			return object.toString();
		} catch (Exception e) {
			e.printStackTrace();
		    return "";
		}
		
	}
	
	
	//http://localhost:8080/snug/GetAllCities?province=四川
	@ResponseBody 
	@RequestMapping(value="/GetAllCities",produces="text/html;charset=UTF-8",method ={ RequestMethod.GET,RequestMethod.POST})
	public String GetAllCities(String province) {
		try {
			province= new String(province.getBytes("iso8859-1"),"utf-8");
			org.json.JSONObject object=new org.json.JSONObject();
			object.put("status", "1");
			object.put("msg", "获取成功");
			if(province.equals("北京"))
			{
				object.put("info", "东城,西城,崇文,宣武,朝阳,丰台,石景山,海淀,门头沟,房山,通州,顺义,昌平,大兴,平谷,怀柔,密云,延庆");
			}
			if(province.equals("上海"))
			{
				object.put("info", "黄浦,卢湾,徐汇,长宁,静安,普陀,闸北,虹口,杨浦,闵行,宝山,嘉定,浦东,金山,松江,青浦,南汇,奉贤,崇明");
			}
			
			if(province.equals("天津"))
			{
				object.put("info", "和平,东丽,河东,西青,河西,津南,南开,北辰,河北,武清,红挢,塘沽,汉沽,大港,宁河,静海,宝坻,蓟县");
			}
			
			
			if(province.equals("重庆"))
			{
				object.put("info", "万州,涪陵,渝中,大渡口,江北,沙坪坝,九龙坡,南岸,北碚,万盛,双挢,渝北,巴南,黔江,长寿,綦江,潼南,铜梁,大足,荣昌,壁山,梁平,城口,丰都,垫江,武隆,忠县,开县,云阳,奉节,巫山,巫溪,石柱,秀山,酉阳,彭水,江津,合川,永川,南川");
			}
			
			if(province.equals("河北"))
			{
				object.put("info", "石家庄,邯郸,邢台,保定,张家口,承德,廊坊,唐山,秦皇岛,沧州,衡水");
			}
			
			
			if(province.equals("山西"))
			{
				object.put("info", "太原,大同,阳泉,长治,晋城,朔州,吕梁,忻州,晋中,临汾,运城");
			}
			
			
			if(province.equals("内蒙古"))
			{
				object.put("info", "呼和浩特,包头,乌海,赤峰,呼伦贝尔盟,阿拉善盟,哲里木盟,兴安盟,乌兰察布盟,锡林郭勒盟,巴彦淖尔盟,伊克昭盟");
			}
			
			if(province.equals("辽宁"))
			{
				object.put("info", "沈阳,大连,鞍山,抚顺,本溪,丹东,锦州,营口,阜新,辽阳,盘锦,铁岭,朝阳,葫芦岛");
			}
			
			if(province.equals("吉林"))
			{
				object.put("info", "长春,吉林,四平,辽源,通化,白山,松原,白城,延边");
			}
			
			if(province.equals("黑龙江"))
			{
				object.put("info", "哈尔滨,齐齐哈尔,牡丹江,佳木斯,大庆,绥化,鹤岗,鸡西,黑河,双鸭山,伊春,七台河,大兴安岭");
			}
			
			if(province.equals("江苏"))
			{
				object.put("info", "南京,镇江,苏州,南通,扬州,盐城,徐州,连云港,常州,无锡,宿迁,泰州,淮安");
			}
			
			if(province.equals("浙江"))
			{
				object.put("info", "杭州,宁波,温州,嘉兴,湖州,绍兴,金华,衢州,舟山,台州,丽水");
			}
			
			if(province.equals("安徽"))
			{
				object.put("info", "合肥,芜湖,蚌埠,马鞍山,淮北,铜陵,安庆,黄山,滁州,宿州,池州,淮南,巢湖,阜阳,六安,宣城,亳州");
			}
			
			if(province.equals("福建"))
			{
				object.put("info", "福州,厦门,莆田,三明,泉州,漳州,南平,龙岩,宁德");
			}
			
			if(province.equals("江西"))
			{
				object.put("info", "南昌市,景德镇,九江,鹰潭,萍乡,新馀,赣州,吉安,宜春,抚州,上饶");
			}
			
			
			if(province.equals("山东"))
			{
				object.put("info", "济南,青岛,淄博,枣庄,东营,烟台,潍坊,济宁,泰安,威海,日照,莱芜,临沂,德州,聊城,滨州,菏泽");
			}
			
			
			if(province.equals("河南"))
			{
				object.put("info", "郑州,开封,洛阳,平顶山,安阳,鹤壁,新乡,焦作,濮阳,许昌,漯河,三门峡,南阳,商丘,信阳,周口,驻马店,济源");
			}
			
			if(province.equals("湖北"))
			{
				object.put("info", "武汉,宜昌,荆州,襄樊,黄石,荆门,黄冈,十堰,恩施,潜江,天门,仙桃,随州,咸宁,孝感,鄂州");
			}
			
			if(province.equals("湖南"))
			{
				object.put("info", "长沙,常德,株洲,湘潭,衡阳,岳阳,邵阳,益阳,娄底,怀化,郴州,永州,湘西,张家界");
			}
			
			if(province.equals("广东"))
			{
				object.put("info", "广州,深圳,珠海,汕头,东莞,中山,佛山,韶关,江门,湛江,茂名,肇庆,惠州,梅州,汕尾,河源,阳江,清远,潮州,揭阳,云浮");
			}
			if(province.equals("广西"))
			{
				object.put("info", "南宁,柳州,桂林,梧州,北海,防城港,钦州,贵港,玉林,南宁地区,柳州地区,贺州,百色,河池");
			}
			
			if(province.equals("海南"))
			{
				object.put("info", "海口,三亚");
			}
			
			
			if(province.equals("四川"))
			{
				object.put("info", "成都,绵阳,德阳,自贡,攀枝花,广元,内江,乐山,南充,宜宾,广安,达川,雅安,眉山,甘孜,凉山,泸州");
			}
			
			if(province.equals("贵州"))
			{
				object.put("info", "贵阳,六盘水,遵义,安顺,铜仁,黔西南,毕节,黔东南,黔南");
			}
			
			if(province.equals("云南"))
			{
				object.put("info", "昆明,大理,曲靖,玉溪,昭通,楚雄,红河,文山,思茅,西双版纳,保山,德宏,丽江,怒江,迪庆,临沧");
			}
			
			if(province.equals("西藏"))
			{
				object.put("info", "拉萨,日喀则,山南,林芝,昌都,阿里,那曲");
			}
			
			if(province.equals("陕西"))
			{
				object.put("info", "西安,宝鸡,咸阳,铜川,渭南,延安,榆林,汉中,安康,商洛");
			}
			
			if(province.equals("甘肃"))
			{
				object.put("info", "兰州,嘉峪关,金昌,白银,天水,酒泉,张掖,武威,定西,陇南,平凉,庆阳,临夏,甘南");
			}
			
			if(province.equals("宁夏"))
			{
				object.put("info", "银川,石嘴山,吴忠,固原");
			}
			
			if(province.equals("新疆"))
			{
				object.put("info", "乌鲁木齐,石河子,克拉玛依,伊犁,巴音郭勒,昌吉,克孜勒苏柯尔克孜,博 尔塔拉,吐鲁番,哈密,喀什,和田,阿克苏");
			}
			
			if(province.equals("青海"))
			{
				object.put("info", "西宁,海东,海南,海北,黄南,玉树,果洛,海西");
			}
			
			if(province.equals("香港"))
			{
				object.put("info", "香港");
			}
			
			if(province.equals("澳门"))
			{
				object.put("info", "澳门");
			}
			
			if(province.equals("台湾"))
			{
				object.put("info", "台北,高雄,台中,台南,屏东,南投,云林,新竹,彰化,苗栗,嘉义,花莲,桃园,宜兰,基隆,台东,金门,马祖,澎湖");
			}
			
			
			return object.toString();
		} catch (Exception e) {
			e.printStackTrace();
		    return "";
		}
		
	}
	
	
	
}
