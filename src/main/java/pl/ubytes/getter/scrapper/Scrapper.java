package pl.ubytes.getter.scrapper;

import pl.ubytes.getter.domain.Video;

import java.io.IOException;
import java.util.List;

public interface Scrapper {

    List<Video> parse() throws IOException;

}
