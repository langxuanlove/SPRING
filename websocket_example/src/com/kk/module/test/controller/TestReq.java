package com.gnet.module.test.controller;

import com.alibaba.fastjson.JSONObject;
import com.gnet.utils.CxfUtil;
import com.gnet.utils.HttpUtil;


public class TestReq {
public static void main1(String[] args)throws Exception {
	String url="http://localhost:8080/Big_Screen/page/displayController/getClubRank";
//	Map<String, Object> map=new HashMap<String, Object>();
//	map.put("clubName", "广州");
//	map.put("pageIndex", "0");
//	map.put("pageSize", "50");
//	String res=HttpRequestor.getInstance().doPost(url, map);
//	System.out.println(res);
	System.out.println(HttpUtil.sendPostRequest(url, "clubName=广州&pageIndex=0&pageSize=50", true));
//	System.out.println(HttpUtil.sendGetRequest(url+ "?clubName=广州&pageIndex=0&pageSize=50", null));
}
public static String getCuJuResult(String str){
	JSONObject json=JSONObject.parseObject(str);
	return JSONObject.parseObject(json.getString("Data")).getString("table0");
} 
public static void main(String[] args) throws Exception {
	String url="http://114.112.89.100:6007/LargeScreenNews.asmx?wsdl";
	// 获取所有的推荐新闻
//	String res=CxfUtil.callService(url, "GetRecommendNews", new Object[]{});
//	System.out.println(res);
	// 获取新闻详细信息
//	String res=CxfUtil.callService(url, "GetNewsDetails", new Object[]{"3271a65e-ecc9-4281-9025-780024a8cc11"});
//	System.out.println(getCuJuResult(res));
	// 获取评论
	String res=CxfUtil.callService(url, "GetNewsComments", new Object[]{"a63278a1-7d73-4d77-a87d-f8417fde726c",1,10});
	System.out.println(getCuJuResult(res));
}

}
