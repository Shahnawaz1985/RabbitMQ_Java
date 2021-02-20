package com.eric.messaging.util;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/**
 * 
 * @author Shahnawaz
 *
 */
public class MessagingUtil {
	
	/**
	 * @param queueName queueName
	 * @param exchangeName exchangeName
	 * @param routingKey routingKey
	 * @return channel for message consumption.
	 */
	public static Channel createAndConfigureChannel(final String queueName, final String exchangeName, final String routingKey) {
		Channel channel = null;
		ConnectionFactory factory = ConnectionFactoryUtil.createConnectionFactory();
		try  {
			Connection conn = factory.newConnection();
			channel = conn.createChannel();
			channel.queueBind(queueName, exchangeName, routingKey);
		}catch(IOException | TimeoutException e) {
			e.printStackTrace();
			throw new RuntimeException("Error occurred while channel configuring for message consumption!");
		}
		return channel;
	}
	
	
}
