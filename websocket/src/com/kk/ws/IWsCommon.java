package com.gnet.ws;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.xml.ws.BindingType;
import javax.xml.ws.soap.SOAPBinding;


@BindingType(value = SOAPBinding.SOAP12HTTP_BINDING)
@WebService(name = "ICommonService",targetNamespace="http://webService.gnet.cn.com/")
public interface IWsCommon {
	@Context
	@WebMethod(operationName="check", action="http://webService.gnet.cn.com/check")
	@GET
	@Path("/check/")
	@Produces({ MediaType.TEXT_PLAIN })
	@HeaderParam(value="")
	public String check(@WebParam(name = "strJson") @FormParam(value = "strJson") String psJson);
	
	
}
