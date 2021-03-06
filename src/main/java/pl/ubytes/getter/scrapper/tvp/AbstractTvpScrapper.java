package pl.ubytes.getter.scrapper.tvp;

import org.apache.commons.lang3.StringUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.ubytes.getter.domain.Video;
import pl.ubytes.getter.scrapper.Scrapper;

import java.io.IOException;

public abstract class AbstractTvpScrapper implements Scrapper {

    protected static final String ADDRESS = "http://www.tvp.pl/shared/cdn/tokenizer_v2.php?object_id=";
    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractTvpScrapper.class);

    protected JSONObject getWithBiggestBitrate(JSONArray formats) {
        JSONObject objectWithBiggestBitrate = null;
        int maxBitrate = 0;
        for (int i = 0; i < formats.length(); i++) {
            JSONObject currentFormat = formats.getJSONObject(i);
            int currentBitrate = Integer.parseInt(currentFormat.get("totalBitrate").toString());
            String mimeType = currentFormat.get("mimeType").toString();
            if (currentBitrate > maxBitrate && mimeType.contains("video")) {
                maxBitrate = currentBitrate;
                objectWithBiggestBitrate = currentFormat;
            }
        }
        return objectWithBiggestBitrate;
    }

    protected Video getVideo(String id) throws IOException {
        LOGGER.info("Processing ID: {}", id);
        String json = Jsoup.connect(ADDRESS + id).get().select("body").text();
        JSONObject obj = new JSONObject(json);
        if ("NO_PREMIUM_ACCESS".equals(obj.get("status"))) {
            LOGGER.warn("Premium episode.");
            return null;
        }
        Video video = null;
        if (obj.has("formats")) {
            Object formatsObject = obj.get("formats");
            video = new Video();
            video.setTitle(StringUtils.stripAccents(obj.get("title").toString()));
            JSONObject jsonVideo;
            if (formatsObject instanceof JSONArray) {
                jsonVideo = this.getWithBiggestBitrate((JSONArray) formatsObject);
            } else {
                jsonVideo = (JSONObject) formatsObject;
            }
            video.setUrl(jsonVideo.get("url").toString());
            video.setBitrate(Integer.valueOf(jsonVideo.get("totalBitrate").toString()));
        } else {
            LOGGER.warn("Incorrect json. Does not have formats.");
        }

        return video;
    }

}
