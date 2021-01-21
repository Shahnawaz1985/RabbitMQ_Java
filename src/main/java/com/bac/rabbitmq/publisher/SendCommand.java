package com.bac.rabbitmq.publisher;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/**
 * 
 * @author Shahnawaz
 *
 */
public class SendCommand {
	
	private static final String QUEUE_NAME = "hello";
	private static final String CONNECTION_URL = "amqp://guest:guest@localhost:5673";
	
	public static void main(String[] args) {
		
		ConnectionFactory factory = new ConnectionFactory();
		try {
			factory.setUri(CONNECTION_URL);
		} catch (KeyManagementException | NoSuchAlgorithmException | URISyntaxException e1) {
			System.out.println("Exception thrown while configuring uri ["+CONNECTION_URL+"]");
			e1.printStackTrace();
		}
		/*
		 * factory.setHost("localhost"); factory.setPassword("guest");
		 * factory.setUsername("guest"); factory.setPort(5673); factory.set
		 */
		try(Connection conn = factory.newConnection()){
			
			Channel channel  = conn.createChannel();
			channel.queueDeclare(QUEUE_NAME, true, false, false, null);
			String message = "Hello World RabbitMQ!";
			channel.basicPublish("", QUEUE_NAME, null, message.getBytes(StandardCharsets.UTF_8));
			
			System.out.println("[x] Sent '" + message + "'");
		} catch (IOException | TimeoutException e) {
		  //catch (Throwable e) {
			System.out.println("Exception captured while sending message on ["+QUEUE_NAME+"]");
			e.printStackTrace();
		}
	}
			

}
