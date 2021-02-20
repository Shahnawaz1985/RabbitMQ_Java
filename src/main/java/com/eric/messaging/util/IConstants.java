package com.eric.messaging.util;

/**
 * 
 * @author Shahnawaz
 *
 */
public interface IConstants {
	
	
	String QUEUE_NAME = "hello";
	
	String POJO_QUEUE_NAME = "pojo-queue"; 
	
	String POJO_EXCHANGE = "pojo-exchange";

	String POJO_EXCHANGE_ROUTING_KEY = "pojo-key";
	
	String RETRY_LETTER_EXCHANGE = "retry-exchange";
	
	String RETRY_QUEUE = "retry-queue";
	
	String RETRY_LETTER_ROUTING_KEY = "retry-routing-key";
	
	String CONNECTION_URL = "amqp://guest:guest@localhost:5673";
	
	String DEAD_LETTER_EXCHANGE_KEY = "x-dead-letter-exchange";

	String DEAD_LETTER_EXCHANGE_ROUTING_KEY = "x-dead-letter-routing-key";

}
