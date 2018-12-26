package somes.selfImproving.hr.recursion;

import java.util.Arrays;
import java.util.stream.IntStream;

/**
 * Created by anme on 29.06.17.
 */
public class ThePowerSum {
    private static boolean test = true;

    public static void main(String[] args) {
        if (test) {
            mainTest();
            return;
        }

        String s ;


        int X = 100;
        int N = 3;

        int Y = findBiggestSquare(X, N);
        System.out.println("RES!!! " + addValIfSucc(X, 0, N, 1, Y));

    }

    private static int addValIfSucc(int resVal, int prevSum, int N, int from, int to) {
        if (prevSum + (int) Math.pow(from, N) == resVal) {
            return 1;
        }

        if (from <= to) {
            return
                    addValIfSucc(resVal, prevSum, N, from + 1, to) +
                            addValIfSucc(resVal, prevSum + (int) Math.pow(from, N), N, from + 1, to);
        } else {
            return 0;
        }
    }

    private static int findBiggestSquare(int X, int N) {
        int max = 1;
        while (true) {
            int finalMax = max;
            Integer next = IntStream.range(0, N).collect(
                    () -> Arrays.asList(1),
                    (integers, value) -> integers.set(0, integers.get(0) * finalMax),
                    (integers, integers2) -> {
                    }).get(0);
            if (next > X) return --max;
            max++;
        }
    }

    public static void mainTest() {
        if (findBiggestSquare(95, 2) != 9) throw new RuntimeException("check!");
        if (findBiggestSquare(100, 2) != 10) throw new RuntimeException("check!");
        if (findBiggestSquare(102, 2) != 10) throw new RuntimeException("check!");
        if (findBiggestSquare(100, 3) != 4) throw new RuntimeException("check!");


        if (addValIfSucc(100, 0, 2, 1, 10) != 3) throw new RuntimeException("check!");
        if (addValIfSucc(100, 0, 3, 1, 10) != 1) throw new RuntimeException("check!");
    }
}

