package com.eric.messaging.util;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

@RunWith(PowerMockRunner.class)
@PrepareForTest(ConnectionFactoryUtil.class)
@PowerMockIgnore("jdk.internal.reflect.*")
public class TestConnectionFactoryUtil {
	
	private ConnectionFactory connFactory;
	private Connection connection;
	
	@Before
	public void setUp() {
		connFactory = PowerMockito.mock(ConnectionFactory.class);
		connection = PowerMockito.mock(Connection.class);		
	}
	
	@Test
	public void testCreateConnectionFactory() {
			//assertEquals(null, connection);
		try {
			PowerMockito.when(connFactory.newConnection()).thenReturn(connection);
		} catch (IOException | TimeoutException e) {
			e.printStackTrace();
		}
	}

}
