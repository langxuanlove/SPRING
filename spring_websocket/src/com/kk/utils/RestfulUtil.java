package com.gnet.utils;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import com.gnet.utils.HttpUtil;

public class RestfulUtil {

	public static String sendHttpRequest(String psUrl, String psJson)
			throws UnsupportedEncodingException, MalformedURLException {
		return HttpUtil.sendPostRequest(psUrl, "strJson=" + psJson, true);
	}
}
