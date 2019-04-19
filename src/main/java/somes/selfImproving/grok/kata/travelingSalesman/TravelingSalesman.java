package somes.selfImproving.grok.kata.travelingSalesman;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Arrays.asList;

/**
 * Created by anme on 27.09.18.
 */
public class TravelingSalesman {
    public static void main(String[] args) {
//        List<String> cities = asList("Dnepr", "Pavlograd", "Ternovka", "Boguslav");
        List<String> cities = asList("A", "B", "C", "D", "E", "F", "G", "H", "K", "L");
        long before = System.currentTimeMillis();

//        List<String> allVariants = travelingSalemanLoop(cities);
        List<String> allVariants = travelingSalemanRecursion(cities);

        System.out.println(allVariants.get(allVariants.size() - 1));
        System.out.println("POSSIBLE VARIANTS: " + allVariants.size());
        System.out.println("TIME: " + (System.currentTimeMillis() - before));
    }

    private static List<String> travelingSalemanRecursion(List<String> cities) {
        List<List<String>> vars = rec(new ArrayList<>(), cities);
        List<String> result = new ArrayList<>();
        for (List<String> allCurrentVariant : vars) {
            result.add(allCurrentVariant.stream().collect(Collectors.joining("-->")));
        }
        return result;
    }

    private static List<List<String>> rec(List<List<String>> variants, List<String> newCities) {
        if (newCities.isEmpty()) return variants;

        if (newCities.size() == 1 && variants.isEmpty()) {
            variants.add(asList(newCities.get(0)));
            return variants;
        } else {
            return
                    addToAllVariants(
                            rec(variants, new ArrayList<>(newCities.subList(0, newCities.size() - 1))),
                            newCities.get(newCities.size() - 1)
                    );
        }
    }

    private static List<String> travelingSalemanLoop(List<String> cities) {
        List<List<String>> allCurrentVariants = new ArrayList<>();
        for (String city : cities) {
            if (allCurrentVariants.isEmpty()) {
                allCurrentVariants.add(asList(city));
            } else {
                allCurrentVariants = addToAllVariants(allCurrentVariants, city);
            }
        }

        List<String> result = new ArrayList<>();
        for (List<String> allCurrentVariant : allCurrentVariants) {
            result.add(allCurrentVariant.stream().collect(Collectors.joining("-->")));
        }
        return result;
    }

    private static List<List<String>> addToAllVariants(List<List<String>> prevCities, String city) {
        System.out.println("CURRENT VARIANTS: " + prevCities.size());
        List<List<String>> result = new ArrayList<>();
        for (List<String> prevVariant : prevCities) {
            result.addAll(putNewItemInEveryPossiblePosition(prevVariant, city));
        }
        return result;
    }

    private static List<List<String>> putNewItemInEveryPossiblePosition(List<String> prevVariant, String city) {
        List<List<String>> result = new ArrayList<>();
        for (int i = 0; i < prevVariant.size() + 1; i++) {
            List<String> clonePrevVariants = new ArrayList<>(prevVariant);
            clonePrevVariants.add(i, city);
            result.add(clonePrevVariants);
        }
        return result;
    }
}
