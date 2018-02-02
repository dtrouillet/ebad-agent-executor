package fr.trouillet.faya.ebad.agent.discovery;

import org.apache.commons.lang.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.FileSystems;
import java.nio.file.FileVisitOption;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.PathMatcher;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.EnumSet;
import java.util.List;
import java.util.stream.Collectors;

/**
 * This class discover batch and app on Windows FileSystem
 */
public class WindowsDiscoveryService implements DiscoveryService {
    private static final Logger LOGGER = LoggerFactory.getLogger(DiscoveryService.class);

    @Value("${ebad.discover.windows.home}")
    private String home;

    @Value("${ebad.discover.windows.batch}")
    private String batch;

    @Override
    public void discoverLocalApplication() {
        LOGGER.debug("Try to list applications");
        List<String> apps = new ArrayList<>();
        try {
            List<Path> fileArrayList = Files.list(Paths.get(home)).filter(Files::isDirectory).collect(Collectors.toList());

            for(Path path : fileArrayList){
                Path path1 = path.resolve(path.toAbsolutePath().toString()+""+batch);
                if(Files.exists(path1)){
                    apps.add(path.getName(path.getNameCount()-1).toString());
                }
            }

            for(String app : apps){
                LOGGER.info(app);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }




    @Override
    public void discoverBatchFromApplication() {
    }
}
