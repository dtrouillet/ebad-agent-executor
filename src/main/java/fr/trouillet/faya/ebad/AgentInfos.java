package fr.trouillet.faya.ebad;

import lombok.Data;

/**
 * Created by dtrouillet on 29/01/2018.
 */
@Data
public class AgentInfos {
    private String name;
    private String host;
    private String[] applications;
    private String environment;
}
