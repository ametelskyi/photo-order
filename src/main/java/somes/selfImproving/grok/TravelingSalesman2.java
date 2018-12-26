package somes.selfImproving.grok;

import java.util.ArrayList;
import java.util.List;

public class TravelingSalesman2 {
    public static void main(String[] args) throws InterruptedException {


        System.out.println(15&1);
        System.out.println(15&2);
        System.out.println(15&3);
        System.out.println(15&4);
        System.out.println(15&5);
        System.out.println(15&6);
        System.out.println(15&7);
        System.out.println(15&12);
        System.out.println(15&100);
        System.out.println(15&1000);
        System.out.println("---");
        System.out.println(15&15);
        System.out.println(15&16);
        System.out.println(15&17);
        System.out.println(15&18);
        System.out.println(15&19);
        System.out.println(15&20);
        System.out.println(15&21);
        System.out.println("---");
        System.out.println(15 % 15);
        System.out.println(16 % 15);
        System.out.println(17 % 15);
        System.out.println(18 % 15);
        System.out.println(19 % 15);
        System.out.println(20 % 15);
        System.out.println(21 % 15);

        String list = "abcdefghklmj";
//        printAllVariants(list);

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
