package com.gnet.utils;


import org.apache.cxf.endpoint.Client;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;

public class CxfUtil {

	public CxfUtil() {
	}

	public static String callService(String wsUrl, String method, Object arg[]) {
		JaxWsDynamicClientFactory dcf = JaxWsDynamicClientFactory.newInstance();
		Client client = dcf.createClient(wsUrl);

		Object res[] = (Object[]) null;
		try {
			res = client.invoke(method, arg);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return (String) res[0];
	}
}