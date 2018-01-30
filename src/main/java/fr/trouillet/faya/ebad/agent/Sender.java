package fr.trouillet.faya.ebad.agent;


import fr.trouillet.faya.ebad.parent.dto.AgentInfos;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessagePostProcessor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import java.net.InetAddress;
import java.net.UnknownHostException;

@Component
public class Sender {
    private final JmsTemplate jmsTemplate;

    public Sender(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    @Scheduled(fixedDelay = 5000)
    public void healthCheck() throws UnknownHostException {
        AgentInfos agentInfos = new AgentInfos();
        String[] app = {"er2", "eb1"};
        agentInfos.setApplications(app);
        agentInfos.setEnvironment("int");
        agentInfos.setHost(InetAddress.getLocalHost().getHostName());
        agentInfos.setName("agent-1.0-SNAPSHOT");
        jmsTemplate.convertAndSend("agent", agentInfos, new MessagePostProcessor() {
            @Override
            public Message postProcessMessage(Message message) throws JMSException {
                message.setStringProperty("app","er2");
                return message;
            }
        });
    }

}
