package com.eric.rabbitmq.publisher;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeoutException;

import org.apache.commons.lang3.SerializationUtils;

import com.eric.messaging.util.ConnectionFactoryUtil;
import com.eric.messaging.util.IConstants;
import com.eric.messaging.util.MessagingUtil;
import com.eric.messaging.util.PojoUtility;
import com.eric.pojo.beans.User;
import com.rabbitmq.client.AMQP;
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
		
			Channel channel = MessagingUtil.declareExchange(IConstants.POJO_EXCHANGE, "direct");
			try {
			for(int i = 0; i < 10; i++) {
				final User user =  PojoUtility.createUser();
				final String userString = PojoUtility.generateJson(user);
				//setting delivery mode to 2 to make it persistent.
				AMQP.BasicProperties props = new AMQP.BasicProperties.Builder().deliveryMode(2).build();
				
				//FOR TESTING DEAD LETTER EXCHANGE ROUTING.
				user.setAddress(null);
				
				if(PojoUtility.validateUser(user)) {
					channel.basicPublish(IConstants.POJO_EXCHANGE, IConstants.POJO_EXCHANGE_ROUTING_KEY, props, SerializationUtils.serialize(userString));
				}else {
					channel = MessagingUtil.declareExchange(IConstants.RETRY_LETTER_EXCHANGE, "direct");
					
					Map<String, Object> queue_args = new HashMap<String, Object>();
					queue_args.put(IConstants.DEAD_LETTER_EXCHANGE_KEY, IConstants.RETRY_LETTER_EXCHANGE);
					queue_args.put(IConstants.DEAD_LETTER_EXCHANGE_ROUTING_KEY, IConstants.RETRY_LETTER_ROUTING_KEY);
					
					channel = MessagingUtil.queueDeclareAndBind(IConstants.RETRY_QUEUE, true, false, false, queue_args, IConstants.RETRY_LETTER_EXCHANGE, IConstants.RETRY_LETTER_ROUTING_KEY);
					
					channel.basicPublish(IConstants.RETRY_LETTER_EXCHANGE, IConstants.RETRY_LETTER_ROUTING_KEY, null, SerializationUtils.serialize(userString));
					System.out.println("Dead letter exchange routing triggered!");
				}
				System.out.println("[x] Sent '" + userString + "'");
			}
			} catch (IOException e) {
				e.printStackTrace();
				System.out.println("Exception captured while publishing message on [" + IConstants.POJO_QUEUE_NAME + "]");
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
