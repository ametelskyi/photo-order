package somes.selfImproving.grok.kata.travelingSalesman;

import java.util.ArrayList;
import java.util.List;

public class TravelingSalesman2 {
    public static void main(String[] args) throws InterruptedException {
    }

    private static void printAllVariants(String list) throws InterruptedException {
        List<String> result = new ArrayList<>();
        for (int i = 0; i < list.length(); i++) {
            result = addToTravelList(result, list.substring(i, i+1));
        }


//        for (String strings : result) {
//            System.out.println(strings);
//        }
        System.out.println(result.size());
    }

    private static List<String> addToTravelList(List<String> result, String city) {
        if(result.size() == 0) {
            result.add(city);
        } else {
            List<String> withNewCity = new ArrayList<>();
            for (String path : result) {
                withNewCity.addAll(addCityToList(path, city));
            }
            result = withNewCity;
        }
        return result;
    }

    private static List<String> addCityToList(String path, String city) {
        List<String> result = new ArrayList<>();
        for (int i = 0; i < path.length()+1; i++) {
            result.add(addCityIntoPlace(path, city, i));
        }
        return result;
    }

    private static String addCityIntoPlace(String path, String city, int i) {
        return path.substring(0, i) + city + path.substring(i);
    }
}
