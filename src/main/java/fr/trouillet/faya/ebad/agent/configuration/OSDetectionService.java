package fr.trouillet.faya.ebad.agent.configuration;

import org.springframework.stereotype.Service;

/**
 * @author dtrouillet
 * Some function need to known on which OS this agent is running
 */
@Service
class OSDetectionService {
    enum OS {
        WINDOWS, UNIX, SOLARIS, MACOS
    }

    private static final String OS_NAME = System.getProperty("os.name").toLowerCase();

    static OS osType() throws EbadConfigurationException{
        if(OS_NAME.contains("win")){
            return OS.WINDOWS;
        }
        if(OS_NAME.contains("mac")){
            return OS.MACOS;
        }
        if(OS_NAME.contains("nix") || OS_NAME.contains("nux") || OS_NAME.contains("aix")){
            return OS.UNIX;
        }
        if(OS_NAME.contains("sunos")){
            return OS.SOLARIS;
        }
        throw new EbadConfigurationException();
    }
}
