package fr.trouillet.faya.ebad.agent.discovery;

import fr.trouillet.faya.ebad.parent.dto.AgentInfos;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.net.InetAddress;
import java.net.UnknownHostException;

@Component
class DiscoverySender {
    private static final Logger LOGGER = LoggerFactory.getLogger(DiscoverySender.class);
    private static final String QUEUE_MASTER = "master";
    private static final long DELAY = 30L * 1000L;

    @Value("${ebad.environment}")
    private String environment;

    private final JmsTemplate jmsTemplate;
    private final DiscoveryService discoveryService;

    public DiscoverySender(JmsTemplate jmsTemplate, DiscoveryService discoveryService) {
        this.jmsTemplate = jmsTemplate;
        this.discoveryService = discoveryService;
    }

    /**
     * This method is usefull to indicate this agent is alive
     */
    //FIXME DTROUILLET use the discovery service to send app
    @Scheduled(fixedDelay = DELAY)
    public void hearthBeat(){
        discoveryService.discoverLocalApplication();
        String[] tmpApp = {"ER2"};
        AgentInfos agentInfos = new AgentInfos();
        agentInfos.setEnvironment(environment);
        agentInfos.setApplications(tmpApp);
        try {
            agentInfos.setHost(InetAddress.getLocalHost().getHostName());
        } catch (UnknownHostException e) {
            LOGGER.error("Unable to get hostname");
            agentInfos.setHost("UNKNOWN");
        }
        jmsTemplate.setExplicitQosEnabled(true);
        jmsTemplate.setTimeToLive(DELAY);
        jmsTemplate.convertAndSend(QUEUE_MASTER,agentInfos);
        LOGGER.debug("hearthBeat sended");
    }
}
