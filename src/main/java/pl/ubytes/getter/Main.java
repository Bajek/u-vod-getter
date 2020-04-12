package pl.ubytes.getter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.ubytes.getter.domain.Video;
import pl.ubytes.getter.scrapper.Scrapper;
import pl.ubytes.getter.scrapper.tvp.HttpTvpPageScrapper;
import pl.ubytes.getter.utils.CurlLinkHelper;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class Main {

    private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);

    public static void main(String... args) throws IOException {
        if (LOGGER.isErrorEnabled()) {
            LOGGER.info("Application has been started with params {}", Arrays.toString(args));
        }
        if (args.length < 1) {
            LOGGER.warn("Please specify input value.");
            System.exit(0);
        }
        Scrapper scrapper = new HttpTvpPageScrapper(args[0], args.length > 1 ? args[1] : null);
        List<Video> videos = scrapper.parse();
        CurlLinkHelper.printCurlLinks(videos);
    }
}
