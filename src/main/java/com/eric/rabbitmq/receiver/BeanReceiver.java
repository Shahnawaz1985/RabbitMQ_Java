package com.eric.rabbitmq.receiver;

import java.io.IOException;

import org.apache.commons.lang3.SerializationUtils;

import com.eric.messaging.util.IConstants;
import com.eric.messaging.util.MessagingUtil;
//import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.DeliverCallback;

/**
 * 
 * @author Shahnawaz
 *
 */
public class BeanReceiver {

	public static void main(String[] args) {
			normalConsume();
			consumeFromRetryExchange();
	}
	
	private static void normalConsume() {
		Channel channel = null;
		try {
			channel = MessagingUtil.createAndConfigureChannel(IConstants.POJO_QUEUE_NAME, IConstants.POJO_EXCHANGE, IConstants.POJO_EXCHANGE_ROUTING_KEY);
			channel.basicQos(10);
			
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
				final String userString = (String)SerializationUtils.deserialize(delivery.getBody());
				System.out.println("[x] Received '" + userString + "'");
			};
			
						
			for(int i = 0; i < 10; i++) {
				channel.basicConsume(IConstants.POJO_QUEUE_NAME, true, deliverCallback, consumerTag -> {});
			}			
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			if(null != channel) {
				Connection conn = channel.getConnection();
				if(null != conn) {
					try {
						conn.close();
					} catch (IOException e) {
						e.printStackTrace();
						throw new RuntimeException("Error occurred while closing connection!");
					} 
				}
			}
		}
		
	}

	private static void consumeFromRetryExchange() {
		Channel channel = null;
			try {
				channel = MessagingUtil.createAndConfigureChannel(IConstants.RETRY_QUEUE, IConstants.RETRY_LETTER_EXCHANGE, IConstants.RETRY_LETTER_ROUTING_KEY);
				channel.basicQos(10);
				
				System.out.println("[*] Waiting for messages from BeanPublisher in : " + IConstants.RETRY_QUEUE);
				final DeliverCallback deliverCallback = (consumerTag, delivery) -> {
					// final User user = PojoUtility.fromBytes(delivery.getBody());
					if (null == delivery.getBody()) {
						System.out.println("Null bytes received from :" + IConstants.RETRY_QUEUE);
					} else {
						System.out.println("[x] Received routing-key : '" + delivery.getEnvelope().getRoutingKey()
								+ "', delivery mode :" + delivery.getProperties().getDeliveryMode());
						System.out.println("User in bytes received from exchange :" + delivery.getEnvelope().getExchange());
					}
					final String userString = (String) SerializationUtils.deserialize(delivery.getBody());
					System.out.println("[x] Received '" + userString + "' from :" + delivery.getEnvelope().getExchange());
				};				
				
				for(int i = 0; i < 10; i++) {
					channel.basicConsume(IConstants.RETRY_QUEUE, true, deliverCallback, consumerTag -> {});
				}
			} catch (IOException e) {
				e.printStackTrace();
			}finally {

				if(null != channel) {
					Connection conn = channel.getConnection();
					if(null != conn) {
						try {
							conn.close();
						} catch (IOException e) {
							e.printStackTrace();
							throw new RuntimeException("Error occurred while closing connection!");
						} 
					}
				}
			
			}
	}

}
