package com.bac.rabbitmq.publisher;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import org.apache.commons.lang3.SerializationUtils;

import com.bac.messaging.util.ConnectionFactoryUtil;
import com.bac.messaging.util.IConstants;
import com.bac.messaging.util.PojoUtility;
import com.bac.pojo.beans.User;
import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/**
 * 
 * @author Shahnawaz
 *
 */
public class BeanPublisher {

	public static void main(String[] args) {
		User user = PojoUtility.createUser();

		ConnectionFactory factory = ConnectionFactoryUtil.createConnectionFactory();

		try (Connection conn = factory.newConnection()) {

			Channel channel = conn.createChannel();
			channel.exchangeDeclare(IConstants.POJO_EXCHANGE, BuiltinExchangeType.DIRECT);
			//channel.queueDeclare(IConstants.POJO_QUEUE_NAME, true, false, false, null);
			//channel.basicPublish("", IConstants.POJO_QUEUE_NAME, null, PojoUtility.getBytes(user));
			channel.basicPublish(IConstants.POJO_EXCHANGE, IConstants.POJO_EXCHANGE_ROUTING_KEY, null, SerializationUtils.serialize(user));
			System.out.println("[x] Sent '" + user + "'");
		} catch (IOException | TimeoutException e) {
			System.out.println("Exception captured while sending message on [" + IConstants.POJO_QUEUE_NAME + "]");
			e.printStackTrace();
		}
	}

}
