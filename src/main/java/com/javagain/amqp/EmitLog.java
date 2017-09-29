package com.javagain.amqp;

import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class EmitLog {

	private static final String EXCHANGE_NAME = "logs";
	
	public static void main(String[] args)
            throws java.io.IOException, TimeoutException {
		ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        
        // Declare exchange
        channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.FANOUT);
        
        // Build messages
	    String[] messages = {"First message.", "Second message..", "Third message...", 
	    		"Forth message....", "Fifth message....."};
	    
	    // Send messages
	    for (String message : messages) {
	    	channel.basicPublish(EXCHANGE_NAME, "", null, message.getBytes());
	        System.out.println(" [x] Sent '" + message + "'");
	    }
	    
	    // Close resources
	    channel.close();
        connection.close();
	}
	
}
