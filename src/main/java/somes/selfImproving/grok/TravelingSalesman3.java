package somes.selfImproving.grok;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TravelingSalesman3 {
    public static void main(String[] args) {
        String cities = "ABCDEFGHJKL";
        List<String> paths = findAllPath(cities);
        paths.forEach(p -> System.out.println(p));
    }

    private static List<String> findAllPath(String cities) {
        List<String> initialPaths = new ArrayList<>();
        for (String city : cities.split("")) {
            initialPaths = calculatePathsWithNewCity(initialPaths, city);
        }
        return initialPaths;
    }

    private static List<String> calculatePathsWithNewCity(List<String> paths, String city) {
        if(paths.size() == 0) {
            paths.add(city);
            return Arrays.asList(city);
        } else {
            List<String> extendedPaths = new ArrayList<>();
            for (String path : paths) {
                extendedPaths.addAll(calculatePathsWithNewCity(path, city));
            }
            return extendedPaths;
        }
    }

    private static List<String> calculatePathsWithNewCity(String path, String city) {
        List<String> paths = new ArrayList<>();
        for (int i = 0; i <= path.length(); i++) {
            paths.add(addCityToPlace(path, city, i));
        }
        return paths;
    }

    private static String addCityToPlace(String path, String city, int i) {
        return path.substring(0, i) + city +  path.substring(i);
    }
}
