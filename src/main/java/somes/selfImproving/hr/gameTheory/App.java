package somes.selfImproving.hr.gameTheory;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by anme on 05.06.17.
 */
public class App {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int steps = in.nextInt();

        List<Integer> queries = new ArrayList<>();
        for (int i = 0; i < steps; i++) {
            queries.add(in.nextInt());
        }

        List<Boolean> isFirstWinner = new ArrayList<>();
        isFirstWinner.add(false);//0
        isFirstWinner.add(false);//1
        isFirstWinner.add(true);//2
        isFirstWinner.add(true);//3
        isFirstWinner.add(true);//4
        isFirstWinner.add(true);//5
        isFirstWinner.add(true);//6
        isFirstWinner.add(false);//7
        isFirstWinner.add(false);//8


        for (int i = 0; i < 100; i++) {
            addNextValue(isFirstWinner);
        }

        queries.forEach(q -> System.out.println(isFirstWinner.get(q)? "First" : "Second"));
    }


    private static void addNextValue(List<Boolean> isFirstWinner) {
        int currentIndex = isFirstWinner.size();
        isFirstWinner.add(
                !isFirstWinner.get(currentIndex-2)
                || !isFirstWinner.get(currentIndex-3)
                || !isFirstWinner.get(currentIndex-5));

    }
}
