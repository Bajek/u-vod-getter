package pl.ubytes.getter.scrapper.tvp;

import org.json.JSONArray;
import org.json.JSONObject;
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

    private final static Logger LOGGER = LoggerFactory.getLogger(TvpPageScrapper.class);

    private final String PATERN = "div.subCategoryMobile > div > div";
    private final String ADDRESS = "http://www.tvp.pl/shared/cdn/tokenizer_v2.php?object_id=";
    private String path;

    public TvpPageScrapper(String path) {
        this.path = path;
    }


    public List<Video> parse() throws IOException {
        Document document = Jsoup.parse(new File(this.path), "UTF-8");
        Elements elements = document.select(PATERN);
        List<Video> videos = new ArrayList<Video>();
        for (Element element : elements) {
            String id = element.select("div").attr("data-id");
            final Video video = getVideo(id);
            if (video != null) {
                videos.add(video);
            }
        }
        return videos;
    }


    private Video getVideo(String id) throws IOException {
        LOGGER.info("Processing ID: {}", id);
        String json = Jsoup.connect(ADDRESS + id).get().select("body").text();
        JSONObject obj = new JSONObject(json);
        Video video = null;
        if (obj.has("formats")) {
            Object formatsObject = obj.get("formats");
            video = new Video();
            video.setTitle(obj.get("title").toString());
            JSONObject jsonVideo;
            if (formatsObject instanceof JSONArray) {
                jsonVideo = this.getWithBiggestBitrate((JSONArray) formatsObject);
            } else {
                jsonVideo = (JSONObject) formatsObject;
            }
            video.setUrl(jsonVideo.get("url").toString());
            video.setBitrate(Integer.valueOf(jsonVideo.get("totalBitrate").toString()));
        } else {
            System.out.println("Incorrect json. Does not have formats.");
        }

        return video;
    }


}


