package fr.trouillet.faya.ebad.agent.discovery;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

/**
 * Created by dtrouillet on 31/01/2018.
 */
public class WindowsDiscoveryService implements DiscoveryService {
    private static final Logger LOGGER = LoggerFactory.getLogger(DiscoveryService.class);

    @Override
    public void discoverLocalApplication() {
        LOGGER.debug("Try to list applications");
        try (DirectoryStream<Path> paths = Files.newDirectoryStream(Paths.get("/"))) {
            paths.forEach(System.out::println);
        } catch (IOException e) {
            LOGGER.error("Error when list files in root folder");
        }
    }

    @Override
    public void discoverBatchFromApplication() {
    }
}
