package pl.ubytes.getter.domain;

public class Video {

    private String title;
    private String url;
    private Integer bitrate;

    public Video(String title, String url, Integer bitrate) {
        this.title = title;
        this.url = url;
        this.bitrate = bitrate;
    }

    public Video() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getBitrate() {
        return bitrate;
    }

    public void setBitrate(Integer bitrate) {
        this.bitrate = bitrate;
    }
}
