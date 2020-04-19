package pl.ubytes.getter.scrapper.tvp;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.ubytes.getter.domain.Video;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HttpTvpPageScrapper extends TvpAbstractScrapper {

    private static final Logger LOGGER = LoggerFactory.getLogger(HttpTvpPageScrapper.class);


    private String oldPattern = "div.strefa-abo__item";
    private String newPattern = "div.odcinki__item";

    private String path;

    public HttpTvpPageScrapper(String path, String season) {
        this.path = path;
        if (season != null) {
            newPattern = newPattern + ".s" + season;
        }
    }

    public List<Video> parseNewPage(Document document) throws IOException {

        Elements elements = document.select(newPattern);
        List<Video> videos = new ArrayList<>();
        for (Element element : elements) {
            String href = element.select("a").attr("href");
            href = href.replace("http://vod.tvp.pl/", "");
            String[] spplitted = href.split("/");
            Video video = this.getVideo(spplitted[0]);
            if (video != null) {
                videos.add(video);
            }
        }
        return videos;
    }

    private List<Video> parseOldPage(Document document) throws IOException {
        Elements elements = document.select(oldPattern);
        List<Video> videos = new ArrayList<>();
        for (Element element : elements) {
            String href = element.select("a").attr("href");
            String[] spplitted = href.split(",");
            Video video = this.getVideo(spplitted[spplitted.length - 1]);
            if (video != null) {
                videos.add(video);
            }
        }
        return videos;
    }

    @Override
    public List<Video> parse() throws IOException {
        Document document = Jsoup.connect(path).get();
        Elements elements = document.select(newPattern);

        if (!elements.isEmpty()) {
            LOGGER.info("Using new page format.");
            return parseNewPage(document);
        } else {
            LOGGER.info("Using old page format.");
            return parseOldPage(document);
        }
    }
}
