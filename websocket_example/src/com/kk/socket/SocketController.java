package com.gnet.socket;


import java.io.IOException;
import java.util.List;

import javax.servlet.ServletContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.alibaba.fastjson.JSON;
import com.gnet.module.display.dao.IDisplayDao;
import com.gnet.utils.SessionMapUtil;
import com.gnet.utils.SpringConfigTool;
import com.google.common.eventbus.Subscribe;

public class SocketController extends TextWebSocketHandler {
	private Logger logger=LoggerFactory.getLogger(getClass());
	private  String data;
	
	public SocketController(String data) {
		super();
		this.data = data;
	}
	public SocketController() {
		
	}
	@Override
	public void handleTextMessage(WebSocketSession session, TextMessage message) throws IOException {
		//简单返回收到的消息
		SessionMapUtil.put(session.getId(), session);
		logger.info("客户端ID:{}",session.getId());
		IDisplayDao displayDao=(IDisplayDao) SpringConfigTool.getBean("displayDao");
		String data=displayDao.selectPageInfo();
		String msg="{\"flag_type\":\"0\",\"msg_body\":"+data+"}";
		logger.info("selectPageInfo方法返回的数据:{}",msg);
		TextMessage msgData=new TextMessage(msg);
		session.sendMessage(msgData);
	}
	@Subscribe
    public void listen(String event) throws IOException {
		TextMessage  msg=new TextMessage(event);
		List<Object> list=SessionMapUtil.list;
		logger.info("list大小：{}",list.size());
		for (int i = 0; i < list.size(); i++) {
			WebSocketSession session=(WebSocketSession)list.get(i);
			if(session.isOpen()){
				session.sendMessage(msg);
			}else{
				SessionMapUtil.remove(session);
			}
			System.out.println(session.isOpen());	
		}
       logger.info("Message:{}",event);
    }
}