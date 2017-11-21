package com.api;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.junit.Test;
import com.google.common.base.Charsets;

public class BaiduApi {

	/**
	 * 
	 * xingzuo:(星座运势测试). <br/>
	 *
	 * @author Jikey
	 */
//	@Test
	public void xingzuo(){
		String httpUrl = "http://apis.baidu.com/bbtapi/constellation/constellation_query";
		String httpArg = "consName=白羊座&type=today";
		String jsonResult = requestGET(httpUrl, httpArg);
		System.out.println(jsonResult);
	}

	@Test
	public void urlShort(){
		String httpUrl = "http://apis.baidu.com/chazhao/shorturl/shorturl";
		String httpArg = "{"+
		    "\"type\": 1,"+ 
		    "\"url\": ["+ 
		        "\"http://tech.sina.com.cn/it/2016-12-14/doc-ifxypipu8052502.shtml\""+
//		        ",\"http://news.sina.com.cn\""+
		    "]"+
		"}";
		System.out.println(httpArg);
		String jsonResult = requestPost(httpUrl, httpArg);
		System.out.println(jsonResult);
	}
	/**
	 * 星座运势接口
	 * 
	 * @param urlAll
	 *            :请求接口
	 * @param httpArg
	 *            :参数
	 * @return 返回结果
	 */
	public static String requestGET(String httpUrl, String httpArg) {
	    BufferedReader reader = null;
	    String result = null;
	    StringBuffer sbf = new StringBuffer();
	    httpUrl = httpUrl + "?" + httpArg;

	    try {
	        URL url = new URL(httpUrl);
	        HttpURLConnection connection = (HttpURLConnection) url
	                .openConnection();
	        connection.setRequestMethod("GET");
	        // 填入apikey到HTTP header
	        connection.setRequestProperty("apikey",  "ec20fc56aa5aae7c64226e75bbc48f25");
	        connection.connect();
	        InputStream is = connection.getInputStream();
	        reader = new BufferedReader(new InputStreamReader(is, Charsets.UTF_8));
	        String strRead = null;
	        while ((strRead = reader.readLine()) != null) {
	            sbf.append(strRead);
	            sbf.append("\r\n");
	        }
	        reader.close();
	        result = sbf.toString();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return result;
	}
	
	
	/**
	 * @param urlAll
	 *            :请求接口
	 * @param httpArg
	 *            :参数
	 * @return 返回结果
	 */
	public static String requestPost(String httpUrl, String httpArg) {
	    BufferedReader reader = null;
	    String result = null;
	    StringBuffer sbf = new StringBuffer();

	    try {
	        URL url = new URL(httpUrl);
	        HttpURLConnection connection = (HttpURLConnection) url
	                .openConnection();
	        connection.setRequestMethod("POST");
	        connection.setRequestProperty("Content-Type", "application/json;charset=utf-8");
	        // 填入apikey到HTTP header
	        connection.setRequestProperty("apikey",  "ec20fc56aa5aae7c64226e75bbc48f25");
	        connection.setDoOutput(true);
	        connection.getOutputStream().write(httpArg.getBytes("UTF-8"));
	        connection.connect();
	        InputStream is = connection.getInputStream();
	        reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
	        String strRead = null;
	        while ((strRead = reader.readLine()) != null) {
	            sbf.append(strRead);
	            sbf.append("\r\n");
	        }
	        reader.close();
	        result = sbf.toString();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return result;
	}

}
