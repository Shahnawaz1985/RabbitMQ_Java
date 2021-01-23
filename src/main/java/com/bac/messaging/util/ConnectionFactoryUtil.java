package com.bac.messaging.util;

import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;

import com.rabbitmq.client.ConnectionFactory;

/**
 * 
 * @author Shahnawaz
 *
 */
public class ConnectionFactoryUtil {
	
	public static ConnectionFactory createConnectionFactory() {
		
		ConnectionFactory factory = new ConnectionFactory();
		try {
			factory.setUri(IConstants.CONNECTION_URL);
		} catch (KeyManagementException | NoSuchAlgorithmException | URISyntaxException e1) {
			System.out.println("Exception thrown while configuring uri ["+IConstants.CONNECTION_URL+"]");
			e1.printStackTrace();
		}
		return factory;
	}

}
