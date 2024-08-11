package com.team.team.configuration;

import java.util.Arrays;


import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.core.JmsTemplate;

@Configuration
public class ActiveMqConfiguration {

    @Value("${spring.activemq.broker-url}")
    private String brokerUrl;
    
    @Bean
    public ActiveMQConnectionFactory activemMqConnectionFactory(String username, String password, String brokerUrl){
        // Here We can connfigure much more properties based on the requirements, this creates ocnnection to active mq broker
        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory();
        // activeMQConnectionFactory.setUserName(username);
        // activeMQConnectionFactory.setPassword(username);
        // activeMQConnectionFactory.setBrokerURL(brokerUrl);
        activeMQConnectionFactory.setTrustedPackages(Arrays.asList("com.team.team"));
        return activeMQConnectionFactory;
    }

    @Bean
    public JmsListenerContainerFactory<?> jmsListenerContainerFactory(ActiveMQConnectionFactory activeMQConnectionFactory){
        // this was used because the messages from destination was not being dequesd/listening.
        DefaultJmsListenerContainerFactory defaultJmsListenerContainerFactory = new DefaultJmsListenerContainerFactory();
        defaultJmsListenerContainerFactory.setConnectionFactory(activeMQConnectionFactory);
        return defaultJmsListenerContainerFactory;
    }

    @Bean
    public JmsTemplate jmsTemplate(){
        // If we are using jms messaging directly without camel routes this is used
        JmsTemplate jmsTemplate = new JmsTemplate();
        jmsTemplate.setConnectionFactory(activemMqConnectionFactory("admin", "admin", brokerUrl));
        return jmsTemplate;
    }

    
}
