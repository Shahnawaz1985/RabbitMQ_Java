package com.eric.messaging.util;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.AMQP.Exchange;
import com.rabbitmq.client.AMQP.Queue;

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
	
	/**
	 * @param exchangeName exchangeName
	 * @param exchanegType exchanegType
	 * @return Channel Channel
	 */
	public static Channel declareExchange(String exchangeName, String exchanegType) {
		Channel channel = null;
		ConnectionFactory factory = ConnectionFactoryUtil.createConnectionFactory();
		Exchange.DeclareOk exchange = null;
		try  {
			Connection conn = factory.newConnection();
			channel = conn.createChannel();
			exchange = channel.exchangeDeclare(exchangeName, exchanegType);			
		}catch(IOException | TimeoutException e) {
			e.printStackTrace();
			throw new RuntimeException("Error occurred while channel configuring for message consumption!");
		}
		return channel;
	}
	
	/**
	 * @param queueName queueName
	 * @param durable durable
	 * @param exclusive exclusive
	 * @param autoDelete autoDelete
	 * @param arguments arguments
	 * @param exchangeName exchangeName
	 * @param routingKey routingKey
	 * @return Channel with queue configuration bind and declare.
	 */
	public static Channel queueDeclareAndBind(String queueName, boolean durable, boolean exclusive, boolean autoDelete,
            Map<String, Object> arguments, String exchangeName, String routingKey) {
		Channel channel = null;
		ConnectionFactory factory = ConnectionFactoryUtil.createConnectionFactory();
		Queue.DeclareOk queue_var = null;
		Queue.BindOk queue_bind = null;
		try  {
			Connection conn = factory.newConnection();
			channel = conn.createChannel();
			queue_var = channel.queueDeclare(queueName, durable, exclusive, autoDelete, arguments);
			queue_bind = channel.queueBind(queueName, exchangeName, routingKey);
		}catch(IOException | TimeoutException e) {
			e.printStackTrace();
			throw new RuntimeException("Error occurred while channel configuring for message consumption!");
		}
		return channel;
	}
	
	
}
