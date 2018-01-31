package fr.trouillet.faya.ebad.agent.discovery;

import org.springframework.stereotype.Service;

/**
 * @author dtrouillet
 * Discover all availables app and batch on the machine
 * Use configured convention
 */
@Service
public interface DiscoveryService {
    void discoverLocalApplication();
    void discoverBatchFromApplication();
}
