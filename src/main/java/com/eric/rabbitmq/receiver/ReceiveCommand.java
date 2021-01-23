package com.eric.rabbitmq.receiver;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;

/**
 * 
 * @author Shahnawaz
 *
 */
public class ReceiveCommand {
	
	private static final String QUEUE_NAME = "hello";
	private static final String CONNECTION_URL = "amqp://guest:guest@localhost:5673";
	
	public static void main(String[] args) {
		
		ConnectionFactory factory = new ConnectionFactory();
		//factory.setHost(CONNECTION_URL);
		
		try {
			factory.setUri(CONNECTION_URL);
		} catch (KeyManagementException | NoSuchAlgorithmException | URISyntaxException e1) {
			System.out.println("Exception thrown while configuring uri ["+CONNECTION_URL+"]");
			e1.printStackTrace();
		}
		
		try(Connection conn = factory.newConnection()){
			
			Channel channel  = conn.createChannel();
			channel.queueDeclare(QUEUE_NAME, true, false, false, null);
			System.out.println("[*] Waiting for messages from SendCommnad. Press Ctrl-C to exit.");
			
			final DeliverCallback deliverCallback = (consumerTag, delivery) -> {
				final String message = new String(delivery.getBody(), StandardCharsets.UTF_8);
				System.out.println("[x] Received '" + message + "'");
			};
			
			channel.basicConsume(QUEUE_NAME, true, deliverCallback, consumerTag -> {});			
		} catch (IOException | TimeoutException e) {
			//catch (Throwable e) {
			System.out.println("Exception captured while receiving message on ["+QUEUE_NAME+"]");
			e.printStackTrace();
		}
		 
	}

}
