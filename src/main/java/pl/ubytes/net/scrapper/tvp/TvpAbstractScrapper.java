package pl.ubytes.net.scrapper.tvp;

import org.json.JSONArray;
import org.json.JSONObject;
import pl.ubytes.net.scrapper.Scrapper;

/**
 * Created by Bartłomiej on 29.12.2015.
 */
public abstract class TvpAbstractScrapper implements Scrapper {

    protected JSONObject getWithBiggestBitrate(JSONArray formats) {
        JSONObject objectWithBiggestBitrate = null;
        Integer maxBitrate = 0;
        for (int i = 0; i < formats.length(); i++) {
            JSONObject currentFormat = formats.getJSONObject(i);
            Integer curretBitrate = Integer.valueOf(currentFormat.get("totalBitrate").toString());
            if (curretBitrate > maxBitrate) {
                maxBitrate = curretBitrate;
                objectWithBiggestBitrate = currentFormat;
            }
        }
        return objectWithBiggestBitrate;
    }

}
