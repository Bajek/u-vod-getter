package pl.ubytes.getter;

import org.apache.commons.lang3.StringUtils;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


class TvpAbstractScrapperTest {

    @Test
    void testShouldGetVideo() {

        String json = "{\"url\":\"http:\\/\\/sdt-thinx1-161t.tvp.pl\\/token\\/video\\/vod\\/41727979\\/20200412\\/520504943\\/6de8d749-1717-4b47-90b6" +
                "-125f214724b7\\/video.ism\\/manifest\",\"status\":\"OK\",\"videoId\":41727979,\"platform\":\"sdt-v2\",\"userIp\":\"31.6.70.111\",\"adaptive\":true,\"live\":false,\"title\":\"odc. 256 \\u2013 W\\u0142a\\u015bciwe relacje\",\"duration\":1471,\"countryIsDefault\":false,\"mimeType\":\"application\\/vnd.ms-ss\",\"ads_enabled\":true,\"payment_type\":0,\"distribution_model\":\"AVOD\",\"formats\":[{\"mimeType\":\"application\\/vnd.ms-ss\",\"totalBitrate\":4000000,\"videoBitrate\":0,\"audioBitrate\":0,\"adaptive\":true,\"url\":\"http:\\/\\/sdt-thinx1-161t.tvp.pl\\/token\\/video\\/vod\\/41727979\\/20200412\\/520504943\\/6de8d749-1717-4b47-90b6-125f214724b7\\/video.ism\\/manifest\",\"downloadable\":false},{\"mimeType\":\"video\\/mp4\",\"totalBitrate\":2850000,\"videoBitrate\":0,\"audioBitrate\":0,\"adaptive\":false,\"url\":\"http:\\/\\/sdt-thinx1-161t.tvp.pl\\/token\\/video\\/vod\\/41727979\\/20200412\\/520504943\\/6de8d749-1717-4b47-90b6-125f214724b7\\/video-6.mp4\",\"downloadable\":false},{\"mimeType\":\"video\\/mp4\",\"totalBitrate\":9100000,\"videoBitrate\":0,\"audioBitrate\":0,\"adaptive\":false,\"url\":\"http:\\/\\/sdt-thinx1-161t.tvp.pl\\/token\\/video\\/vod\\/41727979\\/20200412\\/520504943\\/6de8d749-1717-4b47-90b6-125f214724b7\\/video-9.mp4\",\"downloadable\":false},{\"mimeType\":\"video\\/mp4\",\"totalBitrate\":590000,\"videoBitrate\":0,\"audioBitrate\":0,\"adaptive\":false,\"url\":\"http:\\/\\/sdt-thinx1-161t.tvp.pl\\/token\\/video\\/vod\\/41727979\\/20200412\\/520504943\\/6de8d749-1717-4b47-90b6-125f214724b7\\/video-2.mp4\",\"downloadable\":false},{\"mimeType\":\"application\\/x-mpegurl\",\"totalBitrate\":4000000,\"videoBitrate\":0,\"audioBitrate\":0,\"adaptive\":true,\"url\":\"http:\\/\\/sdt-thinx1-161t.tvp.pl\\/token\\/video\\/vod\\/41727979\\/20200412\\/520504943\\/6de8d749-1717-4b47-90b6-125f214724b7\\/video.ism\\/video.m3u8\",\"downloadable\":false},{\"mimeType\":\"video\\/mp4\",\"totalBitrate\":820000,\"videoBitrate\":0,\"audioBitrate\":0,\"adaptive\":false,\"url\":\"http:\\/\\/sdt-thinx1-161t.tvp.pl\\/token\\/video\\/vod\\/41727979\\/20200412\\/520504943\\/6de8d749-1717-4b47-90b6-125f214724b7\\/video-3.mp4\",\"downloadable\":false},{\"mimeType\":\"video\\/mp4\",\"totalBitrate\":1750000,\"videoBitrate\":0,\"audioBitrate\":0,\"adaptive\":false,\"url\":\"http:\\/\\/sdt-thinx1-161t.tvp.pl\\/token\\/video\\/vod\\/41727979\\/20200412\\/520504943\\/6de8d749-1717-4b47-90b6-125f214724b7\\/video-5.mp4\",\"downloadable\":false},{\"mimeType\":\"video\\/mp4\",\"totalBitrate\":5420000,\"videoBitrate\":0,\"audioBitrate\":0,\"adaptive\":false,\"url\":\"http:\\/\\/sdt-thinx1-161t.tvp.pl\\/token\\/video\\/vod\\/41727979\\/20200412\\/520504943\\/6de8d749-1717-4b47-90b6-125f214724b7\\/video-7.mp4\",\"downloadable\":false},{\"mimeType\":\"video\\/mp4\",\"totalBitrate\":1250000,\"videoBitrate\":0,\"audioBitrate\":0,\"adaptive\":false,\"url\":\"http:\\/\\/sdt-thinx1-161t.tvp.pl\\/token\\/video\\/vod\\/41727979\\/20200412\\/520504943\\/6de8d749-1717-4b47-90b6-125f214724b7\\/video-4.mp4\",\"downloadable\":false}]}";

        JSONObject obj = new JSONObject(json);
        String title = StringUtils.stripAccents(obj.get("title").toString());

        assertEquals("odc. 256 – Wlasciwe relacje", title, "Should have title without accent characters.");
    }

    @Test
    void testShouldRemoveSpecialCharacters() {
        String inputString = "odc _!@#$%^&*()_123_/\\,.;' ";
        inputString = inputString.replaceAll("[^A-Za-z0-9 ]", " ")
                .trim().replaceAll(" +", " ");
        System.out.println(inputString);
    }
}
