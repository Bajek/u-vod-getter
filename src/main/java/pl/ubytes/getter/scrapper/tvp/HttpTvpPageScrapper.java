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

    private String pattern = "div.odcinki__item";
    private String path;

    public HttpTvpPageScrapper(String path, String season) {
        this.path = path;
        if (season != null) {
            pattern = pattern + ".s" + season;
        }
    }

    public List<Video> parse() throws IOException {

        Document document = Jsoup.connect(path).get();
        Elements elements = document.select(pattern);
        List<Video> videos = new ArrayList<>();
        for (Element element: elements) {
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
}
