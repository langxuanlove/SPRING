package com.keytec.activemq.topic;

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



public class ActivemqConsumer_02 {
	private static Logger logger = LoggerFactory.getLogger(ActivemqConsumer_02.class);
  	// ConnectionFactory ：连接工厂，JMS 用它创建连接
	private static TopicConnectionFactory  connectionFactory = null;
        // Connection ：JMS 客户端到JMS Provider 的连接
	private static TopicConnection  connection = null;
	private static TopicSession  session =null;
	public static void consume(String topic) {
  
        // Session： 一个发送或接收消息的线程
        //  TopicSession  session =null;
		
        // Destination ：消息的目的地;消息发送给谁.
        // Destination destination;
        // MessageProducer：消息发送者
        // MessageProducer producer;
        // TextMessage message;
        // 构造ConnectionFactory实例对象，此处采用ActiveMq的实现jar
        connectionFactory = new ActiveMQConnectionFactory(
        		"admin",
        		"admin",
                "tcp://114.112.90.40:61616");
        try {
            // 构造从工厂得到连接对象
            connection = connectionFactory.createTopicConnection();
            connection.setClientID("nnn");
            // 启动
            connection.start();
            // 获取操作连接
            session = connection.createTopicSession(Boolean.FALSE,
                    Session.AUTO_ACKNOWLEDGE);
            // 获取session注意参数值xingbo.xu-queue是一个服务器的queue，须在在ActiveMq的console配置
            //destination = session.createQueue(queue);
            // 得到消息生成者【发送者】
            Topic topicObjRep = session.createTopic(topic+".request");
            Topic topicObjRes = session.createTopic(topic+".response");
            // 创建消息发送者
            TopicSubscriber subscriber = session.createDurableSubscriber(topicObjRep, "ncw-nnn");
           final TopicPublisher publisher = session.createPublisher(topicObjRes);
           publisher.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
            subscriber.setMessageListener(new MessageListener(){
            	public void onMessage(Message message){
            		MapMessage tm = (MapMessage)message;
            		try{
            		//	tm.acknowledge();
            			System.out.println("Received Message:"+tm.getString("text"));
            			//System.out.println(tm.getJMSCorrelationID());
            			MapMessage map = session.createMapMessage();
            			//map.setString("text", "I receviced the message");
            			//map.setString("text", tm.getJMSCorrelationID());
            			//System.out.println(tm.getJMSCorrelationID());
            			map.setJMSCorrelationID(tm.getJMSCorrelationID());
            			System.out.println(tm.getJMSCorrelationID());
        	            map.setLong("time", System.currentTimeMillis());
            			publisher.send(map);
            			
            			//session.commit();
            		//tm.acknowledge();
            		}catch(Exception e){
            			logger.error("MQSenderUtil异常 c1："+e);
            		}
            	}
            });
           
           
        } catch (Exception e) {
        	logger.error("MQSenderUtil异常c2："+e);
        } finally {
        	/* try {
            	if(session != null){
            		session.close();
            	}
                if (null != connection)
                    connection.close();
            } catch (Throwable ignore) {
            	logger.error("MQSenderUtil关闭连接异常："+ignore);
            }*/
        } 
        
    }
   /* public static void consume(String topic) {
    	// ConnectionFactory ：连接工厂，JMS 用它创建连接
    	ActiveMQConnectionFactory  connectionFactory = null;
        // Connection ：JMS 客户端到JMS Provider 的连接
    	Connection  connection = null;
        // Session： 一个发送或接收消息的线程
    	Session  session =null;
    	
        // Destination ：消息的目的地;消息发送给谁.
       // Destination destination;
        // MessageProducer：消息发送者
       // MessageProducer producer;
        // TextMessage message;
        // 构造ConnectionFactory实例对象，此处采用ActiveMq的实现jar
        connectionFactory = new ActiveMQConnectionFactory(

                "tcp://localhost:61616");
        try {
            // 构造从工厂得到连接对象
            connection = connectionFactory.createTopicConnection();
            // 启动
            connection.start();
            // 获取操作连接
            session = connection.createSession(Boolean.FALSE,
                    Session.AUTO_ACKNOWLEDGE);
            // 获取session注意参数值xingbo.xu-queue是一个服务器的queue，须在在ActiveMq的console配置
            //destination = session.createQueue(queue);
            // 得到消息生成者【发送者】
            Topic topicObj = session.createTopic(topic);
         // 创建消息发送者
            MessageConsumer subscriber = session.createConsumer(topicObj);
            subscriber.setMessageListener(new MessageListener(){
            	public void onMessage(Message message){
            		TextMessage tm = (TextMessage)message;
            		try{
            			System.out.println("Received Message:"+tm.toString());
            		}catch(Exception e){
            			e.printStackTrace();
            		}
            	}
            });
            //session.commit();
        } catch (Exception e) {
        	logger.error("MQSenderUtil异常："+e);
        } finally {
            try {
            	if(session != null){
            		session.close();
            	}
                if (null != connection)
                    connection.close();
            } catch (Throwable ignore) {
            	logger.error("MQSenderUtil关闭连接异常："+ignore);
            }
        } 
	
    }*/

    public static void main(String [] agrs)
    throws Exception {
    	//for(int i =0; i<2000; i++){
    	ActivemqConsumer_02.consume("ncw");
    //	}
    }
   }
