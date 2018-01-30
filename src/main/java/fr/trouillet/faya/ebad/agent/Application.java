package fr.trouillet.faya.ebad.agent;

import fr.trouillet.faya.ebad.AgentInfos;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jms.DefaultJmsListenerContainerFactoryConfigurer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.MessageType;

@SpringBootApplication
@EnableJms
public class Application {

    @Bean
    public ActiveMQConnectionFactory activeMQConnectionFactory(){
        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory("admin","admin","tcp://192.168.242.130:61616");
        return activeMQConnectionFactory;
    }

//    @Bean
//    public CachingConnectionFactory cachingConnectionFactory(){
//        return new CachingConnectionFactory(activeMQConnectionFactory());
//    }

//    @Bean
//    public JmsTemplate jmsTemplate(){
//        return new JmsTemplate(activeMQConnectionFactory());
//    }

    @Bean
    public JmsListenerContainerFactory<?> myFactory(ActiveMQConnectionFactory activeMQConnectionFactory,
                                                    DefaultJmsListenerContainerFactoryConfigurer configurer) {
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        // This provides all boot's default to this factory, including the message converter
        configurer.configure(factory, activeMQConnectionFactory);
        // You could still override some of Boot's default if necessary.
        return factory;
    }

    @Bean // Serialize message content to json using TextMessage
    public MessageConverter jacksonJmsMessageConverter() {
        MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
        converter.setTargetType(MessageType.TEXT);
        converter.setTypeIdPropertyName("_type");
        return converter;
    }

    public static void main(String[] args) {
        // Launch the application
        ConfigurableApplicationContext context = SpringApplication.run(Application.class, args);

        JmsTemplate jmsTemplate = context.getBean(JmsTemplate.class);

        // Send a message with a POJO - the template reuse the message converter
        System.out.println("Sending an email message.");

        AgentInfos agentInfos = new AgentInfos();
        String[] app = {"er2","eb1"};
        agentInfos.setApplications(app);
        agentInfos.setEnvironment("int");
        agentInfos.setHost("dlj2eedeva51021");
        agentInfos.setName("agent-1.0-SNAPSHOT");
        jmsTemplate.convertAndSend("agent",agentInfos);
    }

}