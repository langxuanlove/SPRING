package com.keytec.activemq.topic;

import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import javax.jms.DeliveryMode;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.Session;
import javax.jms.Topic;
import javax.jms.TopicConnection;
import javax.jms.TopicConnectionFactory;
import javax.jms.TopicPublisher;
import javax.jms.TopicSession;
import javax.jms.TopicSubscriber;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.keytec.activemq.model.TopicMessage;
import com.keytec.activemq.utils.VerifyUtil;
import com.rop.annotation.IgnoreSignType;
import com.rop.annotation.NeedInSessionType;
import com.rop.annotation.ServiceMethod;
import com.rop.annotation.ServiceMethodBean;

/**
 * 版本控制webserice
 * @author 倪成伟
 * @version 1.0
 */
@ServiceMethodBean(version="1.0")
public class MessageWebService {
	private static Logger logger = LoggerFactory.getLogger(MessageWebService.class);
	// ConnectionFactory ：连接工厂，JMS 用它创建连接
	private static	TopicConnectionFactory  connectionFactory = null;
    // Connection ：JMS 客户端到JMS Provider 的连接
	private static TopicConnection  connection = null;
    // Session： 一个发送或接收消息的线程
	private static TopicSession  session =null;
	
	private static ConcurrentHashMap<String, Boolean> messageStatus = new ConcurrentHashMap<String, Boolean>();
	
	private static Timer timer = null;
	/**
	 * 发送消息
	 * @author ncw
	 */
	@ServiceMethod(method = "messsage.send",version = "1.0",needInSession = NeedInSessionType.NO,ignoreSign=IgnoreSignType.YES)
	public Object addVersions(TopicMessage topicMessage){
		//参数验证
    	Object result=VerifyUtil.verifyThis(topicMessage,"topic","message");
    	if(result!=null)
    		 return result;
    	
    	Map<String,Object> map=new HashMap<String,Object>();
    	
	        connectionFactory = new ActiveMQConnectionFactory(
	        		"admin",
	        		"admin",
	                "tcp://114.112.90.40:61616");
	        try {
	            // 构造从工厂得到连接对象
	            connection = connectionFactory.createTopicConnection();
	           // connection.setClientID("mmm");
	            // 启动
	            connection.start();
	            // 获取操作连接
	            session = connection.createTopicSession(Boolean.FALSE,
	                    Session.AUTO_ACKNOWLEDGE);
	            // 获取session注意参数值xingbo.xu-queue是一个服务器的queue，须在在ActiveMq的console配置
	            //destination = session.createQueue(queue);
	            // 得到消息生成者【发送者】
	            Topic topicObjReq = session.createTopic(topicMessage.getTopic()+".request");
	            Topic topicObjRes = session.createTopic(topicMessage.getTopic()+".response");
	           // 创建消息发送者
	            TopicPublisher publisher = session.createPublisher(topicObjReq);
	            TopicSubscriber subscriber = session.createSubscriber(topicObjRes);
	            // 设置不持久化，此处学习，实际根据项目决定
	            // 设置持久化模式
	            publisher.setDeliveryMode(DeliveryMode.PERSISTENT);
	            MapMessage mapMes = session.createMapMessage();
	            mapMes.setString("text", topicMessage.getMessage());	            
	            mapMes.setLong("time", System.currentTimeMillis());
	           // System.out.println(map);
	           final String messageId = UUID.randomUUID().toString();
	           //System.out.println(messageId);
	           messageStatus.put(messageId, Boolean.FALSE);
	           // map.setString("messageId", messageId);
	           mapMes.setJMSCorrelationID(messageId);
	           subscriber.setMessageListener(new MessageListener(){
	            	public void onMessage(Message message){
	            		MapMessage tm = (MapMessage)message;
	            		try{
	            		//	tm.acknowledge();
	            		//	System.out.println("Received Message:"+tm.getString("text"));
	            		//	System.out.println(tm.getString("text"));
	            			messageStatus.put(tm.getJMSCorrelationID(), Boolean.TRUE);
	            			System.out.println(tm.getJMSCorrelationID());
	            			if(null != timer){
	            				timer.cancel();
	            			}
	            			// session.commit();
	            		//tm.acknowledge();
	            		}catch(Exception e){
	            			e.printStackTrace();
	            		}finally {
	        	            try {
	        	            	if(session != null){
	        	            		//System.out.println(session.getAcknowledgeMode());
	        	            		session.close();
	        	            	}
	        	                if (null != connection)
	        	                   connection.close();
	        	            } catch (Throwable ignore) {
	        	            	logger.error("MQSenderUtil关闭连接异常："+ignore);
	        	            }
	        	        }
	            	}
	            });
	            publisher.send(mapMes);
	            timer = new Timer();
	            timer.schedule(new TimerTask() {
					@Override
					public void run() {
					 if(!messageStatus.get(messageId)){
			            	System.out.println("The client don't received the message, send SMS later...");
			            }	
					 
				      try {
			            	if(session != null){
			            		//System.out.println(session.getAcknowledgeMode());
			            		session.close();
			            	}
			                if (null != connection)
			                   connection.close();
			            } catch (Throwable ignore) {
			            	System.out.println("MQSenderUtil关闭连接异常 p2："+ignore);
			            	logger.error("MQSenderUtil关闭连接异常 p2："+ignore);
			            }
					timer.cancel();
					}
				}, 3000);
	        } catch (Exception e) {
	        	logger.error("MQSenderUtil异常 p1："+e);
	        } 
    	
    	
		return map;
	}
}
