package com.ohnet.bootyrabbit.service;

import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.ohnet.bootyrabbit.sender.SenderApplication;
import com.ohnet.bootyrabbit.to.Content;

@Component
public class SenderService {

	 private final RabbitTemplate rabbitTemplate;
	 //private final Receiver receiver;
	 private final ConfigurableApplicationContext context;
	 
	 private final static Logger log = Logger.getLogger(SenderService.class.getName());
	 
	 
     //Autowired
     //public SenderService(final RabbitTemplate rabbitTemplate) {
     //   this.rabbitTemplate = rabbitTemplate;
     //}
	 public SenderService(//Receiver receiver, 
			 			  RabbitTemplate rabbitTemplate, 
			 			  ConfigurableApplicationContext context) {
	        //this.receiver = receiver;
	        this.rabbitTemplate = rabbitTemplate;
	        this.context = context;
	 }
	
	    //-- Scheduled(fixedDelay = 3000L)
	    //public void sendMessage() {
	    //    final Content message = new Content();
	    //    message.setId(UUID.randomUUID());
	    //    message.setContent("A message fo the rabbit..");
	    //    log.info("Sending message...");
	    //    rabbitTemplate.convertAndSend(MessagingApplication.EXCHANGE_NAME, MessagingApplication.ROUTING_KEY, message);
	   // }
	    
	   

	    public void sendMessage(String message) {
		    log.log(Level.INFO, "Sending message...");
	        rabbitTemplate.convertAndSend(SenderApplication.queueName, message);
	        //receiver.getLatch().await(10000, TimeUnit.MILLISECONDS);
	        context.close();
	    }
	    

}
