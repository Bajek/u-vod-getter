package pl.ubytes.getter.utils;

import pl.ubytes.getter.domain.Video;

import java.util.Collection;
import java.util.Comparator;

public class CurlLinkHelper {

    private CurlLinkHelper() {

    }

    public static void printCurlLinks(Collection<Video> videos) {

        videos.stream().sorted(Comparator.comparing(Video::getTitle))
                .forEach((Video video) ->
                        System.out.println("wget -O " + video.getTitle()
                                .replaceAll("[^A-Za-z0-9 ]", " ")
                                .trim().replaceAll(" +", " ")
                                + " " + video.getUrl()));

    }
}
