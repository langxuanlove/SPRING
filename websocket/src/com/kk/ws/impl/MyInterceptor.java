package com.gnet.ws.impl;


import javax.servlet.http.HttpServletResponse;
import org.apache.cxf.binding.soap.SoapMessage;
import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.message.Message;
import org.apache.cxf.phase.AbstractPhaseInterceptor;
import org.apache.cxf.phase.Phase;
import org.apache.cxf.phase.PhaseInterceptorChain;
import org.apache.cxf.transport.http.AbstractHTTPDestination;
import org.springframework.stereotype.Component;

@Component(value="myInterceptor")
public class MyInterceptor extends AbstractPhaseInterceptor<Message> {

    public MyInterceptor() {
        super(Phase.PRE_INVOKE);  // 在调用方法之前调用自定拦截器
        
    }
    @SuppressWarnings("null")
    public void handleMessage(Message message) throws Fault {
    	 Message context = PhaseInterceptorChain.getCurrentMessage();
    	 HttpServletResponse response =  (HttpServletResponse) context
		         .get(AbstractHTTPDestination.HTTP_RESPONSE); 
    	 System.out.println("拦截器开始...");
		 response.setHeader("Access-Control-Allow-Origin", "http://192.168.1.16:8888");
		 response.setHeader("Access-Control-Allow-Methods", "GET, POST");
		 // 请求withCredentials为true时,则必须加载以下参数.
		 response.setHeader("Access-Control-Allow-Credentials", "true");
		 response.setHeader("Access-Control-Max-Age", "3600");
		 response.setHeader("Access-Control-Allow-Headers", "Access-Control-Allow-Origin, Access-Control-Allow-Methods, Access-Control-Max-Age, X-Auth-Token, Content-Type, Accept");
    }

}