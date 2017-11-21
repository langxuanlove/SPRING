package com.gnet.socket;


import java.io.IOException;
import java.util.List;

import javax.websocket.Session;

import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.gnet.utils.SessionMapUtil;
import com.google.common.eventbus.Subscribe;

public class MyHandler extends TextWebSocketHandler {
	private  String data;
	
	public MyHandler(String data) {
		super();
		this.data = data;
	}

	
	@Override
	public void handleTextMessage(WebSocketSession session, TextMessage message) throws IOException {
//简单返回收到的消息
		SessionMapUtil.put(session.getId(), session);
		System.out.println("客户端ID:"+session.getId());
		TextMessage message2=new TextMessage("success,"+data);
		System.out.println("Message:"+message);
		session.sendMessage(message2);
	}
	@Subscribe
    public void listen(String event) throws IOException {
		TextMessage  msg=new TextMessage(event);
		List<Object> list=SessionMapUtil.list;
		System.out.println("list大小："+list.size());
		for (int i = 0; i < list.size(); i++) {
			WebSocketSession session=(WebSocketSession)list.get(i);
			if(session.isOpen()){
				session.sendMessage(msg);
			}else{
				SessionMapUtil.remove(session);
			}
			System.out.println(session.isOpen());	
		}
        System.out.println("Message:"+event);
    }
}