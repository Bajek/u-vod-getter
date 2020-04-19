package pl.ubytes.getter.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.ubytes.getter.domain.Video;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.Collection;
import java.util.Comparator;

public class CurlLinkHelper {

    private static final Logger LOGGER = LoggerFactory.getLogger(CurlLinkHelper.class);


    private CurlLinkHelper() {

    }

    public static void printCurlLinks(Collection<Video> videos) throws IOException {
        try (PrintWriter fileWriter = new PrintWriter("links")) {
            PrintWriter printWriter = new PrintWriter(fileWriter);
            videos.stream().sorted(Comparator.comparing(Video::getTitle)).forEach((Video video) -> printWriter.println(getUrl(video)));
            printWriter.close();
        }

    }

    private static String getUrl(Video video) {
        LOGGER.info("Generating url for {}", video.getTitle());
        return "wget -O " + video.getTitle().replaceAll("[^A-Za-z0-9]", "_").trim().replaceAll("_+", "_") + " " + video.getUrl();
    }
}
