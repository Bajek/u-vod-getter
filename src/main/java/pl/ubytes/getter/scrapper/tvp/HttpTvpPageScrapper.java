package pl.ubytes.getter.scrapper.tvp;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import pl.ubytes.getter.domain.Video;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HttpTvpPageScrapper extends TvpAbstractScrapper {

    private static final String PATTERN = "div.strefa-abo__item";
    private String path;

    public HttpTvpPageScrapper(String path) {
        this.path = path;
    }

    public List<Video> parse() throws IOException {

        Document document = Jsoup.connect(path).get();
        Elements elements = document.select(PATTERN);
        List<Video> videos = new ArrayList<>();
        for (Element element: elements) {
            String href = element.select("a").attr("href");
            String[] spplitted = href.split(",");
            Video video = this.getVideo(spplitted[spplitted.length - 1]);
            if (video != null) {
                videos.add(video);
            }
        }
        return videos;
    }
}
