package com.team.team.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class MessagingService {
    
    @Autowired
    private JmsTemplate jmsTemplate;

    public void sendMessage(String destination, String message){
        // this is a servuce method from jsmtemplate to convert and en the received meesgafes
        jmsTemplate.convertAndSend(destination, message);
    }
}
