package ro.nicolaemariusghergu.queryit.executor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;
import ro.nicolaemariusghergu.queryit.BackEndApplication;

import javax.annotation.PostConstruct;
import java.io.*;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.file.Path;
import java.util.Objects;
import java.util.Properties;

@Component
public class ExecutorHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(ExecutorHandler.class);

    @PostConstruct
    private void init() {
        LOGGER.info("ExecutorHandler has started. Starting collecting the data...");
    }

    public static void downloadData(String url, String name) throws IOException {
        URL website = new URL(url);
        String resourcesPath = "src/main/resources/";
        String dataPath = resourcesPath + "data/";

        // I don't want to download the data if already exists
        String filePath = dataPath + name + ".json";
        File file = new File(filePath);
        if (file.exists()) {
            LOGGER.info("Data about " + name + " already exists on local!");
            return;
        }
        try (ReadableByteChannel rbc = Channels.newChannel(website.openStream())) {
            try (FileOutputStream fos = new FileOutputStream(filePath)) {
                fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
            }
        }
    }

}
