package com.bac.messaging.util;

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
	
	String CONNECTION_URL = "amqp://guest:guest@localhost:5673";

}
