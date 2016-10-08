package pl.ubytes.net;

import pl.ubytes.net.domain.Video;
import pl.ubytes.net.scrapper.Scrapper;
import pl.ubytes.net.scrapper.tvp.TvpPageScrapper;
import pl.ubytes.net.utils.CurlLinkHelper;

import java.io.IOException;
import java.util.List;

/**
 * Created by Bajek
 * Date 27.12.2015.
 */
public class Main {

    public static void main(String[] args) throws IOException {
        Scrapper scrapper = new TvpPageScrapper(args[0]);
        List<Video> videos = scrapper.parse();
        CurlLinkHelper.printCurlLinks(videos);
    }
}
