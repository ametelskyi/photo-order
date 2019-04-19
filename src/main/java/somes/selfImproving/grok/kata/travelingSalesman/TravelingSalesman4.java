package somes.selfImproving.grok.kata.travelingSalesman;

import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;

public class TravelingSalesman4 {
    public static void main(String[] args) {
        List<String> cities = asList("A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K");

        List<List<String>> routes = findRoutes(cities);
//        for (List<String> route : routes) {
//            for (String city : route) {
//                System.out.print(city);
//            }
//            System.out.println();
//        }

        System.out.println("total " + routes.size());
    }

    private static List<List<String>> findRoutes(List<String> cities) {
        List<List<String>> currentRoutes = new ArrayList<>();
        for (String city : cities) {
            currentRoutes = addNewCityToRoutes(city, currentRoutes);
        }

        return currentRoutes;
    }

    private static List<List<String>> addNewCityToRoutes(String city, List<List<String>> currentRoutes) {
        if(currentRoutes.isEmpty()) {
            currentRoutes.add(asList(city));
            return currentRoutes;
        }

        List<List<String>> extendedRoutes = new ArrayList<>();
        for (List<String> route : currentRoutes) {
            extendedRoutes.addAll(addCityToAllPossibleVariants(city, route));
        }

        return extendedRoutes;
    }

    private static List<List<String>> addCityToAllPossibleVariants(String city, List<String> route) {
        List<List<String>> result = new ArrayList<>();
        for (int i = 0; i <= route.size(); i++) {
            List<String> clone = new ArrayList<>(route);
            clone.add(i, city);
            result.add(clone);
        }
        return result;
    }
}
