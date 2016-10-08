package pl.ubytes.getter.scrapper.tvp;

import org.json.JSONArray;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import pl.ubytes.getter.domain.Video;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created Bajek
 * Date 27.12.2015.
 */
public class TvpPageScrapper extends TvpAbstractScrapper {

    private final String PATERN = "section#seriale > ul > li";
    private final String ADDRESS = "http://www.tvp.pl/shared/cdn/tokenizer_v2.php?object_id=";
    private String path;

    public TvpPageScrapper(String path) {
        this.path = path;
    }


    public List<Video> parse() throws IOException {
        Document document = Jsoup.parse(new File(this.path), "UTF-8");
        Elements elements = document.select(PATERN);
        List<Video> videos = new ArrayList();
        for (Element element : elements) {
            Element subElement = element.select("div.item > div.itemContent > div > ul > li")
                    .get(1).select("li > strong.shortTitle > a").get(0);
            String id = subElement.attr("href");
            String[] splitted = id.split("/");
            id = splitted[splitted.length - 2];
            System.out.println("Processing id: " + id);
            String json = Jsoup.connect(ADDRESS + id).get().select("body").text();
            JSONObject obj = new JSONObject(json);
            if (obj.has("formats")) {
                Object formatsObject = obj.get("formats");
                Video video = new Video();
                video.setTitle(obj.get("title").toString());
                JSONObject jsonVideo;
                if (formatsObject instanceof JSONArray) {
                    jsonVideo = this.getWithBiggestBitrate((JSONArray)formatsObject);
                } else {
                    jsonVideo = (JSONObject)formatsObject;
                }
                video.setUrl(jsonVideo.get("url").toString());
                video.setBitrate(Integer.valueOf(jsonVideo.get("totalBitrate").toString()));
                videos.add(video);
            } else {
                System.out.println("Incorrect json. Does not have formats.");
            }
        }
        return videos;
    }

}
