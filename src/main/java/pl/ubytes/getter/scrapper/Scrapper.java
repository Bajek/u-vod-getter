package pl.ubytes.getter.scrapper;

import pl.ubytes.getter.domain.Video;

import java.io.IOException;
import java.util.List;

/**
 * Created by Bartłomiej on 28.12.2015.
 */
public interface Scrapper {

    List<Video> parse() throws IOException;

}
