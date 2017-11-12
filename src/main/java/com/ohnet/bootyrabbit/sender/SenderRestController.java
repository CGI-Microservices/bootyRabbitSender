package com.ohnet.bootyrabbit.sender;

import java.io.IOException;
import java.util.UUID;
import java.util.concurrent.TimeoutException;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ohnet.bootyrabbit.to.Content;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

@RestController
public class SenderRestController {

	
	@RequestMapping("/helloRest")
    public Content home() throws IOException, TimeoutException {
		Content to = new Content();
		to.setId(UUID.randomUUID());
		to.setContent("Hallo started to send message...");
		
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("localhost");
		Connection connection = factory.newConnection();
		Channel channel = connection.createChannel();
		
		channel.queueDeclare(SenderApplication.queueName, false, false, false, null);
		String message = "First Message";
		channel.basicPublish("", SenderApplication.queueName, null, message.getBytes());
		System.out.println(" [x] Sent '" + message + "'");
	
        return to;
    }
	
	
}
