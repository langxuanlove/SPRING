package com.gnet.ws.impl;
 
import javax.annotation.Resource;
import javax.jws.WebService;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.core.Context;
import javax.xml.ws.BindingType;
import javax.xml.ws.WebServiceContext;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.soap.SOAPBinding;
import org.apache.cxf.annotations.UseAsyncMethod;
import org.apache.cxf.message.Message;
import org.apache.cxf.phase.PhaseInterceptorChain;
import org.apache.cxf.transport.http.AbstractHTTPDestination;
import org.springframework.stereotype.Component;

import com.gnet.module.display.controller.DisplayController;
import com.gnet.module.test.service.ITestService;
import com.gnet.ws.IWsCommon;

@BindingType(value = SOAPBinding.SOAP12HTTP_BINDING)
@Component(value = "ibusWsCommon")
@WebService(serviceName = "Gnet_WebService", portName = "commonServicePort", endpointInterface = "com.gnet.ws.IWsCommon", targetNamespace = "http://webService.gnet.cn.com/")
public class WsCommonImpl implements IWsCommon {

//	@Resource(name = "testService")
//	private ITestService testService;
	@Resource(name = "displayController")
	private DisplayController displayController;
	
	@Override
	@UseAsyncMethod
	public String check(String psJson) {
		System.out.println("参数："+psJson);
		return  displayController.check(psJson);
		
	}
}
