package pl.ubytes.getter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.ubytes.getter.domain.Video;
import pl.ubytes.getter.scrapper.Scrapper;
import pl.ubytes.getter.scrapper.tvp.TvpPageScrapper;
import pl.ubytes.getter.utils.CurlLinkHelper;

import java.io.IOException;
import java.util.List;

/**
 * Created by Bajek
 * Date 27.12.2015.
 */
public class Main {

    final private static Logger LOGGER = LoggerFactory.getLogger(Main.class);

    public static void main(String... args) throws IOException {
        LOGGER.info("Application has been started with params {}", args.toString());
        if (args.length < 1) {
            LOGGER.warn("Please specify input value.");
            System.exit(0);
        }
        Scrapper scrapper = new TvpPageScrapper(args[0]);
        List<Video> videos = scrapper.parse();
        CurlLinkHelper.printCurlLinks(videos);
    }
}
