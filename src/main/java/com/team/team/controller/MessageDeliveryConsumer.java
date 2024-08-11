package com.team.team.controller;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class MessageDeliveryConsumer {
    
    @JmsListener(destination = "myQueue")
    public void receiveMessage(String message){
        // This triggers once the message reaches to the destination
        System.out.println("messgae redeivery happened");
    }
}
