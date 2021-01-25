package com.eric.rabbitmq.receiver;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import org.apache.commons.lang3.SerializationUtils;

import com.eric.messaging.util.ConnectionFactoryUtil;
import com.eric.messaging.util.IConstants;
import com.eric.messaging.util.PojoUtility;
import com.eric.pojo.beans.User;
import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;

/**
 * 
 * @author Shahnawaz
 *
 */
public class BeanReceiver {

	public static void main(String[] args) {
		ConnectionFactory factory = ConnectionFactoryUtil.createConnectionFactory();
		try (Connection conn = factory.newConnection()) {

			Channel channel = conn.createChannel();
			//channel.exchangeDeclare(IConstants.POJO_EXCHANGE, "direct");
			channel.queueBind(IConstants.POJO_QUEUE_NAME, IConstants.POJO_EXCHANGE, IConstants.POJO_EXCHANGE_ROUTING_KEY);
			System.out.println("[*] Waiting for messages from BeanPublisher. Press Ctrl-C to exit.");

			final DeliverCallback deliverCallback = (consumerTag, delivery) -> {
				//final User user = PojoUtility.fromBytes(delivery.getBody());
				if(null == delivery.getBody()) {
					System.out.println("Null bytes received from :"+IConstants.POJO_QUEUE_NAME);
				}else {
					System.out.println("[x] Received routing-key : '" +
				            delivery.getEnvelope().getRoutingKey() +"', delivery mode :"+delivery.getProperties().getDeliveryMode());
					System.out.println("User in bytes received from exchange :"+delivery.getEnvelope().getExchange());
				}
				final User user = (User)SerializationUtils.deserialize(delivery.getBody());
				System.out.println("[x] Received '" + user + "'");
			};
			channel.basicQos(10);
			for(int i = 0; i < 10; i++) {
				channel.basicConsume(IConstants.POJO_QUEUE_NAME, true, deliverCallback, consumerTag -> {});
			}
		} catch (IOException | TimeoutException e) {
			// catch (Throwable e) {
			System.out.println("Exception captured while receiving message on [" + IConstants.POJO_QUEUE_NAME + "]");
			e.printStackTrace();
		}
	}

}