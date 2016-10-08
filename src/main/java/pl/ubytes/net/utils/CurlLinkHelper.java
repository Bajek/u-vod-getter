package pl.ubytes.net.utils;

import pl.ubytes.net.domain.Video;

import java.util.List;

/**
 * Created by Bartłomiej on 28.12.2015.
 */
public class CurlLinkHelper {

    public static void printCurlLinks(List<Video> videos) {
        for (Video video : videos) {
            System.out.println(video.getUrl() + " -o " + video.getTitle().replace('.', '_').replace(' ', '_'));
        }
    }
}
