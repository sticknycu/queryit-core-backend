package ro.nicolaemariusghergu.queryit.executor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;

@Component
public class ExecutorHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(ExecutorHandler.class);

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

    @PostConstruct
    private void init() {
        LOGGER.info("ExecutorHandler has started. Starting collecting the data...");
    }

}
