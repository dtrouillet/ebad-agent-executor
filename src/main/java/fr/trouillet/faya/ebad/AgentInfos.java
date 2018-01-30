package fr.trouillet.faya.ebad;

import java.util.Arrays;

/**
 * Created by dtrouillet on 29/01/2018.
 */
public class AgentInfos {

    private String name;
    private String host;
    private String[] applications;
    private String environment;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String[] getApplications() {
        return applications;
    }

    public void setApplications(String[] applications) {
        this.applications = applications;
    }

    public String getEnvironment() {
        return environment;
    }

    public void setEnvironment(String environment) {
        this.environment = environment;
    }

    @Override
    public String toString() {
        return "AgentInfos{" +
                "name='" + name + '\'' +
                ", host='" + host + '\'' +
                ", applications=" + Arrays.toString(applications) +
                ", environment='" + environment + '\'' +
                '}';
    }
}
