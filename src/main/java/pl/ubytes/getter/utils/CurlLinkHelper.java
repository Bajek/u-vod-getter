package pl.ubytes.getter.utils;

import pl.ubytes.getter.domain.Video;

import java.util.List;

public class CurlLinkHelper {

    private CurlLinkHelper() {

    }

    public static void printCurlLinks(List<Video> videos) {
        for (Video video : videos) {
            System.out.println("wget -O " + video.getTitle()
                    .replace('.', '_')
                    .replace(',', '_')
                    .replace('?', '_')
                    .replace('/', '_')
                    .replace(' ', '_')
                    +  " " + video.getUrl());
        }
    }
}
