package fr.trouillet.faya.ebad.agent.launcher;

import fr.trouillet.faya.ebad.parent.dto.AgentInfos;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

/**
 * Created by dtrouillet on 31/01/2018.
 */
@Component
public class LauncherReceiver {
    @JmsListener(destination = "agent", selector = "app = 'mv1'")
    public void receiveMessage2(AgentInfos agentInfos) {
        System.out.println("Received <" + agentInfos + ">");
    }
}
