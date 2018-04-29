package pl.ubytes.getter.scrapper.tvp;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.ubytes.getter.domain.Video;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TvpPageScrapper extends TvpAbstractScrapper {

    private static final Logger LOGGER = LoggerFactory.getLogger(TvpPageScrapper.class);

    private static final String PATERN = "div.subCategoryMobile > div > div";
    private String path;

    public TvpPageScrapper(String path) {
        this.path = path;
    }


    public List<Video> parse() throws IOException {
        LOGGER.info("Parsing videos");
        Document document = Jsoup.parse(new File(this.path), "UTF-8");
        Elements elements = document.select(PATERN);
        List<Video> videos = new ArrayList<>();
        for (Element element : elements) {
            String id = element.select("div").attr("data-id");
            final Video video = this.getVideo(id);
            if (video != null) {
                videos.add(video);
            }
        }
        return videos;
    }

}


