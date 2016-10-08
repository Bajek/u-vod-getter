package pl.ubytes.getter;

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

    public static void main(String[] args) throws IOException {
        Scrapper scrapper = new TvpPageScrapper(args[0]);
        List<Video> videos = scrapper.parse();
        CurlLinkHelper.printCurlLinks(videos);
    }
}
